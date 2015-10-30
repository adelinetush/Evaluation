package com.planetsystems.monitoring.core.security.authentication;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.planetsystems.monitoring.core.security.ProcnetUserDetails;
import com.planetsystems.monitoring.core.services.AuthenticationService;
import com.planetsystems.monitoring.core.services.ProcnetUserDetailsService;

public class ProcnetAuthenticationProvider implements AuthenticationProvider {

	private ProcnetUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationService authenticationService;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		UserDetails userDetails = getUserDetailsService().loadUserByUsername(
				(String) authentication.getPrincipal());
		if (userDetails != null) {
			if (authenticationService.isValidUserPassword(
					((ProcnetUserDetails) userDetails).getSystemUser(),
					(String) authentication.getCredentials())) {

				if (authenticationService
						.isEnabledUser(((ProcnetUserDetails) userDetails)
								.getSystemUser())) {

					if (((ProcnetUserDetails) userDetails).getSystemUser()
							.isChangePasswordAtNextLogin()) {
						((ProcnetUserDetails) userDetails).getSystemUser()
								.setChangePassword(true);
					} else {
						/*
						 * set password reset flag.
						 */
						if (((ProcnetUserDetails) userDetails).getSystemUser()
								.getDateOfLastPasswordChange() != null) {
							Calendar passwordDate = Calendar.getInstance();
							passwordDate
									.setTime(((ProcnetUserDetails) userDetails)
											.getSystemUser()
											.getDateOfLastPasswordChange());

							// add fourteen days to the password date.
							passwordDate.add(Calendar.DAY_OF_MONTH, 14);

							if (passwordDate.before(Calendar.getInstance())) {
								((ProcnetUserDetails) userDetails)
										.getSystemUser()
										.setChangePassword(true);
							} else {
								((ProcnetUserDetails) userDetails)
										.getSystemUser().setChangePassword(
												false);
							}

						}
					}

					return new UsernamePasswordAuthenticationToken(userDetails,
							authentication.getCredentials(),
							userDetails.getAuthorities());
				} else {
					throw new AuthenticationServiceException(
							"Your account was disabled. Check with the adminstrator.");
				}
			} else {
				throw new AuthenticationServiceException("password don't match");
			}
		} else {
			throw new AuthenticationCredentialsNotFoundException("");
		}
	}

	public boolean supports(Class<? extends Object> supportedClass) {
		if (supportedClass
				.getName()
				.equalsIgnoreCase(
						"org.springframework.security.authentication.UsernamePasswordAuthenticationToken")) {
			return true;
		}

		return false;
	}

	/**
	 * @return the userDetailsService
	 */
	public ProcnetUserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	/**
	 * @param userDetailsService
	 *            the userDetailsService to set
	 */
	@Autowired
	public void setUserDetailsService(
			ProcnetUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * @return the authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService
	 *            the authenticationService to set
	 */
	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
}
