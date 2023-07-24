package com.pj.controller;

import com.pj.dto.Temp;
import com.pj.dto.patientDto;
import com.pj.entity.*;
import com.pj.service.*;
import com.pj.util.GetCardIdInforReqVO;
import com.pj.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 收费记录表(Toll)表控制层
 *
 * @author makejava
 * @since 2023-07-14 21:38:04
 */
@RestController
@RequestMapping("/api/toll/")
public class TollController {
    /**
     * 服务对象
     */
    @Resource
    private TollService tollService;
    @Resource
    private PatientService patientService;
    @Resource
    private MedicalExaminationService medicalExaminationService;
    @Resource
    private RegisterService registerService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private MedicalRecordService medicalRecordService;
    @Resource
    private  UserService userService;
    @Resource
    private TollTakedrugService tollTakedrugService;

    /**
     * 查询患者信息
     * @param cardIdInforReqVO
     * @return
     */
   @PostMapping("/getexaminationtoll")
    public Result getexaminationtoll(GetCardIdInforReqVO cardIdInforReqVO){
       System.out.println(cardIdInforReqVO);
       Result result=new Result();
       patientDto getexaminationtoll = tollService.getexaminationtoll(cardIdInforReqVO);
       System.out.println(getexaminationtoll);
       if (getexaminationtoll!=null){
           result.setStatus(Result.RESPONSE_SUCCESS);
           result.setData(getexaminationtoll);
       }else {
           result.setStatus(Result.RESPONSE_FAIL);
           result.setMessage("没有查询到该卡号的体检信息");
       }
       return result;
   }

    /**
     * 体检收费
     * @param id
     * @param payType
     * @param payPrice
     * @param changePrice
     * @return
     */
   @PostMapping("/saveexaminationtollinfo")
   public Result saveexaminationtollinfo(int id,String payType,String payPrice,String changePrice){
       Result result=new Result();
       MedicalExamination medicalExamination = medicalExaminationService.selectByRegisterId(id);
       Register bydata = registerService.bydata(medicalExamination.getCreateDatetime());
       bydata.setChargeStatus(1);
       bydata.setPayType(payType);
       bydata.setPayPrice(payPrice);
       bydata.setChangePrice(changePrice);
       int update = registerService.update(bydata);
       if (update>0){
           result.setStatus(Result.RESPONSE_SUCCESS);
       }else {
           result.setStatus(Result.RESPONSE_FAIL);
           result.setMessage("体检缴费失败,请联系管理员重试");
       }
       return  result;
   }

    /**
     * 门诊收费根本卡号显示信息
     * @return
     */
    @GetMapping("/getAllMedicalRecord")
   public Result getAllMedicalRecord(Temp temp){
        Result result=new Result();
        List<Register> registerDtos = registerService.selectBymenzhen(temp.getCardId(), temp.getTollStatus());
        if (registerDtos.size()>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setData(registerDtos);
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("没有查询到该卡号的门诊信息");
        }
        return  result;
   }

    /**
     * 点击选择后查询信息
     * @return
     */
   @PostMapping("/getMedicalRecord")
   public Result getMedicalRecord(Temp temp){
       Result result=new Result();
       Register register = registerService.queryById(temp.getRegisterId());
       Patient patient = patientService.selectByCardId(String.valueOf(register.getPatientId()));
       Department department = departmentService.queryById(register.getDepartmentId());
       MedicalRecord medicalRecord = medicalRecordService.searchAllprescriptionNum(temp.getPrescriptionNum());
       MedicalExamination medicalExamination = medicalExaminationService.selectByprescription_num(temp.getPrescriptionNum());
       if (medicalExamination==null){
           patient.setDrugCost(String.valueOf(0));//药费
       }else {
           patient.setDrugCost(medicalRecord.getMoney());//药费
       }
       patient.setPrescriptionNum(temp.getPrescriptionNum());//处方号
       patient.setDoctorName(register.getDoctor());
       patient.setDepartment(department.getName());//科室名字
       patient.setPrescription(medicalRecord.getPrescription());
       patient.setDiagnosisResult(medicalRecord.getDiagnosisResult());//医嘱
       patient.setMedicalOrder(medicalRecord.getMedicalOrder());

       result.setData(patient);
       result.setStatus(Result.RESPONSE_SUCCESS);
       return result;
   }

    /**
     * 门诊收费
     * @param temp
     * @return
     */
   @PostMapping("/saveTollInfo")
    public Result saveTollInfo(@RequestBody Temp temp, HttpServletRequest request){
       Result result=new Result();
       String email = (String) request.getSession().getAttribute("email");
       User user = userService.selectByEmail(email);
       Register register = registerService.queryById(temp.getRegisterId());
       register.setChargeStatus(1);
       int update = registerService.update(register);
       TollTakedrug tollTakedrug = tollTakedrugService.searchAllByprescription_num(temp.getPrescriptionNum());
       tollTakedrug.setTollDateTime(String.valueOf(new Date()));
       tollTakedrug.setMoney(temp.getMoney());
       tollTakedrug.setTollOperator(user.getUsername());
       int update1 = tollTakedrugService.update1(tollTakedrug);
       if (update>0&&update1>0){
           result.setStatus(Result.RESPONSE_SUCCESS);
       }else {
           result.setStatus(Result.RESPONSE_FAIL);
           result.setMessage("收费失败请联系管理员重试");
       }
       return result;
    }

}

