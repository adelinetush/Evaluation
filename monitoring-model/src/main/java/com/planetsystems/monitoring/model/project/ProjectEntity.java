package com.planetsystems.monitoring.model.project;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.gilead.pojo.gwt.LightEntity;

import org.hibernate.annotations.GenericGenerator;

import com.planetsystems.monitoring.model.PerformanceIndicator;
import com.planetsystems.monitoring.model.Questionnaire;
import com.planetsystems.monitoring.model.RecordStatus;
import com.planetsystems.monitoring.model.User;

/**
 * @author Planet Dev002
 * 
 */
//
@MappedSuperclass
public class ProjectEntity extends LightEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 804372976159244900L;
	
	private User changedBy;
	private User createdBy;
	private Date dateChanged;
	private Date dateCreated;
	private String id = null;
	
	private PerformanceIndicator performanceIndicator;
	
	
	
	private RecordStatus recordStatus = RecordStatus.ACTIVE;

	public ProjectEntity() {
		super();
		
	}

	public ProjectEntity(Questionnaire questionnaire) {
		super();
	}


	@Enumerated(EnumType.ORDINAL)
	@Column(name = "record_status", nullable = false)
	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}

	
	@ManyToOne
	@JoinColumn(name = "changed_by", nullable = true)
	public User getChangedBy() {
		return this.changedBy;
	}

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = true)
	public User getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * gets the record status of an entity
	 * 
	 * @return the recordStatus
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_changed", nullable = true)
	public Date getDateChanged() {
		return this.dateChanged;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = true)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * gets the id of the entity.
	 * 
	 * @return {@link #id}
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	
	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}

	public void setCreatedBy(User user) {
		this.createdBy = user;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * sets the id of the entity.
	 * 
	 * @param id
	 *            {@link #id}
	 */
	public void setId(String id) {
		this.id = id;
	}

	public PerformanceIndicator getPerformanceIndicator() {
		return performanceIndicator;
	}

	public void setPerformanceIndicator(PerformanceIndicator performanceIndicator) {
		this.performanceIndicator = performanceIndicator;
	}

	
	
}