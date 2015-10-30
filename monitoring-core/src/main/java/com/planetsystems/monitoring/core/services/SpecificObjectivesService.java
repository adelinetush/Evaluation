package com.planetsystems.monitoring.core.services;

import java.util.List;


import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.SpecificObjectives;

public interface SpecificObjectivesService {
	public boolean save(SpecificObjectives specificObjective);

	public boolean edit(SpecificObjectives specificObjective);

	public boolean delete(SpecificObjectives specificObjective);

	public boolean delete(String specificObjectiveId);

	public SpecificObjectives find(String specificObjectiveId);
	
	
	public SpecificObjectives update(SpecificObjectives specificObjective);

	public List<SpecificObjectives> findAll();
	
	public List<SpecificObjectives> findSpecificObjectivesByObjectiveId(Objectives objective);
}