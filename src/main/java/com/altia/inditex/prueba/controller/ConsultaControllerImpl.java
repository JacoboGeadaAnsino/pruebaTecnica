package com.altia.inditex.prueba.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.altia.inditex.prueba.adapters.ResponsePrice;
import com.altia.inditex.prueba.service.PriceService;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequiredArgsConstructor
public class ConsultaControllerImpl implements ConsultaController {
	
	private final PriceService price;

	@Override
	public ResponseEntity<List<ResponsePrice>> findByStartDateAndProduct(LocalDateTime startDate,
			Long brandId,Long productId) {	
		log.info("Busqueda por productId {} branId {} fecha inicio {}", productId, brandId, productId);
		val response = price.findByStartDateProductIdBrand(startDate, productId, brandId);
		if(response.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(response);
	}



}
