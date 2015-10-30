
package com.planetsystems.monitoring.client.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * @author Planet Developer 001
 *
 */
public class SystemAdministrationDataSource extends DataSource{

	private static SystemAdministrationDataSource instance = null;
	
	public static SystemAdministrationDataSource getInstance(){
		
		if(instance == null){
			instance = new SystemAdministrationDataSource("localSystemAdminDataSource");
		}
		return instance;
	}
	
	public SystemAdministrationDataSource(String id){
		
		 DataSourceTextField pk = new DataSourceTextField("pk", "Primary Key");  
        DataSourceTextField icon = new DataSourceTextField("icon", "ICON");  
        DataSourceTextField name = new DataSourceTextField("name", "Name");  
        setFields(pk, icon, name);  
          
        setTestData(SystemAdministrationData.getNewRecords());  
        setClientOnly(true);  
	}
}
