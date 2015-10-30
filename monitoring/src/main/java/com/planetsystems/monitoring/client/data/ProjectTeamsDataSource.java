package com.planetsystems.monitoring.client.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * @author Planet Developer 001
 *
 */
public class ProjectTeamsDataSource extends DataSource{

	private static ProjectTeamsDataSource instance = null;
	
	public static ProjectTeamsDataSource getInstance(){
			
			if(instance == null){
				instance = new ProjectTeamsDataSource("localPlansDataSource");
			}
			return instance;
		}
		
		public ProjectTeamsDataSource(String id){
			
			 DataSourceTextField pk = new DataSourceTextField("pk", "Primary Key");  
	        DataSourceTextField icon = new DataSourceTextField("icon", "ICON");  
	        DataSourceTextField name = new DataSourceTextField("name", "Name");  
	        setFields(pk, icon, name);  
	          
	        setTestData(ProjectTeamsData.getNewRecords());  
	        setClientOnly(true);  
		}
}
