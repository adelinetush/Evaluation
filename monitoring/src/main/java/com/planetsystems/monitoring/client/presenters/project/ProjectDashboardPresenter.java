package com.planetsystems.monitoring.client.presenters.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.listgrids.ObjectiveListGrid;
import com.planetsystems.monitoring.client.listgrids.SpecificObjectiveGrid;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.utils.Constants;
import com.planetsystems.monitoring.client.views.project.ProjectTeamView;
import com.planetsystems.monitoring.client.widgets.ProjectProperties;
import com.planetsystems.monitoring.client.windows.KPIWindow;
import com.planetsystems.monitoring.client.windows.MemberProfileWindow;
import com.planetsystems.monitoring.client.windows.NewActivityWindow;
import com.planetsystems.monitoring.client.windows.NewSpecificObjectiveWindow;
import com.planetsystems.monitoring.client.windows.TaskPopUpWindow;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.project.Activities;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.project.SpecificObjectives;
import com.planetsystems.monitoring.model.project.TeamMember;
import com.planetsystems.monitoring.shared.MonitoringAction;
import com.planetsystems.monitoring.shared.MonitoringResult;
import com.planetsystems.monitoring.shared.dispatch.users.UsersAction;
import com.planetsystems.monitoring.shared.dispatch.users.UsersResult;






import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordExpandEvent;
import com.smartgwt.client.widgets.grid.events.RecordExpandHandler;

