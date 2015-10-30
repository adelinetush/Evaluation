/**
 * 
 */
package com.planetsystems.monitoring.client.presenters.project.admin;

import java.util.List;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.events.AccountUiHandlers;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsResult;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsResult;


public class AccountsPresenter extends
		Presenter<AccountsPresenter.MyView, AccountsPresenter.MyProxy>
		implements AccountUiHandlers {

	DispatchAsync dispatcher;

	@Inject
	public AccountsPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);

		this.dispatcher = dispatcher;
	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.accounts)
	public interface MyProxy extends Proxy<AccountsPresenter>, Place {

	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	public interface MyView extends View, HasUiHandlers<AccountUiHandlers> {

		void setServerResponse(String serverResponse);

		void loadAllAccounts(List<Account> accounts);

		void loadAllAccountCategories(List<AccountCategory> accountCategory);

		void loadAllActivities(List<Activity> activities);

		void loadCategoriesCombo(List<AccountCategory> accountCategories);

		void clearAccountForm();

		void clearAccountCategoryForm();

		void clearActivityForm();

		void loadDepartmentsGrid(List<Department> departments);
	}

	@Override
	protected void revealInParent() {

		RevealContentEvent.fire(this,
				ProjectManagerPresenter.TYPE_SetContextAreaContent, this);

	}

	@Inject
	PlaceManager placeManager;

	protected void onBind() {
		super.onBind();

		retrieveAllAccountCategoriesButtonClicked();
	}

	protected void onReset() {
		super.onReset();

		retrieveAllAccountCategoriesButtonClicked();
	}

	public void saveAccountButtonClicked(Account account) {

		dispatcher.execute(new AccountsAction(NameTokens.accounts,
				NameTokens.saveOperation, account),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllAccountsButtonClicked();
							getView().clearAccountForm();

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	public void editAccountButtonClicked(Account account) {

		dispatcher.execute(new AccountsAction(NameTokens.accounts,
				NameTokens.editOperation, account),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllAccountsButtonClicked();
							getView().clearAccountForm();
						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	public void deleteAccountButtonClicked(Account account) {

		dispatcher.execute(new AccountsAction(NameTokens.accounts,
				NameTokens.deleteOperation, account),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllAccountsButtonClicked();
							getView().clearAccountForm();
						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	public void retrieveAllAccountsButtonClicked() {

		dispatcher.execute(new AccountsAction(NameTokens.accounts,
				NameTokens.retrieveOperation),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadAllAccounts(result.getAccounts());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	public void saveActivityButtonClicked(Activity activity) {

		dispatcher.execute(new AccountsAction(NameTokens.activities,
				NameTokens.saveOperation, activity),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllActivitiesButtonClicked();
							getView().clearActivityForm();

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	public void editActivityButtonClicked(Activity activity) {

		dispatcher.execute(new AccountsAction(NameTokens.activities,
				NameTokens.editOperation, activity),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllActivitiesButtonClicked();
							getView().clearActivityForm();
						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	public void deleteActivityButtonClicked(Activity activity) {

		dispatcher.execute(new AccountsAction(NameTokens.activities,
				NameTokens.deleteOperation, activity),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllActivitiesButtonClicked();
							getView().clearActivityForm();
						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});
	}

	public void retrieveAllActivitiesButtonClicked() {

		dispatcher.execute(new AccountsAction(NameTokens.activities,
				NameTokens.retrieveOperation),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadAllActivities(result.getActivities());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});
	}

	public void saveAccountCategoryButtonClicked(AccountCategory accountCategory) {

		dispatcher.execute(new AccountsAction(NameTokens.accountCategory,
				NameTokens.saveOperation, accountCategory),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllAccountCategoriesButtonClicked();
							getView().clearAccountCategoryForm();

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});
	}

	public void editAccountCategoryButtonClicked(AccountCategory accountCategory) {

		dispatcher.execute(new AccountsAction(NameTokens.accountCategory,
				NameTokens.editOperation, accountCategory),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllAccountCategoriesButtonClicked();
							getView().clearAccountCategoryForm();

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});
	}

	public void deleteAccountCategoryButtonClicked(
			AccountCategory accountCategory) {

		dispatcher.execute(new AccountsAction(NameTokens.accountCategory,
				NameTokens.deleteOperation, accountCategory),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							retrieveAllAccountCategoriesButtonClicked();
							getView().clearAccountCategoryForm();

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	public void retrieveAllAccountCategoriesButtonClicked() {

		dispatcher.execute(new AccountsAction(NameTokens.accountCategory,
				NameTokens.retrieveOperation),
				new AsyncCallback<AccountsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(AccountsResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadAllAccountCategories(
									result.getAccountCategory());
							getView().loadCategoriesCombo(
									result.getAccountCategory());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	public void retrieveDepartments() {

		dispatcher.execute(new OrganizationalUnitsAction(
				NameTokens.departments, NameTokens.retrieveOperation),
				new AsyncCallback<OrganizationalUnitsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponse(caught.getMessage());

					}

					public void onSuccess(OrganizationalUnitsResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadDepartmentsGrid(
									result.getDepartments());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});
	}

}
