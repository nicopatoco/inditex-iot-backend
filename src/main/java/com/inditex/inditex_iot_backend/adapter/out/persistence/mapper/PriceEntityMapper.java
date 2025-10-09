package com.inditex.inditex_iot_backend.adapter.out.persistence.mapper;

import com.inditex.inditex_iot_backend.adapter.out.persistence.PriceJpaEntity;
import com.inditex.inditex_iot_backend.domain.model.Price;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between JPA entities and domain models. Follows Single Responsibility
 * Principle.
 */
@Component
public class PriceEntityMapper implements EntityToDomainMapper {
  @Override
  public Price toDomain(PriceJpaEntity entity) {
    return new Price(
        entity.getBrand().getId(),
        entity.getProductId(),
        entity.getPriceList(),
        entity.getPriority(),
        entity.getPrice(),
        entity.getCurr(),
        entity.getStartDate(),
        entity.getEndDate());
  }
}
