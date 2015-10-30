package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planetsystems.monitoring.core.daos.PermissionDAO;
import com.planetsystems.monitoring.core.daos.UserDAO;
import com.planetsystems.monitoring.core.security.ProcnetUserDetails;
import com.planetsystems.monitoring.core.services.ProcnetUserDetailsService;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.User;

@Service("userDetailsService")
public class ProcnetUserDetailsServiceImpl implements ProcnetUserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PermissionDAO permissionDAO;

	public ProcnetUserDetails getUserDetailsForUser(User user) {
		ProcnetUserDetails userDetails = null;
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthorities(user);
			if (authorities == null) {
				authorities = new ArrayList<GrantedAuthority>();
			}
			userDetails = new ProcnetUserDetails(user, true, true, true, true,
					authorities);
		}

		return userDetails;
	}

	/**
	 * gets a list of granted authorities for a given user
	 * 
	 * @param user
	 * @return
	 */
	protected List<GrantedAuthority> getUserAuthorities(User user) {
		List<GrantedAuthority> authorities = null;
		if (user != null) {
			authorities = new ArrayList<GrantedAuthority>();
			List<Permission> permissions = null;

			if (user.hasAdministrativePrivileges()) {
				permissions = permissionDAO.findAll();
			} else {
				permissions = user.findPermissions();
			}

			if (permissions != null && permissions.size() > 0) {
				for (Permission perm : permissions) {
					GrantedAuthority ga = new GrantedAuthorityImpl(
							perm.getName());
					authorities.add(ga);
				}
			}
		}

		return authorities;
	}

	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try {
			User user = userDAO.searchUniqueByPropertyEqual("username",
					username);
			if (user != null) {
				return getUserDetailsForUser(user);
			}
		} catch (Exception e) {

			throw new UsernameNotFoundException(e.getMessage(), e);
		}

		return null;
	}

}
