package com.planetsystems.monitoring.core.services;

import com.planetsystems.monitoring.model.User;

public interface AuthenticationService {

	User authenticate(String username, String password,
			boolean attachUserToSecurityContext);

	Boolean isValidUserPassword(User user, String loginPassword);

	boolean isEnabledUser(User systemUser);

}
