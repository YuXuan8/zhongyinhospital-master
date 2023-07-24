package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.medical_recordDto;
import com.pj.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 就诊记录表(MedicalRecord)表控制层
 *
 * @author makejava
 * @since 2023-07-13 10:54:17
 */
@RestController
@RequestMapping("/api/medicalRecord")
public class MedicalRecordController {

    /**
     * 服务对象
     */
    @Resource
    private MedicalRecordService medicalRecordService;

    /**
     * 门珍记录
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Object page(String name,int pageNum,int pageSize){
        PageInfo<medical_recordDto> page = medicalRecordService.page(name, pageNum, pageSize);
        return page;
    }


}

