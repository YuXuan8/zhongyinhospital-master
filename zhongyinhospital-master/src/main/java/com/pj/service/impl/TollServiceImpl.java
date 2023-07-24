package com.pj.service.impl;

import com.pj.dao.MedicalExaminationDao;
import com.pj.dao.PatientDao;
import com.pj.dao.RegisterDao;
import com.pj.dto.patientDto;
import com.pj.entity.MedicalExamination;
import com.pj.entity.Patient;
import com.pj.entity.Register;
import com.pj.entity.Toll;
import com.pj.dao.TollDao;
import com.pj.service.TollService;
import com.pj.util.GetCardIdInforReqVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收费记录表(Toll)表服务实现类
 *
 * @author makejava
 * @since 2023-07-14 21:38:05
 */
@Service
public class TollServiceImpl implements TollService {
    @Resource
    private TollDao tollDao;
    @Resource
    private PatientDao patientDao;
    @Resource
    private RegisterDao registerDao;
    @Resource
    private MedicalExaminationDao medicalExaminationDao;

    /**
     * 体检后查询患者基本信息
     * @param cardIdInforReqVO
     * @return
     */
    @Override
    public patientDto getexaminationtoll(GetCardIdInforReqVO cardIdInforReqVO) {
        Patient patient = patientDao.selectByCardId(cardIdInforReqVO.getCardId());//前端查出来的信息 查询基本信息
        List<Register> registers = registerDao.searchAll3(cardIdInforReqVO.getCardId(), 1);//根据挂科类型和状态查询就诊表
        System.out.println(registers+"111111111111111111111111");
        patientDto patientDto = null;
        if (registers.size()>0){
            for (Register register : registers) {
                //根据就诊时间查询对应的体检表
                MedicalExamination medicalExamination = medicalExaminationDao.create_datetime(register.getCreateDatetime());
                patientDto = new patientDto();//创建增强对象
                BeanUtils.copyProperties(patient, patientDto);//拷贝普通参数
                patientDto.setBodyTemperature(medicalExamination.getBodyTemperature());//添加业务字段
                patientDto.setHeartRate(medicalExamination.getHeartRate());
                patientDto.setBloodPressure(medicalExamination.getBloodPressure());
                patientDto.setExaminationCost(medicalExamination.getExaminationCost());
                patientDto.setPrescriptionNum(medicalExamination.getPrescriptionNum());
                patientDto.setPulse(medicalExamination.getPulse());
                patientDto.setRegisterId(medicalExamination.getRegisterId());
            }
        }else {
           return null;
        }

        return patientDto ;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Toll queryById(Integer id) {
        return this.tollDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param toll        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Toll> queryByPage(Toll toll, PageRequest pageRequest) {
        long total = this.tollDao.count(toll);
        return new PageImpl<>(this.tollDao.queryAllByLimit(toll, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param toll 实例对象
     * @return 实例对象
     */
    @Override
    public Toll insert(Toll toll) {
        this.tollDao.insert(toll);
        return toll;
    }

    /**
     * 修改数据
     *
     * @param toll 实例对象
     * @return 实例对象
     */
    @Override
    public Toll update(Toll toll) {
        this.tollDao.update(toll);
        return this.queryById(toll.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tollDao.deleteById(id) > 0;
    }
}
