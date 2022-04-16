package com.test.test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * coinDesk api response bpi
 * 
 * @author jay
 *
 */
@Data
public class GetResponseBpi implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("USD")
	private GetResponseCurrency usd;

	@JsonProperty("GBP")
	private GetResponseCurrency gbp;

	@JsonProperty("EUR")
	private GetResponseCurrency eur;

	public List<GetResponseCurrency> getResponseCurrencyList() {
		List<GetResponseCurrency> list = new ArrayList<>();
		list.add(this.usd);
		list.add(this.gbp);
		list.add(this.eur);
		return list;
	}
}
