package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.TasksDAO;
import com.planetsystems.monitoring.model.project.Tasks;

@Repository("TasksDao")
public class TasksDAOImpl extends BaseDaoImpl<Tasks> implements TasksDAO {
	
}
