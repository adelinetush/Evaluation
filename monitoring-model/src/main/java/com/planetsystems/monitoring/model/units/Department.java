package com.planetsystems.monitoring.model.units;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.planetsystems.monitoring.model.ParentEntity;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.budget.Activity;

@Entity
public class Department extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139278481128209592L;
	private String departmentName;
	private String departmentDescription;
	private String departmentCode;
	private Set<User> departmentUser;
	private Set<Activity> activity;

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the departmentDescription
	 */
	public String getDepartmentDescription() {
		return departmentDescription;
	}

	/**
	 * @param departmentDescription
	 *            the departmentDescription to set
	 */
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode
	 *            the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the departmentUser
	 */
	@OneToMany(mappedBy = "department")
	public Set<User> getDepartmentUser() {
		return departmentUser;
	}

	/**
	 * @param departmentUser
	 *            the departmentUser to set
	 */
	public void setDepartmentUser(Set<User> departmentUser) {
		this.departmentUser = departmentUser;
	}

	public void addMember(User user) {
		if (this.departmentUser == null)
			this.departmentUser = new HashSet<User>();

		this.departmentUser.add(user);
		user.setDepartment(this);
	}

	/**
	 * @return the activity
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Department_Activity", joinColumns = @JoinColumn(name = "departmentId"), inverseJoinColumns = @JoinColumn(name = "activityId"))
	public Set<Activity> getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(Set<Activity> activity) {
		this.activity = activity;
	}

	@Transient
	public List<Activity> getActivityList() {
		return new ArrayList<Activity>(this.getActivity());
	}

	@Transient
	public void setActivityList(List<Activity> activityList) {
		this.setActivity(new HashSet<Activity>(activityList));
	}

}
