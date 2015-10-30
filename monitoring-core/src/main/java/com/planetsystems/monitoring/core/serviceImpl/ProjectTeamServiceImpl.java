package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.ProjectTeamDAO;
import com.planetsystems.monitoring.core.services.ProjectTeamService;
import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.project.ProjectTitle;


@Service("EmployeeService")
@Transactional
public class ProjectTeamServiceImpl implements ProjectTeamService {
	
	@Autowired
	private ProjectTeamDAO projectTeamDao;

	@Transactional
	public boolean save(ProjectTeam teamMember) {
		try {
		if (teamExists(teamMember.getId())) {
				System.out.println("Employee Already exists");
			} else {
				projectTeamDao.save(teamMember);
				return true;
			}
		

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(ProjectTeam employee) {
		try {
	projectTeamDao.update(employee);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(ProjectTeam teamMember) {
		try {
			projectTeamDao.delete(teamMember);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}



	public ProjectTeam find(String memberId) {
		try {

			ProjectTeam rawProjectTeam=	 projectTeamDao.find(memberId);
			
			
				
				
				return rawProjectTeam;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<ProjectTeam> findAll() {
			
			List<ProjectTeam> projectTeams=new ArrayList<ProjectTeam>();
			
			try{
			List<ProjectTeam> rawProjectTeams=projectTeamDao.findAll();
			
			return rawProjectTeams;
			}catch(Exception ex){
				
			}
			return projectTeams;

		
	}

	public boolean teamExists(String teamId) {
		try {
			for (ProjectTeam team : projectTeamDao.findAll()) {
				if (team.getId().equalsIgnoreCase(teamId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(String employeeId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ProjectTeam> findProjectTeamByProjectId(ProjectTitle project) {
		Search search=new Search();
		search.addFilterEqual("id", project);
		
		try{
		List<ProjectTeam> rawProjectTeams=projectTeamDao.search(search);
		
		return rawProjectTeams;
		}catch(Exception ex){
			
		}
		return null;
	}

	public ProjectTeam update(ProjectTeam team) {
		try {
			if (teamExists(team.getId())) {
				System.out.println("Team  Already exists");
			} else {
				
				ProjectTeam rawProjectTeam=	 projectTeamDao.update(team);
				
				
					
					return rawProjectTeam;
				}

			

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public void persist(ProjectTeam team) {
		try {
			if (teamExists(team.getId())) {
				System.out.println("Team  Already exists");
			} else {
				projectTeamDao.merge(team);
				System.out.println("Project team saved successfully");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		
	}
}
