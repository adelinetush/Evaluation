package com.planetsystems.monitoring.client.widgets;

import java.util.List;

import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.smartgwt.client.widgets.viewer.DetailViewerRecord;

public class ProjectProperties extends DetailViewer {

	public static final String PROJECT_NAME = "projectName";
	private static final String PROJECT_OBJECTIVES = "projectObjectives";
	private static final String PROJECT_GOALS = "projectGoals";
	public static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	public static final String PROJECT_MANAGER = "projectManager";
	private static final String PROJECT_OWNER = "projectOwner";

	public static final String PROJECT_NAME_DISPLAY = "Project Name";
	private static final String PROJECT_OBJECTIVES_DISPLAY = "Project Objectives";
	private static final String PROJECT_GOALS_DISPLAY = "Project Goals:";
	public static final String START_DATE_DISPLAY = "Start Date";
	private static final String END_DATE_DISPLAY = "End Date";
	public static final String PROJECT_MANAGER_DISPLAY = "Project Manager";
	private static final String PROJECT_OWNER_DISPLAY = "Project Owner";

	public ProjectProperties() {
		super();
		setWidth100();
		setHeight100();
		setMargin(15);
		getShowEmptyField();

		DetailViewerField projectName = new DetailViewerField(PROJECT_NAME,
				PROJECT_NAME_DISPLAY);

		DetailViewerField projectObjectives = new DetailViewerField(
				PROJECT_OBJECTIVES, PROJECT_OBJECTIVES_DISPLAY);

		DetailViewerField projectGoals = new DetailViewerField(PROJECT_GOALS,
				PROJECT_GOALS_DISPLAY);

		DetailViewerField startDate = new DetailViewerField(START_DATE,
				START_DATE_DISPLAY);

		DetailViewerField endDate = new DetailViewerField(END_DATE,
				END_DATE_DISPLAY);

		DetailViewerField projectManager = new DetailViewerField(
				PROJECT_MANAGER, PROJECT_MANAGER_DISPLAY);

		DetailViewerField projectOwner = new DetailViewerField(PROJECT_OWNER,
				PROJECT_OWNER_DISPLAY);

		setFields(projectName, projectObjectives, projectGoals, startDate,
				endDate, projectManager, projectOwner);

	}

	public DetailViewerRecord addRowData(ProjectTitle project) {
		DetailViewerRecord record = new DetailViewerRecord();

		record.setAttribute(PROJECT_NAME, project.getProjectTitle());
		String objectives = "";
		String goals = "";
		for (int i = 0; i < project.getProjectObjectives().size(); i++) {
			objectives += "\n" + (i + 1) + " "
					+ project.getProjectObjectives().get(i).getObjectiveName();
		}

		for (int i = 0; i < project.getProjectGoals().size(); i++) {
			goals += "\n" + (i + 1) + " "
					+ project.getProjectGoals().get(i).getGoalName();
		}
		record.setAttribute(PROJECT_OBJECTIVES, objectives);

		record.setAttribute(PROJECT_GOALS, goals);
		
		record.setAttribute(START_DATE, project.getDateCreated());
		
		record.setAttribute(START_DATE, project.getDateCreated());
		
		record.setAttribute(END_DATE, project.getDateCreated());
		
		record.setAttribute(END_DATE, project.getDateCreated());
		
		record.setAttribute(PROJECT_MANAGER, project.getDateCreated());
		
		record.setAttribute(PROJECT_OWNER, project.getDateCreated());
		
		

		return record;
	}

	public void addRecordsToViewer(List<ProjectTitle> projectList) {
		DetailViewerRecord[] records = new DetailViewerRecord[projectList
				.size()];
		int row = 0;
		for (ProjectTitle project : projectList) {
			records[row] = addRowData(project);
			row++;
		}
		this.setData(records);
		this.redraw();

	}

}
