package com.test.test.service;

import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;
import com.test.test.template.ResponseTemplate;

/**
 * CoinDesk API
 * 
 * @author jay
 *
 */
public interface ApiService {

	/** call CoinDesk API */
	ResponseTemplate<GetResponse> callApi();

	/** call CoinDesk API and format */
	ResponseTemplate<CoinDeskResponse> callApiTran();

}
