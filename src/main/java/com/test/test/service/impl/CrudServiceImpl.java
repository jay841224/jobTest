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

@Service
public class CrudServiceImpl implements CrudService {

	@Autowired
	private CurrencyEntityRepository currencyEntityRepo;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void create(CrudRequest req) {
		CurrencyEntity entity = objectMapper.convertValue(req, CurrencyEntity.class);
		currencyEntityRepo.save(entity);
	}

	@Override
	public CrudResponse read(CrudRequest req) throws Exception {
		CurrencyEntity entity = currencyEntityRepo.findById(req.getCurrency()).orElseThrow(() -> new Exception());
		return objectMapper.convertValue(entity, CrudResponse.class);
	}

	@Override
	public void update(CrudRequest req) throws Exception {
		CurrencyEntity entity = currencyEntityRepo.findById(req.getCurrency()).orElseThrow(() -> new Exception());
		entity.setCurrencyNT(req.getCurrencyNT());

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(CrudRequest req) {

		currencyEntityRepo.deleteById(req.getCurrency());
	}

}
