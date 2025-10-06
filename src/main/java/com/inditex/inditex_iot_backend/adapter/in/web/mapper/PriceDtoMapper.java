package com.inditex.inditex_iot_backend.adapter.in.web.mapper;

import com.inditex.inditex_iot_backend.adapter.in.web.dto.PriceResponse;
import com.inditex.inditex_iot_backend.domain.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceDtoMapper {
    public PriceResponse toResponse(Price p) {
        return new PriceResponse(
                p.getProductId(),
                p.getBrandId(),
                p.getPriceList(),
                p.getStartDate(),
                p.getEndDate(),
                p.getAmount(),
                p.getCurrency());
    }
}
