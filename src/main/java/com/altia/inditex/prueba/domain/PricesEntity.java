package com.altia.inditex.prueba.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "prices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PricesEntity {
	
	/*
	 * BRAND_ID START_DATE END_DATE PRICE_LIST  PRODUCT_ID PRIORITY PRICE CURR  
	 */
	
	@EmbeddedId
	private ComposeId id;
	
	
	@Column(name="start_date", insertable = true, updatable = true)
	private LocalDateTime startDate;
	
	@Column(name="end_date", insertable = true, updatable = true)
	private LocalDateTime endDate;
		
	@Column(name="price", insertable = true, updatable = true)
	private BigDecimal price;
	
	@Column(name="curr")
	private String curr;

	@Column(name="priority", insertable = true, updatable = true)
	private Long priority;
}
