package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.dicttypeDto;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Dicttype;
import com.pj.service.DicttypeService;
import com.pj.util.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典类型表(Dicttype)表控制层
 *
 * @author makejava
 * @since 2023-07-17 10:43:01
 */
@RestController
@RequestMapping("/api/dicttype")
public class DicttypeController {
    /**
     * 服务对象
     */
    @Resource
    private DicttypeService dicttypeService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result queryByPage(getAllDrug1 getAllDrug1) {
        Result result=new Result();
        PageInfo<Dicttype> pageInfo = dicttypeService.queryByPage(getAllDrug1);
        result.setData(pageInfo);
        result.setTotal(pageInfo.getTotal());
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
    public ResponseEntity<Dicttype> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.dicttypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dicttype 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Dicttype dicttype) {
        Dicttype insert = dicttypeService.insert(dicttype);
        Result result=new Result();
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
     * @param dicttype 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody Dicttype dicttype) {
        Result result=new Result();
        int update = dicttypeService.update(dicttype);
        if (update>0){
            result.setMessage("修改成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
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
        boolean b = dicttypeService.deleteById(id);
        if (b){
            result.setMessage("删除成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("删除失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }
    //查询列表
    @GetMapping("/getDictType")
    public Result selectgetDictType(){
        Result result=new Result();
        List<dicttypeDto> searall = dicttypeService.searall();
        System.out.println(searall);
        result.setData(searall);
        return  result;
    }

}

