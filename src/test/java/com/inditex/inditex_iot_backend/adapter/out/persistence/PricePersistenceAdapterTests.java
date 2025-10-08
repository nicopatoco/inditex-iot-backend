package com.inditex.inditex_iot_backend.adapter.out.persistence;

import com.inditex.inditex_iot_backend.adapter.out.persistence.mapper.EntityToDomainMapper;
import com.inditex.inditex_iot_backend.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PricePersistenceAdapterTests {

    @Mock
    private JpaPriceRepository repository;

    @Mock
    private EntityToDomainMapper mapper;

    private PricePersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PricePersistenceAdapter(repository, mapper);
    }

    @Test
    @DisplayName("Should return mapped price when entity found")
    void shouldReturnMappedPriceWhenEntityFound() {
        // Given
        int brandId = 1;
        long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T16:00:00");

        BrandJpaEntity brand = new BrandJpaEntity();
        brand.setId(brandId);

        PriceJpaEntity entity = new PriceJpaEntity();
        entity.setBrand(brand);
        entity.setProductId(productId);

        Price expectedPrice = new Price(brandId, productId, 2, 1,
                new BigDecimal("25.45"), "EUR",
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"));

        when(repository.findApplicable(eq(brandId), eq(productId), eq(applicationDate), any(PageRequest.class)))
                .thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(expectedPrice);

        // When
        Optional<Price> result = adapter.loadPrice(brandId, productId, applicationDate);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedPrice, result.get());
        verify(repository, times(1)).findApplicable(eq(brandId), eq(productId), eq(applicationDate),
                any(PageRequest.class));
        verify(mapper, times(1)).toDomain(entity);
    }

    @Test
    @DisplayName("Should return empty when no entity found")
    void shouldReturnEmptyWhenNoEntityFound() {
        // Given
        int brandId = 1;
        long productId = 99999L;
        LocalDateTime applicationDate = LocalDateTime.parse("2019-01-01T00:00:00");

        when(repository.findApplicable(eq(brandId), eq(productId), eq(applicationDate), any(PageRequest.class)))
                .thenReturn(Collections.emptyList());

        // When
        Optional<Price> result = adapter.loadPrice(brandId, productId, applicationDate);

        // Then
        assertFalse(result.isPresent());
        verify(repository, times(1)).findApplicable(eq(brandId), eq(productId), eq(applicationDate),
                any(PageRequest.class));
        verify(mapper, never()).toDomain(any());
    }
}