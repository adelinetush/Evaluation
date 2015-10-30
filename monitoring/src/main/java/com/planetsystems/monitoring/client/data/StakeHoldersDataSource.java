package com.planetsystems.monitoring.client.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * @author Planet Developer 001
 *
 */
public class StakeHoldersDataSource extends DataSource{

	private static StakeHoldersDataSource instance = null;
	
	public static StakeHoldersDataSource getInstance(){
			
			if(instance == null){
				instance = new StakeHoldersDataSource("localPlansDataSource");
			}
			return instance;
		}
		
		public StakeHoldersDataSource(String id){
			
			 DataSourceTextField pk = new DataSourceTextField("pk", "Primary Key");  
	        DataSourceTextField icon = new DataSourceTextField("icon", "ICON");  
	        DataSourceTextField name = new DataSourceTextField("name", "Name");  
	        setFields(pk, icon, name);  
	          
	        setTestData(StakeHoldersData.getNewRecords());  
	        setClientOnly(true);  
		}
}
