package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dto.dicttypeDto;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Dicttype;
import com.pj.dao.DicttypeDao;
import com.pj.service.DicttypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典类型表(Dicttype)表服务实现类
 *
 * @author makejava
 * @since 2023-07-17 10:43:02
 */
@Service
public class DicttypeServiceImpl implements DicttypeService {
    @Resource
    private DicttypeDao dicttypeDao;

    @Override
    public Dicttype searchTypeno(String typeNo) {
        Dicttype dicttype = dicttypeDao.searchTypeno(typeNo);
        return dicttype;
    }

    /**
     * 查询类型和编号
     * @return
     */
    @Override
    public List<dicttypeDto> searall() {
        List<dicttypeDto> searall = dicttypeDao.searall();
        return searall;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dicttype queryById(Integer id) {
        return this.dicttypeDao.queryById(id);
    }

    /**
     * 分页查询
     * @return 查询结果
     */
    @Override
    public PageInfo<Dicttype> queryByPage(getAllDrug1 getAllDrug1) {
        if (getAllDrug1.getName()==""||getAllDrug1.getName()==null){
            getAllDrug1.setName("%");
        }
        PageHelper.startPage(getAllDrug1.getPage(),getAllDrug1.getLimit());
        List<Dicttype> dicttypes = dicttypeDao.selecybyDicttype(getAllDrug1.getName());

        PageInfo<Dicttype> pageInfo=new PageInfo<>(dicttypes);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param dicttype 实例对象
     * @return 实例对象
     */
    @Override
    public Dicttype insert(Dicttype dicttype) {
        this.dicttypeDao.insert(dicttype);
        return dicttype;
    }

    /**
     * 修改数据
     *
     * @param dicttype 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Dicttype dicttype) {
        int update = dicttypeDao.update(dicttype);
        return update;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.dicttypeDao.deleteById(id) > 0;
    }
}
