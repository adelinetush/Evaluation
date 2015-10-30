package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.DepartmentDAO;
import com.planetsystems.monitoring.model.units.Department;


@Repository("DepartmentDAO")
public class DepartmentDAOImpl extends BaseDaoImpl<Department> implements
		DepartmentDAO {

}
