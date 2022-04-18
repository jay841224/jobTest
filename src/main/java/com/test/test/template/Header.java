package com.test.test.template;

import com.test.test.enums.State;

import lombok.Data;

/**
 * response Header
 * @author jay
 *
 */
@Data
public class Header {

	private String code;

	private String state;

	private String msg;

	public Header() {
		this.msg = State.SUCCESS.getState();
		this.state = State.SUCCESS.getState();
		this.code = State.SUCCESS.getCode();
	}

	public Header(State state, String msg) {
		this.state = state.getState();
		this.msg = msg;
		this.code = state.getCode();
	}
}
