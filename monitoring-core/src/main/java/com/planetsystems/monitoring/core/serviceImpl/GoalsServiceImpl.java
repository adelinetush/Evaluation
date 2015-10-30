package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.GoalsDAO;
import com.planetsystems.monitoring.core.services.GoalsService;
import com.planetsystems.monitoring.model.project.Goals;

@Service("GoalService")
@Transactional
public class GoalsServiceImpl implements GoalsService {
	

	@Autowired
	private GoalsDAO goalsDao;
	

	@Transactional
	public boolean save(Goals goals) {
		try {
			if (goalExists(goals.getId())) {
				System.out.println("Goal Already exists");
			} else {
				goalsDao.save(goals);
				
				

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Goals goals) {
		try {
			goalsDao.update(goals);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Goals goals) {
		try {
			goalsDao.delete(goals);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

//	public boolean delete(String goalsId) {
//		try {
//			goalsDao.deleteById(goalsId);
//			return true;
//
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		return false;
//	}

	public Goals find(String goalsId) {
		try {

		Goals rawGoal= goalsDao.find(goalsId);
		
		
          return rawGoal;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Goals> findAll() {
		try {

       List<Goals> rawGoals=goalsDao.findAll();
      
			
			return rawGoals;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean goalExists(String goalsId) {
		try {
			for (Goals bid : goalsDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(goalsId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Goals update(Goals goals) {
		try {
			if (goalExists(goals.getId())) {
				System.out.println("Goal Already exists");
			} else {
	       Goals	rawGoal=goalsDao.update(goals);
	
		
				
				
				return rawGoal;
				
			}
			
			

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public boolean delete(String goalId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Goals> findGoalsByProjectId(String projectId) {
		Search search=new Search();
		search.addFilterEqual("project.id", projectId);
		
		List<Goals> rawGoals=goalsDao.search(search);
		
		
	       return rawGoals;
	}

}
