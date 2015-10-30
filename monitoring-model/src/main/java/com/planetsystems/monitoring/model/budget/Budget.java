package com.planetsystems.monitoring.model.budget;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.units.Department;

@Entity(name="Budget")
public class Budget extends ParentEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5294750810743975453L;
	private Department department;
	private Account account;
	private String accountCode;
	private Activity activity;
	private String activityName;
	private String activityCode;
	private Currency currency;
	private double budgetAmount;
	
	
	/**
	 * @return the department
	 */
	@ManyToOne
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@ManyToOne
	public Activity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * @return the activityCode
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * @param activityCode the activityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	
	/**
	 * @return the budgetAmount
	 */
	public double getBudgetAmount() {
		return budgetAmount;
	}
	/**
	 * @param budgetAmount the budgetAmount to set
	 */
	public void setBudgetAmount(double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	/**
	 * @return the currency
	 */
	@ManyToOne
	public Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the account
	 */
	@ManyToOne
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}
	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	
	

}
