package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.ObjectivesDAO;
import com.planetsystems.monitoring.model.project.Objectives;

@Repository("ObjectivesDao")
public class ObjectivesDAOImpl extends BaseDaoImpl<Objectives> implements ObjectivesDAO {
	
}
