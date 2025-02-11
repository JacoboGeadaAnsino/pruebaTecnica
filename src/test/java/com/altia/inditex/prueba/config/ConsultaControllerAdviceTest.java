package com.altia.inditex.prueba.config;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;

@SpringBootTest
class ConsultaControllerAdviceTest {

	private ConsultaControllerAdvice controllerAdvice;

	@Mock
	private HttpServletRequest request;
	
	@BeforeEach
	public void setUp() {
		controllerAdvice = new ConsultaControllerAdvice();
	}

	@Test
	@DisplayName("Error 400 brandId invalido")
	public void tc001() {
		MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("invalidValue",
				Integer.class, "brandId", null, new NumberFormatException());
		try {
		val response = controllerAdvice.handle(exception, request);
		assertAll(() -> {
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals(1000L, response.getBody().getErrorCode());
			assertEquals("Error de formato de datos", response.getBody().getErrorResponse());
		});
        } catch (IOException e) {
        	fail(e);
        }

	}

	@Test
	@DisplayName("Error 400 product id invalido")
	public void tc002() {
		MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("invalidValue",
				Integer.class, "productId", null, new NumberFormatException());

		try {
			val response = controllerAdvice.handle(exception, request);

			assertAll(() -> {
				assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
				assertEquals(1001L, response.getBody().getErrorCode());
				assertEquals("Error de formato de datos", response.getBody().getErrorResponse());
			});
		} catch (IOException e) {
			fail(e);
		}
	}

	@Test
	@DisplayName("Error 400 formato de fecha")
	public void tc003() {
		MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("invalidValue",
				LocalDate.class, "startDate", null, new ConversionFailedException(null, null, null, null));
		try {
			val response = controllerAdvice.handle(exception, request);
			assertAll(() -> {
				assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
				assertEquals(1002L, response.getBody().getErrorCode());
				assertEquals("Error de formato de datos", response.getBody().getErrorResponse());
			});
		} catch (IOException e) {
			fail(e);
		}

	}

	@Test
	@DisplayName("Error desconocido")
	public void testHandleUnknownParameterException() {
		MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("invalidValue",
				Integer.class, "unknownParam", null, new Exception());
		try {
			val response = controllerAdvice.handle(exception, request);

			assertAll(() -> {
				assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
				assertEquals(5000L, response.getBody().getErrorCode());
				assertEquals("Error de formato de datos", response.getBody().getErrorResponse());
			});
		} catch (IOException e) {
			fail(e);
		}

	}
}
