/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import java.util.List;


import com.gwtplatform.mvp.client.UiHandlers;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.units.Department;


public interface UsersUiHandlers extends UiHandlers {

	void onNewButtonClicked();

	void onSaveUserButtonClicked(User user);

	void onCancelButton();

	void onSaveRoleButtonClicked(Role role);

	void onEditUserButtonClicked(User user);

	void onDeleteUserButtonClicked(User user);

	void onEditRoleButtonClicked(Role role);

	void onDeleteRoleButtonClicked(Role role);

	void onLoadDivisionData();
	
	void onLoadDepartments();

	void onLoadRoleData();

	void onLoadUserGridData();

	void onLoadUsersWithLimits();
	
	void onLoadUsersByDepartment( Department department );

	void onLoadPermissionsGrid();

	void onLoadPermissionsUser();

	void onLoadPermissionsRole(Role role);

	void onLoadRoleUser(User user);

	void onLoadNewRoles(User user);

	void onLoadUsersByRole();

	void onActivateUserButtonClicked(User user);

	void onDeActivateUserButtonClicked(User user);

	void onReportButtonClicked();

	/* void onSavePasswordPolicy(RegularExpression expression); */

	void onAddUserRolesButtonClicked(User user, List<Role> role);

	void onRemoveUserRolesButtonClicked(User user, List<Role> role);

	void onResultSetNextButtonClicked();

	void onResultSetFirstButtonClicked();

	void onResultSetPreviousButtonClicked();

	void onCreateSignature(List<String> signatureStrings);

	void onValidateSignature(String sinature);

	void onCreateSignature(List<String> signatureStrings, User user);

	void onValidateSignature(String sinature, User user);
}
