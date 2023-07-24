package com.pj.controller;

import com.pj.dto.medicalExaminationInfoReqVO;
import com.pj.entity.*;
import com.pj.service.*;
import com.pj.util.GetCardIdInforReqVO;
import com.pj.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 体检报告(MedicalExamination)表控制层
 *
 * @author makejava
 * @since 2023-07-14 10:54:53
 */
@RestController
@RequestMapping("/api/medicalExamination")
public class MedicalExaminationController {
    /**
     * 服务对象
     */
    @Resource
    private MedicalExaminationService medicalExaminationService;
    @Resource
    private PatientService patientService;
    @Resource
    private  RegisterService registerService;
    @Resource
    private UserService userService;
    @Resource
    private OutpatientQueueService outpatientQueueService;
    @Resource
    private IdcardService idcardService;

    /**
     * 根据卡号查询患者信息
     * @param cardIdInforReqVO
     * @return
     */
    @PostMapping("/getCardIdInfor")
    public Result findIdcard(@RequestBody GetCardIdInforReqVO cardIdInforReqVO, HttpServletRequest request){
        Result result=new Result();
        //根据前端获取的id查询信息
        Patient patient = patientService.selectByCardId(cardIdInforReqVO.getCardId());
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.selectByEmail(email);
        //判断有没有卡号信息
        if (patient==null){
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("卡号不存在");
            return result;
        }
        List<Register> registers = registerService.searchAll1(patient.getCardId(), 0);
        if (registers.size()>0){
            for (Register register : registers) {
                OutpatientQueue queue = outpatientQueueService.searchAllByregisterId(register.getId());
                Integer  id = queue.getId();
                MedicalExamination  medicalExamination  = medicalExaminationService.selectByRegisterId(register.getId());
                patient.setPrescriptionNum(medicalExamination.getPrescriptionNum());
                patient.setQueueId(id);
                result.setData(patient);
                result.setStatus(Result.RESPONSE_SUCCESS);
            }
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("没有信息请确认挂号信息");
            return result;
        }

        return result;
    }
    /**
     * 获取当前医生下所有门诊队列患者
     *
     * @return
     */
    @PostMapping("/getalloutpatientqueue")
    public Result getalloutpatientqueue( ) {
        Result result = new Result();
        List<OutpatientQueue> outpatientQueues = outpatientQueueService.selectByStatusOutpatientQueues();
        List<GetCardIdInforReqVO> cardIdInforReqVOS = new ArrayList<>();
        for (OutpatientQueue outpatientQueue : outpatientQueues) {
            GetCardIdInforReqVO cardIdInforReqVO=new GetCardIdInforReqVO();
            Integer patientId = outpatientQueue.getPatientId();
            Idcard idcard = idcardService.queryById(patientId);
            cardIdInforReqVO.setCardId(String.valueOf(patientId));
            cardIdInforReqVO.setPatientName(idcard.getName());
            cardIdInforReqVOS.add(cardIdInforReqVO);
        }
        result.setData(cardIdInforReqVOS);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }
    /**
     * 体检完成
     */
    @PostMapping("/saveMedicalExaminationInfo")
    public Result  Save(@RequestBody medicalExaminationInfoReqVO medicalExaminationInfoReqVO){
        Result result=new Result();
        int upd = medicalExaminationService.upd(medicalExaminationInfoReqVO);
        if (upd>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setStatus(Result.RESPONSE_FAIL);
            result.setMessage("体检信息提交失败");
        }
        return result;
    }

}

