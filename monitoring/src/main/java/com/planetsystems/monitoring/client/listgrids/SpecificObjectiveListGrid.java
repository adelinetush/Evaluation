package com.planetsystems.monitoring.client.listgrids;

import java.util.List;

import com.planetsystems.monitoring.model.project.SpecificObjectives;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class SpecificObjectiveListGrid extends ListGrid {
	
	private ActivityListGrid activityList;

	public static String SPECIFIC_OBJECTIVE_ID = "specificObjectiveId";

	public static String SPECIFIC_OBJECTIVE_NAME = "specificObjective";


	public static String SPECIFIC_OBJECTIVE_ID_DISPLAY = "Specific Objective ID";
	public static String SPECIFIC_OBJECTIVE_NAME_DISPLAY = "Specific Objective";

	ListGridField specificObjectiveId = new ListGridField(
			SPECIFIC_OBJECTIVE_ID, SPECIFIC_OBJECTIVE_ID_DISPLAY);

	ListGridField specificObjective = new ListGridField(
			SPECIFIC_OBJECTIVE_NAME, SPECIFIC_OBJECTIVE_NAME_DISPLAY);

	

	public SpecificObjectiveListGrid() {
		super();
		activityList=new ActivityListGrid();
		specificObjectiveId.setHidden(true);

		this.setBaseStyle("myBoxedGridCell");
		this.setFields(specificObjectiveId, specificObjective);
		this.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		this.setSelectionType(SelectionStyle.SIMPLE);
		this.setWrapCells(true);
		this.setFixedRecordHeights(false);
		this.setCanExpandRecords(true);

	}

	@Override
	protected Canvas getExpansionComponent(ListGridRecord record) {
		
		return activityList;
	}
	
	

	public ActivityListGrid getActivityList() {
		return activityList;
	}

	

	public ListGridRecord addRowData(SpecificObjectives objective) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(SPECIFIC_OBJECTIVE_ID, objective.getId());
		record.setAttribute(SPECIFIC_OBJECTIVE_NAME,
				objective.getSpecificObjective());

		return record;
	}

	public void addRecordsToGrid(List<SpecificObjectives> objectiveList) {
		ListGridRecord[] records = new ListGridRecord[objectiveList.size()];
		int row = 0;
		for (SpecificObjectives objective : objectiveList) {
			records[row] = addRowData(objective);
			row++;
		}
		this.setData(records);

	}

	public ListGridRecord addObjectiveRowData(SpecificObjectives objective) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(SPECIFIC_OBJECTIVE_ID, objective.getId());
		record.setAttribute(SPECIFIC_OBJECTIVE_NAME,
				objective.getSpecificObjective());

		return record;
	}
	
	

	public void addObjectiveRecordsToGrid(List<SpecificObjectives> objectiveList) {
		ListGridRecord[] records = new ListGridRecord[objectiveList.size()];
		int row = 0;
		for (SpecificObjectives obj : objectiveList) {
			records[row] = addObjectiveRowData(obj);
			row++;
		}
		this.setData(records);

	}
	
	

}
