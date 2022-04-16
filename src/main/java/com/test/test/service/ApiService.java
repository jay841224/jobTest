package com.test.test.service;

import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;

public interface ApiService {

	GetResponse callApi();

	CoinDeskResponse callApiTran() throws Exception;

}
