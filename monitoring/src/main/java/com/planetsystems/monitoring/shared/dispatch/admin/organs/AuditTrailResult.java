/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.planetsystems.monitoring.model.audit.AuditSessionTrail;

/**
 * @author Planet Developer 001
 * 
 */
public class AuditTrailResult implements Result {

	private String serverResponse;
	private List<AuditEventsTrail> events;
	private List<AuditSessionTrail> sessions;
	private boolean operationStatus;

	public AuditTrailResult(boolean operationStatus,
			List<AuditEventsTrail> events) {

		this.operationStatus = operationStatus;
		this.events = events;

	}

	public AuditTrailResult(List<AuditSessionTrail> sessions,
			boolean operationStatus) {

		this.operationStatus = operationStatus;
		this.sessions = sessions;

	}
	
	public AuditTrailResult(boolean operationStatus,String serverResponse) {

		this.operationStatus = operationStatus;
		this.serverResponse = serverResponse;

	}

	@SuppressWarnings("unused")
	private AuditTrailResult() {

	}

	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}

	/**
	 * @return the events
	 */
	public List<AuditEventsTrail> getEvents() {
		return events;
	}

	/**
	 * @return the sessions
	 */
	public List<AuditSessionTrail> getSessions() {
		return sessions;
	}

	/**
	 * @return the operationStatus
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}
}
