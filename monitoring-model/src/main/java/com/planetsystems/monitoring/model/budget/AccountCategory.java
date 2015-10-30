package com.planetsystems.monitoring.model.budget;

import javax.persistence.Entity;

import com.planetsystems.monitoring.model.ParentEntity;



@Entity
public class AccountCategory extends ParentEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3481448985984739906L;
	private String accountCategoryName;
	private String accountCategoryDesc;
	private String accountCategoryCode;
	/**
	 * @return the accountCategoryName
	 */
	public String getAccountCategoryName() {
		return accountCategoryName;
	}
	/**
	 * @param accountCategoryName the accountCategoryName to set
	 */
	public void setAccountCategoryName(String accountCategoryName) {
		this.accountCategoryName = accountCategoryName;
	}
	/**
	 * @return the accountCategoryDesc
	 */
	public String getAccountCategoryDesc() {
		return accountCategoryDesc;
	}
	/**
	 * @param accountCategoryDesc the accountCategoryDesc to set
	 */
	public void setAccountCategoryDesc(String accountCategoryDesc) {
		this.accountCategoryDesc = accountCategoryDesc;
	}
	/**
	 * @return the accountCategoryCode
	 */
	public String getAccountCategoryCode() {
		return accountCategoryCode;
	}
	/**
	 * @param accountCategoryCode the accountCategoryCode to set
	 */
	public void setAccountCategoryCode(String accountCategoryCode) {
		this.accountCategoryCode = accountCategoryCode;
	}
			
}
