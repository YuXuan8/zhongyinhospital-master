package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Dite;
import com.pj.entity.Drug;
import com.pj.entity.Stock;
import com.pj.service.DiteService;
import com.pj.service.DrugService;
import com.pj.service.StockService;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 药物信息表(Drug)表控制层
 *
 * @author makejava
 * @since 2023-07-09 23:32:21
 */
@RestController
@RequestMapping("/drugstore")
public class DrugController {
    /**
     * 服务对象
     */
    @Autowired
    private DrugService drugService;
    @Resource
    private DiteService diteService;
    @Resource
    private StockService stockService;

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getAllDrug")
    public Result findAll(@RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize,
                          @RequestParam("drug") String drug, @RequestParam("drugType_search") String drugType_search,
                          @RequestParam("efficacyClassification_search") String efficacyClassification_search,
                          @RequestParam("limitStatus_search") Integer limitStatus_search) {
        Result result = new Result();
        //查询所有数据
        PageInfo<Drug> byPage = drugService.findByPage(pageNum, pageSize, drug, drugType_search, efficacyClassification_search, limitStatus_search);
        //把数据传递到前端
        result.setData(byPage);
        //设置状态layui成功状态是0
        result.setStatus(Result.RESPONSE_FAIL);
        //传递数据长度
        result.setTotal(byPage.getTotal());
        return result;
    }

    /**
     * 药品信息管理发页
     * @param getAllDrug1
     * @return
     */
    @GetMapping("/getAllDrug1")
    public Result findAll1(getAllDrug1 getAllDrug1) {
        Result result = new Result();
        PageInfo<Drug> byPage1 = drugService.findByPage1(getAllDrug1.getPage(),getAllDrug1.getLimit(),getAllDrug1.getName(),getAllDrug1.getDrugType(), getAllDrug1.getUnit(), getAllDrug1.getSpecification());
        //把数据传递到前端
        result.setData(byPage1);
        //设置状态layui成功状态是0
        result.setStatus(Result.RESPONSE_SUCCESS);
        //传递数据长度
        result.setTotal(byPage1.getTotal());
        return result;
    }

    /**
     * 根据传递的内容查询不同信息
     * @param drug
     * @return
     */
    @PostMapping("/getAllDrugType")
    public List<String> getdurg(String drug) {
        List<String> strings = diteService.searchTypenoStrings(drug);
        return strings;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteDrug")
    public Result del(int id) {
        Result result = new Result();
        //执行删除
        int i = drugService.deleteById(id);
        //删除如果小于0获取等于0说明删除失败返回1
        if (i <= 0) {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("删除失败");
        }
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 修改
     *
     * @param drug
     * @return
     */
    @PostMapping("/updateDrug")
    public Result updateDrug(@RequestBody Drug drug) {
        Result result = new Result();
        System.out.println(drug);
        int i = drugService.update(drug);
        if (i <= 0) {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("修改失败");
        }
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 获取所有药品
     *
     * @return
     */
    @GetMapping("/getDrug")
    public Result getdrug() {
        Result result = new Result();
        List<Drug> all = drugService.findAll();
        result.setData(all);
        return result;
    }

    /**
     * 根据药品名查询药品信息
     *
     * @param drug
     * @return
     */
    @PostMapping("/getDrugInfor")
    public Drug getDrugInfor(String drug) {
        Drug drug1 = drugService.searchAllBydrug(drug);//根据药品名查询
        return drug1;
    }

    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * 新增库存
     * @param drug
     * @param addStorageQuantity
     * @return
     */
    @PostMapping("/addStorageQuantity")
    @Transactional
    public Result addStorageQuantity(String drug,int addStorageQuantity){
        Result result=new Result();
        if (!isNumber(String.valueOf(addStorageQuantity))){
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("数量不合法");
            return result;
        }
        if (addStorageQuantity<=0){
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("数量不合法");
            return result;
        }
        Drug drug1 = drugService.searchAllBydrug(drug);
        Integer drugCount = drug1.getDrugCount();
        drug1.setDrugCount(drugCount+addStorageQuantity);//新增库存
        int update = drugService.update(drug1);
        if(update>0){
            Stock stock=new Stock();
            stock.setDrugId(drug1.getId());
            stock.setNum(addStorageQuantity);
            stock.setPrice(drug1.getWholesalePrice());
            String wholesalePrice = drug1.getWholesalePrice();
            double price = Double.parseDouble(wholesalePrice);
            double money=price*addStorageQuantity;
            stock.setMoney(String.valueOf(money));
            stock.setSupplier(drug1.getManufacturer());
            stock.setStockTime(new Date());
            int insert = stockService.insert(stock);
            if (insert>0){
                result.setStatus(Result.RESPONSE_SUCCESS);
            }else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("入库失败");
            }
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("入库失败");
        }
        return result;
    }

    /**
     * 新增剂型
     * @param drugType
     * @return
     */
    @PostMapping("/addDrugType")
    public Result addDrugType(String drugType){
        Result result=new Result();
        //先查询增加的剂型是否存在
        Dite dite = diteService.searchAllByjix(drugType, "drug_type");
        if (dite!=null){
            //已经存在
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("该剂型已存在");
        }else {
            Dite dite1=new Dite();
            dite1.setTypeno("drug_type");
            dite1.setHierarchy(5);
            dite1.setName(drugType);
            dite1.setRemark("药物类型");
            dite1.setFlog(1);
            int i = diteService.insert1(dite1);
            if (i>0){
                result.setStatus(Result.RESPONSE_SUCCESS);
            }else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("新增失败");
            }
        }
        return result;
    }

    /**
     * 新增功效
     * @param efficacyClassification
     * @return
     */
    @PostMapping("/addEfficacyClassification")
    public Result addEfficacyClassification(String efficacyClassification){
        Result result=new Result();
        //先查询增加的剂型是否存在
        Dite dite = diteService.searchAllByjix(efficacyClassification, "efficacy_classification");
        if (dite!=null){
            //已经存在
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("该功效已存在");
        }else {
            Dite dite1=new Dite();
            dite1.setTypeno("efficacy_classification");
            dite1.setHierarchy(5);
            dite1.setName(efficacyClassification);
            dite1.setRemark("药物功效");
            dite1.setFlog(1);
            int i = diteService.insert1(dite1);
            if (i>0){
                result.setStatus(Result.RESPONSE_SUCCESS);
            }else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("新增失败");
            }
        }
        return result;
    }

