package com.altia.inditex.prueba.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.altia.inditex.prueba.adapters.ResponsePrice;

import lombok.val;


@SpringBootTest
class PriceServiceImplTest {
	
	
	@Autowired 
	private PriceService service;

	

	@Test
	@DisplayName("test ok devuelve datos")
	void tc001() {
		val response = service.findByStartDateProductIdBrand(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35455L, 1L);
		assertAll(() -> {
			assertNotNull(response);
			assertTrue(!response.isEmpty());
			assertNotNull(response.get(0));
			assertTrue(response.get(0) instanceof ResponsePrice);
		});

	}
	
	@Test
	@DisplayName("test ok sin datos para la fecha indicada")
	void tc002() {
		val response = service.findByStartDateProductIdBrand(LocalDateTime.now(), 35455L, 1L);
		assertAll(() -> {
			assertNotNull(response);
			assertTrue(response.isEmpty());
		});

	}
	
	
	@Test
	@DisplayName("test ok sin datos para el producto ")
	void tc003() {
		val response = service.findByStartDateProductIdBrand(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35456L, 1L);
		assertAll(() -> {
			assertNotNull(response);
			assertTrue(response.isEmpty());
		});

	}
	
	
	@Test
	@DisplayName("test ok sin datos para la marca")
	void tc004() {
		val response = service.findByStartDateProductIdBrand(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35455L, 2L);
		assertAll(() -> {
			assertNotNull(response);
			assertTrue(response.isEmpty());
		});

	}
	
	

}
