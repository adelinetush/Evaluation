package com.planetsystems.monitoring.client.data;

import com.smartgwt.client.data.DataSource;

import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * @author Planet Developer 001
 *
 */
public class TeamInteractionDataSource extends DataSource{

	private static TeamInteractionDataSource instance = null;
	
	public static TeamInteractionDataSource getInstance(){
			
			if(instance == null){
				instance = new TeamInteractionDataSource("localPlansDataSource");
			}
			return instance;
		}
		
		public TeamInteractionDataSource(String id){
			
			 DataSourceTextField pk = new DataSourceTextField("pk", "Primary Key");  
	        DataSourceTextField icon = new DataSourceTextField("icon", "ICON");  
	        DataSourceTextField name = new DataSourceTextField("name", "Name");  
	        setFields(pk, icon, name);  
	          
	        setTestData(TeamInteractionData.getNewRecords());  
	        setClientOnly(true);  
		}
}
