package com.planetsystems.monitoring.core.services;

import java.util.List;
import com.planetsystems.monitoring.model.project.Deliverable;


public interface DeliverableService {
	
	public boolean save(Deliverable deliverable);
	

	public boolean edit(Deliverable deliverable);

	public boolean delete(Deliverable deliverable);

	public boolean delete(String deliverableId);

	public Deliverable find(String deliverableId);
	
	public Deliverable update(Deliverable deliverable);

	public List<Deliverable> findAll();
	
	public List<Deliverable> findDeliverableByTaskId(String taskId);
}