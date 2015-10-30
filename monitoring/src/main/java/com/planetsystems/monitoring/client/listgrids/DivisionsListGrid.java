/**
 * 
 */
package com.planetsystems.monitoring.client.listgrids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Planet Developer 001
 *
 */
public class DivisionsListGrid extends ListGrid{

	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	public static final String VOTE_ID= "voteID";
	private static final String VOTE_ID_DISPLAY= "Vote ID";
	public static final String VOTE_NAME= "voteName";
	private static final String VOTE_NAME_DISPLAY= "Vote Name";
	public static final String DIVISION_ID= "divisionID";
	private static final String DIVISION_ID_DISPLAY= "Division ID";
	public static final String DIVISION_CODE= "divisionCode";
	private static final String DIVISION_CODE_DISPLAY= "Division Code";
	public static final String DIVISION_NAME= "divisionName";
	private static final String DIVISION_NAME_DISPLAY= "Division Name";
	public static final String DIVISION_DESCRIPTION= "divisionDescription";
	private static final String DIVISION_DESCRIPTION_DISPLAY= "Division Description";
	public static final String DIVISION_CREATE_BY= "createdBy";
	private static final String DIVISION_CREATE_BY_DISPLAY= "Created By";
	public static final String DIVISION_CHANGED_BY= "changedBy";
	private static final String DIVISION_CHANGED_BY_DISPLAY= "ChangedBy";
	public static final String DEPARTMENT_ID= "departmentID";
	private static final String DEPARTMENT_ID_DISPLAY= "Department ID";
	public static final String DEPARTMENT_NAME= "departmentName";
	private static final String DEPARTMENT_NAME_DISPLAY= "Department Name";
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/48/";
	private static final String URL_SUFFIX = ".png";
	
	private static final int ICON_COLUMN_LENGTH = 27; 

	
	public DivisionsListGrid(){
		super();
		
		  // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX); 
	    
	    //DIRECTORATE_ID
	    ListGridField departmentIDField = new ListGridField(DEPARTMENT_ID, DEPARTMENT_ID_DISPLAY); 
	    departmentIDField.setHidden(true);
	    ListGridField divisionIDField = new ListGridField(DIVISION_ID, DIVISION_ID_DISPLAY);  
	    divisionIDField.setHidden(true);
	    ListGridField voteIDField = new ListGridField(VOTE_ID, VOTE_ID_DISPLAY);  
	    voteIDField.setHidden(true);
	    ListGridField departmentNameField = new ListGridField(DEPARTMENT_NAME, DEPARTMENT_NAME_DISPLAY);  
	    departmentNameField .setWidth("20%");
	    ListGridField divisionNameField = new ListGridField(DIVISION_NAME, DIVISION_NAME_DISPLAY);  
	    divisionNameField.setWidth("20%");
		ListGridField divisionDescriptionField = new ListGridField(DIVISION_DESCRIPTION, DIVISION_DESCRIPTION_DISPLAY);
		divisionDescriptionField.setWidth("20%");
		ListGridField createdByField = new ListGridField(DIVISION_CREATE_BY, DIVISION_CREATE_BY_DISPLAY); 
		createdByField.setWidth("15%");
		ListGridField changedByField = new ListGridField(DIVISION_CHANGED_BY, DIVISION_CHANGED_BY_DISPLAY); 
		changedByField.setWidth("15%");
		ListGridField divisionCodeField = new ListGridField(DIVISION_CODE, DIVISION_CODE_DISPLAY); 
		divisionCodeField.setWidth("15%");
		ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);  
		
		this.setCanPickFields(false);
		this.setShowRowNumbers(true);
		this.setCanPickFields(false);
		this.setCanGroupBy(true);
		this.setGroupByField(DEPARTMENT_NAME);
		this.setFields(iconField,departmentIDField,divisionIDField,departmentNameField,divisionCodeField,divisionNameField,divisionDescriptionField,createdByField,changedByField,emptyField);
	}
	  
	  
}