    /**
     * 新增药品信息
     * @param drug
     * @return
     */
    @PostMapping("/addNewDrug")
    public Result addNewDrug(@RequestBody Drug drug){
        Result result=new Result();
        Dite dite = diteService.searchAllByjix(drug.getName(), "drug_name");
        if (dite!=null){
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("该药品已经存在");
        }else {
            Dite dite1=new Dite();
            dite1.setTypeno("drug_name");
            dite1.setHierarchy(5);
            dite1.setName(drug.getName());
            dite1.setRemark("药物名称");
            dite1.setFlog(1);
            int i = diteService.insert1(dite1);
            if (i>0){
                drug.setCreateDatetime(new Date());
                int insert = drugService.insert(drug);
                if (insert>0){
                    Stock stock=new Stock();
                    stock.setDrugId(drug.getId());
                    stock.setNum(drug.getDrugCount());
                    stock.setPrice(drug.getWholesalePrice());
                    String wholesalePrice = drug.getWholesalePrice();
                    double price = Double.parseDouble(wholesalePrice);
                    double money=price*drug.getDrugCount();
                    stock.setMoney(String.valueOf(money));
                    stock.setSupplier(drug.getManufacturer());
                    stock.setStockTime(new Date());
                    int insert2 = stockService.insert(stock);
                    if (insert2>0){
                        result.setStatus(Result.RESPONSE_SUCCESS);
                    }else {
                        result.setStatus(Result.RESPONSE_FAIL);
                        result.setMessage("新增药品失败s");
                    }
                }else {
                    result.setStatus(Result.RESPONSE_FAIL);
                    result.setMessage("新增药品失败");
                }
            }else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("新增药品失败");
            }
        }
        return  result;
    }

    /**
     * 修改信息
     * @param drug
     * @return
     */
    @PutMapping("/drug")
    public Result upd(@RequestBody Drug drug){
        Result result=new Result();
        System.out.println(drug);
        int update = drugService.update(drug);
        if (update>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("修改成功");
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("修改失败");
        }
        return  result;
    }
    @PostMapping("/stock")
    public Result jh1(Stock stock){
        Result result=new Result();
        Drug drug = drugService.queryById(stock.getDrugId());
        Integer drugCount = drug.getDrugCount();
        drug.setDrugCount(drugCount+stock.getNum());
        int update = drugService.update(drug);
        int insert = stockService.insert(stock);
        if (insert>0&&update>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("进货成功");
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("进货失败");
        }
        return  result;
    }


}

