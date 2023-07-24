package com.pj.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.pj.dto.ChangePasswordReqVO;
import com.pj.dto.PageUser;
import com.pj.entity.LoginInfor;
import com.pj.entity.Role;
import com.pj.entity.User;
import com.pj.service.LoginInforService;
import com.pj.service.RoleService;
import com.pj.service.UserService;
import com.pj.util.*;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


/**
 * 医护人员信息表(User)表控制层
 *
 * @author makejava
 * @since 2023-07-03 15:14:09
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginInforService loginInforService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     *
     * @param user
     * @param rerememberMe
     * @param request
     * @return
     */
    @RequestMapping("/dologin")
    @ResponseBody
    public Result login(@RequestBody User user, @RequestParam(defaultValue = "false") boolean rerememberMe, HttpServletRequest request) {
        Result result = new Result();
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
//        2 封装请求数据到 token 对象中
        AuthenticationToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword(), rerememberMe);
        //3 调用 login 方法进行登录认证
        HttpClient client = HttpClients.createDefault();
        String userAgent = request.getHeader("User-Agent");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        Browser browser = ua.getBrowser();
        // 要调用的接口方法
        String url = "https://ip.useragentinfo.com/json";
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject = null;
        String ip=null;
        String add=null;
        try {
            HttpResponse res = client.execute(httpGet);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
                 ip = jsonObject.getString("ip");
                String area = jsonObject.getString("area");
                String country = jsonObject.getString("country");
                String province = jsonObject.getString("province");
                String city = jsonObject.getString("city");
                String isp = jsonObject.getString("isp");
                add=country+province+city+area+isp;
                System.out.println(country+province+city+area+isp+"  ip:"+ip);
            }
        } catch (Exception e) {
            result.setMessage("请连接网络后登录");
            result.setStatus(Result.RESPONSE_FAIL);
            return result;
        }
        try {
            subject.login(token);
            request.getSession().setAttribute("email", user.getEmail());
            String email = user.getEmail();
            User user1 = userService.selectByEmail(email);
            user.setUpdateTime(new Date());//更新时间
            user.setLoginIp(ip);//最后登录ip
            user.setLoginDate(new Date());//最后登录时间
            userService.update(user);
            LoginInfor loginInfor=new LoginInfor();
            loginInfor.setLoginIp(ip);
            loginInfor.setLoginAddress(add);
            loginInfor.setUserId(String.valueOf(user1.getId()));
            loginInfor.setDescription(user.getEmail());
            loginInfor.setLoginBroswer(browser.getName());
            loginInfor.setCreateDatetime(new Date());
            loginInforService.insert(loginInfor);
            result.setStatus(Result.RESPONSE_SUCCESS);
        } catch (UnknownAccountException e) {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("账号错误");
        } catch (IncorrectCredentialsException e) {
            result.setMessage("密码错误");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 主页面用户信息
     * @param request
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public Result getUser(HttpServletRequest request) {
        Result result = new Result();
        //获取用户邮箱
        String email = (String) request.getSession().getAttribute("email");
        //查询该用户信息
        User user = userService.selectByEmail(email);
        result.setData(user);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 用户左侧菜单项
     *
     * @param request
     * @return
     */
    @RequestMapping("/getMenuDto")
    @ResponseBody
    public Result roles(HttpServletRequest request) {
        Result result = new Result();
        //获取用户邮箱
        String email = (String) request.getSession().getAttribute("email");
        //使用邮箱查询相关菜单信息
        List<MenuDto> menuDtos = userService.role(email);
        result.setData(menuDtos);
        return result;
    }

    /**
     * 注册
     *
     * @param username
     * @param email
     * @param password
     * @param roleName
     * @return
     * @throws UnknownHostException
     */
    @PostMapping("/doregister")
    @ResponseBody
    public Result save(String username, String email, String password, String roleName, String code, HttpServletRequest request) throws UnknownHostException {
        Result result = new Result();
//        String code1 = (String) request.getSession().getAttribute("code");
        String code1 = (String) redisTemplate.opsForValue().get(email);
        if (code1.equals(code)) {
            //        创建用户对象进行补全信息和添加
            User user = new User();
            user.setUsername(username);
            user.setPhone("");
            user.setDelFlag("0");
            user.setEmail(email);
            user.setPlainPassword(password);//原始密码
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            password = md5.digestHex(password);
            user.setPassword(password);//加密后的密码
            user.setCreateDatetime(new Date());//创建时间
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            user.setLoginIp(ipAddress);//ip
            user.setLoginDate(new Date());//最后登录时间
            //根据前端传递的用户权限 查出来权限对应的id 和备注
            Role role = roleService.selectByRole(roleName);
            //添加
            int insert = userService.insert(user, role.getId(), role.getRole());
            if (insert > 0) {
                redisTemplate.delete(email);
                result.setStatus(Result.RESPONSE_SUCCESS);
                result.setMessage("注册成功,请返回登录");
            } else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("注册失败请联系管理员重试");
            }
        } else {
            result.setMessage("验证码输入错误");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 正则表达式验证邮箱是否可用
     */
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 发送邮箱验证
     *
     * @param email
     * @param request
     * @return
     */
    @RequestMapping("/code")
    @ResponseBody
    public Result code(String email, HttpServletRequest request) {
        Result result = new Result();
        if (isValidEmail(email)) {//判断邮箱是否可用可用判断数据库是否存在相同账户
            System.out.println("邮箱格式有效");
            result.setMessage("邮箱格式有效");
            User user = userService.selectByEmail(email);
            if (user != null) {
                result.setMessage("邮箱已被注册请更换");
                result.setStatus(Result.RESPONSE_FAIL);
            } else {
                //生成随机四位数的验证码
                String code = ValidateCodeUtils.generateValidateCode(4).toString();
                System.out.println(code);
//                request.getSession().setAttribute("code", code);
                //qq邮箱发送
                EmailUtil.sendAuthCodeEmail(email, code);
                redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
                result.setStatus(Result.RESPONSE_SUCCESS);
            }
        } else {
            System.out.println("邮箱格式无效");
            result.setMessage("邮箱格式无效");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 个人中心修改信息
     * @param user
     * @return
     */
    @PutMapping("/api/user/update")
    public Result updgrxx(@RequestBody User user){
        Result result=new Result();
        int update = userService.update(user);
        if (update>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }
    /**
     * 修改密码
     * @param changePasswordReqVO
     * @param request
     * @return
     */
    @PostMapping("/api/user/changePassword")
    public Result changePassword(@RequestBody ChangePasswordReqVO changePasswordReqVO,HttpServletRequest request){
        Result result=new Result();
        String email = (String) request.getSession().getAttribute("email");//根据信息查询存在在数据库密码
        User user = userService.selectByEmail(email);//当前登录信息
        String password = changePasswordReqVO.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (password.equals(user.getPassword())){
            if (changePasswordReqVO.getNewPassword().equals(changePasswordReqVO.getOkPassword())){
                String newPassword = changePasswordReqVO.getNewPassword();
                newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
                if (newPassword.equals(user.getPassword())){
                    result.setMessage("新密码不能和老密码一致");
                    result.setStatus(Result.RESPONSE_FAIL);
                }else {
                    user.setPassword(newPassword);
                    user.setPlainPassword(changePasswordReqVO.getNewPassword());
                    user.setPwdUpdateDate(new Date());
                    int update = userService.update(user);
                    if (update>0){
                        result.setMessage("修改成功");
                        result.setStatus(Result.RESPONSE_SUCCESS);
                    }else {
                        result.setMessage("密码修改失败");
                        result.setStatus(Result.RESPONSE_FAIL);
                    }
                }
            }else {
                result.setMessage("请确认两次输入的密码一致");
                result.setStatus(Result.RESPONSE_FAIL);
            }
        }else {
            result.setMessage("原密码输入错误");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 管理员管理模糊和分页
     * @param pageUser
     * @return
     */
    @GetMapping("/api/user/page")
    public Result page(PageUser pageUser){
        Result result=new Result();
        PageInfo<User> page = userService.page(pageUser);
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setTotal(page.getTotal());
        result.setData(page);
        return result;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/api/user")
    public Result add(@RequestBody User user){
        System.out.println(user);
        Result result=new Result();
        int save = userService.save(user);
        if (save>0){
            result.setMessage("添加成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("添加失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @GetMapping("/api/user/resetPassword/{id}")
    public Result add(@PathVariable("id")int id){
        Result result=new Result();
        User user=new User();
        user.setId(id);
        String password = "123";
        user.setPlainPassword(password);
        String password1 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password1);
        user.setPwdUpdateDate(new Date());
        int update = userService.update(user);
        if (update>0){
            result.setMessage("重置成功,默认密码为123");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("重置失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 管理员管理修改
     * @param user
     * @return
     */
    @PutMapping("/api/user")
    public  Result upd(@RequestBody User user){
        Result upd = userService.upd(user);
        return upd;
    }
    @DeleteMapping("/api/user/{id}")
    public Result save(@PathVariable("id") int id) {
        Result del = userService.del(id);
        return del;
    }


}

