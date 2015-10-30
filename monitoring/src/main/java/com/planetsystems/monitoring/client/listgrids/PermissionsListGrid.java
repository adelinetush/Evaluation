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
public class PermissionsListGrid extends ListGrid{

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	private static final String PERMISSION_ID="permissionID";
	private static final String PERMISSION_ID_DISPLAY="Permission ID";
	private static final String PERMISSION_NAME="permissionName";
	private static final String PERMISSION_NAME_DISPLAY="Permission Name";
	private static final String PERMISSION_DESCRIPTION="permissionDescription";
	private static final String PERMISSION_DESCRIPTION_DISPLAY="Permission Description";

	
	
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	
	 private static final int ICON_COLUMN_LENGTH = 27; 
	  private static final int PERMISSION_ID_COLUMN_LENGTH = 0;
	   
	  public PermissionsListGrid(){
		  super();
		  
		  // set check box selection
		  this.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		  
		  // List Grid Fields
		  
		    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
		    iconField.setImageSize(16); 
		    iconField.setAlign(Alignment.CENTER);
		    iconField.setType(ListGridFieldType.IMAGE);  
		    iconField.setImageURLPrefix(URL_PREFIX);  
		    iconField.setImageURLSuffix(URL_SUFFIX);
		    
		  ListGridField permissionIDField = new ListGridField(PERMISSION_ID, PERMISSION_ID_DISPLAY,
		    		PERMISSION_ID_COLUMN_LENGTH);
		  permissionIDField.setHidden(true);
		  
		  ListGridField permissionNameField = new ListGridField(PERMISSION_NAME, PERMISSION_NAME_DISPLAY);
		  permissionNameField.setWidth("50%");
		  ListGridField permissionDescriptionField = new ListGridField(PERMISSION_DESCRIPTION, PERMISSION_DESCRIPTION_DISPLAY);
		  permissionNameField.setWidth("50%");
		  ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);
		  emptyField.setWidth("*");
		  
		  this.setFields(iconField,permissionIDField, permissionNameField,permissionDescriptionField);
		 
	  }
	
	
}