public class ProjectDashboardPresenter
		extends
		Presenter<ProjectDashboardPresenter.MyView, ProjectDashboardPresenter.MyProxy> {

	DispatchAsync dispatcher;

	public interface MyView extends View {

		public SpecificObjectiveGrid getSpecificObjectiveList();

		public Label getTitleLabel();

		public ProjectProperties getProjectProperties();

		public ProjectTeamView getProjectTeam();

		public ObjectiveListGrid getObjectiveListGrid();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.projectdashboard)
	public interface MyProxy extends ProxyPlace<ProjectDashboardPresenter> {
	}

	@Inject
	public ProjectDashboardPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {

		RevealContentEvent.fire(this,
				ProjectManagerPresenter.TYPE_SetContextAreaContent, this);

	}

	@Override
	protected void onBind() {
		super.onBind();

	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String title = request.getParameter("title", "No Specific Objective");
		String projectId = request.getParameter("projectId", null);

		getView().getTitleLabel().setContents(title);

		retrieveProjectDetails(projectId, title);

	}

	private void clickListeners(final String projectId) {

		getView().getProjectTeam().getSaveButton()
				.addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {
						String employeeId = getView().getProjectTeam()
								.getMemberCreationForm()
								.getValueAsString("employeeId");

						String position = getView().getProjectTeam()
								.getMemberCreationForm()
								.getValueAsString("position");

						String userId = getView().getProjectTeam()
								.getSelectMember().getValueAsString();
						System.out.println("Member ID: " + employeeId
								+ " Position: " + position + " USER ID: "
								+ userId);

						if (employeeId != null && position != null) {
							TeamMember member = new TeamMember();
							User user = new User();
							user.setId(userId);

							member.setPosition(position);
							member.setEmployeeId(employeeId);

							dispatcher.execute(new MonitoringAction(
									Constants.SAVE_TEAM_MEMBER_OPERATION,
									member, user, projectId),
									new AsyncCallback<MonitoringResult>() {

										public void onFailure(Throwable failure) {
											failure.printStackTrace();
										}

										public void onSuccess(
												MonitoringResult result) {
											if (result != null) {
												getView()
														.getProjectTeam()
														.getProjectListGrid()
														.addRowToGrid(
																result.getMember());

											} else {
												System.out
														.println("No member with this email address. Are you sure the member is a registered user?");
											}

										}

									});

						} else {
							SC.say("Please Enter all the Fields");

						}
					}
				});


		getView().getSpecificObjectiveList().getActivityListGrid()
				.getEditActivityButton().addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {

					}
				});
		getView().getSpecificObjectiveList().getActivityListGrid()
				.getDeleteActivityButton().addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {

					}
				});
		// getView().getSpecificObjectiveList().getActivityListGrid().getDetailsButton()
		// .addClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent event) {
		//
		// }
		// });
	}

	public void retrieveProjectDetails(String projectId, String projectName) {
		if (projectId != null) {
			final ProjectTitle project = new ProjectTitle();
			project.setId(projectId);
			project.setProjectTitle(projectName);

			dispatcher.execute(new MonitoringAction(Constants.PROJECT_DETAILS,
					project), new AsyncCallback<MonitoringResult>() {

				public void onFailure(Throwable error) {
					error.printStackTrace();
				}

				public void onSuccess(MonitoringResult result) {

					final List<Objectives> objectives = result
							.getObjectiveList();
					project.setProjectObjectives(objectives);
					project.setProjectGoals(result.getGoalList());

					List<ProjectTitle> projects = new ArrayList<ProjectTitle>();
					projects.add(project);

					final List<SpecificObjectives> specificObjs = new ArrayList<SpecificObjectives>();

					for (Objectives obj : objectives) {
						for (SpecificObjectives specs : obj
								.getSpecificObjectives()) {
							specificObjs.add(specs);
						}
					}
					getView().getProjectProperties().addRecordsToViewer(
							projects); // Project properties

					getView().getObjectiveListGrid().addRecordsToGrid(
							objectives); // Adding objectives to list grid

					getView().getObjectiveListGrid().addRecordExpandHandler(
							new RecordExpandHandler() {

								public void onRecordExpand(
										RecordExpandEvent event) {
									ListGridRecord record = getView()
											.getObjectiveListGrid()
											.getSelectedRecord();

									for (final Objectives objs : objectives) {
										final List<SpecificObjectives> specs = objs
												.getSpecificObjectives();

										if (objs.getId()
												.equalsIgnoreCase(
														record.getAttribute("objectiveId"))) {
											getView()
													.getObjectiveListGrid()
													.getSpecificObjectiveDetailGrid()
													.addSpecificObjectiveRecordsToGrid(
															objs.getSpecificObjectives());

											getView()
													.getObjectiveListGrid()
													.getSpecificObjectiveDetailGrid()
													.getAddSpecificObjectiveButton()
													.addClickHandler(
															new ClickHandler() {

																public void onClick(
																		ClickEvent event) {
																	NewSpecificObjectiveWindow specWindow = new NewSpecificObjectiveWindow();
																	specWindow
																			.show();

																}
															});

											getView()
													.getObjectiveListGrid()
													.getSpecificObjectiveDetailGrid()
													.getAddKpiButton()
													.addClickHandler(
															new ClickHandler() {

																public void onClick(
																		ClickEvent event) {

																	KPIWindow kpiWindow = new KPIWindow(
																	
																			objs.getId());

																	kpiWindow.getQuestionGrid().updateData();
																	kpiWindow
																			.show();
																	
																
																}
															});

											getView()
													.getObjectiveListGrid()
													.getSpecificObjectiveDetailGrid()
													.getSpecificObjectiveGrid()

													.addDoubleClickHandler(
															new DoubleClickHandler() {

																public void onDoubleClick(
																		DoubleClickEvent event) {
																	ListGridRecord record = getView()
																			.getObjectiveListGrid()
																			.getSpecificObjectiveDetailGrid()
																			.getSpecificObjectiveGrid()

																			.getSelectedRecord();

																	for (SpecificObjectives spec : specs) {
																		if (spec.getId()
																				.equalsIgnoreCase(
																						record.getAttribute("specificObjectiveId"))) {
																			// TaskPopUpWindow
																			// window
																			// =
																			// new
																			// TaskPopUpWindow();
																			// window.getTaskGrid()
																			// .addTasksRecordsToGrid(
																			// spec.));
																			//
																			// window.getTaskGrid()
																			// .addClickHandler(
																			// new
																			// ClickHandler()
																			// {
																			//
																			// public
																			// void
																			// onClick(
																			// ClickEvent
																			// event)
																			// {
																			//
																			// }
																			// });
																			// window.draw();
																			SC.say(spec
																					.getSpecificObjective());
																			break;
																		}

																	}

																}
															});
										}

										break;
									}
								}
							});

					getView().getSpecificObjectiveList().addRecordsToGrid(
							specificObjs);

					getView().getSpecificObjectiveList()
							.addRecordExpandHandler(new RecordExpandHandler() {

								public void onRecordExpand(
										RecordExpandEvent event) {
									ListGridRecord record = getView()
											.getSpecificObjectiveList()
											.getSelectedRecord();

									for (final SpecificObjectives specs : specificObjs) {

										final List<Activities> activities = specs
												.getActivities();
										if (specs
												.getId()
												.equalsIgnoreCase(
														record.getAttribute("specificObjectiveId"))) {
											getView()
													.getSpecificObjectiveList()
													.getActivityListGrid()
													.addActivityRecordsToGrid(
															specs.getActivities());

											getView()
													.getSpecificObjectiveList()
													.getActivityListGrid()

													.getAddActivityButton()
													.addClickHandler(
															new ClickHandler() {

																public void onClick(
																		ClickEvent event) {

																	NewActivityWindow activityeWindow = new NewActivityWindow(
																			specs.getId());

																	activityeWindow
																			.show();
																}
															});

											getView()
													.getSpecificObjectiveList()
													.getActivityListGrid()
													.getActivityGrid()
													.addDoubleClickHandler(
															new DoubleClickHandler() {

																public void onDoubleClick(
																		DoubleClickEvent event) {
																	ListGridRecord record = getView()
																			.getSpecificObjectiveList()
																			.getActivityListGrid()
																			.getActivityGrid()

																			.getSelectedRecord();

																	for (Activities activity : activities) {
																		if (activity
																				.getId()
																				.equalsIgnoreCase(
																						record.getAttribute("activityId"))) {
																			TaskPopUpWindow window = new TaskPopUpWindow();
																			window.getTaskGrid()
																					.addTasksRecordsToGrid(
																							activity.getTasks());

																			window.getTaskGrid()
																					.addClickHandler(
																							new ClickHandler() {

																								public void onClick(
																										ClickEvent event) {

																								}
																							});
																			window.draw();

																			break;
																		}

																	}

																}
															});
										}

										break;
									}
								}
							});

					getView().getProjectTeam().getProjectListGrid()
							.addRowData(result.getTeamMembers());

					getView().getProjectTeam().getProjectListGrid()
							.addDoubleClickHandler(new DoubleClickHandler() {

								public void onDoubleClick(DoubleClickEvent event) {
									ListGridRecord record = getView()
											.getProjectTeam()
											.getProjectListGrid()
											.getSelectedRecord();

									TeamMember member = new TeamMember();
									member.setId(record
											.getAttribute("memberId"));
									member.setFirstName(record
											.getAttribute("firstName"));
									member.setSurname(record
											.getAttribute("surname"));
									member.setEmployeeId(record
											.getAttribute("employeeId"));
									member.setPosition(record
											.getAttribute("position"));

									MemberProfileWindow profileWindow = new MemberProfileWindow();

									profileWindow.getDetailViewer().addRowData(
											member);

									profileWindow.draw();

								}
							});

					dispatcher.execute(new UsersAction(
							NameTokens.retrieveOperation, NameTokens.users),
							new AsyncCallback<UsersResult>() {
								public void onFailure(Throwable caught) {

								}

								public void onSuccess(UsersResult result) {

									List<User> users = result.getUsers();
									LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
									if (users != null) {
										for (User user : users) {
											valueMap.put(user.getId(),
													user.getEmail());
											System.out.println("User: "
													+ user.getEmail());

										}

										getView().getProjectTeam()
												.getSelectMember()
												.setValueMap(valueMap);
									}
								}
							});

				}

			});

			clickListeners(projectId);
		} else {
			System.out.println("Project Id is null");
		}

		// dispatcher.execute(new MonitoringAction(Constants.USER_DETAILS), new
		// AsyncCallback<MonitoringResult>(){
		//
		// public void onFailure(Throwable fail) {
		// fail.printStackTrace();
		//
		// System.out.println("No data returned");
		// }
		//
		// public void onSuccess(MonitoringResult result) {
		// List<User> users=result.getUserList();
		//
		//
		// System.out.println("Number of available users: "+users.size());
		// }
		//
		// });

	}
}
