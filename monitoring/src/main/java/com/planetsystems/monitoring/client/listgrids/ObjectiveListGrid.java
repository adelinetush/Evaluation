package com.planetsystems.monitoring.client.listgrids;

import java.util.List;

import com.planetsystems.monitoring.model.project.Objectives;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ObjectiveListGrid extends ListGrid {
	
	private SpecificObjectiveDetailGrid specificObjectiveList;

	public static String OBJECTIVE_ID = "objectiveId";

	public static String OBJECTIVE_NAME = "specificObjective";


	public static String OBJECTIVE_ID_DISPLAY = "Objective ID";
	public static String OBJECTIVE_NAME_DISPLAY = "Objective";

	ListGridField objectiveId = new ListGridField(
			OBJECTIVE_ID, OBJECTIVE_ID_DISPLAY);

	ListGridField objective = new ListGridField(
			OBJECTIVE_NAME,OBJECTIVE_NAME_DISPLAY);

	

	public ObjectiveListGrid() {
		super();
		specificObjectiveList=new SpecificObjectiveDetailGrid();
		objectiveId.setHidden(true);

		this.setBaseStyle("myBoxedGridCell");
		this.setFields(objectiveId, objective);
		this.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		this.setSelectionType(SelectionStyle.SIMPLE);
		this.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		this.setWrapCells(true);
		this.setFixedRecordHeights(false);
		this.setCanExpandRecords(true);

	}

	@Override
	protected Canvas getExpansionComponent(ListGridRecord record) {
		
		return specificObjectiveList;
	}
	
	

	public SpecificObjectiveDetailGrid getSpecificObjectiveDetailGrid() {
		return specificObjectiveList;
	}

	

	public ListGridRecord addRowData(Objectives objective) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(OBJECTIVE_ID, objective.getId());
		record.setAttribute(OBJECTIVE_NAME,
				objective.getObjectiveName());

		return record;
	}

	public void addRecordsToGrid(List<Objectives> objectiveList) {
		ListGridRecord[] records = new ListGridRecord[objectiveList.size()];
		int row = 0;
		for (Objectives objective : objectiveList) {
			records[row] = addRowData(objective);
			row++;
		}
		this.setData(records);

	}

	public ListGridRecord addObjectiveRowData(Objectives objective) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(OBJECTIVE_ID, objective.getId());
		record.setAttribute(OBJECTIVE_NAME,
				objective.getObjectiveName());

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
	
	

}
