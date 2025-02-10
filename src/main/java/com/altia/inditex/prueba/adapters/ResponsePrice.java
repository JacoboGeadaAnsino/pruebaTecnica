package com.altia.inditex.prueba.adapters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa
a aplicar, fechas de aplicación y precio final a aplicar.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponsePrice{
	
	@JsonProperty("identificador_de_producto")
	private Long idProducto;
	
	@JsonProperty("identificador_de_cadena")
	private Long idCadena;
	
	@JsonProperty("tarifa")
	private Long tarifa;
	
	@JsonProperty("inicio_vigencia_precio")
	private LocalDateTime inicioVigencia;
	
	@JsonProperty("fin_vigencia_precio")
	private LocalDateTime finVigencia;
	
	@JsonProperty("pvp")
	private BigDecimal precio;
	
	@JsonIgnore
	public boolean isEmpty() {
		return Objects.isNull(idProducto) && Objects.isNull(idCadena) && Objects.isNull(tarifa) && Objects.isNull(finVigencia)
				&& Objects.isNull(finVigencia) && Objects.isNull(precio);
	}

}
