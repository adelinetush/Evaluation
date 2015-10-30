package com.planetsystems.monitoring.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Role extends ParentEntity{

	
	private static final long serialVersionUID = -8960781440095607569L;
	public static final String DEFAULT_ADMIN_ROLE = "ROLE_ADMINISTRATOR";

	private String name;
	private String description;
	private Set<Permission> permissions;
	private String permissionName;
	private List<User> users;
	private RoleStatus roleStatus;
	public Role() {
		super();
	}

	public Role(String role_name, String role_decription) {
		super();
		this.name = role_name;
		this.description = role_decription;
	}

	public Role(String name, String description, String permissionName) {
		super();
		this.name = name;
		this.description = description;
		this.permissionName = permissionName;
	}

	
	public Role(String role_id, String role_name, String role_decription,
			Set<Permission> permission, List<User> users) {

		this.name = role_name;
		this.description = role_decription;
		this.permissions = permission;
		this.users = users;
	}

	@Column(name = "role_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String role_name) {
		this.name = role_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String role_decription) {
		this.description = role_decription;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	public Set<Permission> getPermissions() {
		return permissions;
	}

	@Transient
	public List<Permission> getPermissionList(){
		List<Permission> permisions=new ArrayList<Permission>(this.getPermissions());
		return permisions;		
	}
	
	public void setPermissions(Set<Permission> permission) {
		this.permissions = permission;
	}
	
	@Transient
	public void setPermissionList(List<Permission> permissions){
		Set<Permission> permissionSet=new HashSet<Permission>(permissions);
		this.setPermissions(permissionSet);
	}

	
	
	public void addPermission(Permission permission){
		if(permission == null){
			permissions=new HashSet<Permission>();
		}
		
		if(!this.permissions.contains(permission)){
			permissions.add(permission);
		}
	}

	public void removePermission(Permission permission) {
		if (this.getPermissions().contains(permission)) {
			this.getPermissions().remove(permission);
		}
	}

	@ManyToMany(mappedBy = "roles")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<User>();
		}

		if (user != null) {
			if (!this.users.contains(user)) {
				users.add(user);
				user.addRole(this);
			}
		}
	}

	public void removeUser(User user) {
		if (user == null || users == null || users.size() == 0) {
			return;
		}

		if (this.getUsers().contains(user)) {
			getUsers().remove(user);
			user.removeRole(this);
		}
	}

	public boolean checkIfDefaultAdminRole() {
		return this.getName().equals(Role.DEFAULT_ADMIN_ROLE);
	}

	public boolean hasPermission(String perm) {
		if (this.permissions != null) {
			for (Permission permission : this.permissions) {
				if (permission.getName().equalsIgnoreCase(perm)) {
					return true;
				}
			}
		}
		return false;
	}

	public String toString() {
		if (this.getName() != null && this.getName().trim().length() > 0)
			return this.getName();

		return super.toString();
	}

	@Transient
	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	@Transient
	public void addPermissionsToRoles(List<Permission> permissions) {
		for(Permission permission:permissions){
			  this.addPermission(permission);
		}	
	}

	@Enumerated(EnumType.ORDINAL)
	public RoleStatus getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(RoleStatus roleStatus) {
		this.roleStatus = roleStatus;
	}

	

}
