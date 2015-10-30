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
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.events.UsersUiHandlers;
import com.planetsystems.monitoring.client.managers.UsersManager;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.widgets.StatusBar;
import com.planetsystems.monitoring.client.widgets.buttons.SearchButton;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsResult;
import com.planetsystems.monitoring.shared.dispatch.users.UsersAction;
import com.planetsystems.monitoring.shared.dispatch.users.UsersResult;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class UsersPresenter extends
		Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy> implements
		UsersUiHandlers {

	DispatchAsync dispatcher;

	public static final int DEFAULT_MAX_RESULTS = 20;

	private int maxResults;
	private int firstResult;
	private int pageNumber;
	private int numberOfElements;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public UsersPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);

		this.dispatcher = dispatcher;

	}

	@ProxyCodeSplit
	@NameToken(NameTokens.users)
	public interface MyProxy extends Proxy<UsersPresenter>, Place {

	}

	public interface MyView extends View, HasUiHandlers<UsersUiHandlers> {

		void setServerResponse(String text);

		void setServerResponseError(String text);

		void loadAllPermisiions(List<Permission> permissions);

		void loadAllRoles(List<Role> roles);

		void loadAllUsers(List<User> users);

		void loadRoleCombo(List<Role> roles);

		void loadDivisionCombo(List<Division> divisions);

		void loadDepartmentCombo(List<Department> department);

		void loadPermissionsByRole(List<Permission> permissions);

		void loadUserRoles(List<Role> roles);

		void loadNewRoles(List<Role> roles);

		public TextItem getSearchText();

		public SearchButton getSearchButton();

		void ClearUserControls();

		void ClearRoleControls();

		void ClearPolicyControls();

		StatusBar getStatusBar();

	}

	@Inject
	PlaceManager placeManager;

	@Override
	protected void onBind() {
		super.onBind();

		maxResults = DEFAULT_MAX_RESULTS;
		firstResult = 0;
		pageNumber = 1;
		numberOfElements = maxResults;

		onLoadUsersWithLimits();
		onLoadPermissionsGrid();
		onLoadDivisionData();
		onSearchButtonClicked();
		onLoadRoleData();

	}

	@Override
	protected void onReset() {
		super.onReset();

		onLoadPermissionsGrid();
		onLoadDivisionData();
		onSearchButtonClicked();
		onLoadDepartments();
		onLoadRoleData();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.Presenter#revealInParent()
	 */
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				ProjectManagerPresenter.TYPE_SetContextAreaContent, this);

	}

	public void onNewButtonClicked() {
		// TODO Auto-generated method stub

	}

	public void onSaveUserButtonClicked(User user) {

		dispatcher.execute(new UsersAction(NameTokens.saveOperation, user,
				NameTokens.users), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				caught.printStackTrace();
				getView().setServerResponseError(caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					onLoadUsersWithLimits();
					getView().ClearUserControls();

				} else {

					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});
	}

	public void onCancelButton() {
		// TODO Auto-generated method stub

	}

	public void onSaveRoleButtonClicked(Role role) {

		// try{
		dispatcher.execute(new UsersAction(NameTokens.saveOperation,
				NameTokens.roles, role), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponseError(
						"An error Occured: " + caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					getView().ClearRoleControls();
					onLoadRoleData();

				} else {
					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});

	}

	public void onEditUserButtonClicked(User user) {

		dispatcher.execute(new UsersAction(NameTokens.editOperation, user,
				NameTokens.users), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				caught.printStackTrace();
				getView().setServerResponseError(caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					onLoadUsersWithLimits();

					getView().ClearUserControls();

				} else {

					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});
	}

	public void onDeleteUserButtonClicked(User user) {

		dispatcher.execute(new UsersAction(NameTokens.deleteOperation, user,
				NameTokens.users), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				caught.printStackTrace();
				getView().setServerResponseError(caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					onLoadUsersWithLimits();
				} else {

					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});
	}

	public void onEditRoleButtonClicked(Role role) {

		// try{
		dispatcher.execute(new UsersAction(NameTokens.editOperation,
				NameTokens.roles, role), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponseError(
						"An error Occured: " + caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					getView().ClearRoleControls();
					onLoadRoleData();

				} else {
					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});
	}

	public void onDeleteRoleButtonClicked(Role role) {

		// try{
		dispatcher.execute(new UsersAction(NameTokens.deleteOperation,
				NameTokens.roles, role), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponseError(
						"An error Occured: " + caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					getView().ClearRoleControls();
					onLoadRoleData();

				} else {
					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});

	}

	public void onLoadDivisionData() {

		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.divisions,
				NameTokens.retrieveOperation),
				new AsyncCallback<OrganizationalUnitsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponseError(caught.getMessage());

					}

					public void onSuccess(OrganizationalUnitsResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadDivisionCombo(result.getDivisions());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponseError(
									result.getServerResponse());
						}

					}

				});
	}

	public void onLoadRoleData() {

		dispatcher.execute(new UsersAction(NameTokens.retrieveOperation,
				NameTokens.roles), new AsyncCallback<UsersResult>() {
			public void onFailure(Throwable caught) {
				getView().setServerResponseError(
						"An error occured: " + caught.getMessage());
			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().loadAllRoles(result.getRoles());
					getView().loadRoleCombo(result.getRoles());

				} else if (result.isOperationStatus() == false) {

					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}
		});

	}

	public void onLoadUserGridData() {

		/*
		 * dispatcher.execute(new
		 * UsersAction(NameTokens.retrieveOpeartion,NameTokens.users), new
		 * AsyncCallback<UsersResult>() { public void onFailure(Throwable
		 * caught) {
		 * 
		 * Log.error("onFailure() - ",caught);
		 * getView().setServerResponseError("An Error Occurred: "
		 * +caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(UsersResult result) {
		 * 
		 * Log.info("onSuccess..."); if(result.isOperationStatus() == true){
		 * 
		 * Log.info("onSuccess:true");
		 * 
		 * getView().loadAllUsers(result.getUsers()); }
		 * 
		 * else {
		 * 
		 * Log.info("onSuccess:false ");
		 * getView().setServerResponseError(result.getServerResponse()); }
		 * 
		 * } });
		 */

	}

	public void onLoadPermissionsGrid() {

		dispatcher.execute(new UsersAction(NameTokens.retrieveOperation,
				NameTokens.permissions), new AsyncCallback<UsersResult>() {
			public void onFailure(Throwable caught) {
				// Log.debug("onFailure() - " + caught.getMessage());
				getView().setServerResponse(
						"An Error Occurred: " + caught.getMessage());
				// caught.printStackTrace();

			}

			public void onSuccess(UsersResult result) {

				if (result.getPermissions() != null) {

					getView().loadAllPermisiions(result.getPermissions());
				} else if (result.getPermissions() == null) {

					// getView().setServerResponse("Users List is Null");
				}

			}
		});

	}

	public void onLoadPermissionsUser() {

	}

	/*
	 * public void onLoadPermissionsRole(Role role) {
	 * 
	 * 
	 * }
	 */

	public void onLoadRoleUser(User user) {

		dispatcher.execute(
				new UsersAction(NameTokens.retrieveRolesByUserOperation, user,
						NameTokens.roles), new AsyncCallback<UsersResult>() {
					public void onFailure(Throwable caught) {

						getView().setServerResponse(
								"An Error Occurred: " + caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus()) {

							getView().loadUserRoles(result.getRoles());

						} else {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}
				});

	}

	public void onLoadUsersByRole() {

	}

	public void onActivateUserButtonClicked(User user) {

		// try{
		dispatcher.execute(new UsersAction(NameTokens.activateOperation, user,
				NameTokens.users), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponseError(
						"An error Occured: " + caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					getView().ClearRoleControls();
					onLoadUsersWithLimits();

				} else {
					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});
	}

	public void onDeActivateUserButtonClicked(User user) {

		// try{
		dispatcher.execute(new UsersAction(NameTokens.deActivateOperation,
				user, NameTokens.users), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponseError(
						"An error Occured: " + caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());
					getView().ClearRoleControls();
					onLoadUsersWithLimits();

				} else {
					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});

	}

	public void onReportButtonClicked() {

	}

	public void onLoadPermissionsRole(Role role) {

		dispatcher.execute(new UsersAction(
				NameTokens.retrievePermissionsByRoleOperation,
				NameTokens.permissions, role),
				new AsyncCallback<UsersResult>() {
					public void onFailure(Throwable caught) {

						getView().setServerResponse(
								"An Error Occurred: " + caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.getPermissions() != null) {

							getView().loadPermissionsByRole(
									result.getPermissions());
						} else if (result.getPermissions() == null) {

						}

					}
				});

	}

	/*
	 * public void onSavePasswordPolicy(RegularExpression expression) {
	 * 
	 * 
	 * }
	 */

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @return the firstResult
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * @param firstResult
	 *            the firstResult to set
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the numberOfElements
	 */
	public int getNumberOfElements() {
		return numberOfElements;
	}

	/**
	 * @param numberOfElements
	 *            the numberOfElements to set
	 */
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public void onResultSetNextButtonClicked() {

		firstResult += numberOfElements;
		pageNumber++;

		onLoadUsersWithLimits();

		getView().getStatusBar().getResultSetFirstButton().enable();
		getView().getStatusBar().getResultSetPreviousButton().enable();

	}

	public void onResultSetFirstButtonClicked() {

		firstResult = 0;
		pageNumber = 1;

		onLoadUsersWithLimits();

		getView().getStatusBar().getResultSetFirstButton().disable();

	}

	public void onResultSetPreviousButtonClicked() {

		firstResult -= maxResults;
		pageNumber--;

		onLoadUsersWithLimits();

	}

	public void onLoadUsersWithLimits() {

		dispatcher.execute(new UsersAction(
				NameTokens.retrieveByLimitsOpeartion, NameTokens.users,
				getFirstResult(), getMaxResults()),
				new AsyncCallback<UsersResult>() {
					public void onFailure(Throwable caught) {

						getView().setServerResponseError(
								"An Error Occurred: " + caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus() == true) {

							setNumberOfElements(result.getUsers().size());

							if (getPageNumber() == 1) {

								getView().getStatusBar()
										.getResultSetFirstButton().disable();
								getView().getStatusBar()
										.getResultSetPreviousButton().disable();
							}

							// set page number
							String pageNumberLabel = Monitoring.getMessages()
									.page(getPageNumber());
							getView().getStatusBar().getPageNumberLabel()
									.setContents(pageNumberLabel);

							getView().loadAllUsers(result.getUsers());
							UsersManager.getInstance().usersList = result
									.getUsers();

							if (getNumberOfElements() < getMaxResults()) {
								getView().getStatusBar()
										.getResultSetNextButton().disable();
							} else {
								getView().getStatusBar()
										.getResultSetNextButton().enable();
							}
						}

						else {

							getView().setServerResponseError(
									result.getServerResponse());
						}

					}
				});
	}

	public void onLoadNewRoles(User user) {

		dispatcher.execute(new UsersAction(
				NameTokens.retrieveNewRolesOperation, user, NameTokens.roles),
				new AsyncCallback<UsersResult>() {
					public void onFailure(Throwable caught) {

						getView().setServerResponse(
								"An Error Occurred: " + caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus()) {

							getView().loadNewRoles(result.getRoles());

						} else {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}
				});
	}

	public void onAddUserRolesButtonClicked(final User user, List<Role> roles) {

		dispatcher.execute(new UsersAction(NameTokens.addRolesOperation,
				NameTokens.users, user, roles),
				new AsyncCallback<UsersResult>() {

					public void onFailure(Throwable caught) {

						caught.printStackTrace();
						getView().setServerResponseError(caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadRoleUser(user);

						} else {

							getView().setServerResponseError(
									result.getServerResponse());

						}
					}

				});
	}

	public void onRemoveUserRolesButtonClicked(final User user, List<Role> roles) {

		dispatcher.execute(new UsersAction(NameTokens.removeRolesOperation,
				NameTokens.users, user, roles),
				new AsyncCallback<UsersResult>() {

					public void onFailure(Throwable caught) {

						caught.printStackTrace();
						getView().setServerResponseError(caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadRoleUser(user);

						} else {

							getView().setServerResponseError(
									result.getServerResponse());

						}
					}

				});

	}

	public void onSearchButtonClicked() {
		getView().getSearchButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				String searchParameter = getView().getSearchText()
						.getValueAsString();
				getView()
						.loadAllUsers(
								UsersManager.getInstance().searchUsers(
										searchParameter));

			}
		});
	}

	public void onCreateSignature(List<String> signatureStrings) {

		dispatcher.execute(new UsersAction(NameTokens.createSignatureOperation,
				NameTokens.users, signatureStrings),
				new AsyncCallback<UsersResult>() {

					public void onFailure(Throwable caught) {

						caught.printStackTrace();
						getView().setServerResponseError(caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());

						} else {

							getView().setServerResponseError(
									result.getServerResponse());

						}
					}

				});

	}

	public void onValidateSignature(String signature) {

		dispatcher.execute(new UsersAction(
				NameTokens.validateSignatureOperation, NameTokens.users,
				signature), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				caught.printStackTrace();
				getView().setServerResponseError(caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());

				} else {

					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});

	}

	public void onCreateSignature(List<String> signatureStrings, User user) {

		dispatcher.execute(new UsersAction(NameTokens.createSignatureOperation,
				NameTokens.users, signatureStrings, user),
				new AsyncCallback<UsersResult>() {

					public void onFailure(Throwable caught) {

						caught.printStackTrace();
						getView().setServerResponseError(caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());

						} else {

							getView().setServerResponseError(
									result.getServerResponse());

						}
					}

				});

	}

	public void onValidateSignature(String signature, User user) {

		dispatcher.execute(new UsersAction(
				NameTokens.validateSignatureOperation, NameTokens.users,
				signature, user), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				caught.printStackTrace();
				getView().setServerResponseError(caught.getMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					getView().setServerResponse(result.getServerResponse());

				} else {

					getView()
							.setServerResponseError(result.getServerResponse());

				}
			}

		});
	}

	public void onLoadDepartments() {

		dispatcher.execute(new OrganizationalUnitsAction(
				NameTokens.departments, NameTokens.retrieveOperation),
				new AsyncCallback<OrganizationalUnitsResult>() {

					public void onFailure(Throwable caught) {

						getView().setServerResponseError(caught.getMessage());

					}

					public void onSuccess(OrganizationalUnitsResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadDepartmentCombo(
									result.getDepartments());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponseError(
									result.getServerResponse());
						}

					}

				});
	}

	public void onLoadUsersByDepartment(Department department) {

		dispatcher.execute(new UsersAction(
				NameTokens.retrieveUsersByDepartmentOperation,
				NameTokens.users, department),
				new AsyncCallback<UsersResult>() {
					public void onFailure(Throwable caught) {

						getView().setServerResponse(
								"An Error Occurred: " + caught.getMessage());

					}

					public void onSuccess(UsersResult result) {

						if (result.isOperationStatus()) {

							getView().loadAllUsers(result.getUsers());

						} else {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}
				});
	}
}
