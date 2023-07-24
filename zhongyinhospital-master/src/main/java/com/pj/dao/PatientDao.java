package com.pj.dao;

import com.pj.entity.Patient;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 患者表(Patient)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-11 14:57:26
 */
public interface PatientDao {
    //模糊查询
    @Select("  SELECT *\n" +
            "  FROM patient\n" +
            "  WHERE CONCAT(name) LIKE CONCAT('%', #{name}, '%')\n" +
            "    AND CONCAT(id_card) LIKE CONCAT('%', #{idCard}, '%')")
    List<Patient>  mohu(@Param("name")String name,@Param("idCard") String idCard);
    //根据卡号查询信息
    @Select("select * from patient where card_id=#{cardId}")
    Patient selectByCardId(String carId);
    //插入患者表
    int insert(Patient patient);
    //查询患者信息
    @Select("select * from patient where id_card=#{idCard}")
    Patient idCard(String idCard);
    //门诊修改病史
    int update(Patient patient);
    //查询卡号信息的资料查询处方


}

