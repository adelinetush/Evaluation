package com.planetsystems.monitoring.core.services;

import java.util.List;


import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.budget.Budget;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;


/**
 * @author Planet Dev002
 * 
 */
public interface BudgetService {

	/**
	 * @param activity
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Activity saveActivity(Activity activity) throws ValidationFailedException;

	/**
	 * @param id
	 * @return
	 */
	public Activity getActivityId(String id);

	/**
	 * @return
	 */
	public List<Activity> getAllActivities();

	/**
	 * @param offSet
	 * @param limit
	 * @return
	 */
	public List<Activity> getAllActivities(int offSet, int limit);

	/**
	 * @param department
	 * @return
	 */
	public List<Activity> getActivityByDept(Department department);

	/**
	 * @param activity
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Activity editActivity(Activity activity) throws ValidationFailedException;

	/**
	 * @param activityet
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteActivity(Activity activity)
			throws  ValidationFailedException;
	
	/**
	 * @param code
	 * @return
	 */
	public Activity getActivityByCode(String code);

	/**
	 * @param budget
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Budget saveBudget(Budget budget) throws ValidationFailedException;
			

	/**
	 * @param id
	 * @return
	 */
	public Budget getBudgetById(String id);

	/**
	 * @return
	 */
	public List<Budget> getAllBudgets();

	/**
	 * @param department
	 * @return
	 */
	public List<Budget> getBudgetByDepartment(Department department);

	/**
	 * @param budget
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Budget editBudget(Budget budget) throws ValidationFailedException;

	/**
	 * @param budget
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteBudget(Budget budget) throws ValidationFailedException;

	/**
	 * @param account
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Account saveAccount(Account account)
			throws ValidationFailedException;

	/**
	 * @param id
	 * @return
	 */
	public Account getAccountById(String id);

	/**
	 * @return
	 */
	public List<Account> getAllAccounts();
	
	/**
	 * @param code
	 * @return
	 */
	public Account getAccountByCode(String code);

	/**
	 * @param account
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Account editAccount(Account account)
			throws ValidationFailedException;

	/**
	 * @param account
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteAccount(Account account) throws 
			ValidationFailedException;

	/**
	 * @param offSet
	 * @param limit
	 * @return
	 */
	public List<Account> getAllAccounts(int offSet, int limit);

	/**
	 * @param accountCategory
	 * @return
	 */
	public List<Account> getVoteByVoteCategory(AccountCategory accountCategory);
	
	/**
	 * @param accountCategory
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public AccountCategory saveAccountCategory(AccountCategory accountCategory)
			throws ValidationFailedException;

	/**
	 * @param id
	 * @return
	 */
	public AccountCategory getAccountCategorytById(String id);

	/**
	 * @return
	 */
	public List<AccountCategory> getAllAccountCategories();

	/**
	 * @param accountCategory
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public AccountCategory editAccountCategory(AccountCategory accountCategory)
			throws ValidationFailedException;

	/**
	 * @param code
	 * @return
	 */
	public AccountCategory getAccountCategoryByCode(String code);
	
	/**
	 * @param accountCategory
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteAccountCategory(AccountCategory accountCategory)
			throws  ValidationFailedException;
	
	

	/**
	 * @param param
	 * @return
	 */
	public List<Activity> searchActivities(String param);
	
	/**
	 * @param param
	 * @return
	 */
	public List<Account> searchAccounts(String param);
	
	/**
	 * @param param
	 * @return
	 */
	public List<AccountCategory> searchAccountCategories(String param);
	
	/**
	 * @param account
	 * @param activity
	 * @param department
	 * @param financialYear
	 * @param budgetAmount
	 * @throws ValidationFailedException
	 */
	void saveSunBudget(Account account, Activity activity,
			Department department, long budgetAmount) throws ValidationFailedException;
	
	
}
