package com.inditex.inditex_iot_backend.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.inditex_iot_backend.domain.model.Price;
import com.inditex.inditex_iot_backend.domain.port.out.LoadPricePort;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class GetApplicablePriceServiceTest {

  @Test
  void returnsSelectedPriceFromCandidates() {
    LoadPricePort load = mock(LoadPricePort.class);

    var when = LocalDateTime.parse("2020-06-14T16:00:00");
    var p =
        new Price(
            1,
            35455,
            2,
            1,
            new BigDecimal("25.45"),
            "EUR",
            LocalDateTime.parse("2020-06-14T15:00:00"),
            LocalDateTime.parse("2020-06-14T18:30:00"));

    when(load.loadPrice(1, 35455L, when)).thenReturn(Optional.of(p));

    var service = new GetApplicablePriceService(load);
    Optional<Price> result = service.getApplicablePrice(1, 35455L, when);

    assertTrue(result.isPresent());
    assertEquals(2, result.get().getPriceList());
    verify(load, times(1)).loadPrice(1, 35455L, when);
  }
}
