package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.Questionnaire;

public interface QuestionnaireService {
	
	public boolean save(Questionnaire questionnaire);

	public boolean edit(Questionnaire questionnaire);

	public boolean delete(Questionnaire questionnaire);

	public boolean delete(String questionnaire);

	public Questionnaire find(String questionnaireId);
	
	public Questionnaire update(Questionnaire questionnaire);

	public List<Questionnaire> findAll();
	
	
	public List<Questionnaire> findQuestionnaireById(Questionnaire questionnaire);
	

}
