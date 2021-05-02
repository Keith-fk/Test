package com.jaeden.pin.domain.request;

import javax.validation.constraints.NotNull;

/**
 * 类OrderRequest.java
 *
 */
public class OrderRequest extends BaseRequest {
    @NotNull(message = "orderId is null")
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
