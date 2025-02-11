package com.altia.inditex.prueba.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.altia.inditex.prueba.adapters.ConsultaError;
import com.altia.inditex.prueba.adapters.ResponsePrice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@Validated
@RequestMapping({ "/api" })
public interface ConsultaController {
	@Operation(summary = "Consulta por producto, marca y fecha de vigencia")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Producto encontrado", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponsePrice.class))) }),
			@ApiResponse(responseCode = "204", description = "Producto no encontrado"),
			@ApiResponse(responseCode = "400", description = "Error de parametros", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaError.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultaError.class)) }) })
	@GetMapping(path = { "/consulta" }, produces = { "application/json" })
	ResponseEntity<List<ResponsePrice>> findByStartDateAndProduct(
			@Parameter(required = true, name = "Fecha de inicio de vigencia del precio", example = "2025-02-10T19:04:00") @Valid @RequestParam(value = "startDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime paramLocalDateTime,
			@Parameter(required = true, name = "Id de la marca consultar", example = "1") @Valid @RequestParam(value = "brandId", required = true) Long paramLong1,
			@Parameter(required = true, name = "Id de producto", example = "2") @Valid @RequestParam(value = "productId", required = true) Long paramLong2);
}