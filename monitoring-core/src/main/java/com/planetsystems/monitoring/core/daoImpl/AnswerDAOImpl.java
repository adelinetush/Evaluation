package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.AnswerDAO;
import com.planetsystems.monitoring.model.Answer;



@Repository("AnswerDAO")
public class AnswerDAOImpl extends BaseDaoImpl<Answer> implements
		AnswerDAO {

}
