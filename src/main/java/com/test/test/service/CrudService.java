package com.test.test.service;

import com.test.test.dto.CrudRequest;
import com.test.test.dto.CrudResponse;

public interface CrudService {
	
	void create(CrudRequest req);
	
	CrudResponse read(CrudRequest req) throws Exception;
	
	void update(CrudRequest req) throws Exception;
	
	void delete(CrudRequest req);

}
