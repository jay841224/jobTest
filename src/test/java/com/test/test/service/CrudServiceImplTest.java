package com.test.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.dto.CrudRequest;
import com.test.test.entity.CurrencyEntity;
import com.test.test.enums.State;
import com.test.test.exception.TestException;
import com.test.test.repository.CurrencyEntityRepository;
import com.test.test.service.impl.CrudServiceImpl;
import com.test.test.template.ResponseFactory;

/**
 * Service 單元測試
 * @author jay
 *
 */
@SpringBootTest
class CrudServiceImplTest {

	@InjectMocks
	private CrudServiceImpl serviceImpl;

	@Spy
	private ObjectMapper objectMapper;

	@Spy
	private ResponseFactory responseFactory;

	@Mock
	private CurrencyEntityRepository currencyEntityRepo;

	/**
	 * Service單元測試 測試新增功能成功案例
	 */
	@Test
	void createTest() {
		CrudRequest req = new CrudRequest();
		req.setCurrency("NTD");
		req.setCurrencyNT("台幣");

		Assertions.assertEquals(serviceImpl.create(req).getHeader().getCode(), State.SUCCESS.getCode());
	}

	/**
	 * Service單元測試 測試查詢功能成功案例
	 */
	@Test
	void readSuccessTest() {
		CrudRequest req = new CrudRequest();
		req.setCurrency("USD");

		CurrencyEntity entity = new CurrencyEntity();
		entity.setCurrency("USD");
		entity.setCurrencyNT("美金");

		when(currencyEntityRepo.findById(anyString())).thenReturn(Optional.of(entity));
		assertEquals(State.SUCCESS.getCode(), serviceImpl.read(req).getHeader().getCode());
	}

	/**
	 * Service單元測試 測試查詢功能查無資料案例
	 */
	@Test
	void readFailTest() {
		CrudRequest req = new CrudRequest();
		req.setCurrency("NULL");

		when(currencyEntityRepo.findById(anyString())).thenReturn(Optional.empty());
		try {
			serviceImpl.read(req);
		} catch (TestException te) {
			assertEquals(State.FAIL_001.getCode(), te.getState().getCode());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * Service單元測試 測試異動功能成功案例
	 */
	@Test
	void updateSuccessTest() {
		CrudRequest req = new CrudRequest();
		req.setCurrency("NTD");
		req.setCurrencyNT("新台幣");

		CurrencyEntity entity = new CurrencyEntity();
		entity.setCurrency("NTD");
		entity.setCurrencyNT("台幣");

		when(currencyEntityRepo.findById(anyString())).thenReturn(Optional.of(entity));

		assertEquals(serviceImpl.update(req).getHeader().getCode(), State.SUCCESS.getCode());
	}

	/**
	 * Service單元測試 測試異動功能查無資料異動失敗案例
	 */
	@Test
	void updateFailTest() {
		CrudRequest req = new CrudRequest();
		req.setCurrency("NULL");
		req.setCurrencyNT("新台幣");

		when(currencyEntityRepo.findById(anyString())).thenReturn(Optional.empty());
		try {
			serviceImpl.update(req);
		} catch (TestException te) {
			assertEquals(State.FAIL_002.getCode(), te.getState().getCode());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * Service單元測試 測試刪除功能成功案例
	 */
	@Test
	void deleteTest() {
		CrudRequest req = new CrudRequest();
		req.setCurrency("USD");

		assertEquals(serviceImpl.delete(req).getHeader().getCode(), State.SUCCESS.getCode());
	}
}
