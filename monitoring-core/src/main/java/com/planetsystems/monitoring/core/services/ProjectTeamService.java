package com.planetsystems.monitoring.core.services;

import java.util.List;


import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.project.ProjectTitle;

public interface ProjectTeamService {
	public boolean save(ProjectTeam employee);

	public boolean edit(ProjectTeam employee);

	public ProjectTeam update(ProjectTeam team);
	
	public void persist(ProjectTeam team);
	
	public boolean delete(ProjectTeam employee);

	public boolean delete(String employeeId);

	public ProjectTeam find(String employeeId);

	public List<ProjectTeam> findAll();
	
	public List<ProjectTeam> findProjectTeamByProjectId(ProjectTitle project);
}
