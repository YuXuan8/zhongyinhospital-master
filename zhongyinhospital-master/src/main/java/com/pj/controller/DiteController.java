package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.DiteDto;
import com.pj.entity.Dite;
import com.pj.service.DicttypeService;
import com.pj.service.DiteService;
import com.pj.util.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典表(Dite)表控制层
 *
 * @author makejava
 * @since 2023-07-16 00:09:20
 */
@RestController
@RequestMapping("/api/dite")
public class DiteController {
    /**
     * 服务对象
     */
    @Resource
    private DiteService diteService;
    @Resource
    private DicttypeService dicttypeService;

    /**
     * 分页查询
     * @return 查询结果
     */
    @GetMapping("/page")
    public Result queryByPage( DiteDto dto) {
        Result result=new Result();
        PageInfo<Dite> ditePageInfo = diteService.queryByPage(dto);
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setData(ditePageInfo);
        result.setTotal(ditePageInfo.getTotal());
        return result;
    }

    /**
     * 根据选择类型查数据
     * @param typeNo
     * @return
     */
    @GetMapping("/getDite")
    public Result getDite(String typeNo){
        Result result=new Result();
        List<DiteDto> diteDtos = diteService.searchTypeno(typeNo);
        result.setData(diteDtos);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Dite> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.diteService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dite 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Dite dite) {
        Result result=new Result();
        Dite insert = diteService.insert(dite);
        if (insert==null){
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("新增失败");
        }else {
            result.setMessage("新增成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }
        return result;
    }

    /**
     * 编辑数据
     *
     * @param dite 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody Dite dite) {
        Result result=new Result();
        Dite update = diteService.update(dite);
        if (update==null){
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }else {
            result.setMessage("修改成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }
        return result;
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id")Integer id) {
        Result result=new Result();
        boolean b = diteService.deleteById(id);
        if (b){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("删除成功");
        }else {
            result.setMessage("删除失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

}

