package com.test.test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.test.dto.CrudRequest;
import com.test.test.exception.TestException;
import com.test.test.service.impl.CrudServiceImpl;
import com.test.test.service.impl.CurrencyServiceImpl;

/**
 * 把所有API跑過一次
 * @author jay
 *
 */
@SpringBootTest
public class runAllApi {
	
	@Autowired
	private CurrencyServiceImpl currencyServiceImpl;
	
	@Autowired CrudServiceImpl crudServiceImpl;

	private void printObject(Object object, String title) {
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    System.out.println(title);
	    System.out.println(gson.toJson(object));
	    System.out.println("=============================");
	}

	@Test
	void run() {
		printObject(currencyServiceImpl.callApi(), "打CoinDesk:");
		printObject(currencyServiceImpl.callApiTran(), "打CoinDesk並整理:");
		CrudRequest createReq = new CrudRequest();
		createReq.setCurrency("NTD");
		createReq.setCurrencyNT("台幣");
		printObject(crudServiceImpl.create(createReq), "新增幣別:");
		printObject(crudServiceImpl.read(createReq), "新增後查詢幣別:");
		CrudRequest readReq = new CrudRequest();
		readReq.setCurrency("NTD");
		printObject(crudServiceImpl.read(readReq), "查詢幣別:");
		CrudRequest updateReq = new CrudRequest();
		updateReq.setCurrency("NTD");
		updateReq.setCurrencyNT("新台幣");
		printObject(crudServiceImpl.update(updateReq), "異動幣別:");
		printObject(crudServiceImpl.read(updateReq), "異動後查詢幣別:");
		CrudRequest deleteReq = new CrudRequest();
		deleteReq.setCurrency("NTD");
		printObject(crudServiceImpl.delete(deleteReq), "刪除幣別:");
		try {
			printObject(crudServiceImpl.read(deleteReq), "刪除後查詢幣別:");			
		} catch (TestException te) {
			printObject(te.getMessage(), "刪除後查詢幣別:");			
			
		}
	}
}
