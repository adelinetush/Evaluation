package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.TeamMemberDAO;
import com.planetsystems.monitoring.model.project.TeamMember;

@Repository("TeamMemberDao")
public class TeamMemberDAOimpl extends BaseDaoImpl<TeamMember> implements TeamMemberDAO {
	
}