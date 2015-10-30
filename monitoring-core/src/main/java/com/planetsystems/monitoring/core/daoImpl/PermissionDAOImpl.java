package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.PermissionDAO;
import com.planetsystems.monitoring.model.Permission;



@Repository("PermissionDAO")
public class PermissionDAOImpl extends BaseDaoImpl<Permission> implements
		PermissionDAO {

}
