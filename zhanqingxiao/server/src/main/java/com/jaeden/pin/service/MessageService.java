package com.jaeden.pin.service;

import com.jaeden.pin.domain.chat.MessageBody;
import com.jaeden.pin.domain.chat.MessageDO;
import com.jaeden.pin.domain.chat.UserInfoForChat;
import com.jaeden.pin.infrastructure.repository.MessageRepository;
import com.jaeden.pin.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类MessageService.java
 *

 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    public Boolean isExist(Integer orderId) {
        Integer row = messageRepository.findExist(orderId);
        return row != 0;
    }

    public void insert(MessageBody body, Integer userId, Integer orderId) {
        messageRepository.inert(assemble(body, userId, orderId));
    }

    public List<MessageBody> queryAll(Integer orderId, Integer userId) {
        List<MessageDO> messageDOS = messageRepository.queryAll(orderId);
        return messageDOS.parallelStream().map(x -> assemble(x, userId)).collect(Collectors.toList());
    }

    private MessageBody assemble(MessageDO messageDO, Integer userId) {
        UserInfoForChat userDO = userRepository.findChatInfo(messageDO.getUserId());
        MessageBody messageBody = new MessageBody(messageDO.getTime(), userDO, messageDO.getMessage());
        messageBody.setOwnMsg(messageDO.getUserId().equals(userId));
        return messageBody;

    }

    private MessageDO assemble(MessageBody body, Integer userId, Integer orderId) {
        MessageDO messageDO = new MessageDO();
        messageDO.setUserId(userId);
        messageDO.setTime(body.getTime());
        messageDO.setMessage(body.getMessage());
        messageDO.setOrderId(orderId);
        return messageDO;

    }

}
