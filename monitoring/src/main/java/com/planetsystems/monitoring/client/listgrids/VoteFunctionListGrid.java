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
public class VoteFunctionListGrid extends ListGrid{

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	private static final String VOTE_FUNCTION_ID="voteFunctionID";
	private static final String VOTE_FUNCTION_ID_DISPLAY="Vote Function ID";
	private static final String VOTE_FUNCTION_CODE="voteFunctionCode";
	private static final String VOTE_FUNCTION_CODE_DISPLAY="Vote Function Code";
	private static final String VOTE_ID="voteID";
	private static final String VOTE_ID_DISPLAY="Vote ID";
	private static final String VOTE_CODE="voteCode";
	private static final String VOTE_CODE_DISPLAY="Vote Code";
	private static final String VOTE_NAME="voteName";
	private static final String VOTE_NAME_DISPLAY="Vote Name";
	private static final String VOTE_FUNCTION_NAME="voteFunctionName";
	private static final String VOTE_FUNCTION_NAME_DISPLAY="Vote Function Name";
	private static final String VOTE_FUNCTION_DESCRIPTION="voteFunctionDescription";
	private static final String VOTE_FUNCTION_DESCRIPTION_DISPLAY="Vote Function Description";
	private static final String CREATED_BY= "createdBy";
	private static final String CREATED_BY_DISPLAY= "Created By";
	private static final String CHANGED_BY= "changedBy";
	private static final String CHANGED_BY_DISPLAY= "ChangedBy";
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/48/";
	private static final String URL_SUFFIX = ".png";
	

	private static final int ICON_COLUMN_LENGTH = 27; 
	private static final int VOTE_FUNCTION_ID_COLUMN_LENGTH = 0; 
	private static final int VOTE_ID_COLUMN_LENGTH = 0;
	private static final int VOTE_FUNCTION_NAME_COLUMN_LENGTH = 150; 
	private static final int VOTE_FUNCTION_DESCRIPTION_COLUMN_LENGTH = 150;//VOTE_NAME_COLUMN_LENGTH
	private static final int VOTE_NAME_COLUMN_LENGTH = 150;
	
	public VoteFunctionListGrid(){
		super();
		
		 // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX);
	    
	    ListGridField voteFunctionIDField = new ListGridField(VOTE_FUNCTION_ID, VOTE_FUNCTION_ID_DISPLAY);  
	    voteFunctionIDField.setHidden(true);
	    ListGridField voteFunctionCodeField = new ListGridField(VOTE_FUNCTION_CODE, VOTE_FUNCTION_CODE_DISPLAY);  
	    voteFunctionCodeField.setWidth("10%");
	    ListGridField voteNameField = new ListGridField(VOTE_NAME, VOTE_NAME_DISPLAY);
	    voteNameField.setWidth("20%");
	    ListGridField voteIDField = new ListGridField(VOTE_ID, VOTE_ID_DISPLAY); 
	    voteIDField.setHidden(true);
	    ListGridField voteCodeField = new ListGridField(VOTE_CODE, VOTE_CODE_DISPLAY); 
	    voteCodeField.setWidth("10%");
	    ListGridField voteFunctionNameField = new ListGridField(VOTE_FUNCTION_NAME, VOTE_FUNCTION_NAME_DISPLAY);  
	    voteFunctionNameField.setWidth("20%");
	    ListGridField voteFunctionDescriptionField = new ListGridField(VOTE_FUNCTION_DESCRIPTION, VOTE_FUNCTION_DESCRIPTION_DISPLAY); 
	    ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME); 
	    ListGridField createdByField = new ListGridField(CREATED_BY, CREATED_BY_DISPLAY); 
		createdByField.setWidth("20%");
		ListGridField changedByField = new ListGridField(CHANGED_BY, CHANGED_BY_DISPLAY); 
		changedByField.setWidth("20%");
	    
		this.setCanPickFields(false);
		this.setShowRowNumbers(true);
	    this.setFields(iconField,voteIDField,voteFunctionIDField,voteCodeField,voteNameField,voteFunctionCodeField,voteFunctionNameField,createdByField,changedByField);
	}
}
