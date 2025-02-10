package com.altia.inditex.prueba.service;

import java.time.LocalDateTime;
import java.util.List;

import com.altia.inditex.prueba.adapters.ResponsePrice;

public interface PriceService {
	
	public List<ResponsePrice> findByStartDateProductIdBrand(LocalDateTime start, Long productId, Long brandId);

}
