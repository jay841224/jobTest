package com.test.test.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.test.exception.TestException;
import com.test.test.template.Header;
import com.test.test.template.ResponseTemplate;

@ControllerAdvice
public class ErrorHandler {

	@ResponseBody
	@ExceptionHandler(TestException.class)
	private ResponseTemplate<Object> handleTestException(TestException te) {
		Header header = new Header("fail", te.getMessage());
		return new ResponseTemplate<Object>(new Object(), header);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	private ResponseTemplate<Object> handleException(Exception e) {
		Header header = new Header("fail", e.getMessage());
		return new ResponseTemplate<Object>(new Object(), header);
	}
}
