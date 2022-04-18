package com.test.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.test.test.dto.CoinDeskDetailResponse;
import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;
import com.test.test.dto.GetResponseBpi;
import com.test.test.dto.GetResponseCurrency;
import com.test.test.dto.GetResponseTime;
import com.test.test.enums.State;
import com.test.test.service.ApiService;
import com.test.test.template.ResponseFactory;
import com.test.test.template.ResponseTemplate;

@SuppressWarnings("unused")
@SpringBootTest
public class ApiControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ApiController apiController;

	@Autowired
	private ResponseFactory responseFactory;

	@MockBean
	private ApiService apiService;

	@BeforeEach
	public void setpup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.apiController).build();
	}

	/**
	 * build API response
	 * 
	 * @return
	 */
	private ResponseTemplate<GetResponse> createGetResponse() {
		GetResponse getResponse = new GetResponse();
		GetResponseBpi bpi = new GetResponseBpi();
		GetResponseCurrency usd = new GetResponseCurrency();
		GetResponseTime time = new GetResponseTime();
		usd.setCode("USD");
		usd.setSymbol("&#36;");
		usd.setRate("40,204.6717");
		usd.setDescription("United States Dollar");
		usd.setRateFloat("40204.6717");
		time.setUpdated("Apr 15, 2022 11:48:00 UTC");
		time.setUpdatedISO("2022-04-15T11:48:00+00:00");
		time.setUpdateduk("Apr 15, 2022 at 12:48 BST");
		bpi.setUsd(usd);
		getResponse.setBpi(bpi);
		getResponse.setChartName("Bitcoin");
		getResponse.setDisclaimer(
				"disclaimer This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org");
		getResponse.setTime(time);
		return responseFactory.genResponse(getResponse);
	}

	/**
	 * build API TRAN response
	 * 
	 * @return
	 */
	private ResponseTemplate<CoinDeskResponse> createCoinDeskResponse() {
		CoinDeskResponse coinDeskResponse = new CoinDeskResponse();
		List<CoinDeskDetailResponse> coinDeskDetailResponseList = new ArrayList<>();
		CoinDeskDetailResponse coinDeskDetailResponse = new CoinDeskDetailResponse();
		coinDeskDetailResponse.setCode("USD");
		coinDeskDetailResponse.setCodeNT("美金");
		coinDeskDetailResponse.setRate("38,934.7150");
		coinDeskResponse.setTime("2022-04-18 15:57:00");
		coinDeskDetailResponseList.add(coinDeskDetailResponse);
		coinDeskResponse.setDetailList(coinDeskDetailResponseList);

		return responseFactory.genResponse(coinDeskResponse);
	}

	/**
	 * controller單元測試 call API 成功案例
	 */
	@Test
	void callApiTest() {
		when(apiService.callApi()).thenReturn(createGetResponse());

		try {
			// 模擬post，定義request為JSON格式
			ResultActions resultActions = mockMvc.perform(post("/cur/coinDesk").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.SUCCESS.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 call API TRAN 成功案例
	 */
	@Test
	void callApiTranTest() {
		when(apiService.callApiTran()).thenReturn(createCoinDeskResponse());

		try {
			ResultActions resultActions = mockMvc
					.perform(post("/cur/coinDesk/tran").contentType(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk()).andExpect(jsonPath("$.header.code", is(State.SUCCESS.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
}
