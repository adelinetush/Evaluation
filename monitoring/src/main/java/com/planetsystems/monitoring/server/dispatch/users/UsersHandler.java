/**
 * 
 */
package com.planetsystems.monitoring.server.dispatch.users;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.utils.Constants;
import com.planetsystems.monitoring.core.security.util.ProcnetSecurityUtil;
import com.planetsystems.monitoring.core.services.OrganisationalUnitService;
import com.planetsystems.monitoring.core.services.UserService;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.ProcnetErrorCodes;
import com.planetsystems.monitoring.model.Role;

import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.UserCatergory;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.shared.dispatch.users.UsersAction;
import com.planetsystems.monitoring.shared.dispatch.users.UsersResult;

/**
 * @author Planet Developer 001
 * 
 */
public class UsersHandler extends
		AbstractActionHandler<UsersAction, UsersResult> {

	@Autowired
	private UserService userService;

	@Autowired
	private OrganisationalUnitService organizationUnitService;

	/*
	 * @Autowired private DirectorateAndDepartmentService
	 * organizationUnitService;
	 */

	public UsersHandler() {
		super(UsersAction.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param actionType
	 */

	@Override
	public Class<UsersAction> getActionType() {
		return UsersAction.class;
	}

	public UsersResult execute(UsersAction action, ExecutionContext context)
			throws ActionException {

		UsersResult result = null;

		System.out.println("Users Action execute");
		if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation()
						.contentEquals(NameTokens.saveOperation)) {

			result = saveUser(action.getUser());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation()
						.contentEquals(NameTokens.editOperation)) {

			result = editUser(action.getUser());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.addRolesOperation)) {

			result = addUserRoles(action.getUser(), action.getRoles());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.removeRolesOperation)) {

			result = removeUserRoles(action.getUser(), action.getRoles());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveOperation)) {

			result = retrieveAllUsers();

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveByLimitsOpeartion)) {

			result = retrieveUsersWithLimits(action.getOffSet(),
					action.getLimit());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveUsersByDepartmentOperation)) {

			result = retrieveUsersByDepartment(action.getDepartment());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.activateOperation)) {

			result = activateUser(action.getUser());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.deActivateOperation)) {

			result = deActivateUser(action.getUser());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.getLoggedInUser)) {

			result = getLoggedInUser();

		} else if (action.getOperationLevel().contentEquals(NameTokens.roles)
				&& action.getOperation()
						.contentEquals(NameTokens.saveOperation)) {

			result = saveRole(action.getRole());

		} else if (action.getOperationLevel().contentEquals(NameTokens.roles)
				&& action.getOperation()
						.contentEquals(NameTokens.editOperation)) {

			result = editRole(action.getRole());

		} else if (action.getOperationLevel().contentEquals(NameTokens.roles)
				&& action.getOperation().contentEquals(
						NameTokens.deleteOperation)) {

			result = deleteRole(action.getRole());

		} else if (action.getOperationLevel().contentEquals(NameTokens.roles)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveOperation)) {

			result = retrieveAllRoles();

		} else if (action.getOperationLevel().contentEquals(NameTokens.roles)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveRolesByUserOperation)) {

			result = retrieveUserRoles(action.getUser());

		} else if (action.getOperationLevel().contentEquals(NameTokens.roles)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveNewRolesOperation)) {

			result = retrieveRolesNotOnUser(action.getUser());

		}

		else if (action.getOperationLevel().contentEquals(
				NameTokens.permissions)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveOperation)) {

			result = retrieveAllPermissions();

		} else if (action.getOperationLevel().contentEquals(
				NameTokens.permissions)
				&& action.getOperation().contentEquals(
						NameTokens.retrievePermissionsByRoleOperation)) {

			result = retrievePermissionsByRole(action.getRole());

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveDocumentRegisteredUsersOperation)) {

			result = retrieveDocRegisteredUsers();

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.retrieveDocumentUnRegisteredUsersOperation)) {

			result = retrieveDocNotRegisteredUsers();

		} else if (action.getOperationLevel().contentEquals(NameTokens.users)
				&& action.getOperation().contentEquals(
						NameTokens.registerUserOperation)) {

			result = registerDocumentUser(action.getUser());

		}  else {

			result = new UsersResult(false, "No Match");
		}

		return result;
	}

	public void undo(UsersAction action, UsersResult result,
			ExecutionContext context) throws ActionException {

	}

	@SuppressWarnings("unchecked")
	private UsersResult saveUser(User actionUser) {

		UsersResult result = null;

		try {

			// Set User Roles
			List<Role> userRoles = new ArrayList<Role>();
			HashSet<Role> roles = new HashSet<Role>();

			for (Role roleTo : actionUser.getRolesList()) {

				Role role = new Role();
				role = userService.getRoleById(roleTo.getId());

				// userRoles.add(role);
				roles.add(role);

			}
			/*
			 * Role role = new Role(); role =
			 * userService.getRoleById(actionUser.getRoleId());
			 */

			User user = new User();

			user.setfName(actionUser.getfName());
			user.setlName(actionUser.getlName());
			user.setUsername(actionUser.getUsername());
			user.setClearTextPassword(actionUser.getClearTextPassword());
			user.setEmail(actionUser.getEmail());
			user.setSecretQuestion(actionUser.getSecretQuestion());
			user.setSecretAnswer(actionUser.getSecretAnswer());
			user.setPhoneNumber(actionUser.getPhoneNumber());
			user.setRoles(roles);

			user.setStatus(actionUser.getStatus());
			user.setUserCatergory(actionUser.getUserCatergory());

			if (actionUser.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

				Department department = new Department();
				Division division = new Division();
				division = organizationUnitService.getDivisionById(actionUser
						.getDivision().getId());
				department = organizationUnitService.getDepartmentById(division
						.getDepartment().getId());
				user.setDivision(division);
				user.setDepartment(department);

			} else {

			}

			userService.saveUser(user);

			result = new UsersResult(true, "Successfully Saved User: "
					+ actionUser.getFullName());

		} catch (ValidationFailedException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": "
							+ ex.getMessage());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}
		return result;

	}

	private UsersResult editUser(User actionUser) {

		UsersResult result = null;

		try {

			HashSet<Role> roles = new HashSet<Role>();

			/*
			 * for (Role roleTo : actionUser.getRolesList()) {
			 * 
			 * Role role = new Role(); role =
			 * userService.getRoleById(roleTo.getId());
			 * 
			 * roles.add(role);
			 * 
			 * }
			 */
			User user = new User();
			user = userService.getUserById(actionUser.getId());

			user.setfName(actionUser.getfName());
			user.setlName(actionUser.getlName());
			user.setUsername(actionUser.getUsername());

			user.setEmail(actionUser.getEmail());
			user.setPhoneNumber(actionUser.getPhoneNumber());
			// user.setRoles(roles);
			user.setStatus(actionUser.getStatus());
			user.setUserCatergory(actionUser.getUserCatergory());

			if (actionUser.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

				Department department = new Department();
				Division division = new Division();
				division = organizationUnitService.getDivisionById(actionUser
						.getDivision().getId());
				department = organizationUnitService.getDepartmentById(division
						.getDepartment().getId());
				user.setDivision(division);
				user.setDepartment(department);
			} else {

			}

			userService.editUser(user);

			result = new UsersResult(true, "Successfully Edited User: "
					+ actionUser.getFullName());

		} catch (ValidationFailedException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": "
							+ ex.getMessage());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult addUserRoles(User actionUser, List<Role> rolesTo) {

		UsersResult result = null;

		try {

			User user = new User();
			user = userService.getUserById(actionUser.getId());

			for (Role roleTo : rolesTo) {

				Role role = new Role();
				role = userService.getRoleById(roleTo.getId());

				user.addRole(role);

				// roles.add(role);

			}

			userService.editUser(user);

			result = new UsersResult(true,
					"Successfully Added Role/Roles To Users: "
							+ actionUser.getFullName());

		} catch (ValidationFailedException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": "
							+ ex.getMessage());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult removeUserRoles(User actionUser, List<Role> rolesTo) {

		UsersResult result = null;

		try {

			User user = new User();
			user = userService.getUserById(actionUser.getId());

			List<Role> userRoles = new ArrayList<Role>();
			userRoles = user.getRolesList();

			// ListIterator to help remove items from list while iterating over
			// it
			ListIterator<Role> userRolesIter = userRoles.listIterator();
			HashSet<Role> newUserRoles = new HashSet<Role>();

			for (Role role : rolesTo) {

				// Iterator Over Current User Roles and remove the unwanted
				while (userRolesIter.hasNext()) {

					Role roleUser = userRolesIter.next();

					if (role.getId().contentEquals(roleUser.getId())) {

						// userRolesIter.remove();

					} else {

						newUserRoles.add(roleUser);

					}
				}

			}

			user.setRoles(newUserRoles);

			userService.editUser(user);

			result = new UsersResult(true,
					"Successfully Removed Role/Roles from User : "
							+ actionUser.getFullName());

		} catch (ValidationFailedException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": "
							+ ex.getMessage());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult activateUser(User actionUser) {

		UsersResult result = null;

		try {

			Role role = new Role();
			// role = userService.get

			User user = new User();
			user = userService.getUserById(actionUser.getId());

			userService.activateUserAccount(user);

			result = new UsersResult(true, "Activated User: "
					+ actionUser.getFullName());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult deActivateUser(User actionUser) {

		UsersResult result = null;

		try {

			Role role = new Role();
			// role = userService.get

			User user = new User();
			user = userService.getUserById(actionUser.getId());

			userService.deactivateUserAccount(user);

			result = new UsersResult(true, "De-Activated User: "
					+ actionUser.getFullName());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult getLoggedInUser() {

		UsersResult result = null;

		try {

			User loggedInUser = new User();
			loggedInUser = ProcnetSecurityUtil.getLoggedInUser();

			User newloggedInUser = new User();
			List<Permission> permissions = new ArrayList<Permission>();
			List<Permission> permissionsDTOs = new ArrayList<Permission>();

			newloggedInUser.setfName(loggedInUser.getfName());
			newloggedInUser.setlName(loggedInUser.getlName());
			newloggedInUser.setUsername(loggedInUser.getUsername());
			newloggedInUser.setEmail(loggedInUser.getEmail());
			newloggedInUser.setPhoneNumber(loggedInUser.getPhoneNumber());
			if (loggedInUser.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

				Department department = new Department();
				department.setId(loggedInUser.getDepartment().getId());
				department.setDepartmentName(loggedInUser.getDepartment()
						.getDepartmentName());
				department.setDepartmentCode(loggedInUser.getDepartment()
						.getDepartmentCode());

				newloggedInUser.setDepartment(department);

				if (loggedInUser.getDivision() != null) {

					Division division = new Division();
					division.setId(loggedInUser.getDivision().getId());
					division.setDivisionCode(loggedInUser.getDivision()
							.getDivisionCode());
					division.setDivisionName(loggedInUser.getDivision()
							.getDivisionName());

					newloggedInUser.setDivision(division);

				} else {

				}

				newloggedInUser.setUserCatergory(UserCatergory.STAFF);

			} else if (loggedInUser.getUserCatergory().compareTo(
					UserCatergory.SUPERADMIN) == 0) {

				Department department = new Department();
				department.setId(UserCatergory.SUPERADMIN.toString());
				department.setDepartmentName(UserCatergory.SUPERADMIN
						.toString());
				department.setDepartmentCode(UserCatergory.SUPERADMIN
						.toString());

				newloggedInUser.setDepartment(department);

				newloggedInUser.setUserCatergory(UserCatergory.SUPERADMIN);

			} else if (loggedInUser.getUserCatergory().compareTo(
					UserCatergory.BOARDMEMBER) == 0) {

				Department department = new Department();
				department.setId(UserCatergory.BOARDMEMBER.toString());
				department.setDepartmentName(UserCatergory.BOARDMEMBER
						.toString());
				department.setDepartmentCode(UserCatergory.BOARDMEMBER
						.toString());

				newloggedInUser.setDepartment(department);

				newloggedInUser.setUserCatergory(UserCatergory.BOARDMEMBER);

			} else if (loggedInUser.getUserCatergory().compareTo(
					UserCatergory.GUEST) == 0) {

				Department department = new Department();
				department.setId(UserCatergory.GUEST.toString());
				department.setDepartmentName(UserCatergory.GUEST.toString());
				department.setDepartmentCode(UserCatergory.GUEST.toString());

				newloggedInUser.setDepartment(department);

				newloggedInUser.setUserCatergory(UserCatergory.GUEST);
			}

			// Set Permissions

			permissions = loggedInUser.findPermissions();

			for (Permission permission : permissions) {

				Permission permissionDTO = new Permission();
				permissionDTO.setId(permission.getId());
				permissionDTO.setName(permission.getName());
				permissionDTO.setDescription(permission.getDescription());

				permissionsDTOs.add(permissionDTO);

			}

			newloggedInUser.setPermissions(permissionsDTOs);

			result = new UsersResult(true, newloggedInUser);

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;
	}

	private UsersResult retrieveAllUsers() {

		UsersResult result = null;

		try {

			List<User> users = new ArrayList<User>(userService.getUsers()
					.size());
			List<User> usersDTO = new ArrayList<User>();

			users = userService.getUsers();
			for (User user : users) {

				User userDTO = new User();
				userDTO.setId(user.getId());
				userDTO.setfName(user.getfName());
				userDTO.setlName(user.getlName());
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setStatus(user.getStatus());

				// userDTO.setRoleName(user.get);
				userDTO.setRoleId(user.getRoleId());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					Department department = new Department();
					department.setId(user.getDepartment().getId());
					department.setDepartmentName(user.getDepartment()
							.getDepartmentName());
					department.setDepartmentCode(user.getDepartment()
							.getDepartmentCode());

					Division division = new Division();
					division.setDivisionCode(user.getDivision()
							.getDivisionCode());
					division.setDivisionName(user.getDivision()
							.getDivisionName());

					userDTO.setDepartment(department);
					userDTO.setDivision(division);
					userDTO.setUserCatergory(user.getUserCatergory());

				} else {

					userDTO.setUserCatergory(user.getUserCatergory());
				}
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setSecretQuestion(user.getSecretQuestion());
				userDTO.setSecretAnswer(user.getSecretAnswer());

				User userCreated = new User();

				if (user.getCreatedBy() != null) {

					userCreated.setId(user.getCreatedBy().getId());
					userCreated.setfName(user.getCreatedBy().getfName());
					userCreated.setlName(user.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (user.getChangedBy() != null) {

					userChanged.setId(user.getChangedBy().getId());
					userChanged.setfName(user.getChangedBy().getfName());
					userChanged.setlName(user.getChangedBy().getlName());

				} else {

				}

				userDTO.setChangedBy(userChanged);
				userDTO.setCreatedBy(userCreated);
				usersDTO.add(userDTO);

			}

			result = new UsersResult(true, usersDTO, "");

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult retrieveProcurementOfficers() {

		UsersResult result = null;

		try {

			List<User> users = new ArrayList<User>(userService.getUsers()
					.size());
			List<User> usersDTO = new ArrayList<User>();

			users = userService.getProcurementOfficers();

			for (User user : users) {

				User userDTO = new User();
				userDTO.setId(user.getId());
				userDTO.setfName(user.getfName());
				userDTO.setlName(user.getlName());
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setStatus(user.getStatus());

				// userDTO.setRoleName(user.get);
				userDTO.setRoleId(user.getRoleId());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					Department department = new Department();
					department.setId(user.getDepartment().getId());
					department.setDepartmentName(user.getDepartment()
							.getDepartmentName());

					userDTO.setDepartment(department);
					userDTO.setUserCatergory(user.getUserCatergory());

				} else {

					userDTO.setUserCatergory(user.getUserCatergory());
				}
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setSecretQuestion(user.getSecretQuestion());
				userDTO.setSecretAnswer(user.getSecretAnswer());

				User userCreated = new User();

				if (user.getCreatedBy() != null) {

					userCreated.setId(user.getCreatedBy().getId());
					userCreated.setfName(user.getCreatedBy().getfName());
					userCreated.setlName(user.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (user.getChangedBy() != null) {

					userChanged.setId(user.getChangedBy().getId());
					userChanged.setfName(user.getChangedBy().getfName());
					userChanged.setlName(user.getChangedBy().getlName());

				} else {

				}

				userDTO.setChangedBy(userChanged);
				userDTO.setCreatedBy(userCreated);
				usersDTO.add(userDTO);

			}

			result = new UsersResult(true, usersDTO, "");

		} catch (AccessDeniedException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.ACCESSDENIEDEXCEPTION + ": "
							+ ex.getLocalizedMessage());

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult retrieveUsersWithLimits(int offSet, int limit) {

		UsersResult result = null;

		try {

			List<User> users = new ArrayList<User>(userService.getUsers()
					.size());
			List<User> usersDTO = new ArrayList<User>();

			// users = userService.getUsers(offSet, limit);
			users = userService.getUsers();

			for (User user : users) {

				User userDTO = new User();
				userDTO.setId(user.getId());
				userDTO.setfName(user.getfName());
				userDTO.setlName(user.getlName());
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setStatus(user.getStatus());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					Department department = new Department();
					department.setId(user.getDepartment().getId());
					department.setDepartmentName(user.getDepartment()
							.getDepartmentName());
					department.setDepartmentCode(user.getDepartment()
							.getDepartmentCode());

					Division division = new Division();
					division.setDivisionCode(user.getDivision()
							.getDivisionCode());
					division.setDivisionName(user.getDivision()
							.getDivisionName());

					userDTO.setDepartment(department);
					userDTO.setDivision(division);
					userDTO.setUserCatergory(user.getUserCatergory());

				} else {

					userDTO.setUserCatergory(user.getUserCatergory());
				}
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setSecretQuestion(user.getSecretQuestion());
				userDTO.setSecretAnswer(user.getSecretAnswer());

				User userCreated = new User();

				if (user.getCreatedBy() != null) {

					userCreated.setId(user.getCreatedBy().getId());
					userCreated.setfName(user.getCreatedBy().getfName());
					userCreated.setlName(user.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (user.getChangedBy() != null) {

					userChanged.setId(user.getChangedBy().getId());
					userChanged.setfName(user.getChangedBy().getfName());
					userChanged.setlName(user.getChangedBy().getlName());

				} else {

				}

				userDTO.setChangedBy(userChanged);
				userDTO.setCreatedBy(userCreated);
				usersDTO.add(userDTO);

			}

			result = new UsersResult(true, usersDTO, "");

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult retrieveUsersByDepartment(Department departmentTo) {

		UsersResult result = null;

		try {

			List<User> users = new ArrayList<User>(userService.getUsers()
					.size());
			List<User> usersDTO = new ArrayList<User>();

			Department department = new Department();
			department = organizationUnitService.getDepartmentById(departmentTo
					.getId());

			users = userService.getUsersByDepartment(department);

			for (User user : users) {

				User userDTO = new User();
				userDTO.setId(user.getId());
				userDTO.setfName(user.getfName());
				userDTO.setlName(user.getlName());
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setStatus(user.getStatus());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					Department departmentDTO = new Department();
					departmentDTO.setId(user.getDepartment().getId());
					departmentDTO.setDepartmentName(user.getDepartment()
							.getDepartmentName());
					departmentDTO.setDepartmentCode(user.getDepartment()
							.getDepartmentCode());

					Division division = new Division();
					division.setDivisionCode(user.getDivision()
							.getDivisionCode());
					division.setDivisionName(user.getDivision()
							.getDivisionName());

					userDTO.setDepartment(departmentDTO);
					userDTO.setDivision(division);
					userDTO.setUserCatergory(user.getUserCatergory());

				} else {

					userDTO.setUserCatergory(user.getUserCatergory());
				}
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setSecretQuestion(user.getSecretQuestion());
				userDTO.setSecretAnswer(user.getSecretAnswer());

				User userCreated = new User();

				if (user.getCreatedBy() != null) {

					userCreated.setId(user.getCreatedBy().getId());
					userCreated.setfName(user.getCreatedBy().getfName());
					userCreated.setlName(user.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (user.getChangedBy() != null) {

					userChanged.setId(user.getChangedBy().getId());
					userChanged.setfName(user.getChangedBy().getfName());
					userChanged.setlName(user.getChangedBy().getlName());

				} else {

				}

				userDTO.setChangedBy(userChanged);
				userDTO.setCreatedBy(userCreated);
				usersDTO.add(userDTO);

			}

			result = new UsersResult(true, usersDTO, "");

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult retrieveAllPermissions() {

		UsersResult result = null;

		try {

			List<Permission> permissions = new ArrayList<Permission>(
					userService.getPermissions().size());

			permissions = userService.getPermissions();

			result = new UsersResult(permissions, true);

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult retrievePermissionsByRole(Role actionRole) {

		UsersResult result = null;

		try {

			Role role = new Role();
			role = userService.getRoleById(actionRole.getId());

			List<Permission> permissions = new ArrayList<Permission>(
					userService.getPermissions().size());

			for (Permission permission : role.getPermissions()) {

				Permission permissionDTO = new Permission();
				permissionDTO.setId(permission.getId());
				permissionDTO.setName(permission.getName());
				permissionDTO.setDescription(permission.getDescription());

				permissions.add(permissionDTO);

			}
			result = new UsersResult(permissions, true);

		} catch (NullPointerException ex) {

			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());

		}

		return result;

	}

	private UsersResult saveRole(Role actionRole) {

		UsersResult result = null;

		try {
			Role role = new Role();

			List<Permission> permissionsList = new ArrayList<Permission>();

			for (Permission permissionDTO : actionRole.getPermissions()) {
				Permission permission = new Permission();
				permission = userService.getPermissionById(permissionDTO
						.getId());

				permissionsList.add(permission);
			}

			role.setName(actionRole.getName());
			role.setDescription(actionRole.getDescription());

			if (!permissionsList.isEmpty()) {
				role.setPermissionList(permissionsList);
			} else {
				result = new UsersResult(false,
						"Role Not Saved: No Permissions Set!!!");
			}

			userService.saveRole(role);

			result = new UsersResult(true, "Role Saved Successfully");

		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new UsersResult(false, "" + e.getMessage());

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + e.getMessage());
		}

		return result;
	}

	private UsersResult editRole(Role actionRole) {

		UsersResult result = null;

		try {

			List<Permission> permissionsList = new ArrayList<Permission>();

			Role role = new Role();
			role = userService.getRoleById(actionRole.getId());
			role.setName(actionRole.getName());
			role.setDescription(actionRole.getDescription());

			if (actionRole.getPermissions() != null) {

				for (Permission permissionDTO : actionRole.getPermissions()) {
					Permission permission = new Permission();
					permission = userService.getPermissionById(permissionDTO
							.getId());

					permissionsList.add(permission);

					// System.out.println("Permission: "+permission.getName());
				}

				role.setPermissionList(permissionsList);
			} else {

			}

			userService.editRole(role);

			result = new UsersResult(true, "Role Edited Successfully");

		} catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new UsersResult(false, "" + e.getMessage());

		} catch (NullPointerException ex) {
			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {
			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());
		}

		return result;
	}

	private UsersResult deleteRole(Role actionRole) {

		UsersResult result = null;

		try {
			Role role = new Role();
			role = userService.getRoleById(actionRole.getId());

			userService.deleteRole(role);

			result = new UsersResult(true, "Role Deleted Successfully");

		} catch (ValidationFailedException ex) {
			ex.printStackTrace();
			result = new UsersResult(false, "" + ex.getMessage());

		} catch (NullPointerException ex) {
			ex.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ ex.getMessage());

		} catch (Exception ex) {
			ex.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + ex.getMessage());
		}

		return result;
	}

	private UsersResult retrieveAllRoles() {

		UsersResult result = null;

		try {
			List<Role> roles = new ArrayList<Role>(userService.getRoles()
					.size());
			List<Role> roleDTOs = new ArrayList<Role>();

			roles = userService.getRoles();
			// userService.get

			for (Role role : roles) {

				Role roleDTO = new Role();
				roleDTO.setId(role.getId());
				roleDTO.setName(role.getName());
				roleDTO.setDescription(role.getDescription());

				User userCreated = new User();

				if (role.getCreatedBy() != null) {

					userCreated.setId(role.getCreatedBy().getId());
					userCreated.setfName(role.getCreatedBy().getfName());
					userCreated.setlName(role.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (role.getChangedBy() != null) {

					userChanged.setId(role.getChangedBy().getId());
					userChanged.setfName(role.getChangedBy().getfName());
					userChanged.setlName(role.getChangedBy().getlName());

				} else {

				}

				roleDTO.setChangedBy(userChanged);
				roleDTO.setCreatedBy(userCreated);

				roleDTOs.add(roleDTO);
				// role.getP
			}

			result = new UsersResult(true, roleDTOs);

		} /*
		 * catch (ValidationFailedException e) { e.printStackTrace(); result =
		 * new UsersResult(false, "" + e.getMessage());
		 * 
		 * }
		 */catch (NullPointerException e) {
			e.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + e.getMessage());
		}

		return result;
	}

	private UsersResult retrieveUserRoles(User userTo) {

		UsersResult result = null;

		User user = new User();
		user = userService.getUserById(userTo.getId());

		try {
			List<Role> roles = new ArrayList<Role>(user.getRolesList().size());
			List<Role> roleDTOs = new ArrayList<Role>();

			roles = user.getRolesList();

			for (Role role : roles) {

				Role roleDTO = new Role();
				roleDTO.setId(role.getId());
				roleDTO.setName(role.getName());
				roleDTO.setDescription(role.getDescription());

				User userCreated = new User();

				if (role.getCreatedBy() != null) {

					userCreated.setId(role.getCreatedBy().getId());
					userCreated.setfName(role.getCreatedBy().getfName());
					userCreated.setlName(role.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (role.getChangedBy() != null) {

					userChanged.setId(role.getChangedBy().getId());
					userChanged.setfName(role.getChangedBy().getfName());
					userChanged.setlName(role.getChangedBy().getlName());

				} else {

				}

				roleDTO.setChangedBy(userChanged);
				roleDTO.setCreatedBy(userCreated);

				roleDTOs.add(roleDTO);

			}

			result = new UsersResult(true, roleDTOs);

		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + e.getMessage());
		}

		return result;
	}

	/*
	 * This method gets the Roles a User doesn't have
	 */
	private UsersResult retrieveRolesNotOnUser(User userTo) {

		UsersResult result = null;

		try {

			User user = new User();
			user = userService.getUserById(userTo.getId());

			List<Role> roles = new ArrayList<Role>(userService.getRoles()
					.size());
			List<Role> roleDTOs = new ArrayList<Role>();
			List<Role> userRoles = new ArrayList<Role>();
			List<Role> newRoles = new ArrayList<Role>();

			roles = userService.getRoles();

			userRoles = user.getRolesList();

			// Remove the Roles User Already Has

			for (Role role : userRoles) {

				for (Role roleOld : roles) {

					if (role.getId().contentEquals(roleOld.getId())) {

					} else {

						newRoles.add(roleOld);

					}

				}

			}

			for (Role role : newRoles) {

				Role roleDTO = new Role();
				roleDTO.setId(role.getId());
				roleDTO.setName(role.getName());
				roleDTO.setDescription(role.getDescription());

				User userCreated = new User();

				if (role.getCreatedBy() != null) {

					userCreated.setId(role.getCreatedBy().getId());
					userCreated.setfName(role.getCreatedBy().getfName());
					userCreated.setlName(role.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (role.getChangedBy() != null) {

					userChanged.setId(role.getChangedBy().getId());
					userChanged.setfName(role.getChangedBy().getfName());
					userChanged.setlName(role.getChangedBy().getlName());

				} else {

				}

				roleDTO.setChangedBy(userChanged);
				roleDTO.setCreatedBy(userCreated);

				roleDTOs.add(roleDTO);
				// role.getP
			}

			result = new UsersResult(true, roleDTOs);
		} catch (NullPointerException e) {
			e.printStackTrace();
			result = new UsersResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION + ": "
							+ e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			result = new UsersResult(false, ProcnetErrorCodes.GENERAL_EXCEPTION
					+ ": " + e.getMessage());
		}
		return result;

	}

	/**
	 * @param user
	 * @return
	 */
	private UsersResult registerDocumentUser(User userTo) {

		UsersResult result = null;

		try {

			User user = new User();
			user = userService.getUserById(userTo.getId());

			userService.registerUser(user);

			result = new UsersResult(true, "Document Account Registered for "
					+ user.getFullName());

		} catch (ValidationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			result = new UsersResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION + ": "
							+ e.getMessage());
		}
		return result;

	}

	/**
	 * @return Get All Users without CodeBeamer Accounts
	 */
	private UsersResult retrieveDocNotRegisteredUsers() {

		UsersResult result = null;

		try {
			List<User> users = new ArrayList<User>(userService.getUsers()
					.size());
			List<User> usersDTO = new ArrayList<User>();

			users = userService.getUnRegisteredDocumentUsers();
			for (User user : users) {

				User userDTO = new User();
				userDTO.setId(user.getId());
				userDTO.setfName(user.getfName());
				userDTO.setlName(user.getlName());
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setStatus(user.getStatus());
				userDTO.setRegistered(user.isRegistered());

				// userDTO.setRoleName(user.get);
				userDTO.setRoleId(user.getRoleId());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					Department department = new Department();
					department.setId(user.getDepartment().getId());
					department.setDepartmentName(user.getDepartment()
							.getDepartmentName());
					department.setDepartmentCode(user.getDepartment()
							.getDepartmentCode());

					Division division = new Division();
					division.setDivisionCode(user.getDivision()
							.getDivisionCode());
					division.setDivisionName(user.getDivision()
							.getDivisionName());

					userDTO.setDepartment(department);
					userDTO.setDivision(division);
					userDTO.setUserCatergory(user.getUserCatergory());

				} else {

					userDTO.setUserCatergory(user.getUserCatergory());
				}
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setSecretQuestion(user.getSecretQuestion());
				userDTO.setSecretAnswer(user.getSecretAnswer());

				User userCreated = new User();

				if (user.getCreatedBy() != null) {

					userCreated.setId(user.getCreatedBy().getId());
					userCreated.setfName(user.getCreatedBy().getfName());
					userCreated.setlName(user.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (user.getChangedBy() != null) {

					userChanged.setId(user.getChangedBy().getId());
					userChanged.setfName(user.getChangedBy().getfName());
					userChanged.setlName(user.getChangedBy().getlName());

				} else {

				}

				userDTO.setChangedBy(userChanged);
				userDTO.setCreatedBy(userCreated);
				usersDTO.add(userDTO);

			}

			result = new UsersResult(true, usersDTO, "");

		} catch (Exception e) {
			e.printStackTrace();

			result = new UsersResult(false, "Retrieve Users Failed "
					+ e.getMessage());

		}
		return result;

	}

	/**
	 * @return Get All Users with CodeBeamer Accounts
	 */
	private UsersResult retrieveDocRegisteredUsers() {

		UsersResult result = null;

		try {
			List<User> users = new ArrayList<User>(userService.getUsers()
					.size());
			List<User> usersDTO = new ArrayList<User>();

			users = userService.getRegisteredDocumentUsers();
			for (User user : users) {

				User userDTO = new User();
				userDTO.setId(user.getId());
				userDTO.setfName(user.getfName());
				userDTO.setlName(user.getlName());
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setStatus(user.getStatus());
				userDTO.setRegistered(user.isRegistered());

				// userDTO.setRoleName(user.get);
				userDTO.setRoleId(user.getRoleId());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					Department department = new Department();
					department.setId(user.getDepartment().getId());
					department.setDepartmentName(user.getDepartment()
							.getDepartmentName());
					department.setDepartmentCode(user.getDepartment()
							.getDepartmentCode());

					Division division = new Division();
					division.setDivisionCode(user.getDivision()
							.getDivisionCode());
					division.setDivisionName(user.getDivision()
							.getDivisionName());

					userDTO.setDepartment(department);
					userDTO.setDivision(division);
					userDTO.setUserCatergory(user.getUserCatergory());

				} else {

					userDTO.setUserCatergory(user.getUserCatergory());
				}
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setSecretQuestion(user.getSecretQuestion());
				userDTO.setSecretAnswer(user.getSecretAnswer());

				User userCreated = new User();

				if (user.getCreatedBy() != null) {

					userCreated.setId(user.getCreatedBy().getId());
					userCreated.setfName(user.getCreatedBy().getfName());
					userCreated.setlName(user.getCreatedBy().getlName());
				} else {

				}

				User userChanged = new User();
				if (user.getChangedBy() != null) {

					userChanged.setId(user.getChangedBy().getId());
					userChanged.setfName(user.getChangedBy().getfName());
					userChanged.setlName(user.getChangedBy().getlName());

				} else {

				}

				userDTO.setChangedBy(userChanged);
				userDTO.setCreatedBy(userCreated);
				usersDTO.add(userDTO);

			}

			result = new UsersResult(true, usersDTO, "");

		} catch (Exception e) {
			e.printStackTrace();

			result = new UsersResult(false, "Retrieve Users Failed "
					+ e.getMessage());

		}
		return result;
	}
}
