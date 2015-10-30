package com.planetsystems.monitoring.client.widgets;

import com.planetsystems.monitoring.model.project.TeamMember;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.smartgwt.client.widgets.viewer.DetailViewerRecord;

public class MemberDetailViewer extends DetailViewer {

	public static final String MEMBER_NAME = "memberName";
	private static final String EMPLOYEE_ID = "employeeId";
	private static final String GENDER = "gender";
	public static final String POSITION = "position";

	public static final String MEMBER_NAME_DISPLAY = "Name";
	private static final String EMPLOYEE_ID_DISPLAY = "Employee ID";
	private static final String GENDER_DISPLAY = "Gender";
	public static final String POSITION_DISPLAY = "Position";

	public MemberDetailViewer() {
		super();
		setWidth100();
		setHeight100();
		setMargin(15);
		getShowEmptyField();

		DetailViewerField memberName = new DetailViewerField(MEMBER_NAME,
				MEMBER_NAME_DISPLAY);

		DetailViewerField employeeId = new DetailViewerField(EMPLOYEE_ID,
				EMPLOYEE_ID_DISPLAY);

		DetailViewerField gender = new DetailViewerField(GENDER, GENDER_DISPLAY);

		DetailViewerField position = new DetailViewerField(POSITION,
				POSITION_DISPLAY);

		setFields(memberName, employeeId, gender, position);

	}

	public void addRowData(TeamMember member) {
		DetailViewerRecord[] records = new DetailViewerRecord[1];

		DetailViewerRecord record = new DetailViewerRecord();

		record.setAttribute(MEMBER_NAME,
				member.getFirstName() + " " + member.getSurname());

		record.setAttribute(GENDER, member.getEmployeeId());

		record.setAttribute(EMPLOYEE_ID, member.getEmployeeId());

		record.setAttribute(POSITION, member.getPosition());

		records[0] = record;

		this.setData(records);
		this.redraw();

	}

}
