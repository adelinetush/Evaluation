package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.ProjectTitleDAO;
import com.planetsystems.monitoring.model.project.ProjectTitle;

@Repository("ProjectTitleDao")
public class ProjectTitleDAOImpl extends BaseDaoImpl<ProjectTitle> implements ProjectTitleDAO {
	
}
