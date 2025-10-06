package com.inditex.inditex_iot_backend;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.inditex.inditex_iot_backend.domain.port.out.LoadPricesPort;

@SpringBootTest
class InditexIotBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		LoadPricesPort loadPricesPortStub() {
			return (brandId, productId, when) -> Collections.emptyList();
		}
	}
}
