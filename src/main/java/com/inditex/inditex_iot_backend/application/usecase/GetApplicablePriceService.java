package com.inditex.inditex_iot_backend.application.usecase;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.inditex_iot_backend.domain.model.Price;
import com.inditex.inditex_iot_backend.domain.port.in.GetApplicablePriceUseCase;
import com.inditex.inditex_iot_backend.domain.port.out.LoadPricesPort;
import com.inditex.inditex_iot_backend.domain.service.PriceSelector;

@Service
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final LoadPricesPort loadPricesPort;
    private final PriceSelector selector;

    public GetApplicablePriceService(LoadPricesPort loadPricesPort, PriceSelector selector) {
        this.loadPricesPort = loadPricesPort;
        this.selector = selector;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> getApplicablePrice(int brandId, long productId, LocalDateTime applicationDate) {
        var candidates = loadPricesPort.loadPrices(brandId, productId, applicationDate);
        return selector.select(candidates);
    }
}
