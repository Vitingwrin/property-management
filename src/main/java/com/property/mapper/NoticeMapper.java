package com.property.mapper;

import com.property.pojo.Notice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NoticeMapper {
    int insertNotice(Notice notice);
    int updateNotice(Notice notice);
    int deleteNotice(Integer id);
    List<Notice> getAllNotices();
    boolean isExistsTitle(String title);
    List<Notice> getNoticesWithMsg();
}
