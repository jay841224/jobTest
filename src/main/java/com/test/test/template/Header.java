package com.test.test.template;

import lombok.Data;

@Data
public class Header {

	private String state;

	private String msg;

	public Header() {
		this.state = "success";
		this.msg = "成功";
	}

	public Header(String state, String msg) {
		this.state = state;
		this.msg = msg;
	}
}
