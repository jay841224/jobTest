package com.test.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.test.advice.ErrorHandler;
import com.test.test.dto.CrudEmptyResponse;
import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.enums.State;
import com.test.test.service.CrudService;
import com.test.test.template.ResponseFactory;

@SuppressWarnings("unused")
@SpringBootTest
public class CurrencyTranControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private CurrencyTranController currencyTranController;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CrudService crudService;

	@Autowired
	private ResponseFactory responseFactory;

	private CrudRequest fullReq = new CrudRequest();

	private CrudRequest updateReq = new CrudRequest();

	private CrudRequest badReq = new CrudRequest();

	@BeforeEach
	public void setpup() {
		// 設定自定義objectMapper
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter
				.setObjectMapper(new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS));
		mockMvc = MockMvcBuilders.standaloneSetup(this.currencyTranController)
				.setMessageConverters(mappingJackson2HttpMessageConverter).setControllerAdvice(new ErrorHandler())
				.build();

		fullReq.setCurrency("NTD");
		fullReq.setCurrencyNT("台幣");

		updateReq.setCurrency("NTD");
	}

	/**
	 * controller單元測試 新增成功案例
	 */
	@Test
	void createTest() {
		when(crudService.create(isA(CrudRequest.class)))
				.thenReturn(responseFactory.genResponse(new CrudEmptyResponse()));

		try {
			// 模擬post，定義request為JSON格式
			ResultActions resultActions = mockMvc
					.perform(post("/crud/create").content(objectMapper.writeValueAsString(fullReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.SUCCESS.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 查詢測試成功案例
	 */
	@Test
	void readTest() {
		CrudResponse crudResponse = new CrudResponse();
		crudResponse.setCurrency("");
		crudResponse.setCurrencyNT(null);
		when(crudService.read(isA(CrudRequest.class))).thenReturn(responseFactory.genResponse(new CrudResponse()));

		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/read").content(objectMapper.writeValueAsString(updateReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.SUCCESS.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 異動測試成功案例
	 */
	@Test
	void updateTest() {
		when(crudService.update(isA(CrudRequest.class)))
				.thenReturn(responseFactory.genResponse(new CrudEmptyResponse()));

		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/update").content(objectMapper.writeValueAsString(updateReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.SUCCESS.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 刪除測試成功案例
	 */
	@Test
	void deleteTest() {
		when(crudService.delete(isA(CrudRequest.class)))
				.thenReturn(responseFactory.genResponse(new CrudEmptyResponse()));

		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/delete").content(objectMapper.writeValueAsString(updateReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.SUCCESS.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 新增request未符合規範
	 */
	@Test
	void createFailTest() {
		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/create").content(objectMapper.writeValueAsString(badReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.FAIL_003.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 查詢request未符合規範
	 */
	@Test
	void readFailTest() {
		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/read").content(objectMapper.writeValueAsString(badReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.FAIL_003.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 異動request未符合規範
	 */
	@Test
	void updateFailTest() {
		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/update").content(objectMapper.writeValueAsString(badReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.FAIL_003.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

	/**
	 * controller單元測試 刪除request未符合規範
	 */
	@Test
	void deleteFailTest() {
		try {
			ResultActions resultActions = mockMvc
					.perform(post("/crud/delete").content(objectMapper.writeValueAsString(badReq))
							.contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.header.code", is(State.FAIL_003.getCode())));
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}
}
