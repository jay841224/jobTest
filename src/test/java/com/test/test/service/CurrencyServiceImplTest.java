package com.test.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.test.test.dto.GetResponse;
import com.test.test.dto.GetResponseBpi;
import com.test.test.dto.GetResponseCurrency;
import com.test.test.dto.GetResponseTime;
import com.test.test.entity.CurrencyEntity;
import com.test.test.enums.State;
import com.test.test.exception.TestException;
import com.test.test.repository.CurrencyEntityRepository;
import com.test.test.service.impl.CurrencyServiceImpl;
import com.test.test.template.ResponseFactory;

@SpringBootTest
public class CurrencyServiceImplTest {

	@InjectMocks
	private CurrencyServiceImpl currencyService;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private CurrencyEntityRepository currencyEntityRepo;

	@Spy
	private ResponseFactory responseFactory;

	/**
	 * build API response
	 * @return
	 */
	private GetResponse createGetResponse() {
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
		return getResponse;
	}

	/**
	 * Service單元測試 測試時間轉換method
	 */
	@Test
	void getTimeTest() {
		String time = "2022-04-15T11:48:00+00:00";
		try {
			// get method
			Method method = currencyService.getClass().getDeclaredMethod("getTime", String.class);
			// 讓java不檢查是否為可調用方法
			method.setAccessible(true);
			assertEquals("2022-04-15 19:48:00", method.invoke(currencyService, time));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * Service單元測試 測試打 coinDesk API 並組成response成功案例
	 */
	@Test
	void callApiTest() {
		GetResponse getResponse = createGetResponse();
		when(restTemplate.getForObject(anyString(), eq(GetResponse.class))).thenReturn(getResponse);

		assertEquals(State.SUCCESS.getCode(), currencyService.callApi().getHeader().getCode());
	}

	/**
	 * Service單元測試 測試打 coinDesk API 並轉成需求格式回傳response成功案例
	 */
	@Test
	void callApiTranSuccessTest() {
		GetResponse getResponse = createGetResponse();

		when(restTemplate.getForObject(anyString(), eq(GetResponse.class))).thenReturn(getResponse);

		CurrencyEntity entity = new CurrencyEntity();
		entity.setCurrency("USD");
		entity.setCurrencyNT("美金");
		when(currencyEntityRepo.findById(anyString())).thenReturn(Optional.of(entity));

		assertEquals(State.SUCCESS.getCode(), currencyService.callApiTran().getHeader().getCode());
	}

	/**
	 * Service單元測試 測試打 coinDesk API 轉換中文時查無資料失敗案例
	 */
	@Test
	void callApiTranFailTest() {
		GetResponse getResponse = createGetResponse();

		when(restTemplate.getForObject(anyString(), eq(GetResponse.class))).thenReturn(getResponse);
		when(currencyEntityRepo.findById(anyString())).thenReturn(Optional.empty());

		try {
			currencyService.callApiTran();
		} catch (TestException te) {
			assertEquals(State.FAIL_001.getCode(), te.getState().getCode());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
}
