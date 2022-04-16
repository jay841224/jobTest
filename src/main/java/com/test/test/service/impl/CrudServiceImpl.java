package com.test.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.entity.CurrencyEntity;
import com.test.test.repository.CurrencyEntityRepository;
import com.test.test.service.CrudService;
import com.test.test.template.ResponseFactory;
import com.test.test.template.ResponseTemplate;

@Service
public class CrudServiceImpl implements CrudService {

	@Autowired
	private CurrencyEntityRepository currencyEntityRepo;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ResponseFactory responseFactory;

	@Override
	public ResponseTemplate<CrudResponse> create(CrudRequest req) {
		CurrencyEntity entity = objectMapper.convertValue(req, CurrencyEntity.class);
		currencyEntityRepo.save(entity);
		return responseFactory.genResponse(null);
	}

	@Override
	public ResponseTemplate<CrudResponse> read(CrudRequest req) throws Exception {
		CurrencyEntity entity = currencyEntityRepo.findById(req.getCurrency()).orElseThrow(() -> new Exception());
		return responseFactory.genResponse(objectMapper.convertValue(entity, CrudResponse.class));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseTemplate<CrudResponse> update(CrudRequest req) throws Exception {
		CurrencyEntity entity = currencyEntityRepo.findById(req.getCurrency()).orElseThrow(() -> new Exception());
		entity.setCurrencyNT(req.getCurrencyNT());
		currencyEntityRepo.save(entity);
		return responseFactory.genResponse(null);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseTemplate<CrudResponse> delete(CrudRequest req) {

		currencyEntityRepo.deleteById(req.getCurrency());
		return responseFactory.genResponse(null);
	}

}
