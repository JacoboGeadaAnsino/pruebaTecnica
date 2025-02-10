package com.altia.inditex.prueba.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altia.inditex.prueba.domain.ComposeId;
import com.altia.inditex.prueba.domain.PricesEntity;

@Repository
public interface PriceRepository extends JpaRepository<PricesEntity, ComposeId> {
	
	public Optional<PricesEntity> findById(ComposeId id);
	
	
    @Query("SELECT p FROM PricesEntity p WHERE p.id.productId = :productId AND p.id.brandId = :brandId AND :startDate between p.startDate and p.endDate")
	public List<PricesEntity> findByGreaterStartDateAndId(@Param("startDate") LocalDateTime fecha, 
			@Param("brandId") Long brandId,
			@Param("productId") Long productId);

}
