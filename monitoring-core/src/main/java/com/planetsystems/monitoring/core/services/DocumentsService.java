package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.project.Documents;
import com.planetsystems.monitoring.model.project.ProjectTitle;


public interface DocumentsService {
	
	public boolean save(Documents documents);

	public boolean edit(Documents document);

	
	public boolean delete(Documents document);

	public boolean delete(String documentId);

	public Documents find(String documentId);
	
	public Documents update(Documents document);

	public List<Documents> findAll();
	
	public List<ProjectTitle> findProjectByProjectId(ProjectTitle projectTitle);
}