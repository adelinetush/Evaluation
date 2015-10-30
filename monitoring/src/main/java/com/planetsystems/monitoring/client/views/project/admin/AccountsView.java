/**
 * 
 */
package com.planetsystems.monitoring.client.views.project.admin;

import java.util.List;

import com.planetsystems.monitoring.client.listgrids.AccountCategoryListGrid;
import com.planetsystems.monitoring.client.listgrids.AccountsListGrid;
import com.planetsystems.monitoring.client.listgrids.ActivitiesListGrid;
import com.planetsystems.monitoring.client.presenters.project.admin.AccountsPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.events.AccountUiHandlers;
import com.planetsystems.monitoring.client.widgets.ProcnetSelectItem;
import com.planetsystems.monitoring.client.widgets.StatusBar;
import com.planetsystems.monitoring.client.widgets.ToolBar;
import com.planetsystems.monitoring.model.budget.Account;
import com.planetsystems.monitoring.model.budget.AccountCategory;
import com.planetsystems.monitoring.model.budget.Activity;
import com.planetsystems.monitoring.model.units.Department;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class AccountsView extends ViewWithUiHandlers<AccountUiHandlers> implements AccountsPresenter.MyView {

	private static final String CONTEXT_AREA_WIDTH = "100%";

	private HLayout mainPanel;
	private VLayout panel;
	private final ToolBar toolBarDir;
	private final ToolBar toolBarDept;
	private final StatusBar statusBar;
	private final TabSet tabSet;

	private Tab accountsCategoryTab;
	private Tab accountsTab;
	private Tab activitiesTab;

	private VLayout VL_FORM_ACCOUNTCAT;
	private VLayout VL_FORM_ACCOUNTS;
	private VLayout VL_FORM_ACTIVITIES;

	DynamicForm DF_ACCOUNTS;
	DynamicForm DF_ACTIVITY;
	DynamicForm DF_ACCOUNT_CATEGORY;

	TextItem accountCategoryNameText = new TextItem("accountCategoryNameText", "Category Name");
	TextItem accountCategoryCodeText = new TextItem("accountCategoryCodeText", "Category Code");
	TextAreaItem accountCategoryDescriptionText = new TextAreaItem("accountCategoryDescriptionText", "Category Description");

	private ProcnetSelectItem accountCategoryCombo;
	TextItem accountNameText = new TextItem("accountNameText", "Account Name");
	TextItem accountCodeText = new TextItem("accountCodeText", "Account Code");
	TextAreaItem accountDescriptionText = new TextAreaItem("accountDescriptionText", "Description");

	ProcnetSelectItem departmentCombo = new ProcnetSelectItem("departmentCombo", "Department ", 240, 240);
	TextItem activityNameText = new TextItem("activityNameText", "Activity Name");
	TextItem activitCodeText = new TextItem("activitCodeText", "Activity Code");
	TextAreaItem activityDescriptionText = new TextAreaItem("activityDescriptionText", "Description");

	private final Button cancelAccountButton = new Button("Cancel");
	private final Button saveAccountButton = new Button("Save");
	private final Button editAccountButton = new Button("Edit");
	private final Button deleteAccountButton = new Button("Delete");
	private final Button loadAccountButton = new Button("Load Accounts");

	private final Button cancelAccountCategoryButton = new Button("Cancel");
	private final Button saveAccountCategoryButton = new Button("Save");
	private final Button editAccountCategoryButton = new Button("Edit");
	private final Button deleteAccountCategoryButton = new Button("Delete");
	private final Button loadAccountCategoryButton = new Button("Load Categories");

	private final Button cancelActivityButton = new Button("Cancel");
	private final Button saveActivityButton = new Button("Save");
	private final Button editActivityButton = new Button("Edit");
	private final Button deleteActivityButton = new Button("Delete");
	private final Button loadActivityButton = new Button("Load Activities ");

	private final AccountsListGrid accountsListGrid;
	private final ActivitiesListGrid activitiesListGrid;
	private final AccountCategoryListGrid accountCategoryListGrid;

	HLayout HL_BUTTONS_ACCOUNTS = new HLayout();
	HLayout HL_BUTTONS_ACTIVITIES = new HLayout();
	HLayout HL_BUTTONS_ACCOUNTCATEGORY = new HLayout();

	@Inject
	public AccountsView(final ToolBar toolBarDir, ToolBar toolBarDept, final StatusBar statusBar, final TabSet tabSet, final AccountsListGrid accountsListGrid, final ActivitiesListGrid activitiesListGrid, final AccountCategoryListGrid accountCategoryListGrid) {


		this.toolBarDir = toolBarDir;
		this.toolBarDept = toolBarDept;
		this.statusBar = statusBar;
		this.accountsListGrid = accountsListGrid;
		this.activitiesListGrid = activitiesListGrid;
		this.accountCategoryListGrid = accountCategoryListGrid;
		this.tabSet = tabSet;

		accountCategoryCombo = new ProcnetSelectItem("accountCategoryCombo", "Account Category", 240, 240);

		panel = new VLayout();
		panel.setWidth(CONTEXT_AREA_WIDTH);
		panel.setHeight100();

		tabSet.setWidth100();
		tabSet.setHeight100();

		accountsTab = new Tab();
		accountsTab.setTitle("Accounts ");
		accountsTab.setID("accountsTab");
		accountsTab.setCanClose(false);

		activitiesTab = new Tab();
		activitiesTab.setTitle("Activities ");
		activitiesTab.setID("activitiesTab");
		activitiesTab.setCanClose(false);

		// accountsCategoryTab
		accountsCategoryTab = new Tab();
		accountsCategoryTab.setTitle("Account Category ");
		accountsCategoryTab.setID("accountsCategoryTab");
		accountsCategoryTab.setCanClose(false);

		DF_ACCOUNTS = new DynamicForm();
		DF_ACCOUNTS.setWidth100();
		// DF_ACCOUNTS.setHeight("10%");
		DF_ACCOUNTS.setWrapItemTitles(false);
		DF_ACCOUNTS.setNumCols(6);
		DF_ACCOUNTS.setFields(accountCategoryCombo, accountCodeText, accountNameText, accountDescriptionText);

		for (FormItem FI : DF_ACCOUNTS.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		// DF_ACTIVITY
		DF_ACTIVITY = new DynamicForm();
		DF_ACTIVITY.setWidth100();
		DF_ACTIVITY.setWrapItemTitles(false);
		DF_ACTIVITY.setNumCols(4);
		DF_ACTIVITY.setFields(departmentCombo, activityNameText, activitCodeText, activityDescriptionText);

		for (FormItem FI : DF_ACTIVITY.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		// DF_ACCOUNT_CATEGORY
		DF_ACCOUNT_CATEGORY = new DynamicForm();
		DF_ACCOUNT_CATEGORY.setWidth100();
		DF_ACCOUNT_CATEGORY.setWrapItemTitles(false);
		DF_ACCOUNT_CATEGORY.setNumCols(6);
		DF_ACCOUNT_CATEGORY.setFields(accountCategoryNameText, accountCategoryCodeText, accountCategoryDescriptionText);

		for (FormItem FI : DF_ACCOUNT_CATEGORY.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		VL_FORM_ACCOUNTCAT = new VLayout();
		VL_FORM_ACCOUNTCAT.setMembersMargin(5);
		VL_FORM_ACCOUNTCAT.setWidth100();
		VL_FORM_ACCOUNTCAT.setHeight100();

		VL_FORM_ACCOUNTS = new VLayout();
		VL_FORM_ACCOUNTS.setMembersMargin(5);
		VL_FORM_ACCOUNTS.setWidth100();
		VL_FORM_ACCOUNTS.setHeight100();

		VL_FORM_ACTIVITIES = new VLayout();
		VL_FORM_ACTIVITIES.setMembersMargin(5);
		VL_FORM_ACTIVITIES.setWidth100();
		VL_FORM_ACTIVITIES.setHeight100();

		HL_BUTTONS_ACCOUNTS.setMembersMargin(10);
		HL_BUTTONS_ACCOUNTS.setAlign(Alignment.CENTER);
		HL_BUTTONS_ACCOUNTS.setWidth100();
		HL_BUTTONS_ACCOUNTS.setMembersMargin(10);
		HL_BUTTONS_ACCOUNTS.setHeight("*");
		HL_BUTTONS_ACCOUNTS.setMembers(loadAccountButton, saveAccountButton, editAccountButton, deleteAccountButton, cancelAccountButton);

		HL_BUTTONS_ACTIVITIES.setMembersMargin(10);
		HL_BUTTONS_ACTIVITIES.setAlign(Alignment.CENTER);
		HL_BUTTONS_ACTIVITIES.setWidth100();
		HL_BUTTONS_ACTIVITIES.setMembersMargin(10);
		HL_BUTTONS_ACTIVITIES.setHeight("*");
		HL_BUTTONS_ACTIVITIES.setMembers(loadActivityButton, saveActivityButton, editActivityButton, deleteActivityButton, cancelActivityButton);

		HL_BUTTONS_ACCOUNTCATEGORY.setMembersMargin(10);
		HL_BUTTONS_ACCOUNTCATEGORY.setAlign(Alignment.CENTER);
		HL_BUTTONS_ACCOUNTCATEGORY.setWidth100();
		HL_BUTTONS_ACCOUNTCATEGORY.setMembersMargin(10);
		HL_BUTTONS_ACCOUNTCATEGORY.setHeight("*");
		HL_BUTTONS_ACCOUNTCATEGORY.setMembers(loadAccountCategoryButton, saveAccountCategoryButton, editAccountCategoryButton, deleteAccountCategoryButton, cancelAccountCategoryButton);
		// HL_BUTTONS_ACCOUNTCATEGORY

		accountsListGrid.setHeight("90%");
		VL_FORM_ACCOUNTS.addMember(accountsListGrid);
		VL_FORM_ACCOUNTS.addMember(DF_ACCOUNTS);
		VL_FORM_ACCOUNTS.addMember(HL_BUTTONS_ACCOUNTS);

		activitiesListGrid.setHeight("90%");

		VL_FORM_ACTIVITIES.addMember(activitiesListGrid);
		VL_FORM_ACTIVITIES.addMember(DF_ACTIVITY);
		VL_FORM_ACTIVITIES.addMember(HL_BUTTONS_ACTIVITIES);

		accountCategoryListGrid.setHeight("90%");
		VL_FORM_ACCOUNTCAT.addMember(accountCategoryListGrid);
		VL_FORM_ACCOUNTCAT.addMember(DF_ACCOUNT_CATEGORY);
		VL_FORM_ACCOUNTCAT.addMember(HL_BUTTONS_ACCOUNTCATEGORY);

		accountsTab.setPane(VL_FORM_ACCOUNTS);
		activitiesTab.setPane(VL_FORM_ACTIVITIES);
		accountsCategoryTab.setPane(VL_FORM_ACCOUNTCAT);

		tabSet.addTab(accountsCategoryTab);
		tabSet.addTab(accountsTab);
		tabSet.addTab(activitiesTab);
		
		Label headerAdd = new Label();
		headerAdd = new Label();
		headerAdd.setStyleName("crm-ContextArea-Header-Label");
		headerAdd.setHeight("1%");
		headerAdd.setContents("Accounts Setup & Management");
		headerAdd.setWidth("100%");
		headerAdd.setAlign(Alignment.LEFT);
		
		panel.addMember(headerAdd);
		panel.addMember(tabSet);
		
		mainPanel=new HLayout();
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		mainPanel.addMember(panel);

		bindCustomUiHandlers();

		// Accounts
		saveAccountButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (accountCategoryCombo.getValue() == null) {

					SC.warn("Select Account Category,then proceed ");
					accountCategoryCombo.focusInItem();

				} else if (accountCodeText.getValue() == null) {

					SC.warn("Enter Account Code,then proceed ");
					accountCodeText.focusInItem();

				} else if (accountNameText.getValue() == null) {

					SC.warn("Enter Account Name,then proceed ");
					accountNameText.focusInItem();

				} else if (accountDescriptionText.getValue() == null) {

					SC.warn("Enter Description");
					accountDescriptionText.focusInItem();
				}
				{

					Account account = new Account();
					account.setAccountCode(accountCodeText.getValueAsString());
					account.setAccountName(accountNameText.getValueAsString());
					account.setAccountDesc(accountDescriptionText.getValueAsString());

					AccountCategory category = new AccountCategory();
					category.setId(accountCategoryCombo.getValueAsString());

					account.setAccountCategory(category);

					if (getUiHandlers() != null) {

						getUiHandlers().saveAccountButtonClicked(account);

					}

				}
			}

		});
		editAccountButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (accountsListGrid.getSelectedRecord() == null) {

					SC.warn("Select Account to Edit");
					accountsListGrid.focus();
				} else if (accountCategoryCombo.getValue() == null) {

					SC.warn("Select Account Category,then proceed ");
					accountCategoryCombo.focusInItem();

				} else if (accountCodeText.getValue() == null) {

					SC.warn("Enter Account Code,then proceed ");
					accountCodeText.focusInItem();

				} else if (accountNameText.getValue() == null) {

					SC.warn("Enter Account Name,then proceed ");
					accountNameText.focusInItem();

				} else if (accountDescriptionText.getValue() == null) {

					SC.warn("Enter Description");
					accountDescriptionText.focusInItem();
				} else {

					SC.ask("Edit Account", "Do you Really want to Edit Account", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {
								Account account = new Account();
								account.setId(accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_ID));
								account.setAccountCode(accountCodeText.getValueAsString());

								// Log.info("Account Code: " +
								// accountCodeText.getValueAsString());

								account.setAccountName(accountNameText.getValueAsString());
								account.setAccountDesc(accountDescriptionText.getValueAsString());

								AccountCategory category = new AccountCategory();
								category.setId(accountCategoryCombo.getValueAsString());

								account.setAccountCategory(category);

								if (getUiHandlers() != null) {

									getUiHandlers().editAccountButtonClicked(account);

								}

							} else {

							}
						}

					});

				}

			}

		});
		deleteAccountButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (accountsListGrid.getSelectedRecord() == null) {

					SC.warn("Select Account to Edit");
					accountsListGrid.focus();
				}

				else {

					SC.ask("Edit Account", "Do you Really want to Delete Account", new BooleanCallback() {

						public void execute(Boolean value) {

							Account account = new Account();
							account.setId(accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_ID));
							if (getUiHandlers() != null) {

								getUiHandlers().deleteAccountButtonClicked(account);

							}

						}

					});

				}
			}

		});
		loadAccountButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (getUiHandlers() != null) {

					getUiHandlers().retrieveAllAccountsButtonClicked();

				}

			}

		});
		cancelAccountButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				DF_ACCOUNTS.clearValues();

			}

		});

		// Categories
		// loadAccountCategoryButtonn,saveAccountCategoryButton,editAccountCategoryButton,deleteAccountCategoryButton,cancelAccountCategoryButton

		loadAccountCategoryButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {

					getUiHandlers().retrieveAllAccountCategoriesButtonClicked();
				}

			}

		});
		saveAccountCategoryButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (accountCategoryNameText.getValue() == null) {

					SC.warn("Enter Category Name");
					accountCategoryNameText.focusInItem();

				} else if (accountCategoryCodeText.getValue() == null) {

					SC.warn("Enter Category Code");
					accountCategoryCodeText.focusInItem();
				} else if (accountCategoryDescriptionText.getValue() == null) {

					SC.warn("Enter Category Description");
					accountCategoryDescriptionText.focusInItem();
				} else {

					AccountCategory accountCategory = new AccountCategory();
					accountCategory.setAccountCategoryCode(accountCategoryCodeText.getValueAsString());
					accountCategory.setAccountCategoryName(accountCategoryNameText.getValueAsString());
					accountCategory.setAccountCategoryDesc(accountCategoryDescriptionText.getValueAsString());

					if (getUiHandlers() != null) {

						getUiHandlers().saveAccountCategoryButtonClicked(accountCategory);
					}
				}

			}

		});
		editAccountCategoryButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (accountCategoryListGrid.getSelectedRecord() == null) {

					SC.warn("Select Category To Edit");
					accountCategoryListGrid.focus();
				} else if (accountCategoryNameText.getValue() == null) {

					SC.warn("Enter Category Name");
					accountCategoryNameText.focusInItem();

				} else if (accountCategoryCodeText.getValue() == null) {

					SC.warn("Enter Category Code");
					accountCategoryCodeText.focusInItem();
				} else if (accountCategoryDescriptionText.getValue() == null) {

					SC.warn("Enter Category Description");
					accountCategoryDescriptionText.focusInItem();
				} else {
					SC.ask("Edit Account Category", "Do you Really want to Edit This Account Category", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								AccountCategory accountCategory = new AccountCategory();
								accountCategory.setId(accountCategoryListGrid.getSelectedRecord().getAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_ID));
								accountCategory.setAccountCategoryCode(accountCategoryCodeText.getValueAsString());
								accountCategory.setAccountCategoryName(accountCategoryNameText.getValueAsString());
								accountCategory.setAccountCategoryDesc(accountCategoryDescriptionText.getValueAsString());

								if (getUiHandlers() != null) {

									getUiHandlers().editAccountCategoryButtonClicked(accountCategory);
								}

							}

						}

					});

				}

			}

		});
		deleteAccountCategoryButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (accountCategoryListGrid.getSelectedRecord() == null) {

					SC.warn("Select Category To Edit");
					accountCategoryListGrid.focus();
				}

				else {
					SC.ask("Edit Account Category", "Do you Really want to Delete This Account Category", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								AccountCategory accountCategory = new AccountCategory();
								accountCategory.setId(accountCategoryListGrid.getSelectedRecord().getAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_ID));

								if (getUiHandlers() != null) {

									getUiHandlers().deleteAccountCategoryButtonClicked(accountCategory);
								}

							}

						}

					});

				}

			}

		});
		accountsListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				accountCategoryCombo.setValue(accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_CATEGORY_ID));// AccountsListGrid
				System.out.println("Debugging the category.. " + accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_CATEGORY_ID));
				accountCodeText.setValue(accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_CODE));
				accountNameText.setValue(accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_NAME));
				accountDescriptionText.setValue(accountsListGrid.getSelectedRecord().getAttribute(AccountsListGrid.ACCOUNT_DESCRIPTION));
			}

		});
		cancelAccountCategoryButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				DF_ACCOUNT_CATEGORY.clearValues();
			}

		});

		accountCategoryListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

			}

		});

		activitiesListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				departmentCombo.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.DEPARTMENT_ID));
				activityNameText.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_NAME));
				activitCodeText.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_CODE));
				activityDescriptionText.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_DESCRIPTION));

			}

		});

		loadActivityButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {

					getUiHandlers().retrieveAllActivitiesButtonClicked();
				}

			}

		});
		saveActivityButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (departmentCombo.getValue() == null) {

					SC.warn("Select Department");
					departmentCombo.focusInItem();

				} else if (activityNameText.getValue() == null) {

					SC.warn("Enter Activity Name");
					activityNameText.focusInItem();

				} else if (activitCodeText.getValue() == null) {

					SC.warn("Enter Activity Code");
					activitCodeText.focusInItem();
				} else if (activityDescriptionText.getValue() == null) {

					SC.warn("Enter Description ");
					activityDescriptionText.focusInItem();

				} else {

					Activity activity = new Activity();
					activity.setActivityCode(activitCodeText.getValueAsString());
					activity.setActivityName(activityNameText.getValueAsString());
					activity.setActivityDesc(activityDescriptionText.getValueAsString());

					Department department = new Department();
					department.setId(departmentCombo.getValueAsString());

					activity.setDepartment(department);

					if (getUiHandlers() != null) {

						getUiHandlers().saveActivityButtonClicked(activity);

					}
				}

			}

		});
		editActivityButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (activitiesListGrid.getSelectedRecord() == null) {

					SC.warn("Select Record to Edit");
					activitiesListGrid.focus();

				} else if (departmentCombo.getValue() == null) {

					SC.warn("Select Department");
					departmentCombo.focusInItem();

				} else if (activityNameText.getValue() == null) {

					SC.warn("Enter Activity Name");
					activityNameText.focusInItem();

				} else if (activitCodeText.getValue() == null) {

					SC.warn("Enter Activity Code");
					activitCodeText.focusInItem();
				} else if (activityDescriptionText.getValue() == null) {

					SC.warn("Enter Description ");
					activityDescriptionText.focusInItem();

				} else {

					SC.ask("Edit Activity", "Do you want to Really Edit this Activity", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Activity activity = new Activity();
								activity.setId(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_ID));
								activity.setActivityCode(activitCodeText.getValueAsString());
								activity.setActivityName(activityNameText.getValueAsString());
								activity.setActivityDesc(activityDescriptionText.getValueAsString());

								Department department = new Department();
								department.setId(departmentCombo.getValueAsString());

								activity.setDepartment(department);

								if (getUiHandlers() != null) {

									getUiHandlers().editActivityButtonClicked(activity);

								}

							} else {

							}

						}

					});

				}
			}

		});
		deleteActivityButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (activitiesListGrid.getSelectedRecord() == null) {

					SC.warn("Select Record to Edit");
					activitiesListGrid.focus();

				} else if (departmentCombo.getValue() == null) {

					SC.warn("Select Department");
					departmentCombo.focusInItem();

				} else if (activityNameText.getValue() == null) {

					SC.warn("Enter Activity Name");
					activityNameText.focusInItem();

				} else if (activitCodeText.getValue() == null) {

					SC.warn("Enter Activity Code");
					activitCodeText.focusInItem();
				} else if (activityDescriptionText.getValue() == null) {

					SC.warn("Enter Description ");
					activityDescriptionText.focusInItem();

				} else {

					SC.ask("Delete Activity", "Do you want to Really Delete this Activity", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Activity activity = new Activity();
								activity.setId(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_ID));

								if (getUiHandlers() != null) {

									getUiHandlers().deleteActivityButtonClicked(activity);

								}

							} else {

							}

						}

					});

				}

			}

		});
		cancelActivityButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				clearActivityForm();

			}

		});

		accountCategoryListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				accountCategoryNameText.setValue(accountCategoryListGrid.getSelectedRecord().getAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_NAME));
				accountCategoryCodeText.setValue(accountCategoryListGrid.getSelectedRecord().getAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_CODE));
				accountCategoryDescriptionText.setValue(accountCategoryListGrid.getSelectedRecord().getAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_DESCRIPTION));

			}

		});

		tabSet.addTabSelectedHandler(new TabSelectedHandler() {

			public void onTabSelected(TabSelectedEvent event) {

				if (event.getTab().getID().contentEquals(activitiesTab.getID())) {

					if (getUiHandlers() != null) {

						getUiHandlers().retrieveDepartments();
					}
				}
			}

		});

		activitiesListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				departmentCombo.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.DEPARTMENT_ID));
				activityNameText.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_NAME));
				activitCodeText.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_CODE));
				activityDescriptionText.setValue(activitiesListGrid.getSelectedRecord().getAttribute(ActivitiesListGrid.ACTIVITY_DESCRIPTION));
			}

		});
	}

	public Widget asWidget() {

		return mainPanel;
	}

	protected void bindCustomUiHandlers() {

		initToolBar();
	}

	protected void initToolBar() {

		// toolBarDir.addSeparator();

		toolBarDir.addButton(ToolBar.PRINT_PREVIEW_BUTTON, Monitoring.getConstants().printPreviewButtonTooltip(), null);
		toolBarDir.addButton(ToolBar.EXPORT_BUTTON, Monitoring.getConstants().exportButtonTooltip(), null);
		// toolBar.addButton(ToolBar.MAIL_MERGE_BUTTON,
		// Serendipity.getConstants().MailMergeButtonTooltip(), null);
		toolBarDir.addButton(ToolBar.REPORTS_BUTTON, Monitoring.getConstants().reportsButtonTooltip(), null);

		toolBarDir.addSeparator();

		toolBarDir.addButton(ToolBar.DELETE_BUTTON, Monitoring.getConstants().deleteButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					// getUiHandlers().onDeleteButtonClicked();
				}
			}
		});

		// toolBar.addButton(ToolBar.EMAIL_BUTTON,
		// Serendipity.getConstants().EmailButtonTooltip(), null);

		toolBarDir.addSeparator();

		toolBarDir.addButton(ToolBar.REFRESH_BUTTON, Monitoring.getConstants().refreshButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					// getUiHandlers().onRefreshButtonClicked();
				}
			}
		});
	}

	public void setServerResponse(String serverResponse) {

		SC.say("" + serverResponse);

	}

	public void loadAllAccounts(List<Account> accounts) {

		ListGridRecord[] list = new ListGridRecord[accounts.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Account account : accounts) {
			// System.out.println("User: "+user.getFullName()+": "+user.getRole());

			record = new ListGridRecord();
			record.setAttribute(AccountsListGrid.ICON, "calender");
			record.setAttribute(AccountsListGrid.ACCOUNT_ID, account.getId());
			record.setAttribute(AccountsListGrid.ACCOUNT_CODE, account.getAccountCode());
			record.setAttribute(AccountsListGrid.ACCOUNT_NAME, account.getAccountName());
			/*
			 * record.setAttribute(AccountsListGrid.ACCOUNT_CATEGORY_NAME,
			 * account .getAccountCategory().getAccountCategoryName());
			 */

			record.setAttribute(AccountsListGrid.ACCOUNT_CATEGORY_ID, account.getAccountCategory().getId());

			record.setAttribute(AccountsListGrid.ACCOUNT_DESCRIPTION, account.getAccountDesc());

			record.setAttribute("emptyField", "");

			if (account.getChangedBy() != null) {

				record.setAttribute(AccountCategoryListGrid.CHANGED_BY, account.getChangedBy().getFullName());

			} else {

			}

			if (account.getCreatedBy() != null) {

				record.setAttribute(AccountCategoryListGrid.CREATED_BY, account.getCreatedBy().getFullName());
			} else {

			}
			list[listCount] = record;
			listCount++;

		}
		this.accountsListGrid.setData(list);
	}

	public void loadAllAccountCategories(List<AccountCategory> accountCategory) {

		ListGridRecord[] list = new ListGridRecord[accountCategory.size()];
		int listCount = 0;
		ListGridRecord record;
		for (AccountCategory accountCat : accountCategory) {
			// System.out.println("User: "+user.getFullName()+": "+user.getRole());

			record = new ListGridRecord();
			record.setAttribute(AccountCategoryListGrid.ICON, "calender");
			record.setAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_ID, accountCat.getId());
			record.setAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_CODE, accountCat.getAccountCategoryCode());
			record.setAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_NAME, accountCat.getAccountCategoryName());
			record.setAttribute(AccountCategoryListGrid.ACCOUNT_CATEGORY_DESCRIPTION, accountCat.getAccountCategoryDesc());
			record.setAttribute("emptyField", "");

			if (accountCat.getChangedBy() != null) {

				record.setAttribute(AccountCategoryListGrid.CHANGED_BY, accountCat.getChangedBy().getFullName());

			} else {

			}

			if (accountCat.getCreatedBy() != null) {

				record.setAttribute(AccountCategoryListGrid.CREATED_BY, accountCat.getCreatedBy().getFullName());
			} else {

			}
			list[listCount] = record;
			listCount++;

		}
		this.accountCategoryListGrid.setData(list);

	}

	public void loadAllActivities(List<Activity> activities) {

		ListGridRecord[] list = new ListGridRecord[activities.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Activity activity : activities) {

			record = new ListGridRecord();
			record.setAttribute(ActivitiesListGrid.ICON, "calender");
			record.setAttribute(ActivitiesListGrid.ACTIVITY_ID, activity.getId());
			record.setAttribute(ActivitiesListGrid.ACTIVITY_CODE, activity.getActivityCode());
			record.setAttribute(ActivitiesListGrid.ACTIVITY_NAME, activity.getActivityName());
			record.setAttribute(ActivitiesListGrid.DEPARTMENT_ID, activity.getDepartment().getId());
			record.setAttribute(ActivitiesListGrid.DEPARTMENT_CODE, activity.getDepartment().getDepartmentCode());
			record.setAttribute(ActivitiesListGrid.DEPARTMENT_NAME, activity.getDepartment().getDepartmentName());
			record.setAttribute(ActivitiesListGrid.ACTIVITY_DESCRIPTION, activity.getActivityDesc());

			record.setAttribute("emptyField", "");

			if (activity.getChangedBy() != null) {

				record.setAttribute(AccountCategoryListGrid.CHANGED_BY, activity.getChangedBy().getFullName());

			} else {

			}

			if (activity.getCreatedBy() != null) {

				record.setAttribute(AccountCategoryListGrid.CREATED_BY, activity.getCreatedBy().getFullName());
			} else {

			}
			list[listCount] = record;
			listCount++;

		}
		this.activitiesListGrid.setData(list);
	}

	public void clearAccountForm() {

		this.DF_ACCOUNTS.clearValues();

	}

	public void clearAccountCategoryForm() {

		this.DF_ACCOUNT_CATEGORY.clearValues();

	}

	public void clearActivityForm() {

		this.DF_ACTIVITY.clearValues();

	}

	public void loadCategoriesCombo(List<AccountCategory> accountCategories) {
		if (accountCategories != null && accountCategories.size() > 0) {
			DataSource dataSource = new DataSource();
			DataSourceTextField id = new DataSourceTextField("id", "ID");
			DataSourceTextField code = new DataSourceTextField("code", "Code");
			DataSourceTextField name = new DataSourceTextField("name", "Name");
			dataSource.setClientOnly(true);
			dataSource.setFields(id, code, name);
			ListGridRecord[] list = new ListGridRecord[accountCategories.size()];
			int listCount = 0;
			ListGridRecord record;
			for (AccountCategory accountCategory : accountCategories) {

				record = new ListGridRecord();

				record.setAttribute("id", accountCategory.getId());
				record.setAttribute("code", accountCategory.getAccountCategoryCode());
				record.setAttribute("name", accountCategory.getAccountCategoryName());

				list[listCount] = record;
				listCount++;

			}

			dataSource.setTestData(list);

			this.accountCategoryCombo.setOptionDataSource(dataSource);
		} else {

		}

	}

	public void loadDepartmentsGrid(List<Department> departments) {

		DataSource dataSource = new DataSource();

		DataSourceTextField id = new DataSourceTextField("id", "ID");
		DataSourceTextField code = new DataSourceTextField("code", "Code");
		DataSourceTextField name = new DataSourceTextField("name", "Name");

		dataSource.setClientOnly(true);
		dataSource.setFields(id, code, name);

		ListGridRecord[] list = new ListGridRecord[departments.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Department department : departments) {

			record = new ListGridRecord();

			record.setAttribute("id", department.getId());
			record.setAttribute("code", department.getDepartmentCode());
			record.setAttribute("name", department.getDepartmentName());

			list[listCount] = record;
			listCount++;

		}

		dataSource.setTestData(list);

		this.departmentCombo.setOptionDataSource(dataSource);

	}

}
