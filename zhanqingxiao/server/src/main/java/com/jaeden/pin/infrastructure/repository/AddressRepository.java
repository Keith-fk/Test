package com.jaeden.pin.infrastructure.repository;

import com.jaeden.pin.domain.address.AddressDO;
import com.jaeden.pin.domain.address.Dot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 类AddressRepository.java
 *
 */
@Mapper
public interface AddressRepository {
    /**
     * 增加地址
     *
     * @param addressDO
     * @return
     */
    int insert(@Param("addressDO") AddressDO addressDO);

    /**
     * 查询地址
     *
     * @param dot
     * @return
     */
    AddressDO find(@Param("dot") Dot dot);

    /**
     * 增加地址
     *
     * @param id
     */
    void addPoint(@Param("id") Integer id);

    /**
     * 查询地址
     *
     * @param id
     * @return
     */
    AddressDO findById(@Param("id") Integer id);
}
