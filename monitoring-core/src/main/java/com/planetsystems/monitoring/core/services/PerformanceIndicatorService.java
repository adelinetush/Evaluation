package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.PerformanceIndicator;

public interface PerformanceIndicatorService {
	public boolean save(PerformanceIndicator performanceIndicator);

	public boolean edit(PerformanceIndicator performanceIndicator);

	public boolean delete(PerformanceIndicator performanceIndicator);

	public boolean delete(String performanceIndicatorId);

	public PerformanceIndicator find(String performanceIndicatorId);
	
	public PerformanceIndicator update(PerformanceIndicator performanceIndicator);

	public List<PerformanceIndicator> findAll();
	
	
	public List<PerformanceIndicator> findPerformanceIndicatorById(PerformanceIndicator performanceIndicator);
	

}
