package com.planetsystems.monitoring.client.data;

import com.planetsystems.monitoring.client.place.NameTokens;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ProjectsData {
	
private static ListGridRecord[] records;
	
	public static ListGridRecord[] getRecords(){
		if(records == null){
			records = getNewRecords();
			
		}
		return records;
		
	}
	
	public static ListGridRecord createRecord(String pk, String icon, String name )
	{
		ListGridRecord record = new ListGridRecord();
		record.setAttribute("pk", pk);
		record.setAttribute("icon", icon);
		record.setAttribute("name", name);
		return record;
	}
	
	public static ListGridRecord[] getNewRecords(){
		
			return new ListGridRecord[]{
					createRecord("","project",NameTokens.new_project),
					createRecord("","approved",NameTokens.approved_projects),
					createRecord("","refresh",NameTokens.ongoing_projects),
					createRecord("","report",NameTokens.reports)
										
				
			};
}

}
