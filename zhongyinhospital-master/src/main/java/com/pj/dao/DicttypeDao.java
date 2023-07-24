package com.pj.dao;

import com.pj.dto.dicttypeDto;
import com.pj.entity.Dicttype;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 字典类型表(Dicttype)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-17 10:43:02
 */
public interface DicttypeDao {
    /**
     * 查询编号
     */
    @Select("select * from dicttype where  typeNo=#{typeNo}")
    Dicttype searchTypeno(String typeNo);
    /**
     * 查询类型和编号
     * @return
     */
    @Select("select typeNo,name from dicttype")
    List<dicttypeDto> searall();
    /**
     * 模糊查询
     */
    @Select("select * from dicttype where name like concat('%',#{name},'%')")
    List<Dicttype> selecybyDicttype(String name);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dicttype queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param dicttype 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Dicttype> queryAllByLimit(Dicttype dicttype, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dicttype 查询条件
     * @return 总行数
     */
    long count(Dicttype dicttype);

    /**
     * 新增数据
     *
     * @param dicttype 实例对象
     * @return 影响行数
     */
    int insert(Dicttype dicttype);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dicttype> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dicttype> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dicttype> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dicttype> entities);

    /**
     * 修改数据
     *
     * @param dicttype 实例对象
     * @return 影响行数
     */
    int update(Dicttype dicttype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

