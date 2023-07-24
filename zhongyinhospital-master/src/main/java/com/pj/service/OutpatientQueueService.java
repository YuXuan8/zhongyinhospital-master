package com.pj.service;

import com.pj.dto.MedicalRecordReqVO;
import com.pj.entity.OutpatientQueue;
import com.pj.entity.Patient;
import com.pj.entity.User;

import java.util.List;

/**
 * 就诊队列表(OutpatientQueue)表服务接口
 *
 * @author makejava
 * @since 2023-07-12 00:04:35
 */
public interface OutpatientQueueService {
    //使用患者id和状态 查询就诊表
    OutpatientQueue searchAllsttus(int patientId,int userId);
    //挂起
    int update(MedicalRecordReqVO medicalRecordReqVO);
    //根据id查询
    OutpatientQueue queryById(int id);
    //门珍完成
    int upd(MedicalRecordReqVO medicalRecordReqVO, User user);
    //恢复
    Patient huifu(int registerId);
    //根据就诊表的id查询就诊队列表
    OutpatientQueue searchAllByregisterId(int registerId);
    //查询状态是挂起的患者
    List<OutpatientQueue> selectByStatusOutpatientQueues();

}
