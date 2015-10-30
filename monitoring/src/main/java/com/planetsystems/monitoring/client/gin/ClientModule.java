package com.planetsystems.monitoring.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.planetsystems.monitoring.client.place.ClientPlaceManager;
import com.planetsystems.monitoring.client.place.DefaultPlace;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.presenters.ProjectManagerView;
import com.planetsystems.monitoring.client.presenters.project.NewProjectPresenter;
import com.planetsystems.monitoring.client.presenters.project.ProjectDashboardPresenter;
import com.planetsystems.monitoring.client.presenters.project.ProjectsListPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.AccountsPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.AuditTrailPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.CurrencyPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.OrganizationalUnitsPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.UsersPresenter;
import com.planetsystems.monitoring.client.presenters.project.admin.VotePresenter;
import com.planetsystems.monitoring.client.views.project.NewProjectView;
import com.planetsystems.monitoring.client.views.project.ProjectDashboardView;
import com.planetsystems.monitoring.client.views.project.ProjectsListView;
import com.planetsystems.monitoring.client.views.project.admin.AccountsView;
import com.planetsystems.monitoring.client.views.project.admin.AuditTrailView;
import com.planetsystems.monitoring.client.views.project.admin.CurrencyView;
import com.planetsystems.monitoring.client.views.project.admin.OrganizationalUnitsView;
import com.planetsystems.monitoring.client.views.project.admin.UsersView;
import com.planetsystems.monitoring.client.views.project.admin.VoteView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindConstant().annotatedWith(DefaultPlace.class).to(
				NameTokens.projectmanager);

		bindPresenter(ProjectManagerPresenter.class,
				ProjectManagerPresenter.MyView.class, ProjectManagerView.class,
				ProjectManagerPresenter.MyProxy.class);

		bindPresenter(NewProjectPresenter.class,
				NewProjectPresenter.MyView.class, NewProjectView.class,
				NewProjectPresenter.MyProxy.class);

		bindPresenter(ProjectsListPresenter.class,
				ProjectsListPresenter.MyView.class, ProjectsListView.class,
				ProjectsListPresenter.MyProxy.class);

		bindPresenter(UsersPresenter.class, UsersPresenter.MyView.class,
				UsersView.class, UsersPresenter.MyProxy.class);

		bindPresenter(CurrencyPresenter.class, CurrencyPresenter.MyView.class,
				CurrencyView.class, CurrencyPresenter.MyProxy.class);

		bindPresenter(VotePresenter.class, VotePresenter.MyView.class,
				VoteView.class, VotePresenter.MyProxy.class);

		bindPresenter(AuditTrailPresenter.class,
				AuditTrailPresenter.MyView.class, AuditTrailView.class,
				AuditTrailPresenter.MyProxy.class);

		bindPresenter(OrganizationalUnitsPresenter.class,
				OrganizationalUnitsPresenter.MyView.class,
				OrganizationalUnitsView.class,
				OrganizationalUnitsPresenter.MyProxy.class);

		bindPresenter(AccountsPresenter.class, AccountsPresenter.MyView.class,
				AccountsView.class, AccountsPresenter.MyProxy.class);
		

//		// bindPresenter(TasklogPresenter.class, TasklogPresenter.MyView.class,
//		// Tasklogview.class, TasklogPresenter.MyProxy.class);

		bindPresenter(ProjectDashboardPresenter.class,
				ProjectDashboardPresenter.MyView.class,
				ProjectDashboardView.class,
				ProjectDashboardPresenter.MyProxy.class);
	}
}
