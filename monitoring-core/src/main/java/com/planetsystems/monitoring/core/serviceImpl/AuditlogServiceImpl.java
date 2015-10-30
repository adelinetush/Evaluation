package com.planetsystems.monitoring.core.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.AuditEventsDAO;
import com.planetsystems.monitoring.core.daos.AuditSessionDAO;
import com.planetsystems.monitoring.core.services.AuditlogService;
import com.planetsystems.monitoring.model.RecordStatus;
import com.planetsystems.monitoring.model.audit.ActionStatus;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.planetsystems.monitoring.model.audit.AuditSessionTrail;
import com.planetsystems.monitoring.model.audit.Operations;

@Service("auditlogService")
public class AuditlogServiceImpl implements AuditlogService {

	@Autowired
	private AuditEventsDAO auditEventDAO;

	@Autowired
	private AuditSessionDAO auditSessionDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public AuditEventsTrail saveAuditEventTrail(Operations operation,
			ActionStatus actionStatus, String entity)
			{
		Date date = new Date();
		AuditEventsTrail auditEventsTrail = new AuditEventsTrail();
		auditEventsTrail.setDate(date);
		auditEventsTrail.setTime(date);
		auditEventsTrail.setOperation(operation.name());
		auditEventsTrail.setStatus(actionStatus);
		auditEventsTrail.setEntity(entity);
		return auditEventDAO.save(auditEventsTrail);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<AuditEventsTrail> getAuditEventsTrail() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addSortDesc("date");
		search.addSortDesc("time");
		return auditEventDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<AuditEventsTrail> getAuditEventsTrail(int offSet, int limit) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.setFirstResult(offSet);
		search.setMaxResults(limit);
		search.addSortDesc("date");
		search.addSortDesc("time");
		return auditEventDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<AuditEventsTrail> getAuditEventsTrailByDate(Date date) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("date", date);
		search.addSortDesc("date");
		search.addSortDesc("time");
		return auditEventDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<AuditEventsTrail> getAuditEventsTrailByDate(Date date,
			int offSet, int limit) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("date", date);
		search.setFirstResult(offSet);
		search.setMaxResults(limit);
		search.addSortDesc("date");
		search.addSortDesc("time");
		return auditEventDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public AuditSessionTrail saveAuditSessionTrail(
			AuditSessionTrail auditSessionTrail) {
		return auditSessionDAO.save(auditSessionTrail);
	}

}
