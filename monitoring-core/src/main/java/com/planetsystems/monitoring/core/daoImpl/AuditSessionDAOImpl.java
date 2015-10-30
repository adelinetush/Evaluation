package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.AuditSessionDAO;
import com.planetsystems.monitoring.model.audit.AuditSessionTrail;

@Repository("AuditSessionDAO")
public class AuditSessionDAOImpl extends BaseDaoImpl<AuditSessionTrail>
		implements AuditSessionDAO {

}
