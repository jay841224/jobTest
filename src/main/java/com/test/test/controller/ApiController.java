package com.test.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;
import com.test.test.service.ApiService;

@RestController
@RequestMapping("/cur")
public class ApiController {

	@Autowired
	private ApiService apiService;

	/**
	 * call coinDesk api
	 * 
	 * @return
	 */
	@RequestMapping(value = "/coinDesk", method = RequestMethod.POST)
	public GetResponse callApi() {
		return apiService.callApi();
	}

	/**
	 * call coinDesk api and build response
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/coinDesk/tran", method = RequestMethod.POST)
	public CoinDeskResponse callApiTran() throws Exception {
		return apiService.callApiTran();
	}

}
