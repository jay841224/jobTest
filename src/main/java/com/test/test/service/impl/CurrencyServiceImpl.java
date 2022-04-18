package com.test.test.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.test.dto.CoinDeskDetailResponse;
import com.test.test.dto.CoinDeskResponse;
import com.test.test.dto.GetResponse;
import com.test.test.dto.GetResponseCurrency;
import com.test.test.entity.CurrencyEntity;
import com.test.test.enums.State;
import com.test.test.exception.TestException;
import com.test.test.repository.CurrencyEntityRepository;
import com.test.test.service.ApiService;
import com.test.test.template.ResponseFactory;
import com.test.test.template.ResponseTemplate;

/**
 * CoinDesk API
 * 
 * @author jay
 *
 */
@Service
public class CurrencyServiceImpl implements ApiService {

	/** RestTemplate */
	@Autowired
	private RestTemplate restTemplate;

	/** TB_TRANSLATE JPA repository */
	@Autowired
	private CurrencyEntityRepository currencyEntityRepo;

	/** response factory */
	@Autowired
	private ResponseFactory responseFactory;

	/** coinDesk API URL */
	private static final String URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	/** String to DateTime */
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

	/** DateTime to String */
	private static final DateTimeFormatter time2String = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * call coinDesk API
	 */
	@Override
	public ResponseTemplate<GetResponse> callApi() {
		return responseFactory.genResponse(api(GetResponse.class, URL));
	}

	/**
	 * call coinDesk API and build response
	 */
	@Override
	public ResponseTemplate<CoinDeskResponse> callApiTran() {
		GetResponse getResponse = api(GetResponse.class, URL);
		List<CoinDeskDetailResponse> detailList = new ArrayList<>();

		for (GetResponseCurrency responseCurrency : getResponse.getBpi().getResponseCurrencyList()) {
			CurrencyEntity entity = currencyEntityRepo.findById(responseCurrency.getCode())
					.orElseThrow(() -> new TestException(State.FAIL_001, "TB_TRANSLATE 查無資料"));
			CoinDeskDetailResponse coinDeskDetailResponse = new CoinDeskDetailResponse();
			coinDeskDetailResponse.setCode(responseCurrency.getCode());
			coinDeskDetailResponse.setCodeNT(entity.getCurrencyNT());
			coinDeskDetailResponse.setRate(responseCurrency.getRate());
			detailList.add(coinDeskDetailResponse);
		}

		// 組成response
		CoinDeskResponse response = new CoinDeskResponse();
		response.setDetailList(detailList);
		response.setTime(getTime(getResponse.getTime().getUpdatedISO()));
		return responseFactory.genResponse(response);
	}

	/**
	 * call API
	 * 
	 * @param <S>
	 * @param dtoClass
	 * @return
	 * @return
	 */
	private <S> S api(Class<S> dtoClass, String url) {
		return (S) restTemplate.getForObject(url, dtoClass);
	}

	/**
	 * 取得台灣時間
	 * 
	 * @param time
	 * @return
	 */
	private String getTime(String time) {
		// string to localDateTime
		LocalDateTime ldt = LocalDateTime.parse(time, dtf);
		// UTC+0
		ZonedDateTime golbalZonedDateTime = ZonedDateTime.of(ldt, ZoneId.of("UTC"));
		// convert to TW time UTC+8
		ZonedDateTime localZonedDateTime = golbalZonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Taipei"));

		return time2String.format(localZonedDateTime);
	}

}
