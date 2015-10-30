package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.ActivityDAO;
import com.planetsystems.monitoring.model.budget.Activity;



@Repository("ActivityDAO")
public class ActivityDAOImpl extends BaseDaoImpl<Activity> implements
		ActivityDAO {

}
