package com.inditex.inditex_iot_backend.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PricingControllerIT {

  @Autowired private MockMvc mockMvc;

  private static final String PRICES_ENDPOINT = "/prices";
  private static final String BRAND_ID = "brandId";
  private static final String PRODUCT_ID = "productId";
  private static final String APPLICATION_DATE = "applicationDate";
  private static final String PRICE_LIST = "priceList";
  private static final String PRICE = "price";
  private static final String CURRENCY = "currency";
  private static final String PRODUCT_ID_TEST = "35455";

  @Test
  @DisplayName("Test 1: 2020-06-14 10:00 -> price list 1 (35.50 EUR)")
  void test1() throws Exception {
    mockMvc
        .perform(
            get(PRICES_ENDPOINT)
                .param(BRAND_ID, "1")
                .param(PRODUCT_ID, PRODUCT_ID_TEST)
                .param(APPLICATION_DATE, "2020-06-14T10:00:00"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.productId").value(PRODUCT_ID_TEST))
        .andExpect(jsonPath("$." + PRICE_LIST).value(1))
        .andExpect(jsonPath("$." + PRICE).value(35.50))
        .andExpect(jsonPath("$." + CURRENCY).value("EUR"));
  }

  @Test
  @DisplayName("Test 2: 2020-06-14 16:00 -> price list 2 (25.45 EUR)")
  void test2() throws Exception {
    mockMvc
        .perform(
            get(PRICES_ENDPOINT)
                .param(BRAND_ID, "1")
                .param(PRODUCT_ID, PRODUCT_ID_TEST)
                .param(APPLICATION_DATE, "2020-06-14T16:00:00"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$." + PRICE_LIST).value(2))
        .andExpect(jsonPath("$." + PRICE).value(25.45))
        .andExpect(jsonPath("$." + CURRENCY).value("EUR"));
  }

  @Test
  @DisplayName("Test 3: 2020-06-14 21:00 -> price list 1 (35.50 EUR)")
  void test3() throws Exception {
    mockMvc
        .perform(
            get(PRICES_ENDPOINT)
                .param(BRAND_ID, "1")
                .param(PRODUCT_ID, PRODUCT_ID_TEST)
                .param(APPLICATION_DATE, "2020-06-14T21:00:00"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$." + PRICE_LIST).value(1))
        .andExpect(jsonPath("$." + PRICE).value(35.50));
  }

  @Test
  @DisplayName("Test 4: 2020-06-15 10:00 -> price list 3 (30.50 EUR)")
  void test4() throws Exception {
    mockMvc
        .perform(
            get(PRICES_ENDPOINT)
                .param(BRAND_ID, "1")
                .param(PRODUCT_ID, PRODUCT_ID_TEST)
                .param(APPLICATION_DATE, "2020-06-15T10:00:00"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$." + PRICE_LIST).value(3))
        .andExpect(jsonPath("$." + PRICE).value(30.50));
  }

  @Test
  @DisplayName("Test 5: 2020-06-16 21:00 -> price list 4 (38.95 EUR)")
  void test5() throws Exception {
    mockMvc
        .perform(
            get(PRICES_ENDPOINT)
                .param(BRAND_ID, "1")
                .param(PRODUCT_ID, PRODUCT_ID_TEST)
                .param(APPLICATION_DATE, "2020-06-16T21:00:00"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$." + PRICE_LIST).value(4))
        .andExpect(jsonPath("$." + PRICE).value(38.95));
  }

  @Test
  void shouldReturn404WhenNoApplicablePrice() throws Exception {
    mockMvc
        .perform(
            get(PRICES_ENDPOINT)
                .param(BRAND_ID, "1")
                .param(PRODUCT_ID, PRODUCT_ID_TEST)
                .param(APPLICATION_DATE, "2019-01-01T00:00:00"))
        .andExpect(status().isNotFound());
  }
}
