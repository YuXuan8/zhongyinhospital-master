package com.pj.service;

import com.pj.dto.patientDto;
import com.pj.entity.Toll;
import com.pj.util.GetCardIdInforReqVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 收费记录表(Toll)表服务接口
 *
 * @author makejava
 * @since 2023-07-14 21:38:05
 */
public interface TollService {



    patientDto getexaminationtoll(GetCardIdInforReqVO cardIdInforReqVO);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Toll queryById(Integer id);

    /**
     * 分页查询
     *
     * @param toll        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Toll> queryByPage(Toll toll, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param toll 实例对象
     * @return 实例对象
     */
    Toll insert(Toll toll);

    /**
     * 修改数据
     *
     * @param toll 实例对象
     * @return 实例对象
     */
    Toll update(Toll toll);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
