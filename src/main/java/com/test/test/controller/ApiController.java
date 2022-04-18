package com.test.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;
import com.test.test.service.CurrencyService;
import com.test.test.template.ResponseTemplate;

@RestController
@RequestMapping("/cur")
public class ApiController {

	/** ApiService */
	@Autowired
	private CurrencyService currencyService;

	/**
	 * call coinDesk API
	 * 
	 * @return
	 */
	@RequestMapping(value = "/coinDesk", method = RequestMethod.POST)
	public ResponseTemplate<GetResponse> callApi() {
		return currencyService.callApi();
	}

	/**
	 * call coinDesk API and build response
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/coinDesk/tran", method = RequestMethod.POST)
	public ResponseTemplate<CoinDeskResponse> callApiTran() throws Exception {
		return currencyService.callApiTran();
	}

}
