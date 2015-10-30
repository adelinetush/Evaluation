package com.planetsystems.monitoring.core.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.planetsystems.monitoring.core.security.ProcnetUserDetails;
import com.planetsystems.monitoring.model.User;

public interface ProcnetUserDetailsService extends UserDetailsService {

	ProcnetUserDetails getUserDetailsForUser(User user);
}
