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
public class EventsListGrid extends ListGrid{

	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME = "#";
	public static final String EVENT_ID = "eventID";
	private static final String EVENT__ID_DISPLAY = "Event ID";
	public static final String USER_ID = "userID";
	private static final String USER_ID_DISPLAY = "User ID";
	public static final String USER_NAME = "userName";
	private static final String USER_NAME_DISPLAY = "User Name";
	public static final String LOGGED_IN_DATE = "loggedInDate";
	private static final String LOGGED_IN_DATE_DISPLAY = " Date ";
	public static final String LOGGED_IN_TIME = "loggedInTime";
	private static final String LOGGED_IN_TIME_DISPLAY = "LogIn Time ";
	public static final String OPERATION = "operation";
	private static final String OPERATION_DISPLAY = "Operation ";
	public static final String ENTITY = "entity";
	private static final String ENTITY_DISPLAY = "Entity ";
	public static final String ACTION_STATUS = "actionStatus";
	private static final String ACTION_STATUS_DISPLAY = "Action Status ";
	
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	
 private static final int ICON_COLUMN_LENGTH = 27; 
	 
	 public EventsListGrid(){
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
		    ListGridField eventIDField = new ListGridField(EVENT_ID, EVENT__ID_DISPLAY);  
		    eventIDField.setHidden(true);
		    ListGridField userNameField = new ListGridField(USER_NAME, USER_NAME_DISPLAY);  
		    userNameField.setWidth("15%");
		    ListGridField loggedInDateField = new ListGridField(LOGGED_IN_DATE, LOGGED_IN_DATE_DISPLAY);  
		    loggedInDateField.setWidth("10%");
		    ListGridField loggedInTimeField = new ListGridField(LOGGED_IN_TIME, LOGGED_IN_TIME_DISPLAY);  
		    loggedInTimeField.setWidth("10%");
		    ListGridField operationField = new ListGridField(OPERATION, OPERATION_DISPLAY);  
		    operationField.setWidth("15%");
		    ListGridField actionStatusField = new ListGridField(ACTION_STATUS, ACTION_STATUS_DISPLAY);  
		    actionStatusField.setWidth("5%");
		    ListGridField entityField = new ListGridField(ENTITY, ENTITY_DISPLAY);  
		    entityField.setWidth("15%");
		    
		    this.setCanPickFields(false);
		    this.setShowRowNumbers(true);
		    this.setFields(eventIDField,userIDField,userNameField,loggedInDateField,loggedInTimeField,entityField,operationField,actionStatusField);
		    
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
	 }
}
