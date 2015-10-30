package com.planetsystems.monitoring.client.views.project.admin;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.events.OrganizationalUnitsUiHandlers;
import com.planetsystems.monitoring.client.widgets.ProcnetSelectItem;
import com.planetsystems.monitoring.client.widgets.StatusBar;
import com.planetsystems.monitoring.client.widgets.ToolBar;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;
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
import com.smartgwt.client.widgets.form.fields.SelectItem;
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
import com.planetsystems.monitoring.client.listgrids.DepartmentListGrid;
import com.planetsystems.monitoring.client.listgrids.DivisionsListGrid;
import com.planetsystems.monitoring.client.listgrids.SectionsListGrid;
import com.planetsystems.monitoring.client.presenters.project.admin.OrganizationalUnitsPresenter;


public class OrganizationalUnitsView extends ViewWithUiHandlers<OrganizationalUnitsUiHandlers> implements OrganizationalUnitsPresenter.MyView {

	private static final String CONTEXT_AREA_WIDTH = "100%";

	private HLayout mainPanel;
	private VLayout panel;
	private final ToolBar toolBarDir;
	private final ToolBar toolBarDept;
	private final StatusBar statusBar;
	private final TabSet tabSet;

	private Tab departmentsTab;
	private Tab divisionsTab;
	private Tab sectionsTab;

	private DivisionsListGrid divisionListGrid;
	private DepartmentListGrid departmentListGrid;
	private SectionsListGrid sectionListGrid;

	private VLayout VL_FORM_DIV;
	private VLayout VL_FORM_DEPT;
	private VLayout VL_FORM_SECT;

	DynamicForm DF_DIVISIONS;
	DynamicForm DF_DEPARTMENTS;
	DynamicForm DF_SECTIONS;

	TextItem divisionNameText = new TextItem("divisionNameText", "Division Name");
	TextAreaItem divisionDescriptionText = new TextAreaItem("divisionDescriptionText", "Division Description");
	TextItem divisionCodeText = new TextItem("divisionCodeText", "Division Code");

	// private TextItem emailText = new TextItem("emailText","Email");
	TextItem departmentCodeText = new TextItem("departmentCodeText", "DepartmentCode");
	TextItem departmentNameText = new TextItem("departmentNameText", "Department Name");
	TextItem departmentDescriptionText = new TextItem("directorateDescriptionText", "Department Description");
	SelectItem directorateSelect = new SelectItem("directorateSelect", "Select Directorate");
	SelectItem voteCombo = new SelectItem("voteCombo", "Select Vote");

	TextItem sectionNameText = new TextItem("sectionNameText", "Section Name");
	TextItem sectionCodeText = new TextItem("sectionCodeText", "Section Code");
	TextItem sectionDescriptionText = new TextItem("sectionDescriptionText", "Section Description");
	private ProcnetSelectItem divisionsCombo = new ProcnetSelectItem("divisionsCombo", "Division", 240, 240);
	private ProcnetSelectItem departmentsCombo = new ProcnetSelectItem("departmentsCombo", "Department", 240, 240);

	private final Button cancelButtonDivision = new Button("Cancel");
	private final Button saveButtonDivision = new Button("Save");
	private final Button cancelButtonDept = new Button("Cancel");
	private final Button saveButtonDept = new Button("Save");
	private final Button editButtonDept = new Button("Edit");
	private final Button deleteButtonDept = new Button("Delete");
	private final Button editButtonDivision = new Button("Edit");
	private final Button deleteButtonDivision = new Button("Delete");
	private final Button refreshButtonDivision = new Button("Load Division");
	private final Button refreshButtonDept = new Button("Load Departments ");

	private final Button cancelButtonSect = new Button("Cancel");
	private final Button saveButtonSect = new Button("Save");
	private final Button editButtonSect = new Button("Edit");
	private final Button deleteButtonSect = new Button("Delete");
	private final Button refreshButtonSect = new Button("Load Sections");

