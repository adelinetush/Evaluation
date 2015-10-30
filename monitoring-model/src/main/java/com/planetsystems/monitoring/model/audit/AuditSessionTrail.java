package com.planetsystems.monitoring.model.audit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.User;

@Entity
@Table(name="AuditSessionTrail")
public class AuditSessionTrail extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5463330435314192703L;

	private String ip;
	private User user;
	private Date date;
	private Date loginTime;
	private Date logoutTime;
	/*private String operation;
	private ActionStatus status;
	private String table;*/
	
	public AuditSessionTrail() {
		super();
	}

	public AuditSessionTrail(User user, Date date, Date loginTime, Date logoutTime,
			String ip) {
		super();
		this.ip = ip;
		this.user = user;
		this.date = date;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		/*this.operation = operation;
		this.status = status;
		this.table = table;*/
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIME)
	public Date getLoginTime() {
		return loginTime;
	}

	
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@Temporal(TemporalType.TIME)
	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
