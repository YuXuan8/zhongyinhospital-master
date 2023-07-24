package com.pj.controller;

import com.pj.entity.*;
import com.pj.service.*;
import com.pj.util.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 划价收费-拿药信息表(TollTakedrug)表控制层
 *
 * @author makejava
 * @since 2023-07-12 16:56:23
 */
@RestController
@RequestMapping("/api/tollTakedrug")
public class TollTakedrugController {
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
    @Resource
    private DrugService drugService;
    @Resource
    private GooutService gooutService;

    /**
     *根据处方号查询数据
     * @param prescriptionNum
     * @return
     */
    @PostMapping("/getMedicalRecord")
    @Transactional
    public Result getMedicalRecord(String prescriptionNum){
        Result result=new Result();
        MedicalRecord medicalRecord = medicalRecordService.searchAllprescriptionNum(prescriptionNum);
        Register register = registerService.queryById(medicalRecord.getRegisterId());
        Patient patient = patientService.selectByCardId(String.valueOf(register.getPatientId()));
        Department department = departmentService.queryById(register.getDepartmentId());
        MedicalExamination medicalExamination = medicalExaminationService.selectByprescription_num(prescriptionNum);
        if (medicalExamination==null){
            patient.setExaminationCost(String.valueOf(0));//体检费
        }else {
            patient.setExaminationCost(medicalExamination.getExaminationCost());//体检费
        }
        patient.setPrescriptionNum(prescriptionNum);//处方号
        patient.setDoctorName(register.getDoctor());
        patient.setDepartment(department.getName());//科室名字
        patient.setPrescription(medicalRecord.getPrescription());
        patient.setDiagnosisResult(medicalRecord.getDiagnosisResult());//医嘱
        patient.setMedicalOrder(medicalRecord.getMedicalOrder());
        patient.setDrugCost(medicalRecord.getMoney());//药费
        result.setData(patient);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     *药房取药
     * @param prescriptionNum
     * @return
     */
    @PostMapping("/saveTakingDrugInfo")
    @Transactional
    public Result saveTakingDrugInfo(String prescriptionNum, HttpServletRequest httpServletRequest){
        Result result=new Result();
        MedicalRecord medicalRecord = medicalRecordService.searchAllprescriptionNum(prescriptionNum);//获取要取的药
        String drugIds = medicalRecord.getDrugids();//根据诊断表的字段获取每种药品要取多少药
        String[] dataArray = drugIds.split(",");
        Map<Integer, Integer> map = new HashMap<>();
        for (String element : dataArray) {
            int key = Integer.parseInt(element);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        //循环map集合修改药品库存
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            Drug drug = drugService.queryById(key);
            Integer drugCount = drug.getDrugCount();
            if (drugCount<=value){
                result.setStatus(Result.RESPONSE_FAIL);//库存不足
                result.setMessage("药品库存不足");
                return result;
            }
            drug.setDrugCount(drugCount-value);
            drugService.update(drug);
            Goout goout=new Goout();
            goout.setDrugId(key);
            goout.setDrugName(drug.getName());
            goout.setDrugNum(value);
            String price = drug.getPrice();
            double priceValue = Double.parseDouble(price);
            double v = priceValue * value;
            goout.setMoner(String.valueOf(v));
            goout.setTolltakedrugNo(prescriptionNum);
            goout.setCreateTime(new Date());
            TollTakedrug tollTakedrug = tollTakedrugService.searchAllByprescription_num(prescriptionNum);
            goout.setPatientId(String.valueOf(tollTakedrug.getPatientId()));
            gooutService.insert(goout);
        }
        String email = (String) httpServletRequest.getSession().getAttribute("email");
        User user = userService.selectByEmail(email);
        TollTakedrug tollTakedrug = tollTakedrugService.searchAllByprescription_num(prescriptionNum);//查询取药表
        tollTakedrug.setTakingDrugStatus(1);//设置已取药
        tollTakedrug.setTakingDrugOperator(user.getUsername());//药房操作员
        tollTakedrug.setTakingDrugDateTime(String.valueOf(new Date()));//取药时间
        int i = tollTakedrugService.update1(tollTakedrug);//修改
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }


}

