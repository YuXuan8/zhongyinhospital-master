package com.pj.dao;

import com.pj.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 药品进货表(Stock)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-16 15:37:35
 */
public interface StockDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Stock queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param stock    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Stock> queryAllByLimit(Stock stock, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param stock 查询条件
     * @return 总行数
     */
    long count(Stock stock);

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int insert(Stock stock);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Stock> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Stock> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Stock> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Stock> entities);

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int update(Stock stock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

