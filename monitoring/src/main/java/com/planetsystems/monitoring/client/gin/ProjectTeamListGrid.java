package com.planetsystems.monitoring.client.gin;

import java.util.List;


import com.planetsystems.monitoring.model.project.TeamMember;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Planet Developer 001
 * 
 */
public class ProjectTeamListGrid extends ListGrid {

	public static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME = "#";
	public static final String MEMBER_ID = "memberID";
	private static final String FIRST_NAME_DISPLAY = "Firstname";
	public static final String SURNAME = "surname";
	private static final String SURNAME_DISPLAY = "Surname";
	public static final String FIRST_NAME = "firstName";

	public static final String EMPLOYEE_ID = "employeeId";
	private static final String EMPLOYEE_ID_DISPLAY = "EMPLOYEE ID";

	private static final String POSITION_DISPLAY = "Position";
	public static final String POSITION = "position";

	private static final String MEMBER_ID_DISPLAY = "Member ID";

	public static final String EMAIL_ADDRESS = "emailAddress";
	private static final String EMAIL_ADDRESS_DISPLAY = "Email Address";
	public static final String PHONE_CONTACT = "phoneContact";
	private static final String PHONE_CONTACT_DISPLAY = "Phone Contact";

	private static final String EMPTY_FIELD = "emptyField";
	private static final String EMPTY_FIELD_DISPLAY_NAME = " ";
	private static final String URL_PREFIX = "icons/48/";
	private static final String URL_SUFFIX = ".png";

	public static final String CREATED_BY = "createdBy";
	private static final String CREATED_BY_DISPLAY = "Created By";
	public static final String CHANGED_BY = "changedBy";
	private static final String CHANGED_BY_DISPLAY = "ChangedBy";

	private static final int ICON_COLUMN_LENGTH = 27;

	public ProjectTeamListGrid() {

		super();

		// initialize the List Grid fields
		ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME,
				ICON_COLUMN_LENGTH);
		iconField.setImageSize(16);
		iconField.setAlign(Alignment.CENTER);
		iconField.setType(ListGridFieldType.IMAGE);
		iconField.setImageURLPrefix(URL_PREFIX);
		iconField.setImageURLSuffix(URL_SUFFIX);

		ListGridField memberIDField = new ListGridField(MEMBER_ID,
				MEMBER_ID_DISPLAY);
		memberIDField.setHidden(true);

		ListGridField firstNameField = new ListGridField(FIRST_NAME,
				FIRST_NAME_DISPLAY);
		firstNameField.setWidth("10%");

		ListGridField surnameField = new ListGridField(SURNAME, SURNAME_DISPLAY);
		surnameField.setWidth("10%");

		ListGridField positionField = new ListGridField(POSITION,
				POSITION_DISPLAY);
		positionField.setWidth("10%");

		ListGridField employeeIdField = new ListGridField(EMPLOYEE_ID,
				EMPLOYEE_ID_DISPLAY);
		employeeIdField.setWidth("10%");

		ListGridField emailAddressField = new ListGridField(EMAIL_ADDRESS,
				EMAIL_ADDRESS_DISPLAY);
		emailAddressField.setWidth("10%");

		ListGridField phoneContactField = new ListGridField(PHONE_CONTACT,
				PHONE_CONTACT_DISPLAY);
		phoneContactField.setHidden(true);

		ListGridField createdByField = new ListGridField(CREATED_BY,
				CREATED_BY_DISPLAY);
		createdByField.setWidth("10%");

		ListGridField changedByField = new ListGridField(CHANGED_BY,
				CHANGED_BY_DISPLAY);
		changedByField.setWidth("10%");

		this.setShowRowNumbers(true);
		this.setWrapCells(true);
		this.setCanPickFields(false);
		this.setFields(iconField, memberIDField, firstNameField, surnameField,
				positionField, emailAddressField, phoneContactField,
				createdByField, changedByField);
	}

	public void addRowData(List<TeamMember> teamMembers) {

		ListGridRecord[] records = new ListGridRecord[teamMembers.size()];

		int row = 0;
		for (TeamMember member : teamMembers) {

			ListGridRecord record = new ListGridRecord();

			record.setAttribute(MEMBER_ID, member.getId());
			record.setAttribute(FIRST_NAME, member.getFirstName());
			record.setAttribute(SURNAME, member.getSurname());
			record.setAttribute(EMPLOYEE_ID, member.getEmployeeId());
			record.setAttribute(POSITION, member.getPosition());
			record.setAttribute(EMAIL_ADDRESS, member.getEmailAddress());
			record.setAttribute(PHONE_CONTACT, member.getPhoneContact());
			record.setAttribute(CREATED_BY, member.getCreatedBy());
			record.setAttribute(CHANGED_BY, member.getChangedBy());

			records[row] = record;

			row++;

		}

		this.setData(records);

		this.redraw();

	}

	public void addRowToGrid(TeamMember member) {
		ListGridRecord record = new ListGridRecord();

		record.setAttribute(MEMBER_ID, member.getId());
		record.setAttribute(FIRST_NAME, member.getFirstName());
		record.setAttribute(SURNAME, member.getSurname());
		record.setAttribute(EMPLOYEE_ID, member.getEmployeeId());
		record.setAttribute(POSITION, member.getPosition());
		record.setAttribute(EMAIL_ADDRESS, member.getEmailAddress());
		record.setAttribute(PHONE_CONTACT, member.getPhoneContact());
		record.setAttribute(CREATED_BY, member.getCreatedBy());
		record.setAttribute(CHANGED_BY, member.getChangedBy());
		this.addData(record);
		this.redraw();

	}

}
