package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.AccountDAO;
import com.planetsystems.monitoring.model.budget.Account;

@Repository("AccountDAO")
public class AccountDAOImpl extends BaseDaoImpl<Account> implements AccountDAO {

}
