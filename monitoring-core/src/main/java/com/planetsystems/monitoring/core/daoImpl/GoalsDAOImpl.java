package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.GoalsDAO;
import com.planetsystems.monitoring.model.project.Goals;

@Repository("GoalsDao")
public class GoalsDAOImpl extends BaseDaoImpl<Goals> implements GoalsDAO {
	
}
