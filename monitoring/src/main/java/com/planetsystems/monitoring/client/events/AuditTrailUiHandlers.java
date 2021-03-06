/**
 * 
 */
package com.planetsystems.monitoring.client.events;

import java.util.Date;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author Planet Developer 001
 *
 */
public interface AuditTrailUiHandlers extends UiHandlers{

	void getAllAuditEvents();
	void getAuditByDateEvents(Date date);
	void getAllAuditSessions();
	void getAuditSessionsByDate(Date date);
}
