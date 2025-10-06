package com.inditex.inditex_iot_backend.domain.port.out;

import com.inditex.inditex_iot_backend.domain.model.Price;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Output port: how the domain obtains the available prices.
 * (Will be implemented later by the persistence adapter).
 */
public interface LoadPricesPort {
    List<Price> loadPrices(int brandId, long productId, LocalDateTime applicationDate);
}
