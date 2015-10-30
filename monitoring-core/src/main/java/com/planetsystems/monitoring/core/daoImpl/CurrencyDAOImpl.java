package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.CurrencyDAO;
import com.planetsystems.monitoring.model.Currency;


@Repository("CurrencyDAO")
public class CurrencyDAOImpl extends BaseDaoImpl<Currency> implements
		CurrencyDAO {

}
