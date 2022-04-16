package com.test.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CoinDeskDetailResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String codeNT;
	
	private String rate;
}
