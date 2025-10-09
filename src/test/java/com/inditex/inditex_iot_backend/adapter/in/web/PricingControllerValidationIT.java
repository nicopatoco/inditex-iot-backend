package com.inditex.inditex_iot_backend.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PricingControllerValidationIT {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturn400WhenBrandIdInvalid() throws Exception {
    mockMvc
        .perform(
            get("/prices")
                .param("brandId", "0")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-14T10:00:00"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturn400WhenDateFormatIsInvalid() throws Exception {
    mockMvc
        .perform(
            get("/prices")
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-14 10:00:00")) // without 'T'
        .andExpect(status().isBadRequest());
  }
}
