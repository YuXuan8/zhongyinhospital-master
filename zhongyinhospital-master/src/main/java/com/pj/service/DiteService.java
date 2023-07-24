package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.DiteDto;
import com.pj.entity.Dite;

import java.util.List;

/**
 * 字典表(Dite)表服务接口
 *
 * @author makejava
 * @since 2023-07-16 00:09:21
 */
public interface DiteService {


    List<DiteDto> searchTypeno(String typeNo);
    /***
     * 根据姓名查询id
     * @param name
     * @return
     */
    Dite byname(String name);
    /**
     * 新增
     */
    int insert1(Dite dite);
    /**
     * 根据名字查询机型是否存在
     */
    Dite searchAllByjix(String name,String typeNo);

    List<String> searchTypenoStrings(String typeNo);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dite queryById(Integer id);

    /**
     * 分页查询
     *
     */
    PageInfo<Dite> queryByPage(DiteDto dto);

    /**
     * 新增数据
     *
     * @param dite 实例对象
     * @return 实例对象
     */
    Dite insert(Dite dite);

    /**
     * 修改数据
     *
     * @param dite 实例对象
     * @return 实例对象
     */
    Dite update(Dite dite);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
