package com.planetsystems.monitoring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question extends ParentEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3192165835551018008L;

	private String query;
	
	private boolean isMultChoice;
	
	private List<Answer> answers;
	
	private Questionnaire questionnaire;
	
	
	public Question(){
		
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public boolean isMultChoice() {
		return isMultChoice;
	}

	public void setMultChoice(boolean isMultChoice) {
		this.isMultChoice = isMultChoice;
	}

	
	@OneToMany( targetEntity=Answer.class,mappedBy="question", cascade=CascadeType.ALL)
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@ManyToOne
	@JoinColumn(name="questionnaireId")
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	
	

}
