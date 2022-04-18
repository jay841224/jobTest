package com.test.test.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * coinDesk API response cur
 * @author jay
 *
 */
@Data
public class GetResponseCurrency implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String symbol;
	
	private String rate;
	
	private String description;
	
	@JsonProperty("rate_float")
	private String rateFloat;
}
