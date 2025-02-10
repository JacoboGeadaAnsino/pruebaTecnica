package com.altia.inditex.prueba.adapters;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConsultaError {
	
	private String errorResponse;
	private Long errorCode;
	@JsonIgnore
	private HttpStatus status;

}
