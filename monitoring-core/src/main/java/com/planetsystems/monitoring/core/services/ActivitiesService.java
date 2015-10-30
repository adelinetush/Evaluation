package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.project.Activities;

public interface ActivitiesService {
	
	public boolean save(Activities activity);

	public boolean edit(Activities activity);

	public boolean delete(Activities activity);

	public boolean delete(String activityId);

	public Activities find(String activityId);
	
	public Activities update(Activities activity);

	public List<Activities> findAll();
	
	
	public List<Activities>findActivitiesBySpecificObjectiveId(String specificObjectiveId);
}