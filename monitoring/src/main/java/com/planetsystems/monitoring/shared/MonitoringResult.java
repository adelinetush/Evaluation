package com.planetsystems.monitoring.shared;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.project.Documents;
import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.project.TeamMember;

public class MonitoringResult implements Result {
	
	private boolean response;
	private List<ProjectTitle> projectList;
	private List<Goals> goalList;
	
	private List<Objectives> objectiveList;
	
	private List<Documents> documents;
	
	private List<TeamMember> teamMembers;
	
	private TeamMember member;
	
	
	

	public List<Objectives> getObjectiveList() {
		return objectiveList;
	}

	public MonitoringResult() {
	}

	public MonitoringResult(boolean response) {
		this.response = response;
	}
	
	public MonitoringResult(TeamMember member) {
		this.member = member;
	}
	

	public MonitoringResult(List<ProjectTitle> projectList) {
		this.projectList = projectList;

	}
	
	public MonitoringResult(List<Goals> goalList,List<Objectives> objectiveList,List<TeamMember> teamMembers) {
		this.goalList = goalList;
		this.objectiveList=objectiveList;
		this.teamMembers=teamMembers;

	}
	public MonitoringResult(List<Objectives> objectiveList,Objectives objective) {
		this.objectiveList = objectiveList;

	}
	
	public MonitoringResult(List<Documents> documents,Documents document){
		this.documents=documents;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public boolean isResponse() {
		return response;
	}

	public List<ProjectTitle> getProjects() {
		return projectList;
	}

	public List<Goals> getGoalList() {
		return goalList;
	}

	public TeamMember getMember() {
		return member;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	

}
