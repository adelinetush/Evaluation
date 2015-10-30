package com.planetsystems.monitoring.client.listgrids;

import java.util.List;

import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProjectsListGrid extends ListGrid {
	
	public static String PROJECT_ID="projectId";

	public static String PROJECT_TITLE = "projectTitle";

	public static String START_DATE = "startDate";

	public static String PROGRESS = "progress";
	
	public static String GOAL_ID = "goalId";

	public static String GOALS = "goals";
	
	public static String OBJECTIVE_ID = "objectiveId";

	public static String OBJECTIVES = "objectives";

	public static String CLOSING_DATE = "closingDate";

	public static String PROJECT_OWNER = "projectOwner";

	public static String PROJECT_TITLE_DISPLAY = "Project Title";
	public static String START_DATE_DISPLAY = "Start Date";
	public static String PROGRESS_DISPLAY = "Progress";
	public static String CLOSING_DATE_DISPLAY = "Closing Date";
	public static String PROJECT_OWNER_DISPLAY = "Project Owner";
	public static String PROJECT_ID_DISPLAY = "Project ID";

	public ProjectsListGrid() {
		super();

		ListGridField projectId = new ListGridField(PROJECT_ID,
				PROJECT_ID_DISPLAY);
		
		ListGridField projectTitle = new ListGridField(PROJECT_TITLE,
				PROJECT_TITLE_DISPLAY);
		projectId.setHidden(true);

		ListGridField startDate = new ListGridField(START_DATE,
				START_DATE_DISPLAY);

		ListGridField progress = new ListGridField(PROGRESS, PROGRESS_DISPLAY);

		ListGridField closingDate = new ListGridField(CLOSING_DATE,
				CLOSING_DATE_DISPLAY);

		ListGridField projectOwner = new ListGridField(PROJECT_OWNER,
				PROJECT_OWNER_DISPLAY);

		this.setBaseStyle("myBoxedGridCell");
		this.setFields(projectId,projectTitle, startDate, progress, closingDate,
				closingDate, projectOwner);
		this.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		this.setSelectionType(SelectionStyle.SIMPLE);
		this.setWrapCells(true);
		this.setFixedRecordHeights(false);
		

	}

	@Override  
    protected Canvas getExpansionComponent(ListGridRecord record) {  
        VLayout layout = new VLayout(5);  
        layout.setPadding(5);  

        final ListGrid childGrid =new ListGrid();  
        childGrid.setHeight(224);  
        childGrid.setCellHeight(22);  
      

        childGrid.setCanEdit(true);  
        childGrid.setModalEditing(true);  
        childGrid.setEditEvent(ListGridEditEvent.CLICK);  
        childGrid.setListEndEditAction(RowEndEditAction.NEXT);  
        childGrid.setAutoSaveEdits(false);  

        layout.addMember(childGrid);  

      

        return layout;  
    }  
  
	public ListGridRecord addRowData(ProjectTitle project) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(PROJECT_ID, project.getId());
		record.setAttribute(PROJECT_TITLE, project.getProjectTitle());
		record.setAttribute(PROJECT_TITLE, project.getProjectTitle());


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
	
	public ListGridRecord addObjectiveRowData(Objectives objective) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(OBJECTIVE_ID, objective.getId());
		record.setAttribute(OBJECTIVES, objective.getObjectiveName());
	

		return record;
	}
	
	public void addObjectiveRecordsToGrid(List<Objectives> objectiveList) {
		ListGridRecord[] records = new ListGridRecord[objectiveList.size()];
		int row = 0;
		for (Objectives obj : objectiveList) {
			records[row] = addObjectiveRowData(obj);
			row++;
		}
		this.setData(records);

	}
	
	public ListGridRecord addGoalsRowData(Goals goal) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(GOAL_ID, goal.getId());
		record.setAttribute(GOALS, goal.getGoalName());
	

		return record;
	}
	
	public void addGoalsRecordsToGrid(List<Goals> goalList) {
		ListGridRecord[] records = new ListGridRecord[goalList.size()];
		int row = 0;
		for (Goals obj : goalList) {
			records[row] = addGoalsRowData(obj);
			row++;
		}
		this.setData(records);

	}

	

}
