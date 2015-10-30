package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.ObjectivesDAO;
import com.planetsystems.monitoring.core.services.ObjectivesService;
import com.planetsystems.monitoring.model.project.Objectives;

@Service("ObjectiveService")
@Transactional
public class ObjectivesServiceImpl implements ObjectivesService {

	@Autowired
	private ObjectivesDAO objectiveDao;
	

	@Transactional
	public boolean save(Objectives objectives) {
		try {
			if (specificObjectivesExists(objectives.getId())) {
				System.out.println("SpecificObjectives Already exists");
			} else {
				System.out.println("Object to save: " + objectives.getId());
				objectiveDao.save(objectives);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Objectives objectives) {
		try {
			objectiveDao.update(objectives);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Objectives objectives) {
		try {
			objectiveDao.delete(objectives);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

//	public boolean delete(String objectivesId) {
//		try {
//			objectiveDao.deleteById(objectivesId);
//			return true;
//
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		return false;
//	}

	public Objectives find(String objectivesId) {
		try {

         Objectives rawObjectives=objectiveDao.find(objectivesId);
         
         
         
       
         
         return rawObjectives;
         

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Objectives> findAll() {
		try {
			
			List<Objectives> rawObjectives=objectiveDao.findAll();
			
			
             return rawObjectives;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	public boolean specificObjectivesExists(String objectivesId) {
		try {
			for (Objectives bid : objectiveDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(objectivesId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Objectives update(Objectives objective) {
		try {
			if (specificObjectivesExists(objective.getId())) {
				System.out.println("SpecificObjective Already exists");
			} else {
				
				
				Objectives rawObjective=objectiveDao.update(objective);
				
			  
				
				
			return	rawObjective;

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public List<Objectives> findObjectivesByProjectId(String projectId) {
		Search search=new Search();
		search.addFilterEqual("project.id", projectId);

		List<Objectives> rawObjectives=objectiveDao.search(search);
		
		
		return rawObjectives;
	}
	
	
	public boolean delete(String objectiveId) {
		// TODO Auto-generated method stub
		return false;
	}

}
