/**
 * 
 */
package com.planetsystems.monitoring.server.dispatcher.administration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.core.services.CurrencyService;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;
import com.planetsystems.monitoring.model.ProcnetErrorCodes;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.CurrencyAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.CurrencyResult;

/**
 * @author Planet Developer 001
 * 
 */
public class CurrencyHandler extends AbstractActionHandler<CurrencyAction, CurrencyResult> {

	@Autowired
	private CurrencyService currencyService;


	public CurrencyHandler() {
		super(CurrencyAction.class);

	}

	/**
	 * @param actionType
	 */

	@Override
	public Class<CurrencyAction> getActionType() {
		return CurrencyAction.class;
	}

	public CurrencyResult execute(CurrencyAction action, ExecutionContext context) throws ActionException {

		CurrencyResult result = null;

		System.out.println("CurrencyResult execute ");

		if (action.getOperationLevel().contentEquals(NameTokens.currencies) && action.getOperation().contentEquals(NameTokens.saveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = saveCurrency(action.getCurrency());

		} else if (action.getOperationLevel().contentEquals(NameTokens.currencies) && action.getOperation().contentEquals(NameTokens.editOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = editCurrency(action.getCurrency());

		} else if (action.getOperationLevel().contentEquals(NameTokens.currencies) && action.getOperation().contentEquals(NameTokens.deleteOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = deleteCurrency(action.getCurrency());

		} else if (action.getOperationLevel().contentEquals(NameTokens.currencies) && action.getOperation().contentEquals(NameTokens.retrieveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());
			result = retrieveAllCurrency();
		} else if (action.getOperationLevel().contentEquals(NameTokens.currencies) && action.getOperation().contentEquals(NameTokens.retrieveCurrencyStates)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());
			result = retrieveCurrencyStates();
		} else if (action.getOperationLevel().contentEquals(NameTokens.currencyExchangeRates) && action.getOperation().contentEquals(NameTokens.saveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = saveExchangeRate(action.getExchange());

		} else if (action.getOperationLevel().contentEquals(NameTokens.currencyExchangeRates) && action.getOperation().contentEquals(NameTokens.editOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = editExchangeRate(action.getExchange());
		} else if (action.getOperationLevel().contentEquals(NameTokens.currencyExchangeRates) && action.getOperation().contentEquals(NameTokens.deleteOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());
			
			result = deleteExchangeRate(action.getExchange());
			
		} else if (action.getOperationLevel().contentEquals(NameTokens.currencyExchangeRates) && action.getOperation().contentEquals(NameTokens.retrieveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = retrieveExchangeRates(action.getExchange());

		} else {
			System.out.println("action.getOperationLevel()"+action.getOperationLevel());
			System.out.println("action.getOperation()"+action.getOperation());
			System.out.println("No Match");
			result = new CurrencyResult(true, "No match");
		}

		return result;

	}

	public void undo(CurrencyAction action, CurrencyResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

	private CurrencyResult saveCurrency(Currency currencyTo) {

		CurrencyResult result = null;

		try {

			Currency currency = new Currency();

			currency.setCur_name(currencyTo.getCur_name());
			currency.setCur_symbol(currencyTo.getCur_symbol());
			currency.setCur_status(currencyTo.getCur_status());

			currencyService.saveCurrency(currency);

			result = new CurrencyResult(true, "Successfully Saved Currency");

			// System.out.println();
		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private CurrencyResult editCurrency(Currency currencyTo) {

		CurrencyResult result = null;

		try {

			Currency currency = new Currency();
			currency = currencyService.getCurrency(currencyTo.getId());

			currency.setCur_name(currencyTo.getCur_name());
			currency.setCur_symbol(currencyTo.getCur_symbol());
			currency.setCur_status(currencyTo.getCur_status());

			currencyService.editCurrency(currency);

			result = new CurrencyResult(true, "Successfully Edited Currency");

			// System.out.println();
		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private CurrencyResult deleteCurrency(Currency currencyTo) {

		CurrencyResult result = null;

		try {

			Currency currency = new Currency();
			currency = currencyService.getCurrency(currencyTo.getId());

			currency.setCur_name(currencyTo.getCur_name());
			currency.setCur_symbol(currencyTo.getCur_symbol());
			currency.setCur_status(currencyTo.getCur_status());

			// currencyService.saveCurrency(currency);
			currencyService.delete(currency);

			result = new CurrencyResult(true, "Successfully Deleted Currency");

			// System.out.println();
		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private CurrencyResult retrieveAllCurrency() {

		CurrencyResult result = null;

		try {

			List<Currency> currencies = new ArrayList<Currency>(currencyService.getCurrencies().size());
			List<Currency> currencyDTOs = new ArrayList<Currency>();

			currencies = currencyService.getCurrencies();

			System.out.println("Number of Currencies= " + currencyService.getCurrencies());

			for (Currency currency : currencies) {
				Currency currencyDTO = new Currency();
				currencyDTO.setCur_name(currency.getCur_name());
				currencyDTO.setCurrencyStatusId(currency.getCurrencyStatusId());
				currencyDTO.setCurrencyStatusName(currency.getCur_status().toString());
				currencyDTO.setCur_symbol(currency.getCur_symbol());
				currencyDTO.setId(currency.getId());

				User userCreated = new User();

				if (currency.getCreatedBy() != null) {

					userCreated.setId(currency.getCreatedBy().getId());
					userCreated.setfName(currency.getCreatedBy().getfName());
					userCreated.setlName(currency.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (currency.getChangedBy() != null) {

					userChanged.setId(currency.getChangedBy().getId());
					userChanged.setfName(currency.getChangedBy().getfName());
					userChanged.setlName(currency.getChangedBy().getlName());

				} else {

				}

				currencyDTO.setChangedBy(userChanged);
				currencyDTO.setCreatedBy(userCreated);

				currencyDTOs.add(currencyDTO);

			}

			result = new CurrencyResult(true, currencyDTOs);

			// System.out.println();
		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private CurrencyResult retrieveCurrencyStates() {

		CurrencyResult result = null;

		try {

			List<String> statuses = new ArrayList<String>();
			List<String> statusDTOs = new ArrayList<String>();

			statuses = currencyService.getCurrecnyStatus();

			result = new CurrencyResult(statuses, true);

			// System.out.println();
		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private CurrencyResult saveExchangeRate(CurrencyExchangeRate rate) {

		CurrencyResult result = null;

		try {

			CurrencyExchangeRate currencyExchange = new CurrencyExchangeRate();
		//	FinancialYear financialYear = new FinancialYear();
			Currency currencyTo = new Currency();
			Currency currencyFrom = new Currency();

		//	financialYear = financialYearService.getFinancialYearById(rate.getFinancialyear().getId());
			/*
			 * currencyTo = currencyService.getCurrency(rate.getTocurrency()
			 * .getId());
			 */
			currencyFrom = currencyService.getCurrency(rate.getFromcurrency().getId());
		//	currencyExchange.setFinancialyear(financialYear);
			currencyExchange.setTocurrency(currencyTo);
			currencyExchange.setFromcurrency(currencyFrom);
			currencyExchange.setRate(rate.getRate());

			currencyService.saveRate(currencyExchange);

			result = new CurrencyResult(true, "Successfully Saved Currency Exchange Rate");

		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
		return result;
	}

	private CurrencyResult deleteExchangeRate(CurrencyExchangeRate rate) {

		CurrencyResult result = null;

		try {

			CurrencyExchangeRate currencyExchange = new CurrencyExchangeRate();
			// Currency currencyTo = new Currency();
			Currency currencyFrom = new Currency();

			//financialYear = financialYearService.getFinancialYearById(rate.getFinancialyear().getId());
			/*
			 * currencyTo = currencyService.getCurrency(rate.getTocurrency()
			 * .getId());
			 */
			currencyFrom = currencyService.getCurrency(rate.getFromcurrency().getId());
		//	currencyExchange.setFinancialyear(financialYear);
			// currencyExchange.setTocurrency(currencyTo);
			currencyExchange.setFromcurrency(currencyFrom);
			currencyExchange.setRate(rate.getRate());
			currencyExchange.setId(rate.getId());

			currencyService.deleteRate(currencyExchange);

			result = new CurrencyResult(true, "Successfully Deleted CurrencyExchange rate");

			// System.out.println();
		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private CurrencyResult editExchangeRate(CurrencyExchangeRate rate) {

		CurrencyResult result = null;

		try {
			CurrencyExchangeRate currencyExchange = new CurrencyExchangeRate();
		//	FinancialYear financialYear = new FinancialYear();
			Currency currencyTo = new Currency();
			Currency currencyFrom = new Currency();

		//	financialYear = financialYearService.getFinancialYearById(rate.getFinancialyear().getId());
			currencyTo = currencyService.getCurrency(rate.getTocurrency().getId());
			currencyFrom = currencyService.getCurrency(rate.getFromcurrency().getId());
//			
//			System.out.println("financialYear.getId():==>" + financialYear.getId() + " : " );
//			currencyExchange.setFinancialyear(financialYear);
			currencyExchange.setTocurrency(currencyTo);
			currencyExchange.setFromcurrency(currencyFrom);
			currencyExchange.setRate(rate.getRate());
			currencyExchange.setId(rate.getId());

			currencyService.editExchangeRate(currencyExchange);

			result = new CurrencyResult(true, "Successfully Edited Currency Exchange Rate");

		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
		return result;
	}

	private CurrencyResult retrieveExchangeRates(CurrencyExchangeRate rate) {

		CurrencyResult result = null;

		try {

			List<CurrencyExchangeRate> rates = new ArrayList<CurrencyExchangeRate>();
			List<CurrencyExchangeRate> ratesDTO = new ArrayList<CurrencyExchangeRate>();

			CurrencyExchangeRate currencyExchangeRateDTO;

			rates = currencyService.getExchangeRates();
			for (CurrencyExchangeRate exrate : rates) {
				System.out.println("From " + exrate.getFromcurrency().getCur_name() + " to " + exrate.getTocurrency().getCur_name() + " rate  " + exrate.getRate());
			}

			for (CurrencyExchangeRate currencyExchangeRate : rates) {
				currencyExchangeRateDTO = new CurrencyExchangeRate();
				currencyExchangeRateDTO.setId(currencyExchangeRate.getId());
//				currencyExchangeRateDTO.setFinancialYearId(currencyExchangeRate.getFinancialyear().getId());
//				currencyExchangeRateDTO.setFinancialYearName(currencyExchangeRate.getFinancialyear().getFinancialYear_desc());
				currencyExchangeRateDTO.setFromCurrencyId(currencyExchangeRate.getFromcurrency().getId());
				currencyExchangeRateDTO.setFromCurrencyName(currencyExchangeRate.getFromcurrency().getCur_name());
				currencyExchangeRateDTO.setToCurrencyId(currencyExchangeRate.getTocurrency().getId());
				currencyExchangeRateDTO.setToCurrencyName(currencyExchangeRate.getTocurrency().getCur_name());
				currencyExchangeRateDTO.setRate(currencyExchangeRate.getRate());

				User userCreated = new User();

				if (currencyExchangeRate.getCreatedBy() != null) {

					userCreated.setId(currencyExchangeRate.getCreatedBy().getId());
					userCreated.setfName(currencyExchangeRate.getCreatedBy().getfName());
					userCreated.setlName(currencyExchangeRate.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (currencyExchangeRate.getChangedBy() != null) {

					userChanged.setId(currencyExchangeRate.getChangedBy().getId());
					userChanged.setfName(currencyExchangeRate.getChangedBy().getfName());
					userChanged.setlName(currencyExchangeRate.getChangedBy().getlName());

				} else {

				}

				currencyExchangeRateDTO.setChangedBy(userChanged);
				currencyExchangeRateDTO.setCreatedBy(userCreated);

				ratesDTO.add(currencyExchangeRateDTO);

			}

			result = new CurrencyResult(ratesDTO);

		} catch (NullPointerException e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {

			e.printStackTrace();
			result = new CurrencyResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
		return result;
	}
}
