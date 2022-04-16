package com.test.test.service;

import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;
import com.test.test.template.ResponseTemplate;

public interface ApiService {

	ResponseTemplate<GetResponse> callApi();

	ResponseTemplate<CoinDeskResponse> callApiTran() throws Exception;

}
