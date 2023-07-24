package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.entity.Register;

import java.util.Date;
import java.util.List;

/**
 * 挂号记录表(Register)表服务接口
 *
 * @author makejava
 * @since 2023-07-11 13:31:12
 */
public interface RegisterService {

    //根据id查询
    Register queryById(int id);
    //挂号
    int insert(Register register);
    //挂号纪录
    PageInfo<Register> page(Register register,int pageNum,int pageSize);
    //查询是否有未处理的挂号状态
    List<Register> searchAllBycreateId(String createId);
    List<Register> searchAll(String createId,int doctorId,int treatmentStatus);
    //当前医生下的患者
    List<Register> searchAlldoctorId(int doctorId);
    //查询该用户有状态为0的挂号记录吗
    List<Register> searchAllbycreateId(String createId,int treatmentStatus);
    //挂体检号
    int tj(Register register,Integer treatmentStatus);
    //根据患者ID 查询不是体检的患者信息2
    List<Register> searchAll1( String createId, int treatmentStatus);
    int update(Register register);
    //根据时间查询
    Register bydata(Date create_datetime);
    //查询门珍收费信息2
    List<Register> selectBymenzhen(Integer patient_id, Integer charge_status);


}
