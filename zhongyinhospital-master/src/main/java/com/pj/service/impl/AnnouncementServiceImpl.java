package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.AnnouncementDao;
import com.pj.dto.Pageannouncement;
import com.pj.entity.Announcement;
import com.pj.service.AnnouncementService;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 公告(Announcement)表服务实现类
 *
 * @author makejava
 * @since 2023-07-07 22:56:10
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementDao announcementDao;

    /**
     * 查询状态为0的公告
     * @param annStatus
     * @return
     */
    @Override
    public List<Announcement> searchAllByAnnStatus(int annStatus) {
        List<Announcement> announcements = announcementDao.searchAllByAnnStatus(annStatus);
        return announcements;
    }

    /**
     * 分页及模糊查询
     * @param pageannouncement
     * @return
     */
    @Override
    public PageInfo<Announcement> page(Pageannouncement pageannouncement) {
        if (pageannouncement.getTitle()==null||pageannouncement.getTitle()==""){
            pageannouncement.setTitle("%");
        }
        PageHelper.startPage(pageannouncement.getPage(),pageannouncement.getLimit());
        List<Announcement> mohu = announcementDao.mohu(pageannouncement.getTitle());
        PageInfo<Announcement> pageInfo=new PageInfo<>(mohu);
        return pageInfo;
    }

    /**
     * 新增公告
     * @param announcement
     * @return
     */
    @Override
    public Result add(Announcement announcement) {
        announcement.setCreateDatetime(new Date());

        Date date = new Date();
        //创建时间格式字符串
        String pattern = "yyyy-MM-dd HH:mm:ss";
        //获取当地时区
        Locale locale = Locale.getDefault();
        //利用SimpleDateFormat 进行时间格式的转换
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,locale);
        String time = sdf.format(date);
        announcement.setAnnDate(time);
        int insert = announcementDao.insert(announcement);
        Result result=new Result();
        if (insert>0){
            result.setMessage("新增成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("新增失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 修改
     * @param announcement
     * @return
     */
    @Override
    public Result upd(Announcement announcement) {
        Date date = new Date();
        //创建时间格式字符串
        String pattern = "yyyy-MM-dd HH:mm:ss";
        //获取当地时区
        Locale locale = Locale.getDefault();
        //利用SimpleDateFormat 进行时间格式的转换
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,locale);
        String time = sdf.format(date);
        announcement.setAnnDate(time);
        int update = announcementDao.update(announcement);
        Result result=new Result();
        if (update>0){
            result.setMessage("修改成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    @Override
    public int deleteById(int id) {
        int i = announcementDao.deleteById(id);
        return  i;
    }
}
