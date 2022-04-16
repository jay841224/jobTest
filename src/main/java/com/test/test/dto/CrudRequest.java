package com.test.test.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CrudRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "currency 不得為空", groups = { All.class, Update.class })
	private String currency;

	@NotBlank(message = "currencyNT 不得為空", groups = { All.class })
	private String currencyNT;

	public interface All {

	}

	public interface Update {

	}
}
