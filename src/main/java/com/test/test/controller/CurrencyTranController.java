package com.test.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.CrudEmptyResponse;
import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.service.CrudService;
import com.test.test.template.ResponseTemplate;

@RestController
@RequestMapping("/crud")
public class CurrencyTranController {

	/** CrudService */
	@Autowired
	private CrudService crudService;

	/**
	 * 新增
	 * 
	 * @param req CrudRequest
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseTemplate<CrudEmptyResponse> create(@RequestBody @Validated(CrudRequest.All.class) CrudRequest req) {
		return crudService.create(req);
	}

	/**
	 * 查詢
	 * 
	 * @param req CrudRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ResponseTemplate<CrudResponse> read(@RequestBody @Validated(CrudRequest.Update.class) CrudRequest req) {
		return crudService.read(req);
	}

	/**
	 * 異動
	 * 
	 * @param req CrudRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseTemplate<CrudEmptyResponse> update(
			@RequestBody @Validated(CrudRequest.Update.class) CrudRequest req) {
		return crudService.update(req);
	}

	/**
	 * 刪除
	 * 
	 * @param req CrudRequest
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseTemplate<CrudEmptyResponse> delete(
			@RequestBody @Validated(CrudRequest.Update.class) CrudRequest req) {
		return crudService.delete(req);
	}
}
