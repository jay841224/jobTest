package com.test.test.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * coinDesk api response time
 * @author jay
 *
 */
@Data
public class GetResponseTime implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String updated;
	
	private String updatedISO;
	
	private String updateduk;
}
