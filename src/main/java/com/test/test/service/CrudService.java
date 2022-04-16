package com.test.test.service;

import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;
import com.test.test.template.ResponseTemplate;

public interface CrudService {
	
	ResponseTemplate<CrudResponse> create(CrudRequest req);
	
	ResponseTemplate<CrudResponse>  read(CrudRequest req) throws Exception;
	
	ResponseTemplate<CrudResponse>  update(CrudRequest req) throws Exception;
	
	ResponseTemplate<CrudResponse>  delete(CrudRequest req);

}
