package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.Question;
import com.planetsystems.monitoring.model.Questionnaire;

public interface QuestionService {
	
	public boolean save(Question question);

	public boolean edit(Question question);

	public boolean delete(Question question);

	public boolean delete(String question);

	public Question find(String questionId);
	
	public Question update(Question question);

	public List<Question> findAll();
	
	
	public List<Question> findQuestionById(Question question);
	

}