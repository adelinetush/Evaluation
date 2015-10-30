/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;

/**
 * @author Planet Developer 001
 * 
 */
public class AccountsResult implements Result {

	private String serverResponse;
	private boolean operationStatus;
	private String level;
	

	private List<Account> accounts;
	private List<Activity> activities;
	private List<AccountCategory> accountCategory;
	

	public AccountsResult(boolean operationStatus, String serverResponse) {

		this.operationStatus=operationStatus;
		this.serverResponse=serverResponse;
		
	}
	
	public AccountsResult(boolean operationStatus, List<Account> accounts) {

		this.operationStatus=operationStatus;
		this.accounts=accounts;
		
	}
	
	public AccountsResult(List<Activity> activities,boolean operationStatus) {

		this.operationStatus=operationStatus;
		this.activities=activities;
		
	}
	
	public AccountsResult(boolean operationStatus,List<AccountCategory> accountCategory,String level) {

		this.operationStatus=operationStatus;
		this.accountCategory= accountCategory;
		this.level=level;
		
	}

	@SuppressWarnings("unused")
	private AccountsResult() {

	}

	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}

	/**
	 * @return the operationStatus
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @return the activities
	 */
	public List<Activity> getActivities() {
		return activities;
	}

	/**
	 * @return the accountCategory
	 */
	public List<AccountCategory> getAccountCategory() {
		return accountCategory;
	}

}
