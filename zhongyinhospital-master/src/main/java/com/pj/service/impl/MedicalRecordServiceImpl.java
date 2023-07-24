package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dto.medical_recordDto;
import com.pj.entity.MedicalRecord;
import com.pj.dao.MedicalRecordDao;
import com.pj.service.MedicalRecordService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 就诊记录表(MedicalRecord)表服务实现类
 *
 * @author makejava
 * @since 2023-07-13 10:54:18
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Resource
    private MedicalRecordDao medicalRecordDao;

    /**
     * 根据处方好查询
     * @param prescriptionNum
     * @return
     */
    @Override
    public MedicalRecord searchAllprescriptionNum(String prescriptionNum) {
        MedicalRecord medicalRecord = medicalRecordDao.searchAllprescriptionNum(prescriptionNum);
        return medicalRecord;
    }

    /**
     * 门诊记录
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<medical_recordDto> page(String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if (name == null || name.equals("")) {
            name="%";
        }
        List<medical_recordDto> mohu = medicalRecordDao.mohu(name);
        PageInfo<medical_recordDto> pageInfo=new PageInfo<>(mohu);
        return pageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MedicalRecord queryById(Integer id) {
        return this.medicalRecordDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param medicalRecord 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<MedicalRecord> queryByPage(MedicalRecord medicalRecord, PageRequest pageRequest) {
        long total = this.medicalRecordDao.count(medicalRecord);
        return new PageImpl<>(this.medicalRecordDao.queryAllByLimit(medicalRecord, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param medicalRecord 实例对象
     * @return 实例对象
     */
    @Override
    public MedicalRecord insert(MedicalRecord medicalRecord) {
        this.medicalRecordDao.insert(medicalRecord);
        return medicalRecord;
    }

    /**
     * 修改数据
     *
     * @param medicalRecord 实例对象
     * @return 实例对象
     */
    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        this.medicalRecordDao.update(medicalRecord);
        return this.queryById(medicalRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.medicalRecordDao.deleteById(id) > 0;
    }
}
