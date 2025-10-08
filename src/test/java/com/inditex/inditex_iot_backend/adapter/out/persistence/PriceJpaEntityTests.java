package com.inditex.inditex_iot_backend.adapter.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceJpaEntityTests {

    private BrandJpaEntity brand;

    @BeforeEach
    void setUp() {
        brand = new BrandJpaEntity();
        brand.setId(1);
        brand.setName("ZARA");
    }

    @Test
    @DisplayName("Should set and get all fields correctly")
    void shouldSetAndGetFields() {
        // Given
        PriceJpaEntity price = new PriceJpaEntity();

        // When
        price.setBrand(brand);
        price.setProductId(35455L);
        price.setPriceList(2);
        price.setPriority(1);
        price.setPrice(new BigDecimal("25.45"));
        price.setCurr("EUR");
        price.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        price.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));

        // Then
        assertNull(price.getId()); // ID is generated
        assertEquals(brand, price.getBrand());
        assertEquals(35455L, price.getProductId());
        assertEquals(2, price.getPriceList());
        assertEquals(1, price.getPriority());
        assertEquals(new BigDecimal("25.45"), price.getPrice());
        assertEquals("EUR", price.getCurr());
        assertEquals(LocalDateTime.parse("2020-06-14T15:00:00"), price.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-14T18:30:00"), price.getEndDate());
    }

    @Test
    @DisplayName("Should be equal when IDs match")
    void shouldBeEqualWhenIdsMatch() {
        // Given
        PriceJpaEntity price1 = createPriceEntity(1L);
        PriceJpaEntity price2 = createPriceEntity(1L);

        // Then
        assertEquals(price1, price2);
    }

    @Test
    @DisplayName("Should not be equal when IDs differ")
    void shouldNotBeEqualWhenIdsDiffer() {
        // Given
        PriceJpaEntity price1 = createPriceEntity(1L);
        PriceJpaEntity price2 = createPriceEntity(2L);

        // Then
        assertNotEquals(price1, price2);
    }

    @Test
    @DisplayName("Should be equal to itself")
    void shouldBeEqualToItself() {
        // Given
        PriceJpaEntity price = createPriceEntity(1L);

        // Then
        assertEquals(price, price);
    }

    @Test
    @DisplayName("Should not be equal to null")
    void shouldNotBeEqualToNull() {
        // Given
        PriceJpaEntity price = createPriceEntity(1L);

        // Then
        assertNotEquals(null, price);
    }

    @Test
    @DisplayName("Should not be equal to different class")
    void shouldNotBeEqualToDifferentClass() {
        // Given
        PriceJpaEntity price = createPriceEntity(1L);

        // Then
        assertNotEquals("Not a Price", price);
    }

    @Test
    @DisplayName("Should have consistent hashCode")
    void shouldHaveConsistentHashCode() {
        // Given
        PriceJpaEntity price1 = createPriceEntity(1L);
        PriceJpaEntity price2 = createPriceEntity(1L);

        // Then
        assertEquals(price1.hashCode(), price2.hashCode());
    }

    private PriceJpaEntity createPriceEntity(Long id) {
        PriceJpaEntity price = new PriceJpaEntity();
        // Use reflection to set ID (simulating JPA behavior)
        try {
            var idField = PriceJpaEntity.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(price, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        price.setBrand(brand);
        price.setProductId(35455L);
        price.setPriceList(2);
        price.setPriority(1);
        price.setPrice(new BigDecimal("25.45"));
        price.setCurr("EUR");
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now().plusDays(1));
        return price;
    }
}