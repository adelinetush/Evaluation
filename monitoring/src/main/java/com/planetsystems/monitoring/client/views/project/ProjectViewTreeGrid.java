package com.planetsystems.monitoring.client.views.project;

import java.util.List;


import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;


public class ProjectViewTreeGrid extends ListGrid {

	public static String NUMBER = "no";
	public static String PROJECT_NAME = "projectName";
	public static String OWNER = "owner";
	public static String STARTED = "started";
	public static String ENDED = "ended";
	public static String STATUS= "status";
	public static String NEXT_ACTOR = "nextActor";
	public static String PROGRESS = "progress";
	public static String LATEST_COMMENT = "latestComment";
	public static String PROJECT_ID = "projectId";

	public static String NUMBER_DISPLAY = "No";
	public static String PROJECT_NAME_DISPLAY = "Project Name";
	public static String OWNER_DISPLAY = "Owner";
	public static String STARTED_DISPLAY = "Started";
	public static String ENDED_DISPLAY = "Ended";
	public static String STATUS_DISPLAY = "Status";
	public static String NEXT_ACTOR_DISPLAY = "Next Actor";
	public static String LATEST_COMMENT_DISPLAY = "Latest Comment";
	public static String PROGRESS_DISPLAY = "Progress";
	
	public static String PROJECT_ID_DISPLAY = "Project ID";

	public ProjectViewTreeGrid() {
		super();
		

		ListGridField projectId = new ListGridField(PROJECT_ID,
				PROJECT_ID_DISPLAY);
		projectId.setHidden(true);

		ListGridField projectName = new ListGridField(PROJECT_NAME,
				PROJECT_NAME_DISPLAY);
		projectId.setHidden(true);

		ListGridField projectOwner = new ListGridField(OWNER,
				OWNER_DISPLAY);

		ListGridField startDate = new ListGridField(STARTED, STARTED_DISPLAY);

		ListGridField closingDate = new ListGridField(ENDED,
				ENDED_DISPLAY);

		ListGridField projectProgress = new ListGridField(PROGRESS,
				PROGRESS_DISPLAY);
		
		ListGridField status = new ListGridField(STATUS,
				STATUS_DISPLAY);
		
		ListGridField nextActor = new ListGridField(NEXT_ACTOR,
				NEXT_ACTOR_DISPLAY);

		ListGridField latestComment = new ListGridField(LATEST_COMMENT,
				LATEST_COMMENT_DISPLAY);

		this.setBaseStyle("myBoxedGridCell");
		this.setFields(projectId, projectName,status, projectOwner, startDate,
				closingDate, projectProgress, nextActor,latestComment);
		
		this.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		this.setSelectionType(SelectionStyle.SIMPLE);
		this.setWrapCells(true);
		this.setFixedRecordHeights(false);
		this.setCanExpandRecords(false);

	}


	public ListGridRecord addRowData(ProjectTitle project) {
		ListGridRecord record = new ListGridRecord();
		
		record.setAttribute(PROJECT_ID, project.getId());
		record.setAttribute(PROJECT_NAME, project.getProjectTitle());
		record.setAttribute(STATUS, project.getProjectTitle());
		record.setAttribute(OWNER, project.getId());
		record.setAttribute(STARTED, project.getProjectTitle());
		record.setAttribute(ENDED, project.getProjectTitle());
		record.setAttribute(PROGRESS, project.getId());
		record.setAttribute(NEXT_ACTOR, project.getProjectTitle());
		record.setAttribute(LATEST_COMMENT, project.getProjectTitle());

		return record;
	}

	public void addRecordsToGrid(List<ProjectTitle> projectList) {
		ListGridRecord[] records = new ListGridRecord[projectList.size()];
		int row = 0;
		for (ProjectTitle project : projectList) {
			records[row] = addRowData(project);
			row++;
		}
		this.setData(records);

	}

	



}
