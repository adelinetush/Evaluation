package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.QuestionDAO;
import com.planetsystems.monitoring.core.services.QuestionService;
import com.planetsystems.monitoring.model.Answer;
import com.planetsystems.monitoring.model.Question;

@Service("QuestionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDao;

	@Transactional
	public boolean save(Question answer) {
		try {
			if (questionExists(answer.getId())) {
				System.out.println("Employee Already exists");
			} else {
				System.out.println("Object to save: " + answer.getId());
				questionDao.save(answer);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Question question) {
		try {
			questionDao.update(question);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Question question) {
		try {
			questionDao.delete(question);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Question find(String questionId) {
		try {

			Question rawQuestion = questionDao.find(questionId);

			if (rawQuestion != null) {
				Question question = new Question();
				question.setId(rawQuestion.getId());
				question.setQuery(rawQuestion.getQuery());

				List<Answer> answers = new ArrayList<Answer>();
				for (Answer rawAnswer : question.getAnswers()) {
					Answer answer = new Answer();

					answer.setId(rawAnswer.getId());
					answer.setAnswer(rawAnswer.getAnswer());

					answers.add(answer);
				}
				question.setAnswers(answers);

				return question;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Question> findAll() {
		try {


			List<Question> rawQuestions = questionDao.findAll();

			

			return rawQuestions;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean questionExists(String questionId) {
		try {
			for (Question bid : questionDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(questionId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Question update(Question selectedQuestion) {
		try {
			if (questionExists(selectedQuestion.getId())) {
				System.out.println("Employee Already exists");
			} else {
				
				Question rawQuestion=questionDao.update(selectedQuestion);
				

						return rawQuestion;
			}

		}catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public boolean delete(String questionId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Question> findQuestionById(Question selectedQuestion) {
		Search search = new Search();
		search.addFilterEqual("id", selectedQuestion.getId());
		try{
		List<Question> rawQuestions=questionDao.search(search);
		
		return rawQuestions;
		
	}catch (Exception ex) {
		System.out.println(ex.getMessage() + "Trying to retrieve");
	}

             return null;
}
}
