package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.dicttypeDto;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Dicttype;

import java.util.List;

/**
 * 字典类型表(Dicttype)表服务接口
 *
 * @author makejava
 * @since 2023-07-17 10:43:02
 */
public interface DicttypeService {

    Dicttype searchTypeno(String typeNo);

    List<dicttypeDto> searall();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dicttype queryById(Integer id);

    /**
     * 分页查询
     *
     */
    PageInfo<Dicttype> queryByPage(getAllDrug1 getAllDrug1);

    /**
     * 新增数据
     *
     * @param dicttype 实例对象
     * @return 实例对象
     */
    Dicttype insert(Dicttype dicttype);

    /**
     * 修改数据
     *
     * @param dicttype 实例对象
     * @return 实例对象
     */
    int update(Dicttype dicttype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
