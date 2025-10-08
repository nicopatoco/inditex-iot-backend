package com.inditex.inditex_iot_backend.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandJpaEntityTests {

    @Test
    @DisplayName("Should set and get all fields correctly")
    void shouldSetAndGetFields() {
        // Given
        BrandJpaEntity brand = new BrandJpaEntity();

        // When
        brand.setId(1);
        brand.setName("ZARA");

        // Then
        assertEquals(1, brand.getId());
        assertEquals("ZARA", brand.getName());
    }

    @Test
    @DisplayName("Should be equal when IDs match")
    void shouldBeEqualWhenIdsMatch() {
        // Given
        BrandJpaEntity brand1 = new BrandJpaEntity();
        brand1.setId(1);
        brand1.setName("ZARA");

        BrandJpaEntity brand2 = new BrandJpaEntity();
        brand2.setId(1);
        brand2.setName("BERSHKA");

        // Then
        assertEquals(brand1, brand2);
        assertEquals(brand1.hashCode(), brand2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when IDs differ")
    void shouldNotBeEqualWhenIdsDiffer() {
        // Given
        BrandJpaEntity brand1 = new BrandJpaEntity();
        brand1.setId(1);
        brand1.setName("ZARA");

        BrandJpaEntity brand2 = new BrandJpaEntity();
        brand2.setId(2);
        brand2.setName("ZARA");

        // Then
        assertNotEquals(brand1, brand2);
    }

    @Test
    @DisplayName("Should be equal to itself")
    void shouldBeEqualToItself() {
        // Given
        BrandJpaEntity brand = new BrandJpaEntity();
        brand.setId(1);
        brand.setName("ZARA");

        // Then
        assertEquals(brand, brand);
    }

    @Test
    @DisplayName("Should not be equal to null")
    void shouldNotBeEqualToNull() {
        // Given
        BrandJpaEntity brand = new BrandJpaEntity();
        brand.setId(1);

        // Then
        assertNotEquals(null, brand);
    }

    @Test
    @DisplayName("Should not be equal to different class")
    void shouldNotBeEqualToDifferentClass() {
        // Given
        BrandJpaEntity brand = new BrandJpaEntity();
        brand.setId(1);

        // Then
        assertNotEquals("Not a Brand", brand);
    }

    @Test
    @DisplayName("Should not be equal when one ID is null")
    void shouldNotBeEqualWhenOneIdIsNull() {
        // Given
        BrandJpaEntity brand1 = new BrandJpaEntity();
        brand1.setId(null);

        BrandJpaEntity brand2 = new BrandJpaEntity();
        brand2.setId(1);

        // Then
        assertNotEquals(brand1, brand2);
    }
}