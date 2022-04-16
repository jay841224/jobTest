package com.test.test.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.test.exception.TestException;
import com.test.test.template.ResponseTemplate;

@ControllerAdvice
public class ErrorHandler {

	
	@ExceptionHandler(TestException.class)
	private ResponseTemplate<S> handleTestException() {
		
	}
}
