package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.DeliverableDAO;
import com.planetsystems.monitoring.model.project.Deliverable;

@Repository("DeliverableDao")
public class DeliverableDAOImpl extends BaseDaoImpl<Deliverable> implements DeliverableDAO {
	
}
