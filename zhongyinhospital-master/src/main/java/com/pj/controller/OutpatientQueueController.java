package com.pj.controller;

import com.pj.dao.MedicalExaminationDao;
import com.pj.dto.MedicalRecordReqVO;
import com.pj.entity.*;
import com.pj.service.*;
import com.pj.util.DateUtil;
import com.pj.util.GetCardIdInforReqVO;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 就诊队列表(OutpatientQueue)表控制层
 *
 * @author makejava
 * @since 2023-07-12 00:04:34
 */
@RestController
@RequestMapping("/api/outpatient")
public class OutpatientQueueController {
    /**
     * 服务对象
     */
    @Resource
    private OutpatientQueueService outpatientQueueService;
    @Resource
    private PatientService patientService;
    @Autowired
    private RegisterService registerService;
    @Resource
    private UserService userService;
    @Resource
    private TollTakedrugService tollTakedrugService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private MedicalExaminationDao medicalExaminationDao;

    /**
     * 根据卡号获取患者信息
     *
     * @param cardIdInforReqVO
     * @return
     */
    @PostMapping("/getCardIdInfor")
    public Result findIdcard(@RequestBody GetCardIdInforReqVO cardIdInforReqVO, HttpServletRequest request) {
        Result result = new Result();
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.selectByEmail(email);
        int i1=0;
        //根据当前账号id 和患者id 状态 查询 register表
        List<Register> registers = registerService.searchAll(cardIdInforReqVO.getCardId(), user.getId(),i1);
        Date date=null;
        for (Register register : registers) {
            //如果getTreatmentStatus就诊状态等于0获取时间
            if (register.getTreatmentStatus().equals(0)){
                String currentDateToString = DateUtil.getCurrentDateToString();//当前年月日
                String pattern = "yyyy-MM-dd HH:mm:ss";
                Locale locale = Locale.getDefault();
                //利用SimpleDateFormat 进行时间格式的转换
                SimpleDateFormat sdf = new SimpleDateFormat(pattern,locale);
                String time = sdf.format(register.getCreateDatetime());
                String s = DateUtil.DateTimeToDate(time);//就诊表创建时间
                String b=DateUtil.DateTimeToDate(currentDateToString);
                if (b.equals(s)){
                    date=register.getCreateDatetime();
                }else {
                    register.setTreatmentStatus(1);
                    registerService.update(register);
                    result.setStatus(Result.RESPONSE_FAIL);
                    result.setMessage("该挂号已过期");
                    return result;
                }

            }
        }
        //根据当前账号id 和患者id 状态 查询 register表不为0
        if (registers.size() > 0) {
            //获取部门字段
            Department department = departmentService.queryById(user.getDepartmentId());
            //根据前端获取的id查询信息
            Patient patient = null;
            try {
                int i = Integer.parseInt(cardIdInforReqVO.getCardId());
                //根据前端获取的患者信息和register表的创建时间查询 关联的tollTakedrug获取处方号字段
                TollTakedrug tollTakedrug = tollTakedrugService.searchAllByIdandzt(i,date);
                patient = patientService.selectByCardId(cardIdInforReqVO.getCardId());
                //判断有没有卡号信息
                if (patient == null) {
                    result.setStatus(Result.RESPONSE_FAIL);
                    result.setMessage("卡号不存在");
                    return result;
                }
                patient.setPrescriptionNum(tollTakedrug.getPrescriptionNum());
                patient.setDate(tollTakedrug.getCreateDatetime());
                patient.setDepartment(department.getName());
                String id = patient.getCardId();
                //根据患者id和当前登录账号id及未就诊的状态查询 OutpatientQueue.id给patient赋值前端未隐藏字段
                OutpatientQueue queue = outpatientQueueService.searchAllsttus(Integer.parseInt(id),user.getId());
                patient.setQueueId(queue.getId());
                result.setData(patient);
                result.setStatus(Result.RESPONSE_SUCCESS);
            } catch (Exception e) {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("没有挂号信息");
            }
        } else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("没有挂号信息,请确认医生信息");
        }
        return result;
    }

    /**
     * 获取当前医生下所有门诊队列患者
     *
     * @return
     */
    @PostMapping("/getalloutpatientqueue")
    public Result getalloutpatientqueue(HttpServletRequest request) {
        Result result = new Result();
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.selectByEmail(email);
        List<Register> registers = registerService.searchAlldoctorId(user.getId());
        List<Register> registers1= new ArrayList<>();
        for (Register register : registers) {
            String currentDateToString = DateUtil.getCurrentDateToString();//当前年月日
            String pattern = "yyyy-MM-dd HH:mm:ss";
            Locale locale = Locale.getDefault();
            //利用SimpleDateFormat 进行时间格式的转换
            SimpleDateFormat sdf = new SimpleDateFormat(pattern,locale);
            String time = sdf.format(register.getCreateDatetime());
            String s = DateUtil.DateTimeToDate(time);//就诊表创建时间
            String b=DateUtil.DateTimeToDate(currentDateToString);
            if (b.equals(s)){
                registers1.add(register);
            }
        }
        result.setData(registers1);
        return result;
    }

    /**
     * 挂起
     */
    @PostMapping("/ProcessLaterMedicalRecord")
    public Result gua(@RequestBody MedicalRecordReqVO medicalRecordReqVO) {
        Result result = new Result();
        Integer queueId = medicalRecordReqVO.getQueueId();
        OutpatientQueue queue = outpatientQueueService.queryById(queueId);
        if (queue.getStatus().equals(-1)) {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("该患者已挂起");
        } else {
            int update = outpatientQueueService.update(medicalRecordReqVO);
            if (update > 0) {
                result.setStatus(Result.RESPONSE_SUCCESS);
            } else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("挂起失败，请重试");
            }
        }
        return result;
    }

    /**
     * 恢复
     * @param registerId
     * @return
     */
    @PostMapping("/restorePatientInfor")
    public Result huifu(int registerId){
        Result result=new Result();
        Patient huifu = outpatientQueueService.huifu(registerId);
        if (huifu!=null){
            result.setData(huifu);
            result.setStatus(Result.RESPONSE_SUCCESS);

        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("恢复失败，请重试");
        }
        return result;
    }

    /**、
     * 门诊完成
     * @param medicalRecordReqVO
     * @param request
     * @return
     */
    @PostMapping("/addMedicalRecord")
    public Result add(@RequestBody MedicalRecordReqVO medicalRecordReqVO, HttpServletRequest request) {
        Result result = new Result();
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.selectByEmail(email);
        Integer queueId = medicalRecordReqVO.getQueueId();
        OutpatientQueue queue = outpatientQueueService.queryById(queueId);
        System.out.println(queue+"+++++++++++++++++");
        if (queue.getStatus().equals(-1)) {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("该患者已挂起请恢复后尝试");
        } else {
            int upd = outpatientQueueService.upd(medicalRecordReqVO, user);
            if (upd > 0) {
                result.setStatus(Result.RESPONSE_SUCCESS);
            } else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("失败，请联系管理员重试");
            }
        }
        return result;
    }
    /**
     * 获取体检信息
     */
    @PostMapping("/getMedicalExamination")
    public Result getMedicalExamination(String prescriptionNum){
        Result result=new Result();
        MedicalExamination medicalExamination = medicalExaminationDao.selectByprescription_num(prescriptionNum);
        if (medicalExamination!=null){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setData(medicalExamination);
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("没有找到体检信息");
        }
        return result;
    }

}

