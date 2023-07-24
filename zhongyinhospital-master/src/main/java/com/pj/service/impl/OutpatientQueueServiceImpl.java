package com.pj.service.impl;

import com.pj.dao.*;
import com.pj.dto.MedicalRecordReqVO;
import com.pj.entity.*;
import com.pj.service.OutpatientQueueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 就诊队列表(OutpatientQueue)表服务实现类
 *
 * @author makejava
 * @since 2023-07-12 00:04:35
 */
@Service
public class OutpatientQueueServiceImpl implements OutpatientQueueService {
    @Resource
    private OutpatientQueueDao outpatientQueueDao;
    @Resource
    private MedicalRecordDao medicalRecordDao;
    @Resource
    private RegisterDao registerDao;
    @Resource
    private PatientDao patientDao;
    @Resource
    private DepartmentDao departmentDao;
//    @Resource
//    private MedicalExamination medicalExamination;

    /**使用患者id和状态 查询就诊表
     * @param patientId
     * @return
     */
    @Override
    public OutpatientQueue searchAllsttus(int patientId,int userId) {
        OutpatientQueue queue = outpatientQueueDao.searchAllsttus(patientId,userId);
        return queue;
    }

    /**
     * 挂起
     * @param medicalRecordReqVO
     * @return
     */
    @Override
    @Transactional
    public int update(MedicalRecordReqVO medicalRecordReqVO) {
        OutpatientQueue queue=new OutpatientQueue();//创建队像
        queue.setStatus(-1);//设置状态为挂起
        queue.setId(medicalRecordReqVO.getQueueId());//设置id
        int update = outpatientQueueDao.update(queue);//修改状态
        MedicalRecord medicalRecord = medicalRecordDao.searchAllprescriptionNum(medicalRecordReqVO.getPrescriptionNum());//查询就诊记录表
        medicalRecord.setConditionDescription(medicalRecordReqVO.getConditionDescription());
        int update1 = medicalRecordDao.update(medicalRecord);
//        medicalRecordDao.update();
        return update1;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public OutpatientQueue queryById(int id) {
        OutpatientQueue queue = outpatientQueueDao.queryById(id);
        return queue;
    }
    /**
     * 门珍完成
     */
    @Override
    @Transactional
    public int upd(MedicalRecordReqVO medicalRecordReqVO, User user){
        List<Register> registers = registerDao.searchAll(String.valueOf(medicalRecordReqVO.getCardId()), user.getId(), 0);
        System.out.println(registers);
        for (Register register : registers) {
            register.setTreatmentStatus(1);//修改就诊状态
            registerDao.update(register);
        }
        Patient patient = patientDao.selectByCardId(String.valueOf(medicalRecordReqVO.getCardId()));
        patient.setCareer(medicalRecordReqVO.getCareer());//职业
        patient.setMaritalStatus(medicalRecordReqVO.getMaritalStatus());//是否结婚·
        patient.setFamilyHistory(medicalRecordReqVO.getFamilyHistory());//病式
        patient.setPersonalHistory(medicalRecordReqVO.getPersonalHistory());
        patient.setPastHistory(medicalRecordReqVO.getPastHistory());
        int update = patientDao.update(patient);//修改就诊表
        OutpatientQueue queue=new OutpatientQueue();
        queue.setStatus(0);//修改队列状态
        queue.setId(medicalRecordReqVO.getQueueId());
        int update1 = outpatientQueueDao.update(queue);
        MedicalRecord medicalRecord = medicalRecordDao.searchAllprescriptionNum(medicalRecordReqVO.getPrescriptionNum());
        medicalRecord.setPrescription(medicalRecordReqVO.getPrescription());//设置用药计量
        medicalRecord.setDrugids(medicalRecordReqVO.getDrugIds());//药品信息
        medicalRecord.setMoney(String.valueOf(medicalRecordReqVO.getMoney()));//多少钱
        medicalRecord.setConditionDescription(medicalRecordReqVO.getConditionDescription());//主诉
        medicalRecord.setDiagnosisResult(medicalRecordReqVO.getDiagnosisResult());
        medicalRecord.setMedicalOrder(medicalRecordReqVO.getMedicalOrder());
        int update2 = medicalRecordDao.update(medicalRecord);
        return update1;
    }

    /**
     * 恢复
     * @param registerId
     * @return
     */
    @Override
    @Transactional
    public Patient huifu(int registerId) {
        //根据就挂号信息表的id查询患者id
        Register register = registerDao.queryById(registerId);
        Integer departmentId = register.getDepartmentId();
        Department department = departmentDao.queryById(departmentId);
        //根据患者id查询患者患者表
        Patient patient = patientDao.selectByCardId(String.valueOf(register.getPatientId()));
        //查询处方号和创建时间
        MedicalRecord medicalRecord = medicalRecordDao.searchAllregisterId(registerId);
        patient.setPrescriptionNum(medicalRecord.getPrescriptionNum());//处方号
        patient.setDate(medicalRecord.getCreateDatetime());//创建时间
        patient.setConditionDescription(medicalRecord.getConditionDescription());//主诉信息
        patient.setDepartment(department.getName());//部门
        OutpatientQueue queue = outpatientQueueDao.searchAllByregisterId(registerId);
        queue.setStatus(1);
        patient.setQueueId(queue.getId());
        int update = outpatientQueueDao.update(queue);
        return patient;
    }

    /**
     * 根据就诊id查询就诊队列
     * @param registerId
     * @return
     */
    @Override
    public OutpatientQueue searchAllByregisterId(int registerId) {
        OutpatientQueue queue = outpatientQueueDao.searchAllByregisterId(registerId);
        return queue;
    }

    /**
     * 查询状态是挂起的患者
     * @return
     */
    @Override
    public List<OutpatientQueue> selectByStatusOutpatientQueues() {
        List<OutpatientQueue> outpatientQueues = outpatientQueueDao.selectByStatusOutpatientQueues();
        return outpatientQueues;
    }


}
