package com.planetsystems.monitoring.client.views.project.admin;

import java.util.ArrayList;


import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.events.UsersUiHandlers;
import com.planetsystems.monitoring.client.widgets.StatusBar;
import com.planetsystems.monitoring.client.widgets.ToolBar;
import com.planetsystems.monitoring.client.widgets.buttons.CancelButton;
import com.planetsystems.monitoring.client.widgets.buttons.DeleteButton;
import com.planetsystems.monitoring.client.widgets.buttons.EditButton;
import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.planetsystems.monitoring.client.widgets.buttons.SearchButton;
import com.planetsystems.monitoring.client.widgets.esignatures.SigNetRegistrationWindow;
import com.planetsystems.monitoring.model.Permission;
import com.planetsystems.monitoring.model.Role;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.UserCatergory;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordExpandEvent;
import com.smartgwt.client.widgets.grid.events.RecordExpandHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerRecord;
import com.planetsystems.monitoring.client.listgrids.PasswordPolicyListGrid;
import com.planetsystems.monitoring.client.listgrids.PermissionsListGrid;
import com.planetsystems.monitoring.client.listgrids.UserRoleListGrid;
import com.planetsystems.monitoring.client.listgrids.UsersContextAreaListGrid;
import com.planetsystems.monitoring.client.presenters.project.admin.UsersPresenter;

public class UsersView extends ViewWithUiHandlers<UsersUiHandlers> implements UsersPresenter.MyView {

	private static final String CONTEXT_AREA_WIDTH = "100%";

	private HLayout mainPanel;
	private VLayout panel;
	private VLayout VL_USERS;
	private VLayout VL_ROLES;
	private VLayout VL_ADD_USERS;
	private VLayout VL_PERMISSIONS;
	private HLayout HL_PERMISSIONS;
	private VLayout VL_PASSWORD;

	private SectionStack stackPermissions;
	private SectionStackSection section1;

	private final ToolBar toolBarUsers;
	private final ToolBar toolBarRoles;

	private final UsersContextAreaListGrid usersListGrid;
	public final PermissionsListGrid permissionsListGrid;
	private final UserRoleListGrid rolesListGrid;
	private UserRoleListGrid userRolesListGrid;
	private ListGrid permissionSelectedListGrid;
	private final PasswordPolicyListGrid passwordPolicyListGrid;
	private ListGrid rolesSelectedListGrid;
	private final UserRoleListGrid newRolesListGrid;
	private ListGrid newRolesSelectedListGrid;

	private final StatusBar statusBar;

	private DynamicForm DF_USERS = new DynamicForm();
	private DynamicForm DF_USERS_2 = new DynamicForm();
	private DynamicForm DF_USERS1 = new DynamicForm();
	private DynamicForm DF_USERS2 = new DynamicForm();
	private DynamicForm DF_ROLES = new DynamicForm();
	private DynamicForm DF_PERMISSIONS = new DynamicForm();
	private DynamicForm DF_POLICY = new DynamicForm();

	private final TabSet tabSet;
	private Tab viewUsersTab;
	private Tab roleTab;
	private Tab permissionsTab;
	private Tab addUsersTab;
	private Tab passwordPolicyTab;

	HLayout HL_ROLE_BUTTONS = new HLayout();
	HLayout HL_USERS = new HLayout();
	HLayout HL_USERS_BUTTONS = new HLayout();
	HLayout HL_PERMISSIONS_BUTTONS = new HLayout();
	HLayout HL_POLICY_BUTTONS = new HLayout();

	SaveButton saveUserButton = new SaveButton("Save");
	EditButton editUserButton = new EditButton("Edit");
	SaveButton addUserButton = new SaveButton("New User");
	CancelButton cancelUserButton = new CancelButton("Cancel");
	SaveButton saveRoleButton = new SaveButton("Save");
	CancelButton cancelRoleButton = new CancelButton("Cancel");
	EditButton editRoleButton = new EditButton("Edit");
	DeleteButton deleteRoleButton = new DeleteButton("Delete");
	Button permissionDoneButton = new Button("Done");
	Button setPermissionsButton = new Button("Set Permissions");
	SaveButton savePolicyButton = new SaveButton("Save");
	CancelButton cancelPolicyButton = new CancelButton("Cancel");
	SaveButton saveNewUserRoles = new SaveButton("Save Roles");
	CancelButton clearNewUserRoleButton = new CancelButton("Cancel");
	SaveButton doneButton = new SaveButton("Submit");
	EditButton signButton = new EditButton("Signature");
	SaveButton registerSignButton = new SaveButton("Register Signature",200);
	
	// Users Controls
	public TextItem firstNameText = new TextItem("firstNameText", "Frist Name");
	private TextItem lastNameText = new TextItem("lastNameText", "Last Name");
	private TextItem emailText = new TextItem("emailText", "Email");
	private TextItem userNameText = new TextItem("userNameText", "User Name");
	private PasswordItem passwordText = new PasswordItem("passwordText", "Password");
	private PasswordItem retypePasswordText = new PasswordItem("retypePasswordText", "Retype Password");
	private TextItem phoneNumberText = new TextItem("phoneNumberText", "Phone Number");
	private CheckboxItem statusCheckboxItem = new CheckboxItem("statusCheckboxItem", "Active");
	private ComboBoxItem departmentCombo = new ComboBoxItem("departmentCombo", "Attach To Division");
	private ComboBoxItem roleCombo = new ComboBoxItem("roleCombo", "Role");
	private ComboBoxItem userCategoryCombo = new ComboBoxItem("userCategoryCombo", "User Category ");
	private ComboBoxItem roleFilterPermissionCombo = new ComboBoxItem("roleFilterPermissionCombo", "Filter By Role");
	private ComboBoxItem permissionFilterByUser = new ComboBoxItem("permissionFilterByUser", "Filter By User");
	private TextItem secretQuestionText = new TextItem("secretQuestionText", "Secret Question");
	private TextItem secretAnswerText = new TextItem("secretAnswerText", "Secret Answer");

	// Roles Controls
	private TextItem roleIDText = new TextItem("roleIDText", "Role ID");
	private TextItem roleNameText = new TextItem("roleNameText", "Role Name");
	private TextItem roleDescriptionText = new TextItem("roleDescriptionText", "Role Description");

	private HLayout rollOverCanvas;
	private ListGridRecord rollOverRecord;

	private String userID = null;

	private RadioGroupItem passwordRadioGroupItem = new RadioGroupItem();

	private DetailViewer passwordViewer = new DetailViewer();
	private DetailViewerRecord viewerRecord = new DetailViewerRecord();

	CheckboxItem oneNumericValue = new CheckboxItem("oneNumericValue", "The Password Should Contain atleast one Numeric value (0-9).");
	CheckboxItem oneUppercaseValue = new CheckboxItem("oneUppercaseValue", "The Password should contain atleast one Alphabet of (a-z) in lower case.");
	CheckboxItem oneLowercaseValue = new CheckboxItem("oneLowercaseValue", "The Password should contain atleast one Alphabet of (a-z) in lower case.");
	CheckboxItem oneSpecialCharacterValue = new CheckboxItem("oneSpecialCharacterValue", "The Password should contain atleast one Special character out of the following values : ! @ # $ * - ~ =");
	CheckboxItem notContainValue = new CheckboxItem("notContainValue", "The Password should not contain any of the following values:+ [] {} %  () ^ & : / >< . , |");
	CheckboxItem lengthValue = new CheckboxItem("lengthValue", "Set Length");

