package com.pj.service;

import com.pj.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 药品进货表(Stock)表服务接口
 *
 * @author makejava
 * @since 2023-07-16 15:37:37
 */
public interface StockService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Stock queryById(Integer id);

    /**
     * 分页查询
     *
     * @param stock       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Stock> queryByPage(Stock stock, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    int insert(Stock stock);

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    Stock update(Stock stock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
