/**
 * 
 */
package com.planetsystems.monitoring.client.events;

import com.gwtplatform.mvp.client.UiHandlers;

import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;

/**
 * @author Planet Developer 001
 *
 */
public interface AccountUiHandlers extends UiHandlers {

	void saveAccountCategoryButtonClicked(AccountCategory accountCategory);
	void editAccountCategoryButtonClicked(AccountCategory accountCategory);
	void deleteAccountCategoryButtonClicked(AccountCategory accountCategory);
	void retrieveAllAccountCategoriesButtonClicked();
	
	void saveAccountButtonClicked(Account account);
	void editAccountButtonClicked(Account account);
	void deleteAccountButtonClicked(Account account);
	void retrieveAllAccountsButtonClicked();
	
	void saveActivityButtonClicked(Activity activity);
	void editActivityButtonClicked(Activity activity);
	void deleteActivityButtonClicked(Activity activity);
	void retrieveAllActivitiesButtonClicked();
	
	void retrieveDepartments();
	
}
