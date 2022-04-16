package com.test.test.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * coinDesk api response
 * @author jay
 *
 */
@Data
public class GetResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private GetResponseTime time;
	
	private String disclaimer;
	
	private String chartName;
	
	private GetResponseBpi bpi;
	
}
