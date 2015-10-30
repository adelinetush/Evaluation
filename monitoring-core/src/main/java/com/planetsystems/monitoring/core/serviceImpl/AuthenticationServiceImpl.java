package com.planetsystems.monitoring.core.serviceImpl;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.planetsystems.monitoring.core.daos.UserDAO;
import com.planetsystems.monitoring.core.security.ProcnetUserDetails;
import com.planetsystems.monitoring.core.security.util.ProcnetSecurityUtil;
import com.planetsystems.monitoring.core.services.AuthenticationService;
import com.planetsystems.monitoring.core.services.ProcnetUserDetailsService;
import com.planetsystems.monitoring.model.User;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private ProcnetUserDetailsService procnetUserDetailsService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public User authenticate(String username, String password,
			boolean attachUserToSecurityContext) {
		User user = null;
		if (StringUtils.isNotBlank(username)
				&& StringUtils.isNotBlank(password)) {
			user = userDao.searchUniqueByPropertyEqual("username", username);
			if (user != null && isValidUserPassword(user, password)) {
				if (attachUserToSecurityContext) {
					ProcnetUserDetails userDetails = procnetUserDetailsService
							.getUserDetailsForUser(user);
					if (userDetails != null) {
						ProcnetSecurityUtil.setSecurityContext(userDetails);
					}
				} else {
					return user;
				}
			} else {
				log.error("Access denied for " + user.getUsername());
				throw new AccessDeniedException(
						"password and username mismatch.");
			}
		}

		return user;
	}

	public Boolean isValidUserPassword(User user, String password) {

		if (user == null || StringUtils.isBlank(password)
				|| StringUtils.isBlank(user.getSalt())) {

			return false;
		}
		// Test
		if (password.equals(user.getPassword())) {
			return true;
		}
		String hashedPassword = ProcnetSecurityUtil.encodeString(password
				+ user.getSalt());
		if (hashedPassword.equals(user.getPassword())) {
			return true;
		} else {

			hashedPassword = ProcnetSecurityUtil.encodeString2(password
					+ user.getSalt());
			if (hashedPassword.equals(user.getPassword())) {
				return true;
			}
		}

		return false;
	}

	public UserDAO getUserDAO() {
		return userDao;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDao = userDAO;
	}

	public boolean isEnabledUser(User systemUser) {
		if (systemUser.getStatus().equals(Boolean.FALSE)) {
			return false;
		}
		return true;
	}
}
