package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.QuestionDAO;
import com.planetsystems.monitoring.model.Question;

@Repository("QuestionDAO")
public class QuestionDAOImpl extends BaseDaoImpl<Question> implements
QuestionDAO {

}
