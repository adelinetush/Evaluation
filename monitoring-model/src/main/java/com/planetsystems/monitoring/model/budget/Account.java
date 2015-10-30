package com.planetsystems.monitoring.model.budget;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.planetsystems.monitoring.model.ParentEntity;


@Entity
public class Account extends ParentEntity{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4282356392901314497L;
	private String accountCode;
	private String accountName;
	private String accountDesc;
	private AccountCategory accountCategory;
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
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the accountDesc
	 */
	public String getAccountDesc() {
		return accountDesc;
	}
	/**
	 * @param accountDesc the accountDesc to set
	 */
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	/**
	 * @return the accountCategory
	 */
	@ManyToOne
	public AccountCategory getAccountCategory() {
		return accountCategory;
	}
	/**
	 * @param accountCategory the accountCategory to set
	 */
	public void setAccountCategory(AccountCategory accountCategory) {
		this.accountCategory = accountCategory;
	}
	
	
	
}
