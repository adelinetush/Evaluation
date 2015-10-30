package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.UserDAO;
import com.planetsystems.monitoring.model.User;

@Repository("UserDAO")
public class UserDAOImpl extends BaseDaoImpl<User> implements UserDAO {

}
