package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.AnswerDAO;
import com.planetsystems.monitoring.core.services.AnswerService;
import com.planetsystems.monitoring.model.Answer;


@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDAO answerDao;

	@Transactional
	public boolean save(Answer answer) {
		try {
			if (answerExists(answer.getId())) {
				System.out.println("Employee Already exists");
			} else {
				System.out.println("Object to save: " + answer.getId());
				answerDao.save(answer);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Answer answer) {
		try {
			answerDao.update(answer);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Answer answer) {
		try {
			answerDao.delete(answer);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}



	public Answer find(String answerId) {
		try {
			
			Answer rawAnswer=answerDao.find(answerId);
			
			
				return rawAnswer;
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Answer> findAll() {
		try {

			 List<Answer> rawAnswers=answerDao.findAll();
			 
			
			 
			
			return rawAnswers;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean answerExists(String teacherId) {
		try {
			for (Answer bid : answerDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(teacherId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Answer update(Answer answer) {
		try {
			if (answerExists(answer.getId())) {
				System.out.println("Employee Already exists");
			} else {
				

				Answer rawAnswer=answerDao.update(answer);
				
				
				 
					return rawAnswer;
			
				
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	
	public boolean delete(String teacherId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Answer> findAnswerById(Answer selectedAnswer) {
		Search search=new Search();
		search.addFilterEqual("id", selectedAnswer.getId());
		
		List<Answer> rawAnswers= answerDao.search(search);
		
		 return rawAnswers;
}
	
}
