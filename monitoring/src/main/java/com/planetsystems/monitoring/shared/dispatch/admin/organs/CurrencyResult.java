/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;

/**
 * @author Planet Developer 001
 * 
 */
public class CurrencyResult implements Result {

	private String serverResponse;
	private boolean operationStatus;

	private List<Currency> currencies;
	private CurrencyExchangeRate rate;
	private List<String> currencyStates;
	private List<CurrencyExchangeRate> rates;

	public CurrencyResult(boolean operationStatus, String serverResponse) {

		this.operationStatus=operationStatus;
		this.serverResponse=serverResponse;
		
	}

	public CurrencyResult(boolean operationStatus, List<Currency> currencies) {

		this.operationStatus=operationStatus;
		this.currencies=currencies;
		
	}
	
	public CurrencyResult( CurrencyExchangeRate rate,boolean operationStatus) {

		this.operationStatus=operationStatus;
		this.rate=rate;
		
	}
	
	public CurrencyResult( List<String> currencyStates,boolean operationStatus) {

		this.operationStatus=operationStatus;
		this.currencyStates=currencyStates;
		
	}

	@SuppressWarnings("unused")
	private CurrencyResult(){
		
	}
	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}

	/**
	 * @return the operationStatus
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}

	/**
	 * @return the currencies
	 */
	public List<Currency> getCurrencies() {
		return currencies;
	}

	/**
	 * @return the rates
	 */
	public CurrencyExchangeRate getRate() {
		return rate;
	}

	/**
	 * @return the currencyStates
	 */
	public List<String> getCurrencyStates() {
		return currencyStates;
	}

	public CurrencyResult(List<CurrencyExchangeRate> rates) {
		super();
		this.rates = rates;
		this.operationStatus=true;
	}

	public List<CurrencyExchangeRate> getRates() {
		return rates;
	}
	
	
	
	
}
