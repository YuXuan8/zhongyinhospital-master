package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.PageloginInfor;
import com.pj.entity.LoginInfor;
import com.pj.service.LoginInforService;
import com.pj.util.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录信息表(LoginInfor)表控制层
 *
 * @author makejava
 * @since 2023-07-18 11:10:49
 */
@RestController
@RequestMapping("/api/loginInfor")
public class LoginInforController {
    /**
     * 服务对象
     */
    @Resource
    private LoginInforService loginInforService;

    /**
     * 分页
     * @param pageloginInfor
     * @return
     */
    @GetMapping("/page")
    public Result queryByPage(PageloginInfor pageloginInfor) {
        PageInfo<LoginInfor> page = loginInforService.page(pageloginInfor);
        Result result=new Result();
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setData(page);
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<LoginInfor> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.loginInforService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param loginInfor 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<LoginInfor> add(LoginInfor loginInfor) {
        return ResponseEntity.ok(this.loginInforService.insert(loginInfor));
    }

    /**
     * 编辑数据
     *
     * @param loginInfor 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<LoginInfor> edit(LoginInfor loginInfor) {
        return ResponseEntity.ok(this.loginInforService.update(loginInfor));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.loginInforService.deleteById(id));
    }

}

