package com.jaeden.pin.infrastructure.cache;

import com.jaeden.pin.domain.order.OrderDO;
import com.jaeden.pin.domain.order.OrderVO;
import com.jaeden.pin.service.AddressService;
import com.jaeden.pin.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 类OrderCache.java 的实现描述：
 */
@Component
@CacheConfig(cacheNames = "OrderMap")
public class OrderCache {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;

    @Cacheable(key = "#p0.id")
    public OrderVO getOrder(OrderDO orderDO) {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(orderDO.getId());
        orderVO.setStartAddress(addressService.queryGeoAddress(orderDO.getStartAddressId()));
        orderVO.setEndAddress(addressService.queryGeoAddress(orderDO.getEndAddressId()));
        orderVO.setPublishTime(transfer(orderDO.getGmtCreate()));
        orderVO.setUpdateTime(transfer(orderDO.getGmtModified()));
        orderVO.setTargetTime(transfer(orderDO.getTargetTime()));
        orderVO.setTargetNum(orderDO.getTargetNum());
        orderVO.setCurrentNum(orderDO.getCurrentNum());
        orderVO.setLeaderName(userRepository.findChatInfo(orderDO.getLeader()).getNickName());
        return orderVO;
    }

    @CacheEvict(key = "#p0")
    public void clear(Integer orderId) {
    }

    private String transfer(LocalDateTime time) {
        return time.toString().replace("T", " ");
    }

}
