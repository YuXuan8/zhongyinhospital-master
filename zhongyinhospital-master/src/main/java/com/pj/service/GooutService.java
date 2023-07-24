package com.pj.service;

import com.pj.entity.Goout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 药品出库表(Goout)表服务接口
 *
 * @author makejava
 * @since 2023-07-18 19:14:53
 */
public interface GooutService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Goout queryById(Integer id);

    /**
     * 分页查询
     *
     * @param goout       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Goout> queryByPage(Goout goout, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param goout 实例对象
     * @return 实例对象
     */
    Goout insert(Goout goout);

    /**
     * 修改数据
     *
     * @param goout 实例对象
     * @return 实例对象
     */
    Goout update(Goout goout);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
