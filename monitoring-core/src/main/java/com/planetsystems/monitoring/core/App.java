package com.planetsystems.monitoring.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.planetsystems.monitoring.core.services.ActivitiesService;
import com.planetsystems.monitoring.core.services.DeliverableService;
import com.planetsystems.monitoring.core.services.GoalsService;
import com.planetsystems.monitoring.core.services.ObjectivesService;
import com.planetsystems.monitoring.core.services.ProjectTitleService;
import com.planetsystems.monitoring.core.services.SpecificObjectivesService;
import com.planetsystems.monitoring.core.services.TasksService;
import com.planetsystems.monitoring.model.ProjectTeam;
import com.planetsystems.monitoring.model.project.Activities;
import com.planetsystems.monitoring.model.project.Deliverable;
import com.planetsystems.monitoring.model.project.Goals;
import com.planetsystems.monitoring.model.project.Objectives;
import com.planetsystems.monitoring.model.project.ProjectTitle;
import com.planetsystems.monitoring.model.project.SpecificObjectives;
import com.planetsystems.monitoring.model.project.Tasks;

public class App {
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"monitoring-application-context.xml");

	@Autowired
	public static void main(String[] args) {

		// UserService userService = ctx.getBean(UserService.class);
		// User user=new User();
		// user.setUsername("admin");
		// user.setPassword("qwerty");
		// List<Role> roles=new ArrayList<Role>();
		// Role role=new Role();
		// role.setName("ADMIN");
		// roles.add(role);
		// user.setRolesForUser(roles);
		// try {
		// userService.saveUser(user);
		//
		//
		// } catch (ValidationFailedException e) {
		// e.printStackTrace();
		// }

		ProjectTitleService service = ctx.getBean(ProjectTitleService.class);

		// ProjectTeamService teamService =
		// ctx.getBean(ProjectTeamService.class);

		ProjectTeam team = new ProjectTeam();
		// ProjectTeam saveteam= teamService.update();

		ProjectTitle project = new ProjectTitle();
		project.setProjectTitle("Coffee processing chain Project");
		project.setProjectTeam(team);

		ProjectTitle savedProject = service.update(project);

		System.out.println("Saved Project Team ID: "
				+ savedProject.getProjectTeam().getId());

//		DocumentsService documentService = ctx.getBean(DocumentsService.class);
//
//		ProjectTitle project = new ProjectTitle();
//		project.setProjectTitle("Walter Reed project");
//
//		ProjectTitle ptitle = service.update(project);
//
//		Documents docs = new Documents("WorkPlan", new Date(), "document",
//				"2 MB", "/workplan.docx", ptitle);
//
//		documentService.save(docs);
//
//		List<Documents> documents = documentService.findAll();
//		for (Documents doc : documents) {
//			System.out.println("Document ID: " + doc.getId() + " FileName: "
//					+ doc.getFileName());
//		}

		//ProjectTitleService service = ctx.getBean(ProjectTitleService.class);
		GoalsService goalService = ctx.getBean(GoalsService.class);
		ObjectivesService objectiveService = ctx
				.getBean(ObjectivesService.class);
		SpecificObjectivesService specificObjectiveService = ctx
				.getBean(SpecificObjectivesService.class);
		ActivitiesService activitiesService = ctx
				.getBean(ActivitiesService.class);
		TasksService tasksService = ctx.getBean(TasksService.class);
		DeliverableService deliverableService = ctx
				.getBean(DeliverableService.class);

		ProjectTitle newProject = new ProjectTitle();
		newProject.setProjectTitle("Malaria Eradication");

		Objectives objectives = new Objectives();
		objectives
				.setObjectiveName("To provide nets to all mothers and infants");

		Goals goal = new Goals();
		goal.setGoalName("To drop the infant mortality rate by 20 percent");

		SpecificObjectives specificObjective = new SpecificObjectives();
		specificObjective
				.setSpecificObjective("To procure ten thousand insecticide treated nets from Europe");

		Activities activity = new Activities();
		activity.setActivityName("Publish a solicitation document");

		Tasks task = new Tasks();

		task.setTaskName("Write a solicitation document and identify good media house to advertise it");

		Deliverable deliverable = new Deliverable();
		deliverable
				.setDeliverableName("Insecticide treated net procument solicitation document advert in the papers");

		ProjectTitle projectTitle = service.update(project);
		objectives.setProject(projectTitle);
		goal.setProject(projectTitle);

		Objectives objective = objectiveService.update(objectives);
		goalService.save(goal);

		specificObjective.setObjective(objective);

		SpecificObjectives specificObj = specificObjectiveService
				.update(specificObjective);

		activity.setSpecificObjective(specificObj);

		Activities activ = activitiesService.update(activity);
		task.setActivity(activ);

		Tasks tasks = tasksService.update(task);

		deliverable.setTask(tasks);

		deliverableService.save(deliverable);

		List<ProjectTitle> projects = service.findAll();

		System.out.println("This was executed");

		for (ProjectTitle proj : projects) {
			System.out.println("Project ID: " + proj.getId()
					+ " Project Tile: " + proj.getProjectTitle());
		}

	}
}
