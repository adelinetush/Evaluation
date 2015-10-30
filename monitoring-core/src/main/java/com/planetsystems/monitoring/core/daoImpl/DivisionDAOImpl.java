package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.DivisionDAO;
import com.planetsystems.monitoring.model.units.Division;


@Repository("DivisionDAO")
public class DivisionDAOImpl extends BaseDaoImpl<Division> implements
		DivisionDAO {

}
