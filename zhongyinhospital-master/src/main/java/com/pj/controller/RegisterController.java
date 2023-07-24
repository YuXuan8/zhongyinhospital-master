package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.UserDto;
import com.pj.entity.Register;
import com.pj.entity.User;
import com.pj.service.RegisterService;
import com.pj.service.UserService;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 挂号记录表(Register)表控制层
 *
 * @author makejava
 * @since 2023-07-11 13:31:12
 */
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    /**
     * 服务对象
     */
    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    /**
     * 根据科室查询医生
     * @param departmentId
     * @param registerType
     * @return
     */
    @GetMapping("/getAllRegisterDoctor")
    public  List<UserDto> getAllRegisterDoctor(int departmentId,int registerType) {
        List<UserDto> userDtos = userService.departmentId(departmentId, registerType);
        return userDtos;
    }

    /**
     * 挂号
     * @param register
     * @param request
     * @return
     */

    @PostMapping("/addRegisterInfor")
    public Result add(@RequestBody Register register, HttpServletRequest request){
        Result result=new Result();
        String email = (String) request.getSession().getAttribute("email");//从session中去除email
        User user = userService.selectByEmail(email);//根据emai查询当前登录的用户信息
        if (!register.getDepartmentId().equals(11)||!register.getRegisterType().equals("其他（体检）")){
            String ID = String.valueOf(System.currentTimeMillis());//获取当前毫秒值
            String registerednum="RE"+ID;
            register.setRegisterednum(registerednum);//门诊编号
            register.setCreateId(user.getId());//创建人id
            register.setRegisterStatus(1);//挂号状态
            register.setTreatmentStatus(0);//就诊状态
            register.setCreateName(user.getUsername());//创建人
            register.setChargeStatus(0);//挂号状态
            register.setCreateDatetime(new Date());//创建时间
            List<Register> registers = registerService.searchAll(String.valueOf(register.getPatientId()),register.getDoctorId(),0);
            for (Register register1 : registers) {
                if (register1.getTreatmentStatus().equals(0)){
                    result.setStatus(Result.RESPONSE_FAIL);
                    result.setMessage("该医生下有此患者未受诊记录");
                    return result;
                }
            }
            int insert = registerService.insert(register);//新增
            if (insert>0){
                request.getSession().setAttribute("doctorId",register.getDoctorId());
                result.setStatus(Result.RESPONSE_SUCCESS);
            }else {
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("挂号失败请联系管理员重试");
            }
        }else {
             Integer treatmentStatus=0;
            List<Register> registers = registerService.searchAllbycreateId(String.valueOf(register.getPatientId()), treatmentStatus);
            if (registers.size()==0){
                result.setStatus(Result.RESPONSE_FAIL);
                result.setMessage("请先挂门诊号在挂体检号");
            }else {
                for (Register register1 : registers) {
                    if (register1.getRegisterType().equals("其他（体检）")) {
                        result.setStatus(Result.RESPONSE_FAIL);
                        result.setMessage("有未体检的挂号");
                        return result;
                    }else {
                        Integer id = register1.getId();
                        String ID = String.valueOf(System.currentTimeMillis());//获取当前毫秒值
                        String registerednum="RE"+ID;
                        register.setRegisterednum(registerednum);//门诊编号
                        register.setCreateId(user.getId());//创建人id
                        register.setRegisterStatus(1);//挂号状态
                        register.setTreatmentStatus(0);//就诊状态
                        register.setCreateName(user.getUsername());//创建人
                        register.setChargeStatus(1);//挂号状态
                        register.setCreateDatetime(new Date());//创建时间
                        int tj = registerService.tj(register, id);
                        if (tj>0){
                            request.getSession().setAttribute("tj",register.getDoctorId());
                            result.setStatus(Result.RESPONSE_SUCCESS);
                        }else {
                            result.setStatus(Result.RESPONSE_FAIL);
                            result.setMessage("挂号失败请联系管理员重试");
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * 挂号纪律列表
     * @param register
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Object page( Register register,int pageNum,int pageSize){
        PageInfo<Register> page = registerService.page(register,pageNum, pageSize);
        return page;
    }
//    @PostMapping("/coverCardId")
//    public Result coverCardId(Integer cardId,String idCard){
//
//    }

}

