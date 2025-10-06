package com.inditex.inditex_iot_backend.domain.service;

import com.inditex.inditex_iot_backend.domain.model.Price;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service of domain that selects the applicable price
 * between several candidates, prioritizing by priority
 * and start dates.
 */
public class PriceSelector {

    public Optional<Price> select(List<Price> candidates) {
        return candidates.stream()
                .sorted(Comparator
                        .comparingInt(Price::getPriority).reversed()
                        .thenComparing(Price::getStartDate).reversed())
                .findFirst();
    }
}