	private final Button addDepartmentButton = new Button("Add Department");

	HLayout HL_BUTTONS_DIVISION = new HLayout();
	HLayout HL_BUTTONS_DEPT = new HLayout();
	HLayout HL_BUTTONS_SECT = new HLayout();

	private String mode = "directorates";

	@Inject
	public OrganizationalUnitsView(final ToolBar toolBarDir, ToolBar toolBarDept, final StatusBar statusBar, final DivisionsListGrid divisionListGrid, final DepartmentListGrid departmentListGrid, final TabSet tabSet) {


		this.toolBarDir = toolBarDir;
		this.toolBarDept = toolBarDept;
		this.statusBar = statusBar;
		this.departmentListGrid = departmentListGrid;
		this.divisionListGrid = divisionListGrid;
		this.tabSet = tabSet;
		sectionListGrid=new SectionsListGrid();
		Label headerAdd = new Label();
		headerAdd = new Label();
		headerAdd.setStyleName("crm-ContextArea-Header-Label");
		headerAdd.setHeight("1%");
		headerAdd.setContents("Organisation Units Setup & Management");
		headerAdd.setWidth("100%");
		headerAdd.setAlign(Alignment.LEFT);

		panel = new VLayout();
		panel.setWidth(CONTEXT_AREA_WIDTH);
		panel.setHeight100();

		this.divisionListGrid.setHeight("85%");
		this.departmentListGrid.setHeight("85%");
		sectionListGrid.setHeight("85%");

		HL_BUTTONS_DIVISION.setMembersMargin(10);
		HL_BUTTONS_DIVISION.setAlign(Alignment.CENTER);
		HL_BUTTONS_DIVISION.setWidth100();
		HL_BUTTONS_DIVISION.setMembersMargin(10);
		HL_BUTTONS_DIVISION.setHeight("1%");

		HL_BUTTONS_DEPT.setMembersMargin(10);
		HL_BUTTONS_DEPT.setAlign(Alignment.CENTER);
		HL_BUTTONS_DEPT.setWidth100();
		HL_BUTTONS_DEPT.setMembersMargin(10);
		HL_BUTTONS_DEPT.setHeight("1%");

		HL_BUTTONS_SECT.setMembersMargin(10);
		HL_BUTTONS_SECT.setAlign(Alignment.CENTER);
		HL_BUTTONS_SECT.setWidth100();
		HL_BUTTONS_SECT.setMembersMargin(10);
		HL_BUTTONS_SECT.setHeight("1%");

		tabSet.setWidth100();
		tabSet.setHeight100();

		divisionsTab = new Tab();
		divisionsTab.setTitle("Divisions ");
		divisionsTab.setID("divisionsTab");
		divisionsTab.setCanClose(false);

		departmentsTab = new Tab();
		departmentsTab.setTitle("Departments ");
		departmentsTab.setID("departmentsTab");
		departmentsTab.setCanClose(false);

		sectionsTab = new Tab();
		sectionsTab.setTitle("Sections ");
		sectionsTab.setID("sectionsTab");
		sectionsTab.setCanClose(false);

		// Dynamic Forms

		DF_DIVISIONS = new DynamicForm();
		DF_DIVISIONS.setWidth100();
		DF_DIVISIONS.setHeight("10%");
		DF_DIVISIONS.setWrapItemTitles(false);
		DF_DIVISIONS.setNumCols(6);

		// DF_DEPARTMENTS = new DynamicForm();
		DF_DEPARTMENTS = new DynamicForm();
		DF_DEPARTMENTS.setWidth100();
		DF_DEPARTMENTS.setHeight("10%");
		DF_DEPARTMENTS.setWrapItemTitles(false);
		DF_DEPARTMENTS.setNumCols(6);

		DF_SECTIONS = new DynamicForm();
		DF_SECTIONS.setWidth100();
		DF_SECTIONS.setHeight("10%");
		DF_SECTIONS.setWrapItemTitles(false);
		DF_SECTIONS.setNumCols(4);

		DF_DIVISIONS.setItems(departmentsCombo, divisionCodeText, divisionNameText, divisionDescriptionText);
		DF_DEPARTMENTS.setItems(departmentNameText, departmentCodeText, departmentDescriptionText);

		for (FormItem FI : DF_DIVISIONS.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		for (FormItem FI : DF_DEPARTMENTS.getFields())

		{
			FI.setWidth(200);
			FI.setCellHeight(37);
		}

		DF_SECTIONS.setItems(divisionsCombo, sectionCodeText, sectionNameText, sectionDescriptionText);
		for (FormItem FI : DF_SECTIONS.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		VL_FORM_DIV = new VLayout();
		VL_FORM_DIV.setMembersMargin(5);
		VL_FORM_DIV.setWidth100();
		VL_FORM_DIV.setHeight100();

		VL_FORM_DEPT = new VLayout();
		VL_FORM_DEPT.setMembersMargin(10);
		VL_FORM_DEPT.setHeight100();
		VL_FORM_DEPT.setWidth100();

		VL_FORM_SECT = new VLayout();
		VL_FORM_SECT.setMembersMargin(5);
		VL_FORM_SECT.setWidth100();
		VL_FORM_SECT.setHeight100();
		// Add Controls To UI

		HL_BUTTONS_DIVISION.setMembers(saveButtonDivision, editButtonDivision, deleteButtonDivision, refreshButtonDivision, cancelButtonDivision);
		HL_BUTTONS_DEPT.setMembers(saveButtonDept, editButtonDept, deleteButtonDept, refreshButtonDept, cancelButtonDept);
		HL_BUTTONS_SECT.setMembers(saveButtonSect, editButtonSect, deleteButtonSect, refreshButtonSect, cancelButtonSect);

		VL_FORM_DIV.addMember(divisionListGrid);
		VL_FORM_DIV.addMember(DF_DIVISIONS);
		VL_FORM_DIV.addMember(HL_BUTTONS_DIVISION);

		VL_FORM_DEPT.addMember(departmentListGrid);
		VL_FORM_DEPT.addMember(DF_DEPARTMENTS);
		VL_FORM_DEPT.addMember(HL_BUTTONS_DEPT);

		VL_FORM_SECT.addMember(sectionListGrid);
		VL_FORM_SECT.addMember(DF_SECTIONS);
		VL_FORM_SECT.addMember(HL_BUTTONS_SECT);

		divisionsTab.setPane(VL_FORM_DIV);
		departmentsTab.setPane(VL_FORM_DEPT);
		sectionsTab.setPane(VL_FORM_SECT);

		tabSet.addTab(departmentsTab);
		tabSet.addTab(divisionsTab);
		tabSet.addTab(sectionsTab);
		
		
		panel.addMember(headerAdd);
		panel.addMember(tabSet);
		mainPanel=new HLayout();
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		mainPanel.addMember(panel);

		bindCustomUiHandlers();

		// saveButtonDept, editButtonDept,
		// deleteButtonDept, refreshButtonDept, cancelButtonDept

		saveButtonDept.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// departmentNameText,departmentCodeText,
				// departmentDescriptionText
				if (departmentNameText.getValue() == null) {

					SC.warn("Enter Department Name");
					departmentNameText.focusInItem();

				} else if (departmentCodeText.getValue() == null) {

					SC.warn("Enter Department Code");
					departmentCodeText.focusInItem();
				} else if (departmentDescriptionText.getValue() == null) {

					SC.warn("Enter Department Description");
					departmentDescriptionText.focusInItem();
				} else {

					Department department = new Department();
					department.setDepartmentCode(departmentCodeText.getValueAsString());
					department.setDepartmentName(departmentNameText.getValueAsString());
					department.setDepartmentDescription(departmentDescriptionText.getValueAsString());

					if (getUiHandlers() != null) {

						getUiHandlers().saveDepartmentButtonClicked(department);

					}
				}

			}

		});

