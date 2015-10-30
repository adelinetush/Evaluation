package com.planetsystems.monitoring.model.audit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.User;


@Entity
@Table(name="AuditEventsTrail")
public class AuditEventsTrail extends ParentEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6791856819268486677L;
	
	private User user;
	private Date date;
	private Date time;
	private String operation;
	private ActionStatus status;
	private String entity;
	
	public AuditEventsTrail() {
		super();
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Enumerated(EnumType.ORDINAL)
	public ActionStatus getStatus() {
		return status;
	}
	public void setStatus(ActionStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the time
	 */
	@Temporal(TemporalType.TIME)
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * @param id
	 */
	public AuditEventsTrail(String id) {
		super(id);
	}	
	
	
	
}
