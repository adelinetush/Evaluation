package com.planetsystems.monitoring.model.project;

import java.util.ArrayList;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Objectives extends ProjectEntity {
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7382127201711475892L;

	private String objectiveName;
	
	private List<SpecificObjectives> specificObjectives=new ArrayList<SpecificObjectives>();
	
	private List<Goals> goals=new ArrayList<Goals>();
	
	private ProjectTitle project;
	
	public Objectives(){
		
	}
	
	public Objectives(ProjectTitle project, List<Goals> goals, String objectiveName,List<SpecificObjectives> specificObjectives){
		this.project=project;
		this.goals=goals;
		this.objectiveName=objectiveName;
		this.specificObjectives=specificObjectives;
	}

	
	
	public String getObjectiveName() {
		return objectiveName;
	}

	public void setObjectiveName(String objectiveName) {
		this.objectiveName = objectiveName;
	}

	@OneToMany( targetEntity=SpecificObjectives.class,mappedBy="objective", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<SpecificObjectives> getSpecificObjectives() {
		return specificObjectives;
	}

	public void setSpecificObjectives(List<SpecificObjectives> specificObjectives) {
		this.specificObjectives = specificObjectives;
	}

	@ManyToMany
	@JoinTable(name="JOIN_GOAL_OBJECTIVE", joinColumns={@JoinColumn(name="objectiveId")},inverseJoinColumns={@JoinColumn(name="goalId")})
	public List<Goals> getGoals() {
		return goals;
	}

	public void setGoals(List<Goals> goals) {
		this.goals = goals;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="projectId")
	public ProjectTitle getProject() {
		return project;
	}

	public void setProject(ProjectTitle project) {
		this.project = project;
	}
	
	

}
