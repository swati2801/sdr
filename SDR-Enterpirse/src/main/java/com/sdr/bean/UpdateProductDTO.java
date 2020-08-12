package com.sdr.bean;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Validated
@Data
public class UpdateProductDTO {

	@JsonProperty
	private List<UpdateProductListDTO> listToUpdate;
	
}
