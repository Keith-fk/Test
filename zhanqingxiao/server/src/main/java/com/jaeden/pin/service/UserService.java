package com.jaeden.pin.service;

import com.google.common.base.Preconditions;
import com.jaeden.pin.domain.chat.UserInfoForChat;
import com.jaeden.pin.domain.request.LoginRequest;
import com.jaeden.pin.domain.user.UserDO;
import com.jaeden.pin.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类UserService.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-21
 */
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Integer insert(LoginRequest info, String openid) {
        UserDO userDO = assemble(info, openid);
        int row = userRepository.insert(userDO);
        Preconditions.checkState(row == 1, "login insert error openid" + openid);
        return userDO.getId();
    }

    public Integer find(String openId) {
        return userRepository.findId(openId);
    }

    public UserInfoForChat findChatInfo(Integer userId) {
        return userRepository.findChatInfo(userId);
    }

    public void update(LoginRequest infoDTO, String openid) {
        int row = userRepository.update(assemble(infoDTO, openid));
        Preconditions.checkState(row == 1, "login update error openid" + openid);
    }

    private UserDO assemble(LoginRequest infoDTO, String openid) {
        UserDO userDO = new UserDO();
        userDO.setOpenid(openid);
        userDO.setDeleted(false);
        if (infoDTO.getUserInfo() != null) {
            userDO.setGender(infoDTO.getUserInfo().getGender());
            userDO.setNickName(infoDTO.getUserInfo().getNickName());
            userDO.setAvatarUrl(infoDTO.getUserInfo().getAvatarUrl());
            userDO.setCountry(infoDTO.getUserInfo().getCountry());
            userDO.setProvince(infoDTO.getUserInfo().getProvince());
            userDO.setCity(infoDTO.getUserInfo().getCity());
            userDO.setLanguage(infoDTO.getUserInfo().getLanguage());
        }
        return userDO;
    }
}
