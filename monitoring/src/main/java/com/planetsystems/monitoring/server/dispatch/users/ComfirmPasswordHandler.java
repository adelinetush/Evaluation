/**
 * 
 */
package com.planetsystems.monitoring.server.dispatch.users;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.core.security.util.ProcnetSecurityUtil;
import com.planetsystems.monitoring.core.services.AuthenticationService;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.shared.dispatch.users.ConfirmPasswordAction;
import com.planetsystems.monitoring.shared.dispatch.users.ConfirmPasswordResult;

/**
 * @author Planet Dev002
 *
 */
public class ComfirmPasswordHandler extends AbstractActionHandler<ConfirmPasswordAction, ConfirmPasswordResult>{

	@Autowired
	private AuthenticationService authenticationService;
	
	public ComfirmPasswordHandler() {
		super(ConfirmPasswordAction.class);
	}

	public ConfirmPasswordResult execute(ConfirmPasswordAction action,
			ExecutionContext context) throws ActionException {
		ConfirmPasswordResult result=null;
		
		boolean userStatus=false;
		
		try {
			User user=ProcnetSecurityUtil.getLoggedInUser();
			if(action.getPassword()!=null){
				boolean status=	authenticationService.isValidUserPassword(user, action.getPassword());
				userStatus=status;
				
				result=new ConfirmPasswordResult(userStatus);
			}
			
		} catch (SessionExpiredException e) {
			result=new ConfirmPasswordResult("Error "+e.toString());
		}
		
		return result;
	}

	public void undo(ConfirmPasswordAction action,
			ConfirmPasswordResult result, ExecutionContext context)
			throws ActionException {
		
		
	}



}
