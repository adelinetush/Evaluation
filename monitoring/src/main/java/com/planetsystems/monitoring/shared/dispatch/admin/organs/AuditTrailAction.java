/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.planetsystems.monitoring.model.audit.AuditSessionTrail;

/**
 * @author Planet Developer 001
 * 
 */
public class AuditTrailAction extends UnsecuredActionImpl<AuditTrailResult> {

	private String operationLevel;
	private String operation;
	private AuditSessionTrail sessionTrail;
	private AuditEventsTrail eventTrail;

	public AuditTrailAction(String operationLevel, String operation) {

		this.operationLevel = operationLevel;
		this.operation = operation;

	}

	public AuditTrailAction(String operationLevel, AuditSessionTrail sessionTrail) {

		this.operationLevel = operationLevel;
		this.sessionTrail = sessionTrail;

	}

	public AuditTrailAction(String operationLevel, AuditEventsTrail eventTrail) {

		this.operationLevel = operationLevel;
		this.eventTrail = eventTrail;

	}

	@SuppressWarnings("unused")
	private AuditTrailAction() {

	}

	/**
	 * @return the operationLevel
	 */
	public String getOperationLevel() {
		return operationLevel;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the sessionTrail
	 */
	public AuditSessionTrail getSessionTrail() {
		return sessionTrail;
	}

	/**
	 * @return the eventTrail
	 */
	public AuditEventsTrail getEventTrail() {
		return eventTrail;
	}
}
