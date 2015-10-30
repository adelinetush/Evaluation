package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.ProjectTitle;


public interface ObjectivesService {
	
	public boolean save(Objectives objective);

	public boolean edit(Objectives objective);

	public boolean delete(Objectives objective);

	public boolean delete(String objectiveId);

	public Objectives find(String objectiveId);
	
	
	public Objectives update(Objectives objective);

	public List<Objectives> findAll();
	
	public List<Objectives> findObjectivesByProjectId(String projectId);
}