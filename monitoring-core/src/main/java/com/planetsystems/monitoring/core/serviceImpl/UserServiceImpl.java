package com.planetsystems.monitoring.core.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.ProcnetConstants;
import com.planetsystems.monitoring.core.daos.PermissionDAO;
import com.planetsystems.monitoring.core.daos.RoleDAO;
import com.planetsystems.monitoring.core.daos.UserDAO;
import com.planetsystems.monitoring.core.security.util.ProcnetSecurityUtil;
import com.planetsystems.monitoring.core.services.AuditlogService;
import com.planetsystems.monitoring.core.services.UserService;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.RecordStatus;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.RoleStatus;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.audit.ActionStatus;
import com.planetsystems.monitoring.model.audit.Operations;
import com.planetsystems.monitoring.model.exception.OperationFailedException;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.security.PermissionConstants;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PermissionDAO permissionDao;

	@Autowired
	private RoleDAO roleDao;

	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private AuditlogService auditlogService;
	
	

	public static Search prepareUserSearchByLoginName(String query) {
		Search search = new Search();
		String param = new StringBuilder().append("%")
				.append(StringEscapeUtils.escapeSql(query)).append("%")
				.toString();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterLike("username", param);
		search.addSort("username", false, true);

		return search;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void activateUserAccount(User user) throws SessionExpiredException {
		User activatedUser = getUserById(user.getId());
		activatedUser.setStatus(Boolean.TRUE);
		try {
			userDao.save(activatedUser);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT,
					ActionStatus.SUCCESS, "User Activation");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void changePassword(String password, User user)
			throws ValidationFailedException, SessionExpiredException {
		changePassword(password, user, true, true);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User changePassword(String password, User user,
			boolean sendChangePasswordNotification)
			throws ValidationFailedException, SessionExpiredException {
		return changePassword(password, user, sendChangePasswordNotification,
				true);
	}

	public User changePassword(String password, User user,
			boolean sendChangePasswordNotification,
			boolean enforcePasswordPolicy) throws ValidationFailedException,
			SessionExpiredException {
		user.setClearTextPassword(password);

		if (enforcePasswordPolicy)
			enforcePasswordPolicy(user);

		if (StringUtils.isBlank(user.getSalt())
				&& StringUtils.isEmpty(user.getSalt())) {
			user.setSalt(ProcnetSecurityUtil.getRandomToken());
		}

		String hashedPassword = ProcnetSecurityUtil.encodeString(user
				.getClearTextPassword() + user.getSalt());

		user.setPassword(hashedPassword);
		user.setDateOfLastPasswordChange(Calendar.getInstance().getTime());
		user.setChangePasswordAtNextLogin(false);

		
		try {
			return userDao.save(user);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT,
					ActionStatus.SUCCESS, "User Password Change");
		}
	}

	@Transactional(readOnly = true)
	public int countUsers(Search search) {
		return userDao.count(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deactivateUserAccount(User user)
			throws ValidationFailedException, SessionExpiredException {
		User deactivatedUser = getUserById(user.getId());

		if (user.getUsername().contentEquals("administrator")) {
			try {
				throw new ValidationFailedException(
						"default administrator cannot be deactivated");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User Activation");
			}
		}
		deactivatedUser.setStatus(Boolean.FALSE);
		try {
			userDao.save(deactivatedUser);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT,
					ActionStatus.SUCCESS, "User Deactivation");
		}
	}

	@Secured(PermissionConstants.PERM_DELETE_ROLE)
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRole(Role role) throws OperationFailedException,
			ValidationFailedException, SessionExpiredException {
		if (StringUtils.equalsIgnoreCase(role.getName(),
				Role.DEFAULT_ADMIN_ROLE)) {
			try {
				throw new OperationFailedException(
						"cannot delete default administration role");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE,
						ActionStatus.FAIL, "Role");
			}
		}
		if (role.getRoleStatus() == RoleStatus.DEFAULT) {
			try {
				throw new ValidationFailedException(
						"cannot delete default role");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE,
						ActionStatus.FAIL, "Role");
			}
		}
		roleDao.remove(role);
	}

	@Secured(PermissionConstants.PERM_DELETE_USER)
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(User user) throws OperationFailedException,
			ValidationFailedException, SessionExpiredException {
		if (user.getUsername().contentEquals("administrator")) {
			try {
				throw new ValidationFailedException(
						"cannot delete default administrator");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE,
						ActionStatus.FAIL, "User");
			}
		}
		try {
			userDao.remove(user);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE,
					ActionStatus.SUCCESS, "User");
		}
	}

	@Secured(PermissionConstants.PERM_EDIT_ROLES)
	@Transactional(propagation = Propagation.REQUIRED)
	public Role editRole(Role role) throws ValidationFailedException,
			SessionExpiredException {
		Role rl = getRoleById(role.getId());
		role.setCreatedBy(rl.getCreatedBy());
		role.setDateCreated(rl.getDateCreated());

		if (StringUtils.isBlank(role.getName())
				|| StringUtils.isEmpty(role.getName())) {
			try {
				throw new ValidationFailedException(
						"the name of the role is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "Role");
			}
		}

		if (StringUtils.isBlank(role.getDescription())
				|| StringUtils.isEmpty(role.getDescription())) {
			try {
				throw new ValidationFailedException(
						"the description of the role is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "Role");
			}
		}

		try {
			roleDao.save(role);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT,
					ActionStatus.SUCCESS, "Role");
		}
		return rl;
	}

	@Secured(PermissionConstants.PERM_EDIT_USERS)
	@Transactional(propagation = Propagation.REQUIRED)
	public User editUser(User user) throws ValidationFailedException,
			SessionExpiredException {
		User usr = getUserById(user.getId());
		user.setPassword(usr.getPassword());
		user.setSalt(usr.getSalt());
		user.setStatus(usr.getStatus());
		user.setCreatedBy(usr.getCreatedBy());
		user.setDateCreated(usr.getDateCreated());
		enforcePasswordPolicy(usr);

		if (StringUtils.isBlank(user.getUsername())
				|| StringUtils.isEmpty(user.getUsername())) {
			try {
				throw new ValidationFailedException(
						"the username of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (user.getUserCatergory() == null) {
			try {
				throw new ValidationFailedException(
						"the user catergory is not selected");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (user.getRoles() == null) {
			try {
				throw new ValidationFailedException(
						"the role of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (StringUtils.isBlank(user.getSecretQuestion())
				|| StringUtils.isEmpty(user.getSecretQuestion())) {
			try {
				throw new ValidationFailedException(
						"the secret question of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (StringUtils.isBlank(user.getSecretAnswer())
				|| StringUtils.isEmpty(user.getSecretAnswer())) {
			try {
				throw new ValidationFailedException(
						"the secret answer of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (StringUtils.isBlank(user.getfName())
				|| StringUtils.isEmpty(user.getfName())) {
			try {
				throw new ValidationFailedException(
						"the first name of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (StringUtils.isBlank(user.getlName())
				|| StringUtils.isEmpty(user.getlName())) {
			try {
				throw new ValidationFailedException(
						"the the last of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}
		if (StringUtils.isBlank(user.getEmail())
				|| StringUtils.isEmpty(user.getEmail())) {
			try {
				throw new ValidationFailedException(
						"the email of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}

		if (StringUtils.isBlank(user.getPhoneNumber())
				|| StringUtils.isEmpty(user.getPhoneNumber())) {
			try {
				throw new ValidationFailedException(
						"the phone number of the user is not supplied");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.EDIT,
						ActionStatus.FAIL, "User");
			}
		}

		try {
			userDao.save(user);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT,
					ActionStatus.SUCCESS, "User");
		}
		return user;
	}

	private void enforcePasswordPolicy(User user)
			throws ValidationFailedException {
		/*
		 * enforce password policy (at least 2 numeric characters and a minimum
		 * of 8 characters
		 */
		String regex = "^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,}$";
		if (StringUtils.isNotBlank(user.getClearTextPassword())) {
			if (!user.getClearTextPassword().matches(regex)) {
				throw new ValidationFailedException(
						"the password should have atleast 2 numeric characters and "
								+ "a minimum of eight characters long.");
			}
		}
	}

	@Secured(PermissionConstants.PERM_VIEW_ROLE)
	@Transactional(readOnly = true)
	public List<Role> findRolesByName(String searchString) {
		Search search = new Search();
		search.addFilter(new Filter("name", searchString, Filter.OP_LIKE));
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		List<Role> roles = roleDao.search(search);
		return roles;
	}

	@Transactional(readOnly = true)
	public Role getRolesByName(String searchString) {
		Search search = new Search();
		search.addFilter(new Filter("name", searchString, Filter.OP_LIKE));
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return roleDao.searchUnique(search);
	}

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		return userDao.searchUniqueByPropertyEqual("username", username);
	}

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public List<User> findUsersByUsername(String username) {
		Search search = new Search();
		search.addFilter(new Filter("username", username, Filter.OP_LIKE));
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		List<User> users = userDao.search(search);

		return users;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User getCommissioner(Division division) {
		Search search = new Search();
		search.addFilterSome("roles",Filter.equal("name", "ASSISTANT COMMISSIONER"));
		search.addFilterEqual("division", division);
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return (User) userDao.search(search).get(0);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User getSecretaryContractsCommittee() {
		Search search = new Search();
		search.addFilterSome("roles",Filter.equal("name", "SECRETARY CONTRACTS COMMITTEE"));
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("status", Boolean.TRUE);
		return (User) userDao.search(search).get(0);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> getProcurementOfficers() {
		Search search = new Search();
		search.addFilterSome("roles",Filter.equal("name", "PROCUREMENT OFFICER"));
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("status", Boolean.TRUE);
		return userDao.search(search);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User getAssistantCommissioner(Division division) {
		Search search = new Search();
		search.addFilterSome("roles",Filter.equal("name", "ASSISTANT COMMISSIONER"));
		search.addFilterEqual("division", division);
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("status", Boolean.TRUE);
		return (User) userDao.search(search).get(0);
	}

	public int getNumberOfUsersInSearch(String query) {
		Search search = prepareUserSearchByLoginName(query);
		return userDao.count(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Permission getPermissionById(String id) {
		return permissionDao.searchUniqueByPropertyEqual("id", id);
	}

	public List<Permission> getPermissions() {
		List<Permission> permissions = permissionDao
				.searchByRecordStatus(RecordStatus.ACTIVE);
		return permissions;
	}

	@Secured(PermissionConstants.PERM_VIEW_ROLE)
	@Transactional(readOnly = true)
	public Role getRoleById(String id) {
		Role role = roleDao.searchUniqueByPropertyEqual("id", id);
		return role;
	}

	@Secured(PermissionConstants.PERM_VIEW_ROLE)
	@Transactional(readOnly = true)
	public List<Role> getRoles() {
		List<Role> roles = roleDao.searchByRecordStatus(RecordStatus.ACTIVE);
		return roles;
	}

	@Transactional(readOnly = true)
	public List<Role> getRoles(int offset, int limit) {
		Search search = new Search();
		search.addSortAsc("name");
		search.setFirstResult(offset);
		search.setMaxResults(limit);
		return roleDao.search(search);
	}

	@Transactional(readOnly = true)
	public List<Role> getRoles(User user, int offset, int limit) {

		Search search = new Search();
		search.addFilterSome("users", new Filter("name", user));
		search.addSortAsc("name");
		search.setFirstResult(offset);
		search.setMaxResults(limit);

		return roleDao.search(search);
	}
	
	@Transactional(readOnly = true)
	public List<Role> getRoles(User user) {

		List<Role> roles=new ArrayList<Role>();
		if(user!=null){
		for(Role role:user.getRoles()){
		roles.add(role);
		}
		}
		return roles;
	}

	@Secured(PermissionConstants.PERM_VIEW_ROLE)
	@Transactional(readOnly = true)
	public List<Role> getRolesByPage(Integer pageNo) {
		Search search = new Search();
		search.setMaxResults(ProcnetConstants.MAX_NUM_PAGE_RECORD);
		search.addSort("id", false, true);
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		if (pageNo > 0) {
			search.setPage(pageNo - 1);
		} else {
			search.setPage(0);
		}

		List<Role> roles = roleDao.search(search);
		return roles;
	}

	@Transactional(readOnly = true)
	public int getTotalNumberOfRoles() {
		return roleDao.count(new Search());
	}

	@Transactional(readOnly = true)
	public int getTotalNumberOfRoles(User user) {
		Search search = new Search();
		search.addFilterSome("users", new Filter("", user));
		return roleDao.count(search);
	}

	public int getTotalNumberOfUsers() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return userDao.count(search);
	}

	@Transactional(readOnly = true)
	public User getUserById(String userId) {
		return userDao.searchUniqueByPropertyEqual("id", userId);
	}

	@Transactional(readOnly = true)
	public User getUserByRoleName(String roleName) {
		Search search = new Search();
		search.addFilterSome("roles", Filter.equal("name", roleName));
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("status", Boolean.TRUE);
		return userDao.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public User getUserByUsername(String username) {
		return userDao.searchUniqueByPropertyEqual("username", username);
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDao;
	}

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public List<User> getUsers() {
		List<User> users = userDao.searchByRecordStatus(RecordStatus.ACTIVE);
		return users;
	}

	@Transactional(readOnly = true)
	public List<User> getUsers(int offset, int limit) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addSortAsc("username");
		search.setFirstResult(offset);
		search.setMaxResults(limit);
		return userDao.search(search);
	}
	
	

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public List<User> getUsers(Integer pageNo) {
		Search search = new Search();
		search.setMaxResults(ProcnetConstants.MAX_NUM_PAGE_RECORD);
		search.addSort("username", false, true);
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);

		if (pageNo != null && pageNo > 0) {
			search.setPage(pageNo - 1);
		} else {
			search.setPage(0);
		}

		List<User> users = userDao.search(search);
		return users;
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByRoleName(String roleName) {
		Search search = new Search();
		search.addFilterSome("roles", Filter.equal("name", roleName));
		search.addFilterEqual("status", Boolean.TRUE);
		return userDao.search(search);
	}

	public void savePermision(Permission permission)
			throws ValidationFailedException {
		if (StringUtils.isBlank(permission.getName())
				|| StringUtils.isEmpty(permission.getName())) {
			throw new ValidationFailedException(
					"the name of the permission is not supplied");
		}

		if (StringUtils.isBlank(permission.getDescription())
				|| StringUtils.isEmpty(permission.getDescription())) {
			throw new ValidationFailedException(
					"the description of the permission is not supplied");
		}

		if (StringUtils.isBlank(permission.getId())
				|| StringUtils.isEmpty(permission.getId())) {
			Permission existingPerm = permissionDao
					.searchUniqueByPropertyEqual("name", permission.getName());
			if (existingPerm != null) {
				throw new ValidationFailedException(
						"a permission with the given name already exists");
			}
		}

		permissionDao.save(permission);
	}

	@Secured(PermissionConstants.PERM_SAVE_ROLE)
	@Transactional(propagation = Propagation.REQUIRED)
	public Role saveRole(Role role) throws ValidationFailedException {
		if (StringUtils.isBlank(role.getName())
				|| StringUtils.isEmpty(role.getName())) {
			throw new ValidationFailedException(
					"the name of the role is not supplied");
		}

		if (StringUtils.isBlank(role.getDescription())
				|| StringUtils.isEmpty(role.getDescription())) {
			throw new ValidationFailedException(
					"the description of the role is not supplied");
		}

		if (StringUtils.isBlank(role.getId())
				|| StringUtils.isEmpty(role.getId())) {
			Role existingRole = roleDao.searchUniqueByPropertyEqual("name",
					role.getName());
			if (existingRole != null) {
				throw new ValidationFailedException(
						"a role is the given name already exists");
			}
		}
		Set<Permission> defaultPermission = new HashSet<Permission>();
		defaultPermission
				.add(getPermissionById("BE77AA9B-60CC-47F7-B163-12B0064BE0FA"));
		role.setPermissions(defaultPermission);
		roleDao.save(role);

		return role;
	}

	@Secured(PermissionConstants.PERM_SAVE_USER)
	@Transactional(propagation = Propagation.REQUIRED)
	public User saveUser(User user) throws ValidationFailedException{

		enforcePasswordPolicy(user);

		if (user.getDivision() == null)
			throw new ValidationFailedException("Division Missing");

		if (StringUtils.isBlank(user.getUsername())
				|| StringUtils.isEmpty(user.getUsername())) {
			throw new ValidationFailedException(
					"the username of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getClearTextPassword())
				|| StringUtils.isEmpty(user.getClearTextPassword())) {
			throw new ValidationFailedException(
					"the password of the user is not supplied");
		}

		if (user.getRoles() == null) {
			throw new ValidationFailedException(
					"the role of the user is not supplied");
		}
		if (user.getUserCatergory() == null) {
			throw new ValidationFailedException(
					"the user catergory is not selected");
		}
		if (StringUtils.isBlank(user.getSecretQuestion())
				|| StringUtils.isEmpty(user.getSecretQuestion())) {
			throw new ValidationFailedException(
					"the secret question of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getSecretAnswer())
				|| StringUtils.isEmpty(user.getSecretAnswer())) {
			throw new ValidationFailedException(
					"the secret answer of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getfName())
				|| StringUtils.isEmpty(user.getfName())) {
			throw new ValidationFailedException(
					"the first name of the user is not supplied");
		}
		if (StringUtils.isBlank(user.getlName())
				|| StringUtils.isEmpty(user.getlName())) {
			throw new ValidationFailedException(
					"the the last of the user is not supplied");
		}
		if (StringUtils.isBlank(user.getEmail())
				|| StringUtils.isEmpty(user.getEmail())) {
			throw new ValidationFailedException(
					"the email of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getPhoneNumber())
				|| StringUtils.isEmpty(user.getPhoneNumber())) {
			throw new ValidationFailedException(
					"the phone number of the user is not supplied");
		}
		// check whether the id is null or empty this is to ensure that the user
		// is new
		if (StringUtils.isBlank(user.getId())
				|| StringUtils.isEmpty(user.getId())) {
			// check whet her username exists
			log.debug(this.getClass().getName()
					+ " - checking for existing user");
			User existingUser = findUserByUsername(user.getUsername());
			if (existingUser != null) {
				throw new ValidationFailedException(
						"a user with this username - " + user.getUsername()
								+ " already exists");
			}

			// by default, the user is set to active
			user.setStatus(Boolean.TRUE);
		} else {
			User existingUser = userDao.find(user.getId());

			if (existingUser != null) {
				user.setPassword(existingUser.getPassword());
				user.setSalt(existingUser.getSalt());
				user.setStatus(existingUser.getStatus());
			}

			User existingUserWithSimilarUsername = findUserByUsername(user
					.getUsername());
			if (!existingUserWithSimilarUsername.getId().equalsIgnoreCase(
					user.getId())) {
				throw new ValidationFailedException(
						"a user with this username - " + user.getUsername()
								+ " already exists");
			}
		}

		ProcnetSecurityUtil.prepUserCredentials(user);
	
			try {
				
				userDao.save(user);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				throw new ValidationFailedException(" User Account Not Created ");
				
			} 
			
		
		return user;
	}

	/**
	 * Search for users
	 */

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public List<User> searchUserByLoginName(String query) {
		Search search = prepareUserSearchByLoginName(query);
		List<User> users = userDao.search(search);
		return users;
	}

	public List<User> searchUserByLoginName(String query, Integer pageNo) {
		Search search = prepareUserSearchByLoginName(query);
		search.setMaxResults(ProcnetConstants.MAX_NUM_PAGE_RECORD);

		if (pageNo != null && pageNo > 0) {
			search.setPage(pageNo - 1);
		} else {
			search.setPage(0);
		}

		List<User> users = userDao.search(search);
		return users;
	}

	@Transactional(readOnly = true)
	public List<User> searchUsers(Search search) {
		return userDao.search(search);
	}

	/**
	 * @param userDAO
	 *            the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDao = userDAO;
	}

	public void validateDisplayAdmin() throws ValidationFailedException,
			SessionExpiredException {
		User user = ProcnetSecurityUtil.getLoggedInUser();
		if (!user.hasPermission(PermissionConstants.PERM_VIEW_ADMINISTRATION)) {
			throw new ValidationFailedException(
					"Access to view Administration not allowed for current user");
		}
	}

	public void validateDisplayBudget() throws ValidationFailedException,
			SessionExpiredException {
		User user = ProcnetSecurityUtil.getLoggedInUser();
		if (!user.hasPermission(PermissionConstants.PERM_DISPLAY_BUDGET)) {
			throw new ValidationFailedException(
					"Access to view Budget not allowed for current user");
		}
	}

	public void validateDisplayCommitee() throws ValidationFailedException,
			SessionExpiredException {
		User user = ProcnetSecurityUtil.getLoggedInUser();
		if (!user.hasPermission(PermissionConstants.PERM_DISPLAY_COMMITEE))
			throw new ValidationFailedException(
					"Access to view Committee not allowed for current user");
	}

	public void validateDisplayPlanning() throws ValidationFailedException,
			SessionExpiredException {
		User user = ProcnetSecurityUtil.getLoggedInUser();
		if (!user.hasPermission(PermissionConstants.PERM_DISPLAY_PLANNING)) {
			throw new ValidationFailedException(
					"Access to view Planning not allowed for current user");
		}

	}

	public void validateDisplayRequistion() throws ValidationFailedException,
			SessionExpiredException {
		User user = ProcnetSecurityUtil.getLoggedInUser();
		if (!user.hasPermission(PermissionConstants.PERM_DISPLAY_REQUISTION)) {
			throw new ValidationFailedException(
					"Access to view Requistion not allowed for current user");
		}
	}

	public void validateDisplayTender() throws ValidationFailedException,
			SessionExpiredException {
		User user = ProcnetSecurityUtil.getLoggedInUser();
		if (!user.hasPermission(PermissionConstants.PERM_DISPLAY_TENDER)) {
			throw new ValidationFailedException(
					"Access to view Tender not allowed for current user");
		}
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByRoleId(String roleId) {
		Search search = new Search();
		search.addFilterSome("roles", Filter.equal("id", roleId));
		search.addFilterEqual("status", Boolean.TRUE);
		return userDao.search(search);
	}

	@Transactional(readOnly = true)
	public User getUserByRoleId(String roleId) {
		Search search = new Search();
		search.addFilterSome("roles", Filter.equal("id", roleId));
		search.addFilterEqual("status", Boolean.TRUE);
		return userDao.searchUnique(search);
	}

	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.core.service.UserService#createUserCodeBeamerAccount(com.planetsystems.procnet.model.User)
	 */
	
	@Secured(PermissionConstants.PERM_EDIT_USERS)
	@Transactional(propagation = Propagation.REQUIRED)
	public User createUserCodeBeamerAccount(User user)
	        
			throws ValidationFailedException,
			OperationFailedException, IOException
			 {
		
		User usr = getUserById(user.getId());
		user.setPassword(usr.getPassword());
		user.setSalt(usr.getSalt());
		user.setStatus(usr.getStatus());
		user.setCreatedBy(usr.getCreatedBy());
		user.setDateCreated(usr.getDateCreated());
		enforcePasswordPolicy(usr);

		if (StringUtils.isBlank(user.getUsername())
				|| StringUtils.isEmpty(user.getUsername())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the username of the user is not supplied");
		}

		if (user.getUserCatergory() == null) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the user catergory is not selected");
		}

		if (user.getRoles() == null) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the role of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getSecretQuestion())
				|| StringUtils.isEmpty(user.getSecretQuestion())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the secret question of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getSecretAnswer())
				|| StringUtils.isEmpty(user.getSecretAnswer())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the secret answer of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getfName())
				|| StringUtils.isEmpty(user.getfName())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the first name of the user is not supplied");
		}
		if (StringUtils.isBlank(user.getlName())
				|| StringUtils.isEmpty(user.getlName())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the the last of the user is not supplied");
		}
		if (StringUtils.isBlank(user.getEmail())
				|| StringUtils.isEmpty(user.getEmail())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the email of the user is not supplied");
		}

		if (StringUtils.isBlank(user.getPhoneNumber())
				|| StringUtils.isEmpty(user.getPhoneNumber())) {
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			throw new ValidationFailedException(
					"the phone number of the user is not supplied");
		}
		
		
		
		try{
			
		userDao.save( user );
		
		}catch(Exception ex){
			
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.FAIL, "User");
			
		}
		finally{
			
			auditlogService.saveAuditEventTrail(Operations.SAVE,
					ActionStatus.SUCCESS, "User");
		}
		
		
		return user;
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByDepartment(Department department) {
	
		Search search = new Search();
	
		search.addFilterEqual("department", department);
		
		try{
			
		return userDao.search(search);
		
		}catch(Exception ex){
			
			auditlogService.saveAuditEventTrail(Operations.VIEW,
					ActionStatus.FAIL, "User");
			
			return null;
			
		}finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW,
					ActionStatus.SUCCESS, "User");
		}
		
	}

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public List<User> getRegisteredDocumentUsers() {

		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("registered", true);
		
		return userDao.search(search);
	}

	@Secured(PermissionConstants.PERM_VIEW_USER)
	@Transactional(readOnly = true)
	public List<User> getUnRegisteredDocumentUsers() {
		
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("registered", false);
		
		return userDao.search(search);
	}

	@Secured(PermissionConstants.PERM_SAVE_USER)
	@Transactional(propagation = Propagation.REQUIRED)
	public User registerUser(User user) throws ValidationFailedException {
	
		
		User userEdited = user;
		
		try {
			user.setRegistered(true);
			
			// Edit Procnet User
			userEdited = editUser(user);
			
		}catch (SessionExpiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}

		return userEdited;
		
	}

	public List<User> getUserByEmailAddress(String emailAddress) {
		
		Search search = new Search();
		search.addFilterSome("emailAddress", Filter.equal("emailAddress", emailAddress));
		search.addFilterEqual("status", Boolean.TRUE);
		
		return userDao.search(search);
		
	}
	
	

}
