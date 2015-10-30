package com.planetsystems.monitoring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Planet Dev002
 *
 */
@Entity
@Table(name = "CurrencyExchangeRate")
public class CurrencyExchangeRate extends ParentEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6898358007699053331L;

	private String exchangeRatedesc;

	private String financialYearName;
	private Currency fromcurrency;

	private String fromCurrencyName;
	private double Rate;
	private Currency tocurrency;
	private String toCurrencyName;
	private String toCurrencyId;
	private String fromCurrencyId;
	private String financialYearId;
	//private S
	/**
	 * 
	 */
	public CurrencyExchangeRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CurrencyExchangeRate(String financialYearName,
			String fromCurrencyName, double rate, String toCurrencyName) {
		super();
		this.financialYearName = financialYearName;
		this.fromCurrencyName = fromCurrencyName;
		Rate = rate;
		this.toCurrencyName = toCurrencyName;
	}

	public CurrencyExchangeRate(String financialYearId,
			String fromCurrencyId, String toCurrencyId) {
		super();
		this.financialYearId = financialYearId;
		this.fromCurrencyId = fromCurrencyId;
		this.toCurrencyId = toCurrencyId;
	}

	public CurrencyExchangeRate(String financialYearId,
			String fromCurrencyId, String toCurrencyId,String financialYearName,
			String fromCurrencyName,String toCurrencyName) {
		super();
		this.financialYearId = financialYearId;
		this.fromCurrencyId = fromCurrencyId;
		this.toCurrencyId = toCurrencyId;
		this.financialYearName=financialYearName;
		this.fromCurrencyName=fromCurrencyName;
		this.toCurrencyName=toCurrencyName;
	}
	/**
	 * @param fromcurrency
	 * @param tocurrency
	 * @param rate
	 * @param financialyear
	 */
	public CurrencyExchangeRate(Currency fromcurrency, Currency tocurrency,
			double rate) {
		super();
		this.fromcurrency = fromcurrency;
		this.tocurrency = tocurrency;
		Rate = rate;
	}

	
	/**
	 * @return the fromcurrency
	 */
	@ManyToOne
	@JoinColumn(name = "from_cur_fk", nullable = false)
	public Currency getFromcurrency() {
		return fromcurrency;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return Rate;
	}

	/**
	 * @return the tocurrency
	 */
	@ManyToOne
	@JoinColumn(name = "to_cur_fk", nullable = false)
	public Currency getTocurrency() {
		return tocurrency;
	}

	
	/**
	 * @param fromcurrency
	 *            the fromcurrency to set
	 */
	public void setFromcurrency(Currency fromcurrency) {
		this.fromcurrency = fromcurrency;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		Rate = rate;
	}

	/**
	 * @param tocurrency
	 *            the tocurrency to set
	 */
	public void setTocurrency(Currency tocurrency) {
		this.tocurrency = tocurrency;
	}

	@Transient
	public String getFinancialYearName() {
		return financialYearName;
	}


	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}


	@Transient
	public String getFromCurrencyName() {
		return fromCurrencyName;
	}


	public void setFromCurrencyName(String fromCurrencyName) {
		this.fromCurrencyName = fromCurrencyName;
	}


	@Transient
	public String getToCurrencyName() {
		return toCurrencyName;
	}


	public void setToCurrencyName(String toCurrencyName) {
		this.toCurrencyName = toCurrencyName;
	}


	/**
	 * @return the toCurrencyId
	 */
	@Transient
	public String getToCurrencyId() {
		return toCurrencyId;
	}


	/**
	 * @param toCurrencyId the toCurrencyId to set
	 */
	public void setToCurrencyId(String toCurrencyId) {
		this.toCurrencyId = toCurrencyId;
	}


	/**
	 * @return the fromCurrencyId
	 */
	@Transient
	public String getFromCurrencyId() {
		return fromCurrencyId;
	}


	/**
	 * @param fromCurrencyId the fromCurrencyId to set
	 */
	public void setFromCurrencyId(String fromCurrencyId) {
		this.fromCurrencyId = fromCurrencyId;
	}


	/**
	 * @return the financialYearId
	 */
	@Transient
	public String getFinancialYearId() {
		return financialYearId;
	}


	/**
	 * @param financialYearId the financialYearId to set
	 */
	public void setFinancialYearId(String financialYearId) {
		this.financialYearId = financialYearId;
	}


	/**
	 * @return the exchangeRatedesc
	 */
	public String getExchangeRatedesc() {
		return exchangeRatedesc;
	}


	/**
	 * @param exchangeRatedesc the exchangeRatedesc to set
	 */
	public void setExchangeRatedesc(String exchangeRatedesc) {
		this.exchangeRatedesc = exchangeRatedesc;
	}
	
	
	
}
