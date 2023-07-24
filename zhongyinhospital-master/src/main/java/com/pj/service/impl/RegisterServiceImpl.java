package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.*;
import com.pj.entity.*;
import com.pj.service.RegisterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 挂号记录表(Register)表服务实现类
 *
 * @author makejava
 * @since 2023-07-11 13:31:12
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RegisterDao registerDao;
    @Resource
    private OutpatientQueueDao queueDaol;
    @Resource
    private IdcardDao idcardDao;
    @Resource
    private UserDao userDao;
    @Resource
    private TollTakedrugDao tollTakedrugDao;
    @Resource
    private MedicalRecordDao MedicalRecordDa;
    @Resource
    private MedicalExaminationDao medicalExaminationDao;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Register queryById(int id) {
        Register register = registerDao.queryById(id);
        return register;
    }

    /**
     * 挂号
     * @param register
     * @return
     */
    @Override
    @Transactional
    public int insert(Register register) {
        int insert = registerDao.insert(register);//新增挂号表
        OutpatientQueue queue=new OutpatientQueue();
        //补充就诊表数据进行插入
        queue.setRegisterId(register.getId());
        queue.setUserId(register.getDoctorId());
        queue.setPatientId(register.getPatientId());
        queue.setStatus(1);
        Idcard idcard = idcardDao.queryById(register.getPatientId());
        String Remark=idcard.getName()+"#"+register.getDoctor();
        queue.setRemark(Remark);
        queue.setCreateDatetime(new Date());
        queueDaol.insert(queue);
        //从就诊信息中提取医生id修改医生已挂号数量
        User user = userDao.queryById(register.getDoctorId());
        Integer nowNum = user.getNowNum();
        System.out.println("当前挂号数"+nowNum);
        user.setNowNum(nowNum+1);
        user.setUpdateTime(new Date());
        userDao.update(user);
        TollTakedrug tollTakedrug=new TollTakedrug();
        tollTakedrug.setCreateDatetime(new Date());//创建时间
        tollTakedrug.setPatientId(register.getPatientId());
        long currentTimestamp = System.currentTimeMillis();
        String PrescriptionNum="O"+currentTimestamp;//创建处方号
        tollTakedrug.setPrescriptionNum(PrescriptionNum);
        tollTakedrug.setTakingDrugStatus(0);
        tollTakedrugDao.insert(tollTakedrug);//新增处方表
        MedicalRecord medicalRecord=new MedicalRecord();
        medicalRecord.setPrescriptionNum(PrescriptionNum);
        medicalRecord.setRegisterId(register.getId());
        medicalRecord.setCreateDatetime(new Date());
        MedicalRecordDa.insert(medicalRecord);//新增就诊记录表
        return insert;
    }

    /**
     * 挂号纪录列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Register> page(Register register,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Register> all = registerDao.findAll(register);
        PageInfo<Register> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }

    /**
     * 查询是否有为处理的挂号
     * @param createId
     * @return
     */
    @Override
    public List<Register> searchAllBycreateId(String createId) {
        List<Register> registers = registerDao.searchAllBycreateId(createId);
        return registers;
    }

    /**
     * 查询当前患者在当前医生下状态未就诊的信息
     * @param createId
     * @param doctorId
     * @param treatmentStatus
     * @return
     */
    @Override
    public List<Register> searchAll(String createId, int doctorId,int treatmentStatus) {
        List<Register> registers = registerDao.searchAll(createId, doctorId,treatmentStatus);
        return registers;
    }

    /**
     * 当前医生下的患者
     * @param doctorId
     * @return
     */
    @Override
    public List<Register> searchAlldoctorId(int doctorId) {
        List<Register> registers = registerDao.searchAlldoctorId(doctorId);
        return registers;
    }

    /**
     * 查询患者下有状态为o的挂号记录吗
     * @param createId
     * @param treatmentStatus
     * @return
     */
    @Override
    public List<Register> searchAllbycreateId(String createId, int treatmentStatus) {
        List<Register> registers = registerDao.searchAllbycreateId(createId, treatmentStatus);
        return registers;
    }

    /**
     * 体检号
     * @param register
     * @return
     */
    @Override
    public int tj(Register register,Integer id) {
        int insert = registerDao.insert(register);//新增挂号表
        User user = userDao.queryById(register.getDoctorId());
        Integer nowNum = user.getNowNum();
        System.out.println("当前挂号数"+nowNum);
        user.setNowNum(nowNum+1);
        user.setUpdateTime(new Date());
        userDao.update(user);
        MedicalRecord medicalRecord = MedicalRecordDa.searchAllregisterId(id);
        MedicalExamination medicalExamination=new MedicalExamination();
        medicalExamination.setPrescriptionNum(medicalRecord.getPrescriptionNum());
        medicalExamination.setCreateDatetime(new Date());
        medicalExamination.setRegisterId(id);
        int insert1 = medicalExaminationDao.insert(medicalExamination);
        return insert;
    }

    /**
     * 根据患者id 查询挂号不是 体检的信息
     * @param createId
     * @param treatmentStatus
     * @return
     */
    @Override
    public List<Register> searchAll1(String createId, int treatmentStatus) {
        List<Register> registers = registerDao.searchAll1(createId, treatmentStatus);
        return registers;
    }

    @Override
    public int update(Register register) {
        int update = registerDao.update(register);
        return update;
    }

    /**
     * 根据时间查询
     * @param create_datetime
     * @return
     */
    @Override
    public Register bydata(Date create_datetime) {
        Register bydata = registerDao.bydata(create_datetime);
        return bydata;
    }

    /**
     * 门珍收费查询收费信息
     * @param patient_id
     * @param charge_status
     * @return
     */
    @Override
    public List<Register> selectBymenzhen(Integer patient_id, Integer charge_status) {
        List<Register> registerDtos = registerDao.selectBymenzhen(patient_id, charge_status);
        return registerDtos;
    }


}
