package com.altia.inditex.prueba.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.altia.inditex.prueba.adapters.ResponsePrice;
import com.altia.inditex.prueba.repository.PriceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
	
	private final PriceRepository priceRepository;

	@Override
	public List<ResponsePrice> findByStartDateProductIdBrand(LocalDateTime start, Long productId, Long brandId) {
		return priceRepository.findByGreaterStartDateAndId(start, brandId, productId)
				.parallelStream().map(price -> ResponsePrice.builder()
						.idCadena(price.getId().getBrandId())
						.idProducto(price.getId().getProductId())
						.inicioVigencia(price.getStartDate())
						.finVigencia(price.getEndDate())
						.precio(price.getPrice())
						.tarifa(null)
						.build()).collect(Collectors.toList());
	}

}
