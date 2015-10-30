package com.planetsystems.monitoring.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Planet Dev002
 *
 */
@Entity
@Table(name = "Currency")
public class Currency extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790641978927753302L;
	private String cur_name;
	private String cur_desc;
	private Status cur_status;
	private String cur_symbol;
	private String currencyStatusId;
	private String currencyStatusName;

	/**
	 * @param cur_name
	 * @param cur_symbol
	 * @param cur_status
	 */
	public Currency(String cur_name, String cur_symbol, Status cur_status) {
		super();
		this.cur_name = cur_name;
		this.cur_symbol = cur_symbol;
		this.cur_status = cur_status;
	}

	/**
	 * @return the cur_name
	 */
	public String getCur_name() {
		return cur_name;
	}

	/**
	 * @return the cur_status
	 */
	@Enumerated(EnumType.ORDINAL)
	public Status getCur_status() {
		return cur_status;
	}

	/**
	 * @return the cur_symbol
	 */
	public String getCur_symbol() {
		return cur_symbol;
	}

	/**
	 * @param cur_name
	 *            the cur_name to set
	 */
	public void setCur_name(String cur_name) {
		this.cur_name = cur_name;
	}

	/**
	 * @param cur_status
	 *            the cur_status to set
	 */
	public void setCur_status(Status cur_status) {
		this.cur_status = cur_status;
	}

	/**
	 * @param cur_symbol
	 *            the cur_symbol to set
	 */
	public void setCur_symbol(String cur_symbol) {
		this.cur_symbol = cur_symbol;
	}

	/**
	 * 
	 */
	public Currency() {
		super();
	}

	/**
	 * @return the cur_desc
	 */
	public String getCur_desc() {
		return cur_desc;
	}

	/**
	 * @param cur_desc the cur_desc to set
	 */
	public void setCur_desc(String cur_desc) {
		this.cur_desc = cur_desc;
	}

	/**
	 * @return the currencyStatusId
	 */
	@Transient
	public String getCurrencyStatusId() {
		return currencyStatusId;
	}
	
	/**
	 * @param currencyStatusId the currencyStatusId to set
	 */
	public void setCurrencyStatusId(String currencyStatusId) {
		this.currencyStatusId = currencyStatusId;
	}

	/**
	 * @return the currencyStatusName
	 */
	@Transient
	public String getCurrencyStatusName() {
		return currencyStatusName;
	}

	/**
	 * @param currencyStatusName the currencyStatusName to set
	 */
	public void setCurrencyStatusName(String currencyStatusName) {
		this.currencyStatusName = currencyStatusName;
	}
	
}
