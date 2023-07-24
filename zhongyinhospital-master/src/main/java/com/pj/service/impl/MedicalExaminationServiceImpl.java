package com.pj.service.impl;

import com.pj.dao.OutpatientQueueDao;
import com.pj.dao.RegisterDao;
import com.pj.dto.medicalExaminationInfoReqVO;
import com.pj.entity.MedicalExamination;
import com.pj.dao.MedicalExaminationDao;
import com.pj.entity.OutpatientQueue;
import com.pj.entity.Register;
import com.pj.service.MedicalExaminationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 体检报告(MedicalExamination)表服务实现类
 *
 * @author makejava
 * @since 2023-07-14 10:54:54
 */
@Service
public class MedicalExaminationServiceImpl implements MedicalExaminationService {
    @Resource
    private MedicalExaminationDao medicalExaminationDao;
    @Resource
    private RegisterDao registerDao;
    @Resource
    private OutpatientQueueDao outpatientQueueDao;


    /**
     * 体检完成
     * @param medicalExaminationInfoReqVO
     * @return
     */
    @Override
    @Transactional
    public int upd(medicalExaminationInfoReqVO medicalExaminationInfoReqVO) {
        String prescriptionNum = medicalExaminationInfoReqVO.getPrescriptionNum();
        MedicalExamination medicalExamination = medicalExaminationDao.selectByprescription_num(prescriptionNum);
        medicalExamination.setBloodPressure(medicalExaminationInfoReqVO.getBloodPressure());
        medicalExamination.setBodyTemperature(medicalExaminationInfoReqVO.getBodyTemperature());
        medicalExamination.setExaminationCost(String.valueOf(medicalExaminationInfoReqVO.getExaminationCost()));
        medicalExamination.setHeartRate(medicalExaminationInfoReqVO.getHeartRate());
        medicalExamination.setPulse(medicalExaminationInfoReqVO.getPulse());
        int update = medicalExaminationDao.update(medicalExamination);
        OutpatientQueue queue = outpatientQueueDao.queryById(Integer.parseInt(medicalExaminationInfoReqVO.getQueueId()));
        Integer patientId = queue.getPatientId();
        List<Register> registers = registerDao.searchAll2(String.valueOf(patientId), 0);
        if (registers.size()>0){
            for (Register register : registers) {
                register.setTreatmentStatus(1);
                register.setChargeStatus(0);
                int update1 = registerDao.update(register);
            }
        }

        return update;
    }

    /**
     * 根据体检表查询体检表
     * @param prescription_num
     * @return
     */
    @Override
    public MedicalExamination selectByprescription_num(String prescription_num) {
        MedicalExamination medicalExamination = medicalExaminationDao.selectByprescription_num(prescription_num);
        return medicalExamination;
    }

    /**
     * 根据就诊id查询 体检表
     * @param registerId
     * @return
     */
    @Override
    public MedicalExamination selectByRegisterId(Integer registerId) {
        MedicalExamination medicalExamination = medicalExaminationDao.selectByRegisterId(registerId);
        return medicalExamination;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MedicalExamination queryById(Integer id) {
        return this.medicalExaminationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param medicalExamination 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @Override
    public Page<MedicalExamination> queryByPage(MedicalExamination medicalExamination, PageRequest pageRequest) {
        long total = this.medicalExaminationDao.count(medicalExamination);
        return new PageImpl<>(this.medicalExaminationDao.queryAllByLimit(medicalExamination, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param medicalExamination 实例对象
     * @return 实例对象
     */
    @Override
    public MedicalExamination insert(MedicalExamination medicalExamination) {
        this.medicalExaminationDao.insert(medicalExamination);
        return medicalExamination;
    }

    /**
     * 修改数据
     *
     * @param medicalExamination 实例对象
     * @return 实例对象
     */
    @Override
    public MedicalExamination update(MedicalExamination medicalExamination) {
        this.medicalExaminationDao.update(medicalExamination);
        return this.queryById(medicalExamination.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.medicalExaminationDao.deleteById(id) > 0;
    }
}
