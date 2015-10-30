package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.CurrencyExchangeDAO;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;

@Repository("CurrencyExchangeDAO")
public class CurrencyExchangeDAOImpl extends BaseDaoImpl<CurrencyExchangeRate>implements CurrencyExchangeDAO {

}
