package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.CurrencyDAO;
import com.planetsystems.monitoring.core.daos.CurrencyExchangeDAO;
import com.planetsystems.monitoring.core.services.AuditlogService;
import com.planetsystems.monitoring.core.services.CurrencyService;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;
import com.planetsystems.monitoring.model.RecordStatus;
import com.planetsystems.monitoring.model.Status;
import com.planetsystems.monitoring.model.audit.ActionStatus;
import com.planetsystems.monitoring.model.audit.Operations;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;

@Service("currencyServiceImpl")
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDAO currencyDao;

	@Autowired
	private CurrencyExchangeDAO currencyExchangeRateDao;



	@Autowired
	private AuditlogService auditlogService;

	

	/**
	 * @author
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Currency currency) throws ValidationFailedException {
		if (isCurrencyDeletable(currency) == false) {
			try {
				throw new ValidationFailedException("Currency is referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Currency");
			}
		}
		try {
			currencyDao.remove(currency);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Currency");
		}
	}

	private boolean isCurrencyDeletable(Currency currency) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterOr(Filter.equal("tocurrency", currency), Filter.equal("fromcurrency", currency));
		if (currencyExchangeRateDao.count(search) > 0)
			return false;
		else
			return true;
	}

	/**
	 * @param currency
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Currency editCurrency(Currency currency) throws ValidationFailedException {
		this.ValidateCurrency(currency);
		if (getDefaultCurrency() != null) {
			if (currency.getId().contentEquals(getDefaultCurrency().getId()) == false) {
				if (isDefaultCurrencyExisting() && isCurrencySetAsDefault(currency)) {
					try {
						throw new ValidationFailedException("Can't have more than one default currency");
					} finally {
						auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.FAIL, "Currency");
					}
				}
			}
		}
		try {
			return currencyDao.save(currency);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Currency");
		}

	}

	private boolean exchangeRateExists(Currency fromCurrency, Currency toCurrency) {
		Search search = new Search();
		search.addFilterEqual("fromcurrency", fromCurrency);
		search.addFilterEqual("tocurrency", toCurrency);

		if (currencyExchangeRateDao.count(search) > 0)
			return true;
		return false;
	}

	private CurrencyExchangeRate exchangeRate(Currency fromCurrency, Currency toCurrency) {
		Search search = new Search();
		search.addFilterEqual("fromcurrency", fromCurrency);
		search.addFilterEqual("tocurrency", toCurrency);
		return currencyExchangeRateDao.searchUnique(search);
	}

	private boolean exchangeRateExistsExclude(String excludeId, Currency fromCurrency, Currency toCurrency) {
		Search search = new Search();
		search.addFilterEqual("fromcurrency", fromCurrency);
		search.addFilterEqual("tocurrency", toCurrency);
		search.addFilterNotEqual("id", excludeId);

		if (currencyExchangeRateDao.count(search) > 0)
			return true;
		return false;
	}


	@Transactional(readOnly = true)
	public List<Currency> getCurrencies() {
		try {
			return currencyDao.findAll();
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Currency");
		}
	}

	@Transactional(readOnly = true)
	public Currency getCurrency(String currencyId) {
		return currencyDao.searchUniqueByPropertyEqual("id", currencyId);

	}

	@Transactional(readOnly = true)
	public Currency getCurrencyByName(String currencyNAme) {
		Search search = new Search();
		search.addFilterEqual("cur_name", currencyNAme);
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		try {
			return currencyDao.searchUnique(search);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Currency");
		}
	}

	/**
	 * @param OtherCurrency
	 * @param amount
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	@Transactional(readOnly = true)
	public Double getDefaultAmount(Currency otherCurrency, Double amount) throws ValidationFailedException {
		Currency defaultCurrency = getDefaultCurrency();
		CurrencyExchangeRate rate = getExchangeRate(otherCurrency, defaultCurrency);
		boolean doesRateExist = exchangeRateExists(otherCurrency, defaultCurrency);

		if (isDefaultCurrencyExisting() && !isCurrencySetAsDefault(otherCurrency)) {
			if (doesRateExist) {
				return rate.getRate() * amount;
			} else if (!doesRateExist)
				throw new ValidationFailedException("The Exchange Rate to " + defaultCurrency.getCur_name() + " is not set");
		}

		else if (!isDefaultCurrencyExisting() && !isCurrencySetAsDefault(otherCurrency))
			throw new ValidationFailedException("Default currency for the system is not set. Please Contact Administrator");

		else if (isDefaultCurrencyExisting() && isCurrencySetAsDefault(otherCurrency))
			return amount;

		return amount;
	}

	/**
	 * @return
	 * @throws SessionExpiredException
	 */
	@Transactional(readOnly = true)
	public Currency getDefaultCurrency() {
		Search search = new Search();
		search.addFilterEqual("cur_status", Status.DEFAULT);
		try {
			return currencyDao.searchUnique(search);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Currency");
		}
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param financialYear
	 * @return
	 * @throws SessionExpiredException
	 */
	@Transactional(readOnly = true)
	public CurrencyExchangeRate getExchangeRate(Currency from, Currency to) {
		Search search = new Search();
		search.addFilterEqual("fromcurrency", from);
		search.addFilterEqual("tocurrency", to);
		try {
			return currencyExchangeRateDao.searchUnique(search);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Exchange Rate");
		}

	}

	/**
	 * @throws SessionExpiredException
	 * @returnl
	 */
	@Transactional(readOnly = true)
	public List<CurrencyExchangeRate> getExchangeRates() {
		try {
			return currencyExchangeRateDao.findAll();
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Currency");
		}
	}

	private boolean isCurrencySetAsDefault(Currency currency) {
		if (currency.getCur_status().equals(Status.DEFAULT))
			return true;
		return false;
	}

	public boolean isDefaultCurrencyExisting() {
		Search search = new Search();
		search.addFilterEqual("cur_status", Status.DEFAULT);
		currencyDao.search(search);
		if (currencyDao.count(search) > 0)
			return true;
		return false;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Currency saveCurrency(Currency currency) throws ValidationFailedException {
		ValidateCurrency(currency);
		if (getCurrencyByName(currency.getCur_name()) != null) {
			try {
				throw new ValidationFailedException("Currency Can't Save: Currency with Name " + currency.getCur_name() + " Already exists");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Currency");
			}
		}
		try {
			return currencyDao.save(currency);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Currency");
		}
	}

	/**
	 * @param currency
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public CurrencyExchangeRate saveRate(CurrencyExchangeRate rate) throws ValidationFailedException {
		CurrencyExchangeRate exRate = new CurrencyExchangeRate();

		if (getDefaultCurrency() != null) {
			exRate.setTocurrency(getDefaultCurrency());
			exRate.setFromcurrency(rate.getFromcurrency());
			exRate.setRate(rate.getRate());
		} else
			throw new ValidationFailedException("Default Currency is not set in the System");

		ValidateRate(exRate);
		if (StringUtils.isBlank(rate.getId())) {
			if (exchangeRateExists(exRate.getFromcurrency(), exRate.getTocurrency())) {
				try {
					throw new ValidationFailedException("exchange rate with same from and to currencies already exists for this financial year ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Exchange Rate");
				}
			}
		} else {
			if (exchangeRateExistsExclude(rate.getId(), rate.getFromcurrency(), exRate.getTocurrency())) {
				try {
					throw new ValidationFailedException("exchange rate with same from and to currencies already exists for this financial year  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Exchange Rate");
				}
			}
		}

		try {
			return currencyExchangeRateDao.save(exRate);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Currency");
		}
	}

	public void ValidateCurrency(Currency currency) throws ValidationFailedException {
		if (currency.getCur_name() == null) {
			try {
				throw new ValidationFailedException("Currency Can't Save: Missing Currency Name");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Currency");
			}
		}
		if (currency.getCur_status() == null)
			throw new ValidationFailedException("Currency Can't Save: Missing Currency Status");

		if (currency.getCur_symbol() == null) {
			try {
				throw new ValidationFailedException("Currency Can't Save: missing currency Symbol");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Currency");
			}
		}

		if (currency.getCur_status() == null) {
			try {
				throw new ValidationFailedException("Currency Can't Save: Currency Status is not set");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Currency");
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CurrencyExchangeRate editExchangeRate(CurrencyExchangeRate rate) throws ValidationFailedException {
		ValidateRate(rate);
		/*if (exchangeRate(rate.getFromcurrency(), rate.getTocurrency(), rate.getFinancialyear()) != null) {
			if (rate.getId().contentEquals(exchangeRate(rate.getFromcurrency(), rate.getTocurrency(), rate.getFinancialyear()).getId()) == false) {
				if (rate.getId() != null) {

					if (exchangeRateExists(rate.getFromcurrency(), rate.getTocurrency(), rate.getFinancialyear())) {
						try {
							throw new ValidationFailedException("exchange rate with same from and to currencies already exists for this financial year " + rate.getFinancialyear().getFinancialYear_desc() + ".");
						} finally {
							auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Exchange Rate");
						}
					}
				}
			}*/
			/*
			 * else { if (exchangeRateExistsExclude(rate.getId(),
			 * rate.getFromcurrency(), rate.getTocurrency(),
			 * rate.getFinancialyear())) { try { throw new
			 * ValidationFailedException(
			 * "exchange rate with same from and to currencies already exists for this financial year  "
			 * + rate.getFinancialyear().getFinancialYear_desc() + "."); }
			 * finally { auditlogService.saveAuditEventTrail(Operations.SAVE,
			 * ActionStatus.FAIL, "Exchange Rate"); } } }
			 */
		//}
		try {
			return currencyExchangeRateDao.save(rate);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Currency");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRate(CurrencyExchangeRate rate) throws ValidationFailedException {
		if (isRateDeletable(rate)) {
			try {
				currencyExchangeRateDao.remove(rate);
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Currency");
			}
		} else
			throw new ValidationFailedException("Rate is already Used by other records");
	}

	public boolean isRateDeletable(CurrencyExchangeRate rate) {
		Search search = new Search();
		search.addFilterOr(new Filter("currency", rate.getFromcurrency()), new Filter("currency", rate.getFromcurrency()), new Filter("currency", rate.getTocurrency()), new Filter("currency", rate.getTocurrency()));
		

		return true;
	}

	private void ValidateRate(CurrencyExchangeRate exchangeRate) throws ValidationFailedException {
		if (exchangeRate.getFromcurrency() == null) {
			try {
				throw new ValidationFailedException("exchange rate missing from currency.");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Exchange Rate");
			}
		}

		if (exchangeRate.getRate() < 0.0) {
			try {
				throw new ValidationFailedException("exchange rate missing a conversion rate.");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Exchange Rate");

			}
		}
	
		if (exchangeRate.getFromcurrency().getId().equals(exchangeRate.getTocurrency().getId())) {
			try {
				throw new ValidationFailedException("exchange rate from & to currencies cannot be the same.");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Exchange Rate");

			}
		}

	}

	@Transactional(readOnly = true)
	public List<String> getCurrecnyStatus() {
		List<String> status = new ArrayList<String>();
		for (Status lin : Status.values()) {
			status.add(lin.name());
		}
		return status;
	}

}