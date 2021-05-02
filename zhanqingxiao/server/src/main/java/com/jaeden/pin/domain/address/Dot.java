package com.jaeden.pin.domain.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类Point.java
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dot implements Serializable {
    private static final long serialVersionUID = 779891169234313335L;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
}
