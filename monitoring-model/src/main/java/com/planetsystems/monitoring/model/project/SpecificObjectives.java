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
public class SpecificObjectives extends ProjectEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2645517680164237576L;

	
	private String specificObjective;
	
	private Objectives objective;
	
	private List<Activities> activities=new ArrayList<Activities>();
	
	public SpecificObjectives(){
	}

	public SpecificObjectives(Objectives objective, String specificObjective, List<Activities> activities){
		this.objective=objective;
		this.specificObjective=specificObjective;
		this.activities=activities;
	}

	

	
	public String getSpecificObjective() {
		return specificObjective;
	}

	public void setSpecificObjective(String specificObjective) {
		this.specificObjective = specificObjective;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="objectiveId")
	public Objectives getObjective() {
		return objective;
	}

	public void setObjective(Objectives objective) {
		this.objective = objective;
	}

	@OneToMany( targetEntity=Activities.class,mappedBy="specificObjective", cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	public List<Activities> getActivities() {
		return activities;
	}

	public void setActivities(List<Activities> activities) {
		this.activities = activities;
	}
	
	
}
