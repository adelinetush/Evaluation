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
public class Tasks extends ProjectEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6091249471226298136L;

	
	private String taskName;
	
	private Activities activity;
	
	private List<Deliverable> deliverables=new ArrayList<Deliverable>();
	
	
	public Tasks(){
		
	}
	
	public Tasks(Activities activity,String taskName,List<Deliverable> deliverables){
		this.activity=activity;
		this.taskName=taskName;
		this.deliverables=deliverables;
		
	}

	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="activityId")
	public Activities getActivity() {
		return activity;
	}

	public void setActivity(Activities activity) {
		this.activity = activity;
	}

	@OneToMany( targetEntity=Deliverable.class,mappedBy="task", cascade=CascadeType.REFRESH)
	public List<Deliverable> getDeliverables() {
		return deliverables;
	}

	public void setDeliverables(List<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}
	
	

}
