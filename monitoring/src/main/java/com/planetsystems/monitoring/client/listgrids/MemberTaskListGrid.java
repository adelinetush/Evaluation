package com.planetsystems.monitoring.client.listgrids;

import com.planetsystems.monitoring.model.project.Tasks;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MemberTaskListGrid extends ListGrid {

;
	public static final String TASK_ID = "taskId";
	private static final String TASK_ID_DISPLAY = "Task ID";
	
	public static final String STATUS = "status";
	private static final String STATUS_DISPLAY = "Status";
	
	public static final String TASK_NAME = "taskName";

	public static final String TASK_NAME_DISPLAY = "Task";
	
	private static final String START_DATE_DISPLAY = "Start Date";

	private static final String START_DATE = "startDate";
	
	
	public static final String END_DATE = "endDate";

	private static final String END_DATE_DISPLAY = "End Date";

	public static final String PROGRESS = "progress";
	
	private static final String PROGRESS_DISPLAY = "Progress";
	
	public static final String SUPERVISOR = "supervisor";
	private static final String SUPERVISOR_DISPLAY = "Supervisor";
	

	private static final String COMMENT = "comment";
	private static final String COMMENT_DISPLAY = "Comment";
	

	public MemberTaskListGrid() {

		super();


		ListGridField taskIdField = new ListGridField(TASK_ID, TASK_ID_DISPLAY);
		taskIdField.setHidden(true);
		

		ListGridField statusField = new ListGridField(STATUS,
				STATUS_DISPLAY);

		ListGridField taskNameField = new ListGridField(TASK_NAME,
				TASK_NAME_DISPLAY);
		taskNameField.setWidth("10%");

		ListGridField startDateField = new ListGridField(START_DATE, START_DATE_DISPLAY);
		startDateField.setWidth("10%");

		ListGridField endDateField = new ListGridField(END_DATE,
				END_DATE_DISPLAY);
		endDateField.setWidth("10%");

		ListGridField progressField = new ListGridField(PROGRESS,
				PROGRESS_DISPLAY);
		progressField.setWidth("10%");

		ListGridField supervisorField = new ListGridField(SUPERVISOR,
				SUPERVISOR_DISPLAY);
		supervisorField.setWidth("10%");
		

		ListGridField commentField = new ListGridField(COMMENT,COMMENT_DISPLAY);
		commentField.setWidth("10%");

		

		this.setShowRowNumbers(true);
		this.setWrapCells(true);
		this.setCanPickFields(false);
		this.setFields(taskIdField, taskNameField, statusField, startDateField,
				endDateField, progressField, supervisorField,
				commentField);
	}

	

	public void addRowToGrid(Tasks task) {
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(TASK_ID, task.getId());
		record.setAttribute(TASK_NAME, task.getTaskName());
		record.setAttribute(STATUS, task.getTaskName());
		record.setAttribute(START_DATE, task);
		record.setAttribute(END_DATE, task.getTaskName());
		record.setAttribute(PROGRESS, task.getTaskName());
		record.setAttribute(SUPERVISOR, task.getTaskName());
		record.setAttribute(COMMENT, task.getTaskName());
		
		this.addData(record);
		this.redraw();

	}

}
