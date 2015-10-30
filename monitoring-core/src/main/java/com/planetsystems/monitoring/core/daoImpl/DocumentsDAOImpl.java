package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;


import com.planetsystems.monitoring.core.daos.DocumentsDAO;
import com.planetsystems.monitoring.model.project.Documents;

@Repository("DocumentsDao")
public class DocumentsDAOImpl extends BaseDaoImpl<Documents> implements DocumentsDAO {
	
}

