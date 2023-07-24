package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.Pageannouncement;
import com.pj.entity.Announcement;
import com.pj.util.Result;

import java.util.List;

/**
 * 公告(Announcement)表服务接口
 *
 * @author makejava
 * @since 2023-07-07 22:56:10
 */
public interface AnnouncementService {

//    公告
    List<Announcement> searchAllByAnnStatus(int annStatus);

    /**
     * 分页及
     * @param pageannouncement
     * @return
     */
    PageInfo<Announcement> page(Pageannouncement pageannouncement);
    /**
     * 添加
     */
    Result add(Announcement announcement);
    /**
     * 修改
     */
    Result upd(Announcement announcement);

    int deleteById(int id);

}
