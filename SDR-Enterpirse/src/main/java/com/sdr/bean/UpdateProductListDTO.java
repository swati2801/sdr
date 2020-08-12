package com.sdr.bean;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Validated
@Data
public class UpdateProductListDTO {
	
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private Integer quantity;
	
}
