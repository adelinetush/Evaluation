package com.planetsystems.monitoring.client.listgrids;

import java.util.List;


import com.planetsystems.monitoring.model.project.SpecificObjectives;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SpecificObjectiveDetailGrid extends VLayout {

	public static String SPECIFIC_OBJECTIVE_ID = "specificObjectiveId";

	public static String SPECIFIC_OBJECTIVE_NAME = "specificObjectiveName";

	public static String START_DATE = "startDate";

	public static String END_DATE = "endDate";

	public static String PROGRESS = "progress";

	public static String NUMBER_OF_TASKS = "numberOfTasks";

	public static String SPECIFIC_OBJECTIVE_NAME_DISPLAY = "Specific Objective";
	public static String SPECIFIC_OBJECTIVE_ID_DISPLAY = "Specific Objective ID";
	public static String START_DATE_DISPLAY = "Start Date";
	public static String END_DATE_DISPLAY = "End Date";
	public static String PROGRESS_DISPLAY = "Progress";
	public static String NUMBER_OF_TASKS_DISPLAY = "Number of Activities";

	private ListGrid specificObjectiveGrid;
	
	private IButton addSpecificObjectiveButton,deleteSpecificObjectiveButton,editSpecificObjectiveButton,addKpiButton;

	public SpecificObjectiveDetailGrid() {
		super();
		setPadding(5);
		setMembersMargin(5);

		ListGridField specificObjectiveId = new ListGridField(SPECIFIC_OBJECTIVE_ID,
				SPECIFIC_OBJECTIVE_ID_DISPLAY);
		specificObjectiveId.setHidden(true);

		ListGridField specificObjectiveName = new ListGridField(SPECIFIC_OBJECTIVE_NAME,
				SPECIFIC_OBJECTIVE_NAME_DISPLAY);

		ListGridField startDate = new ListGridField(START_DATE,
				START_DATE_DISPLAY);

		ListGridField endDate = new ListGridField(END_DATE, END_DATE_DISPLAY);

		ListGridField progress = new ListGridField(PROGRESS, PROGRESS_DISPLAY);

		ListGridField numberOfTasks = new ListGridField(NUMBER_OF_TASKS,
				NUMBER_OF_TASKS_DISPLAY);
		
		

		specificObjectiveGrid = new ListGrid();
		specificObjectiveGrid.setHeight(224);
		specificObjectiveGrid.setCellHeight(22);
		specificObjectiveGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);

		specificObjectiveGrid.setFields(specificObjectiveId, specificObjectiveName, startDate, endDate,
				progress, numberOfTasks);
	//	specificObjectiveGrid.setCanEdit(true);
	//	specificObjectiveGrid.setModalEditing(true);
	//	specificObjectiveGrid.setEditEvent(ListGridEditEvent.CLICK);
		//specificObjectiveGrid.setListEndEditAction(RowEndEditAction.NEXT);
		//specificObjectiveGrid.setAutoSaveEdits(false);
		
		HLayout hlayout=new HLayout();
		addSpecificObjectiveButton=new IButton("Add");
		deleteSpecificObjectiveButton=new IButton("Delete");
	    editSpecificObjectiveButton=new IButton("Edit");
	    addKpiButton=new IButton("Add KPI");
	    
		hlayout.setMembers(addSpecificObjectiveButton,deleteSpecificObjectiveButton,editSpecificObjectiveButton,addKpiButton);
        hlayout.setHeight("1%");
        hlayout.setAlign(Alignment.CENTER);
		addMember(specificObjectiveGrid);
		addMember(hlayout);
		

		
		
	}

	public ListGridRecord addSpecificObjectiveRowData(SpecificObjectives objective) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(SPECIFIC_OBJECTIVE_ID, objective.getId());
		record.setAttribute(SPECIFIC_OBJECTIVE_NAME, objective.getSpecificObjective());

		return record;
	}

	public void addSpecificObjectiveRecordsToGrid(List<SpecificObjectives> objectiveList) {
		ListGridRecord[] records = new ListGridRecord[objectiveList.size()];
		int row = 0;
		for (SpecificObjectives objective : objectiveList) {
			records[row] = addSpecificObjectiveRowData(objective);
			row++;
		}
		specificObjectiveGrid.setData(records);

	}

	public ListGrid getSpecificObjectiveGrid() {
		return specificObjectiveGrid;
	}

	public IButton getAddSpecificObjectiveButton() {
		return addSpecificObjectiveButton;
	}

	public IButton getDeleteSpecificObjectiveButton() {
		return deleteSpecificObjectiveButton;
	}

	public IButton getEditSpecificObjectiveButton() {
		return editSpecificObjectiveButton;
	}

	public IButton getAddKpiButton() {
		return addKpiButton;
	}
	

	
	
	

}
