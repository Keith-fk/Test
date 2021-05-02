package com.jaeden.pin.domain.request;

import com.jaeden.pin.domain.address.Dot;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类AdviceOrderRequest.java 的实现描述：
 *
 */
@Data
@EqualsAndHashCode
public class AdviceOrderRequest extends BaseRequest{
    private Dot dot;
}
