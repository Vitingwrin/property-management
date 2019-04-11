package com.property.service;

import com.property.mapper.NoticeMapper;
import com.property.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/11 9:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public int insertNotice(Notice notice){
        return noticeMapper.insertNotice(notice);
    }
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }
    public int deleteNotice(Integer id) {
        return noticeMapper.deleteNotice(id);
    }
    public List<Notice> getAllNotices() {
        return noticeMapper.getAllNotices();
    }
    public boolean isTitleUnique(String title) {
        return !noticeMapper.isExistsTitle(title);
    }
    public List<Notice> getNoticesWithMsg() {
        return noticeMapper.getNoticesWithMsg();
    }
}
