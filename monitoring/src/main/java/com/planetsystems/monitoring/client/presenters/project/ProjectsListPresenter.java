package com.planetsystems.monitoring.client.presenters.project;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.listgrids.ProjectsListGrid;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.utils.Constants;
import com.planetsystems.monitoring.client.views.handlers.TaskUiHandlers;
import com.planetsystems.monitoring.shared.MonitoringAction;
import com.planetsystems.monitoring.shared.MonitoringResult;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

public class ProjectsListPresenter extends
		Presenter<ProjectsListPresenter.MyView, ProjectsListPresenter.MyProxy>
		implements TaskUiHandlers {

	DispatchAsync dispatcher;

	public interface MyView extends View {

		public ProjectsListGrid getProjectsGrid();

	}

	@ProxyCodeSplit
	@NameToken(NameTokens.ongoing_projects)
	public interface MyProxy extends ProxyPlace<ProjectsListPresenter> {
	}

	@Inject
	PlaceManager manager;

	@Inject
	public ProjectsListPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				ProjectManagerPresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		retrieveProjectRecords();

	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
	}

	private void retrieveProjectRecords() {
		dispatcher.execute(new MonitoringAction(Constants.PENDING_PROJECTS),
				new AsyncCallback<MonitoringResult>() {

					public void onFailure(Throwable arg) {
						arg.printStackTrace();
					}

					public void onSuccess(MonitoringResult result) {

						if (result != null) {
							if (result.getProjects() != null) {
								getView().getProjectsGrid().addRecordsToGrid(
										result.getProjects());
								onProjectListItemClick();
							} else {
								SC.say("No Projects");
							}
						} else {
							System.out.println("Result: is null");
						}
					}
				});
	}

	


	public void onProjectListItemClick() {
		getView().getProjectsGrid().addSelectionChangedHandler(new SelectionChangedHandler() {
			
			public void onSelectionChanged(SelectionEvent event) {
			ListGridRecord record=	getView().getProjectsGrid().getSelectedRecord();
						PlaceRequest request=new PlaceRequest(NameTokens.projectdashboard).with("title", record.getAttribute("projectTitle")).with("projectId", record.getAttribute("projectId"));
						manager.revealPlace(request);
			}
		});
	}

}
