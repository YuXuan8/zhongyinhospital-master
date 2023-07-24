package com.pj.service;

import com.pj.entity.Idcard;

/**
 * 身份证信息表(Idcard)表服务接口
 *
 * @author makejava
 * @since 2023-07-11 15:29:34
 */
public interface IdcardService {
    //根据身份证查询信息
    Idcard byidCard(String idCard);

    //插入挂号信息时插入就诊信息的表述
    Idcard queryById(int id);


}
