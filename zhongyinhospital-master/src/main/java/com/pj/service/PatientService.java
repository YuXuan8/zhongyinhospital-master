package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.Pagepatient;
import com.pj.entity.Patient;

/**
 * 患者表(Patient)表服务接口
 *
 * @author makejava
 * @since 2023-07-11 14:57:27
 */
public interface PatientService {
    //根据id查询患者表信息
    Patient selectByCardId(String carId);
    //插入身份信息的同时插入患者表
    int insert(Patient patient);
    //根据身份证查询患者表信息
    Patient idCard(String idCard);
    //补卡操作
    int insert1(Patient patient);

    //门诊修改病史
    int update(Patient patient);

    PageInfo<Patient> page(Pagepatient pagepatient);

    int insert2(Patient patient);

}
