package com.pj.dao;

import com.pj.dto.DiteDto;
import com.pj.entity.Dite;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 字典表(Dite)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-16 00:09:21
 */
public interface DiteDao {

    List<Dite> mohu(@Param("typeno") String typeno,@Param("id") Integer id);
    //根据科室查询id
    @Select("select * from dite where name=#{name}")
    Dite byname(String name);
    /**
     * 根据名字查询机型是否存在
     */
    @Select("select * from dite where name=#{name} and typeNo=#{typeNo}")
    Dite searchAllByjix(@Param("name") String name,@Param("typeNo") String typeNo);
    /**
     * 药剂名称 和功效分类
     * @param typeNo
     * @return
     */
    @Select("select  name from dite where typeNo=#{typeNo}")
    List<String> searchTypenoStrings(String typeNo);

    @Select("select  name,id from dite where typeNo=#{typeNo}")
    List<DiteDto> searchTypeno(String typeNo);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dite queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param dite     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Dite> queryAllByLimit(Dite dite, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dite 查询条件
     * @return 总行数
     */
    long count(Dite dite);

    /**
     * 新增数据
     *
     * @param dite 实例对象
     * @return 影响行数
     */
    int insert(Dite dite);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dite> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dite> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dite> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dite> entities);

    /**
     * 修改数据
     *
     * @param dite 实例对象
     * @return 影响行数
     */
    int update(Dite dite);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

