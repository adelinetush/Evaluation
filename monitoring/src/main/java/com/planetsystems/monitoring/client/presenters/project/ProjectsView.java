package com.planetsystems.monitoring.client.presenters.project;

import com.google.gwt.user.client.ui.Widget;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.views.handlers.TaskUiHandlers;
import com.smartgwt.client.widgets.layout.VLayout;



public class ProjectsView extends ViewWithUiHandlers<TaskUiHandlers> implements ProjectsPresenter.MyView {

	private VLayout widget;

	
	//private ProjectPane projectPane;
	
	

	@Inject
	public ProjectsView() {
		widget = new VLayout();
	//	projectPane=new ProjectPane();

//		projectsGrid = new ProjectsListGrid(){
//			  @Override  
//	            protected Canvas getExpansionComponent(final ListGridRecord record) { 
//			 detailView=new ProjectDetailView();
//				  detailView.setWidth100();
//				  detailView.setHeight("50%");
//	  
//	                return detailView;  
//	            }  
//	        };  
	  
		

//		projectsGrid.setWidth("90%");
//		projectsGrid.setHeight100();
		widget.setBackgroundColor("blue");

	}



	public Widget asWidget() {
		return widget;
	}

	

//	public ProjectPane getProjectPane() {
//		return projectPane;
//	}
//
//	public void setProjectPane(ProjectPane projectPane) {
//		this.projectPane = projectPane;
//	}
}
