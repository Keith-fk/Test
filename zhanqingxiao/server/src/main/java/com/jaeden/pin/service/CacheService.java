package com.jaeden.pin.service;

import com.jaeden.pin.domain.address.Dot;
import com.jaeden.pin.domain.order.OrderCacheDO;
import com.jaeden.pin.domain.order.TimeDTO;
import com.jaeden.pin.domain.request.FuzzyOrderRequest;
import com.jaeden.pin.domain.request.PublishOrderRequest;
import com.jaeden.pin.domain.user.UserToken;
import com.jaeden.pin.infrastructure.cache.GeoCache;
import com.jaeden.pin.infrastructure.cache.TokenCache;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类CacheService.java的实现
 *

 */
@Service
public class CacheService {
    @Autowired
    private TokenCache tokenCache;
    @Autowired
    private GeoCache geoCache;


    public String addUserId(Integer id, String sessionKey, String openid) {
        String token = RandomStringUtils.randomAlphanumeric(16);
        UserToken userToken = tokenCache.generateToken(token, id, sessionKey, openid);
        return userToken.getToken();
    }

    public Integer getUserId(String token) {
        UserToken userToken = tokenCache.getInfo(token);
        if (userToken == null) {
            return null;
        }
        return userToken.getUserId();
    }

    public String getOpenId(String token) {
        UserToken userToken = tokenCache.getInfo(token);
        if (userToken == null) {
            return null;
        }
        return userToken.getOpenid();
    }

    public List<Integer> findNearOrderId(FuzzyOrderRequest request) {
        List<Integer> startOrderIdList = geoCache.findOrderId(request.getStartDot(), true);
        List<Integer> endOrderIdList = geoCache.findOrderId(request.getEndDot(), false);
        startOrderIdList.retainAll(endOrderIdList);
        return startOrderIdList;
    }

    public void publishCache(PublishOrderRequest request, Integer orderId) {
        OrderCacheDO orderCacheDO = new OrderCacheDO();
        orderCacheDO.setOrderId(orderId);
        orderCacheDO.setTargetTime(assemble(request.getTimeDTO()));

        publishAddress(true, request.getStartAddress().getDot(), orderCacheDO);
        publishAddress(false, request.getEndAddress().getDot(), orderCacheDO);
    }

    private void publishAddress(boolean isStart, Dot dot, OrderCacheDO orderCacheDO) {
        geoCache.add(isStart, dot, orderCacheDO);
    }


    private LocalDateTime assemble(TimeDTO time) {
        return LocalDateTime.of(time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute());
    }

    List<Integer> startAdvice(Dot dot) {
        return geoCache.findOrderId(dot, true);
    }
}
