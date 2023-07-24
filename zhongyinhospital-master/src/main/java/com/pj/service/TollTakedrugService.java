package com.pj.service;

import com.pj.entity.TollTakedrug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

/**
 * 划价收费-拿药信息表(TollTakedrug)表服务接口
 *
 * @author makejava
 * @since 2023-07-12 16:56:24
 */
public interface TollTakedrugService {

    int update1(TollTakedrug tollTakedrug);

    TollTakedrug searchAllByprescription_num(String prescription_num);
    /**
     * 查询卡号信息的同时查询处方单
     * @param patientId
     * @return
     */
    TollTakedrug searchAllByIdandzt(int patientId, Date createDatetime);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TollTakedrug queryById(Integer id);

    /**
     * 分页查询
     *
     * @param tollTakedrug 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<TollTakedrug> queryByPage(TollTakedrug tollTakedrug, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tollTakedrug 实例对象
     * @return 实例对象
     */
    TollTakedrug insert(TollTakedrug tollTakedrug);

    /**
     * 修改数据
     *
     * @param tollTakedrug 实例对象
     * @return 实例对象
     */
    TollTakedrug update(TollTakedrug tollTakedrug);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
