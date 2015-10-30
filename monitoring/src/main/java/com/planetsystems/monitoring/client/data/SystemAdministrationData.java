/**
 * 
 */
package com.planetsystems.monitoring.client.data;

import com.planetsystems.monitoring.client.place.NameTokens;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Planet Developer 001
 * 
 */
public class SystemAdministrationData {

	private static ListGridRecord[] records;

	public static ListGridRecord[] getRecords() {
		if (records == null) {
			records = getNewRecords();

		}
		return records;

	}

	public static ListGridRecord createRecord(String pk, String icon,
			String name) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute("pk", pk);
		record.setAttribute("icon", icon);
		record.setAttribute("name", name);
		return record;
	}

	public static ListGridRecord[] getNewRecords() {

		return new ListGridRecord[] {
				createRecord("", "audittrail", NameTokens.auditTrail),
				createRecord("", "projects", NameTokens.accounts),
				createRecord("", "users", NameTokens.users),
				createRecord("", "organizations", NameTokens.organizationalUnits),
				createRecord("", "currency", NameTokens.currencies),
				createRecord("", "TaskLogs", NameTokens.tasklogs),
				createRecord("", "Reports", NameTokens.reports)

		};

	}
}
