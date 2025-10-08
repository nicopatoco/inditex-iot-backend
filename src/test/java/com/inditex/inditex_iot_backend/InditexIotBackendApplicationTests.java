package com.inditex.inditex_iot_backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class InditexIotBackendApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@DisplayName("Should load Spring context successfully")
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
		assertThat(applicationContext.getBeanDefinitionCount()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Should have all required beans")
	void shouldHaveRequiredBeans() {
		// Verify key beans are loaded
		assertThat(applicationContext.containsBean("getApplicablePriceService")).isTrue();
		assertThat(applicationContext.containsBean("pricePersistenceAdapter")).isTrue();
		assertThat(applicationContext.containsBean("pricingController")).isTrue();
	}
}