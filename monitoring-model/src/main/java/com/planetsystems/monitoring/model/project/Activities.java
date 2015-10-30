package com.planetsystems.monitoring.model.project;

import java.util.ArrayList;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Activities extends ProjectEntity {
	
	private static final long serialVersionUID = -4392198915902323736L;

	private String activityName;
	
	private SpecificObjectives specificObjective;
	
	private List<Tasks> tasks=new ArrayList<Tasks>();
	
	public Activities(){
	}
	
	public Activities(SpecificObjectives specificObjective,String activityName, List<Tasks> tasks){
		this.specificObjective=specificObjective;
		this.activityName=activityName;
		this.tasks=tasks;
		
	}


	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="specificObjectiveId")
	public SpecificObjectives getSpecificObjective() {
		return specificObjective;
	}

	public void setSpecificObjective(SpecificObjectives specificObjective) {
		this.specificObjective = specificObjective;
	}

	@OneToMany( targetEntity=Tasks.class,mappedBy="activity", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}
	
	

}

