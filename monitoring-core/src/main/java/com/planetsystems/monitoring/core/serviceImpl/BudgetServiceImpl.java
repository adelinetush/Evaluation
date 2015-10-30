package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.AccountCategoryDAO;
import com.planetsystems.monitoring.core.daos.AccountDAO;
import com.planetsystems.monitoring.core.daos.ActivityDAO;
import com.planetsystems.monitoring.core.daos.BudgetDAO;
import com.planetsystems.monitoring.core.security.util.ProcnetSecurityUtil;
import com.planetsystems.monitoring.core.services.AuditlogService;
import com.planetsystems.monitoring.core.services.BudgetService;
import com.planetsystems.monitoring.core.services.CurrencyService;
import com.planetsystems.monitoring.model.RecordStatus;
import com.planetsystems.monitoring.model.audit.ActionStatus;
import com.planetsystems.monitoring.model.audit.Operations;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.budget.Budget;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;

@Service("BudgetService")
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private BudgetDAO budgetDAO;

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private AccountCategoryDAO accountCategoryDAO;

	@Autowired
	private AuditlogService auditlogService;

	

	@Autowired
	private CurrencyService currencyService;

	@Transactional(propagation = Propagation.REQUIRED)
	public Activity saveActivity(Activity activity) throws ValidationFailedException {
		validateActivity(activity);
		checkifActivityExists(activity);
		try {
			return activityDAO.save(activity);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Activity");
		}
	}

	public void validateActivity(Activity activity) throws ValidationFailedException {
		if (activity.getActivityCode() == null) {
			try {
				throw new ValidationFailedException("Activity Code is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Activity");
			}
		}
		if (activity.getActivityName() == null) {
			try {
				throw new ValidationFailedException("Activity Name is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Activity");
			}
		}
		if (activity.getActivityDesc() == null) {
			try {
				throw new ValidationFailedException("Activity Description is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Activity");
			}
		}

	}

	public void checkifActivityExists(Activity activity) throws ValidationFailedException {
		for (Activity activ : getAllActivities()) {
			if (activ.getActivityName().contentEquals(activity.getActivityName())) {
				try {
					throw new ValidationFailedException("Activity with the name " + activity.getActivityName() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Activity");
				}
			}

			if (activ.getActivityCode().contentEquals(activity.getActivityCode())) {
				try {
					throw new ValidationFailedException("Activity with the code " + activity.getActivityCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Activity");
				}
			}

			if (activ.getActivityDesc().contentEquals(activity.getActivityDesc())) {
				try {
					throw new ValidationFailedException("Activity with the Description " + activity.getActivityDesc() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Activity");
				}
			}

		}
	}

	@Transactional(readOnly = true)
	public List<Activity> searchActivities(String param) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterOr(new Filter[] { new Filter("activityCode", param, Filter.OP_LIKE), new Filter("activityName", param, Filter.OP_LIKE), new Filter("activityDesc", param, Filter.OP_LIKE), new Filter("department.departmentName", param, Filter.OP_LIKE),
				new Filter("department.departmentCode", param, Filter.OP_LIKE), new Filter("department.departmentDescription", param, Filter.OP_LIKE) });
		return activityDAO.search(search);
	}

	@Transactional(readOnly = true)
	public Activity getActivityId(String id) {
		return activityDAO.searchUniqueByPropertyEqual("id", id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Activity> getAllActivities() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return activityDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Activity getActivityByCode(String code) {
		Search search = new Search();
		search.addFilterEqual("activityCode", code);
		return activityDAO.searchUnique(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Activity> getAllActivities(int offSet, int limit) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.setFirstResult(offSet);
		search.setMaxResults(limit);
		return activityDAO.search(search);
	}

	


	@Transactional(propagation = Propagation.REQUIRED)
	public Activity editActivity(Activity activity) throws ValidationFailedException {
		validateActivity(activity);
		if (getActivityByCode(activity.getActivityCode()) != null) {
			if (activity.getId().contentEquals(getActivityByCode(activity.getActivityCode()).getId()) == false) {
				if (activity.getActivityCode().contentEquals(getActivityByCode(activity.getActivityCode()).getActivityCode()) == true)
					throw new ValidationFailedException("Activity with the Code " + activity.getActivityCode() + " already exists");
			}
		}

		try {
			return activityDAO.save(activity);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Activity");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteActivity(Activity activity) throws ValidationFailedException {
		if (isActivityDeletable(activity) == false) {
			try {
				throw new ValidationFailedException("Activity is already referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Activity");
			}
		}

		try {
			activityDAO.remove(activity);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Activity");
		}
	}

	public boolean isActivityDeletable(Activity activity) {
		return true;
	}

	@Transactional(readOnly = true)
	public List<Activity> getActivityByDept(Department department) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("department", department);
		return activityDAO.search(search);
	}

	@Transactional(readOnly = true)
	public List<Activity> getActivityByLoggedInUserDept() throws SessionExpiredException {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("department", ProcnetSecurityUtil.getLoggedInUser());
		return activityDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Budget saveBudget(Budget budget) throws ValidationFailedException {
		try {
			return budgetDAO.save(budget);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Budget");
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveSunBudget(Account account, Activity activity, Department department,  long budgetAmount) throws ValidationFailedException {
		if (getBudgetByAccountActivityDept(account, activity, department) == null) {

			if (currencyService.getDefaultCurrency() == null)
				throw new ValidationFailedException("Default Currency is not set");
			Budget budget = new Budget();
			budget.setBudgetAmount(budgetAmount);
			budget.setAccount(account);
			budget.setActivityName(activity.getActivityName());
			budget.setAccountCode(account.getAccountCode());
			budget.setActivity(activity);
			budget.setAccountCode(activity.getActivityCode());
			budget.setDepartment(department);
			budget.setCurrency(currencyService.getDefaultCurrency());
			budgetDAO.save(budget);
		}

		else if (getBudgetByAccountActivityDept(account, activity, department) != null) {
			Budget budget = getBudgetByAccountActivityDept(account, activity, department);
			budget.setBudgetAmount(budgetAmount);
			budgetDAO.save(budget);
		}
	}

	

	@Transactional(readOnly = true)
	public Budget getBudgetByAccountActivityDept( Account account, Activity activity, Department department) {
		Search search = new Search();
		search.addFilterEqual("department", department);
		search.addFilterEqual("account", account);
		search.addFilterEqual("activity", activity);
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return budgetDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public Budget getBudgetById(String id) {
		return budgetDAO.searchUniqueByPropertyEqual("id", id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Budget> getAllBudgets() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return budgetDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Budget> getBudgetByDepartment(Department department) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("department", department);
		return budgetDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Budget editBudget(Budget budget) throws ValidationFailedException {
		try {
			return budgetDAO.save(budget);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Budget");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteBudget(Budget budget) throws ValidationFailedException {
		if (isBudgetDeletable(budget) == false) {
			try {
				throw new ValidationFailedException("Budget is already referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Budget");
			}
		}

		try {
			budgetDAO.remove(budget);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Budget");
		}
	}

	public boolean isBudgetDeletable(Budget budget) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("department", budget);
		if (budgetDAO.count(search) > 0)
			return false;
		else
			return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Account saveAccount(Account account) throws ValidationFailedException {
		validateAccount(account);
		checkifAccountExists(account);
		try {
			return accountDAO.save(account);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Account");
		}
	}

	public void validateAccount(Account account) throws ValidationFailedException {
		if (account.getAccountCode() == null) {
			try {
				throw new ValidationFailedException("Account Code is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account");
			}
		}
		if (account.getAccountName() == null) {
			try {
				throw new ValidationFailedException("Account Name is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account");
			}
		}
		if (account.getAccountDesc() == null) {
			try {
				throw new ValidationFailedException("Account Description is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account");
			}
		}

		if (account.getAccountCategory() == null) {
			try {
				throw new ValidationFailedException("Account Category is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account Category");
			}
		}

	}

	public void checkifAccountExists(Account account) throws ValidationFailedException {
		for (Account acc : getAllAccounts()) {
			if (account.getAccountCode().contentEquals(acc.getAccountCode())) {
				try {
					throw new ValidationFailedException("Account with the name " + account.getAccountCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account");
				}
			}

			if (account.getAccountCode().contentEquals(acc.getAccountCode())) {
				try {
					throw new ValidationFailedException("Account with the code " + account.getAccountCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account");
				}
			}

			if (account.getAccountDesc().contentEquals(acc.getAccountDesc())) {
				try {
					throw new ValidationFailedException("Account with the Description " + account.getAccountDesc() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account");
				}
			}

		}
	}

	@Transactional(readOnly = true)
	public Account getAccountById(String id) {
		return accountDAO.searchUniqueByPropertyEqual("id", id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Account> getAllAccounts() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return accountDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Account getAccountByCode(String code) {
		Search search = new Search();
		search.addFilterEqual("accountCode", code);
		return accountDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public List<Account> searchAccounts(String param) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterOr(new Filter[] { new Filter("accountCode", param, Filter.OP_LIKE), new Filter("accountName", param, Filter.OP_LIKE), new Filter("accountDesc", param, Filter.OP_LIKE), new Filter("accountCategory.accountCategoryName", param, Filter.OP_LIKE),
				new Filter("accountCategory.accountCategoryDesc", param, Filter.OP_LIKE), new Filter("accountCategory.accountCategoryCode", param, Filter.OP_LIKE) });
		return accountDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Account> getAllAccounts(int offSet, int limit) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.setFirstResult(offSet);
		search.setMaxResults(limit);
		return accountDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Account editAccount(Account account) throws ValidationFailedException {
		validateAccount(account);
		if (getAccountByCode(account.getAccountCode()) != null) {
			if (account.getId().contentEquals(getAccountByCode(account.getAccountCode()).getId()) == false) {
				if (account.getAccountCode().contentEquals(getAccountByCode(account.getAccountCode()).getAccountCode()) == true)
					throw new ValidationFailedException("Account with the Code " + account.getAccountCode() + " already exists");
			}
		}

		try {
			return accountDAO.save(account);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Account");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAccount(Account account) throws ValidationFailedException {
			try {
				accountDAO.remove(account);
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Account");
			}
		
	}

	

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Account> getVoteByVoteCategory(AccountCategory accountCategory) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("voteCategory", accountCategory);
		return accountDAO.search(search);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public AccountCategory saveAccountCategory(AccountCategory accountCategory) throws ValidationFailedException {
		validateAccountCategory(accountCategory);
		checkifAccountCategoryExists(accountCategory);
		try {
			return accountCategoryDAO.save(accountCategory);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Account Category");
		}

	}

	public void validateAccountCategory(AccountCategory account) throws ValidationFailedException {
		if (account.getAccountCategoryDesc() == null) {
			try {
				throw new ValidationFailedException("Account Category Description is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account Category");
			}
		}
		if (account.getAccountCategoryName() == null) {
			try {
				throw new ValidationFailedException("Account Category Name is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account Category");
			}
		}

		if (account.getAccountCategoryCode() == null) {
			try {
				throw new ValidationFailedException("Account Category Code is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account Category");
			}
		}

	}

	public void checkifAccountCategoryExists(AccountCategory accountCategory) throws ValidationFailedException {
		for (AccountCategory accountCat : getAllAccountCategories()) {
			if (accountCat.getAccountCategoryCode().contentEquals(accountCategory.getAccountCategoryCode())) {
				try {
					throw new ValidationFailedException("Account Category with the Code " + accountCategory.getAccountCategoryCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account Category");
				}
			}

			if (accountCat.getAccountCategoryName().contentEquals(accountCategory.getAccountCategoryName())) {
				try {
					throw new ValidationFailedException("Account Category with the Name " + accountCategory.getAccountCategoryName() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Account Category");
				}
			}

			/*
			 * if
			 * (accountCat.getAccountCategoryDesc().contentEquals(accountCategory
			 * .getAccountCategoryDesc())) { try { throw new
			 * ValidationFailedException
			 * ("Account Category with the Description " +
			 * accountCategory.getAccountCategoryDesc() + " already exists  ");
			 * } finally { auditlogService.saveAuditEventTrail(Operations.SAVE,
			 * ActionStatus.FAIL, "Account Category"); } }
			 */
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public AccountCategory getAccountCategoryByCode(String code) {
		Search search = new Search();
		search.addFilterEqual("accountCategoryCode", code);
		return accountCategoryDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public AccountCategory getAccountCategorytById(String id) {
		return accountCategoryDAO.searchUniqueByPropertyEqual("id", id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<AccountCategory> getAllAccountCategories() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return accountCategoryDAO.search(search);
	}

	@Transactional(readOnly = true)
	public List<AccountCategory> searchAccountCategories(String param) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterOr(new Filter[] { new Filter("accountCategoryName", param, Filter.OP_LIKE), new Filter("accountCategoryDesc", param, Filter.OP_LIKE), new Filter("accountCategoryCode", param, Filter.OP_LIKE) });
		return accountCategoryDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public AccountCategory editAccountCategory(AccountCategory accountCategory) throws ValidationFailedException {
		validateAccountCategory(accountCategory);
		if (getAccountCategoryByCode(accountCategory.getAccountCategoryCode()) != null) {
			if (accountCategory.getId().contentEquals(getAccountCategoryByCode(accountCategory.getAccountCategoryCode()).getId()) == false) {
				if (accountCategory.getAccountCategoryCode().contentEquals(getAccountCategoryByCode(accountCategory.getAccountCategoryCode()).getAccountCategoryCode()) == true)
					throw new ValidationFailedException("Account Category with the Code " + accountCategory.getAccountCategoryCode() + " already exists");
			}
		}
		try {
			return accountCategoryDAO.save(accountCategory);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Account Category");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAccountCategory(AccountCategory accountCategory) throws ValidationFailedException {
		if (isAccountCategoryDeletable(accountCategory) == false) {
			try {
				throw new ValidationFailedException("Account is already referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Account Category");
			}
		}

		try {
			accountCategoryDAO.remove(accountCategory);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Account Category");
		}
	}

	public boolean isAccountCategoryDeletable(AccountCategory accountCategory) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("accountCategory", accountCategory);
		if (accountDAO.count(search) > 0)
			return false;
		else
			return true;
	}

	
	
	

}
