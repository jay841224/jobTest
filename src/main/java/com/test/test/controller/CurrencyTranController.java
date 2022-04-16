package com.test.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.service.CrudService;

@RestController
@RequestMapping("/crud")
public class CurrencyTranController {

	@Autowired
	private CrudService crudService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void create(@RequestBody @Validated(CrudRequest.All.class) CrudRequest req) {
		crudService.create(req);
	}

	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public CrudResponse read(@RequestBody @Validated(CrudRequest.Update.class) CrudRequest req) throws Exception {
		return crudService.read(req);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody @Validated(CrudRequest.Update.class) CrudRequest req) throws Exception {
		crudService.update(req);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody @Validated(CrudRequest.Update.class) CrudRequest req) {

	}
}
