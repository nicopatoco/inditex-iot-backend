package com.inditex.inditex_iot_backend.domain.service;

import com.inditex.inditex_iot_backend.domain.model.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PriceSelectorTest {

    private final PriceSelector selector = new PriceSelector();

    @Test
    void shouldSelectPriceWithHighestPriority() {
        Price lowPriority = new Price(1, 35455, 1, 0,
                new BigDecimal("35.50"), "EUR",
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"));

        Price highPriority = new Price(1, 35455, 2, 1,
                new BigDecimal("25.45"), "EUR",
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"));

        Optional<Price> selected = selector.select(List.of(lowPriority, highPriority));

        assertTrue(selected.isPresent());
        assertEquals(2, selected.get().getPriceList());
        assertEquals(new BigDecimal("25.45"), selected.get().getAmount());
    }

    @Test
    void shouldSelectMostRecentIfSamePriority() {
        Price older = new Price(1, 35455, 3, 1,
                new BigDecimal("30.50"), "EUR",
                LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"));

        Price newer = new Price(1, 35455, 4, 1,
                new BigDecimal("38.95"), "EUR",
                LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"));

        Optional<Price> selected = selector.select(List.of(older, newer));

        assertTrue(selected.isPresent());
        assertEquals(4, selected.get().getPriceList());
        assertEquals(new BigDecimal("38.95"), selected.get().getAmount());
    }
}
