package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.VoteDAO;
import com.planetsystems.monitoring.model.Vote;



@Repository("VoteDAO")
public class VoteDAOImpl extends BaseDaoImpl<Vote> implements VoteDAO {

}
