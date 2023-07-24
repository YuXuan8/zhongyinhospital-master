package com.pj.dao;

import com.pj.entity.Register;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 挂号记录表(Register)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-11 13:31:12
 */
public interface RegisterDao {
    //根据id查询
    Register queryById(int id);
    //挂号
    int insert(Register register);
    //挂号记录列表
    List<Register> findAll(Register register);
    //查询病人是否有过挂号
    @Select("select * from register where create_id=#{createId}")
    List<Register> searchAllBycreateId(String createId);
    //
    @Select("select * from register where patient_id=#{patientId} and doctor_id=#{doctorId} and treatment_status=#{treatmentStatus}")
    List<Register> searchAll(@Param("patientId") String createId,@Param("doctorId")int doctorId,@Param("treatmentStatus") int treatmentStatus);
    //当前医生下的患者
    @Select("SELECT r.*, i.name as name,oq.status as status " +
            "FROM register r " +
            "JOIN idcard i ON i.id = r.patient_id " +
            "JOIN outpatient_queue oq ON oq.register_id = r.id " +
            "WHERE r.doctor_id = #{doctorId} and oq.status!=0")
    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "status",column = "status")
    })
    List<Register> searchAlldoctorId(int doctorId);
    //修改
    int update(Register register);
    //查询该用户有状态为0的挂号记录
    @Select("select * from register where patient_id=#{patientId} and treatment_status=#{treatmentStatus}")
    List<Register> searchAllbycreateId(@Param("patientId") String createId,@Param("treatmentStatus") Integer treatmentStatus);
    //根据患者id查询 就诊表不是体检的信息
    @Select("select * from register where patient_id=#{patientId} and register_type!='其他（体检）' and treatment_status=#{treatmentStatus}")
    List<Register> searchAll1(@Param("patientId") String createId,@Param("treatmentStatus") int treatmentStatus);
    //查询患者是体检的信息
    @Select("select * from register where patient_id=#{patientId} and register_type='其他（体检）' and treatment_status=#{treatmentStatus}")
    List<Register> searchAll2(@Param("patientId") String createId,@Param("treatmentStatus") int treatmentStatus);
    //根据时间查询
    @Select("select * from register where create_datetime=#{create_datetime}")
    Register bydata(Date create_datetime);
    @Select("select * from register where patient_id=#{patientId} and register_type='其他（体检）' and treatment_status=#{treatmentStatus} and charge_status=0")
    List<Register> searchAll3(@Param("patientId") String createId,@Param("treatmentStatus") int treatmentStatus);
    //查询门珍收费信息2
    List<Register> selectBymenzhen(@Param("patient_id") Integer patient_id,@Param("charge_status") Integer charge_status);


}

