package com.inditex.inditex_iot_backend.adapter.out.persistence.mapper;

import com.inditex.inditex_iot_backend.adapter.out.persistence.PriceJpaEntity;
import com.inditex.inditex_iot_backend.domain.model.Price;

/*
 * Output port: how the domain maps the JPA entities to domain models.
 */
public interface EntityToDomainMapper {
  Price toDomain(PriceJpaEntity entity);
}
