package com.planetsystems.monitoring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Questionnaire extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8360626887748593801L;
	
	private List<Question> questions;
	
	private String comment;
	
	private PerformanceIndicator performanceIndicator;
	
	public Questionnaire(){
		
	}

	@OneToMany( targetEntity=Question.class,mappedBy="questionnaire", cascade=CascadeType.ALL)
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne
	@JoinColumn(name="performanceIndicator")
	public PerformanceIndicator getPerformanceIndicator() {
		return performanceIndicator;
	}

	public void setPerformanceIndicator(PerformanceIndicator performanceIndicator) {
		this.performanceIndicator = performanceIndicator;
	}
	
	

}
