/**
 * 
 */
package com.planetsystems.monitoring.server.dispatch.admin.accounts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.core.services.BudgetService;
import com.planetsystems.monitoring.core.services.OrganisationalUnitService;
import com.planetsystems.monitoring.model.ProcnetErrorCodes;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsResult;

/**
 * @author Planet Developer 001
 * 
 */
public class AccountsHandler extends AbstractActionHandler<AccountsAction, AccountsResult> {

	
	@Autowired
	private BudgetService budgetService;

	@Autowired
	private OrganisationalUnitService organizationalService;

	

	public AccountsHandler() {
		super(AccountsAction.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param actionType
	 */

	@Override
	public Class<AccountsAction> getActionType() {
		return AccountsAction.class;
	}

	public AccountsResult execute(AccountsAction action, ExecutionContext context) throws ActionException {

		System.out.println("AccountsResult execute");

		AccountsResult result = null;

		if (action.getOperationLevel().contentEquals(NameTokens.accounts) && action.getOperation().contentEquals(NameTokens.saveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = saveAccount(action.getAccount());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accounts) && action.getOperation().contentEquals(NameTokens.editOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());
			result = editAccount(action.getAccount());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accounts) && action.getOperation().contentEquals(NameTokens.deleteOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());
			result = deleteAccount(action.getAccount());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accounts) && action.getOperation().contentEquals(NameTokens.retrieveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = retrieveAllAccounts();// retrieveBudgetedAccounts
		} else if (action.getOperationLevel().contentEquals(NameTokens.activities) && action.getOperation().contentEquals(NameTokens.saveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = saveActivity(action.getActivity());

		} else if (action.getOperationLevel().contentEquals(NameTokens.activities) && action.getOperation().contentEquals(NameTokens.editOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = editActivity(action.getActivity());

		} else if (action.getOperationLevel().contentEquals(NameTokens.activities) && action.getOperation().contentEquals(NameTokens.deleteOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());
			result = deleteActivity(action.getActivity());

		} else if (action.getOperationLevel().contentEquals(NameTokens.activities) && action.getOperation().contentEquals(NameTokens.retrieveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = retrieveAlActivities();

		} else if (action.getOperationLevel().contentEquals(NameTokens.activities) && action.getOperation().contentEquals(NameTokens.retrieveActivitiesByDepartmentOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = retrieveActivitiesByDepartment(action.getDepartment());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accountCategory) && action.getOperation().contentEquals(NameTokens.saveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = saveAccountCategory(action.getAccountCategory());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accountCategory) && action.getOperation().contentEquals(NameTokens.editOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = editAccountCategory(action.getAccountCategory());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accountCategory) && action.getOperation().contentEquals(NameTokens.deleteOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = deleteAccountCategory(action.getAccountCategory());

		} else if (action.getOperationLevel().contentEquals(NameTokens.accountCategory) && action.getOperation().contentEquals(NameTokens.retrieveOperation)) {

			System.out.println("Level: " + action.getOperationLevel() + ", Operation: " + action.getOperation());

			result = retrieveAlAccountCategories();

		} else {

			result = new AccountsResult(true, "No Matching Operation ");

		}

		return result;
	}

	public void undo(AccountsAction action, AccountsResult result, ExecutionContext context) throws ActionException {

	}

	private AccountsResult saveAccount(Account accountTo) {

		AccountsResult result = null;

		try {

			Account account = new Account();
			account.setAccountCode(accountTo.getAccountCode());
			account.setAccountName(accountTo.getAccountName());
			account.setAccountDesc(accountTo.getAccountDesc());

			AccountCategory category = new AccountCategory();
			category = budgetService.getAccountCategorytById(accountTo.getAccountCategory().getId());

			account.setAccountCategory(category);

			budgetService.saveAccount(account);

			result = new AccountsResult(true, "Successfully Saved Account");

		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult editAccount(Account accountTo) {

		AccountsResult result = null;

		try {
			System.out.println(accountTo.getId());
			System.out.println(accountTo.getAccountCode());
			System.out.println(accountTo.getAccountName());
			System.out.println(accountTo.getAccountDesc());
			System.out.println(accountTo.getAccountCategory().getAccountCategoryCode());

			Account account = new Account();
			account = budgetService.getAccountById(accountTo.getId());

			AccountCategory category = new AccountCategory();
			category = budgetService.getAccountCategorytById(accountTo.getAccountCategory().getId());

			System.out.println("Account Code: " + accountTo.getAccountCode());
			account.setAccountCode(accountTo.getAccountCode());
			account.setAccountName(accountTo.getAccountName());
			account.setAccountDesc(accountTo.getAccountDesc());
			account.setAccountCategory(category);

			budgetService.editAccount(account);

			result = new AccountsResult(true, "Edit Account Successfully");

		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult deleteAccount(Account accountTo) {

		AccountsResult result = null;

		try {
			System.out.println(accountTo.getId() + " Debuging account id..");

			Account account = new Account();
			account = budgetService.getAccountById(accountTo.getId());

			budgetService.deleteAccount(account);

			result = new AccountsResult(true, "Deleted Successfully");

		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult retrieveAllAccounts() {

		AccountsResult result = null;

		try {

			List<Account> accounts = new ArrayList<Account>();
			List<Account> accountsDTOs = new ArrayList<Account>();

			accounts = budgetService.getAllAccounts();

			for (Account account : accounts) {

				Account accountDTO = new Account();
				accountDTO.setId(account.getId());
				accountDTO.setAccountCode(account.getAccountCode());
				accountDTO.setAccountName(account.getAccountName());
				accountDTO.setAccountDesc(account.getAccountDesc());

				AccountCategory category = new AccountCategory();
				category.setId(account.getAccountCategory().getId());
				category.setAccountCategoryCode(account.getAccountCategory().getAccountCategoryCode());
				category.setAccountCategoryName(account.getAccountCategory().getAccountCategoryName());
				category.setAccountCategoryDesc(account.getAccountCategory().getAccountCategoryDesc());

				accountDTO.setAccountCategory(category);

				User user = new User();

				if (account.getCreatedBy() != null) {

					user.setId(account.getCreatedBy().getId());
					user.setfName(account.getCreatedBy().getfName());
					user.setlName(account.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (account.getChangedBy() != null) {

					userChanged.setId(account.getChangedBy().getId());
					userChanged.setfName(account.getChangedBy().getfName());
					userChanged.setlName(account.getChangedBy().getlName());

				} else {

				}

				accountDTO.setChangedBy(userChanged);
				accountDTO.setCreatedBy(user);

				accountsDTOs.add(accountDTO);
			}

			result = new AccountsResult(true, accountsDTOs);

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult retrieveBudgetedAccounts() {

		AccountsResult result = null;

		try {

			List<Account> accounts = new ArrayList<Account>();
			List<Account> accountsDTOs = new ArrayList<Account>();

			

			//accounts = budgetService.getBudgetedAccountsByFinancialYearCode(year);

			System.out.println();

			for (Account account : accounts) {

				Account accountDTO = new Account();
				accountDTO.setId(account.getId());
				accountDTO.setAccountCode(account.getAccountCode());
				accountDTO.setAccountName(account.getAccountName());
				accountDTO.setAccountDesc(account.getAccountDesc());

				AccountCategory category = new AccountCategory();
				category.setId(account.getAccountCategory().getId());
				category.setAccountCategoryCode(account.getAccountCategory().getAccountCategoryCode());
				category.setAccountCategoryName(account.getAccountCategory().getAccountCategoryName());
				category.setAccountCategoryDesc(account.getAccountCategory().getAccountCategoryDesc());

				accountDTO.setAccountCategory(category);

				User user = new User();

				if (account.getCreatedBy() != null) {

					user.setId(account.getCreatedBy().getId());
					user.setfName(account.getCreatedBy().getfName());
					user.setlName(account.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (account.getChangedBy() != null) {

					userChanged.setId(account.getChangedBy().getId());
					userChanged.setfName(account.getChangedBy().getfName());
					userChanged.setlName(account.getChangedBy().getlName());

				} else {

				}

				accountDTO.setChangedBy(userChanged);
				accountDTO.setCreatedBy(user);

				accountsDTOs.add(accountDTO);
			}

			result = new AccountsResult(true, accountsDTOs);

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult saveAccountCategory(AccountCategory accountCategoryTo) {

		AccountsResult result = null;

		try {

			AccountCategory accountCategory = new AccountCategory();
			accountCategory.setAccountCategoryCode(accountCategoryTo.getAccountCategoryCode());
			accountCategory.setAccountCategoryName(accountCategoryTo.getAccountCategoryName());
			accountCategory.setAccountCategoryDesc(accountCategoryTo.getAccountCategoryDesc());

			budgetService.saveAccountCategory(accountCategory);

			result = new AccountsResult(true, "Save Account Category Successfully");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult editAccountCategory(AccountCategory accountCategoryTo) {

		AccountsResult result = null;

		try {

			AccountCategory accountCategory = new AccountCategory();
			accountCategory = budgetService.getAccountCategorytById(accountCategoryTo.getId());

			accountCategory.setAccountCategoryCode(accountCategoryTo.getAccountCategoryCode());
			accountCategory.setAccountCategoryName(accountCategoryTo.getAccountCategoryName());
			accountCategory.setAccountCategoryDesc(accountCategoryTo.getAccountCategoryDesc());

			System.out.println("Description: " + accountCategory.getAccountCategoryDesc());

			budgetService.editAccountCategory(accountCategory);

			result = new AccountsResult(true, "Edit Account Category Successfully");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult deleteAccountCategory(AccountCategory accountCategoryTo) {

		AccountsResult result = null;

		try {

			AccountCategory accountCategory = new AccountCategory();
			accountCategory = budgetService.getAccountCategorytById(accountCategoryTo.getId());

			budgetService.deleteAccountCategory(accountCategory);

			result = new AccountsResult(true, "Delete Account Category Successfully");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult retrieveAlAccountCategories() {

		AccountsResult result = null;

		try {

			List<AccountCategory> accountCategories = new ArrayList<AccountCategory>();
			List<AccountCategory> accountCategoriesDTOs = new ArrayList<AccountCategory>();

			accountCategories = budgetService.getAllAccountCategories();

			for (AccountCategory accountCategory : accountCategories) {

				AccountCategory accountCategoryDTO = new AccountCategory();
				accountCategoryDTO.setId(accountCategory.getId());
				accountCategoryDTO.setAccountCategoryCode(accountCategory.getAccountCategoryCode());
				accountCategoryDTO.setAccountCategoryName(accountCategory.getAccountCategoryName());
				accountCategoryDTO.setAccountCategoryDesc(accountCategory.getAccountCategoryDesc());

				User user = new User();

				if (accountCategory.getCreatedBy() != null) {

					user.setId(accountCategory.getCreatedBy().getId());
					user.setfName(accountCategory.getCreatedBy().getfName());
					user.setlName(accountCategory.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (accountCategory.getChangedBy() != null) {

					userChanged.setId(accountCategory.getChangedBy().getId());
					userChanged.setfName(accountCategory.getChangedBy().getfName());
					userChanged.setlName(accountCategory.getChangedBy().getlName());

				} else {

				}

				accountCategoryDTO.setChangedBy(userChanged);
				accountCategoryDTO.setCreatedBy(user);

				accountCategoriesDTOs.add(accountCategoryDTO);
			}

			result = new AccountsResult(true, accountCategoriesDTOs, "level");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	// Activities
	private AccountsResult saveActivity(Activity activityTo) {

		AccountsResult result = null;

		try {

			Activity activity = new Activity();
			activity.setActivityCode(activityTo.getActivityCode());
			activity.setActivityName(activityTo.getActivityName());
			activity.setActivityDesc(activityTo.getActivityDesc());

			Department department = new Department();
			department = organizationalService.getDepartmentById(activityTo.getDepartment().getId());
			activity.setDepartment(department);

			budgetService.saveActivity(activity);

			result = new AccountsResult(true, "Save Activity Successfully");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult editActivity(Activity activityTo) {

		AccountsResult result = null;

		try {

			Activity activity = new Activity();
			activity = budgetService.getActivityId(activityTo.getId());

			activity.setActivityCode(activityTo.getActivityCode());
			activity.setActivityName(activityTo.getActivityName());
			activity.setActivityDesc(activityTo.getActivityDesc());

			Department department = new Department();
			department = organizationalService.getDepartmentById(activityTo.getDepartment().getId());
			activity.setDepartment(department);

			budgetService.editActivity(activity);

			result = new AccountsResult(true, "Successfully Edited Activity");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult deleteActivity(Activity activityTo) {

		AccountsResult result = null;

		try {

			Activity activity = new Activity();
			activity = budgetService.getActivityId(activityTo.getId());

			budgetService.deleteActivity(activity);

			result = new AccountsResult(true, "Successfully Deleted Activity");

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult retrieveAlActivities() {

		AccountsResult result = null;

		try {

			List<Activity> activities = new ArrayList<Activity>();
			List<Activity> activitiesDTOs = new ArrayList<Activity>();

			activities = budgetService.getAllActivities();
			System.out.println("Number of Activities= " + activities.size());

			for (Activity activity : activities) {

				Activity activityDTO = new Activity();
				activityDTO.setId(activity.getId());
				activityDTO.setActivityCode(activity.getActivityCode());
				activityDTO.setActivityName(activity.getActivityName());
				activityDTO.setActivityDesc(activity.getActivityDesc());

				Department department = new Department();
				department.setId(activity.getDepartment().getId());
				department.setDepartmentName(activity.getDepartment().getDepartmentName());
				department.setDepartmentCode(activity.getDepartment().getDepartmentCode());

				activityDTO.setDepartment(department);

				User user = new User();

				if (activity.getCreatedBy() != null) {

					user.setId(activity.getCreatedBy().getId());
					user.setfName(activity.getCreatedBy().getfName());
					user.setlName(activity.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (activity.getChangedBy() != null) {

					userChanged.setId(activity.getChangedBy().getId());
					userChanged.setfName(activity.getChangedBy().getfName());
					userChanged.setlName(activity.getChangedBy().getlName());

				} else {

				}

				activityDTO.setChangedBy(userChanged);
				activityDTO.setCreatedBy(user);

				activitiesDTOs.add(activityDTO);
			}

			result = new AccountsResult(activitiesDTOs, true);

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}

	private AccountsResult retrieveActivitiesByDepartment(Department departmentTo) {

		AccountsResult result = null;
		

		try {

			Department departmentFrom = new Department();
			departmentFrom = organizationalService.getDepartmentById(departmentTo.getId());

			List<Activity> activities = new ArrayList<Activity>();
			List<Activity> activitiesDTOs = new ArrayList<Activity>();

			activities = budgetService.getActivityByDept(departmentFrom);

			for (Activity activity : activities) {

				Activity activityDTO = new Activity();
				activityDTO.setId(activity.getId());
				activityDTO.setActivityCode(activity.getActivityCode());
				activityDTO.setActivityName(activity.getActivityName());
				activityDTO.setActivityDesc(activity.getActivityDesc());

				Department department = new Department();
				department.setId(activity.getDepartment().getId());
				department.setDepartmentName(activity.getDepartment().getDepartmentName());
				department.setDepartmentCode(activity.getDepartment().getDepartmentCode());

				activityDTO.setDepartment(department);

				User user = new User();

				if (activity.getCreatedBy() != null) {

					user.setId(activity.getCreatedBy().getId());
					user.setfName(activity.getCreatedBy().getfName());
					user.setlName(activity.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (activity.getChangedBy() != null) {

					userChanged.setId(activity.getChangedBy().getId());
					userChanged.setfName(activity.getChangedBy().getfName());
					userChanged.setlName(activity.getChangedBy().getlName());

				} else {

				}

				activity.setChangedBy(userChanged);
				activity.setCreatedBy(user);

				activitiesDTOs.add(activityDTO);
			}

			result = new AccountsResult(activitiesDTOs, true);

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AccountsResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION);
		}

		return result;
	}
}
