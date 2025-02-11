package com.altia.inditex.prueba.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.altia.inditex.prueba.adapters.ResponsePrice;
import com.altia.inditex.prueba.service.PriceService;

import lombok.val;

@SpringBootTest
class ConsultaControllerImplTest {
	
	@Mock
	private PriceService service;
	private ConsultaController controller;
	
	@BeforeEach
	public void setUp() {
		controller = new ConsultaControllerImpl(service);
	}

	@Test
	@DisplayName("Respuesta 200 con datos")
	void tc001() {
		when(service.findByStartDateProductIdBrand(any(), anyLong(), anyLong()))
			.thenReturn(Arrays.asList(ResponsePrice.builder()
					.finVigencia(LocalDateTime.now().plusDays(5L))
					.inicioVigencia(LocalDateTime.now().minusDays(5L))
					.idCadena(1L)
					.idProducto(35455L)
					.precio(new BigDecimal("35.50"))
					.tarifa(null)
					.build()));
		val response = controller.findByStartDateAndProduct(LocalDateTime.now(), 35455L, 1L);
		assertAll(() -> {
			assertEquals(HttpStatus.OK, response.getStatusCode(), () -> "no coincide el status code http");
			assertNotNull(response.getBody(), () -> "Body null");
			assertTrue(!response.getBody().isEmpty(), () -> "Array vacio");
			assertNotNull(response.getBody().get(0), () -> "respuesta sin datos");
		});
	}
	
	
	@Test
	@DisplayName("Respuesta 204 con datos")
	void tc002() {
		when(service.findByStartDateProductIdBrand(any(), anyLong(), anyLong()))
			.thenReturn(Arrays.asList());
		val response = controller.findByStartDateAndProduct(LocalDateTime.now(), 35455L, 1L);
		assertAll(() -> {
			assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), () -> "no coincide el status code http");
			assertNull(response.getBody(), () -> "Body NOT null");
		});
	}
	
	

}
