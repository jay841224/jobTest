package com.test.test.exception;

import com.test.test.enums.State;

import lombok.Getter;

/**
 * 本專案自定義Exception
 * @author jay
 *
 */
@Getter
public class TestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private State state;

	public TestException(State state, String str) {
		super(str);
		this.state = state;
	}

}
