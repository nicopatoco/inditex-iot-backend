package com.inditex.inditex_iot_backend.config;

import com.inditex.inditex_iot_backend.domain.service.PriceSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public PriceSelector priceSelector() {
        return new PriceSelector();
    }
}
