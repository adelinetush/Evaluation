package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.AccountCategoryDAO;
import com.planetsystems.monitoring.model.budget.AccountCategory;

@Repository("AccountCategoryDAO")
public class AccountCategoryDAOImpl extends BaseDaoImpl<AccountCategory>
		implements AccountCategoryDAO {

}
