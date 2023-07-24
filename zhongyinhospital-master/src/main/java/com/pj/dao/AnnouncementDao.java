package com.pj.dao;

import com.pj.entity.Announcement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公告(Announcement)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-07 22:56:09
 */
public interface AnnouncementDao {
    /**
     * 查询状态为0的公告
     * @param annStatus
     * @return
     */
    @Select("select * from announcement where ann_status=#{annStatus}")
    List<Announcement> searchAllByAnnStatus(int annStatus);

    /**
     * 模糊查询
     * @param title
     * @return
     */
    @Select("select * from announcement where  title  like  concat('%',#{title},'%' )")
    List<Announcement> mohu(String title);

    int insert(Announcement announcement);

    int update(Announcement announcement);

    int deleteById(int id);

}

