package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.medical_recordDto;
import com.pj.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 就诊记录表(MedicalRecord)表服务接口
 *
 * @author makejava
 * @since 2023-07-13 10:54:18
 */
public interface MedicalRecordService {
    /**
     * 根据处方号完成
     * @param prescriptionNum
     * @return
     */
    MedicalRecord searchAllprescriptionNum(String prescriptionNum);

    PageInfo<medical_recordDto> page(String name, int pageNum, int pageSize);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MedicalRecord queryById(Integer id);

    /**
     * 分页查询
     *
     * @param medicalRecord 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<MedicalRecord> queryByPage(MedicalRecord medicalRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param medicalRecord 实例对象
     * @return 实例对象
     */
    MedicalRecord insert(MedicalRecord medicalRecord);

    /**
     * 修改数据
     *
     * @param medicalRecord 实例对象
     * @return 实例对象
     */
    MedicalRecord update(MedicalRecord medicalRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
