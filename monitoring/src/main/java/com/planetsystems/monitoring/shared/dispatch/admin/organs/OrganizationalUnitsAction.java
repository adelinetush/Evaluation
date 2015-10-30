/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;


/**
 * @author Planet Developer 001
 * 
 */
public class OrganizationalUnitsAction extends
		UnsecuredActionImpl<OrganizationalUnitsResult> {

	private String operation;
	private String operationLevel;

	private Department department;
	private Division division;
	private Section section;
	

	@SuppressWarnings("unused")
	private OrganizationalUnitsAction() {

	}

	public OrganizationalUnitsAction(String operationLevel,String operation) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		
	}
	
	public OrganizationalUnitsAction(String operationLevel,String operation,Department department) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.department=department;
		
	}
	
	public OrganizationalUnitsAction(String operationLevel,String operation,Division division) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.division=division;
		
	}
	
	public OrganizationalUnitsAction(String operationLevel,String operation,Section section) {

		this.operationLevel=operationLevel;
		this.operation=operation;
		this.section=section;
		
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the operationLevel
	 */
	public String getOperationLevel() {
		return operationLevel;
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
	 * @return the section
	 */
	public Section getSection() {
		return section;
	}
}
