package com.planetsystems.monitoring.client.views.project;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.listgrids.ProjectsListGrid;
import com.planetsystems.monitoring.client.presenters.project.ProjectsListPresenter;
import com.planetsystems.monitoring.client.views.handlers.TaskUiHandlers;
import com.smartgwt.client.widgets.layout.HLayout;



public class ProjectsListView extends ViewWithUiHandlers<TaskUiHandlers> implements ProjectsListPresenter.MyView {

	private HLayout widget;

	private ProjectsListGrid projectsGrid;
	
	

	@Inject
	public ProjectsListView() {
		widget = new HLayout();

		projectsGrid = new ProjectsListGrid();
	  
		projectsGrid.setWidth("90%");
		projectsGrid.setHeight100();
		widget.setBackgroundColor("blue");
		widget.addMember(projectsGrid);

	}

	

	public ProjectsListGrid getProjectsGrid() {
		return projectsGrid;
	}

	public Widget asWidget() {
		return widget;
	}
}
