package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.SpecificObjectivesDAO;
import com.planetsystems.monitoring.model.project.SpecificObjectives;

@Repository("SpecificObjectivesDao")
public class SpecificObjectivesDAOImpl extends BaseDaoImpl<SpecificObjectives> implements SpecificObjectivesDAO {
	
}
