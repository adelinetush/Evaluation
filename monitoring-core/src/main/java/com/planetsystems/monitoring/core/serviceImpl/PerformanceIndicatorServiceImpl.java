package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.PerformanceIndicatorDAO;
import com.planetsystems.monitoring.core.services.PerformanceIndicatorService;
import com.planetsystems.monitoring.model.PerformanceIndicator;
import com.planetsystems.monitoring.model.Questionnaire;

@Service("PerformanceIndicatorService")
@Transactional
public class PerformanceIndicatorServiceImpl implements PerformanceIndicatorService {

	@Autowired
	private PerformanceIndicatorDAO performanceIndicatorDao;

	@Transactional
	public boolean save(PerformanceIndicator performanceIndicator) {
		try {
			if (performanceIndicatorExists(performanceIndicator.getId())) {
				System.out.println("Employee Already exists");
			} else {
				System.out.println("Object to save: " + performanceIndicator.getId());
				performanceIndicatorDao.save(performanceIndicator);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(PerformanceIndicator performanceIndicator) {
		try {
			performanceIndicatorDao.update(performanceIndicator);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(PerformanceIndicator performanceIndicator) {
		try {
			performanceIndicatorDao.delete(performanceIndicator);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}



	public PerformanceIndicator find(String performanceIndicatorId) {
		try {
					
	PerformanceIndicator rawPerformanceIndicator= performanceIndicatorDao.find(performanceIndicatorId);
	
	
	
	return rawPerformanceIndicator;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PerformanceIndicator> findAll() {
		
		try {

			 List<PerformanceIndicator> rawPerformanceIndicators=performanceIndicatorDao.findAll();
			
				
		return rawPerformanceIndicators;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean performanceIndicatorExists(String performanceIndicatorId) {
		try {
			for (PerformanceIndicator bid : performanceIndicatorDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(performanceIndicatorId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public PerformanceIndicator update(PerformanceIndicator selectedPerformanceIndicator) {
		try {
			if (performanceIndicatorExists(selectedPerformanceIndicator.getId())) {
				System.out.println("Employee Already exists");
			} else {
			PerformanceIndicator rawPerformanceIndicator= performanceIndicatorDao.update(selectedPerformanceIndicator);
			
			PerformanceIndicator performanceIndicator= new PerformanceIndicator();
			performanceIndicator.setId(rawPerformanceIndicator.getId());
			performanceIndicator.setComment(rawPerformanceIndicator.getComment());
			
			List<Questionnaire> rawQuestionnaires=rawPerformanceIndicator.getQuestionnaires();
			List<Questionnaire> questionnaires=new ArrayList<Questionnaire>();
			
			if(rawQuestionnaires !=null){
			for(Questionnaire rawQuestionnaire: rawQuestionnaires){
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setId(rawQuestionnaire.getId());
				questionnaire.setComment(rawQuestionnaire.getComment());
				questionnaires.add(questionnaire);
			}
			
			}
			performanceIndicator.setQuestionnaires(questionnaires);
			
			

			return performanceIndicator;
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
	
	public List<PerformanceIndicator> findPerformanceIndicatorById(PerformanceIndicator selectedPerformanceIndicator) {
		Search search=new Search();
		search.addFilterEqual("id", selectedPerformanceIndicator.getId());
		List<PerformanceIndicator> indicators=new ArrayList<PerformanceIndicator>();
		try{
		
		List<PerformanceIndicator> rawPerformanceIndicators=performanceIndicatorDao.search(search);
		
		if(rawPerformanceIndicators !=null){
		for(PerformanceIndicator rawPerformanceIndicator: rawPerformanceIndicators){
			
			
			PerformanceIndicator performanceIndicator= new PerformanceIndicator();
			performanceIndicator.setId(rawPerformanceIndicator.getId());
			performanceIndicator.setComment(rawPerformanceIndicator.getComment());
			
			List<Questionnaire> rawQuestionnaires=rawPerformanceIndicator.getQuestionnaires();
			List<Questionnaire> questionnaires=new ArrayList<Questionnaire>();
			
			if(rawQuestionnaires !=null){
			for(Questionnaire rawQuestionnaire: rawQuestionnaires){
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setId(rawQuestionnaire.getId());
				questionnaire.setComment(rawQuestionnaire.getComment());
				questionnaires.add(questionnaire);
			}
			
			}
			performanceIndicator.setQuestionnaires(questionnaires);
			
			indicators.add(performanceIndicator);
			
		}
		
		
		}
		
		
		
	}catch(Exception e){
		System.out.println(e.getMessage() + "Trying retrieve data");
	}
				
		return indicators;
		
	}

}
