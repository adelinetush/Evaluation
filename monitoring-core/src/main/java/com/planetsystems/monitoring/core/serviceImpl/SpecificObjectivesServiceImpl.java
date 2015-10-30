package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.SpecificObjectivesDAO;
import com.planetsystems.monitoring.core.services.SpecificObjectivesService;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.SpecificObjectives;

@Service("SpecificObjectiveService")
@Transactional
public class SpecificObjectivesServiceImpl implements SpecificObjectivesService {

	@Autowired
	private SpecificObjectivesDAO specificObjectiveDao;

	@Transactional
	public boolean save(SpecificObjectives specificObjectives) {
		try {
			if (specificObjectivesExists(specificObjectives.getId())) {
				System.out.println("SpecificObjectives Already exists");
			} else {
				System.out.println("Object to save: "
						+ specificObjectives.getId());
				specificObjectiveDao.save(specificObjectives);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(SpecificObjectives specificObjectives) {
		try {

			specificObjectiveDao.update(specificObjectives);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(SpecificObjectives specificObjectives) {
		try {
			specificObjectiveDao.delete(specificObjectives);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	// public boolean delete(String specificObjectivesId) {
	// try {
	// specificObjectiveDao.deleteById(specificObjectivesId);
	// return true;
	//
	// } catch (Exception ex) {
	// System.out.println(ex.getMessage());
	// }
	// return false;
	// }

	public SpecificObjectives find(String specificObjectivesId) {
		try {

			SpecificObjectives rawSpecObj = specificObjectiveDao
					.find(specificObjectivesId);

			

				return rawSpecObj;


		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<SpecificObjectives> findAll() {
		try {

			List<SpecificObjectives> rawSpecs = specificObjectiveDao.findAll();

			

			return rawSpecs;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean specificObjectivesExists(String specificObjectivesId) {
		try {
			for (SpecificObjectives bid : specificObjectiveDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(specificObjectivesId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public SpecificObjectives update(SpecificObjectives specificObjective) {
		try {
			if (specificObjectivesExists(specificObjective.getId())) {
				System.out.println("SpecificObjective Already exists");
			} else {
				
			
          SpecificObjectives rawSpecObj=specificObjectiveDao.update(specificObjective);
			
			
				
				return rawSpecObj;

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public List<SpecificObjectives> findSpecificObjectivesByObjectiveId(
			Objectives selectedObjective) {
		Search search = new Search();
		search.addFilterEqual("objective.id", selectedObjective.getId());

		List<SpecificObjectives> rawSpecs = specificObjectiveDao.search(search);
		
		return rawSpecs;
	}

	public boolean delete(String specificObjectiveId) {
		return false;
	}

}
