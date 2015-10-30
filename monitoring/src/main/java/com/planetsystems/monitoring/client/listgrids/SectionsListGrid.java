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
public class SectionsListGrid extends ListGrid{

	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	private static final String VOTE_ID= "voteID";
	private static final String VOTE_ID_DISPLAY= "Vote ID";
	public static final String SECTION_CODE= "sectionCode";
	private static final String SECTION_CODE_DISPLAY= "Section Code";
	public static final String SECTIONS_ID= "sectionID";
	private static final String SECTIONS_ID_DISPLAY= "Section ID";
	public static final String SECTIONS_NAME= "sectionName";
	private static final String SECTIONS_NAME_DISPLAY= "Section Name";
	public static final String SECTIONS_DESCRIPTION= "sectionDescription";
	private static final String SECTIONS_DESCRIPTION_DISPLAY= "Sections Description";
	public static final String SECTION_CREATE_BY= "createdBy";
	private static final String SECTION_CREATE_BY_DISPLAY= "Created By";
	public static final String SECTION_CHANGED_BY= "changedBy";
	private static final String SECTION_CHANGED_BY_DISPLAY= "ChangedBy";
	public static final String DIVISION_ID= "divisionID";
	private static final String DIVISION_ID_DISPLAY= "Division ID";
	public static final String DIVISION_NAME= "divisionName";
	private static final String DIVISION_NAME_DISPLAY= "Division Name";
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/48/";
	private static final String URL_SUFFIX = ".png";
	
	private static final int ICON_COLUMN_LENGTH = 27; 

	
	public SectionsListGrid(){
		super();
		
		  // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX); 
	    
	    //DIRECTORATE_ID
	    ListGridField divisionIDField = new ListGridField(DIVISION_ID, DIVISION_ID_DISPLAY); 
	    divisionIDField.setHidden(true);
	    ListGridField sectionIDField = new ListGridField(SECTIONS_ID, SECTIONS_ID_DISPLAY);  
	    sectionIDField.setHidden(true);
	    ListGridField voteIDField = new ListGridField(VOTE_ID, VOTE_ID_DISPLAY);  
	    voteIDField.setHidden(true);
	    ListGridField sectionCodeField = new ListGridField(SECTION_CODE, SECTION_CODE_DISPLAY);  
	    sectionCodeField.setWidth("15%");
	    ListGridField divisionNameField = new ListGridField(DIVISION_NAME, DIVISION_NAME_DISPLAY);  
	    divisionNameField .setWidth("20%");
	    ListGridField sectionNameField = new ListGridField(SECTIONS_NAME, SECTIONS_NAME_DISPLAY);  
	    sectionNameField.setWidth("20%");
		ListGridField sectionDescriptionField = new ListGridField(SECTIONS_DESCRIPTION, SECTIONS_DESCRIPTION_DISPLAY);
		sectionDescriptionField.setWidth("15%");
		ListGridField createdByField = new ListGridField(SECTION_CREATE_BY, SECTION_CREATE_BY_DISPLAY); 
		createdByField.setWidth("15%");
		ListGridField changedByField = new ListGridField(SECTION_CHANGED_BY, SECTION_CHANGED_BY_DISPLAY); 
		changedByField.setWidth("15%");
		ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);  
		
		this.setCanPickFields(false);
		this.setShowRowNumbers(true);
		this.setCanPickFields(false);
		this.setFields(iconField,divisionIDField,sectionIDField,divisionNameField,sectionCodeField,sectionNameField,sectionDescriptionField,createdByField,changedByField,emptyField);
	}
}
