package com.pj.dao;

import com.pj.dto.medical_recordDto;
import com.pj.entity.MedicalRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 就诊记录表(MedicalRecord)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-13 10:54:17
 */
public interface MedicalRecordDao {
    /**
     * 根据姓名模糊查询
     */
    @Select("SELECT mr.*, p.card_id, p.`name`, ttd.taking_drug_status\n" +
            "        FROM `medical_record` mr\n" +
            "                 join register r on mr.register_id = r.id\n" +
            "                 join toll_takedrug ttd on mr.prescription_num = ttd.prescription_num\n" +
            "                 join patient p on r.patient_id = p.card_id\n" +
            "        where r.doctor like concat('%', #{name}, '%')\n" +
            "           or p.name like concat('%', #{name}, '%')")
    List<medical_recordDto>  mohu(String name);
    /**
     * 根据处方号查询
     */
    @Select("select * from medical_record where prescription_num=#{prescriptionNum}")
    MedicalRecord searchAllprescriptionNum(String prescriptionNum);

    /**
     * 门珍id
     * @param
     * @return
     */
    @Select("select * from medical_record where register_id=#{registerId}")
    MedicalRecord searchAllregisterId(int registerId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MedicalRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param medicalRecord 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<MedicalRecord> queryAllByLimit(MedicalRecord medicalRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param medicalRecord 查询条件
     * @return 总行数
     */
    long count(MedicalRecord medicalRecord);

    /**
     * 新增数据
     *
     * @param medicalRecord 实例对象
     * @return 影响行数
     */
    int insert(MedicalRecord medicalRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MedicalRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MedicalRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MedicalRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MedicalRecord> entities);

    /**
     * 修改数据
     *
     * @param medicalRecord 实例对象
     * @return 影响行数
     */
    int update(MedicalRecord medicalRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

