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
public class AccountCategoryListGrid extends ListGrid{

	
	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	public static final String ACCOUNT_CATEGORY_ID= "accountCategoryID";
	private static final String ACCOUNT_CATEGORY_ID_DISPLAY= "Category ID";
	public static final String ACCOUNT_CATEGORY_CODE= "accountCategoryCode";
	private static final String ACCOUNT_CATEGORY_CODE_DISPLAY= "Category Code";
	public static final String ACCOUNT_CATEGORY_NAME= "accountCategoryName";
	private static final String ACCOUNT_CATEGORY_NAME_DISPLAY= "Category Name";
	public static final String ACCOUNT_CATEGORY_DESCRIPTION= "categoryDescription";
	private static final String ACCOUNT_CATEGORY_DESCRIPTION_DISPLAY= "Description ";
	
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/48/";
	private static final String URL_SUFFIX = ".png";
	
	public static final String CREATED_BY= "createdBy";
	private static final String CREATED_BY_DISPLAY= "Created By";
	public static final String CHANGED_BY= "changedBy";
	private static final String CHANGED_BY_DISPLAY= "ChangedBy";
	
	private static final int ICON_COLUMN_LENGTH = 27;
	
	public AccountCategoryListGrid(){
		
		super();
		
		 // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX);
	    
	    ListGridField accountCategoryIDField = new ListGridField(ACCOUNT_CATEGORY_ID, ACCOUNT_CATEGORY_ID_DISPLAY);  
	    accountCategoryIDField.setHidden(true);
	    
	    ListGridField accountCategoryNameField = new ListGridField(ACCOUNT_CATEGORY_NAME, ACCOUNT_CATEGORY_NAME_DISPLAY); 
	    accountCategoryNameField.setWidth("25%");
	    
	    ListGridField accountCategoryCodeField = new ListGridField(ACCOUNT_CATEGORY_CODE, ACCOUNT_CATEGORY_CODE_DISPLAY);  
	    accountCategoryCodeField.setWidth("20%");
	    
	    ListGridField accountCategoryDescField = new ListGridField(ACCOUNT_CATEGORY_DESCRIPTION, ACCOUNT_CATEGORY_DESCRIPTION_DISPLAY);  
	    accountCategoryDescField.setWidth("25%");
	    
	    ListGridField createdByField = new ListGridField(CREATED_BY, CREATED_BY_DISPLAY); 
		createdByField.setWidth("25%");
		ListGridField changedByField = new ListGridField(CHANGED_BY, CHANGED_BY_DISPLAY); 
		changedByField.setWidth("25%");
		
		  this.setShowRowNumbers(true);
		this.setWrapCells(true);
		 this.setCanPickFields(false);
	    this.setFields(iconField,accountCategoryIDField,accountCategoryNameField,accountCategoryCodeField,
	    		accountCategoryDescField,createdByField,changedByField);
	    
	}
	
}
