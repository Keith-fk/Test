package com.jaeden.pin.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.jaeden.pin.domain.BaseResponse;
import com.jaeden.pin.domain.request.LoginRequest;
import com.jaeden.pin.service.CacheService;
import com.jaeden.pin.service.UserService;
import com.jaeden.pin.service.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类LoginController.java的实现
 *
 */
@RestController
@RequestMapping("auth")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private WxMaService wxService;
    @Autowired
    private CacheService cacheService;

    @PostMapping("login")
    public BaseResponse login(@RequestBody LoginRequest info) {
        BaseResponse response = new BaseResponse();
        WxMaJscode2SessionResult result = null;
        try {
            result = wxService.getUserService().getSessionInfo(info.getCode());
        } catch (WxErrorException e) {
            log.warn("login fail/wx error e:{}", e.getMessage());
        }
        if (result == null) {
            log.warn("session result is null code:{}", info.getCode());
            return ResponseUtil.errorResponse("session result is null");
        }
        String openId = result.getOpenid();
        Integer old = userService.find(openId);
        if (old == null) {
            old = userService.insert(info, openId);
        } else {
            userService.update(info, openId);
        }
        String token = cacheService.addUserId(old, result.getSessionKey(), openId);
        response.setSuccess(true);
        response.setData(token);
        return response;
    }
}
