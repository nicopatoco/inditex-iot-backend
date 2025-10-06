package com.inditex.inditex_iot_backend.adapter.in.web;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.inditex_iot_backend.adapter.in.web.dto.PriceResponse;
import com.inditex.inditex_iot_backend.adapter.in.web.mapper.PriceDtoMapper;
import com.inditex.inditex_iot_backend.domain.port.in.GetApplicablePriceUseCase;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/prices")
@Validated
public class PricingController {

    private final GetApplicablePriceUseCase useCase;
    private final PriceDtoMapper mapper;

    public PricingController(GetApplicablePriceUseCase useCase, PriceDtoMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getApplicablePrice(
            @RequestParam @Min(1) int brandId,
            @RequestParam @Min(1) long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        return useCase.getApplicablePrice(brandId, productId, applicationDate)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
