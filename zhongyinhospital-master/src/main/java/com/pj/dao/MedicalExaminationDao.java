package com.pj.dao;

import com.pj.entity.MedicalExamination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 体检报告(MedicalExamination)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-14 10:54:53
 */
public interface MedicalExaminationDao {
    /**
     * 根据时间查询体检表
     */
    @Select("select * from medical_examination where create_datetime=#{create_datetime}")
    MedicalExamination create_datetime(Date create_datetime);
    /**
     * 根据就诊id查询就诊表
     */
    @Select("select * from medical_examination where register_id=#{registerId}")
    MedicalExamination selectByRegisterId(Integer registerId);

    /**
     * 根据就诊id查询就诊表
     */
    @Select("select * from medical_examination where prescription_num=#{prescription_num}")
    MedicalExamination selectByprescription_num(String prescription_num);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MedicalExamination queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param medicalExamination 查询条件
     * @param pageable           分页对象
     * @return 对象列表
     */
    List<MedicalExamination> queryAllByLimit(MedicalExamination medicalExamination, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param medicalExamination 查询条件
     * @return 总行数
     */
    long count(MedicalExamination medicalExamination);

    /**
     * 新增数据
     *
     * @param medicalExamination 实例对象
     * @return 影响行数
     */
    int insert(MedicalExamination medicalExamination);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MedicalExamination> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MedicalExamination> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MedicalExamination> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MedicalExamination> entities);

    /**
     * 修改数据
     *
     * @param medicalExamination 实例对象
     * @return 影响行数
     */
    int update(MedicalExamination medicalExamination);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

