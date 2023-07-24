package com.pj.service.impl;

import com.pj.dao.IdcardDao;
import com.pj.entity.Idcard;
import com.pj.service.IdcardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 身份证信息表(Idcard)表服务实现类
 *
 * @author makejava
 * @since 2023-07-11 15:29:34
 */
@Service
public class IdcardServiceImpl implements IdcardService {
    @Resource
    private IdcardDao idcardDao;

    /**
     * 根据身份证查询信息
     * @param idCard
     * @return
     */
    @Override
    public Idcard byidCard(String idCard) {
        Idcard idcard = idcardDao.byidCard(idCard);
        return idcard;
    }

    @Override
    public Idcard queryById(int id) {
        return idcardDao.queryById(id);
    }
}
