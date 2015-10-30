/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.units.Department;


/**
 * @author Planet Developer 001
 * 
 */
public class AccountsAction extends UnsecuredActionImpl<AccountsResult> {

	private String operationLevel;
	private String operation;

	private Account account;
	private Activity activity;
	private AccountCategory accountCategory;
	private Department department;
	
	

	public AccountsAction(String operationLevel, String operation,
			Account account) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.account=account;
	}

	public AccountsAction(String operationLevel, String operation,
			Activity activity) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.activity=activity;
		
	}
	
	public AccountsAction(String operationLevel, String operation,
			AccountCategory accountCategory) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.accountCategory=accountCategory;
		
	}
	
	public AccountsAction(String operationLevel, String operation) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		
	}
	
	public AccountsAction(String operationLevel, String operation,
			Department department) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.department=department;
		
	}
	
	
	
	@SuppressWarnings("unused")
	private AccountsAction() {

	}

	/**
	 * @return the operationLevel
	 */
	public String getOperationLevel() {
		return operationLevel;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @return the accountCategory
	 */
	public AccountCategory getAccountCategory() {
		return accountCategory;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	
}
