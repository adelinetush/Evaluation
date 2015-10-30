package com.planetsystems.monitoring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="answers")
public class Answer extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -461807869995545061L;
	
	private Question question;
	
	private String answer;
	
	private String rightAnswer;
	
	public Answer(){
		
	}

	@ManyToOne
	@JoinColumn(name="questionId")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}


}
