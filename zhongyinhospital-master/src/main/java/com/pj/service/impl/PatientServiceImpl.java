package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.IdcardDao;
import com.pj.dao.PatientDao;
import com.pj.dto.Pagepatient;
import com.pj.entity.Idcard;
import com.pj.entity.Patient;
import com.pj.service.PatientService;
import com.pj.util.IDCardUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 患者表(Patient)表服务实现类
 *
 * @author makejava
 * @since 2023-07-11 14:57:27
 */
@Service
public class PatientServiceImpl implements PatientService {
    @Resource
    private PatientDao patientDao;
    @Resource
    private IdcardDao idcardDao;
    //根据id查询患者表
    @Override
    public Patient selectByCardId(String carId) {
        Patient patient = patientDao.selectByCardId(carId);
        return patient;
    }


    @Override
    @Transactional//配置事务
    public int insert(Patient patient) {
        Idcard idcard=new Idcard();
        idcard.setName(patient.getName());//姓名
        idcard.setSex(IDCardUtil.getSex(patient.getIdCard()));//性别
        idcard.setNationality(patient.getNationality());//民族
        idcard.setIdCard(patient.getIdCard());//身份证号
        idcard.setBirthday(IDCardUtil.getBirthday(patient.getIdCard()));//生日
        idcard.setAddress(patient.getAddress());//地址
        int insert = idcardDao.insert(idcard);//插入身份信息表
        patient.setCardId(String.valueOf(idcard.getId()));//设置id
        String age = IDCardUtil.getAge(patient.getIdCard());//设置年龄
        patient.setAge(Integer.valueOf(age));
        patient.setSex(IDCardUtil.getSex(patient.getIdCard()));
        patient.setBirthday(IDCardUtil.getBirthday(patient.getIdCard()));
        patient.setCreateDatetime(new Date());//创建时间
        patientDao.insert(patient);
        return insert;
    }

    /**
     * 有身份信息但是没患者表时补卡
     * @param patient
     * @return
     */
    @Override
    public int insert1(Patient patient) {
        String idCard = patient.getIdCard();
        Idcard idcard = idcardDao.byidCard(idCard);
        patient.setCardId(String.valueOf(idcard.getId()));//设置id
        String age = IDCardUtil.getAge(patient.getIdCard());//设置年龄
        patient.setAge(Integer.valueOf(age));
        patient.setSex(IDCardUtil.getSex(patient.getIdCard()));
        patient.setBirthday(IDCardUtil.getBirthday(patient.getIdCard()));
        patient.setCreateDatetime(new Date());//创建时间
        int insert = patientDao.insert(patient);
        return insert;
    }

    /**
     * 修改病史
     * @param patient
     * @return
     */
    @Override
    public int update(Patient patient) {
        int update = patientDao.update(patient);
        return update;
    }

    @Override
    public PageInfo<Patient> page(Pagepatient pagepatient) {
        if (pagepatient.getName()==null||pagepatient.getName()==""){
            pagepatient.setName("%");
        }
        if (pagepatient.getIdCard()==null||pagepatient.getIdCard()==""){
            pagepatient.setIdCard("%");
        }
        PageHelper.startPage(pagepatient.getPage(),pagepatient.getLimit());
        List<Patient> mohu = patientDao.mohu(pagepatient.getName(), pagepatient.getIdCard());
        PageInfo<Patient> pageInfo =new PageInfo<>(mohu);
        return pageInfo;
    }

    @Override
    public int insert2(Patient patient) {
        int insert = patientDao.insert(patient);
        return insert;
    }

    /**
     * 根据身份证查询患者信息
     * @param idCard
     * @return
     */
    @Override
    public Patient idCard(String idCard) {
        Patient patient = patientDao.idCard(idCard);
        return patient;
    }
}
