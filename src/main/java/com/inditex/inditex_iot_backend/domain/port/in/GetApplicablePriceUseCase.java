package com.inditex.inditex_iot_backend.domain.port.in;

import com.inditex.inditex_iot_backend.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Input port: define which external operations Could be requested by the system regarding prices.
 */
public interface GetApplicablePriceUseCase {
  Optional<Price> getApplicablePrice(int brandId, long productId, LocalDateTime applicationDate);
}
