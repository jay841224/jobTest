package com.test.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.dto.CrudEmptyResponse;
import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.entity.CurrencyEntity;
import com.test.test.enums.State;
import com.test.test.exception.TestException;
import com.test.test.repository.CurrencyEntityRepository;
import com.test.test.service.CrudService;
import com.test.test.template.ResponseFactory;
import com.test.test.template.ResponseTemplate;

/**
 * 查詢、異動、新增、刪除
 * @author jay
 *
 */
@Service
public class CrudServiceImpl implements CrudService {

	/** TB_TRANSLATE JPA repository */
	@Autowired
	private CurrencyEntityRepository currencyEntityRepo;

	/** ObjectMapper */
	@Autowired
	private ObjectMapper objectMapper;

	/** response factory */
	@Autowired
	private ResponseFactory responseFactory;

	/**
	 * 新增
	 */
	@Override
	public ResponseTemplate<CrudEmptyResponse> create(CrudRequest req) {
		CurrencyEntity entity = objectMapper.convertValue(req, CurrencyEntity.class);
		currencyEntityRepo.save(entity);
		return responseFactory.genResponse(new CrudEmptyResponse());
	}

	/**
	 * 查詢
	 */
	@Override
	public ResponseTemplate<CrudResponse> read(CrudRequest req) throws TestException {
		CurrencyEntity entity = currencyEntityRepo.findById(req.getCurrency())
				.orElseThrow(() -> new TestException(State.FAIL_001, "TB_TRANSLATE 查無資料"));
		return responseFactory.genResponse(objectMapper.convertValue(entity, CrudResponse.class));
	}

	/**
	 * 異動
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseTemplate<CrudEmptyResponse> update(CrudRequest req) throws TestException {
		CurrencyEntity entity = currencyEntityRepo.findById(req.getCurrency())
				.orElseThrow(() -> new TestException(State.FAIL_002, "TB_TRANSLATE 查無資料 異動失敗"));
		entity.setCurrencyNT(req.getCurrencyNT());
		currencyEntityRepo.save(entity);
		return responseFactory.genResponse(new CrudEmptyResponse());
	}

	/**
	 * 刪除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseTemplate<CrudEmptyResponse> delete(CrudRequest req) {
		currencyEntityRepo.deleteById(req.getCurrency());
		return responseFactory.genResponse(new CrudEmptyResponse());
	}

}
