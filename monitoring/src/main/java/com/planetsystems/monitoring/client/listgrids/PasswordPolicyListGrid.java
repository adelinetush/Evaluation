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
public class PasswordPolicyListGrid extends ListGrid {

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME = "#";

	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	private static final String CREATED_BY = "createdBy";
	private static final String CREATED_BY_DISPLAY = "Created By";
	private static final String CHANGED_BY = "changedBy";
	private static final String CHANGED_BY_DISPLAY = "ChangedBy";
	private static final String POLICY_NAME = "policyExpression";
	private static final String POLICY_NAME_DISPLAY = "Expression";
	private static final String POLICY_ID = "policyID";
	private static final String POLICY_ID_DISPLAY = "Policy ID";
	private static final String POLICY_STATUS = "policyStatus";
	private static final String POLICY_STATUS_DISPLAY = "Status";
	private static final String DATE_CREATED = "dateCreated";
	private static final String DATE_CREATED_DISPLAY = "Date Created ";
	private static final String DATE_CHANGED = "dateChanged";
	private static final String DATE_CHANGED_DISPLAY = "Date Changed";

	private static final int ICON_COLUMN_LENGTH = 27;

	public PasswordPolicyListGrid() {
		super();

		// initialize the List Grid fields
		ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME,
				ICON_COLUMN_LENGTH);
		iconField.setImageSize(16);
		iconField.setAlign(Alignment.CENTER);
		iconField.setType(ListGridFieldType.IMAGE);
		iconField.setImageURLPrefix(URL_PREFIX);
		iconField.setImageURLSuffix(URL_SUFFIX);

		ListGridField policyIDField = new ListGridField(POLICY_ID,
				POLICY_ID_DISPLAY);
		policyIDField.setHidden(true);

		ListGridField policyExpressionField = new ListGridField(POLICY_NAME,
				POLICY_NAME_DISPLAY);
		policyExpressionField.setWidth("30%");

		ListGridField userStatusField = new ListGridField(POLICY_STATUS,
				POLICY_STATUS_DISPLAY);
		userStatusField.setWidth("10%");

		ListGridField createdByField = new ListGridField(CREATED_BY,
				CREATED_BY_DISPLAY);
		createdByField.setWidth("20%");
		ListGridField changedByField = new ListGridField(CHANGED_BY,
				CHANGED_BY_DISPLAY);
		changedByField.setWidth("20%");
		ListGridField dateCreatedField = new ListGridField(DATE_CREATED,
				DATE_CREATED_DISPLAY);
		dateCreatedField.setWidth("10%");
		ListGridField dateChangedField = new ListGridField(DATE_CHANGED,
				DATE_CHANGED_DISPLAY);
		dateChangedField.setWidth("10%");

		this.setShowRollOverCanvas(true);
		this.setShowRollOver(true);
		this.setShowRowNumbers(true);
		this.setCanPickFields(false);

		this.setFields(iconField, policyIDField, policyExpressionField,
				userStatusField, createdByField, changedByField,dateCreatedField,dateChangedField);
		

	    final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy");
	    dateCreatedField.setCellFormatter(new CellFormatter() {
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
	    
	    dateChangedField.setCellFormatter(new CellFormatter() {
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

	}
}
