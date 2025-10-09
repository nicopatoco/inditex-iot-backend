package com.inditex.inditex_iot_backend.adapter.in.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponse {
  private long productId;
  private int brandId;
  private int priceList;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private BigDecimal price;
  private String currency;

  public PriceResponse(
      long productId,
      int brandId,
      int priceList,
      LocalDateTime startDate,
      LocalDateTime endDate,
      BigDecimal price,
      String currency) {
    this.productId = productId;
    this.brandId = brandId;
    this.priceList = priceList;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.currency = currency;
  }

  public long getProductId() {
    return productId;
  }

  public int getBrandId() {
    return brandId;
  }

  public int getPriceList() {
    return priceList;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getCurrency() {
    return currency;
  }
}
