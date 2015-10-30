/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import com.gwtplatform.mvp.client.UiHandlers;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;


/**
 * @author Planet Developer 001
 *
 */
public interface CurrencyUiHandlers extends UiHandlers{

	void onSaveCurrencyButtonClicked(Currency currency);
	void onCancelCurrencyButtonClicked();
	void onSaveExchangeButtonClicked(CurrencyExchangeRate rate);
	void onCancelExchangeButtonClicked();
	void onEditCurrencyButtonClicked(Currency currency);
	void onEditExchangeButtonClicked(CurrencyExchangeRate rate);
	void onDeleteCurrencyButtonClicked(Currency currency);
	void onDeleteExchangeButtonCliked(CurrencyExchangeRate rate);
	void onLoadCurrencyExchangeButtonClicked();
	void onLoadCurrencyButtonClicked();
	void onLoadCurrencyStates();
	void onLoadFinancialYears();
	
	void onRetrieveCurrencyStates();
	void onGetActiveFinancialYear();
}
