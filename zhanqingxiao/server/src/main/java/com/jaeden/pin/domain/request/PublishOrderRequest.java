package com.jaeden.pin.domain.request;

import com.jaeden.pin.domain.address.GeoAddress;
import com.jaeden.pin.domain.order.TimeDTO;

import javax.validation.constraints.NotNull;

/**
 * 类PublishOrderRequest.java的实现描述：TODO
 *
 */
public class PublishOrderRequest extends BaseRequest {
    /**
     * 起点
     */
    @NotNull(message = "起点不能空")
    private GeoAddress startAddress;
    /**
     * 终点
     */
    @NotNull(message = "终点不能空")
    private GeoAddress endAddress;
    @NotNull(message = "时间不能空")
    private TimeDTO timeDTO;
    /**
     * 队伍人数
     */
    @NotNull(message = "目标人数")
    private Integer targetNum;

    public GeoAddress getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(GeoAddress startAddress) {
        this.startAddress = startAddress;
    }

    public GeoAddress getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(GeoAddress endAddress) {
        this.endAddress = endAddress;
    }


    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public TimeDTO getTimeDTO() {
        return timeDTO;
    }

    public void setTimeDTO(TimeDTO timeDTO) {
        this.timeDTO = timeDTO;
    }
}
