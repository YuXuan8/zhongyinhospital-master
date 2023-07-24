package com.pj.service.impl;

import com.pj.entity.Goout;
import com.pj.dao.GooutDao;
import com.pj.service.GooutService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 药品出库表(Goout)表服务实现类
 *
 * @author makejava
 * @since 2023-07-18 19:14:53
 */
@Service("gooutService")
public class GooutServiceImpl implements GooutService {
    @Resource
    private GooutDao gooutDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Goout queryById(Integer id) {
        return this.gooutDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param goout       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Goout> queryByPage(Goout goout, PageRequest pageRequest) {
        long total = this.gooutDao.count(goout);
        return new PageImpl<>(this.gooutDao.queryAllByLimit(goout, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param goout 实例对象
     * @return 实例对象
     */
    @Override
    public Goout insert(Goout goout) {
        this.gooutDao.insert(goout);
        return goout;
    }

    /**
     * 修改数据
     *
     * @param goout 实例对象
     * @return 实例对象
     */
    @Override
    public Goout update(Goout goout) {
        this.gooutDao.update(goout);
        return this.queryById(goout.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.gooutDao.deleteById(id) > 0;
    }
}
