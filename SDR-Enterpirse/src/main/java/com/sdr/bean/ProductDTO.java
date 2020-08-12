package com.sdr.bean;


import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Validated
@Data
public class ProductDTO {
	
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private Integer quantity;
	
	@JsonProperty
	private Integer type;
	
	@JsonProperty
	private Integer finalProduct;


}
