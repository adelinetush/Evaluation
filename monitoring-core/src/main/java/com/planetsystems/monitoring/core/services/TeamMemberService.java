package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.project.TeamMember;

public interface TeamMemberService {
	
	public boolean save(TeamMember teamMember);

	public boolean edit(TeamMember teamMember);

	public boolean delete(TeamMember teamMember);

	public boolean delete(String memberId);

	public TeamMember find(String memberId);

	public TeamMember update(TeamMember teamMember);

	public List<TeamMember> findAll();

	public List<TeamMember> findTeamByTeamId(ProjectTeam team);

}