package com.planetsystems.monitoring.core.daoImpl;

import org.springframework.stereotype.Repository;

import com.planetsystems.monitoring.core.daos.SectionDAO;
import com.planetsystems.monitoring.model.units.Section;

@Repository("SectionDAO")
public class SectionDAOImpl extends BaseDaoImpl<Section> implements SectionDAO {

}
