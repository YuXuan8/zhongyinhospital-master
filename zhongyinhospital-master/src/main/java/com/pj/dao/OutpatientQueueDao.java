package com.pj.dao;

import com.pj.entity.OutpatientQueue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 就诊队列表(OutpatientQueue)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-12 00:04:35
 */
public interface OutpatientQueueDao {
    //新增挂号的同时新增就诊
    int insert(OutpatientQueue outpatientQueue);
    //使用患者id和状态 查询就诊表
    @Select("select * from outpatient_queue where patient_id=#{patientId} and status!=0 and user_id=#{userId}")
    OutpatientQueue searchAllsttus(@Param("patientId") int patientId,@Param("userId") int userId);
    //修改
    int update(OutpatientQueue outpatientQueue);
    //根据id查询
    OutpatientQueue queryById(int id);

    @Select("select * from outpatient_queue where register_id=#{registerId}")
    OutpatientQueue searchAllByregisterId(int registerId);

    @Select("select * from outpatient_queue where  status=-1")
    List<OutpatientQueue> selectByStatusOutpatientQueues();

}

