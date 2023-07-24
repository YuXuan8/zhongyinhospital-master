package com.pj.service.impl;

import com.pj.entity.TollTakedrug;
import com.pj.dao.TollTakedrugDao;
import com.pj.service.TollTakedrugService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 划价收费-拿药信息表(TollTakedrug)表服务实现类
 *
 * @author makejava
 * @since 2023-07-12 16:56:24
 */
@Service
public class TollTakedrugServiceImpl implements TollTakedrugService {
    @Resource
    private TollTakedrugDao tollTakedrugDao;

    @Override
    public int update1(TollTakedrug tollTakedrug) {
        int update = tollTakedrugDao.update(tollTakedrug);
        return update;
    }

    @Override
    public TollTakedrug searchAllByprescription_num(String prescription_num) {
        TollTakedrug tollTakedrug = tollTakedrugDao.searchAllByprescription_num(prescription_num);
        return tollTakedrug;
    }

    /**
     * 查询卡号信息的同时查询处方单
     * @param patientId
     * @return
     */
    @Override
    public TollTakedrug searchAllByIdandzt(int patientId, Date createDatetime) {
        TollTakedrug tollTakedrug = tollTakedrugDao.searchAllByIdandzt(patientId,createDatetime);
        return tollTakedrug;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TollTakedrug queryById(Integer id) {
        return this.tollTakedrugDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param tollTakedrug 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<TollTakedrug> queryByPage(TollTakedrug tollTakedrug, PageRequest pageRequest) {
        long total = this.tollTakedrugDao.count(tollTakedrug);
        return new PageImpl<>(this.tollTakedrugDao.queryAllByLimit(tollTakedrug, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param tollTakedrug 实例对象
     * @return 实例对象
     */
    @Override
    public TollTakedrug insert(TollTakedrug tollTakedrug) {
        this.tollTakedrugDao.insert(tollTakedrug);
        return tollTakedrug;
    }

    /**
     * 修改数据
     *
     * @param tollTakedrug 实例对象
     * @return 实例对象
     */
    @Override
    public TollTakedrug update(TollTakedrug tollTakedrug) {
        this.tollTakedrugDao.update(tollTakedrug);
        return this.queryById(tollTakedrug.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tollTakedrugDao.deleteById(id) > 0;
    }
}
