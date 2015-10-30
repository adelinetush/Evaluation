package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.Answer;

public interface AnswerService {
	
	public boolean save(Answer answer);

	public boolean edit(Answer answer);

	public boolean delete(Answer answer);

	public boolean delete(String answerId);

	public Answer find(String answerId);
	
	public Answer update(Answer answer);

	public List<Answer> findAll();
	
	
	public List<Answer> findAnswerById(Answer answer);
	

}