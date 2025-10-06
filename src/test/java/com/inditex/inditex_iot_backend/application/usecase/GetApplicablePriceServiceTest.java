package com.inditex.inditex_iot_backend.application.usecase;

import com.inditex.inditex_iot_backend.domain.model.Price;
import com.inditex.inditex_iot_backend.domain.port.out.LoadPricesPort;
import com.inditex.inditex_iot_backend.domain.service.PriceSelector;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetApplicablePriceServiceTest {

    @Test
    void returnsSelectedPriceFromCandidates() {
        LoadPricesPort load = mock(LoadPricesPort.class);
        PriceSelector selector = spy(new PriceSelector());

        var when = LocalDateTime.parse("2020-06-14T16:00:00");
        var p1 = new Price(1, 35455, 1, 0, new BigDecimal("35.50"), "EUR",
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"));
        var p2 = new Price(1, 35455, 2, 1, new BigDecimal("25.45"), "EUR",
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"));

        when(load.loadPrices(1, 35455L, when)).thenReturn(List.of(p1, p2));

        var service = new GetApplicablePriceService(load, selector);
        Optional<Price> result = service.getApplicablePrice(1, 35455L, when);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
        verify(load, times(1)).loadPrices(1, 35455L, when);
    }
}
