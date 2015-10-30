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

@Entity
public class Goals extends ProjectEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3823719949382425137L;

	private String goalName;

	private ProjectTitle project;

	private List<Objectives> objectives = new ArrayList<Objectives>();

	public Goals() {

	}

	public Goals(String goalName, ProjectTitle project,
			List<Objectives> objectives) {
		this.goalName = goalName;
		this.project = project;
		this.objectives = objectives;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "projectId")
	public ProjectTitle getProject() {
		return project;
	}

	public void setProject(ProjectTitle project) {
		this.project = project;
	}

	@ManyToMany
	@JoinTable(name = "JOIN_GOAL_OBJECTIVE", joinColumns = { @JoinColumn(name = "goalId") }, inverseJoinColumns = { @JoinColumn(name = "objectiveId") })
	public List<Objectives> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<Objectives> objectives) {
		this.objectives = objectives;
	}

}
