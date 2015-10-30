package com.planetsystems.monitoring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class PerformanceIndicator extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107023339179309069L;

	private String performanceIndicator;
	
	private String comment;
	
	private List<Questionnaire> questionnaires;
	
	public PerformanceIndicator(){
		
	}

	public String getPerformanceIndicator() {
		return performanceIndicator;
	}

	public void setPerformanceIndicator(String performanceIndicator) {
		this.performanceIndicator = performanceIndicator;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@OneToMany( targetEntity=Questionnaire.class,mappedBy="performanceIndicator", cascade=CascadeType.ALL)
	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	
}