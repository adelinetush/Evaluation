package com.planetsystems.monitoring.client.views.project;

import com.google.gwt.user.client.ui.Widget;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.presenters.project.NewProjectPresenter;
import com.planetsystems.monitoring.client.views.handlers.TaskUiHandlers;
import com.planetsystems.monitoring.client.widgets.buttons.PreviewButton;
import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class NewProjectView extends ViewWithUiHandlers<TaskUiHandlers>
		implements NewProjectPresenter.MyView {

	private HLayout layout;

	private ProjectDetailsDynamicForm projectDetails;
	private ProjectObjectivesInterface projectObjectives;

	private ProjectSpecificObjectivesInterface projectSpecificObjectives;

	private Button saveButton;
	private Button previewButton;
	
 private TreeGrid specificObjectivesGrid,objectivesGrid;



	@Inject
	public NewProjectView() {

		projectDetails = new ProjectDetailsDynamicForm();

		projectObjectives = new ProjectObjectivesInterface();

		projectSpecificObjectives = new ProjectSpecificObjectivesInterface();
		

		final SectionStack sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setWidth("90%");
	

		SectionStackSection section1 = new SectionStackSection("Project Details");
		section1.setExpanded(true);
		section1.setCanCollapse(true);
		section1.addItem(projectDetails);
		sectionStack.addSection(section1);

		SectionStackSection section2 = new SectionStackSection("Project Objectives");
		section2.setExpanded(true);
		section2.setCanCollapse(true);
		section2.addItem(projectObjectives);
		objectivesGrid = new TreeGridInterface();
		section2.addItem(objectivesGrid);
		sectionStack.addSection(section2);

		SectionStackSection section3 = new SectionStackSection("Project Specific Objectives");
		section3.setExpanded(true);
		section3.setCanCollapse(true);
		section3.addItem(projectSpecificObjectives);
		projectSpecificObjectives.setHeight("1%");

		specificObjectivesGrid = new TreeGridInterface();

		section3.addItem(specificObjectivesGrid);

		sectionStack.addSection(section3);

		saveButton = new SaveButton("Save Project");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sectionStack.expandSection(0);
			}
		});

		previewButton = new PreviewButton("Preview");

		previewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sectionStack.collapseSection(0);
			}
		});

		layout = new HLayout();
		layout.setMembersMargin(20);
		layout.addMember(sectionStack);

		VLayout buttons = new VLayout();
		buttons.setMembersMargin(10);
		buttons.addMember(saveButton);
		buttons.addMember(previewButton);

		layout.addMember(buttons);
		
	}

	public Widget asWidget() {
		return layout;
	}

	public HLayout getLayout() {
		return layout;
	}

	public ProjectDetailsDynamicForm getProjectDetails() {
		return projectDetails;
	}

	public ProjectObjectivesInterface getProjectObjectives() {
		return projectObjectives;
	}

	public ProjectSpecificObjectivesInterface getProjectSpecificObjectives() {
		return projectSpecificObjectives;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public TreeGrid getSpecificObjectivesGrid() {
		return specificObjectivesGrid;
	}



	public TreeGrid getObjectivesGrid() {
		return objectivesGrid;
	}

	
	
	public Button getPreviewButton() {
		return previewButton;
	}

}
