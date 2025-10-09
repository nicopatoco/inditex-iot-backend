package com.inditex.inditex_iot_backend.adapter.out.persistence;

import com.inditex.inditex_iot_backend.adapter.out.persistence.mapper.EntityToDomainMapper;
import com.inditex.inditex_iot_backend.domain.model.Price;
import com.inditex.inditex_iot_backend.domain.port.out.LoadPricePort;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PricePersistenceAdapter implements LoadPricePort {

  private final JpaPriceRepository repo;
  private final EntityToDomainMapper mapper;

  public PricePersistenceAdapter(JpaPriceRepository repo, EntityToDomainMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  @Override
  public Optional<Price> loadPrice(int brandId, long productId, LocalDateTime applicationDate) {
    // Query already brings the most prioritary record (ordered + limit 1)
    return repo.findApplicable(brandId, productId, applicationDate, PageRequest.of(0, 1)).stream()
        .findFirst()
        .map(mapper::toDomain);
  }
}
