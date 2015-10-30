package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;


public interface CurrencyService {
	/**
	 * @author
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	void delete(Currency currency) throws ValidationFailedException;

	/**
	 * @param currency
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	Currency editCurrency(Currency currency) throws ValidationFailedException;

	
	
	/**
	 * @return
	 * @throws SessionExpiredException
	 */
	List<Currency> getCurrencies();

	/**
	 * @return
	 */
	Currency getCurrency(String currencyId);

	/**
	 * @param currencyNAme
	 * @return
	 * @throws SessionExpiredException
	 */
	Currency getCurrencyByName(String currencyNAme);

	/**
	 * @param OtherCurrency
	 * @param amount
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	Double getDefaultAmount(Currency OtherCurrency, Double amount)
			throws ValidationFailedException;

	/**
	 * @return
	 * @throws SessionExpiredException
	 */
	public Currency getDefaultCurrency();

	/**
	 * 
	 * @param from
	 * @param to
	 * @param financialYear
	 * @return
	 * @throws SessionExpiredException
	 */
	CurrencyExchangeRate getExchangeRate(Currency from, Currency to);

	/**
	 * @throws SessionExpiredException
	 * @returnl
	 */
	public List<CurrencyExchangeRate> getExchangeRates();

	/**
	 * @return
	 */
	boolean isDefaultCurrencyExisting();

	/**
	 * @param currency
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	Currency saveCurrency(Currency currency) throws ValidationFailedException;

	/**
	 * @param currency
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	CurrencyExchangeRate saveRate(CurrencyExchangeRate currency)
			throws ValidationFailedException;
	
	/**
	 * @param rate
	 * @return
	 * @throws ValidationFailedException
	 */
	public CurrencyExchangeRate editExchangeRate(CurrencyExchangeRate rate)
			throws ValidationFailedException;

	/**
	 * @return
	 */
	public List<String> getCurrecnyStatus();
	
	
	/**
	 * @param rate
	 * @throws ValidationFailedException
	 */
	public void deleteRate(CurrencyExchangeRate rate)
			throws ValidationFailedException;

}