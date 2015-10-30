package com.planetsystems.monitoring.model;

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

/**
 * @author Planet Dev002
 * 
 */
//
@MappedSuperclass
public class ParentEntity extends LightEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947367048467122912L;
	private User changedBy;
	private User createdBy;
	private Date dateChanged;
	private Date dateCreated;
	private String id = null;
	private RecordStatus recordStatus = RecordStatus.ACTIVE;

	/**
	 * default constructor
	 */
	public ParentEntity() {
		super();
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

	/**
	 * gets the record status of an entity
	 * 
	 * @return the recordStatus
	 */

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "record_status", nullable = false)
	public RecordStatus getRecordStatus() {
		return recordStatus;
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

	/**
	 * sets the record status of the entity
	 * 
	 * @param recordStatus
	 *            the recordStatus to set
	 */

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}

	public ParentEntity(String id) {
		super();
		this.id = id;
	}

}