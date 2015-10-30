package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.exception.OperationFailedException;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;

/**
 * @author
 */
public interface UserService {

	/**
	 * @param user
	 * @throws SessionExpiredException
	 */
	public void activateUserAccount(User user) throws SessionExpiredException;

	
	/**
	 * changes the password for the given user. Using the given clear text
	 * password
	 * 
	 * @param password
	 *            new password for the user.
	 * @param user
	 *            the user whose password is to be changed.
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void changePassword(String password, User user)
			throws ValidationFailedException, SessionExpiredException;
	

	/**
	 * 
	 * @param password
	 *            new password for the user
	 * @param user
	 *            the user whose password is to be changed
	 * @param sendChangePasswordNotification
	 *            whether to send a change password notification.
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	User changePassword(String password, User user,
			boolean sendChangePasswordNotification)
			throws ValidationFailedException, SessionExpiredException;

	User changePassword(String password, User user,
			boolean sendChangePasswordNotification,
			boolean enforcePasswordPolicy) throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @param search
	 * @return
	 */
	int countUsers(Search search);

	/**
	 * @param user
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public void deactivateUserAccount(User user)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * deletes a given role from the system
	 * 
	 * @param role
	 * @throws OperationFailedException
	 *             thrown when the delete operation fails.
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void deleteRole(Role role) throws OperationFailedException,
			ValidationFailedException, SessionExpiredException;

	/**
	 * deletes a given user from the system
	 * 
	 * @param user
	 * @throws OperationFailedException
	 *             thrown when the delete operation fails
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void deleteUser(User user) throws OperationFailedException,
			ValidationFailedException, SessionExpiredException;

	/**
	 * @param role
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	Role editRole(Role role) throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @param user
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	User editUser(User user) throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * finds roles whose name(s) are like the searchString provided. This means
	 * that if a search string like 'ct' is provided, all roles whose names
	 * begin with ct characters are returned
	 * 
	 * @param searchString
	 * @return
	 */
	List<Role> findRolesByName(String searchString);

	/**
	 * gets a user based on their username
	 * 
	 * @param username
	 *            string for login name
	 * @return User or null if no much found
	 */
	User findUserByUsername(String username);
	
	/**
	 * @param searchString
	 * @return
	 */
	public Role getRolesByName(String searchString);

	/**
	 * finds users whose username(s) are like the query provided. This means
	 * that if a query like 'ct' is provided, all users who begin with the ct
	 * characters are returned
	 * 
	 * @param query
	 * @return
	 */
	List<User> findUsersByUsername(String query);

	/**
	 * @param dept
	 * @return
	 */
	User getCommissioner(Division division);

	/**
	 * gets total number of users who meet the search criteria
	 * 
	 * @param query
	 * @return
	 */
	int getNumberOfUsersInSearch(String query);

	/**
	 * gets the permission with the given id
	 * 
	 * @param id
	 * @return
	 */
	Permission getPermissionById(String id);

	/**
	 * gets a list of all permissions
	 * 
	 * @return
	 */
	List<Permission> getPermissions();

	/**
	 * @param text
	 * @return
	 */
	Role getRoleById(String text);

	/**
	 * gets a list of all
	 * 
	 * @return
	 */
	List<Role> getRoles();

	/**
	 * gets the list of roles starting at the given offset and ending at the
	 * given limit.
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Role> getRoles(int offset, int limit);

	/**
	 * gets the roles for a given user starting at the given offset and ending
	 * at the given limit.
	 * 
	 * @param user
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Role> getRoles(User user, int offset, int limit);

	/**
	 * gets the roles for a given user 
	 * 
	 * @param user
	 * @return
	 */
	List<Role> getRoles(User user);
	
	/**
	 * gets a list of roles on a given page number
	 * 
	 * @param pageNo
	 * @return
	 */
	List<Role> getRolesByPage(Integer pageNo);

	/**
	 * gets the total number of roles in the system.
	 * 
	 * @return
	 */
	int getTotalNumberOfRoles();

	/**
	 * gets the total number of roles for the given user.
	 * 
	 * @param user
	 * @return
	 */
	int getTotalNumberOfRoles(User user);

	/**
	 * @return
	 */
	int getTotalNumberOfUsers();

	/**
	 * gets a user with a given Id
	 * 
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);

	/**
	 * @param roleName
	 * @return
	 */
	User getUserByRoleName(String roleName);

	/**
	 * gets the username with the given username
	 * 
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	
	/**
	 * gets the username with the given username
	 * 
	 * @param username
	 * @return
	 */
	List<User> getUserByEmailAddress(String emailAddress);

	/**
	 * gets a list of all users in the system
	 * 
	 * @return
	 */
	List<User> getUsers();

	/**
	 * gets the list of users starting at the given offset and ending at the
	 * given limit.
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<User> getUsers(int offset, int limit);

	/**
	 * gets a list of users on a given page number
	 * 
	 * @param pageNo
	 * @return
	 */
	List<User> getUsers(Integer pageNo);

	/**
	 * gets a list of users based on a role with the given name.
	 * 
	 * @param roleName
	 * @return
	 */
	List<User> getUsersByRoleName(String roleName);
	
	/**
	 * @param department
	 * @return
	 */
	List<User> getUsersByDepartment(Department department);

	/**
	 * saves a given {@link Permission}
	 * 
	 * @param permission
	 * @throws ValidationFailedException
	 *             thrown when the validation of the user fails.
	 */
	void savePermision(Permission permission) throws ValidationFailedException;

	/**
	 * saves a given role
	 * 
	 * @param role
	 * @return
	 * @throws ValidationFailedException
	 *             thrown when the validation of the user fails.
	 */
	Role saveRole(Role role) throws ValidationFailedException;

	/**
	 * saves a given user
	 * 
	 * @param user
	 * @return
	 * @throws ValidationFailedException
	 *             thrown when the validation of the user fails.
	 * @throws ProcnetCodeBeamerException 
	 */
	User saveUser(User user) throws ValidationFailedException;

	/**
	 * gets a list of users that contain a given query in their name
	 * 
	 * @param query
	 * @return searched user
	 */
	List<User> searchUserByLoginName(String query);

	/**
	 * gets a paged list of users that contain a given query in their name
	 * 
	 * @param query
	 * @param pageNo
	 * @return
	 */
	List<User> searchUserByLoginName(String query, Integer pageNo);

	/**
	 * @param search
	 * @return
	 */
	List<User> searchUsers(Search search);

	/**
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void validateDisplayAdmin() throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void validateDisplayBudget() throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void validateDisplayCommitee() throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void validateDisplayPlanning() throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void validateDisplayRequistion() throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	void validateDisplayTender() throws ValidationFailedException,
			SessionExpiredException;

	/**
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersByRoleId(String roleId);

	/**
	 * @param roleId
	 * @return
	 */
	public User getUserByRoleId(String roleId);
	
	/**
	 * @param division
	 * @return
	 */
	public User getAssistantCommissioner(Division division);
	
	/**
	 * @return
	 */
	public List<User> getProcurementOfficers();
	

	/**
	 * @return
	 * Gets Users That have Code Beamer Accounts
	 */
	List<User> getRegisteredDocumentUsers();
	
	/**
	 * @return
	 * Gets Users With Out Code Beamer Accounts
	 */
	List<User> getUnRegisteredDocumentUsers();
	
	User registerUser(User user) throws ValidationFailedException;
	
	
}
