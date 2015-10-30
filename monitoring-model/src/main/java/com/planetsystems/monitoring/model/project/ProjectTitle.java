package com.planetsystems.monitoring.model.project;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.planetsystems.monitoring.model.PerformanceIndicator;
import com.planetsystems.monitoring.model.ProjectTeam;

@Entity
public class ProjectTitle  extends PerformanceIndicator{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5316487584147472314L;


	private String projectTitle;
	
	
	private List<Goals> projectGoals;
	
	private List<Objectives> projectObjectives=new ArrayList<Objectives>();
	
	
	private List<Documents> projectDocuments=new ArrayList<Documents>();
	
	private ProjectTeam projectTeam;
	
	
	public ProjectTitle(){
		
	}
	
	public ProjectTitle(String projectTile,List<Goals> projectGoals,List<Objectives> projectObjectives,ProjectTeam projectTeam){
		this.projectTitle=projectTile;
		this.projectGoals=projectGoals;
		this.projectObjectives=projectObjectives;
		this.projectTeam=projectTeam;
	}


	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	@OneToMany( targetEntity=Goals.class,mappedBy="project", cascade=CascadeType.ALL)
	public List<Goals> getProjectGoals() {
		return projectGoals;
	}

	public void setProjectGoals(List<Goals> projectGoals) {
		this.projectGoals = projectGoals;
	}

	@OneToMany( targetEntity=Objectives.class,mappedBy="project", cascade=CascadeType.ALL)
	public List<Objectives> getProjectObjectives() {
		return projectObjectives;
	}

	public void setProjectObjectives(List<Objectives> projectObjectives) {
		this.projectObjectives = projectObjectives;
	}

	@OneToMany( targetEntity=Documents.class,mappedBy="project", cascade=CascadeType.ALL)
	public List<Documents> getProjectDocuments() {
		return projectDocuments;
	}

	public void setProjectDocuments(List<Documents> projectDocuments) {
		this.projectDocuments = projectDocuments;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="projectTeamId")
	public ProjectTeam getProjectTeam() {
		return projectTeam;
	}

	public void setProjectTeam(ProjectTeam projectTeam) {
		this.projectTeam = projectTeam;
	}

	
	
	
	

}
