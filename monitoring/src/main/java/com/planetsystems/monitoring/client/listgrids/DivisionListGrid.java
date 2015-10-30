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
public class DivisionListGrid extends ListGrid{

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	private static final String DIVISION_CODE="divisionCode";
	private static final String DIVISION_CODE_DISPLAY="Division Code";
	private static final String DIVISION_NAME="divisionName";
	private static final String DIVISION_NAME_DISPLAY="Division Name";
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	
	private static final int ICON_COLUMN_LENGTH = 27;

	public DivisionListGrid(){
		super();
		
		
		// initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX);
	    
	    ListGridField divisionCodeField = new ListGridField(DIVISION_CODE, DIVISION_CODE_DISPLAY);  
	    divisionCodeField.setWidth("30%");
	    
	    ListGridField divisionNameField = new ListGridField(DIVISION_NAME, DIVISION_NAME_DISPLAY);  
	    divisionNameField.setWidth("30%");
	    ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);
	    emptyField.setWidth("*");
	    
	    this.setFields(iconField,divisionCodeField,divisionNameField,emptyField);
	}
}
