package com.pj.controller;

import com.pj.entity.Goout;
import com.pj.service.GooutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 药品出库表(Goout)表控制层
 *
 * @author makejava
 * @since 2023-07-18 19:14:53
 */
@RestController
@RequestMapping("goout")
public class GooutController {
    /**
     * 服务对象
     */
    @Resource
    private GooutService gooutService;

    /**
     * 分页查询
     *
     * @param goout       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Goout>> queryByPage(Goout goout, PageRequest pageRequest) {
        return ResponseEntity.ok(this.gooutService.queryByPage(goout, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Goout> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.gooutService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goout 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Goout> add(Goout goout) {
        return ResponseEntity.ok(this.gooutService.insert(goout));
    }

    /**
     * 编辑数据
     *
     * @param goout 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Goout> edit(Goout goout) {
        return ResponseEntity.ok(this.gooutService.update(goout));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.gooutService.deleteById(id));
    }

}

