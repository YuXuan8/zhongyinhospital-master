package com.pj.controller;

import com.pj.service.IdcardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 身份证信息表(Idcard)表控制层
 *
 * @author makejava
 * @since 2023-07-11 15:29:34
 */
@RestController
@RequestMapping("/api/Idcard")
public class IdcardController {
    /**
     * 服务对象
     */
    @Resource
    private IdcardService idcardService;




}

