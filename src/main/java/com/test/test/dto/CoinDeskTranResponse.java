package com.test.test.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * call coinDesk data transfer response
 * @author jay
 *
 */
@Data
public class CoinDeskTranResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String updateTime;

	private String currencyType;

	private String currencyTypeNT;

	private String exRate;
}
