/**
 * 
 */
package com.planetsystems.monitoring.client.listgrids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Planet Developer 001
 *
 */
public class DepartmentListGrid extends ListGrid{

	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	public static final String DEPARTMENT_ID= "departmentID";
	private static final String DEPARTMENT_ID_DISPLAY= "Department ID";
	private static final String DIRECTORATE_ID= "directorateID";
	private static final String DIRECTORATE_ID_DISPLAY= "Directorate ID";
	public static final String DEPARTMENT_CODE= "departmentCode";
	private static final String DEPARTMENT_CODE_DISPLAY= "Department Code";
	public static final String DEPARTMENT_NAME= "departmentName";
	private static final String DEPARTMENT_NAME_DISPLAY= "Department Name";
	public static final String DEPARTMENT_DESCRIPTION= "departmentDescription";
	private static final String DEPARTMENT_DESCRIPTION_DISPLAY= "Department Description";
	public static final String DEPARTMENT_CREATE_BY= "createdBy";
	private static final String DEPARTMENT_CREATE_BY_DISPLAY= "Created By";
	public static final String DEPARTMENT_CHANGED_BY= "changedBy";
	private static final String DEPARTMENT_CHANGED_BY_DISPLAY= "ChangedBy";
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	
	private static final int ICON_COLUMN_LENGTH = 27; 


	
	public DepartmentListGrid(){
		super();
		
		  // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX); 
	    
	    ListGridField departmentIDField = new ListGridField(DEPARTMENT_ID, DEPARTMENT_ID_DISPLAY); 
	    departmentIDField.setHidden(true);
	    ListGridField directorateIDField = new ListGridField(DIRECTORATE_ID, DIRECTORATE_ID_DISPLAY);  
	    directorateIDField.setHidden(true);
	    ListGridField departmentCodeField = new ListGridField(DEPARTMENT_CODE, DEPARTMENT_CODE_DISPLAY);  
	    departmentCodeField.setWidth("10%");
	    ListGridField departmentNameField = new ListGridField(DEPARTMENT_NAME, DEPARTMENT_NAME_DISPLAY);  
	    departmentNameField .setWidth("20%");
		ListGridField departmentDescriptionField = new ListGridField(DEPARTMENT_DESCRIPTION, DEPARTMENT_DESCRIPTION_DISPLAY);  
		departmentDescriptionField.setWidth("30%");
		ListGridField createdByField = new ListGridField(DEPARTMENT_CREATE_BY, DEPARTMENT_CREATE_BY_DISPLAY);  
		createdByField.setWidth("10%");
		ListGridField changedByField = new ListGridField(DEPARTMENT_CHANGED_BY, DEPARTMENT_CHANGED_BY_DISPLAY);
		changedByField.setWidth("10%");
		ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);  
		
		this.setFields(iconField,departmentIDField,departmentCodeField,departmentNameField,
				departmentDescriptionField,createdByField,changedByField);
		
		
	    this.setShowRowNumbers(true);
        this.setGroupStartOpen(GroupStartOpen.ALL); 
        this.setCanPickFields(false);
		this.setCanGroupBy(true);
		//this.groupBy(DIRECTORATE_NAME,DIRECTORATE_NAME);
	}
	  
}
