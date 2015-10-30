/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import java.util.List;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;

/**
 * @author Planet Developer 001
 * 
 */
public class CurrencyAction extends UnsecuredActionImpl<CurrencyResult> {

	private String operation;
	private Currency currency;
	private CurrencyExchangeRate exchange;
	private List<CurrencyExchangeRate> rates;
	private String operationLevel;

	public CurrencyAction(String operation,String operationLevel, Currency currency) {

		this.operation = operation;
		this.currency = currency;
		this.operationLevel=operationLevel;
		

	}

	public CurrencyAction(String operation,String operationLevel, CurrencyExchangeRate exchange) {

		this.operation = operation;
		this.exchange=exchange;
		this.operationLevel=operationLevel;
		
	}
	
	public CurrencyAction(String operation,String operationLevel) {

		this.operation = operation;
		this.operationLevel=operationLevel;
		
	}
	
	@SuppressWarnings("unused")
	private CurrencyAction(){
		
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @return the exchange
	 */
	public CurrencyExchangeRate getExchange() {
		return exchange;
	}

	/**
	 * @return the operationLevel
	 */
	public String getOperationLevel() {
		return operationLevel;
	}

	public List<CurrencyExchangeRate> getRates() {
		return rates;
	}
	
	
}
