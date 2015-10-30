package com.planetsystems.monitoring.model.project;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.ProjectTeam;

@Entity
public class TeamMember extends ParentEntity {

	private static final long serialVersionUID = -9206619931082676628L;

	private String firstName, surname, position, emailAddress, phoneContact,
			employeeId;

	private ProjectTeam projectTeam;

	public TeamMember() {

	}

	public TeamMember(ProjectTeam projectTeam, String firstName,
			String surname, String employeeId, String position,
			String emailAddress, String phoneContact) {
		this.projectTeam = projectTeam;
		this.firstName = firstName;
		this.surname = surname;
		this.emailAddress = emailAddress;
		this.employeeId = employeeId;
		this.position = position;
		this.emailAddress = emailAddress;
		this.phoneContact = phoneContact;
		this.employeeId = employeeId;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectTeamId")
	public ProjectTeam getProjectTeam() {
		return projectTeam;
	}

	public void setProjectTeam(ProjectTeam projectTeam) {
		this.projectTeam = projectTeam;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneContact() {
		return phoneContact;
	}

	public void setPhoneContact(String phoneContact) {
		this.phoneContact = phoneContact;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
