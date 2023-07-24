package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.RoleDao;
import com.pj.dao.UserDao;
import com.pj.dao.UserRoleDao;
import com.pj.dto.PageUser;
import com.pj.dto.UserDto;
import com.pj.entity.Role;
import com.pj.entity.User;
import com.pj.entity.UserRole;
import com.pj.service.DepartmentService;
import com.pj.service.UserService;
import com.pj.util.DateUtil;
import com.pj.util.MenuDto;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 医护人员信息表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-07-03 15:14:10
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleDao roleDao;


    /**
     *新增用户
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int save(User user) {
        user.setDelFlag("0");
        user.setCreateDatetime(new Date());
        String password = user.getPassword();
        String password1 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password1);
        user.setPlainPassword(password);
        int update = userDao.insert(user);
        List<Integer> roleIds = user.getRoleIds();
        int insert = 0;
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            Role role = roleDao.queryById(roleId);
            userRole.setUid(user.getId());
            userRole.setRoleId(roleId);
            userRole.setDescription(role.getRole());
            userRole.setCreateDatetime(new Date());
            insert = userRoleDao.insert(userRole);
        }
        if (update > 0 && insert > 0) {
            return 1;
        } else {
            return 0;
        }


    }

    @Override
    public PageInfo<User> page(PageUser pageUser) {
        if (pageUser.getRole()==""||pageUser.getRole()==null){
            pageUser.setRole("%");
        }
        PageHelper.startPage(pageUser.getPage(),pageUser.getLimit());
        List<User> mohu = userDao.mohu(pageUser.getRole());
        PageInfo<User> pageInfo=new PageInfo<>(mohu);
        return pageInfo;
    }

    /**
     *根据邮箱查询用户
     * @param email
     * @return
     */
    @Override
    public User selectByEmail(String email) {
        User user = userDao.selectByEmail(email);
        return user;
    }

    /**
     * 查询用户可用菜单及权限
     * @param email
     * @return
     */
    @Override
    public List<MenuDto> role(String email) {
        List<MenuDto> menuDtoList = userDao.role(email);
        return menuDtoList;
    }

    /**
     * 添加用户和用户权限
     * @param user
     * @param id
     * @param description
     * @return
     */
    @Override
    @Transactional//配置事务
    public int insert(User user,int id,String description) {
        //添加普通用户
        int insert = userDao.insert(user);
        //权限对象
        UserRole userRole=new UserRole();
        userRole.setRoleId(id);
        userRole.setUid(user.getId());//这里的id时mybatis 添加完返回给我们的
        userRole.setCreateDatetime(new Date());//创建时间
        userRole.setDescription(description);//备注
        userRoleDao.insert(userRole);//添加权限
        return insert;
    }

    @Override
    public int update(User user) {
        int update = userDao.update(user);
        return update;
    }

    /**
     * 查询科室下的医生
     * @param departmentId
     * @param departmentType
     * @return
     */
    @Override
    public List<UserDto> departmentId(int departmentId, int departmentType) {
        List<UserDto> userDaos = userDao.departmentId(departmentId, departmentType);
        for (UserDto dao : userDaos) {
            String currentDateToString = DateUtil.getCurrentDateToString();//当前年月日
            String updateTime = String.valueOf(dao.getUpdateTime());
            String s = DateUtil.DateTimeToDate(updateTime);//医生挂号更新时间
            if (currentDateToString.equals(s)){
                dao.setNowNum(0);
                dao.setUpdateTime(new Date());
            }
        }
        return userDaos;
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @Override
    public Result upd(User user) {
        user.setUpdateTime(new Date());
        int update = userDao.update(user);
        List<Integer> roleIds = user.getRoleIds();
       userRoleDao.deletbyuid(user.getId());
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            Role role = roleDao.queryById(roleId);
            userRole.setUid(user.getId());
            userRole.setRoleId(roleId);
            userRole.setDescription(role.getRole());
            userRole.setCreateDatetime(new Date());
           userRoleDao.insert(userRole);
        }
        Result result = new Result();
        if (update > 0 ) {
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("修改成功");
            return result;
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("修改失败");
            return result;
        }
    }

    @Override
    public Result del(int id) {
        User user = userDao.queryById(id);
        user.setDelFlag("2");
        int update = userDao.update(user);
        Result result=new Result();
        if (update>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("删除成功");
            return result;
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("删除失败");
            return result;
        }
    }


}
