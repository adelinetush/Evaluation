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
import com.planetsystems.monitoring.client.events.OrganizationalUnitsUiHandlers;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.widgets.ProcnetSelectItem;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsResult;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsResult;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;


public class OrganizationalUnitsPresenter extends Presenter<OrganizationalUnitsPresenter.MyView, OrganizationalUnitsPresenter.MyProxy> implements OrganizationalUnitsUiHandlers {

	DispatchAsync dispatcher;

	@Inject
	public OrganizationalUnitsPresenter(EventBus eventBus, MyView view, MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);

		this.dispatcher = dispatcher;
	}

	
	@ProxyCodeSplit
	@NameToken(NameTokens.organizationalUnits)
	public interface MyProxy extends Proxy<OrganizationalUnitsPresenter>, Place {

	}

	
	public interface MyView extends View, HasUiHandlers<OrganizationalUnitsUiHandlers> {

		void setServerResponse(String serverResponse);

		void loadDepartmentsGrid(List<Department> departments);

		void loadDivisionsGrid(List<Division> divisions);

		void loadSectionsGrid(List<Section> sections);

		void loadDepartmentsCombo(List<Department> departments);

		void clearDepartmentForm();

		void clearDivisionForm();

		void clearSectionForm();

		public ProcnetSelectItem getDivisionsCombo();
	}

	@Override
	protected void revealInParent() {

		RevealContentEvent.fire(this, ProjectManagerPresenter.TYPE_SetContextAreaContent, this);

	}

	@Inject
	PlaceManager placeManager;
	protected void onBind() {
		super.onBind();
		retrieveAllDepartmentsButtonClicked();
		retrieveAllDivisionsButtonClicked();
		retrieveAllSectionsButtonClicked();
		loadDivisionsCombo();
	}

	protected void onReset() {

		super.onReset();
		retrieveAllSectionsButtonClicked();
		retrieveAllDepartmentsButtonClicked();
		retrieveAllDivisionsButtonClicked();
	}

	public void saveDepartmentButtonClicked(Department department) {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.departments, NameTokens.saveOperation, department), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearDepartmentForm();
					retrieveAllDepartmentsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});

	}

	public void editDepartmentButtonClicked(Department department) {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.departments, NameTokens.editOperation, department), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearDepartmentForm();
					retrieveAllDepartmentsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});

	}

	public void deleteDepartmentButtonClicked(Department department) {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.departments, NameTokens.deleteOperation, department), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearDepartmentForm();
					retrieveAllDepartmentsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void retrieveAllDepartmentsButtonClicked() {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.departments, NameTokens.retrieveOperation), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().loadDepartmentsGrid(result.getDepartments());
					getView().loadDepartmentsCombo(result.getDepartments());

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void saveDivisionButtonClicked(Division division) {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.divisions, NameTokens.saveOperation, division), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearDivisionForm();
					retrieveAllDivisionsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void editDivisionButtonClicked(Division division) {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.divisions, NameTokens.editOperation, division), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearDivisionForm();
					retrieveAllDivisionsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void deleteDivisionButtonClicked(Division division) {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.divisions, NameTokens.deleteOperation, division), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearDivisionForm();
					retrieveAllDivisionsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});

	}

	public void retrieveAllDivisionsButtonClicked() {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.divisions, NameTokens.retrieveOperation), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().loadDivisionsGrid(result.getDivisions());
					// getView().loadDivisionsCombo(result.getDivisions());

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void saveSectionButtonClicked(Section section) {

		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.sections, NameTokens.saveOperation, section), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearSectionForm();
					retrieveAllSectionsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});

	}

	public void editSectionButtonClicked(Section section) {

		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.sections, NameTokens.editOperation, section), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearSectionForm();
					retrieveAllSectionsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void deleteSectionButtonClicked(Section section) {

		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.sections, NameTokens.deleteOperation, section), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().setServerResponse(result.getServerResponse());
					getView().clearSectionForm();
					retrieveAllSectionsButtonClicked();

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void retrieveAllSectionsButtonClicked() {


		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.sections, NameTokens.retrieveOperation), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());

			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {


					getView().loadSectionsGrid(result.getSections());

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

	public void loadDivisionsCombo() {
		// TODO Auto-generated method stub
		dispatcher.execute(new OrganizationalUnitsAction(NameTokens.divisions, NameTokens.retrieveOperation), new AsyncCallback<OrganizationalUnitsResult>() {

			public void onFailure(Throwable caught) {

				getView().setServerResponse(caught.getMessage());
			}

			public void onSuccess(OrganizationalUnitsResult result) {


				if (result.isOperationStatus() == true) {

					DataSource dataSource = new DataSource();

					DataSourceTextField id = new DataSourceTextField("id", "ID");
					DataSourceTextField code = new DataSourceTextField("code", "Code");
					DataSourceTextField name = new DataSourceTextField("name", "Name");
					List<Division> divisions = result.getDivisions();
					
					dataSource.setClientOnly(true);
					dataSource.setFields(id, code, name);

					ListGridRecord[] list = new ListGridRecord[divisions.size()];
					int listCount = 0;
					ListGridRecord record;
					for (Division division : divisions) {
						record = new ListGridRecord();
						record.setAttribute("id", division.getId());
						record.setAttribute("code", division.getDivisionCode());
						record.setAttribute("name", division.getDivisionName());
						list[listCount] = record;
						listCount++;
					}
					dataSource.setTestData(list);
					getView().getDivisionsCombo().setOptionDataSource(dataSource);

				} else if (result.isOperationStatus() == false) {

					getView().setServerResponse(result.getServerResponse());
				}

			}

		});
	}

}
