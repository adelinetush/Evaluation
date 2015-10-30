/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;


public class OrganizationalUnitsResult implements Result {

	private String serverResponse;
	private Department department;
	private Division division;
	private Section section;

	private List<Department> departments;
	private List<Division> divisions;
	private List<Section> sections;

	private boolean operationStatus;

	@SuppressWarnings("unused")
	private OrganizationalUnitsResult(){
		
		
	}
	
	public OrganizationalUnitsResult(boolean operationStatus,
			String serverResponse) {

		this.operationStatus = operationStatus;
		this.serverResponse = serverResponse;

	}

	public OrganizationalUnitsResult(boolean operationStatus,
			List<Department> departments) {

		this.operationStatus = operationStatus;
		this.departments = departments;

	}

	public OrganizationalUnitsResult(
			List<Division> divisions,boolean operationStatus) {

		this.operationStatus = operationStatus;
		this.divisions = divisions;

	}
	
	public OrganizationalUnitsResult(boolean operationStatus,
			List<Section> sections,String serverResponse) {

		this.operationStatus = operationStatus;
		this.sections = sections;
		this.serverResponse = serverResponse;
		
	}

	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the division
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * @return the departments
	 */
	public List<Department> getDepartments() {
		return departments;
	}

	/**
	 * @return the divisions
	 */
	public List<Division> getDivisions() {
		return divisions;
	}

	/**
	 * @return the sections
	 */
	public List<Section> getSections() {
		return sections;
	}

	/**
	 * @return the operationStatus
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}

	/**
	 * @return the section
	 */
	public Section getSection() {
		return section;
	}

}
