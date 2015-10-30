package com.planetsystems.monitoring.model.project;

import javax.persistence.Entity;



import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Deliverable  extends ProjectEntity{
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 958656586143399443L;

	private String deliverableName;
	
	private Tasks task;
	
	public Deliverable(){
		
	}
	
public Deliverable(Tasks task,String deliverableName){
	this.task=task;
	this.deliverableName=deliverableName;
}


public String getDeliverableName() {
	return deliverableName;
}

public void setDeliverableName(String deliverableName) {
	this.deliverableName = deliverableName;
}

@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
@JoinColumn(name="taskId")
public Tasks getTask() {
	return task;
}

public void setTask(Tasks task) {
	this.task = task;
}


}
