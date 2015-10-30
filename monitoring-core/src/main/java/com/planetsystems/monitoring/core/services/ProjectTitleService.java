package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.ProjectTitle;

public interface ProjectTitleService {
	public boolean save(ProjectTitle teacher);

	public boolean edit(ProjectTitle teacher);

	public boolean delete(ProjectTitle teacher);

	public boolean delete(String teacherId);

	public ProjectTitle find(String teacherId);
	
	public ProjectTitle update(ProjectTitle teacher);

	public List<ProjectTitle> findAll();
	
	
	public List<ProjectTitle> findProjectsByProjectId(ProjectTitle projectTitle);
	

}


