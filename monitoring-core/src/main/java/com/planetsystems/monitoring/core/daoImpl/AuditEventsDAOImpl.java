package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.AuditEventsDAO;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;


@Repository("AuditEventsDAO")
public class AuditEventsDAOImpl extends BaseDaoImpl<AuditEventsTrail> implements
		AuditEventsDAO {

}
