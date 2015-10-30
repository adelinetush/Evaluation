package com.planetsystems.monitoring.client.listgrids;

import java.util.List;

import com.planetsystems.monitoring.client.widgets.buttons.DeleteButton;
import com.planetsystems.monitoring.client.widgets.buttons.EditButton;
import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.planetsystems.monitoring.model.project.Activities;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ActivityListGrid extends VLayout {

	public static String ACTIVITY_ID = "activityId";

	public static String ACTIVITY_NAME = "activityName";

	public static String START_DATE = "startDate";

	public static String END_DATE = "endDate";

	public static String PROGRESS = "progress";

	public static String NUMBER_OF_TASKS = "numberOfTasks";

	public static String ACTIVITY_ID_DISPLAY = "Progress";
	public static String ACTIVITY_NAME_DISPLAY = "Activity";
	public static String START_DATE_DISPLAY = "Start Date";
	public static String END_DATE_DISPLAY = "End Date";
	public static String PROGRESS_DISPLAY = "Progress";
	public static String NUMBER_OF_TASKS_DISPLAY = "Number of Tasks";

	private ListGrid activityGrid;
	
	private Button addActivityButton,deleteActivityButton,editActivityButton,addKpiButton;

	public ActivityListGrid() {
		super();
		setPadding(5);
		setMembersMargin(5);

		ListGridField activityId = new ListGridField(ACTIVITY_ID,
				ACTIVITY_ID_DISPLAY);
		activityId.setHidden(true);

		ListGridField activityName = new ListGridField(ACTIVITY_NAME,
				ACTIVITY_NAME_DISPLAY);

		ListGridField startDate = new ListGridField(START_DATE,
				START_DATE_DISPLAY);

		ListGridField endDate = new ListGridField(END_DATE, END_DATE_DISPLAY);

		ListGridField progress = new ListGridField(PROGRESS, PROGRESS_DISPLAY);

		ListGridField numberOfTasks = new ListGridField(NUMBER_OF_TASKS,
				NUMBER_OF_TASKS_DISPLAY);
		
		

		activityGrid = new ListGrid();
		activityGrid.setHeight(224);
		activityGrid.setCellHeight(22);
		activityGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);

		activityGrid.setFields(activityId, activityName, startDate, endDate,
				progress, numberOfTasks);
		
		//activityGrid.setCanEdit(true);
	//	activityGrid.setModalEditing(true);
		//activityGrid.setEditEvent(ListGridEditEvent.CLICK);
		//activityGrid.setListEndEditAction(RowEndEditAction.NEXT);
		//activityGrid.setAutoSaveEdits(false);
		
		HLayout hlayout=new HLayout();
		addActivityButton=new SaveButton("Add");
		deleteActivityButton=new DeleteButton("Delete");
	    editActivityButton=new EditButton("Edit");
	    addKpiButton=new SaveButton("Add KPI");
		hlayout.setMembers(addActivityButton,deleteActivityButton,editActivityButton,addKpiButton);
        hlayout.setHeight("1%");
        hlayout.setAlign(Alignment.CENTER);
		addMember(activityGrid);
		addMember(hlayout);
		

		
		
	}

	public ListGridRecord addActivityRowData(Activities activity) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(ACTIVITY_ID, activity.getId());
		record.setAttribute(ACTIVITY_NAME, activity.getActivityName());

		return record;
	}

	public void addActivityRecordsToGrid(List<Activities> activityList) {
		ListGridRecord[] records = new ListGridRecord[activityList.size()];
		int row = 0;
		for (Activities act : activityList) {
			records[row] = addActivityRowData(act);
			row++;
		}
		activityGrid.setData(records);

	}
	

	public ListGrid getActivityGrid() {
		return activityGrid;
	}

	public Button getAddActivityButton() {
		return addActivityButton;
	}

	public Button getDeleteActivityButton() {
		return deleteActivityButton;
	}

	public Button getEditActivityButton() {
		return editActivityButton;
	}

	public Button getAddKpiButton() {
		return addKpiButton;
	}
	
	

}
