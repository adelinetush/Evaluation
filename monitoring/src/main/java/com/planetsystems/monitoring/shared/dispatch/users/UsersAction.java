/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.users;

import java.util.List;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.units.Department;

/**
 * @author Planet Developer 001
 * 
 */
public class UsersAction extends UnsecuredActionImpl<UsersResult> {

	private String operation;
    private String operationLevel;
    
    private int opCode;

	private User user;
	private Role role;
	
	private List<Role> roles;

	private int offSet;
	private int limit;
	
	private List<String> signatures;
	
	private String signature;
	
	private Department department;
	
	public UsersAction(String operation, User user,String operationLevel) {

		this.operation=operation;
		this.user=user;
		this.operationLevel=operationLevel;
		
	}

	public UsersAction(String operation,String operationLevel, Role role) {

		this.operation=operation;
		this.role=role;
		this.operationLevel=operationLevel;
		
	}
	
	public UsersAction(String operation,String operationLevel,User user, List<Role> roles) {

		this.operation=operation;
		this.roles = roles;
		this.operationLevel=operationLevel;
		this.user = user;
		
	}

	public UsersAction(String operation,String operationLevel) {

		this.operation=operation;
		this.operationLevel=operationLevel;
		
	}
	
	public UsersAction(int opCode) {

		this.opCode=opCode;
		
	}
	
	public UsersAction(String operation,String operationLevel,int offSet,int limit) {

		this.operation=operation;
		this.operationLevel=operationLevel;
		this.offSet=offSet;
		this.limit=limit;
		
	}
	
	public UsersAction(String operation,String operationLevel,List<String> signatures) {

		this.operation=operation;
		this.signatures = signatures;
		this.operationLevel=operationLevel;
	
		
	}
	
	public UsersAction(String operation,String operationLevel,List<String> signatures, User user) {

		this.operation=operation;
		this.signatures = signatures;
		this.operationLevel=operationLevel;
		this.user = user;
	
		
	}
	
	public UsersAction(String operation,String operationLevel,String signature, User user) {

		this.operation=operation;
		this.signature = signature;
		this.operationLevel=operationLevel;
		this.user = user;
	
		
	}
	
	public UsersAction(String operation,String operationLevel,String signature) {

		this.operation=operation;
		this.signature = signature;
		this.operationLevel=operationLevel;
	
		
	}
	
	public UsersAction(String operation,String operationLevel,Department department) {

		this.operation=operation;
		this.department = department;
		this.operationLevel=operationLevel;
	
		
	}

	@SuppressWarnings("unused")
	private UsersAction() {

	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @return the operationLevel
	 */
	public String getOperationLevel() {
		return operationLevel;
	}

	/**
	 * @return the offSet
	 */
	public int getOffSet() {
		return offSet;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	
	/**
	 * @return the signatures
	 */
	public List<String> getSignatures() {
		return signatures;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	public int getOpCode() {
		return opCode;
	}
	
	
}
