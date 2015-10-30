/**
 * 
 */
package com.planetsystems.monitoring.client.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * @author Planet Developer 001
 *
 */
public class ProjectsDataSource extends DataSource{

	private static ProjectsDataSource instance = null;
	
	public static ProjectsDataSource getInstance(){
			
			if(instance == null){
				instance = new ProjectsDataSource("localPlansDataSource");
			}
			return instance;
		}
		
		public ProjectsDataSource(String id){
			
			 DataSourceTextField pk = new DataSourceTextField("pk", "Primary Key");  
	        DataSourceTextField icon = new DataSourceTextField("icon", "ICON");  
	        DataSourceTextField name = new DataSourceTextField("name", "Name");  
	        setFields(pk, icon, name);  
	          
	        setTestData(ProjectsData.getNewRecords());  
	        setClientOnly(true);  
		}
}