
package com.planetsystems.monitoring.client.utils;


public interface MonitoringConstants extends com.google.gwt.i18n.client.Constants {
	
	// Menus
	@DefaultStringValue("<u>N</u>ew Activity")
	String NewActivityMenuName();

	@DefaultStringValue("<u>F</u>ile")
	String FileMenuName();

	@DefaultStringValue("<u>S</u>ecurity")
	String SecurityMenuName();

	@DefaultStringValue("New, Open, Save, Import & Export, Options, Exit")
	String FileMenuItemNames();

	@DefaultStringValue("Change Password, Log Out")
	String SecurityMenuItemNames();

	@DefaultStringValue("Task, Fax, Phone Call, Email, Letter, Appointment")
	String NewActivityMenuItemNames();

	@DefaultStringValue("New Re<u>c</u>ord")
	String NewRecordMenuName();

	@DefaultStringValue("<u>G</u>o To")
	String GoToMenuName();


	@DefaultStringValue("Settings")
	String SettingsMenuItemName();

	@DefaultStringValue("Administration, Templates, Data Management")
	String SettingsMenuItemNames();
	
	@DefaultStringValue("<u>T</u>ools")
	String ToolsMenuName();

	@DefaultStringValue("My Profile, System Settings, Diagnostics, Database Admin, Data Integration, Template Designs, eSignature Setting, Add-Ins, Backup & Recovery")
	String ToolsMenuItemNames();

	@DefaultStringValue("<u>H</u>elp")
	String HelpMenuName();

	@DefaultStringValue("Definitions, Process Maps, Module Help, eLearning & PPDA links, About Procnet")
	String HelpMenuItemNames();

	@DefaultStringValue("<u>A</u>ctions")
	String ActionsMenuName();

	@DefaultStringValue("Notifications,Previous Discussions")
	String ActionsMenuItemNames();

	@DefaultStringValue("<u>V</u>iews")
	String ViewsMenuName();

	@DefaultStringValue("Show Sections, Hide Sections, Item Status(Action File), Documents & Mailings, Starndard Reports, Custom Reports, Pivot WorkSheets, Search")
	String ViewsMenuItemNames();

	@DefaultStringValue("<u>M</u>odules")
	String ModulesMenuName();

	@DefaultStringValue("New Project, Discussions")
	String ModulesMenuItemNames();

	// Navigation Pane Header

	@DefaultStringValue("Sections")
	String Workplace();

	@DefaultStringValue("Activities")
	String Activities();

	// Navigation Pane

	@DefaultStringValue("Details")
	String AccountDetailsStackSectionName();

	@DefaultStringValue("Projects")
	String ProjectsStackSectionName();

	@DefaultStringValue("Project Teams")
	String ProjectTeamsStackSectionName();

	@DefaultStringValue("Team Interation")
	String TeamInteractionStackSectionName();

	@DefaultStringValue("Stakeholders")
	String StackHoldersStackSectionName();


	@DefaultStringValue("Settings")
	String SettingsStackSectionName();

	// Toolbar

	@DefaultStringValue("New")
	String NewButton();

	// StatusBar

	@DefaultStringValue("1 of 50 selected")
	String SelectedLabel();

	@DefaultStringValue("Page 1")
	String PageNumberLabel();

	// Form Toolbar

	@DefaultStringValue("Save and Close")
	String SaveAndCloseButton();

	@DefaultStringValue("Help")
	String HelpButton();

	// Form Tabs

	@DefaultStringValue("General")
	String GeneralTab();

	@DefaultStringValue("Administration")
	String AdministrationTab();

	@DefaultStringValue("Notes")
	String NotesTab();

	// ToolBar

	@DefaultStringValue("New")
	String newButton();

	@DefaultStringValue("New")
	String newButtonTooltip();

	@DefaultStringValue("Run Workflow...")
	String workflowButton();

	@DefaultStringValue("Run Workflow")
	String workflowButtonTooltip();

	@DefaultStringValue("Reports")
	String reportsButton();

	@DefaultStringValue("Reports")
	String reportsButtonTooltip();

	@DefaultStringValue("Print Preview")
	String printPreviewButtonTooltip();

	@DefaultStringValue("Export")
	String exportButtonTooltip();

	@DefaultStringValue("Mail Merge")
	String mailMergeButtonTooltip();

	@DefaultStringValue("Assign")
	String assignButtonTooltip();

	@DefaultStringValue("Delete")
	String deleteButtonTooltip();

	@DefaultStringValue("Email")
	String emailButtonTooltip();

	@DefaultStringValue("Attach")
	String attachButtonTooltip();

	@DefaultStringValue("Refresh")
	String refreshButtonTooltip();

	@DefaultStringValue("Load")
	String loadButtonTooltip();

	@DefaultStringValue("Upload")
	String uploadButtonTooltip();

	@DefaultStringValue("Edit")
	String editButton();

	@DefaultStringValue("Edit")
	String editButtonToolTip();

	// Form ToolBar

	@DefaultStringValue("Save and Close")
	String saveAndCloseButton();

	@DefaultStringValue("Help")
	String helpButton();

	@DefaultStringValue("Save")
	String saveButtonTooltip();

	@DefaultStringValue("Save and Close")
	String saveAndCloseButtonTooltip();

	@DefaultStringValue("Help")
	String helpButtonTooltip();

	// Tasks
	@DefaultStringValue("Mark As Completed")
	String markCompleteButton();

	@DefaultStringValue("Mark Task as Completed")
	String markCompleteButtonTooltip();

	@DefaultStringValue("Claim Task")
	String claimTaskButton();

	@DefaultStringValue("Claim Task")
	String claimTaskButtonTooltip();

	@DefaultStringValue("Personal Task")
	String personalTaskButton();

	@DefaultStringValue("Personal Task")
	String personalTaskButtonTooltip();

	@DefaultStringValue("Group Task")
	String groupTaskButton();

	@DefaultStringValue("Group Task")
	String groupTaskButtonTooltip();

	@DefaultStringValue("Delegate Task")
	String delegateTaskButton();

	@DefaultStringValue("Delegate Task")
	String delegateTaskButtonTooltip();

	@DefaultStringValue("Task WorkFlow")
	String completeTaskButton();

	@DefaultStringValue("Complete Task")
	String completeTaskButtonTooltip();
	
	@DefaultStringValue("Complete Task")
	String completeBidTaskButton();

	@DefaultStringValue("Activate")
	String activateButton();

	@DefaultStringValue("Set Active")
	String activateButtonTooltip();

	@DefaultStringValue("DeActivate")
	String deActivateButton();

	@DefaultStringValue("Set Inactive")
	String deActivateButtonTooltip();
	/*
	 * @DefaultStringValue("LINEITEM") String lineItemProduct();
	 * 
	 * @DefaultStringValue("CPV") String commonProductCatalogue();
	 */
}
