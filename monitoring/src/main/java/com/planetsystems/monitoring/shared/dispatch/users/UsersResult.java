/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.users;

import java.util.List;


import com.gwtplatform.dispatch.shared.Result;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.User;

/**
 * @author Planet Developer 001
 * 
 */
public class UsersResult implements Result {

	private String serverResponse;

	private boolean operationStatus;

	private List<User> users;
	private List<Role> roles;
	private List<Permission> permissions;
	
	private User user;
	
	private boolean signatureState;

	public UsersResult(boolean operationStatus, List<User> users,String serverResponse) {

		this.operationStatus=operationStatus;
		this.users=users;
		this.serverResponse=serverResponse;
	}



	public UsersResult(boolean operationStatus, List<Role> roles) {

		this.operationStatus=operationStatus;
		this.roles=roles;
		
	}
	
	public UsersResult( List<Permission> permissions,boolean operationStatus) {

		this.permissions=permissions;
		this.operationStatus=operationStatus;
		
	}
	
	

	public UsersResult(boolean operationStatus, String serverResponse) {

		this.operationStatus=operationStatus;
		this.serverResponse=serverResponse;
		
	}
	
	public UsersResult(boolean operationStatus, User user) {

		this.operationStatus=operationStatus;
		this. user= user;
		
	}


	@SuppressWarnings("unused")
	private UsersResult(){
		
		}

	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}



	/**
	 * @return the operationStatus
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}



	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}



	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}



	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}



	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @return the signatureState
	 */
	public boolean isSignatureState() {
		return signatureState;
	}
}
