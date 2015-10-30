package com.planetsystems.monitoring.client.views.project;

import com.google.gwt.user.client.ui.Widget;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.planetsystems.monitoring.client.widgets.ProjectProperties;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.planetsystems.monitoring.client.listgrids.ObjectiveListGrid;
import com.planetsystems.monitoring.client.listgrids.SpecificObjectiveGrid;
import com.planetsystems.monitoring.client.presenters.project.ProjectDashboardPresenter;



public class ProjectDashboardView extends ViewImpl implements
		ProjectDashboardPresenter.MyView {

	private  Label titleLabel=new Label();

	private HLayout widget;

	private TabSet tabs;

	private Tab detailsTab, specificTab, activitiesTab, teamTab;
	
	public ObjectiveListGrid objectiveList;
	
	public ProjectProperties projectProperties;
	
	public ProjectTeamView projectTeam;
	
	private SpecificObjectiveGrid specificObjectiveList;
	
	
	
	@Inject
	public ProjectDashboardView() {
		widget = new HLayout();

		VLayout vlayout = new VLayout();

		tabs = new TabSet();
		tabs.setTabBarPosition(Side.TOP);
		tabs.setTabBarAlign(Side.LEFT);
		tabs.setWidth100();
		tabs.setHeight100();
		
		
		detailsTab = new Tab("Project Details");
		
		projectProperties=new ProjectProperties();
		detailsTab.setPane(projectProperties);
		tabs.addTab(detailsTab);
		
		specificTab = new Tab("Specific Objectives");
		objectiveList=new ObjectiveListGrid();
		specificTab.setPane(objectiveList);
		
		tabs.addTab(specificTab);
		
		

		activitiesTab = new Tab("Activities");
		specificObjectiveList=new SpecificObjectiveGrid();
		activitiesTab.setPane(specificObjectiveList);
		
		tabs.addTab(activitiesTab);
		
		teamTab = new Tab("Team");
		projectTeam=new ProjectTeamView();
		teamTab.setPane(projectTeam);
		tabs.addTab(teamTab);
		
		 titleLabel = new Label();
		 titleLabel.setWidth(300);
		 titleLabel.setHeight(30);
		 titleLabel.setAlign(Alignment.CENTER);
		
		vlayout.addMember(titleLabel);
		vlayout.addMember(tabs);
		widget.addMember(vlayout);

	}

	public Widget asWidget() {
		return widget;
	}

	public ObjectiveListGrid getObjectiveListGrid() {
		return objectiveList;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public ProjectProperties getProjectProperties() {
		return projectProperties;
	}

	public ProjectTeamView getProjectTeam() {
		return projectTeam;
	}

	public SpecificObjectiveGrid getSpecificObjectiveList() {
		return specificObjectiveList;
	}

	
	
	
}
