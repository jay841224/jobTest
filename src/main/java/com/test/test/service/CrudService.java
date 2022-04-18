package com.test.test.service;

import com.test.test.dto.CrudEmptyResponse;
import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.exception.TestException;
import com.test.test.template.ResponseTemplate;

/**
 * CRUD
 * @author jay
 *
 */
public interface CrudService {
	
	/** 新增 */
	ResponseTemplate<CrudEmptyResponse> create(CrudRequest req);
	
	/** 查詢 */
	ResponseTemplate<CrudResponse>  read(CrudRequest req) throws TestException;
	
	/** 異動 */
	ResponseTemplate<CrudEmptyResponse>  update(CrudRequest req) throws TestException;
	
	/** 刪除 */
	ResponseTemplate<CrudEmptyResponse>  delete(CrudRequest req);

}
