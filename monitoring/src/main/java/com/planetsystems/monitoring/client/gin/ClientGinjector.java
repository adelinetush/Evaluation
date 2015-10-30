package com.planetsystems.monitoring.client.gin;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.presenters.project.NewProjectPresenter;
import com.planetsystems.monitoring.client.presenters.project.ProjectDashboardPresenter;
import com.planetsystems.monitoring.client.presenters.project.ProjectsListPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.AccountsPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.AuditTrailPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.CurrencyPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.OrganizationalUnitsPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.UsersPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.VotePresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<ProjectManagerPresenter> getMainPresenter();

	AsyncProvider<ProjectsListPresenter> getProjectsListPresenter();

	AsyncProvider<ProjectDashboardPresenter> getProjectDashboardPresenter();
	
	AsyncProvider<NewProjectPresenter> getNewProjectPresenter();

	AsyncProvider<UsersPresenter> getUsersPresenter();

	AsyncProvider<CurrencyPresenter> getCurrencyPresenter();

	AsyncProvider<VotePresenter> getVotePresenter();

	AsyncProvider<AuditTrailPresenter> getAuditTrailPresenter();

	AsyncProvider<AccountsPresenter> getAccountsPresenter();

	AsyncProvider<OrganizationalUnitsPresenter> getOrganizationalUnitsPresenter();

	//AsyncProvider<TasklogPresenter> getTasklogPresenter();

}
