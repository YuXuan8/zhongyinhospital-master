package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.Pagepatient;
import com.pj.entity.Idcard;
import com.pj.entity.Patient;
import com.pj.service.IdcardService;
import com.pj.service.PatientService;
import com.pj.service.RegisterService;
import com.pj.util.GetCardIdInforReqVO;
import com.pj.util.IDCardUtil;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 患者表(Patient)表控制层
 *
 * @author makejava
 * @since 2023-07-11 14:57:26
 */
@RestController
@RequestMapping("/api/Patient")
public class PatientController {
    /**
     * 服务对象
     */
    @Resource
    private PatientService patientService;
    @Autowired
    private IdcardService idcardDao;
    @Autowired
    private RegisterService registerService;

    /**
     * 根据卡号获取患者信息
     * @param cardIdInforReqVO
     * @return
     */
    @PostMapping("/getCardIdInfor")
    public Result findIdcard(@RequestBody GetCardIdInforReqVO cardIdInforReqVO){
        Result result=new Result();
        //根据前端获取的id查询信息
        Patient patient = patientService.selectByCardId(cardIdInforReqVO.getCardId());
        //判断有没有卡号信息
        if (patient==null){
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("卡号不存在");
        }
        result.setData(patient);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 新增身负信息和患者表
     * @param patient
     * @return
     */
    @PostMapping("/addPatientInfor")
    public Result add(@RequestBody Patient patient){
        Result result=new Result();
        boolean isValid = IDCardUtil.validateCard(patient.getIdCard());//判断身份证是否合规
        if (isValid){//合法
            String idCard = patient.getIdCard();//页面传递的身份证
            Idcard idcard = idcardDao.byidCard(idCard);//查询身份信息表
            if (idcard!=null){//如果存在
                Patient patient1 = patientService.idCard(idCard);//查询患者表
                if (patient1!=null){//患者表和信息表存在的话提示信息已激活
                    result.setMessage("ACTIVATED");
                    result.setStatus(Result.RESPONSE_FAIL);
                }else {//不存在则缺少患者表信息提示补办
                    result.setMessage("COVER");
                    result.setStatus(Result.RESPONSE_FAIL);
                }
            }else {//不存在
                patientService.insert(patient);//插入身份信息和患者表
                result.setData(patient.getCardId());
                result.setStatus(Result.RESPONSE_SUCCESS);
            }
        }else {//身份证不合法
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("身份证不合法");
        }
        return result;
    }
    /**
     * 当用户只用于身份信息但是没有患者表时补卡
     * @param patient
     * @return
     */
    @PostMapping("/coverCardId")
    public Result coverCardId(@RequestBody Patient patient){
        Result result=new Result();
        //获取数据插入
        int i = patientService.insert1(patient);
        if (i<=0){
            //插入失败
            result.setStatus(Result.RESPONSE_FAIL);
        }
        result.setData(patient.getCardId());
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 病里修改
     * @param patient
     * @return
     */
    @PostMapping("/changePatientInfor")
    private Result changePatientInfor(@RequestBody Patient patient){
        Result result=new Result();
        int i = patientService.update(patient);
        if (i<=0){
            result.setStatus(Result.RESPONSE_FAIL);
        }
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 患者管理分页及模糊查询
     * @param pagepatient
     * @return
     */
    @GetMapping("/page")
    public Result page(Pagepatient pagepatient){
        Result result=new Result();
        PageInfo<Patient> page = patientService.page(pagepatient);
        result.setTotal(page.getTotal());
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setData(page);
        return result;
    }

    /**
     * 新增
     * @param patient
     * @return
     */
    @PostMapping
    public Result add1(@RequestBody Patient patient){
        Result result=new Result();
        String idCard = patient.getIdCard();
        Patient patient1 = patientService.idCard(idCard);
        if (patient1==null){
            int insert = patientService.insert2(patient);
            if (insert>0){
                result.setStatus(Result.RESPONSE_SUCCESS);
                result.setMessage("新增成功");
            }else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("新增失败");
            }
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("该信息已存在");
        }
        return result;
    }

    /**
     * 修改
     * @param patient
     * @return
     */
    @PutMapping
    public Result upd(@RequestBody Patient patient){
        Result result=new Result();
        int update = patientService.update(patient);
        if (update>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("修改成功");
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("修改失败");
        }
       return result;
    }



}

