package com.planetsystems.monitoring.client.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;


public class SettingsDataSource extends DataSource{

	private static SettingsDataSource instance = null;
	
	public static SettingsDataSource getInstance(){
			
			if(instance == null){
				instance = new SettingsDataSource("localPlansDataSource");
			}
			return instance;
		}
		
		public SettingsDataSource(String id){
			
			 DataSourceTextField pk = new DataSourceTextField("pk", "Primary Key");  
	        DataSourceTextField icon = new DataSourceTextField("icon", "ICON");  
	        DataSourceTextField name = new DataSourceTextField("name", "Name");  
	        setFields(pk, icon, name);  
	          
	        setTestData(SettingsData.getNewRecords());  
	        setClientOnly(true);  
		}
}


