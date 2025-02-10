package com.altia.inditex.prueba.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.altia.inditex.prueba.adapters.ResponsePrice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
@Validated
@RequestMapping("/api")
public interface ConsultaController {
	
	@Operation(summary = "Consulta por producto, marca y fecha de vigencia")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Producto encontrado"),
		    @ApiResponse(responseCode = "204", description = "Producto no encontrado"),
		    @ApiResponse(responseCode = "400", description = "Error de parametros"),
		    @ApiResponse(responseCode = "500", description = "Error interno")
		})
	@GetMapping(path = "/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponsePrice>> findByStartDateAndProduct(
			 @Parameter(required = true, name = "Fecha de inicio de vigencia del precio",example="2025-02-10T19:04:00")
			@Valid @RequestParam(value = "startDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			 @Parameter(required = true, name = "Id de la marca consultar",example="1")
			@Valid @RequestParam(value = "brandId", required = true) Long brandId,
			 @Parameter(required = true, name = "Id de producto",example="2")
			@Valid @RequestParam(value = "productId", required = true) Long productId);
}
