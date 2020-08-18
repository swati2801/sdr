package com.neelamber.bean;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Validated
@Data
public class ResidentDTO {
	
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String flatNo;
	
	@JsonProperty
	private String telephone1;
	
	@JsonProperty
	private String telephone2;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String carNo;

}
