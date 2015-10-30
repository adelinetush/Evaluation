package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.BudgetDAO;
import com.planetsystems.monitoring.model.budget.Budget;


@Repository("BudgetDAO")
public class BudgetDAOImpl extends BaseDaoImpl<Budget> implements BudgetDAO {

}
