package com.planetsystems.monitoring.client.widgets;

import com.planetsystems.monitoring.client.views.project.DocumentsViewTreeGrid;

import com.planetsystems.monitoring.client.views.project.ProjectViewTreeGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;


public class MainContextPane extends VLayout {

	private static final String WEST_WIDTH = "70%";

	public static VLayout workspace;
	
	public  DocumentsViewTreeGrid documentsGrid;
	
	public ProjectViewTreeGrid projectsGrid;

	public MainContextPane() {
		super();

		this.setStyleName("crm-NavigationPane");
		this.setWidth(WEST_WIDTH);

		workspace = new VLayout();
		workspace.setWidth100();
		workspace.setHeight("50%");
		workspace.setTitle("Documents");

		documentsGrid=new DocumentsViewTreeGrid();
		documentsGrid.setHeight("20%");
		documentsGrid.setWidth100();
		documentsGrid.setTitle("Documents");
		documentsGrid.updateData();
		
		
		projectsGrid=new ProjectViewTreeGrid();
		projectsGrid.setHeight("15%");
		projectsGrid.setWidth100();
		projectsGrid.setTitle("Main Window");
		this.addMember(workspace);
		this.addMember(documentsGrid);
		this.addMember(projectsGrid);
		
		

	}

	public  VLayout getWorkspace() {
		return workspace;
	}

	public  TreeGrid getDocumentsGrid() {
		return documentsGrid;
	}

	public  ProjectViewTreeGrid getProjectsGrid() {
		return projectsGrid;
	}
	

}
