package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.PageloginInfor;
import com.pj.entity.LoginInfor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 登录信息表(LoginInfor)表服务接口
 *
 * @author makejava
 * @since 2023-07-18 11:10:50
 */
public interface LoginInforService {

    PageInfo<LoginInfor> page(PageloginInfor pageloginInfor);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LoginInfor queryById(Integer id);

    /**
     * 分页查询
     *
     * @param loginInfor  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<LoginInfor> queryByPage(LoginInfor loginInfor, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param loginInfor 实例对象
     * @return 实例对象
     */
    LoginInfor insert(LoginInfor loginInfor);

    /**
     * 修改数据
     *
     * @param loginInfor 实例对象
     * @return 实例对象
     */
    LoginInfor update(LoginInfor loginInfor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
