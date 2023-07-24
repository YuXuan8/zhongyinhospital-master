package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.MenuDto;
import com.pj.entity.Menu;
import com.pj.entity.User;
import com.pj.service.MenuService;
import com.pj.service.UserService;
import com.pj.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 菜单权限表(Menu)表控制层
 *
 * @author makejava
 * @since 2023-07-05 13:49:21
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;
    @GetMapping("/getMenu")
    public Result getMenu(){
        List<Menu> all = menuService.all();
        List<HashMap<String, Object>> result1 = new ArrayList<>();

// 构建一级菜单
        for (Menu menu : all) {
            HashMap<String, Object> map = new HashMap<>();
            if (menu.getMenuType().equals("M")) {
                map.put("id", menu.getMenuId());
                map.put("title", menu.getMenuName());
                map.put("spread", false);
                result1.add(map);
            }
        }

// 构建二级菜单
        for (Menu menu : all) {
            if (menu.getMenuType().equals("C")) {
                HashMap<String, Object> subMap = new HashMap<>();
                subMap.put("id", menu.getMenuId());
                subMap.put("title", menu.getMenuName());

                // 查找对应的一级菜单
                for (HashMap<String, Object> map : result1) {
                    if (map.get("id").equals(menu.getParentId())) {
                        List<HashMap<String, Object>> children = (List<HashMap<String, Object>>) map.get("children");
                        if (children == null) {
                            children = new ArrayList<>();
                            map.put("children", children);
                        }
                        children.add(subMap);
                        break;
                    }
                }
            }
        }
        Result result = new Result();
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setData(result1);
        return result;

    }

    /**
     * 查询所有父目录
     * @param parentId
     * @return
     */
    @GetMapping
    public Result xia(int parentId){
        Result result=new Result();
        List<MenuDto> seelectbytyoe = menuService.seelectbytyoe("M");
        result.setData(seelectbytyoe);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;

    }

    /**
     * 模糊查询和分页
     * @param menuDto
     * @return
     */
    @GetMapping("/page")
    public Result page(MenuDto menuDto){
        Result result=new Result();
        PageInfo<Menu> page = menuService.page(menuDto);
        result.setData(page);
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 新增一级菜单和二级菜单
     * @param menu
     * @param request
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Menu menu, HttpServletRequest request){
        Result result= null;
        try {
            result = new Result();
            Menu seelectbyname = menuService.seelectbyname(menu.getMenuName());
            if (seelectbyname==null){
                String email = (String) request.getSession().getAttribute("email");
                User user = userService.selectByEmail(email);
                menu.setCreateTime(new Date());
                menu.setIsRefresh("1");
                menu.setCreateBy(user.getUsername());
                int insert = menuService.insert(menu);
                if (insert>0){
                    result.setMessage("新增成功");
                    result.setStatus(Result.RESPONSE_SUCCESS);
                }else {
                    result.setMessage("新增失败");
                    result.setStatus(Result.RESPONSE_FAIL);
                }
            }else {
                result.setMessage("该菜单已存在");
                result.setStatus(Result.RESPONSE_FAIL);
            }
        } catch (Exception e) {
            result.setMessage("该菜单已存在");
            result.setStatus(Result.RESPONSE_FAIL);
        }

        return result;

    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PutMapping
    public Result upd(@RequestBody Menu menu){
        Result result=new Result();
        int update = menuService.update(menu);
        if (update>0){
            result.setMessage("修改成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return  result;
    }
    @DeleteMapping("/{menuId}")
    public Result del(@PathVariable("menuId")int  menuId){
        Result result=new Result();
        int i = menuService.deleteById(menuId);
        if (i>0){
            result.setMessage("删除成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("删除失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return  result;
    }


}

