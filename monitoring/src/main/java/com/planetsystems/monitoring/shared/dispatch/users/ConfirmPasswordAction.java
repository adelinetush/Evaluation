/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.users;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import com.planetsystems.monitoring.model.User;


/**
 * @author Planet Dev002
 *
 */
public class ConfirmPasswordAction extends UnsecuredActionImpl<ConfirmPasswordResult>{

	/**
	 * 
	 */
	private User user;	
	private String password;	

	
	public ConfirmPasswordAction() {
		super();
	}
	
	
	/**
	 * @param user
	 * @param password
	 */
	public ConfirmPasswordAction(User user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password
	 */
	public ConfirmPasswordAction(String password) {
		super();
		this.password = password;
	}
	
	
	

}
