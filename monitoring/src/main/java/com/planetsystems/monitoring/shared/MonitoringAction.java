package com.planetsystems.monitoring.shared;

import java.util.HashMap;
import java.util.List;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.project.TeamMember;

 

public class MonitoringAction extends UnsecuredActionImpl<MonitoringResult> {
	private int operation;
	private String projectTitle;
	private String projectGoal;
	private ProjectTitle project;
	private List<String> objectives;
	
	private User user;
	
	private TeamMember member;
	
	private HashMap<Integer,List<String>> specificObjs;
	
	public MonitoringAction() { 
		
		
	}

	public MonitoringAction(int operation) {
		this.operation = operation;
	}
	
	
	public MonitoringAction(int operation, String  projectTitle,String projectGoal,List<String> objectives,HashMap<Integer,List<String>> specificObjs)
	{
		this.operation = operation;
		this.projectTitle = projectTitle;
		this.projectGoal = projectGoal;
		this.objectives=objectives;
		this.specificObjs=specificObjs;
	}
	

	public MonitoringAction(int operation, ProjectTitle project) {
		this.operation=operation;
		this.project=project;
		
	}
	
	public MonitoringAction(int operation, TeamMember member, User user,String projectTitle) {
		this.operation=operation;
		this.member=member;
		this.user=user;
		this.projectTitle=projectTitle;
		
	}

	public int getOperation() {
		return operation;
	}

	
	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectGoal() {
		return projectGoal;
	}

	public void setProjectGoal(String projectGoal) {
		this.projectGoal = projectGoal;
	}

	public List<String> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<String> objectives) {
		this.objectives = objectives;
	}

	public HashMap<Integer, List<String>> getSpecificObjs() {
		return specificObjs;
	}

	public void setSpecificObjs(HashMap<Integer, List<String>> specificObjs) {
		this.specificObjs = specificObjs;
	}

	public ProjectTitle getProject() {
		return project;
	}

	public void setProjectId(ProjectTitle project) {
		this.project = project;
	}

	public TeamMember getTeamMember() {
		return member;
	}

	public User getUser() {
		return user;
	}

	
	

	
	
	
		

}
