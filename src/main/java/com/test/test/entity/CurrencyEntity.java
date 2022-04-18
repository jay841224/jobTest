package com.test.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * table TB_TRANSLATE
 * @author jay
 *
 */
@Entity
@Table(name = "TB_TRANSLATE")
@Data
public class CurrencyEntity {

	@Id
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "CURRENCYNT")
	private String currencyNT;
	
}
