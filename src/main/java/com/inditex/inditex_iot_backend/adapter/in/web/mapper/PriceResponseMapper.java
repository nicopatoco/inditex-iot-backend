package com.inditex.inditex_iot_backend.adapter.in.web.mapper;

import com.inditex.inditex_iot_backend.adapter.in.web.dto.PriceResponse;
import com.inditex.inditex_iot_backend.domain.model.Price;

public interface PriceResponseMapper {
    PriceResponse toResponse(Price price);
}