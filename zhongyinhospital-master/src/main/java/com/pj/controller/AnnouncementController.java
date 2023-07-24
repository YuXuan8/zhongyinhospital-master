package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.Pageannouncement;
import com.pj.entity.Announcement;
import com.pj.service.AnnouncementService;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告(Announcement)表控制层
 *
 * @author makejava
 * @since 2023-07-07 22:56:09
 */
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {
    /**
     * 服务对象
     */
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 查询所有状态为0的公告
     * @param annStatus
     * @return
     */
    @GetMapping
    public Result select(@RequestParam  int annStatus){
        Result result=new Result();
        List<Announcement> announcements = announcementService.searchAllByAnnStatus(annStatus);
        result.setData(announcements);
        return result;
    }

    /**
     * 分页及模糊查询
     * @param pageannouncement
     * @return
     */
    @GetMapping("/page")
    public Result page(Pageannouncement pageannouncement){
        Result result=new Result();
        PageInfo<Announcement> page = announcementService.page(pageannouncement);
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setTotal(page.getTotal());
        result.setData(page);
        return result;
    }

    /**
     * 新增公告
     * @param announcement
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Announcement announcement){
        Result add = announcementService.add(announcement);
        return add;
    }

    /**
     *修改
     * @param announcement
     * @return
     */
    @PutMapping
    public Result upd(@RequestBody Announcement announcement){
        Result upd = announcementService.upd(announcement);
        return upd;
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id")Integer id) {
        Result result=new Result();
        int i = announcementService.deleteById(id);
        if (i>0){
            result.setStatus(Result.RESPONSE_SUCCESS);
            result.setMessage("删除成功");
        }else {
            result.setMessage("删除失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }


}

