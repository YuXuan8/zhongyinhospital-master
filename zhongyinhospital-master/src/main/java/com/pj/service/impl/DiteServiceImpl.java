package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.DicttypeDao;
import com.pj.dto.DiteDto;
import com.pj.entity.Dicttype;
import com.pj.entity.Dite;
import com.pj.dao.DiteDao;
import com.pj.service.DiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典表(Dite)表服务实现类
 *
 * @author makejava
 * @since 2023-07-16 00:09:21
 */
@Service
public class DiteServiceImpl implements DiteService {
    @Resource
    private DiteDao diteDao;
    @Resource
    private DicttypeDao dicttypeDao;

    @Override
    public List<DiteDto> searchTypeno(String typeNo) {
        List<DiteDto> diteDtos = diteDao.searchTypeno(typeNo);
        return diteDtos;
    }

    /**
     * 根据科室查询id
     * @param name
     * @return
     */
    @Override
    public Dite byname(String name) {
        Dite byname = diteDao.byname(name);
        return byname;
    }

    /**
     * 新增
     * @param dite
     * @return
     */
    @Override
    public int insert1(Dite dite) {
        int insert = diteDao.insert(dite);
        return insert;
    }

    /**
     * 根据机型查询信息是否存在
     * @param name
     * @param typeNo
     * @return
     */
    @Override
    public Dite searchAllByjix(String name, String typeNo) {
        Dite dite = diteDao.searchAllByjix(name, typeNo);
        return dite;
    }

    @Override
    public List<String> searchTypenoStrings(String typeNo) {
        List<String> strings = diteDao.searchTypenoStrings(typeNo);
        return strings;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dite queryById(Integer id) {
        return this.diteDao.queryById(id);
    }

    /**
     * 分页
     * @param dto
     * @return
     */
    @Override
    public PageInfo<Dite> queryByPage(DiteDto dto) {
        PageHelper.startPage(dto.getPage(),dto.getLimit());
        List<Dite> mohu = diteDao.mohu(dto.getTypeNo(), dto.getId());
        for (Dite dite : mohu) {
            String typeno = dite.getTypeno();
            Dicttype dicttype = dicttypeDao.searchTypeno(typeno);
            dite.setTypeName(dicttype.getName());
        }
        PageInfo<Dite> page= new PageInfo<>(mohu);
        return page;
    }

    /**
     * 新增数据
     *
     * @param dite 实例对象
     * @return 实例对象
     */
    @Override
    public Dite insert(Dite dite) {
        this.diteDao.insert(dite);
        return dite;
    }

    /**
     * 修改数据
     *
     * @param dite 实例对象
     * @return 实例对象
     */
    @Override
    public Dite update(Dite dite) {
        this.diteDao.update(dite);
        return this.queryById(dite.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.diteDao.deleteById(id) > 0;
    }
}
