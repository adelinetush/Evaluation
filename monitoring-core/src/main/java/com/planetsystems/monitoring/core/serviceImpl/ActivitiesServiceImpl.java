package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.ActivitiesDAO;
import com.planetsystems.monitoring.core.services.ActivitiesService;
import com.planetsystems.monitoring.model.project.Activities;
import com.planetsystems.monitoring.model.project.Deliverable;
import com.planetsystems.monitoring.model.project.SpecificObjectives;
import com.planetsystems.monitoring.model.project.Tasks;

@Service("ActivitiesService")
@Transactional
public class ActivitiesServiceImpl implements ActivitiesService {

	@Autowired
	private ActivitiesDAO activityDao;

	@Transactional
	public boolean save(Activities activity) {
		try {
			if (activityExists(activity.getId())) {
				System.out.println("Activity Already exists");
			} else {
				System.out.println("Object to save: " + activity.getId());
				activityDao.save(activity);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Activities activity) {
		try {
			activityDao.update(activity);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Activities activity) {
		try {
			activityDao.delete(activity);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Activities find(String activityId) {
		Activities activities = new Activities();
		try {

			Activities rawActivities = activityDao.find(activityId);
			

				return activities;

			

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Activities> findAll() {
		try {

			List<Activities> rawActivities = activityDao.findAll();

			

				return rawActivities;

			

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean activityExists(String activityId) {
		try {
			for (Activities bid : activityDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(activityId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Activities update(Activities activity) {

		try {
			if (activityExists(activity.getId())) {
				System.out.println("Goal Already exists");
			} else {
				Activities rawActivities = activityDao.update(activity);
				

					return rawActivities;

				}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}

	public List<Activities> findActivitiesBySpecificObjectiveId(
			String specificObjectiveId) {
		Search search = new Search();
		search.addFilterEqual("specificObjective.id", specificObjectiveId);
		return activityDao.search(search);
	}

	public boolean delete(String activityId) {
		// TODO Auto-generated method stub
		return false;
	}

}
