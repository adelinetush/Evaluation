package com.planetsystems.monitoring.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.client.utils.Constants;
import com.planetsystems.monitoring.core.services.ActivitiesService;
import com.planetsystems.monitoring.core.services.DeliverableService;
import com.planetsystems.monitoring.core.services.DocumentsService;
import com.planetsystems.monitoring.core.services.GoalsService;
import com.planetsystems.monitoring.core.services.ObjectivesService;
import com.planetsystems.monitoring.core.services.ProjectTeamService;
import com.planetsystems.monitoring.core.services.ProjectTitleService;
import com.planetsystems.monitoring.core.services.SpecificObjectivesService;
import com.planetsystems.monitoring.core.services.TasksService;
import com.planetsystems.monitoring.core.services.TeamMemberService;
import com.planetsystems.monitoring.core.services.UserService;
import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.project.Activities;
import com.planetsystems.monitoring.model.project.Deliverable;
import com.planetsystems.monitoring.model.project.Documents;
import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.project.SpecificObjectives;
import com.planetsystems.monitoring.model.project.Tasks;
import com.planetsystems.monitoring.model.project.TeamMember;
import com.planetsystems.monitoring.shared.MonitoringAction;
import com.planetsystems.monitoring.shared.MonitoringResult;

public class MonitoringActionHandler extends
		AbstractActionHandler<MonitoringAction, MonitoringResult> {

	@Autowired
	private ProjectTitleService projectTitleService;

	@Autowired
	private GoalsService goalService;

	@Autowired
	private ObjectivesService objectivesService;

	@Autowired
	private SpecificObjectivesService specificObjectivesService;

	
	@Autowired
	private ActivitiesService activitiesService;

	@Autowired
	private TasksService taskService;

	@Autowired
	private DeliverableService deliverableService;

	@Autowired
	private DocumentsService documentsService;

	@Autowired
	private UserService userService;

	@Autowired
	private TeamMemberService teamMemberService;

	@Autowired
	private ProjectTeamService projectTeamService;

	public MonitoringActionHandler() {
		super(MonitoringAction.class);
	}

	public MonitoringResult execute(MonitoringAction action,
			ExecutionContext context) throws ActionException {
		if (action != null) {
			if (action.getOperation() == 1) {
				

				ProjectTitle title = new ProjectTitle();
				title.setProjectTitle(action.getProjectTitle());
				ProjectTeam team = new ProjectTeam();
				title.setProjectTeam(team);

				ProjectTitle savedTitle = projectTitleService.update(title);

				
				System.out.println("Saved Project: "+savedTitle.getProjectTitle());
				
				System.out.println("Saved Project ID: "+savedTitle.getId());
				Goals goal = new Goals();
				goal.setProject(savedTitle);
				goal.setGoalName(action.getProjectGoal());
				

				goalService.save(goal);

				List<String> objectives = action.getObjectives();

				HashMap<Integer, List<String>> specificObjectives = action
						.getSpecificObjs();

				for (int i = 0; i < objectives.size(); i++) {

					String object = objectives.get(i);

					Objectives objs = new Objectives();
					objs.setProject(savedTitle);
					objs.setObjectiveName(object);

					Objectives objective = objectivesService.update(objs);

					System.out.println("Objective: " + objective.getId());

					if (specificObjectives != null) {

						if (specificObjectives.containsKey(i)) {

							List<String> children = specificObjectives.get(i);

							for (int j = 0; j < children.size(); j++) {
								System.out.println("Specific Objective: "
										+ children.get(j));
								SpecificObjectives specObjs = new SpecificObjectives();
								specObjs.setObjective(objective);
								specObjs.setSpecificObjective(children.get(j));
								specificObjectivesService.update(specObjs);

							}

						}
					}
				}

				return new MonitoringResult(true);

			} else if (action.getOperation() == Constants.PENDING_PROJECTS) {

				List<ProjectTitle> projects = projectTitleService.findAll();
				List<ProjectTitle> newObject = new ArrayList<ProjectTitle>();

				for (ProjectTitle proj : projects) {
					ProjectTitle title = new ProjectTitle();

					title.setProjectTitle(proj.getProjectTitle());
					title.setId(proj.getId());

					newObject.add(title);
				}

				return new MonitoringResult(newObject);

			} else if (action.getOperation() == Constants.PROJECT_DETAILS) {

				// ProjectTeam projectTeam = new ProjectTeam();

				List<Goals> rawGoals = goalService.findGoalsByProjectId(action
						.getProject().getId());

				List<Objectives> rawObjectives = objectivesService
						.findObjectivesByProjectId(action.getProject().getId());

				List<Goals> goals = new ArrayList<Goals>();

				List<Objectives> objectives = new ArrayList<Objectives>();

				if (rawGoals != null) {
					if (rawGoals.size() != 0) {

						for (Goals rawgoal : rawGoals) {
							Goals goal = new Goals();
							goal.setId(rawgoal.getId());
							goal.setGoalName(rawgoal.getGoalName());
							goals.add(goal);
						}
					}
				}

				if (rawObjectives != null) {
					if (rawObjectives.size() != 0) {

						for (Objectives rawobj : rawObjectives) {

							List<SpecificObjectives> specObjs = new ArrayList<SpecificObjectives>();

							//

							List<SpecificObjectives> rawSpecificObjectives = specificObjectivesService
									.findSpecificObjectivesByObjectiveId(rawobj);

							Objectives obj = new Objectives();
							obj.setSpecificObjectives(specObjs);

							obj.setId(rawobj.getId());
							obj.setObjectiveName(rawobj.getObjectiveName());

							for (SpecificObjectives specObj : rawSpecificObjectives) {

								SpecificObjectives spec = new SpecificObjectives();
								spec.setSpecificObjective(specObj
										.getSpecificObjective());
								spec.setId(specObj.getId());

								List<Activities> rawActivities = activitiesService
										.findActivitiesBySpecificObjectiveId(specObj
												.getId());

								List<Activities> activities = new ArrayList<Activities>();

								for (Activities rawAct : rawActivities) {

									Activities activity = new Activities();
									activity.setActivityName(rawAct
											.getActivityName());
									activity.setId(rawAct.getId());
									activities.add(activity);
									List<Tasks> rawTasks = taskService
											.findTasksByActivityId(rawAct
													.getId());

									List<Tasks> tasksList = new ArrayList<Tasks>();
									for (Tasks rawTask : rawTasks) {

										Tasks task = new Tasks();
										task.setTaskName(rawTask.getTaskName());
										task.setId(rawTask.getId());
										tasksList.add(task);

										List<Deliverable> rawDeliverables = deliverableService
												.findDeliverableByTaskId(rawTask
														.getId());

										for (Deliverable rawdeliverable : rawDeliverables) {

											Deliverable deliverable = new Deliverable();
											deliverable
													.setDeliverableName(rawdeliverable
															.getDeliverableName());

										}

									}

									activity.setTasks(tasksList);

								}

								spec.setActivities(activities);

								specObjs.add(spec);
							}

							obj.setSpecificObjectives(specObjs);

							objectives.add(obj);
						}

					}
				}
				ProjectTitle project = projectTitleService.find(action
						.getProject().getId());
				
				List<TeamMember> rawMembers=teamMemberService.findTeamByTeamId(project.getProjectTeam());
				 
				System.out.println("Team members number: "+ rawMembers.size());
				 List<TeamMember> members=new ArrayList<TeamMember>();
				 
				 for(TeamMember team: rawMembers){
					 ProjectTeam proj=new ProjectTeam();
					// proj.setId(team.getProjectTeam().getId());
					 
					TeamMember member=new TeamMember(proj, team.getFirstName(),
							team.getSurname(), team.getEmployeeId(), team.getPosition(),
							team.getEmailAddress(), team.getPhoneContact());
					
					members.add(member);
				 }
				 System.out.println("Recreated number of team members: "+ members.size());
				return new MonitoringResult(goals, objectives, members);

			} else if (action.getOperation() == Constants.SAVE_TEAM_MEMBER_OPERATION) {

				List<User> users = userService.getUsers();
				User selectedUser = null;

				for (User user : users) {
					if (user.getId().equalsIgnoreCase(action.getUser().getId())) {
						selectedUser = user;

						break;
					}
				}

				if (selectedUser != null) {

					ProjectTitle project = projectTitleService.find(action
							.getProjectTitle());

					TeamMember member = null,newMember=null;
					if (project != null) {
						System.out.println("Selected Project ID: "+ project.getProjectTeam().getId());
						
						member = new TeamMember(project.getProjectTeam(),
								selectedUser.getFirstName(),
								selectedUser.getLastName(), action
										.getTeamMember().getEmployeeId(),
								action.getTeamMember().getPosition(),
								selectedUser.getEmail(),
								selectedUser.getPhoneNumber());

						member = teamMemberService.update(member);
						
							 ProjectTeam proj=new ProjectTeam();
							 proj.setId(member.getProjectTeam().getId());
							 
							newMember=new TeamMember(proj, member.getFirstName(),
									member.getSurname(), member.getEmployeeId(), member.getPosition(),
									member.getEmailAddress(), member.getPhoneContact());
							
						
					} else {
						System.out.println("Project service is null");

					}

					return new MonitoringResult(newMember);

				} else {
					return null;
				}

			} else if (action.getOperation() == Constants.ALL_PROJECTS) {

				List<ProjectTitle> rawProjects = projectTitleService.findAll();
				List<ProjectTitle> projects = new ArrayList<ProjectTitle>();

				for (ProjectTitle rawproject : rawProjects) {
					ProjectTitle ptitle = new ProjectTitle();
					ptitle.setProjectTitle(rawproject.getProjectTitle());
					ptitle.setId(rawproject.getId());
					ptitle.setDateChanged(rawproject.getDateChanged());
					ptitle.setDateCreated(rawproject.getDateCreated());

					projects.add(ptitle);
				}

				return new MonitoringResult(projects);

			} else if (action.getOperation() == Constants.ALL_DOCUMENTS) {

				List<Documents> rawDocs = documentsService.findAll();

				List<Documents> documents = new ArrayList<Documents>();

				for (Documents docs : rawDocs) {
					List<ProjectTitle> rawProjects = projectTitleService
							.findProjectsByProjectId(docs.getProject());
					if (rawProjects != null) {
						ProjectTitle rawProject = rawProjects.get(0);
						ProjectTitle project = new ProjectTitle();
						project.setId(rawProject.getId());
						project.setProjectTitle(rawProject.getProjectTitle());

						Documents doc = new Documents(docs.getFileName(),
								docs.getDateModified(), docs.getFileType(),
								docs.getFileSize(), docs.getFilePath(), project);

						doc.setId(docs.getId());

						documents.add(doc);

					}

				}

				return new MonitoringResult(documents, null);

			}
		}
		return null;
	}

	public void undo(MonitoringAction action, MonitoringResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<MonitoringAction> getActionType() {
		return MonitoringAction.class;
	}
}
