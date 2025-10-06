package com.inditex.inditex_iot_backend.adapter.out.persistence;

import com.inditex.inditex_iot_backend.domain.model.Price;
import com.inditex.inditex_iot_backend.domain.port.out.LoadPricesPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PricePersistenceAdapter implements LoadPricesPort {

    private final JpaPriceRepository repo;

    public PricePersistenceAdapter(JpaPriceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Price> loadPrices(int brandId, long productId, LocalDateTime applicationDate) {
        return repo.findApplicable(brandId, productId, applicationDate, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(e -> new Price(
                        e.getBrand().getId(),
                        e.getProductId(),
                        e.getPriceList(),
                        e.getPriority(),
                        e.getPrice(),
                        e.getCurr(),
                        e.getStartDate(),
                        e.getEndDate()))
                .map(List::of)
                .orElseGet(List::of);
    }
}
