package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.PerformanceIndicatorDAO;
import com.planetsystems.monitoring.model.PerformanceIndicator;



@Repository("PerformanceIndicatorDAO")
public class PerformanceIndicatorDAOImpl extends BaseDaoImpl<PerformanceIndicator> implements
PerformanceIndicatorDAO {

}
