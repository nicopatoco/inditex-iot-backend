package com.inditex.inditex_iot_backend.application.usecase;

import com.inditex.inditex_iot_backend.domain.model.Price;
import com.inditex.inditex_iot_backend.domain.port.in.GetApplicablePriceUseCase;
import com.inditex.inditex_iot_backend.domain.port.out.LoadPricePort;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

  private final LoadPricePort loadPricePort;

  public GetApplicablePriceService(LoadPricePort loadPricePort) {
    this.loadPricePort = loadPricePort;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Price> getApplicablePrice(
      int brandId, long productId, LocalDateTime applicationDate) {
    return loadPricePort.loadPrice(brandId, productId, applicationDate);
  }
}
