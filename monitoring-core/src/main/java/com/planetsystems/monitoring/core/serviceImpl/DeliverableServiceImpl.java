package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.DeliverableDAO;
import com.planetsystems.monitoring.core.services.DeliverableService;
import com.planetsystems.monitoring.model.project.Deliverable;
import com.planetsystems.monitoring.model.project.Tasks;

@Service("DeliverableService")
@Transactional
public class DeliverableServiceImpl implements DeliverableService {

	@Autowired
	private DeliverableDAO deliverableDao;

	@Transactional
	public boolean save(Deliverable deliverable) {
		try {
			if (deliverableExists(deliverable.getId())) {
				System.out.println("Deliverable Already exists");
			} else {
				System.out.println("Object to save: " + deliverable.getId());
				deliverableDao.save(deliverable);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Deliverable deliverable) {
		try {
			deliverableDao.update(deliverable);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Deliverable deliverable) {
		try {
			deliverableDao.delete(deliverable);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	// public boolean delete(String deliverableId) {
	// try {
	// deliverableDao.deleteById(deliverableId);
	// return true;
	//
	// } catch (Exception ex) {
	// System.out.println(ex.getMessage());
	// }
	// return false;
	// }

	public Deliverable find(String deliverableId) {
		try {

			Deliverable rawDeliverable= deliverableDao.find(deliverableId);
			
			
			if(rawDeliverable !=null){
				Deliverable deliverable = new Deliverable();

				Tasks task = new Tasks();

				task.setId(rawDeliverable.getTask().getId());

				deliverable.setDeliverableName(rawDeliverable
						.getDeliverableName());

				deliverable.setId(rawDeliverable.getId());
				deliverable.setTask(task);
				
				return deliverable;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Deliverable> findAll() {
		try {

	List<Deliverable> rawDeliverables= deliverableDao.findAll();
			
			List<Deliverable> deliverables = new ArrayList<Deliverable>();

			if (rawDeliverables != null) {

				for (Deliverable rawDeliverable : rawDeliverables) {

					Deliverable deliverable = new Deliverable();

					Tasks task = new Tasks();

					task.setId(rawDeliverable.getTask().getId());

					deliverable.setDeliverableName(rawDeliverable
							.getDeliverableName());

					deliverable.setId(rawDeliverable.getId());
					deliverable.setTask(task);

					deliverables.add(deliverable);

				}

			}

			return deliverables;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean deliverableExists(String deliverableId) {
		try {
			for (Deliverable bid : deliverableDao.findAll()) {
				if (bid.getId().equalsIgnoreCase(deliverableId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Deliverable update(Deliverable newDeliverable) {
		try {
			if (deliverableExists(newDeliverable.getId())) {
				System.out.println("Deliverable Already exists");
			} else {

			Deliverable rawDeliverable=	deliverableDao.update(newDeliverable);
			
			if(rawDeliverable !=null){
				Deliverable deliverable = new Deliverable();

				Tasks task = new Tasks();

				task.setId(rawDeliverable.getTask().getId());

				deliverable.setDeliverableName(rawDeliverable
						.getDeliverableName());

				deliverable.setId(rawDeliverable.getId());
				deliverable.setTask(task);
				
				return deliverable;
			}

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public List<Deliverable> findDeliverableByTaskId(String taskId) {
		Search search = new Search();
		search.addFilterEqual("task.id", taskId);
		List<Deliverable> rawDeliverables = deliverableDao.search(search);

		List<Deliverable> deliverables = new ArrayList<Deliverable>();

		if (rawDeliverables != null) {

			for (Deliverable rawDeliverable : rawDeliverables) {

				Deliverable deliverable = new Deliverable();

				Tasks task = new Tasks();

				task.setId(rawDeliverable.getTask().getId());

				deliverable.setDeliverableName(rawDeliverable
						.getDeliverableName());

				deliverable.setId(rawDeliverable.getId());
				deliverable.setTask(task);

				deliverables.add(deliverable);

			}

		}

		return deliverables;
	}

	public boolean delete(String deliverableId) {
		// TODO Auto-generated method stub
		return false;
	}

}
