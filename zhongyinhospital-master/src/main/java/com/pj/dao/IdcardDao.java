package com.pj.dao;

import com.pj.entity.Idcard;
import org.apache.ibatis.annotations.Select;

/**
 * 身份证信息表(Idcard)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-11 15:29:34
 */
public interface IdcardDao {
    //插入身份证信息表
     int  insert(Idcard idcard);
     //根据身份证号查询信息
    @Select("select * from idcard where id_card=#{idCard} ")
    Idcard byidCard(String idCard);
    //插入挂号信息时插入就诊信息的表述
     Idcard queryById(int id);
}

