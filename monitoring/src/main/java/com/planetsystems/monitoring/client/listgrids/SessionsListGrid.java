/**
 * 
 */
package com.planetsystems.monitoring.client.listgrids;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Planet Developer 001
 * 
 */
public class SessionsListGrid extends ListGrid {

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME = "#";
	private static final String SESSION_ID = "sessionID";
	private static final String SESSION_ID_DISPLAY = "Session ID";
	private static final String USER_ID = "userID";
	private static final String USER_ID_DISPLAY = "User ID";
	private static final String USER_NAME = "userName";
	private static final String USER_NAME_DISPLAY = "User Name";
	private static final String USER_FIRST_NAME = "userFirstName";
	private static final String USER_FIRST_NAME_DISPLAY = "User FirstName";
	private static final String USER_SECOND_NAME = "userLastName";
	private static final String USER_SECOND_NAME_DISPLAY = "User Last Name";
	private static final String LOGGED_IN_DATE = "loggedInDate";
	private static final String LOGGED_IN_DATE_DISPLAY = " Date ";
	private static final String LOGGED_IN_TIME = "loggedInTime";
	private static final String LOGGED_IN_TIME_DISPLAY = "LogIn Time ";
	private static final String LOGGED_OUT_TIME = "loggedOutTime";
	private static final String LOGGED_OUT_TIME_DISPLAY = "LogOut Time ";
	private static final String OPERATION = "operation";
	private static final String OPERATION_DISPLAY = "Operation ";
	private static final String ACTION_STATUS = "actionStatus";
	private static final String ACTION_STATUS_DISPLAY = "Action Status ";
	private static final String TABLE = "table";
	private static final String TABLE_DISPLAY = "Table ";
	
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	private static final String CREATED_BY= "createdBy";
	private static final String CREATED_BY_DISPLAY= "Created By";
	private static final String CHANGED_BY= "changedBy";
	private static final String CHANGED_BY_DISPLAY= "ChangedBy";
	
	 private static final int ICON_COLUMN_LENGTH = 27; 
	 
	 public SessionsListGrid(){
		  super();
		  
		  // initialize the List Grid fields  
		    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
		    iconField.setImageSize(16); 
		    iconField.setAlign(Alignment.CENTER);
		    iconField.setType(ListGridFieldType.IMAGE);  
		    iconField.setImageURLPrefix(URL_PREFIX);  
		    iconField.setImageURLSuffix(URL_SUFFIX); 
		    
		    
		    ListGridField userIDField = new ListGridField(USER_ID, USER_ID_DISPLAY);  
		    userIDField.setHidden(true);
		    ListGridField sessionIDField = new ListGridField(SESSION_ID, SESSION_ID_DISPLAY);  
		    sessionIDField.setHidden(true);
		    ListGridField userNameField = new ListGridField(USER_NAME, USER_NAME_DISPLAY);  
		    userNameField.setWidth("20%");
		    ListGridField loggedInDateField = new ListGridField(LOGGED_IN_DATE, LOGGED_IN_DATE_DISPLAY);  
		    loggedInDateField.setWidth("10%");
		    ListGridField loggedInTimeField = new ListGridField(LOGGED_IN_TIME, LOGGED_IN_TIME_DISPLAY);  
		    loggedInTimeField.setWidth("10%");
		    ListGridField loggedOutTimeField = new ListGridField(LOGGED_OUT_TIME, LOGGED_OUT_TIME_DISPLAY);  
		    loggedOutTimeField.setWidth("10%");
		    ListGridField operationField = new ListGridField(OPERATION, OPERATION_DISPLAY);  
		    operationField.setWidth("15%");
		    ListGridField actionStatusField = new ListGridField(ACTION_STATUS, ACTION_STATUS_DISPLAY);  
		    actionStatusField.setWidth("15%");
		    ListGridField tableField = new ListGridField(TABLE, TABLE_DISPLAY);  
		    tableField.setWidth("15%");
		    
		    this.setCanPickFields(false);
		    this.setShowRowNumbers(true);
		    this.setFields(sessionIDField,userIDField,userNameField,loggedInDateField,loggedInTimeField,
		    		loggedOutTimeField,operationField,actionStatusField,tableField);
		    
		    final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy");
		    loggedInDateField.setCellFormatter(new CellFormatter() {
		        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
		            if (value != null) {

		                try {
		                    Date dateValue = (Date) value;
		                    return dateFormatter.format(dateValue);
		                } catch (Exception e) {
		                    return value.toString();
		                }
		            } else {
		                return "";
		            }
		        }
		    });
		    
		    final DateTimeFormat timeFormatter = DateTimeFormat.getFormat("HH:MM:SS");
		  
		    loggedInTimeField.setCellFormatter(new CellFormatter() {
		        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
		            if (value != null) {

		                try {
		                    Date dateValue = (Date) value;
		                    return timeFormatter.format(dateValue);
		                } catch (Exception e) {
		                    return value.toString();
		                }
		            } else {
		                return "";
		            }
		        }
		    });
		    
		    loggedOutTimeField.setCellFormatter(new CellFormatter() {
		        public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
		            if (value != null) {

		                try {
		                    Date dateValue = (Date) value;
		                    return timeFormatter.format(dateValue);
		                } catch (Exception e) {
		                    return value.toString();
		                }
		            } else {
		                return "";
		            }
		        }
		    });
	 }
}
