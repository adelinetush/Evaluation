package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.TeamMemberDAO;
import com.planetsystems.monitoring.core.services.TeamMemberService;
import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.project.TeamMember;

@Service("TeamMemberService")
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {

	@Autowired
	private TeamMemberDAO teamMemberDao;

	@Transactional
	public boolean save(TeamMember teamMember) {
		try {
			if (teamMemberExists(teamMember.getId())) {
				System.out.println("Employee Already exists");
			} else {
				System.out.println("Object to save: " + teamMember.getId());
				teamMemberDao.save(teamMember);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(TeamMember teamMember) {
		try {
			teamMemberDao.update(teamMember);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(TeamMember teamMember) {
		try {
			teamMemberDao.delete(teamMember);
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

	public TeamMember find(String teamMemberId) {
		try {

			
			TeamMember rawTeamMember= teamMemberDao.find(teamMemberId);

			
			
			return rawTeamMember;
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<TeamMember> findAll() {
		
	
		try {

			
			List<TeamMember> rawTeamMembers = teamMemberDao.findAll();

         return  rawTeamMembers;
         
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean teamMemberExists(String teamMemberId) {
		try {
			for (TeamMember teamMember : teamMemberDao.findAll()) {
				if (teamMember.getId().equalsIgnoreCase(teamMemberId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public TeamMember update(TeamMember teamMember) {
		try {
			if (teamMemberExists(teamMember.getId())) {
				System.out.println("Team Member Already exists");
			} else {
				TeamMember rawTeamMember= teamMemberDao.update(teamMember);

				
				
				return rawTeamMember;
				}
			

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public boolean delete(String teacherId) {
		return false;
	}

	public List<TeamMember> findTeamByTeamId(ProjectTeam projectTeam) {
		Search search = new Search();
		search.addFilterEqual("id", projectTeam);

		List<TeamMember> rawMembers = teamMemberDao.search(search);
	

		return rawMembers;
	}

}
