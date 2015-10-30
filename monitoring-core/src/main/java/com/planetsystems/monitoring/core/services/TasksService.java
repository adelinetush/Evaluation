package com.planetsystems.monitoring.core.services;

import java.util.List;
import com.planetsystems.monitoring.model.project.Tasks;


public interface TasksService {
	
	public boolean save(Tasks tasks);

	public boolean edit(Tasks tasks);

	public boolean delete(Tasks tasks);

	public boolean delete(String taskId);

	public Tasks find(String taskId);
	
	public Tasks update(Tasks tasks);
	

	public List<Tasks> findAll();
	
	public List<Tasks> findTasksByActivityId(String activityId);
}