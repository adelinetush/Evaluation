package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.ProjectTitle;


public interface GoalsService {
	
	public boolean save(Goals goal);

	public boolean edit(Goals goal);

	public boolean delete(Goals goal);
	

	public boolean delete(String goalId);

	public Goals find(String goalId);
	
	public Goals update(Goals goal);

	public List<Goals> findAll();
	
	
	public List<Goals> findGoalsByProjectId(String projectId);
}