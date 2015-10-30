package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;
import com.planetsystems.monitoring.core.daos.QuestionnaireDAO;
import com.planetsystems.monitoring.model.Questionnaire;



@Repository("QuestionnaireDAO")
public class QuestionnaireDAOImpl extends BaseDaoImpl<Questionnaire> implements
		QuestionnaireDAO {

}
