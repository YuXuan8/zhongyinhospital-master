package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dto.PageloginInfor;
import com.pj.entity.LoginInfor;
import com.pj.dao.LoginInforDao;
import com.pj.service.LoginInforService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录信息表(LoginInfor)表服务实现类
 *
 * @author makejava
 * @since 2023-07-18 11:10:50
 */
@Service
public class LoginInforServiceImpl implements LoginInforService {
    @Resource
    private LoginInforDao loginInforDao;

    /**
     * 登录记录和分页
     * @param pageloginInfor
     * @return
     */
    @Override
    public PageInfo<LoginInfor> page(PageloginInfor pageloginInfor) {
        if (pageloginInfor.getUsername()==""||pageloginInfor.getUsername()==null){
            pageloginInfor.setUsername("%");
        }
        PageHelper.startPage(pageloginInfor.getPage(),pageloginInfor.getLimit());
        List<LoginInfor> mohu = loginInforDao.mohu(pageloginInfor.getUsername());
        PageInfo<LoginInfor> pageInfo=new PageInfo<>(mohu);
        return pageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LoginInfor queryById(Integer id) {
        return this.loginInforDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param loginInfor  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<LoginInfor> queryByPage(LoginInfor loginInfor, PageRequest pageRequest) {
        long total = this.loginInforDao.count(loginInfor);
        return new PageImpl<>(this.loginInforDao.queryAllByLimit(loginInfor, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param loginInfor 实例对象
     * @return 实例对象
     */
    @Override
    public LoginInfor insert(LoginInfor loginInfor) {
        this.loginInforDao.insert(loginInfor);
        return loginInfor;
    }

    /**
     * 修改数据
     *
     * @param loginInfor 实例对象
     * @return 实例对象
     */
    @Override
    public LoginInfor update(LoginInfor loginInfor) {
        this.loginInforDao.update(loginInfor);
        return this.queryById(loginInfor.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.loginInforDao.deleteById(id) > 0;
    }
}
