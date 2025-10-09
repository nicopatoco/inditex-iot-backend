package com.inditex.inditex_iot_backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Price {
  private final int brandId;
  private final long productId;
  private final int priceList;
  private final int priority;
  private final BigDecimal amount;
  private final String currency;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Price)) return false;
    Price price = (Price) o;
    return brandId == price.brandId && productId == price.productId && priceList == price.priceList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(brandId, productId, priceList);
  }
}