		editButtonDept.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (departmentListGrid.getSelectedRecord() == null) {

					SC.say("Select Record to Edit");
					departmentListGrid.focus();

				} else if (departmentNameText.getValue() == null) {

					SC.warn("Enter Department Name");
					departmentNameText.focusInItem();

				} else if (departmentCodeText.getValue() == null) {

					SC.warn("Enter Department Code");
					departmentCodeText.focusInItem();
				} else if (departmentDescriptionText.getValue() == null) {

					SC.warn("Enter Department Description");
					departmentDescriptionText.focusInItem();
				} else {

					SC.ask("Edit Department", "Do you Really want to Edit this Department", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Department department = new Department();// DepartmentListGrid
																			// departmentListGrid
								department.setId(departmentListGrid.getSelectedRecord().getAttribute(DepartmentListGrid.DEPARTMENT_ID));

								department.setDepartmentCode(departmentCodeText.getValueAsString());
								department.setDepartmentName(departmentNameText.getValueAsString());
								department.setDepartmentDescription(departmentDescriptionText.getValueAsString());

								if (getUiHandlers() != null) {

									getUiHandlers().editDepartmentButtonClicked(department);

								}

							} else {

							}
						}

					});

				}
			}

		});

		deleteButtonDept.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (departmentListGrid.getSelectedRecord() == null) {

					SC.say("Select Record to Edit");
					departmentListGrid.focus();

				} else {

					SC.ask("Delete Department", "Do you Really want to Delete this Department", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Department department = new Department();
								department.setId(departmentListGrid.getSelectedRecord().getAttribute(DepartmentListGrid.DEPARTMENT_ID));

								if (getUiHandlers() != null) {

									getUiHandlers().deleteDepartmentButtonClicked(department);

								}

							} else {

							}
						}

					});

				}
			}

		});

		departmentListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				departmentNameText.setValue(departmentListGrid.getSelectedRecord().getAttribute(DepartmentListGrid.DEPARTMENT_NAME));
				departmentCodeText.setValue(departmentListGrid.getSelectedRecord().getAttribute(DepartmentListGrid.DEPARTMENT_CODE));
				departmentDescriptionText.setValue(departmentListGrid.getSelectedRecord().getAttribute(DepartmentListGrid.DEPARTMENT_DESCRIPTION));

			}

		});

		refreshButtonDept.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUiHandlers() == null) {

					getUiHandlers().retrieveAllDepartmentsButtonClicked();
				}
			}

		});

		cancelButtonDept.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				clearDepartmentForm();
			}

		});

		// Division Button Handlers

		saveButtonDivision.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (departmentsCombo.getValue() == null) {

					SC.say("Select Department Division belongs to");
					departmentsCombo.focusInItem();
				} else if (divisionCodeText.getValue() == null) {

					SC.say("Enter Division Code");
					divisionCodeText.focusInItem();

				} else if (divisionNameText.getValue() == null) {

					SC.say("Enter Division Name");
					divisionCodeText.focusInItem();

				} else if (divisionDescriptionText.getValue() == null) {

					SC.say("Enter Division Description");
					divisionDescriptionText.focusInItem();

				} else {

					Division division = new Division();
					division.setDivisionCode(divisionCodeText.getValueAsString());
					division.setDivisionName(divisionNameText.getValueAsString());
					division.setDivisionDesctiption(divisionDescriptionText.getValueAsString());

					Department department = new Department();
					department.setId(departmentsCombo.getValueAsString());

					division.setDepartment(department);

					if (getUiHandlers() != null) {

						getUiHandlers().saveDivisionButtonClicked(division);
					}
				}

			}

		});
		editButtonDivision.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (divisionListGrid.getSelectedRecord() == null) {

					SC.warn("Select Divsion Record to Edit");
					divisionListGrid.focus();

				} else if (departmentsCombo.getValue() == null) {

					SC.warn("Select Department Division belongs to");
					departmentsCombo.focusInItem();
				} else if (divisionCodeText.getValue() == null) {

					SC.warn("Enter Division Code");
					divisionCodeText.focusInItem();

				} else if (divisionNameText.getValue() == null) {

					SC.warn("Enter Division Name");
					divisionCodeText.focusInItem();

				} else if (divisionDescriptionText.getValue() == null) {

					SC.warn("Enter Division Description");
					divisionDescriptionText.focusInItem();

				} else {

					SC.ask("Edit Division", "Do you Really want to Edit Division", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Division division = new Division();
								division.setId(divisionListGrid.getSelectedRecord().getAttribute(DivisionsListGrid.DIVISION_ID));

								division.setDivisionCode(divisionCodeText.getValueAsString());
								division.setDivisionName(divisionNameText.getValueAsString());
								division.setDivisionDesctiption(divisionDescriptionText.getValueAsString());

								Department department = new Department();
								department.setId(departmentsCombo.getValueAsString());

								division.setDepartment(department);

								if (getUiHandlers() != null) {

									getUiHandlers().editDivisionButtonClicked(division);

								}
							}

						}

					});

				}
			}

		});
		deleteButtonDivision.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (divisionListGrid.getSelectedRecord() == null) {

					SC.warn("Select Divsion Record to Edit");
					divisionListGrid.focus();

				} else {

					SC.ask("Delete Division", "Do you Really want to Delete Division", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Division division = new Division();
								division.setId(divisionListGrid.getSelectedRecord().getAttribute(DivisionsListGrid.DIVISION_ID));

								if (getUiHandlers() != null) {

									getUiHandlers().deleteDivisionButtonClicked(division);

								}
							}

						}

					});

				}
			}

		});
		refreshButtonDivision.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {

					getUiHandlers().retrieveAllDivisionsButtonClicked();
				}
			}

		});
		cancelButtonDivision.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				clearDivisionForm();
			}

		});

		// Section Button Handlers
		saveButtonSect.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (divisionsCombo.getValue() == null) {

					SC.warn("Select Division");
					divisionsCombo.focusInItem();
				} else if (sectionCodeText.getValue() == null) {

					SC.warn("Enter Section Code");
					sectionCodeText.focusInItem();
				} else if (sectionNameText.getValue() == null) {

					SC.warn("Enter Section Name");
					sectionNameText.focusInItem();
				} else if (sectionDescriptionText.getValue() == null) {

					SC.warn("Enter Section Description");
					sectionDescriptionText.focusInItem();
				} else {

					Section section = new Section();
					section.setSectionCode(sectionCodeText.getValueAsString());
					section.setSectionName(sectionNameText.getValueAsString());
					section.setSectionDescription(sectionDescriptionText.getValueAsString());

					Division division = new Division();
					division.setId(divisionsCombo.getValueAsString());

					section.setDivision(division);

					if (getUiHandlers() != null) {

						getUiHandlers().saveSectionButtonClicked(section);

					}

				}

			}
		});

		editButtonSect.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (sectionListGrid.getSelectedRecord() == null) {

					SC.warn("Select Section Record to Edit");
					sectionListGrid.focus();
				} else if (divisionsCombo.getValue() == null) {

					SC.warn("Select Division");
					divisionsCombo.focusInItem();
				} else if (sectionCodeText.getValue() == null) {

					SC.warn("Enter Section Code");
					sectionCodeText.focusInItem();
				} else if (sectionNameText.getValue() == null) {

					SC.warn("Enter Section Name");
					sectionNameText.focusInItem();
				} else if (sectionDescriptionText.getValue() == null) {

					SC.warn("Enter Section Description");
					sectionDescriptionText.focusInItem();
				} else {

					SC.ask("Edit Section", "Do you Really want to Edit Section", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Section section = new Section();
								section.setId(sectionListGrid.getSelectedRecord().getAttribute(SectionsListGrid.SECTIONS_ID));

								section.setSectionCode(sectionCodeText.getValueAsString());
								section.setSectionName(sectionNameText.getValueAsString());
								section.setSectionDescription(sectionDescriptionText.getValueAsString());

								Division division = new Division();
								System.out.println("division==>" + divisionsCombo.getValueAsString());
								division.setId(divisionsCombo.getValueAsString());

								section.setDivision(division);

								if (getUiHandlers() != null) {

									getUiHandlers().editSectionButtonClicked(section);

								}

							}

						}

					});

				}

			}
		});

		deleteButtonSect.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {


				if (sectionListGrid.getSelectedRecord() == null) {

					SC.warn("Select Section Record to Edit");
					sectionListGrid.focus();
				}

				else {

					SC.ask("Delete Section", "Do you Really want to Delete Section", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								Section section = new Section();
								section.setId(sectionListGrid.getSelectedRecord().getAttribute(SectionsListGrid.SECTIONS_ID));

								if (getUiHandlers() != null) {

									getUiHandlers().deleteSectionButtonClicked(section);

								}

							}

						}

					});

				}
			}

		});

		refreshButtonSect.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {

					getUiHandlers().retrieveAllSectionsButtonClicked();
				}
			}

		});

		cancelButtonSect.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				clearSectionForm();

			}

		});

		divisionListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				departmentsCombo.setValue(divisionListGrid.getSelectedRecord().getAttribute(DivisionsListGrid.DEPARTMENT_ID));
				divisionCodeText.setValue(divisionListGrid.getSelectedRecord().getAttribute(DivisionsListGrid.DIVISION_CODE));
				divisionNameText.setValue(divisionListGrid.getSelectedRecord().getAttribute(DivisionsListGrid.DIVISION_NAME));
				divisionDescriptionText.setValue(divisionListGrid.getSelectedRecord().getAttribute(DivisionsListGrid.DIVISION_DESCRIPTION));
			}

		});

		sectionListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				// divisionsCombo,sectionCodeText,sectionNameText,
				// sectionDescriptionText
				divisionsCombo.setValue(sectionListGrid.getSelectedRecord().getAttribute(SectionsListGrid.DIVISION_ID));
				sectionCodeText.setValue(sectionListGrid.getSelectedRecord().getAttribute(SectionsListGrid.SECTION_CODE));
				sectionNameText.setValue(sectionListGrid.getSelectedRecord().getAttribute(SectionsListGrid.SECTIONS_NAME));
				sectionDescriptionText.setValue(sectionListGrid.getSelectedRecord().getAttribute(SectionsListGrid.SECTIONS_DESCRIPTION));

			}

		});

		tabSet.addTabSelectedHandler(new TabSelectedHandler() {

			public void onTabSelected(TabSelectedEvent event) {

				if (event.getTab().getID().contentEquals(sectionsTab.getID())) {

					if (getUiHandlers() != null) {

						getUiHandlers().retrieveAllDivisionsButtonClicked();
					}
				}
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

	public void loadDepartmentsGrid(List<Department> departments) {

		ListGridRecord[] list = new ListGridRecord[departments.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Department department : departments) {
			// System.out.println("User: "+user.getFullName()+": "+user.getRole());

			record = new ListGridRecord();
			record.setAttribute(DepartmentListGrid.ICON, "calender");
			record.setAttribute(DepartmentListGrid.DEPARTMENT_ID, department.getId());
			record.setAttribute(DepartmentListGrid.DEPARTMENT_CODE, department.getDepartmentCode());
			record.setAttribute(DepartmentListGrid.DEPARTMENT_NAME, department.getDepartmentName());
			record.setAttribute(DepartmentListGrid.DEPARTMENT_DESCRIPTION, department.getDepartmentDescription());

			record.setAttribute("emptyField", "");

			if (department.getChangedBy() != null) {

				record.setAttribute(DepartmentListGrid.DEPARTMENT_CHANGED_BY, department.getChangedBy().getFullName());

			} else {

			}

			if (department.getCreatedBy() != null) {

				record.setAttribute(DepartmentListGrid.DEPARTMENT_CREATE_BY, department.getCreatedBy().getFullName());
			} else {

			}
			list[listCount] = record;
			listCount++;

		}
		this.departmentListGrid.setData(list);

	}

	public void clearDepartmentForm() {

		this.DF_DEPARTMENTS.clearValues();

	}

	public void loadDivisionsGrid(List<Division> divisions) {

		ListGridRecord[] list = new ListGridRecord[divisions.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Division division : divisions) {
			// System.out.println("User: "+user.getFullName()+": "+user.getRole());

			record = new ListGridRecord();
			record.setAttribute(DivisionsListGrid.ICON, "calender");
			record.setAttribute(DivisionsListGrid.DEPARTMENT_ID, division.getDepartment().getId());
			record.setAttribute(DivisionsListGrid.DEPARTMENT_NAME, division.getDepartment().getDepartmentName());
			record.setAttribute(DivisionsListGrid.DIVISION_ID, division.getId());
			record.setAttribute(DivisionsListGrid.DIVISION_NAME, division.getDivisionName());
			record.setAttribute(DivisionsListGrid.DIVISION_DESCRIPTION, division.getDivisionDesctiption());
			record.setAttribute(DivisionsListGrid.DIVISION_CODE, division.getDivisionCode());

			record.setAttribute("emptyField", "");

			if (division.getChangedBy() != null) {

				record.setAttribute(DivisionsListGrid.DIVISION_CHANGED_BY, division.getChangedBy().getFullName());

			} else {

			}

			if (division.getCreatedBy() != null) {

				record.setAttribute(DivisionsListGrid.DIVISION_CREATE_BY, division.getCreatedBy().getFullName());
			} else {

			}
			list[listCount] = record;
			listCount++;

		}
		this.divisionListGrid.setData(list);
	}

	public void loadSectionsGrid(List<Section> sections) {

		ListGridRecord[] list = new ListGridRecord[sections.size()];
		int listCount = 0;
		ListGridRecord record;
		for (Section section : sections) {
			// System.out.println("User: "+user.getFullName()+": "+user.getRole());

			record = new ListGridRecord();
			record.setAttribute(SectionsListGrid.ICON, "calender");
			record.setAttribute(SectionsListGrid.SECTIONS_ID, section.getId());
			record.setAttribute(SectionsListGrid.SECTIONS_NAME, section.getSectionName());
			record.setAttribute(SectionsListGrid.SECTIONS_DESCRIPTION, section.getSectionDescription());
			record.setAttribute(SectionsListGrid.SECTION_CODE, section.getSectionCode());
			record.setAttribute(SectionsListGrid.DIVISION_ID, section.getDivision().getId());
			record.setAttribute(SectionsListGrid.DIVISION_NAME, section.getDivision().getDivisionName());

			record.setAttribute("emptyField", "");

			if (section.getChangedBy() != null) {

				record.setAttribute(SectionsListGrid.SECTION_CHANGED_BY, section.getChangedBy().getFullName());

			} else {

			}

			if (section.getCreatedBy() != null) {

				record.setAttribute(SectionsListGrid.SECTION_CREATE_BY, section.getCreatedBy().getFullName());
			} else {

			}
			list[listCount] = record;
			listCount++;

		}
		this.sectionListGrid.setData(list);
	}

	public void loadDepartmentsCombo(List<Department> departments) {

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

		this.departmentsCombo.setOptionDataSource(dataSource);
	}

	public void clearDivisionForm() {

		this.DF_DIVISIONS.clearValues();
	}

	public void clearSectionForm() {

		this.DF_SECTIONS.clearValues();
	}

	public ProcnetSelectItem getDivisionsCombo() {
		return divisionsCombo;
	}

}
