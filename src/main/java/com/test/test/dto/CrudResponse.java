package com.test.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CrudResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String currency;

	private String currencyNT;
}
