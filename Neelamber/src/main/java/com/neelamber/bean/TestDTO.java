package com.neelamber.bean;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Validated
@Data
public class TestDTO {
	@JsonProperty
	private String name;
}
