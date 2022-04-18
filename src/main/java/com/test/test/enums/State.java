package com.test.test.enums;

import lombok.Getter;

/**
 * 交易狀態
 * @author jay
 *
 */
@Getter
public enum State {
	/** 交易成功 */
	SUCCESS("交易成功", "000"),
	/** 交易失敗 */
	FAIL("交易失敗", "999"),
	/** 查無資料 */
	FAIL_001("查無資料", "001"),
	/** 異動失敗 */
	FAIL_002("異動失敗", "002"),
	/** 檢核失敗 */
	FAIL_003("檢核失敗", "003");

	private State(String state, String code) {
		this.state = state;
		this.code = code;
	}

	private final String state;

	private final String code;
}