	private TextItem lowerThresholdText = new TextItem("lowerThresholdText", "Lower Threshold");
	private TextItem upperThresholdText = new TextItem("upperThresholdText", "Upper Threshold");
	
	private TextItem searchText = new TextItem("searchText", "Search");
	private SearchButton searchButton = new SearchButton("Search");

	private HLayout HL_USER_ROLES = new HLayout();
	private HLayout HL_NEW_ROLES_BUTTON = new HLayout();

	private HLayout HL_USERS_ACTIONS;
	HLayout HL_USERS_WINDOW_BUTTONS = new HLayout();

	// This is used to determine whether user is editing a user account or
	// saving a new user
	// That the doneButton can call the appropriate function
	// Values: 1. SAVE 2. EDIT
	private String userActionMode;

	private ComboBoxItem departmentLoadCombo = new ComboBoxItem("departmentLoadCombo", "Load By Department");
	
	@Inject
	public UsersView(ToolBar toolBar, UsersContextAreaListGrid listGrid, StatusBar statusBar, final TabSet tabSet, ToolBar toolBarRoles, final PermissionsListGrid permissionsListGrid, final UserRoleListGrid rolesListGrid, PasswordPolicyListGrid passwordPolicyListGrid,
			final UserRoleListGrid newRolesListGrid) {


		this.toolBarUsers = toolBar;
		this.toolBarRoles = toolBarRoles;
		this.usersListGrid = listGrid;
		this.statusBar = statusBar;
		this.tabSet = tabSet;
		this.permissionsListGrid = permissionsListGrid;
		this.rolesListGrid = rolesListGrid;
		this.passwordPolicyListGrid = passwordPolicyListGrid;
		this.newRolesListGrid = newRolesListGrid;

		panel = new VLayout();
		
		mainPanel = new HLayout();
		panel.setWidth100();
		panel.setHeight100();

		panel.setWidth100();
		panel.setHeight100();

		tabSet.setWidth100();
		tabSet.setHeight100();

		rolesListGrid.setHeight("*");

		// Tabs
		viewUsersTab = new Tab();
		viewUsersTab.setTitle("Users ");
		viewUsersTab.setCanClose(false);
		viewUsersTab.setID("viewusers");

		roleTab = new Tab();
		roleTab.setTitle("Roles ");
		roleTab.setCanClose(false);
		roleTab.setID("roles");

		addUsersTab = new Tab();
		addUsersTab.setTitle("Add Users ");
		addUsersTab.setCanClose(false);
		addUsersTab.setID("addusers");

		permissionsTab = new Tab();
		permissionsTab.setTitle("Permissions ");
		permissionsTab.setID("permissions");
		permissionsTab.setCanClose(false);

		passwordPolicyTab = new Tab();
		passwordPolicyTab.setTitle("Password Policy ");
		passwordPolicyTab.setID("passwordPolicyTab");
		passwordPolicyTab.setCanClose(false);

		tabSet.addTab(viewUsersTab);
		// tabSet.addTab(addUsersTab);
		tabSet.addTab(roleTab);
		tabSet.addTab(permissionsTab);
		tabSet.addTab(passwordPolicyTab);

		HL_USERS_ACTIONS = new HLayout();
		HL_USERS_ACTIONS.setWidth100();
		HL_USERS_ACTIONS.setHeight("2%");
		HL_USERS_ACTIONS.setMembersMargin(10);
		HL_USERS_ACTIONS.setAlign(Alignment.CENTER);
		HL_USERS_ACTIONS.setMembers(addUserButton, editUserButton,registerSignButton);

		// Add Users
		VL_USERS = new VLayout();
		VL_USERS.setHeight100();
		VL_USERS.setWidth100();
		VL_USERS.setMembersMargin(2);
		
		
		HLayout HL_LOAD_DF=new HLayout();
		HL_LOAD_DF.setHeight("2%");
		
		DynamicForm searchFieldDF =new DynamicForm();
		searchFieldDF.setFields(searchText);
		for (FormItem FI : searchFieldDF.getFields()) {
			FI.setWidth(300);
			//FI.setCellHeight(40);
		}
		
		HLayout searchFieldContainer =new HLayout();
		searchFieldContainer.setHeight100();
		searchFieldContainer.setWidth("50%");
		searchFieldContainer.setAlign(Alignment.RIGHT);
		searchFieldContainer.setAlign(VerticalAlignment.BOTTOM);
		searchFieldContainer.setMembers(searchFieldDF,searchButton);
		
		HLayout loadContainer =new HLayout();
		loadContainer.setHeight100();
		loadContainer.setWidth("50%");
		loadContainer.setAlign(Alignment.LEFT);
		
		DynamicForm DF_LOAD=new DynamicForm();
		departmentLoadCombo.setTitleOrientation(TitleOrientation.TOP);
		DF_LOAD.setFields(departmentLoadCombo);
		for (FormItem FI : DF_LOAD.getFields()) {
			FI.setWidth(300);
			FI.setCellHeight(40);
		}
		
		loadContainer.addMember(DF_LOAD);
		
		HL_LOAD_DF.setMembers(loadContainer,searchFieldContainer);
		
		VL_USERS.addMember(toolBarUsers);
		VL_USERS.addMember(HL_LOAD_DF);
		VL_USERS.addMember(usersListGrid);
		VL_USERS.addMember(statusBar);
		VL_USERS.addMember(HL_USERS_ACTIONS);
		// VL_USERS.addMember(HL_USERS_BUTTONS);

		// Roles
		VL_ROLES = new VLayout();
		VL_ROLES.setHeight100();
		VL_ROLES.setWidth100();
		VL_ROLES.setMembersMargin(2);

		DF_ROLES.setWidth100();
		DF_ROLES.setNumCols(6);
		DF_ROLES.setWrapItemTitles(false);

		DF_ROLES.setItems(roleNameText, roleDescriptionText);
		for (FormItem FI : DF_ROLES.getFields()) {
			FI.setWidth(300);
			FI.setCellHeight(47);
		}

		HL_ROLE_BUTTONS.setWidth100();
		HL_ROLE_BUTTONS.setHeight("2%");
		HL_ROLE_BUTTONS.setMembersMargin(10);
		HL_ROLE_BUTTONS.setAlign(Alignment.CENTER);
		HL_ROLE_BUTTONS.setMembers(setPermissionsButton, saveRoleButton, editRoleButton, deleteRoleButton, cancelRoleButton);

		VL_ROLES.addMember(toolBarRoles);
		VL_ROLES.addMember(DF_ROLES);
		VL_ROLES.addMember(rolesListGrid);
		VL_ROLES.addMember(HL_ROLE_BUTTONS);

		// Permissions

		VL_PERMISSIONS = new VLayout();
		VL_PERMISSIONS.setHeight100();
		VL_PERMISSIONS.setWidth100();
		VL_PERMISSIONS.setMembersMargin(5);

		stackPermissions = new SectionStack();
		stackPermissions.setWidth100();
		stackPermissions.setHeight("95%");

		DF_PERMISSIONS.setItems(permissionFilterByUser, roleFilterPermissionCombo);
		DF_PERMISSIONS.setWrapItemTitles(false);
		for (FormItem FI : DF_PERMISSIONS.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		String title = Canvas.imgHTML("icons/16/permissions.png" + "Select Permissions To Add To Role");
		section1 = new SectionStackSection("Select Permissions To Add To Role");
		section1.setCanCollapse(false);
		section1.setExpanded(true);
		section1.setControls(DF_PERMISSIONS);

		permissionSelectedListGrid = new ListGrid();
		permissionSelectedListGrid.setWidth("50%");
		permissionSelectedListGrid.setHeight100();

		ListGridField permissionID = new ListGridField("permissionID", "Permission ID");
		permissionID.setHidden(true);

		ListGridField permissionName = new ListGridField("permissionName", "Permission Name");

		permissionSelectedListGrid.setFields(permissionID, permissionName);

		HL_PERMISSIONS = new HLayout();
		HL_PERMISSIONS.setHeight100();
		HL_PERMISSIONS.setWidth100();
		HL_PERMISSIONS.setMembersMargin(5);

		HL_PERMISSIONS.addMember(permissionsListGrid);
		HL_PERMISSIONS.addMember(permissionSelectedListGrid);

		HL_PERMISSIONS_BUTTONS = new HLayout();
		HL_PERMISSIONS_BUTTONS.setHeight("*");
		HL_PERMISSIONS_BUTTONS.setWidth100();
		HL_PERMISSIONS_BUTTONS.setMembersMargin(10);
		HL_PERMISSIONS_BUTTONS.setAlign(Alignment.CENTER);

		HL_PERMISSIONS_BUTTONS.addMember(permissionDoneButton);

		section1.setItems(HL_PERMISSIONS);

		stackPermissions.addSection(section1);

		VL_PERMISSIONS.setMembers(stackPermissions, HL_PERMISSIONS_BUTTONS);

		// Password Policy

		VL_PASSWORD = new VLayout();
		VL_PASSWORD.setHeight100();
		VL_PASSWORD.setWidth100();
		VL_PASSWORD.setMembersMargin(5);

		passwordRadioGroupItem.setTitle("Select Policy for Passwords");
		passwordRadioGroupItem.setColSpan("*");
		passwordRadioGroupItem.setRequired(true);
		passwordRadioGroupItem.setVertical(true);
		passwordRadioGroupItem.setValueMap("The Password Should Contain atleast one Numeric value (0-9).", "The Password should contain atleast one Alphabet of (A-Z) in uppercase.", "The Password should contain atleast one Alphabet of (a-z) in lower case.",
				"The Password should contain atleast one Special character out of the following values : ! @ # $ * - ~ =");

		DF_POLICY.setTitleOrientation(TitleOrientation.TOP);
		DF_POLICY.setItems(oneNumericValue, oneUppercaseValue, oneLowercaseValue, oneSpecialCharacterValue, notContainValue, lengthValue, lowerThresholdText, upperThresholdText);
		lowerThresholdText.setDisabled(true);
		upperThresholdText.setDisabled(true);

		HL_POLICY_BUTTONS.setHeight("*");
		HL_POLICY_BUTTONS.setWidth100();
		HL_POLICY_BUTTONS.setMembersMargin(10);
		HL_POLICY_BUTTONS.setAlign(Alignment.CENTER);
		HL_POLICY_BUTTONS.setMembers(savePolicyButton, cancelPolicyButton);

		VL_PASSWORD.addMember(DF_POLICY);
		VL_PASSWORD.addMember(HL_POLICY_BUTTONS);

		passwordPolicyListGrid.setHeight("*");

		VL_PASSWORD.addMember(passwordPolicyListGrid);

		viewUsersTab.setPane(VL_USERS);
		roleTab.setPane(VL_ROLES);

		permissionsTab.setPane(VL_PERMISSIONS);
		passwordPolicyTab.setPane(VL_PASSWORD);

		panel.addMember(tabSet);
		
		mainPanel.addMember(panel);
		

		bindCustomUiHandlers();

		tabSet.addTabSelectedHandler(new TabSelectedHandler() {

			public void onTabSelected(TabSelectedEvent event) {

				if (event.getID().contains("addusers")) {

					HL_USER_ROLES.setMembers(userRolesListGrid, rolesSelectedListGrid);

					// Load All Roles To Add To New User
					if (getUiHandlers() != null) {

						getUiHandlers().onLoadRoleData();

					}
					saveUserButton.setDisabled(false);

				}

			}

		});

		//
		permissionsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

			public void onSelectionChanged(SelectionEvent event) {
				permissionSelectedListGrid.setData(permissionsListGrid.getSelection());

			}

		});

		cancelUserButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				tabSet.selectTab("viewusers");
				saveUserButton.setDisabled(false);
				HL_USERS.setMembers(DF_USERS, DF_USERS1, DF_USERS2);

			}

		});

		addUserButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				setUserActionMode("SAVE");

				VL_ADD_USERS = new VLayout();
				VL_ADD_USERS.setHeight100();
				VL_ADD_USERS.setWidth100();
				VL_ADD_USERS.setMembersMargin(2);

				HL_USERS.setHeight("*");
				HL_USERS.setWidth100();
				HL_USERS.setMembersMargin(2);

				DF_USERS.setWidth100();
				DF_USERS.setNumCols(2);
				DF_USERS.setWrapItemTitles(false);

				DF_USERS_2.setWidth100();
				DF_USERS_2.setNumCols(2);
				DF_USERS_2.setWrapItemTitles(false);

				DF_USERS1.setWidth100();
				DF_USERS1.setNumCols(2);
				DF_USERS1.setWrapItemTitles(false);

				DF_USERS2.setWidth100();
				DF_USERS2.setNumCols(2);
				DF_USERS2.setWrapItemTitles(false);

				DF_POLICY.setWidth100();
				// DF_POLICY.setNumCols(2);
				DF_POLICY.setWrapItemTitles(false);

				userCategoryCombo.setRequired(true);

				firstNameText.setRequired(true);
				lastNameText.setRequired(true);
				DF_USERS.setItems(userCategoryCombo, departmentCombo, firstNameText, lastNameText);

				userNameText.setRequired(true);
				emailText.setRequired(true);
				phoneNumberText.setRequired(true);
				// statusCheckboxItem.setRequired(true);
				DF_USERS1.setItems(userNameText, emailText, phoneNumberText, statusCheckboxItem);

				passwordText.setRequired(true);
				retypePasswordText.setRequired(true);
				secretQuestionText.setRequired(true);
				secretAnswerText.setRequired(true);
				DF_USERS2.setItems(passwordText, retypePasswordText, secretQuestionText, secretAnswerText);

				for (FormItem FI : DF_USERS.getFields()) {
					FI.setWidth(200);
					FI.setCellHeight(47);
				}
				for (FormItem FI : DF_USERS_2.getFields()) {
					FI.setWidth(200);
					FI.setCellHeight(47);
				}
				for (FormItem FI : DF_USERS1.getFields()) {
					FI.setWidth(200);
					FI.setCellHeight(47);
				}
				for (FormItem FI : DF_USERS2.getFields()) {
					FI.setWidth(200);
					FI.setCellHeight(47);
				}
				for (FormItem FI : DF_POLICY.getFields()) {
					FI.setWidth(200);
					FI.setCellHeight(47);
				}

				HL_USERS_WINDOW_BUTTONS.setWidth100();
				HL_USERS_WINDOW_BUTTONS.setHeight("2%");
				HL_USERS_WINDOW_BUTTONS.setMembersMargin(10);
				HL_USERS_WINDOW_BUTTONS.setAlign(Alignment.CENTER);
				HL_USERS_WINDOW_BUTTONS.setMembers(doneButton, cancelUserButton);

				// User Selected Roles ListGrid
				rolesSelectedListGrid = new ListGrid();
				rolesSelectedListGrid.setWidth("50%");
				rolesSelectedListGrid.setHeight100();

				ListGridField roleID = new ListGridField(UserRoleListGrid.ROLE_ID, UserRoleListGrid.ROLE_ID_DISPLAY);
				roleID.setHidden(true);

				ListGridField roleName = new ListGridField(UserRoleListGrid.ROLE_NAME, UserRoleListGrid.ROLE_NAME_DISPLAY);

				ListGridField roleDescription = new ListGridField(UserRoleListGrid.ROLE_DESCRIPTION, UserRoleListGrid.ROLE_DESCRIPTION_DISPLAY);
				rolesSelectedListGrid.setFields(roleID, roleName, roleDescription);

				// userRolesListGrid.setFields(roleID, roleName,
				// roleDescription);

				userRolesListGrid = new UserRoleListGrid();
				userRolesListGrid.setCanExpandRecords(false);
				userRolesListGrid.changedByField.setHidden(true);
				userRolesListGrid.createdByField.setHidden(true);

				// Handler that sets the Roles Selected To be added to the User
				userRolesListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

					public void onSelectionChanged(SelectionEvent event) {

						rolesSelectedListGrid.setData(userRolesListGrid.getSelection());

					}

				});

				userRolesListGrid.setWidth("50%");
				userRolesListGrid.setHeight100();

				HL_USER_ROLES.setWidth100();
				HL_USER_ROLES.setHeight("50%");
				HL_USER_ROLES.setGroupTitle("Set User Roles");
				HL_USER_ROLES.setIsGroup(true);

				HL_USER_ROLES.setMembers(userRolesListGrid, rolesSelectedListGrid);

				HL_USERS.setMembers(DF_USERS, DF_USERS1, DF_USERS2);
				// VL_ADD_USERS.addMember(headerInitial2);
				VL_ADD_USERS.addMember(HL_USERS);
				VL_ADD_USERS.addMember(HL_USER_ROLES);
				VL_ADD_USERS.addMember(HL_USERS_WINDOW_BUTTONS);

				final Window MyWindow = new Window();
				MyWindow.setAutoCenter(true);
				MyWindow.setWidth("100%");
				MyWindow.setHeight("90%");
				MyWindow.setIsModal(true);
				MyWindow.setTitle("New User ");

				MyWindow.addItem(VL_ADD_USERS);
				MyWindow.setShowModalMask(true);

				MyWindow.addCloseClickHandler(new CloseClickHandler() {

					public void onCloseClick(CloseClientEvent event) {
						// ClearControls();
						MyWindow.clear();
					}
				});

				MyWindow.show();

				// Load All Roles
				if (getUiHandlers() != null) {

					getUiHandlers().onLoadRoleData();

				}

			}

		});

		saveUserButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (userCategoryCombo.getValue() == null) {
					SC.say("Select Category");
					userCategoryCombo.focusInItem();
				} else if (userCategoryCombo.getValue() != null && userCategoryCombo.getValueAsString().contentEquals(UserCatergory.STAFF.toString()) && departmentCombo.getValue() == null) {

					SC.say("Select Department");
					departmentCombo.focusInItem();
				} /*
				 * else if (roleCombo.getValue() == null) {
				 * SC.say("Select Role"); roleCombo.focusInItem(); }
				 */else if (firstNameText.getValue() == null) {
					SC.say("Enter First Name");
					firstNameText.focusInItem();
				} else if (lastNameText.getValue() == null) {
					SC.say("Enter Last Name");
					lastNameText.focusInItem();
				} else if (emailText.getValue() == null) {
					SC.say("Enter Email");
					emailText.focusInItem();
				} else if (userNameText.getValue() == null) {
					SC.say("Enter User Name");
					userNameText.focusInItem();
				} else if (phoneNumberText.getValue() == null) {
					SC.say("Enter Phone Number");
					phoneNumberText.focusInItem();
				} else if (statusCheckboxItem.getValue() == null) {
					SC.say("Set Status of Account");
					statusCheckboxItem.focusInItem();
				} else if (passwordText.getValue() == null) {
					SC.say("Enter Password");
					passwordText.focusInItem();
				} else if (retypePasswordText.getValue() == null) {
					SC.say("Retype Password");
					retypePasswordText.focusInItem();
				} else if (secretQuestionText.getValue() == null) {
					SC.say("Enter Secret Question");
					secretQuestionText.focusInItem();
				} else if (secretAnswerText.getValue() == null) {
					SC.say("Enter Secret Answer");
					secretAnswerText.focusInItem();
				} else if (rolesSelectedListGrid.getSelection() == null) {

					SC.warn("Select Users Roles!!");

				} else {

					User user = new User();
					user.setfName(firstNameText.getValueAsString());
					user.setlName(lastNameText.getValueAsString());
					user.setUsername(userNameText.getValueAsString());
					user.setClearTextPassword(passwordText.getValueAsString());
					user.setEmail(emailText.getValueAsString());
					user.setSecretQuestion(secretQuestionText.getValueAsString());
					user.setSecretAnswer(secretAnswerText.getValueAsString());
					user.setPhoneNumber(phoneNumberText.getValueAsString());

					user.setRoleId(roleCombo.getValueAsString());

					user.setStatus(statusCheckboxItem.getValueAsBoolean());

					if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.STAFF.toString())) {

						user.setUserCatergory(UserCatergory.STAFF);

						Division division = new Division();
						division.setId(departmentCombo.getValueAsString());
						user.setDivision(division);

					} else if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.BOARDMEMBER.toString())) {

						user.setUserCatergory(UserCatergory.BOARDMEMBER);
					} else if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.GUEST.toString())) {

						user.setUserCatergory(UserCatergory.GUEST);
					}

					// Set Users Roles

					List<Role> userRoles = new ArrayList<Role>();

					if (rolesSelectedListGrid.getRecords().length > 0) {

						for (Record record : rolesSelectedListGrid.getRecords()) {

							Role role = new Role();
							role.setId(record.getAttribute(UserRoleListGrid.ROLE_ID));

							userRoles.add(role);

						}
					} else {

					}

					user.setRolesForUser(userRoles);

					// End of Setting User Roles

					if (getUiHandlers() != null) {
						getUiHandlers().onSaveUserButtonClicked(user);
					}

				}
				// ClearControlsUsers();
			}

		});

		editUserButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (usersListGrid.getSelectedRecord() != null) {
					setUserActionMode("EDIT");

					VL_ADD_USERS = new VLayout();
					VL_ADD_USERS.setHeight100();
					VL_ADD_USERS.setWidth100();
					VL_ADD_USERS.setMembersMargin(2);

					HL_USERS.setHeight("*");
					HL_USERS.setWidth100();
					HL_USERS.setMembersMargin(2);

					DF_USERS.setWidth100();
					DF_USERS.setNumCols(2);
					DF_USERS.setWrapItemTitles(false);

					DF_USERS_2.setWidth100();
					DF_USERS_2.setNumCols(2);
					DF_USERS_2.setWrapItemTitles(false);

					DF_USERS1.setWidth100();
					DF_USERS1.setNumCols(2);
					DF_USERS1.setWrapItemTitles(false);

					DF_USERS2.setWidth100();
					DF_USERS2.setNumCols(2);
					DF_USERS2.setWrapItemTitles(false);

					DF_POLICY.setWidth100();
					// DF_POLICY.setNumCols(2);
					DF_POLICY.setWrapItemTitles(false);

					userCategoryCombo.setRequired(true);

					firstNameText.setRequired(true);
					lastNameText.setRequired(true);
					DF_USERS.setItems(userCategoryCombo, departmentCombo, firstNameText, lastNameText);

					userNameText.setRequired(true);
					emailText.setRequired(true);
					phoneNumberText.setRequired(true);
					// statusCheckboxItem.setRequired(true);
					DF_USERS1.setItems(userNameText, emailText, phoneNumberText, statusCheckboxItem);

					for (FormItem FI : DF_USERS.getFields()) {
						FI.setWidth(200);
						FI.setCellHeight(47);
					}
					for (FormItem FI : DF_USERS_2.getFields()) {
						FI.setWidth(200);
						FI.setCellHeight(47);
					}
					for (FormItem FI : DF_USERS1.getFields()) {
						FI.setWidth(200);
						FI.setCellHeight(47);
					}

					for (FormItem FI : DF_POLICY.getFields()) {
						FI.setWidth(200);
						FI.setCellHeight(47);
					}

					HL_USERS_WINDOW_BUTTONS.setWidth100();
					HL_USERS_WINDOW_BUTTONS.setHeight("2%");
					HL_USERS_WINDOW_BUTTONS.setMembersMargin(10);
					HL_USERS_WINDOW_BUTTONS.setAlign(Alignment.CENTER);
					HL_USERS_WINDOW_BUTTONS.setMembers(doneButton, cancelUserButton);

					HL_USERS.setMembers(DF_USERS, DF_USERS1);
					// VL_ADD_USERS.addMember(headerInitial2);
					VL_ADD_USERS.addMember(HL_USERS);
					VL_ADD_USERS.addMember(HL_USERS_WINDOW_BUTTONS);

					final Window MyWindow = new Window();
					MyWindow.setAutoCenter(true);
					MyWindow.setWidth("50%");
					MyWindow.setHeight("50%");
					MyWindow.setIsModal(true);
					MyWindow.setTitle("Edit User ");

					MyWindow.addItem(VL_ADD_USERS);
					MyWindow.setShowModalMask(true);

					MyWindow.addCloseClickHandler(new CloseClickHandler() {

						public void onCloseClick(CloseClientEvent event) {
							// ClearControls();
							MyWindow.clear();
						}
					});

					MyWindow.show();

					// Load All Roles
					if (getUiHandlers() != null) {

						getUiHandlers().onLoadRoleData();

					}

					// Set The Field Values
					departmentCombo.setValue(usersListGrid.getSelectedRecord().getAttributeAsString("departmentID"));
					firstNameText.setValue(usersListGrid.getSelectedRecord().getAttributeAsString("userFirstName"));
					lastNameText.setValue(usersListGrid.getSelectedRecord().getAttributeAsString("userLastName"));
					userNameText.setValue(usersListGrid.getSelectedRecord().getAttributeAsString("userName"));
					emailText.setValue(usersListGrid.getSelectedRecord().getAttributeAsString("userEmail"));
					phoneNumberText.setValue(usersListGrid.getSelectedRecord().getAttributeAsString("userPhoneNumber"));

					if (usersListGrid.getSelectedRecord().getAttributeAsString("userStatus").contentEquals("Active")) {
						statusCheckboxItem.setValue(true);
					} else if (usersListGrid.getSelectedRecord().getAttributeAsString("userStatus").contentEquals("De-Activated")) {
						statusCheckboxItem.setValue(false);
					}

				} else {

					SC.warn(" Select User Account To Edit!!! ");

				}

			}

		});

		doneButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUserActionMode().contentEquals("SAVE")) {

					if (DF_USERS.validate()) {

						if (userCategoryCombo.getValue() != null && userCategoryCombo.getValueAsString().contentEquals(UserCatergory.STAFF.toString()) && departmentCombo.getValue() == null) {

							SC.say("Select Department");
							departmentCombo.focusInItem();
						}
						if (DF_USERS1.validate() && DF_USERS2.validate()) {

							SC.ask("Create New User Account", " Do you want to continue and create this User Account?", new BooleanCallback() {

								public void execute(Boolean value) {

									if (value != null && value) {

										User user = new User();
										user.setfName(firstNameText.getValueAsString());
										user.setlName(lastNameText.getValueAsString());
										user.setUsername(userNameText.getValueAsString());
										user.setClearTextPassword(passwordText.getValueAsString());
										user.setEmail(emailText.getValueAsString());
										user.setSecretQuestion(secretQuestionText.getValueAsString());
										user.setSecretAnswer(secretAnswerText.getValueAsString());
										user.setPhoneNumber(phoneNumberText.getValueAsString());

										user.setRoleId(roleCombo.getValueAsString());

										user.setStatus(statusCheckboxItem.getValueAsBoolean());

										if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.STAFF.toString())) {

											user.setUserCatergory(UserCatergory.STAFF);

											Division division = new Division();
											division.setId(departmentCombo.getValueAsString());
											user.setDivision(division);

										} else if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.BOARDMEMBER.toString())) {

											user.setUserCatergory(UserCatergory.BOARDMEMBER);
										} else if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.GUEST.toString())) {

											user.setUserCatergory(UserCatergory.GUEST);
										}

										// Set Users Roles

										List<Role> userRoles = new ArrayList<Role>();

										if (rolesSelectedListGrid.getRecords().length > 0) {

											for (Record record : rolesSelectedListGrid.getRecords()) {

												Role role = new Role();
												role.setId(record.getAttribute(UserRoleListGrid.ROLE_ID));

												userRoles.add(role);

											}
										} else {

										}

										user.setRolesForUser(userRoles);

										// End of Setting User Roles

										if (getUiHandlers() != null) {
											getUiHandlers().onSaveUserButtonClicked(user);
										}
									} else {

									}

								}

							});

						} else {

						}

					}

				} else if (getUserActionMode().contentEquals("EDIT")) {

					if (DF_USERS.validate()) {

						if (userCategoryCombo.getValue() != null && userCategoryCombo.getValueAsString().contentEquals(UserCatergory.STAFF.toString()) && departmentCombo.getValue() == null) {

							SC.say("Select Department");
							departmentCombo.focusInItem();
						}
						if (DF_USERS1.validate()) {

							SC.ask("Edit User Account", " Do you want to continue and EDIT this User Account?", new BooleanCallback() {

								public void execute(Boolean value) {

									if (value != null && value) {

										User user = new User();
										user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

										user.setfName(firstNameText.getValueAsString());
										user.setlName(lastNameText.getValueAsString());
										user.setUsername(userNameText.getValueAsString().trim());
										user.setClearTextPassword(passwordText.getValueAsString());
										user.setEmail(emailText.getValueAsString().trim());
										user.setSecretQuestion(secretQuestionText.getValueAsString());
										user.setSecretAnswer(secretAnswerText.getValueAsString());
										user.setPhoneNumber(phoneNumberText.getValueAsString());
										user.setStatus(statusCheckboxItem.getValueAsBoolean());

										if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.STAFF.toString())) {

											user.setUserCatergory(UserCatergory.STAFF);

											Division division = new Division();
											division.setId(departmentCombo.getValueAsString());
											user.setDivision(division);

										} else if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.BOARDMEMBER.toString())) {

											user.setUserCatergory(UserCatergory.BOARDMEMBER);
										} else if (userCategoryCombo.getValueAsString().contentEquals(UserCatergory.GUEST.toString())) {

											user.setUserCatergory(UserCatergory.GUEST);
										}

										if (getUiHandlers() != null) {
											getUiHandlers().onEditUserButtonClicked(user);
										}
									} else {

									}

								}

							});

						} else {

						}

					}
				} else {

				}

			}

		});

		saveRoleButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (roleNameText.getValue() == null) {

					SC.say("Enter Role");
					roleNameText.focusInItem();
				} else if (roleDescriptionText.getValue() == null) {

					SC.say("Enter Role Description");
					roleDescriptionText.focusInItem();
				} else if (permissionSelectedListGrid.getRecords().length == 0) {

					SC.say("You Must Set Role Permissions Before Attempting To Save Role");
					tabSet.selectTab("permissions");
					permissionSelectedListGrid.focus();
				} else {

					List<Permission> permissions = new ArrayList<Permission>();

					for (ListGridRecord record : permissionSelectedListGrid.getRecords()) {

						Permission permission = new Permission();
						permission.setId(record.getAttribute("permissionID"));

						permissions.add(permission);

					}

					Role role = new Role();

					role.setName(roleNameText.getValueAsString());
					role.setDescription(roleDescriptionText.getValueAsString());
					// role.setPermissions(permissions);
					role.setPermissionList(permissions);

					if (getUiHandlers() != null) {
						getUiHandlers().onSaveRoleButtonClicked(role);
					}
				}
				// ClearControlsRoles();
			}

		});

		editRoleButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (rolesListGrid.getSelectedRecord() == null) {

					SC.say("Select Role Record To Edit");
					rolesListGrid.focus();

				} else if (roleNameText.getValue() == null) {

					SC.say("Enter Role");
					roleNameText.focusInItem();
				} else if (roleDescriptionText.getValue() == null) {

					SC.say("Enter Role Description");
					roleDescriptionText.focusInItem();
				} else if (permissionSelectedListGrid.getRecords() == null) {

					SC.say("Re-Assign Permissions To Role");
					permissionSelectedListGrid.focus();
				} else {

					final Role role = new Role();

					role.setId(rolesListGrid.getSelectedRecord().getAttribute("roleID"));
					role.setName(roleNameText.getValueAsString());
					role.setDescription(roleDescriptionText.getValueAsString());

					List<Permission> permissions = new ArrayList<Permission>();

					for (ListGridRecord record : permissionSelectedListGrid.getRecords()) {

						Permission permission = new Permission();
						permission.setId(record.getAttribute("permissionID"));

						permissions.add(permission);

					}

					role.setPermissionList(permissions);

					SC.ask("Edit Role", "Do you really want to Edit this Role", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {
									getUiHandlers().onEditRoleButtonClicked(role);
								}
							} else {

							}

						}

					});

				}

				tabSet.selectTab("viewusers");
				HL_USERS.setMembers(DF_USERS, DF_USERS1, DF_USERS2);
			}

		});

		deleteRoleButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (rolesListGrid.getRecords().length == 0) {

					SC.say("Select Role Record to Delete");
					rolesListGrid.focus();
				} else {

					final Role role = new Role();

					role.setId(rolesListGrid.getSelectedRecord().getAttribute("roleID"));

					SC.ask("Delete Role", "Do you Really want to delete this Role ", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {
									getUiHandlers().onDeleteRoleButtonClicked(role);
								}

							} else {

							}

						}

					});

				}
			}

		});

		cancelRoleButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				ClearRoleControls();
			}

		});

		permissionDoneButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				tabSet.selectTab("roles");

			}

		});

		setPermissionsButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				tabSet.selectTab("permissions");

			}

		});

		// Filter Permissions By Role
		permissionFilterByUser.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {

			}

		});

		this.rolesListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				// roleNameText,roleDescriptionText

				try {
					roleNameText.setValue(event.getRecord().getAttributeAsString("roleName"));
					roleDescriptionText.setValue(event.getRecord().getAttributeAsString("roleDescription"));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
		rolesListGrid.addRecordExpandHandler(new RecordExpandHandler() {

			public void onRecordExpand(RecordExpandEvent event) {

				Role role = new Role();
				role.setId(event.getRecord().getAttribute("roleID"));

				if (getUiHandlers() != null) {

					getUiHandlers().onLoadPermissionsRole(role);
				}

			}

		});

		usersListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

			}

		});
		// Roll Over Control Handlers

		usersListGrid.getEditImg().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				departmentCombo.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("departmentID"));
				roleCombo.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("roleID"));
				firstNameText.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("userFirstName"));
				lastNameText.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("userLastName"));
				userNameText.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("userName"));
				emailText.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("userEmail"));
				phoneNumberText.setValue(usersListGrid.getRollOverRecord().getAttributeAsString("userPhoneNumber"));

				if (usersListGrid.getRollOverRecord().getAttributeAsString("userStatus").contentEquals("Active")) {
					statusCheckboxItem.setValue(true);
				} else if (usersListGrid.getRollOverRecord().getAttributeAsString("userStatus").contentEquals("De-Activated")) {
					statusCheckboxItem.setValue(false);
				}
				/*
				 * setUserID(usersListGrid.getRollOverRecord()
				 * .getAttributeAsString("userID"));
				 */

				HL_USERS.setMembers(DF_USERS, DF_USERS1);
				tabSet.selectTab("addusers");
				saveUserButton.setDisabled(true);
			}

		});


		lengthValue.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {

				if (lengthValue.getValueAsBoolean() == true) {

					lowerThresholdText.setDisabled(false);
					upperThresholdText.setDisabled(false);
				} else if (lengthValue.getValueAsBoolean() == true) {

					lowerThresholdText.setDisabled(true);
					upperThresholdText.setDisabled(true);
				}

			}

		});

		loadUserCategoryCombo();

		newRolesListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

			public void onSelectionChanged(SelectionEvent event) {

				newRolesSelectedListGrid.setData(newRolesListGrid.getSelection());

			}

		});

		usersListGrid.addRecordExpandHandler(new RecordExpandHandler() {

			public void onRecordExpand(RecordExpandEvent event) {

				User user = new User();
				user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

				if (getUiHandlers() != null) {

					getUiHandlers().onLoadRoleUser(user);
				}

			}

		});

		// Remove Role Handlers
		usersListGrid.getRemoveButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				final User user = new User();
				user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

				// Set Users Roles

				final List<Role> userRoles = new ArrayList<Role>();

				if (usersListGrid.getRoleListGrid().getSelection().length > 0) {

					System.out.println("Number Of Roles Selected On Grid= " + usersListGrid.getRoleListGrid().getSelection().length);

					for (Record record : usersListGrid.getRoleListGrid().getSelection()) {

						Role role = new Role();
						role.setId(record.getAttribute(UserRoleListGrid.ROLE_ID));

						userRoles.add(role);

					}

					SC.ask("Remove Roles To User", "Do you want to continue and Remove This ROLE(ROLES) from User", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {

									getUiHandlers().onRemoveUserRolesButtonClicked(user, userRoles);
								}
							} else {

							}

						}

					});
				} else {


				}

			}

		});

		listGrid.getAddRoleButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				final Window MyWindow = new Window();
				MyWindow.setAutoCenter(true);
				MyWindow.setWidth("70%");
				MyWindow.setHeight("60%");
				MyWindow.setIsModal(true);
				VLayout VL_NEW_ROLES = new VLayout();
				VL_NEW_ROLES.setWidth100();
				VL_NEW_ROLES.setHeight100();

				HL_NEW_ROLES_BUTTON.setWidth100();
				HL_NEW_ROLES_BUTTON.setHeight("2%");
				HL_NEW_ROLES_BUTTON.setAlign(Alignment.CENTER);
				HL_NEW_ROLES_BUTTON.setMembers(saveNewUserRoles, clearNewUserRoleButton);

				HLayout HL_NEW_ROLES = new HLayout();
				HL_NEW_ROLES.setWidth100();
				HL_NEW_ROLES.setHeight("98%");

				newRolesListGrid.setWidth("50%");
				newRolesListGrid.setHeight100();
				newRolesListGrid.setCanExpandRecords(false);
				newRolesListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);

				newRolesSelectedListGrid = new ListGrid();

				ListGridField roleID = new ListGridField(UserRoleListGrid.ROLE_ID, UserRoleListGrid.ROLE_ID_DISPLAY);
				roleID.setHidden(true);

				ListGridField roleName = new ListGridField(UserRoleListGrid.ROLE_NAME, UserRoleListGrid.ROLE_NAME_DISPLAY);

				ListGridField roleDescription = new ListGridField(UserRoleListGrid.ROLE_DESCRIPTION, UserRoleListGrid.ROLE_DESCRIPTION_DISPLAY);
				newRolesSelectedListGrid.setFields(roleID, roleName, roleDescription);

				newRolesSelectedListGrid.setWidth("50%");
				newRolesSelectedListGrid.setHeight100();

				HL_NEW_ROLES.setMembers(newRolesListGrid, newRolesSelectedListGrid);
				VL_NEW_ROLES.setMembers(HL_NEW_ROLES, HL_NEW_ROLES_BUTTON);

				MyWindow.addItem(VL_NEW_ROLES);
				MyWindow.setShowModalMask(true);
				MyWindow.setTitle("Add Roles to Users");
				MyWindow.addCloseClickHandler(new CloseClickHandler() {

					public void onCloseClick(CloseClientEvent event) {

						MyWindow.clear();
					}

				});

				User user = new User();
				user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

				if (getUiHandlers() != null) {

					getUiHandlers().onLoadNewRoles(user);
				}
				MyWindow.show();

			}

		});

		listGrid.getRemoveButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

			}

		});

		saveNewUserRoles.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				final User user = new User();
				user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

				// Set Users Roles

				final List<Role> userRoles = new ArrayList<Role>();

				if (newRolesSelectedListGrid.getRecords().length > 0) {

					for (Record record : newRolesSelectedListGrid.getRecords()) {

						Role role = new Role();
						role.setId(record.getAttribute(UserRoleListGrid.ROLE_ID));

						userRoles.add(role);

					}

					SC.ask("Add Roles To User", "Do you want to continue and add new ROLE(ROLES) to this User", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {

									getUiHandlers().onAddUserRolesButtonClicked(user, userRoles);
								}
							} else {

							}

						}

					});

				} else {


				}

			}

		});

		clearNewUserRoleButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

			}

		});
		
		registerSignButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				final SigNetRegistrationWindow popup = new SigNetRegistrationWindow();

				popup.getNextButton().addClickHandler(
						new com.google.gwt.event.dom.client.ClickHandler() {

							public void onClick(

							com.google.gwt.event.dom.client.ClickEvent event) {

								if (usersListGrid.getSelectedRecord() == null) {

									SC.warn("Select User To Create Signature");
									usersListGrid.focus();

								}
								if (popup.isSignatureCountFull()) {

								} else {
									String imageurl = popup.getCanvas()
											.toDataUrl("image/png");

									popup.setSignatureCount(popup
											.getSignatureCount() + 1);

									popup.getImageList().add(imageurl);
									if (popup.getSignatureCount() > 10) {
										// do sthg with image list

										popup.getInstruction()
												.setVisible(false);
										popup.getCountLabel()
												.setText(
														"All your signatures have been captured");
										popup.getCountLabel()
												.setHorizontalAlignment(
														HorizontalPanel.ALIGN_CENTER);
										popup.getNextButton().setVisible(false);
										popup.getClearButton()
												.setVisible(false);
										popup.getCanvas().setVisible(false);
										popup.setSignatureCountFull(true);
										popup.setWidth(400);
										popup.setHeight(100);

										// Print Base64 Strings

										int numb = 0;
										for (String imageString : popup
												.getImageList()) {

											numb++;

										}
										
										User user = new User();
										user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

										if (getUiHandlers() != null) {

											getUiHandlers().onCreateSignature(
													popup.getImageList(),user);
										}

									} else {
										popup.getCountLabel().setText(
												popup.getSignatureCount()
														+ " of 10");
									}
									popup.getContext()
											.clearRect(
													0,
													0,
													popup.getCanvas()
															.getOffsetWidth(),
													popup.getCanvas()
															.getOffsetHeight());
								}

							}

						});
				popup.setAutoCenter(true);
				popup.show();

			}

		});
		
		// Load Users By Departments
		departmentLoadCombo.addChangedHandler(new ChangedHandler(){

			public void onChanged(ChangedEvent event) {
				
				Department department = new Department();
				department.setId(departmentLoadCombo.getValueAsString());
				
				if( getUiHandlers() != null){
					
					getUiHandlers().onLoadUsersByDepartment(department);
				}
				
			}
			
		});

	}

	protected void bindCustomUiHandlers() {

		initToolBar();
		initStatusBar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.View#asWidget()
	 */
	public Widget asWidget() {

		return mainPanel;
	}

	protected void initToolBar() {

		toolBarUsers.addButton(ToolBar.ACTIVATE_BUTTON, Monitoring.getConstants().activateButton(), Monitoring.getConstants().activateButtonTooltip(), new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (usersListGrid.getSelectedRecord() == null) {
					SC.say("Select User Account To Activate");
					usersListGrid.focus();
				} else {
					User user = new User();
					user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

					if (getUiHandlers() != null) {
						getUiHandlers().onActivateUserButtonClicked(user);
					}
					saveUserButton.setDisabled(false);
				}
			}

		});
		toolBarUsers.addSeparator();
		toolBarUsers.addButton(ToolBar.DEACTIVATE_BUTTON, Monitoring.getConstants().deActivateButton(), Monitoring.getConstants().deActivateButtonTooltip(), new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (usersListGrid.getSelectedRecord() == null) {
					SC.say("Select User Account To De-Activate");
					usersListGrid.focus();
				} else {

					User user = new User();
					user.setId(usersListGrid.getSelectedRecord().getAttribute(UsersContextAreaListGrid.USER_ID));

					if (getUiHandlers() != null) {
						getUiHandlers().onDeActivateUserButtonClicked(user);
					}
				}

			}

		});
		toolBarUsers.addSeparator();
		toolBarUsers.addButton(ToolBar.EXPORT_BUTTON, Monitoring.getConstants().exportButtonTooltip(), null);
		toolBarUsers.addSeparator();
		toolBarUsers.addButton(ToolBar.REPORTS_BUTTON, Monitoring.getConstants().reportsButtonTooltip(), new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {

					getUiHandlers().onReportButtonClicked();
				}

			}

		});
		toolBarUsers.addSeparator();

		toolBarUsers.addButton(ToolBar.REFRESH_BUTTON, Monitoring.getConstants().refreshButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onLoadUserGridData();
				}
			}
		});
	}

	protected void initStatusBar() {

		// "0 of 50 selected"
		// statusBar.getSelectedLabel().setContents(Serendipity.getConstants().selectedLabel());

		getStatusBar().getResultSetFirstButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {
					getUiHandlers().onResultSetFirstButtonClicked();
				}

			}
		});

		getStatusBar().getResultSetPreviousButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {

					getUiHandlers().onResultSetPreviousButtonClicked();
				}

			}
		});

		// "Page 1"
		// statusBar.getPageNumberLabel().setContents(Serendipity.getConstants().pageNumberLabel());

		getStatusBar().getResultSetNextButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {
					getUiHandlers().onResultSetNextButtonClicked();
				}

			}
		});
	}

	public void ClearRoleControls() {

		DF_ROLES.clearValues();

	}

	public void ClearPolicyControls() {

		this.DF_POLICY.clearValues();

	}

	public void setServerResponse(String text) {

		SC.say("" + text);
	}

	public void loadAllPermisiions(List<Permission> permissions) {

		ListGridRecord[] list = new ListGridRecord[permissions.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Permission permission : permissions) {
			record = new ListGridRecord();
			record.setAttribute("icon", "permissions");
			record.setAttribute("permissionID", permission.getId());
			record.setAttribute("permissionName", permission.getName());
			record.setAttribute("permissionDescription", permission.getDescription());
			record.setAttribute("emptyField", "");

			list[listCount] = record;
			listCount++;

		}
		if (list.length > 0) {
			this.permissionsListGrid.setData(list);
		} else {

		}

	}

	public void loadAllRoles(List<Role> roles) {

		ListGridRecord[] list = new ListGridRecord[roles.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Role role : roles) {
			record = new ListGridRecord();
			record.setAttribute("icon", "users");
			record.setAttribute("roleID", role.getId());
			record.setAttribute("roleName", role.getName());
			record.setAttribute("roleDescription", role.getDescription());
			record.setAttribute("emptyField", "");

			list[listCount] = record;
			listCount++;

		}
		if (list.length > 0) {
			this.rolesListGrid.setData(list);
			if(this.userRolesListGrid!=null){
				this.userRolesListGrid.setData(list);
			}
			

		} else {

		}

	}

	public void loadAllUsers(List<User> users) {

		try {
			ListGridRecord[] list = new ListGridRecord[users.size()];
			int listCount = 0;
			ListGridRecord record;
			for (User user : users) {
				// System.out.println("User: "+user.getFullName()+": "+user.getRole());

				record = new ListGridRecord();
				record.setAttribute("icon", "users");
				record.setAttribute("userName", user.getUsername());
				record.setAttribute("userFirstName", user.getfName());
				record.setAttribute("userLastName", user.getlName());
				record.setAttribute("userEmail", user.getEmail());
				record.setAttribute("roleID", user.getRoleId());
				record.setAttribute("roleName", user.getRoleName());// userPhoneNumber
				record.setAttribute("userPhoneNumber", user.getPhoneNumber());
				record.setAttribute("emptyField", "");
				record.setAttribute(UsersContextAreaListGrid.USER_ID, user.getId());

				if (user.getUserCatergory().compareTo(UserCatergory.STAFF) == 0) {

					record.setAttribute("departmentName", user.getDepartment().getDepartmentName());
					record.setAttribute("departmentID", user.getDepartment().getId());
				} else {

					record.setAttribute("departmentName", user.getUserCatergory().toString());
					// record.setAttribute("userCategory",
					// user.getUserCatergory().toString());

				}
				if (user.getStatus() == true) {

					// System.out.println("Status: "+user.getStatus());
					record.setAttribute("userStatus", "Active");
				} else if (user.getStatus() == false) {
					record.setAttribute("userStatus", "Inactive");
				}

				if (user.getChangedBy() != null) {

					record.setAttribute("changedBy", user.getChangedBy().getFullName());

				} else {

				}

				if (user.getCreatedBy() != null) {

					record.setAttribute("createdBy", user.getCreatedBy().getFullName());
				} else {

				}

				list[listCount] = record;
				listCount++;

			}
			if (list.length > 0) {
				this.usersListGrid.setData(list);
			} else {

			}
		} catch (Exception e) {
			SC.say("Exception Occured" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadUserCategoryCombo() {

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

		valueMap.put(UserCatergory.STAFF.toString(), UserCatergory.STAFF.toString());
		valueMap.put(UserCatergory.GUEST.toString(), UserCatergory.GUEST.toString());
		valueMap.put(UserCatergory.BOARDMEMBER.toString(), UserCatergory.BOARDMEMBER.toString());

		this.userCategoryCombo.setValueMap(valueMap);

	}

	// Used To Load the Roles to grid used to set user roles when creating a new
	// User
	public void loadRoleCombo(List<Role> roles) {

		/*
		 * LinkedHashMap<String, String> valueMap = new LinkedHashMap<String,
		 * String>();
		 * 
		 * for (Role role : roles) { valueMap.put(role.getId(), role.getName());
		 * }
		 * 
		 * if (!valueMap.isEmpty()) { this.roleCombo.setValueMap(valueMap); }
		 * else {
		 * 
		 * }
		 */

	}

	public void loadDivisionCombo(List<Division> divisions) {

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

		for (Division division : divisions) {
			valueMap.put(division.getId(), division.getDivisionName());
		}

		if (!valueMap.isEmpty()) {
			this.departmentCombo.setValueMap(valueMap);
		} else {

		}
	}

	public void setServerResponseError(String text) {

		SC.warn("" + text);

	}

	public void ClearUserControls() {

		DF_USERS.clearValues();
		DF_USERS1.clearValues();
		DF_USERS2.clearValues();

	}

	public void loadPermissionsByRole(List<Permission> permissions) {

		ListGridRecord[] list = new ListGridRecord[permissions.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Permission permission : permissions) {
			record = new ListGridRecord();
			record.setAttribute("icon", "permissions");
			record.setAttribute("permissionID", permission.getId());
			record.setAttribute("permissionName", permission.getName());
			record.setAttribute("permissionDescription", permission.getDescription());
			record.setAttribute("emptyField", "");

			list[listCount] = record;
			listCount++;

		}
		if (list.length > 0) {
			this.rolesListGrid.getPermissionsGrid().setData(list);
		} else {

		}

	}

	public StatusBar getStatusBar() {

		return statusBar;
	}

	public void loadUserRoles(List<Role> roles) {

		ListGridRecord[] list = new ListGridRecord[roles.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Role role : roles) {
			record = new ListGridRecord();
			record.setAttribute("icon", "users");
			record.setAttribute("roleID", role.getId());
			record.setAttribute("roleName", role.getName());
			record.setAttribute("roleDescription", role.getDescription());
			record.setAttribute("emptyField", "");

			list[listCount] = record;
			listCount++;

		}
		if (list.length > 0) {

			this.usersListGrid.getRoleListGrid().setData(list);

		} else {

		}
	}

	public void loadNewRoles(List<Role> roles) {

		ListGridRecord[] list = new ListGridRecord[roles.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Role role : roles) {

			record = new ListGridRecord();
			record.setAttribute("icon", "users");
			record.setAttribute("roleID", role.getId());
			record.setAttribute("roleName", role.getName());
			record.setAttribute("roleDescription", role.getDescription());
			record.setAttribute("emptyField", "");

			list[listCount] = record;
			listCount++;

		}
		if (list.length > 0) {

			newRolesListGrid.setData(list);

		} else {

		}
	}

	/**
	 * @return the userActionMode
	 */
	public String getUserActionMode() {
		return userActionMode;
	}

	/**
	 * @param userActionMode
	 *            the userActionMode to set
	 */
	public void setUserActionMode(String userActionMode) {
		this.userActionMode = userActionMode;
	}

	public TextItem getSearchText() {
		return searchText;
	}

	public SearchButton getSearchButton() {
		return searchButton;
	}

	public void loadDepartmentCombo(List<Department> departments) {
	
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

		for (Department department : departments) {
			valueMap.put(department.getId(), department.getDepartmentName());
		}

		if (!valueMap.isEmpty()) {
			this.departmentLoadCombo.setValueMap(valueMap);
		} else {

		}
	}
	

}
