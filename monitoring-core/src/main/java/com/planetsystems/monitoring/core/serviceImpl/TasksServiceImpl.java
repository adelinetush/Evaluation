package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.TasksDAO;
import com.planetsystems.monitoring.core.services.TasksService;
import com.planetsystems.monitoring.model.project.Tasks;

@Service("TasksService")
@Transactional
public class TasksServiceImpl implements TasksService {

	@Autowired
	private TasksDAO tasksDao;

	@Transactional
	public boolean save(Tasks task) {
		try {
			if (taskExists(task.getId())) {
				System.out.println("Tasks Already exists");
			} else {
				System.out.println("Object to save: " + task.getId());
				tasksDao.save(task);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Tasks task) {
		try {
			tasksDao.update(task);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Tasks task) {
		try {
			tasksDao.delete(task);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Tasks find(String teacherId) {
		try {

			Tasks rawTasks = tasksDao.find(teacherId);

			

				return rawTasks;

			

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Tasks> findAll() {
		try {

			List<Tasks> rawTasks = tasksDao.findAll();

			
				return rawTasks;
			

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean taskExists(String tasksId) {
		try {
			for (Tasks bid : tasksDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(tasksId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Tasks update(Tasks tasks) {
		try {
			if (taskExists(tasks.getId())) {
				System.out.println("Tasks Already exists");
			} else {
				Tasks rawTask = tasksDao.update(tasks);

				

				return rawTask;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public List<Tasks> findTasksByActivityId(String activityId) {
		Search search = new Search();
		search.addFilterEqual("activity.id", activityId);
		List<Tasks> rawTasks = tasksDao.search(search);

		

		return rawTasks;
	}

	public boolean delete(String taskId) {
		return false;
	}

}
