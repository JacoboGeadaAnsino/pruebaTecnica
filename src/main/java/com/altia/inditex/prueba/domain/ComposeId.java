package com.altia.inditex.prueba.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ComposeId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(name="brand_id", nullable = false, updatable = true, insertable = true)
	private Long brandId;
	@Column(name="product_id", nullable = false, updatable = true, insertable = true)
	private Long productId;
	@Column(name="price_list", nullable = false, updatable = true, insertable = true)
	private Long priceList;


}
