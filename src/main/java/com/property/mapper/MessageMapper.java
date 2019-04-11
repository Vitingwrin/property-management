package com.property.mapper;

import com.property.pojo.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageMapper {
    int insertMessage(Message message);
    int deleteMessageById(Integer id);
    int deleteMessageByNotice(Integer noticeId);
    List<Message> getNoticeMessage(Integer noticeId);
}
