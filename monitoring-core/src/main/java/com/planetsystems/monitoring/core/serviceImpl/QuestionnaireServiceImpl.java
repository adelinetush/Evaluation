package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.QuestionnaireDAO;
import com.planetsystems.monitoring.core.services.QuestionnaireService;
import com.planetsystems.monitoring.model.Question;
import com.planetsystems.monitoring.model.Questionnaire;

@Service("QuestionnaireService")
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	private QuestionnaireDAO questionnaireDao;

	@Transactional
	public boolean save(Questionnaire questionnaire) {
		try {
			if (questionnaireExists(questionnaire.getId())) {
				System.out.println("Employee Already exists");
			} else {
				System.out.println("Object to save: " + questionnaire.getId());
				questionnaireDao.save(questionnaire);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Questionnaire questionnaire) {
		try {
			questionnaireDao.update(questionnaire);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Questionnaire questionnaire) {
		try {
			questionnaireDao.delete(questionnaire);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}



	public Questionnaire find(String questionnaireId) {
		try {
					
		Questionnaire rawQuestionnaire=questionnaireDao.find(questionnaireId);
		
		if(rawQuestionnaire !=null){
			Questionnaire questionnaire=new Questionnaire();
			
			questionnaire.setId(rawQuestionnaire.getId());
			questionnaire.setComment(rawQuestionnaire.getComment());
			
			List<Question> rawQuestions=rawQuestionnaire.getQuestions();
			
			List<Question> questions=new ArrayList<Question>();
			
			for(Question rawQuestion: rawQuestions){
				Question question=new Question();
				question.setId(rawQuestion.getId());
				question.setQuery(rawQuestion.getQuery());
				
				questions.add(question);
			}
			
			
			return questionnaire;
		}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Questionnaire> findAll() {
		try {

			List<Questionnaire> questionnaires=new ArrayList<Questionnaire>();
			
			 List<Questionnaire> rawQuestionnaires=questionnaireDao.findAll();
			 
			for(Questionnaire rawQuestionnaire: rawQuestionnaires){
							
				Questionnaire questionnaire=new Questionnaire();
				
				questionnaire.setId(rawQuestionnaire.getId());
				questionnaire.setComment(rawQuestionnaire.getComment());
				
				List<Question> rawQuestions=rawQuestionnaire.getQuestions();
				
				List<Question> questions=new ArrayList<Question>();
				
				for(Question rawQuestion: rawQuestions){
					Question question=new Question();
					question.setId(rawQuestion.getId());
					question.setQuery(rawQuestion.getQuery());
					
					questions.add(question);
				}
				
				questionnaires.add(questionnaire);
			}
			
			return questionnaires;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean questionnaireExists(String questionnaireId) {
		try {
			for (Questionnaire bid : questionnaireDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(questionnaireId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Questionnaire update(Questionnaire selectedQuestionnaire) {
		try {
			if (questionnaireExists(selectedQuestionnaire.getId())) {
				System.out.println("Employee Already exists");
			} else {
		Questionnaire rawQuestionnaire=questionnaireDao.update(selectedQuestionnaire);
			
              Questionnaire questionnaire=new Questionnaire();
			
			questionnaire.setId(rawQuestionnaire.getId());
			questionnaire.setComment(rawQuestionnaire.getComment());
			
			List<Question> rawQuestions=rawQuestionnaire.getQuestions();
			
			List<Question> questions=new ArrayList<Question>();
			
			for(Question rawQuestion: rawQuestions){
				Question question=new Question();
				question.setId(rawQuestion.getId());
				question.setQuery(rawQuestion.getQuery());
				
				questions.add(question);
			}
			questionnaire.setId(rawQuestionnaire.getId());
			
			questionnaire.setComment(rawQuestionnaire.getComment());
			
			questionnaire.setQuestions(questions);
			
			
			return questionnaire;

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	
	public boolean delete(String questionId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Questionnaire> findQuestionnaireById(Questionnaire selectedQuestionnaire) {
		Search search=new Search();
		search.addFilterEqual("id", selectedQuestionnaire.getId());
		
		try{
		 List<Questionnaire> rawQuestionnaires=questionnaireDao.search(search);
			
		 
		 List<Questionnaire> questionnaires=new ArrayList<Questionnaire>();
		 
		for(Questionnaire rawQuestionnaire: rawQuestionnaires){
						
			Questionnaire questionnaire=new Questionnaire();
			
			questionnaire.setId(rawQuestionnaire.getId());
			questionnaire.setComment(rawQuestionnaire.getComment());
			
			List<Question> rawQuestions=rawQuestionnaire.getQuestions();
			
			List<Question> questions=new ArrayList<Question>();
			
			for(Question rawQuestion: rawQuestions){
				Question question=new Question();
				question.setId(rawQuestion.getId());
				question.setQuery(rawQuestion.getQuery());
				
				questions.add(question);
			}
			
			questionnaires.add(questionnaire);
		
		}
		
		return questionnaires;
		
		}catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

}
