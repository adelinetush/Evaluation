package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.ProjectTitleDAO;
import com.planetsystems.monitoring.core.services.ProjectTitleService;
import com.planetsystems.monitoring.model.project.ProjectTitle;

@Service("ProjectTitleService")
@Transactional
public class ProjectTitleServiceImpl implements ProjectTitleService {

	@Autowired
	private ProjectTitleDAO projectTitleDao;

	@Transactional
	public boolean save(ProjectTitle project) {
		try {
			if (projectTitleExists(project.getId())) {
				System.out.println("Employee Already exists");
			} else {
				projectTitleDao.save(project);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(ProjectTitle teacher) {
		try {
			projectTitleDao.update(teacher);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(ProjectTitle projectTitle) {
		try {
			projectTitleDao.delete(projectTitle);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	// public boolean delete(String employeeId) {
	// try {
	// projectTitleDao.deleteById(employeeId);
	// return true;
	//
	// } catch (Exception ex) {
	// System.out.println(ex.getMessage());
	// }
	// return false;
	// }

	public ProjectTitle find(String projectId) {
		try {
			ProjectTitle rawProject = projectTitleDao.find(projectId);
			
				return rawProject;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<ProjectTitle> findAll() {
		List<ProjectTitle> rawProjects = projectTitleDao.findAll();

		
		return rawProjects;
	}

	public boolean projectTitleExists(String teacherId) {
		try {
			for (ProjectTitle bid : projectTitleDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(teacherId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public ProjectTitle update(ProjectTitle projectTitle) {
		try {
			if (projectTitleExists(projectTitle.getId())) {
				System.out.println("Employee Already exists");
			} else {
				ProjectTitle rawProject = projectTitleDao.update(projectTitle);
				
					return rawProject;
				}

		

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public boolean delete(String teacherId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ProjectTitle> findProjectsByProjectId(ProjectTitle projectTitle) {
		Search search = new Search();
		search.addFilterEqual("id", projectTitle.getId());
		try {
	
			List<ProjectTitle> rawProjects = projectTitleDao.search(search);

			
			return rawProjects;
		
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
