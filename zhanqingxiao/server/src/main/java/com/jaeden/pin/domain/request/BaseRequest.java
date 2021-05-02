package com.jaeden.pin.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类BaseRequest.java的实现描述：TODO
 *
 */
@Data
public class BaseRequest {
    @NotNull(message = "token can't null")
    private String token;
}
