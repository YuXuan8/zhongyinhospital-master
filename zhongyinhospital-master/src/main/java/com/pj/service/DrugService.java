package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.entity.Drug;

import java.util.List;

/**
 * 药物信息表(Drug)表服务接口
 *
 * @author makejava
 * @since 2023-07-09 23:32:23
 */
public interface DrugService {
    PageInfo<Drug> findByPage1(Integer pageNum, Integer pageSize,String drug,String drugType_search,String unit,String specification);

    /**
     * 新增药品
     */
    int insert(Drug drug);
    /**
     * 根据药品名查询药品信息
     * @param name
     * @return
     */
    Drug searchAllBydrug(String name);

    int update(Drug Drug);

    /**
     * 药物管理列表及模糊查询
     * @return
     */
    List<Drug> findAll1(String name,String drugType,String efficacyClassification,Integer limitStatus);

    List<Drug> findAll();
    /**
     * 分页
     */
    PageInfo<Drug> findByPage(Integer pageNum, Integer pageSize,String drug,String drugType_search,String efficacyClassification_search,Integer limitStatus_search);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据id查询药品信息
     * @param id
     * @return
     */
    Drug queryById(int id);

}
