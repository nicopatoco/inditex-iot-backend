package com.inditex.inditex_iot_backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Price {
    private final int brandId;
    private final long productId;
    private final int priceList;
    private final int priority;
    private final BigDecimal amount;
    private final String currency;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Price(int brandId, long productId, int priceList, int priority,
            BigDecimal amount, String currency,
            LocalDateTime startDate, LocalDateTime endDate) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.priority = priority;
        this.amount = amount;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBrandId() {
        return brandId;
    }

    public long getProductId() {
        return productId;
    }

    public int getPriceList() {
        return priceList;
    }

    public int getPriority() {
        return priority;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Price))
            return false;
        Price price = (Price) o;
        return brandId == price.brandId &&
                productId == price.productId &&
                priceList == price.priceList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, priceList);
    }
}
