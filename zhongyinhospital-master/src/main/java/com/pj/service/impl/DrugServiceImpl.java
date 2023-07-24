package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.DrugDao;
import com.pj.entity.Drug;
import com.pj.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 药物信息表(Drug)表服务实现类
 *
 * @author makejava
 * @since 2023-07-09 23:32:23
 */
@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugDao drugDao;

    /**
     * 药品信息分页
     * @param pageNum
     * @param pageSize
     * @param drug
     * @param drugType_search
     * @param unit
     * @param specification
     * @return
     */
    @Override
    public PageInfo<Drug> findByPage1(Integer pageNum, Integer pageSize, String drug, String drugType_search, String unit, String specification) {
        PageHelper.startPage(pageNum,pageSize);
        List<Drug> all2 = drugDao.findAll2(drug, drugType_search, unit, specification);
        PageInfo<Drug> pageInfo=new PageInfo<>(all2);
        return pageInfo;
    }

    /**
     * 新增药品
     * @param drug
     * @return
     */
    @Override
    public int insert(Drug drug) {
        int insert = drugDao.insert(drug);
        return insert;
    }

    /**
     * 根据药品名查询药品信息
     * @param name
     * @return
     */
    @Override
    public Drug searchAllBydrug(String name) {
        Drug drug = drugDao.searchAllBydrug(name);
        return drug;
    }

    @Override
    public int update(Drug drug) {
        int update = drugDao.update(drug);
        return update;
    }

    @Override
    public List<Drug> findAll1(String name, String drugType, String efficacyClassification, Integer limitStatus) {
        List<Drug> all = drugDao.findAll1(name, drugType, efficacyClassification, limitStatus);
        return all;
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> all = drugDao.findAll();
        return all;
    }


    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Drug> findByPage(Integer pageNum, Integer pageSize,String drug,String drugType_search,String efficacyClassification_search,Integer limitStatus_search) {
        PageHelper.startPage(pageNum,pageSize);
        List<Drug> all = this.findAll1(drug,drugType_search,efficacyClassification_search,limitStatus_search);
        PageInfo<Drug> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        int i = drugDao.deleteById(id);
        return i;
    }

    /**
     * 根据id 查询药品信息
     * @param id
     * @return
     */
    @Override
    public Drug queryById(int id) {
        Drug drug = drugDao.queryById(id);
        return drug;
    }


}
