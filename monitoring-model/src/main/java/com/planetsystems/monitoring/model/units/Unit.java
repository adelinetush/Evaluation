package com.planetsystems.monitoring.model.units;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.UnitType;
import com.planetsystems.monitoring.model.User;

@Entity
public class Unit extends ParentEntity{
	
	/**
     * 
     */
	private static final long serialVersionUID = -3562944656742016410L;
	private String name;
	private String abbreviation;
	private UnitType type;	
	private User unitHead;
	private List<User> members;
	private List<Unit> childUnit;
	private Unit parentUnit;
	
	
	public Unit() {
		super();
	}

	/**
	 * Sets the name for the organisation unit
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the name for the organisation unit
	 */
	@Column(name = "unit_name", nullable = false)
	public String getName() {
		return this.name;
	}

	/**
	 * returns the abbreviation of orgUnit's name
	 * 
	 * @return
	 */
	@Column(name = "abbreviation", nullable = true)
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * sets the abbreviation of the orgUnit's name
	 * 
	 * @param abbreviation
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * Sets the type of organistation unit
	 * 
	 * @param type
	 */
	public void setType(UnitType type) {
		this.type = type;
	}

	/**
	 * Gets the type of organistation unit
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "unit_type", nullable = false)
	public UnitType getType() {
		return this.type;
	}

	
	/**
	 * gets the head of this organisation unit
	 * 
	 * @return the unitHead
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_head", nullable = true)
	public User getUnitHead() {
		return unitHead;
	}

	/**
	 * sets the head of this organisation unit
	 * 
	 * @param unitHead
	 *            the unitHead to set
	 */
	public void setUnitHead(User unitHead) {
		this.unitHead = unitHead;
	}

	/**
	 * gets the members of this organisation unit.
	 * 
	 * @return the members
	 */
	@OneToMany
	public List<User> getMembers() {
		return members;
	}

	/**
	 * sets the members of this organisation unit
	 * 
	 * @param members
	 *            the members to set
	 */
	public void setMembers(List<User> members) {
		this.members = members;
	}

	
	/**
	 * gets the parent organisation unit for this unit.
	 * 
	 * @return the parentUnit
	 */
	@ManyToOne
	@JoinColumn(name = "parent_unit_id", nullable = true)
	public Unit getParentUnit() {
		return parentUnit;
	}

	/**
	 * sets the parent organisation unit for this unit.
	 * 
	 * @param parentUnit
	 *            the parentUnit to set
	 */
	public void setParentUnit(Unit parentUnit) {
		this.parentUnit = parentUnit;
	}

	
	/**
	 * adds the given user as a member in the organisation unit.
	 * 
	 * @param user
	 */
	public void addMember(User user) {
		if (this.members == null)
			this.members = new ArrayList<User>();

		this.members.add(user);
		//user.setUnit(this);
	}

	@Override
	public String toString() {
		if (getName() != null)
			return getName();
		return super.toString();
	}

	/**
	 * @return the childUnit
	 */
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "parentUnit")
	public List<Unit> getChildUnit() {
		return childUnit;
	}

	/**
	 * @param childUnit the childUnit to set
	 */
	public void setChildUnit(List<Unit> childUnit) {
		this.childUnit = childUnit;
	}
	
	public void addChildUnit(Unit unit) {
		if (this.childUnit == null)
			this.childUnit = new ArrayList<Unit>();

		this.childUnit.add(unit);
		unit.setParentUnit(this);
	}

	/**
	 * removes the given organisation unit from this unit.
	 * 
	 * @param unit
	 */
	public void removeChildUnit(Unit unit) {
		if (this.childUnit == null)
			return;
		this.childUnit.remove(unit);
	}
		
}
