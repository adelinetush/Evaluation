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
public class Section extends ParentEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8119303539572322275L;
	private String sectionName;
	private String sectionDescription;
	private String sectionCode;
	private Division division;
	private Set<User> sectionUser;
	
	/**
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}
	/**
	 * @param sectionName the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	/**
	 * @return the sectionDescription
	 */
	public String getSectionDescription() {
		return sectionDescription;
	}
	/**
	 * @param sectionDescription the sectionDescription to set
	 */
	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}
	/**
	 * @return the sectionCode
	 */
	public String getSectionCode() {
		return sectionCode;
	}
	/**
	 * @param sectionCode the sectionCode to set
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	/**
	 * @return the division
	 */
	@ManyToOne
	public Division getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(Division division) {
		this.division = division;
	}
	/**
	 * @return the sectionUsers
	 */
	@OneToMany(mappedBy="section", fetch=FetchType.EAGER)
	public Set<User> getSectionUser() {
		return sectionUser;
	}
	/**
	 * @param sectionUsers the sectionUsers to set
	 */
	public void setSectionUser(Set<User> sectionUser) {
		this.sectionUser = sectionUser;
	}
	
	public void addMember(User user) {
		if (this.sectionUser == null)
			this.sectionUser = new HashSet<User>();
		this.sectionUser.add(user);
		user.setSection(this);
	}
	
	
	

}
