package com.planetsystems.monitoring.client.presenters;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.utils.Constants;
import com.planetsystems.monitoring.client.views.handlers.MainPageUiHandlers;
import com.planetsystems.monitoring.client.views.project.TreeNodeRecord;
import com.planetsystems.monitoring.client.widgets.ApplicationMenu;
import com.planetsystems.monitoring.client.widgets.MainContextPane;
import com.planetsystems.monitoring.client.widgets.Masthead;
import com.planetsystems.monitoring.client.widgets.NavigationPane;
import com.planetsystems.monitoring.client.widgets.NavigationPaneHeader;
import com.planetsystems.monitoring.model.UserCatergory;
import com.planetsystems.monitoring.model.project.Documents;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.shared.MonitoringAction;
import com.planetsystems.monitoring.shared.MonitoringResult;
import com.planetsystems.monitoring.shared.dispatch.users.UsersAction;
import com.planetsystems.monitoring.shared.dispatch.users.UsersResult;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.Tree;

public class ProjectManagerPresenter
		extends
		Presenter<ProjectManagerPresenter.MyView, ProjectManagerPresenter.MyProxy>
		implements MainPageUiHandlers {

	DispatchAsync dispatcher;

	private static NavigationPaneHeader navigationPaneHeader;
	private static NavigationPane navigationPane;

	public static Department userDepartment = new Department();
	public static Division userDivision = new Division();

	public interface MyView extends View, HasUiHandlers<MainPageUiHandlers> {

		NavigationPaneHeader getNavigationPaneHeader();

		public NavigationPane getNavigationPane();

		public MainContextPane getMainContextPane();

		// void setLoggedInUser(User user);
		void setGetLoggedInUserError(String serverErrorResponse);

		Masthead getMastHead();

		ApplicationMenu getApplicationMenu();

		// void setUserPermissions(List<Permission> permissions);

		VLayout getWestLayout();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.projectmanager)
	public interface MyProxy extends ProxyPlace<ProjectManagerPresenter> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

	@Inject
	PlaceManager manager;

	@Inject
	public ProjectManagerPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		// getView().setUiHandlers(this);
		this.dispatcher = dispatcher;

		ProjectManagerPresenter.navigationPaneHeader = view
				.getNavigationPaneHeader();
		ProjectManagerPresenter.navigationPane = view.getNavigationPane();
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		onclickMenu();

		dispatcher.execute(new MonitoringAction(Constants.ALL_PROJECTS),
				new AsyncCallback<MonitoringResult>() {

					public void onFailure(Throwable error) {

					}

					public void onSuccess(MonitoringResult result) {
						List<ProjectTitle> projects = result.getProjects();

						getView().getMainContextPane().getProjectsGrid()
								.addRecordsToGrid(projects);

					}

				});

		dispatcher.execute(new MonitoringAction(Constants.ALL_DOCUMENTS),
				new AsyncCallback<MonitoringResult>() {

					public void onFailure(Throwable error) {

					}

					public void onSuccess(MonitoringResult result) {
						List<Documents> documents = result.getDocuments();

						List<ProjectTitle> folders = new ArrayList<ProjectTitle>();

						HashMap<ProjectTitle, List<Documents>> files = new HashMap<ProjectTitle, List<Documents>>();

						if (documents != null) {

							for (int i = 0; i < documents.size(); i++) {

								ProjectTitle ptitle = documents.get(i)
										.getProject();

								List<Documents> docs = new ArrayList<Documents>();

								for (Documents doc : documents) {
									if (doc.getProject().equals(ptitle)) {
										docs.add(doc);
									}

								}
								folders.add(ptitle);
								files.put(documents.get(i).getProject(), docs);

							}

							Tree tree = updateDocumentsData(folders, files);

							if (tree != null) {
								getView().getMainContextPane()
										.getDocumentsGrid().setData(tree);
							}

						}
					}

				});

	}

	public class NavigationPaneClickHandler implements RecordClickHandler {
		public void onRecordClick(RecordClickEvent event) {

			Record record = event.getRecord();
			String name = record.getAttributeAsString("name");

			

		}

		public class ApplicationMenuClickHandler implements ClickHandler {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.smartgwt.client.widgets.menu.events.ClickHandler#onClick(
			 * com.smartgwt.client.widgets.menu.events.MenuItemClickEvent)
			 */
			public void onClick(MenuItemClickEvent event) {
				String applicationName = event.getItem().getTitle();
				// SC.say("You clicked: " + applicationName);

				if (applicationName.contains("Log Out")) {

				}
			}

		}

	}

	public void onNavigationPaneSectionHeaderClicked(String name) {
		SC.confirm("Navigation Pane header: " + name, null);
	}

	public void onNavigationPaneSectionClicked(String name) {
		SC.confirm("Navigation Pane Section: " + name, null);
	}

	public void onNewProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	private void onclickMenu() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new RecordClickHandler() {
					public void onRecordClick(RecordClickEvent event) {
						Record record = event.getRecord();
						String name = record.getAttributeAsString("name");
						PlaceRequest req = new PlaceRequest(name);
						manager.revealPlace(req);

					}
				});

		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().SettingsStackSectionName(),
				new RecordClickHandler() {
					public void onRecordClick(RecordClickEvent event) {
						
						Record record = event.getRecord();
						String name = record.getAttributeAsString("name");
						PlaceRequest req = new PlaceRequest(name);
						manager.revealPlace(req);

					}
				});

		getView().getMainContextPane().getProjectsGrid()
				.addSelectionChangedHandler(new SelectionChangedHandler() {

					public void onSelectionChanged(SelectionEvent event) {
						ListGridRecord record = getView().getMainContextPane()
								.getProjectsGrid().getSelectedRecord();
						PlaceRequest request = new PlaceRequest(
								NameTokens.projectdashboard).with("title",
								record.getAttribute("projectName"));
						manager.revealPlace(request);
					}
				});
	}

	public void onCurrentProjectsClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onClosedProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onStakeholdersClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onTeamInterationClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onApprovedProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onRejectedProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onSuspendedProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onPendingProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	public void onOngoingProjectClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().ProjectsStackSectionName(),
				new NavigationPaneClickHandler());
	}

	private Tree updateDocumentsData(List<ProjectTitle> folders,
			HashMap<ProjectTitle, List<Documents>> documents) {

		Tree tree = new Tree();
		if (folders != null) {

			TreeNodeRecord[] records = new TreeNodeRecord[folders.size()];

			for (int i = 0; i < folders.size(); i++) {

				ProjectTitle project = folders.get(i);
				records[i] = new TreeNodeRecord(project.getId(),
						project.getProjectTitle(), "Folder", "20MB", "Kizito",
						new Date(), new Date());

				if (documents != null) {

					if (documents.containsKey(project)) {

						List<Documents> files = documents.get(project);

						if (files != null) {

							TreeNodeRecord[] leafs = new TreeNodeRecord[files
									.size()];

							for (int j = 0; j < files.size(); j++) {

								leafs[j] = new TreeNodeRecord(files.get(j)
										.getId(), files.get(j).getFileName(),
										"Folder", "5MB", "Kizito", new Date(),
										new Date());
							}
							records[i].setChildren(leafs);
						}
					}
				}
			}
			tree.setData(records);
		}
		return tree;
	}

	public void onCreateUserClickHandler() {
		getView().getNavigationPane().addRecordClickHandler(
				Monitoring.getConstants().SettingsStackSectionName(),
				new NavigationPaneClickHandler());

	}

	public void onGetLoggedInUser() {

		dispatcher.execute(new UsersAction(NameTokens.getLoggedInUser,
				NameTokens.users), new AsyncCallback<UsersResult>() {

			public void onFailure(Throwable caught) {

				getView().setGetLoggedInUserError(caught.getLocalizedMessage());

			}

			public void onSuccess(UsersResult result) {

				if (result.isOperationStatus() == true) {

					if (result.getUser().getUserCatergory()
							.compareTo(UserCatergory.STAFF) == 0) {

						userDepartment.setId(result.getUser().getDepartment()
								.getId());
						userDepartment.setDepartmentCode(result.getUser()
								.getDepartment().getDepartmentCode());
						userDepartment.setDepartmentName(result.getUser()
								.getDepartment().getDepartmentName());

						userDivision.setId(result.getUser().getDivision()
								.getId());
						userDivision.setDivisionCode(result.getUser()
								.getDivision().getDivisionCode());
						userDivision.setDivisionName(result.getUser()
								.getDivision().getDivisionName());

						getView().getMastHead().setSignedInUserLabelContents(
								result.getUser().getFullName() + " "
										+ userDepartment.getDepartmentName());

					} else {

						userDepartment.setId(result.getUser()
								.getUserCatergory().toString());
						userDepartment.setDepartmentCode(result.getUser()
								.getUserCatergory().toString());
						userDepartment.setDepartmentName(result.getUser()
								.getUserCatergory().toString());

						getView().getMastHead().setSignedInUserLabelContents(
								result.getUser().getFullName() + " "
										+ userDepartment.getDepartmentName());
					}

					// Log.info("Set Logged In User Permissions");
					// getView().setUserPermissions(result.getUser().getPermissions());

				} else {

					getView().setGetLoggedInUserError(
							result.getServerResponse());

				}

			}

		});
	}

	public void onShowUserTaskClickHandler() {

		PlaceRequest placeRequest = new PlaceRequest(NameTokens.tasks);
		manager.revealPlace(placeRequest);

	}

}