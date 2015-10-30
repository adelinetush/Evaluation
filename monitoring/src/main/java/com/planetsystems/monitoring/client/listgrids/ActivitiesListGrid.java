/**
 * 
 */
package com.planetsystems.monitoring.client.listgrids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Planet Developer 001
 *
 */
public class ActivitiesListGrid extends ListGrid{

	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	public static final String ACCOUNT_ID= "accountID";
	private static final String ACCOUNT_ID_DISPLAY= "Account ID";
	public static final String ACCOUNT_CODE= "accountCode";
	private static final String ACCOUNT_CODE_DISPLAY= "Account Code";
	public static final String ACCOUNT_NAME= "accountName";
	private static final String ACCOUNT_NAME_DISPLAY= "Account Name";
	public static final String ACTIVITY_ID= "activityID";
	private static final String ACTIVITY_ID_DISPLAY= "Activity ID";
	public static final String ACTIVITY_CODE= "activityCode";
	private static final String ACTIVITY_CODE_DISPLAY= "Activity Code";
	public static final String ACTIVITY_NAME= "activityName";
	private static final String ACTIVITY_NAME_DISPLAY= "Activity Name";
	public static final String ACCOUNT_CATEGORY_ID= "accountCategoryID";
	private static final String ACCOUNT_CATEGORY_ID_DISPLAY= "Category ID";
	public static final String ACCOUNT_CATEGORY_NAME= "accountCategoryName";
	private static final String ACCOUNT_CATEGORY_NAME_DISPLAY= "Category Name";
	public static final String ACTIVITY_DESCRIPTION= "activityDescription";
	private static final String ACTIVITY_DESCRIPTION_DISPLAY= "Description";
	
	public static final String DEPARTMENT_ID= "departmentID";
	private static final String DEPARTMENT_ID_DISPLAY= "Department ID";
	public static final String DEPARTMENT_CODE= "departmentCode";
	private static final String DEPARTMENT_CODE_DISPLAY= "Department Code";
	public static final String DEPARTMENT_NAME= "departmentName";
	private static final String DEPARTMENT_NAME_DISPLAY= "Department Name";
	 
	private static final String URL_PREFIX = "icons/48/";
	private static final String URL_SUFFIX = ".png";
	
	public static final String CREATED_BY= "createdBy";
	private static final String CREATED_BY_DISPLAY= "Created By";
	public static final String CHANGED_BY= "changedBy";
	private static final String CHANGED_BY_DISPLAY= "ChangedBy";
	
	private static final int ICON_COLUMN_LENGTH = 27;
	
	public ActivitiesListGrid(){
		
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
	    ListGridField departmentCodeField = new ListGridField(DEPARTMENT_CODE, DEPARTMENT_CODE_DISPLAY);  
	    departmentCodeField.setWidth("10%");
	    ListGridField departmentNameField = new ListGridField(DEPARTMENT_NAME, DEPARTMENT_NAME_DISPLAY);  
	    departmentNameField .setWidth("20%");
	    
	    ListGridField accountIDField = new ListGridField(ACCOUNT_ID, ACCOUNT_ID_DISPLAY);  
	    accountIDField.setHidden(true);
	    
	    ListGridField accountNameField = new ListGridField(ACCOUNT_NAME, ACCOUNT_NAME_DISPLAY); 
	    accountNameField.setWidth("10%");
	    
	    ListGridField accountCategoryIDField = new ListGridField(ACCOUNT_CATEGORY_ID, ACCOUNT_CATEGORY_ID_DISPLAY);  
	    accountCategoryIDField.setHidden(true);
	    
	    ListGridField accountCategoryNameField = new ListGridField(ACCOUNT_CATEGORY_NAME, ACCOUNT_CATEGORY_NAME_DISPLAY); 
	    accountCategoryNameField.setHidden(true);
	    
	    ListGridField accountCodeField = new ListGridField(ACCOUNT_CODE, ACCOUNT_CODE_DISPLAY); 
	    accountCodeField.setWidth("10%");
	    
	    ListGridField activityIDField = new ListGridField(ACTIVITY_ID, ACTIVITY_ID_DISPLAY);  
	    activityIDField.setHidden(true);
	    
	    ListGridField activityNameField = new ListGridField(ACTIVITY_NAME, ACTIVITY_NAME_DISPLAY); 
	    activityNameField.setWidth("15%");
	    
	    ListGridField activityCodeField = new ListGridField(ACTIVITY_CODE, ACTIVITY_CODE_DISPLAY); 
	    activityCodeField.setWidth("10%");
	    
	    ListGridField createdByField = new ListGridField(CREATED_BY, CREATED_BY_DISPLAY); 
		createdByField.setWidth("15%");
		ListGridField changedByField = new ListGridField(CHANGED_BY, CHANGED_BY_DISPLAY); 
		changedByField.setWidth("15%");
		
		 ListGridField activityDescriptionField = new ListGridField(ACTIVITY_DESCRIPTION, ACTIVITY_DESCRIPTION_DISPLAY); 
		 activityDescriptionField.setWidth("20%");
		    
		  this.setShowRowNumbers(true);
		this.setWrapCells(true);
		 this.setCanPickFields(false);
	    this.setFields(iconField, departmentIDField,activityIDField,departmentCodeField,departmentNameField,activityNameField
	    		,activityCodeField,activityDescriptionField,createdByField,changedByField);
	this.setSelectionAppearance(SelectionAppearance.CHECKBOX);
	
	}
}
