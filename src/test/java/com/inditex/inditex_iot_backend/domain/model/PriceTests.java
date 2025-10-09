package com.inditex.inditex_iot_backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceTests {

  private static final String PRICE_AMOUNT = "25.45";

  @Test
  @DisplayName("Should create Price with all fields correctly")
  void shouldCreatePriceCorrectly() {
    // Given
    int brandId = 1;
    long productId = 35455L;
    int priceList = 2;
    int priority = 1;
    BigDecimal amount = new BigDecimal(PRICE_AMOUNT);
    String currency = "EUR";
    LocalDateTime startDate = LocalDateTime.parse("2020-06-14T15:00:00");
    LocalDateTime endDate = LocalDateTime.parse("2020-06-14T18:30:00");

    // When
    Price price =
        new Price(brandId, productId, priceList, priority, amount, currency, startDate, endDate);

    // Then
    assertEquals(brandId, price.getBrandId());
    assertEquals(productId, price.getProductId());
    assertEquals(priceList, price.getPriceList());
    assertEquals(priority, price.getPriority());
    assertEquals(amount, price.getAmount());
    assertEquals(currency, price.getCurrency());
    assertEquals(startDate, price.getStartDate());
    assertEquals(endDate, price.getEndDate());
  }

  @Test
  @DisplayName("Should consider prices equal when brandId, productId and priceList match")
  void shouldBeEqualWhenKeysMatch() {
    // Given
    Price price1 =
        new Price(
            1,
            35455L,
            2,
            1,
            new BigDecimal(PRICE_AMOUNT),
            "EUR",
            LocalDateTime.parse("2020-06-14T15:00:00"),
            LocalDateTime.parse("2020-06-14T18:30:00"));
    Price price2 =
        new Price(
            1,
            35455L,
            2,
            5,
            new BigDecimal("99.99"),
            "USD",
            LocalDateTime.parse("2020-01-01T00:00:00"),
            LocalDateTime.parse("2025-12-31T23:59:59"));

    // Then
    assertEquals(price1, price2);
    assertEquals(price1.hashCode(), price2.hashCode());
  }

  @Test
  @DisplayName("Should not be equal when keys differ")
  void shouldNotBeEqualWhenKeysDiffer() {
    // Given
    Price price1 =
        new Price(
            1,
            35455L,
            2,
            1,
            new BigDecimal(PRICE_AMOUNT),
            "EUR",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1));
    Price price2 =
        new Price(
            1,
            35455L,
            3,
            1,
            new BigDecimal(PRICE_AMOUNT),
            "EUR",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1));

    // Then
    assertNotEquals(price1, price2);
  }

  @Test
  @DisplayName("Should be equal to itself")
  void shouldBeEqualToItself() {
    // Given
    Price price =
        new Price(
            1,
            35455L,
            2,
            1,
            new BigDecimal(PRICE_AMOUNT),
            "EUR",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1));

    // Then
    assertEquals(price, price);
  }

  @Test
  @DisplayName("Should not be equal to null")
  void shouldNotBeEqualToNull() {
    // Given
    Price price =
        new Price(
            1,
            35455L,
            2,
            1,
            new BigDecimal(PRICE_AMOUNT),
            "EUR",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1));

    // Then
    assertNotEquals(null, price);
  }

  @Test
  @DisplayName("Should not be equal to different class")
  void shouldNotBeEqualToDifferentClass() {
    // Given
    Price price =
        new Price(
            1,
            35455L,
            2,
            1,
            new BigDecimal(PRICE_AMOUNT),
            "EUR",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1));

    // Then
    assertNotEquals("Not a Price", price);
  }
}
