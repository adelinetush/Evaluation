/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.users;

import com.gwtplatform.dispatch.shared.Result;

/**
 * @author Planet Dev002
 *
 */
public class ConfirmPasswordResult implements Result{

	/**
	 * 
	 */
	public ConfirmPasswordResult() {
		super();
	}
	
	private boolean userStatus;
	private String serverResponse;
	/**
	 * @return the userStatus
	 */
	public boolean isUserStatus() {
		return userStatus;
	}
	/**
	 * @return the serverResponse
	 */
	public String getServerResponse() {
		return serverResponse;
	}
	/**
	 * @param userStatus
	 * @param serverResponse
	 */
	public ConfirmPasswordResult(boolean userStatus, String serverResponse) {
		super();
		this.userStatus = userStatus;
		this.serverResponse = serverResponse;
	}
	/**
	 * @param userStatus
	 */
	public ConfirmPasswordResult(boolean userStatus) {
		super();
		this.userStatus = userStatus;
	}
	/**
	 * @param serverResponse
	 */
	public ConfirmPasswordResult(String serverResponse) {
		super();
		this.serverResponse = serverResponse;
	}
		
}
