package com.inditex.inditex_iot_backend.domain.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditex_iot_backend.domain.model.Price;

/**
 * Output port: how the domain obtains the available prices.
 * (Will be implemented later by the persistence adapter).
 */
public interface LoadPricePort {
    Optional<Price> loadPrice(int brandId, long productId, LocalDateTime applicationDate);
}
