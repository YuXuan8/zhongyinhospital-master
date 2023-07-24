package com.pj.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制页面跳转
 */
@Controller
public class PageController {
    //登录页
    @RequestMapping({"/","/loginUI"})
    public String loginUi(){
        return  "login";
    }
    //主页
    @RequestMapping({"home"})
    public String hone(){
        return  "home";
    }
    //错误页
    @RequestMapping({"error"})
    public String error(){
        return  "error";
    }
    //主页公告信息
    @RequestMapping({"main"})
    public String main(){
        return  "main";
    }
    //角色管理
    @RequiresPermissions("system:role:*")
    @RequestMapping({"/admin/role"})
    public String adminrole(){
        return  "/admin/role";
    }
    @RequiresPermissions("system:drug:*")
    @RequestMapping("/drugStore/drug")
    public String drugStore(){
        return "/drugStore/drug";
    }
    //挂号收费
    @RequiresPermissions("system:register:*")
    @RequestMapping("/register/register")
    public String ghsf(){
        return "/register/register";
    }
    //挂号记录
    @RequestMapping("/register/registerRecord")
    public String ghjl(){
        return "/register/registerRecord";
    }
    //门诊就诊
    @RequiresPermissions("system:outpatient:*")
    @RequestMapping("/outpatient/outpatient")
    public String mzjz(){
        return "/outpatient/outpatient";
    }
    //门珍纪录
    @RequiresPermissions("system:medicalRecord:*")
    @RequestMapping("/outpatient/medicalRecord")
    public String mzjl(){
        return "/outpatient/medicalRecord";
    }
    //普通体检
    @RequiresPermissions("system:medicalExamination:*")
    @RequestMapping("/medicalExamination/medicalExamination")
    public String pttj(){
        return "/medicalExamination/medicalExamination";
    }
    //门诊收费
    @RequiresPermissions("system:toll:*")
    @RequestMapping("/toll/outpatientToll")
    public String mzsf(){
        return "/toll/outpatientToll";
    }
    //体检收费
    @RequestMapping("/toll/examinationToll")
    public String tjsf(){
        return "/toll/examinationToll";
    }
    //药房取药
    @RequiresPermissions("system:takingdrug:*")
    @RequestMapping("/takingdrug/takingdrug")
    public String yfqy(){
        return "/takingdrug/takingdrug";
    }
    //药物管理
    @RequiresPermissions("system:drugStore:*")
    @RequestMapping("/drugStore/drugManage")
    public String ywgl(){
        return "/drugStore/drugManage";
    }
    //入库管理
    @RequestMapping("/drugStore/storageManage")
    public String rkgl(){
        return "/drugStore/storageManage";
    }
    //进货页面
    @RequestMapping("/admin/stockAdd")
    public String jh(){
        return "/admin/stockAdd";
    }
    //个人中心
    @RequiresPermissions("system:toUserinfo:*")
    @RequestMapping("//userInfo")
    public String grzx(){
        return "/userInfo";
    }
    //科室管理
    @RequiresPermissions("system:department:*")
    @RequestMapping("/admin/department")
    public String ksgl(){
        return "/admin/department";
    }
    //字典类型管理
    @RequiresPermissions("system:dicttype:*")
    @RequestMapping("/admin/dicttype")
    public String zdlxgl(){
        return "/admin/dicttype";
    }
    //字典管理
    @RequiresPermissions("system:dite:*")
    @RequestMapping("/admin/dict")
    public String zddgl(){
        return "/admin/dict";
    }
    //菜单管理
    @RequiresPermissions("system:menu:*")
    @RequestMapping("/admin/menu")
    public String cdgl(){
        return "/admin/menu";
    }
    //管理员管理
    @RequiresPermissions("system:user:*")
    @RequestMapping("/admin/user")
    public String glygl(){
        return "/admin/user";
    }
    //公告管理
    @RequiresPermissions("system:announcement:*")
    @RequestMapping("/admin/announcement")
    public String gggl(){
        return "/admin/announcement";
    }
    //登录管理
    @RequiresPermissions("system:loginInfo:*")
    @RequestMapping("/admin/loginInfo")
    public String dlgl(){
        return "/admin/loginInfo";
    }
    //患者管理
    @RequiresPermissions("system:patient:*")
    @RequestMapping("/admin/patient")
    public String hzgl(){
        return "/admin/patient";
    }

}
