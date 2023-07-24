package com.pj.dao;

import com.pj.entity.TollTakedrug;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 划价收费-拿药信息表(TollTakedrug)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-12 16:56:24
 */
public interface TollTakedrugDao {
    /**
     * 根据处方号查询
     * @param prescription_num
     * @return
     */
    @Select("select * from toll_takedrug where prescription_num=#{prescription_num}")
    TollTakedrug searchAllByprescription_num(String prescription_num);

    @Select("SELECT * FROM toll_takedrug WHERE patient_id=#{patientId} and taking_drug_status=0 and create_datetime=#{createDatetime}")
    TollTakedrug searchAllByIdandzt(@Param("patientId") int patientId, @Param("createDatetime") Date createDatetime);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TollTakedrug queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param tollTakedrug 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<TollTakedrug> queryAllByLimit(TollTakedrug tollTakedrug, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tollTakedrug 查询条件
     * @return 总行数
     */
    long count(TollTakedrug tollTakedrug);

    /**
     * 新增数据
     *
     * @param tollTakedrug 实例对象
     * @return 影响行数
     */
    int insert(TollTakedrug tollTakedrug);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TollTakedrug> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TollTakedrug> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TollTakedrug> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TollTakedrug> entities);

    /**
     * 修改数据
     *
     * @param tollTakedrug 实例对象
     * @return 影响行数
     */
    int update(TollTakedrug tollTakedrug);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

