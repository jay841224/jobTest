package com.test.test.advice;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.test.enums.State;
import com.test.test.exception.TestException;
import com.test.test.template.Header;
import com.test.test.template.ResponseTemplate;

/**
 * error handler
 * 
 * @author jay
 *
 */
@ControllerAdvice
public class ErrorHandler {

	/**
	 * TestException
	 * 
	 * @param te
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(TestException.class)
	private ResponseTemplate<Object> handleTestException(TestException te) {
		Header header = new Header(te.getState(), te.getMessage());
		return new ResponseTemplate<Object>(new Object(), header);
	}

	/**
	 * validation exception
	 * 
	 * @param manve
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseTemplate<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
		Header header = new Header(State.FAIL_003, manve.getAllErrors().get(0).getDefaultMessage());
		return new ResponseTemplate<Object>(new Object(), header);
	}

	/**
	 * Exception
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	private ResponseTemplate<Object> handleException(Exception e) {
		Header header = new Header(State.FAIL, e.getMessage());
		return new ResponseTemplate<Object>(new Object(), header);
	}
}
