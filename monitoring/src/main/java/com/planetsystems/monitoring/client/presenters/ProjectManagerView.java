package com.planetsystems.monitoring.client.presenters;

import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.data.ProjectsDataSource;
import com.planetsystems.monitoring.client.data.SettingsDataSource;
import com.planetsystems.monitoring.client.data.StakeHoldersDataSource;
import com.planetsystems.monitoring.client.data.SystemAdministrationDataSource;
import com.planetsystems.monitoring.client.data.TeamInteractionDataSource;
import com.planetsystems.monitoring.client.views.handlers.MainPageUiHandlers;
import com.planetsystems.monitoring.client.widgets.ApplicationMenu;
import com.planetsystems.monitoring.client.widgets.LiveOperationsPane;
import com.planetsystems.monitoring.client.widgets.MainContextPane;
import com.planetsystems.monitoring.client.widgets.MainStatusBar;
import com.planetsystems.monitoring.client.widgets.Masthead;
import com.planetsystems.monitoring.client.widgets.NavigationPane;
import com.planetsystems.monitoring.client.widgets.NavigationPaneHeader;
import com.planetsystems.monitoring.client.widgets.StatusBar;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;


public class ProjectManagerView extends ViewWithUiHandlers<MainPageUiHandlers>
		implements ProjectManagerPresenter.MyView {

	private static final int NORTH_HEIGHT = 65;
	private static final int DEFAULT_MENU_WIDTH = 70;
	private static final String DEFAULT_MARGIN = "0px";

	// Application Menu Widget
	private final ApplicationMenu applicationMenu;
	private final Masthead mastHead;
	private final NavigationPaneHeader navigationPaneHeader;
	private final NavigationPane navigationPane;

	private final LiveOperationsPane liveOperationsPane;

	private final MainContextPane mainContextPane;

	private final StatusBar statusBar;
	private final MainStatusBar mainStatusBar;

	private VLayout panel;
	private HLayout northLayout;
	private HLayout southLayout;
	public static VLayout westLayout, eastLayout;
	private VLayout centerLayout;

	private HLayout HL_PASS_BUTTONS;

	private DynamicForm DF_FORM;

	private PasswordItem passwordText = new PasswordItem("passwordText",
			"Password");
	private PasswordItem confirmPasswordText = new PasswordItem(
			"confirmPasswordText", "Confirm Password");
	private PasswordItem oldPasswordText = new PasswordItem("oldPasswordText",
			"Old Password");

	private final Button saveButton = new Button("Save");
	private final Button clearButton = new Button("Clear");
	private final Button closeButton = new Button("Close");

	private VLayout VL_WINDOW;

	private HLayout HL_BUTTONS;

	private VLayout widget;

	
	@Inject
	public ProjectManagerView(Masthead mastHead,
			ApplicationMenu applicationMenu,
			NavigationPaneHeader navigationPaneHeader,
			NavigationPane navigationPane, StatusBar statusBar,
			MainStatusBar mainStatusBar, LiveOperationsPane liveOperationsPane,
			MainContextPane mainContextPane) {

		this.mastHead = mastHead;
		this.applicationMenu = applicationMenu;
		this.navigationPaneHeader = navigationPaneHeader;
		this.navigationPane = navigationPane;
		this.statusBar = statusBar;
		this.mainStatusBar = mainStatusBar;
		this.liveOperationsPane = liveOperationsPane;
		this.mainContextPane = mainContextPane;

		Window.enableScrolling(false);
		Window.setMargin(DEFAULT_MARGIN);

		panel = new VLayout();
		panel.setWidth100();
		panel.setHeight100();

		centerLayout = this.mainContextPane.getWorkspace();

		northLayout = new HLayout();
		northLayout.setHeight(NORTH_HEIGHT);

		initApplicationMenu();

		// initialise the nested layout container
		VLayout vLayout = new VLayout();
		vLayout.addMember(this.mastHead);
		vLayout.addMember(this.applicationMenu);

		// add the nested layout container to the North layout container
		northLayout.addMember(vLayout);

		// initialise the West layout container
		westLayout = this.navigationPane;
		eastLayout = this.liveOperationsPane;

		// westLayout.setVisible(true);

		// centerLayout
		// initialise the South layout container
		southLayout = new HLayout();
		southLayout.setMembers(westLayout, mainContextPane, eastLayout);

		DF_FORM = new DynamicForm();
		DF_FORM.setWidth100();
		DF_FORM.setNumCols(2);
		DF_FORM.setWrapItemTitles(false);

		DF_FORM.setItems(oldPasswordText, passwordText, confirmPasswordText);

		for (FormItem FI : DF_FORM.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		HL_PASS_BUTTONS = new HLayout();
		HL_PASS_BUTTONS.setWidth100();
		HL_PASS_BUTTONS.setMembersMargin(2);
		HL_PASS_BUTTONS.setMembers(saveButton, clearButton, closeButton);

		VL_WINDOW = new VLayout();
		VL_WINDOW.setWidth100();
		VL_WINDOW.setHeight100();
		VL_WINDOW.setBorder("1px solid black");
		VL_WINDOW.setPadding(2);

		HL_BUTTONS = new HLayout();
		HL_BUTTONS.setWidth100();
		HL_BUTTONS.setHeight("*");
		HL_BUTTONS.setBorder("1px solid black");
		HL_BUTTONS.setPadding(2);

		/*
		 * this.setAutoCenter(true); this.setWidth("40%");
		 * this.setHeight("45%"); this.setIsModal(true);
		 * this.addItem(VL_WINDOW); this.setTitle("Change Password");
		 */

		VL_WINDOW.addMember(DF_FORM);
		VL_WINDOW.addMember(HL_PASS_BUTTONS);

		panel.addMember(northLayout);
		panel.addMember(southLayout);
		panel.addMember(mainStatusBar);

		initNavigationPane();

		bindCustomUiHandlers();
	}

	private static final String NAME = "name";

	protected void bindCustomUiHandlers() {

		navigationPane.addRecordClickHandler(Monitoring.getConstants()
				.ProjectsStackSectionName(), new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				onRecordClicked(event);
			}
		});
	}

	public void onRecordClicked(RecordClickEvent event) {
		Record record = event.getRecord();
		String name = record.getAttributeAsString(NAME);
		GWT.log("Clicked Item: " + name);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.View#asWidget()
	 */
	public Widget asWidget() {

		return panel;
	}

	public void setInSlot(Object slot, Widget content) {
		if (slot == ProjectManagerPresenter.TYPE_SetContextAreaContent) {

			mainContextPane.setMembers((HLayout) content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void initApplicationMenu() {

		applicationMenu.addMenu(Monitoring.getConstants().FileMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.FileMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						SC.confirm("Item Clicked", null);
					}
				});
		applicationMenu.addMenu(Monitoring.getConstants().ActionsMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.ActionsMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						String applicationName = event.getItem().getTitle();

						if (applicationName.contains("Workflow & Approvals")) {

						}

					}

				});
		applicationMenu.addMenu(Monitoring.getConstants().ViewsMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.ViewsMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						String applicationName = event.getItem().getTitle();

						if (applicationName.contains("Show Sections")) {

							westLayout.setVisible(true);
						} else if (applicationName.contains("Hide Sections")) {

							westLayout.setVisible(false);
						} else if (applicationName.contains("Search")) {
							// Monitoring.fileUpload();
						}
					}

				});
		applicationMenu.addMenu(Monitoring.getConstants().ModulesMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.ModulesMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						SC.confirm("Item Clicked", null);
					}
				});
		applicationMenu.addMenu(Monitoring.getConstants().ToolsMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.ToolsMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						SC.confirm("Item Clicked", null);
					}
				});
		applicationMenu.addMenu(Monitoring.getConstants().SecurityMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.SecurityMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {

						GWT.log("Application Menu Clicked: ", null);
						String applicationName = event.getItem().getTitle();

						// SC.say(""+applicationName);
						if (applicationName.contains("Log Out")) {
							Monitoring.logout();
						} else if (applicationName.contains("Change Password")) {
							GWT.log("Change Password: ", null);

						}

					}

				});
		applicationMenu.addMenu(Monitoring.getConstants().HelpMenuName(),
				DEFAULT_MENU_WIDTH, Monitoring.getConstants()
						.HelpMenuItemNames(), new ClickHandler() {

					public void onClick(MenuItemClickEvent event) {
						SC.confirm("Item Clicked", null);
					}
				});
	}

	private void initNavigationPane() {

		navigationPane.addSection(Monitoring.getConstants()
				.ProjectsStackSectionName(), ProjectsDataSource.getInstance());

		navigationPane.addSection(Monitoring.getConstants()
				.TeamInteractionStackSectionName(), TeamInteractionDataSource
				.getInstance());
		navigationPane.addSection(Monitoring.getConstants()
				.StackHoldersStackSectionName(), StakeHoldersDataSource
				.getInstance());
		navigationPane.addSection(Monitoring.getConstants().SettingsStackSectionName(), SystemAdministrationDataSource.getInstance());

		if (getUiHandlers() != null) {

			getUiHandlers().onNewProjectClickHandler();
			getUiHandlers().onClosedProjectClickHandler();
			getUiHandlers().onApprovedProjectClickHandler();
			
			getUiHandlers().onPendingProjectClickHandler();
			getUiHandlers().onTeamInterationClickHandler();
			getUiHandlers().onStakeholdersClickHandler();
			getUiHandlers().onCreateUserClickHandler();
		}

	}

	public NavigationPaneHeader getNavigationPaneHeader() {
		return navigationPaneHeader;
	}

	public NavigationPane getNavigationPane() {
		return navigationPane;
	}

	public void setGetLoggedInUserError(String serverErrorResponse) {

		SC.warn(serverErrorResponse);
	}

	public Masthead getMastHead() {

		return mastHead;
	}

	public ApplicationMenu getApplicationMenu() {

		return applicationMenu;
	}

	public VLayout getWestLayout() {

		return westLayout;
	}

	
	public MainContextPane getMainContextPane() {
		return mainContextPane;
	}

	public void setUserPermissions() {

		navigationPane.addSection(Monitoring.getConstants()
				.TeamInteractionStackSectionName(), ProjectsDataSource
				.getInstance());

		navigationPane.addSection(Monitoring.getConstants()
				.ProjectsStackSectionName(), ProjectsDataSource.getInstance());

		navigationPane.addSection(Monitoring.getConstants()
				.ProjectTeamsStackSectionName(), ProjectsDataSource
				.getInstance());

		navigationPane.addSection(Monitoring.getConstants()
				.SettingsStackSectionName(), SettingsDataSource.getInstance());

		if (getUiHandlers() != null) {
			getUiHandlers().onNewProjectClickHandler();

			getUiHandlers().onTeamInterationClickHandler();
		}

	}

	//

}
