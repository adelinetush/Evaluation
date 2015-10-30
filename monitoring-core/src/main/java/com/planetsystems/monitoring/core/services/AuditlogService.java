package com.planetsystems.monitoring.core.services;

import java.util.Date;
import java.util.List;

import com.planetsystems.monitoring.model.audit.ActionStatus;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.planetsystems.monitoring.model.audit.AuditSessionTrail;
import com.planetsystems.monitoring.model.audit.Operations;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;


public interface AuditlogService {
	/**
	 * @param operation
	 * @param actionStatus
	 * @param entity
	 * @return
	 * @throws SessionExpiredException
	 */
	AuditEventsTrail saveAuditEventTrail(Operations operation,
			ActionStatus actionStatus, String entity);

	/**
	 * @param auditSessionTrail
	 * @return
	 */
	public AuditSessionTrail saveAuditSessionTrail(
			AuditSessionTrail auditSessionTrail);

	
	/**
	 * @return
	 */
	public List<AuditEventsTrail> getAuditEventsTrail();

	/**
	 * @param date
	 * @return
	 */
	public List<AuditEventsTrail> getAuditEventsTrailByDate(Date date);

	/**
	 * @param offSet
	 * @param limit
	 * @return
	 */
	public List<AuditEventsTrail> getAuditEventsTrail(int offSet, int limit);

	/**
	 * @param date
	 * @param offSet
	 * @param limit
	 * @return
	 */
	public List<AuditEventsTrail> getAuditEventsTrailByDate(Date date,
			int offSet, int limit);

}
