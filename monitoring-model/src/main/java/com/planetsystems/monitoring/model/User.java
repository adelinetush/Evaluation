package com.planetsystems.monitoring.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;


@Entity
@Table(name = "Usr")
public class User extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1925072376013941114L;
	private String username;
	private String user_password;
	private String salt;
	private String secretQuestion;
	private String secretAnswer;
	private Date dateOfLastPasswordChange;
	private boolean changePassword;
	private boolean changePasswordAtNextLogin = false;
	private String clearTextPassword;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String voteId;
	private String voteName;
	private Boolean status;
	private List<Permission> permissions;
	private Set<Role> roles;
	private UserCatergory userCatergory;
	private String roleName;
	private String roleId;
	private Department department;
	private Division division;
	private Section section;
	
	private boolean registered;
	
	
	/**
	 * @return the username
	 */
	public User() {

	}

	public User(String id, String username, String password, Boolean status) {

		this.username = username;
		this.user_password = password;
		this.status = status;

	}

	public User(String username, String password, String fName, String lName, String email, String roleName, String phoneNumber, String secretQuestion, String secretAnswer, String clearTextPassword) {

		this.username = username;
		this.firstName = fName;
		this.lastName = lName;
		this.setRoleName(roleName);
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.clearTextPassword = clearTextPassword;

	}

	public User(String username, String password, String fName, String lName, String email, String phoneNumber, Set<Role> roles, String secretQuestion, String secretAnswer, String clearTextPassword) {

		this.username = username;
		this.roles = roles;
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.clearTextPassword = clearTextPassword;
	}

	public User(String username, String password, String fName, String lName, String email, String phoneNumber, String roleName) {

		this.username = username;
		this.setRoleName(roleName);
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
		this.phoneNumber = phoneNumber;

	}

	@Column(name = "username", nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return user_password;
	}

	public void setPassword(String password) {
		this.user_password = password;
	}

	@Column(name = "salt", nullable = false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "secret_question", nullable = false)
	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	@Column(name = "secret_answer", nullable = true)
	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	/**
	 * @return the fName
	 */
	@Column(name = "first_name", nullable = true)
	public String getfName() {
		return firstName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.firstName = fName;
	}

	/**
	 * @return the lName
	 */
	@Column(name = "last_name", nullable = true)
	public String getlName() {
		return lastName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName) {
		this.lastName = lName;
	}

	/**
	 * @return the email
	 */
	@Column(name = "email_address", nullable = true)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @return the roleId
	 */
	@Transient
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setRolesForUser(List<Role> rolesList) {
		Set<Role> roleSet = new HashSet<Role>(rolesList);
		this.setRoles(roleSet);
	}

	public void setRole(Role role) {
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(role);
		this.setRoles(roleSet);
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_last_password_change", nullable = true)
	public Date getDateOfLastPasswordChange() {
		return dateOfLastPasswordChange;
	}

	public void setDateOfLastPasswordChange(Date dateOfLastPasswordChange) {
		this.dateOfLastPasswordChange = dateOfLastPasswordChange;
	}

	@Transient
	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	@Column(name = "change_password_at_next_login", nullable = false)
	public boolean isChangePasswordAtNextLogin() {
		return changePasswordAtNextLogin;
	}

	public void setChangePasswordAtNextLogin(boolean changePasswordAtNextLogin) {
		this.changePasswordAtNextLogin = changePasswordAtNextLogin;
	}

	@Transient
	public String getClearTextPassword() {
		return clearTextPassword;
	}

	public void setClearTextPassword(String clearTextPassword) {
		this.clearTextPassword = clearTextPassword;
	}

	public boolean hasNewPassword() {
		return (clearTextPassword != null && clearTextPassword.trim().length() > 0);
	}

	@Column(name = "user_status", nullable = false)
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean user_status) {
		this.status = user_status;
	}

	public void addRole(Role role) {
		if (roles == null) {
			roles = new HashSet<Role>();
		}

		if (!this.roles.contains(role)) {
			roles.add(role);
		}
	}

	public void removeRole(Role role) {
		if (roles != null) {
			for (Role r : roles) {
				if (r.getName().equals(role.getName())) {
					roles.remove(role);
					break;
				}
			}
		}
	}

	public List<Permission> findPermissions() {
		List<Permission> permissions = null;
		HashSet<Permission> permissionsSet = new HashSet<Permission>();
		
		if (roles != null && roles.size() > 0) {
			permissions = new ArrayList<Permission>();
			for (Role role : roles) {
				if (role.getPermissions() != null && role.getPermissions().size() > 0) {
					
					for (Permission perm : role.getPermissions()) {
						
						permissionsSet.add(perm);
						
					}
				}
			}
			
			for( Permission permis: permissionsSet){
				
				permissions.add(permis);
			}
			
		}
		return permissions;
	}

	public boolean hasAdministrativePrivileges() {
		if (roles != null) {
			for (Role role : roles) {
				if (role.checkIfDefaultAdminRole()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasPermission(String perm) {
		if (this.hasAdministrativePrivileges())
			return true;

		if (this.roles != null) {
			for (Role role : this.roles) {
				if (role.hasPermission(perm)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.lastName != null && this.firstName != null)
			return this.firstName + " " + this.lastName;
		else if (this.username != null)
			return this.username;

		return super.toString();
	}

	@Transient
	public String getFullName() {
		StringBuilder builder = new StringBuilder();
		if (this.lastName != null)
			builder.append(this.lastName);

		builder.append(" ");

		if (this.firstName != null)
			builder.append(this.firstName);

		return builder.toString();
	}

	@Transient
	public List<Role> getRolesList() {

		List<Role> rolesList = new ArrayList<Role>(this.getRoles());

		return rolesList;
	}

	@Transient
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Transient
	public void addRoleToUser(List<Role> roles) {
		for (Role role : roles) {
			this.addRole(role);
		}
	}

	/**
	 * @return the voteId
	 */
	@Transient
	public String getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId
	 *            the voteId to set
	 */
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the voteName
	 */
	@Transient
	public String getVoteName() {
		return voteName;
	}

	/**
	 * @param voteName
	 *            the voteName to set
	 */
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	@Enumerated(EnumType.ORDINAL)
	public UserCatergory getUserCatergory() {
		return userCatergory;
	}

	public void setUserCatergory(UserCatergory userCatergory) {
		this.userCatergory = userCatergory;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the department
	 */
	@ManyToOne
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */

	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the division
	 */
	@ManyToOne
	public Division getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(Division division) {
		this.division = division;
	}

	/**
	 * @return the section
	 */
	@ManyToOne
	public Section getSection() {
		return section;
	}

	/**
	 * @param section
	 *            the section to set
	 */
	public void setSection(Section section) {
		this.section = section;
	}

	@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @return the registered
	 */
	public boolean isRegistered() {
		return registered;
	}

	/**
	 * @param registered the registered to set
	 */
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

}
