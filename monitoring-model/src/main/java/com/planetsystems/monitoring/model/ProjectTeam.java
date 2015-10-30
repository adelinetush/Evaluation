package com.planetsystems.monitoring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.project.TeamMember;

@Entity
@Table(name = "projectTeams")
public class ProjectTeam extends ParentEntity {

	
	private static final long serialVersionUID = 6649994162476919308L;
	

	private List<TeamMember> teamMembers=new ArrayList<TeamMember>();

	
	private ProjectTitle project;
	public ProjectTeam() {

	}

	public ProjectTeam(List<TeamMember> teamMembers) {
		this.teamMembers=teamMembers;
	}


	@OneToMany(targetEntity = TeamMember.class, mappedBy = "projectTeam", cascade = CascadeType.ALL)
	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="projectTeamId")
	public ProjectTitle getProject() {
		return project;
	}

	public void setProject(ProjectTitle project) {
		this.project = project;
	}

	
	
}
