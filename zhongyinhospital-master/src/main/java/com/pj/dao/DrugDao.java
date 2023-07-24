package com.pj.dao;

import com.pj.entity.Drug;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 药物信息表(Drug)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-09 23:32:22
 */
public interface DrugDao {
    List<Drug> findAll2(@Param("name") String name,@Param("drugType") String drugType,@Param("unit") String unit,@Param("specification") String specification);
    /**
     * 新增药品
     * @param drug
     * @return
     */
    int insert(Drug drug);
    /**
     * 根据药品名查询药品信息
     */
    @Select("select * from drug where name=#{name}")
    Drug searchAllBydrug(String name);
    /**
     * 药物管理列表及模糊查询
     * @return
     */
    List<Drug> findAll1(@Param("name") String name,@Param("drugType") String drugType,@Param("efficacyClassification") String efficacyClassification,@Param("limitStatus") Integer limitStatus);
    @Select("select * from drug")
    List<Drug> findAll();
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
     */ Drug queryById(int id);

    /**
     * 修改
     */

    int update(Drug Drug);


}

