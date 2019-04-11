package com.property.service;

import com.property.mapper.MessageMapper;
import com.property.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/11 10:17
 */@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService {

    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public int insertMessage(Message message) {
        return messageMapper.insertMessage(message);
    }
    public int deleteMessageById(Integer id) {
        return messageMapper.deleteMessageById(id);
    }
    public int deleteMessageByNotice(Integer noticeId) {
        return messageMapper.deleteMessageByNotice(noticeId);
    }
    public List<Message> getNoticeMessage(Integer noticeId) {
        return messageMapper.getNoticeMessage(noticeId);
    }
}
