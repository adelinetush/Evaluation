package com.planetsystems.monitoring.model.budget;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.units.Department;

@Entity
public class Activity extends ParentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7163707648065886382L;
	private String activityCode;
	private String activityName;
	private String activityDesc;
	private Department department;
	
	/**
	 * @return the activityCode
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * @param activityCode the activityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * @return the activityDesc
	 */
	public String getActivityDesc() {
		return activityDesc;
	}
	/**
	 * @param activityDesc the activityDesc to set
	 */
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	/**
	 * @return the department
	 */
	@ManyToOne
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment (Department department) {
		this.department = department;
	}	
	
	
	
}
