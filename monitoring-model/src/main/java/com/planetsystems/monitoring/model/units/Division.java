package com.planetsystems.monitoring.model.units;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.User;


@Entity
public class Division extends ParentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8959465690629491596L;
	private String divisionName;
	private String divisionDesctiption;
	private String divisionCode;
	private Department department;
	private Set<User> divisionUser;
	
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the divisionDesctiption
	 */
	public String getDivisionDesctiption() {
		return divisionDesctiption;
	}
	/**
	 * @param divisionDesctiption the divisionDesctiption to set
	 */
	public void setDivisionDesctiption(String divisionDesctiption) {
		this.divisionDesctiption = divisionDesctiption;
	}
	/**
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}
	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
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
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * @return the divisionUser
	 */
	@OneToMany(mappedBy="division", fetch=FetchType.EAGER)
	public Set<User> getDivisionUser() {
		return divisionUser;
	}
	/**
	 * @param divisionUser the divisionUser to set
	 */
	public void setDivisionUser(Set<User> divisionUser) {
		this.divisionUser = divisionUser;
	}
	
	public void addMember(User user) {
		if (this.divisionUser == null)
			this.divisionUser = new HashSet<User>();

		this.divisionUser.add(user);
		user.setDivision(this);
	}
	

}
 