package com.planetsystems.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Planet Dev002
 * 
 */
@Entity
public class Permission extends ParentEntity {

	private static final long serialVersionUID = -4704884664511912874L;
	private String name;
	private String description;

	public Permission() {
		super();
	}

	public Permission(String perm_name, String perm_description) {

		super();
		this.name = perm_name;
		this.description = perm_description;

	}

	@Column(name = "permission_name")
	public String getName() {
		return name;
	}

	public void setName(String perm_name) {
		this.name = perm_name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String perm_description) {
		this.description = perm_description;
	}

	public static String getObjectNameFromPermisionName(Permission p) {
		String subString = "";
		if (p.getName().indexOf('_') != -1)
			subString = p.getName().substring(p.getName().indexOf('_') + 1);
		else
			return p.getName();

		if (subString.indexOf('_') != -1)
			return subString.substring(subString.indexOf('_') + 1);
		else
			return subString;
	}

}
