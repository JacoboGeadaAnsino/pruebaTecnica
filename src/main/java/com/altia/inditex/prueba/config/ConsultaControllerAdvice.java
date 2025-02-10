package com.altia.inditex.prueba.config;

import java.io.IOException;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.altia.inditex.prueba.adapters.ConsultaError;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ConsultaControllerAdvice extends ResponseEntityExceptionHandler {
	

	
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ConsultaError> handle(MethodArgumentTypeMismatchException exception, HttpServletRequest request) throws IOException {
		log.error("Error de formato {} ", exception.getMessage());
		val consultaError  = new ConsultaError();
		consultaError.setStatus(HttpStatus.BAD_REQUEST);
		consultaError.setErrorResponse("Error de formato de datos");
		switch (exception.getName()) {
			case "brandId": {
				if(exception.getCause() instanceof NumberFormatException) {
					consultaError.setErrorCode(1000L);
				} else {
					log.error("Error generico de datos");
					consultaError.setErrorCode(5000L);
					consultaError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				break;
			}
			case "productId" : {
				if(exception.getCause() instanceof NumberFormatException) {
					consultaError.setErrorCode(1001L);
				} else {
					log.error("Error generico de datos");
					consultaError.setErrorCode(5000L);
					consultaError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				break;
			}
			case "startDate" : {
				if(exception.getCause() instanceof ConversionFailedException) {
					consultaError.setErrorCode(1002L);
				} else {
					log.error("Error generico de datos");
					consultaError.setErrorCode(5000L);
					consultaError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				break;
			}
		default:
			log.error("Error desconocido de datos");
			consultaError.setErrorCode(5000L);
			consultaError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(consultaError, consultaError.getStatus());

	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request){
		log.error("Error de validacion de datos {}", exception.getMessage());
		val consultaError = new ConsultaError();
		consultaError.setErrorCode(1003L);
		consultaError.setErrorResponse("Error de peticion sin datos obligatorios");
		return new ResponseEntity<>(consultaError, HttpStatus.BAD_REQUEST);

	}

}
