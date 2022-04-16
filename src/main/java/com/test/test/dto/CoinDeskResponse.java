package com.test.test.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * call coinDesk data response
 * 
 * @author jay
 *
 */
@Data
public class CoinDeskResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String time;

	private List<CoinDeskDetailResponse> detailList;
}
