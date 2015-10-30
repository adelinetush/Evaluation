package com.planetsystems.monitoring.client.listgrids;


import java.util.List;
import com.planetsystems.monitoring.model.project.Tasks;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TasksListGrid extends VLayout {

	public static String TASK_ID = "taskId";

	public static String TASK_NAME = "taskName";

	public static String START_DATE = "startDate";

	public static String END_DATE = "endDate";

	public static String PROGRESS = "progress";

	public static String STATUS = "status";
	
	public static String RESOURCE = "resource";
	
	public static String COMMENT = "comment";

	public static String TASK_ID_DISPLAY = "Task ID";
	public static String TASK_NAME_DISPLAY = "Task";
	public static String START_DATE_DISPLAY = "Start Date";
	public static String END_DATE_DISPLAY = "End Date";
	public static String PROGRESS_DISPLAY = "Progress";
	public static String STATUS_DISPLAY = "Status";
	public static String RESOURCE_DISPLAY = "Resource";
	public static String COMMENT_DISPLAY = "Comment";

	private ListGrid activityGrid;
	
	private IButton statusButton,progressButton,editTaskButton,deleteTaskButton;

	public TasksListGrid() {
		super();
		setPadding(5);
		setMembersMargin(5);

		ListGridField taskId = new ListGridField(TASK_ID,
				TASK_ID_DISPLAY);
		taskId.setHidden(true);

		ListGridField taskName = new ListGridField(TASK_NAME,
				TASK_NAME_DISPLAY);

		ListGridField startDate = new ListGridField(START_DATE,
				START_DATE_DISPLAY);

		ListGridField endDate = new ListGridField(END_DATE, END_DATE_DISPLAY);

		ListGridField progress = new ListGridField(PROGRESS, PROGRESS_DISPLAY);

		ListGridField status = new ListGridField(STATUS,
				STATUS_DISPLAY);
		ListGridField resource = new ListGridField(RESOURCE,
				RESOURCE_DISPLAY);
		
		ListGridField comment = new ListGridField(COMMENT,
				COMMENT_DISPLAY);
		
		

		activityGrid = new ListGrid();
		activityGrid.setHeight(224);
		activityGrid.setCellHeight(22);

		activityGrid.setFields(taskId, taskName, startDate, endDate,
				progress, status,resource,comment);
		
		//activityGrid.setCanEdit(true);
	//	activityGrid.setModalEditing(true);
		//activityGrid.setEditEvent(ListGridEditEvent.CLICK);
		//activityGrid.setListEndEditAction(RowEndEditAction.NEXT);
		//activityGrid.setAutoSaveEdits(false);
		
		HLayout hlayout=new HLayout();
		statusButton=new IButton("Change Status");
		progressButton=new IButton("Change Progress");
	    editTaskButton=new IButton("Edit Task");
	    deleteTaskButton=new IButton("Delete Task");
		hlayout.setMembers(statusButton,progressButton,editTaskButton,deleteTaskButton);
        hlayout.setHeight("1%");
        hlayout.setAlign(Alignment.CENTER);
		addMember(activityGrid);
		addMember(hlayout);
		

		
		
	}

	public ListGridRecord addTaskRowData(Tasks task) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(TASK_ID, task.getId());
		record.setAttribute(TASK_NAME, task.getTaskName());
		record.setAttribute(START_DATE, task.getId());
		record.setAttribute(END_DATE, task.getTaskName());
		record.setAttribute(PROGRESS, task.getId());
		record.setAttribute(STATUS, task.getTaskName());
		record.setAttribute(RESOURCE, task.getTaskName());

		return record;
	}

	public void addTasksRecordsToGrid(List<Tasks> tasksList) {
		ListGridRecord[] records = new ListGridRecord[tasksList.size()];
		int row = 0;
		for (Tasks task : tasksList) {
			records[row] = addTaskRowData(task);
			row++;
		}
		activityGrid.setData(records);

	}
	

	public ListGrid getActivityGrid() {
		return activityGrid;
	}

	public IButton getStatusButton() {
		return statusButton;
	}

	public IButton getProgressButton() {
		return progressButton;
	}

	public IButton getEditTaskButton() {
		return editTaskButton;
	}

	public IButton getDeleteTaskButton() {
		return deleteTaskButton;
	}

	

}
