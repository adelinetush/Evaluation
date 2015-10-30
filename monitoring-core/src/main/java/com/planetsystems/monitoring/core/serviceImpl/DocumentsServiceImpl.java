package com.planetsystems.monitoring.core.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.DocumentsDAO;
import com.planetsystems.monitoring.core.services.DocumentsService;
import com.planetsystems.monitoring.model.project.Documents;
import com.planetsystems.monitoring.model.project.ProjectTitle;

@Service("DocumentsService")
@Transactional
public class DocumentsServiceImpl implements DocumentsService {

	@Autowired
	private DocumentsDAO documentsDao;
	

	
	@Transactional
	public boolean save(Documents documents) {
		try {
			if (documentExists(documents.getId())) {
				System.out.println("Goal Already exists");
			} else {
				System.out.println("Object to save: " + documents.getId());
				documentsDao.save(documents);

				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to sync Data Core:");
		}

		return false;
	}

	public boolean edit(Documents documents) {
		try {
			documentsDao.update(documents);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Documents documents) {
		try {
			documentsDao.delete(documents);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

//	public boolean delete(String goalsId) {
//		try {
//			goalsDao.deleteById(goalsId);
//			return true;
//
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		return false;
//	}

	public Documents find(String documentId) {
		try {

			return documentsDao.find(documentId);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public List<Documents> findAll() {
		try {

			return documentsDao.findAll();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean documentExists(String documentId) {
		try {
			for (Documents document : documentsDao.findAll()) {
				if (document.getId().equalsIgnoreCase(documentId)) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public Documents update(Documents documents) {
		try {
			if (documentExists(documents.getId())) {
				System.out.println("Goal Already exists");
			} else {
			return	documentsDao.update(documents);

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Trying to save data");
		}
		return null;
	}

	public boolean delete(String goalId) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<ProjectTitle> findProjectByProjectId(ProjectTitle projectTitle) {
		Search search=new Search();
		search.addFilterEqual("project.id", projectTitle.getId());
		return documentsDao.search(search);
	}

	

}
