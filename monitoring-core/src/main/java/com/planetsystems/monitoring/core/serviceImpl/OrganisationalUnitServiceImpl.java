package com.planetsystems.monitoring.core.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.planetsystems.monitoring.core.daos.DepartmentDAO;
import com.planetsystems.monitoring.core.daos.DivisionDAO;
import com.planetsystems.monitoring.core.daos.SectionDAO;
import com.planetsystems.monitoring.core.daos.VoteDAO;
import com.planetsystems.monitoring.core.services.AuditlogService;
import com.planetsystems.monitoring.core.services.OrganisationalUnitService;
import com.planetsystems.monitoring.model.RecordStatus;
import com.planetsystems.monitoring.model.Vote;
import com.planetsystems.monitoring.model.audit.ActionStatus;
import com.planetsystems.monitoring.model.audit.Operations;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;

@Service("organisationalUnitService")
public class OrganisationalUnitServiceImpl implements OrganisationalUnitService {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private DivisionDAO divisionDAO;

	@Autowired
	private SectionDAO sectionDAO;

	@Autowired
	private AuditlogService auditlogService;

	@Autowired
	private VoteDAO voteDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public Department saveDepartment(Department department) throws ValidationFailedException, SessionExpiredException {
		validateDepartment(department);
		checkifDepartmentNameExists(department);
		try {
			return departmentDAO.save(department);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Department");
		}

	}

	public void validateDepartment(Department department) throws ValidationFailedException, SessionExpiredException {
		if (department.getDepartmentCode() == null) {
			try {
				throw new ValidationFailedException("Department Code is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
			}
		}
		if (department.getDepartmentName() == null) {
			try {
				throw new ValidationFailedException("Department Name is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
			}
		}
		if (department.getDepartmentDescription() == null) {
			try {
				throw new ValidationFailedException("Department Description is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
			}
		}

	}

	public void checkifDepartmentNameExists(Department department) throws ValidationFailedException, SessionExpiredException {
		for (Department dept : getAllDepartments()) {
			if (department.getDepartmentName().contentEquals(dept.getDepartmentName())) {
				try {
					throw new ValidationFailedException("Department with the name " + department.getDepartmentName() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
				}
			}

			if (department.getDepartmentCode().contentEquals(dept.getDepartmentCode())) {
				try {
					throw new ValidationFailedException("Department with the code " + department.getDepartmentCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
				}
			}

			if (department.getDepartmentDescription().contentEquals(dept.getDepartmentDescription())) {
				try {
					throw new ValidationFailedException("Department with the Description " + department.getDepartmentCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
				}
			}

		}
	}

	@Transactional(readOnly = true)
	public Department getDepartmentById(String id) {
		return departmentDAO.searchUniqueByPropertyEqual("id", id);
	}

	@Transactional(readOnly = true)
	public Department getDepartmentByCode(String code) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("departmentCode", code);
		return departmentDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public Department getDepartmentByName(String name) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("departmentName", name);
		return departmentDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public Division getDivisionByCode(String code) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("divisionCode", code);
		return divisionDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public Division getDivisionByName(String name) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("divisionName", name);
		return divisionDAO.searchUnique(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Department> getAllDepartments() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return departmentDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Department> getAllDepartmentsBy() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("departmentCode", "112");
		Date curDate = new Date();
		long ret = 0;
		for (Department depart : this.getAllDepartments()) {
			List<Date> dates = new ArrayList<Date>();
			dates.add(depart.getDateCreated());

			for (Date d : dates) {
				if (Math.abs(curDate.getTime() - ret) > Math.abs(curDate.getTime() - d.getTime())) {
					ret = d.getTime();
				}
			}

		}
		search.addFilterEqual("dateCreated", new Date(ret));
		return departmentDAO.searchUnique(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Department editDepartment(Department department) throws ValidationFailedException, SessionExpiredException {
		validateDepartment(department);
		if (getDepartmentByName(department.getDepartmentName()) != null) {

			if (department.getId().contentEquals(getDepartmentByName(department.getDepartmentName()).getId()) == false) {
				if (department.getDepartmentName().contentEquals(getDepartmentByName(department.getDepartmentName()).getDepartmentName()) == true)
					throw new ValidationFailedException("Unit with the Name " + department.getDepartmentName() + " already exists");
			}
		}

		if (getDepartmentByCode(department.getDepartmentCode()) != null) {

			if (department.getId().contentEquals(getDepartmentByCode(department.getDepartmentCode()).getId()) == false) {
				if (department.getDepartmentCode().contentEquals(getDepartmentByCode(department.getDepartmentCode()).getDepartmentCode()) == true)
					throw new ValidationFailedException("Unit with the Code " + department.getDepartmentCode() + " already exists");
			}

		}

		try {
			return departmentDAO.save(department);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Department");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDepartment(Department department) throws SessionExpiredException, ValidationFailedException {
		if (isDepartmentDeletable(department) == false) {
			try {
				throw new ValidationFailedException("Department is already referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Department");
			}
		}

		try {
			departmentDAO.remove(department);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Department");
		}
	}

	public boolean isDepartmentDeletable(Department department) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("department", department);
		if (divisionDAO.count(search) > 0)
			return false;
		else
			return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Division saveDivision(Division division) throws ValidationFailedException, SessionExpiredException {
		validateDivision(division);
		checkifDivisionExists(division);
		try {
			return divisionDAO.save(division);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Department");
		}
	}

	@Transactional(readOnly = true)
	public Division getDivisionById(String id) {
		return divisionDAO.searchUniqueByPropertyEqual("id", id);
	}

	public void validateDivision(Division division) throws ValidationFailedException, SessionExpiredException {
		if (division.getDivisionCode() == null) {
			try {
				throw new ValidationFailedException("Division Code is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Division");
			}
		}
		if (division.getDivisionName() == null) {
			try {
				throw new ValidationFailedException("Division Name is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Division");
			}
		}
		if (division.getDivisionDesctiption() == null) {
			try {
				throw new ValidationFailedException("Division Description is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Division");
			}
		}

	}

	public void checkifDivisionExists(Division division) throws ValidationFailedException, SessionExpiredException {
		for (Division div : getAllDivisions()) {
			if (division.getDivisionName().contentEquals(div.getDivisionName())) {
				try {
					throw new ValidationFailedException("Division with the name " + division.getDivisionName() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Division");
				}
			}

			if (division.getDivisionCode().contentEquals(div.getDivisionCode())) {
				try {
					throw new ValidationFailedException("Division with the code " + division.getDivisionCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Division");
				}
			}

			if (division.getDivisionDesctiption().contentEquals(div.getDivisionDesctiption())) {
				try {
					throw new ValidationFailedException("Division with the Description " + division.getDivisionCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Division");
				}
			}

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Division> getAllDivisions() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return divisionDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Division editDivision(Division division) throws ValidationFailedException, SessionExpiredException {
		validateDivision(division);

		if (getDivisionByName(division.getDivisionName()) != null) {
			if (division.getId().contentEquals(getDivisionByName(division.getDivisionName()).getId()) == false) {
				if (division.getDivisionName().contentEquals(getDivisionByName(division.getDivisionName()).getDivisionName()) == true)
					throw new ValidationFailedException("Unit with the Name " + division.getDivisionName() + " already exists");
			}
		}
		if (getDivisionByCode(division.getDivisionCode()) != null) {
			if (division.getId().contentEquals(getDivisionByCode(division.getDivisionCode()).getId()) == false) {
				if (division.getDivisionCode().contentEquals(getDivisionByCode(division.getDivisionCode()).getDivisionCode()) == true)
					throw new ValidationFailedException("Unit with the Code " + division.getDivisionCode() + " already exists");
			}
		}

		try {
			return divisionDAO.save(division);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Division");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDivision(Division division) throws SessionExpiredException, ValidationFailedException {
		if (isDivisionDeletable(division) == false) {
			try {
				throw new ValidationFailedException("Division is already referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Division");
			}
		}

		try {
			divisionDAO.remove(division);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Division");
		}
	}

	public boolean isDivisionDeletable(Division division) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("division", division);
		if (sectionDAO.count(search) > 0)
			return false;
		else
			return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Division> getDivisionbyDepartment(Department department) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("department", department);
		try {
			return divisionDAO.search(search);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Division");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Section saveSection(Section section) throws ValidationFailedException, SessionExpiredException {
		validateSection(section);
		checkifSectionExists(section);
		try {
			return sectionDAO.save(section);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.SUCCESS, "Section");
		}
	}

	@Transactional(readOnly = true)
	public Section getSectionByCode(String code) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("sectionCode", code);
		return sectionDAO.searchUnique(search);
	}

	@Transactional(readOnly = true)
	public Section getSectionByName(String name) {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("sectionName", name);
		return sectionDAO.searchUnique(search);
	}

	public void validateSection(Section section) throws ValidationFailedException, SessionExpiredException {
		if (section.getSectionCode() == null) {
			try {
				throw new ValidationFailedException("Section Code is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Department");
			}
		}
		if (section.getSectionName() == null) {
			try {
				throw new ValidationFailedException("Section Name is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Section");
			}
		}
		if (section.getSectionDescription() == null) {
			try {
				throw new ValidationFailedException("Section Description is missing");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Section");
			}
		}

	}

	public void checkifSectionExists(Section section) throws ValidationFailedException, SessionExpiredException {
		for (Section secn : getAllSections()) {
			if (section.getSectionName().contentEquals(secn.getSectionName())) {
				try {
					throw new ValidationFailedException("Section with the name " + section.getSectionName() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Section");
				}
			}

			if (section.getSectionCode().contentEquals(secn.getSectionCode())) {
				try {
					throw new ValidationFailedException("Section with the code " + section.getSectionCode() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Section");
				}
			}

			if (section.getSectionDescription().contentEquals(secn.getSectionDescription())) {
				try {
					throw new ValidationFailedException("Section with the Description " + section.getSectionDescription() + " already exists  ");
				} finally {
					auditlogService.saveAuditEventTrail(Operations.SAVE, ActionStatus.FAIL, "Section");
				}
			}

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Section> getAllSections() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return sectionDAO.search(search);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Section editSection(Section section) throws ValidationFailedException, SessionExpiredException {
		validateSection(section);
		if (getSectionByCode(section.getSectionCode()) != null) {
			if (section.getId().contentEquals(getSectionByCode(section.getSectionCode()).getId()) == false) {
				if (section.getSectionCode().contentEquals(getSectionByCode(section.getSectionCode()).getSectionCode()) == true)
					throw new ValidationFailedException("Unit with the Code " + section.getSectionCode() + " already exists");
			}
		}
		if (getSectionByName(section.getSectionName()) != null) {
			if (section.getId().contentEquals(getSectionByName(section.getSectionName()).getId()) == false) {
				if (section.getSectionName().contentEquals(getSectionByName(section.getSectionName()).getSectionName()) == true)
					throw new ValidationFailedException("Unit with the Name " + section.getSectionName() + " already exists");
			}
		}

		try {
			return sectionDAO.save(section);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.EDIT, ActionStatus.SUCCESS, "Section");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteSection(Section section) throws SessionExpiredException, ValidationFailedException {
		if (isSectionDeletable(section) == false) {
			try {
				throw new ValidationFailedException("Department is already referenced by other records");
			} finally {
				auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.FAIL, "Section");
			}
		}

		try {
			sectionDAO.removeById(section.getId());
		} finally {
			auditlogService.saveAuditEventTrail(Operations.DELETE, ActionStatus.SUCCESS, "Section");
		}
	}

	public boolean isSectionDeletable(Section section) {

		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Section> getSectionbyDivision(Division division) throws SessionExpiredException {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		search.addFilterEqual("division", division);
		try {
			return sectionDAO.search(search);
		} finally {
			auditlogService.saveAuditEventTrail(Operations.VIEW, ActionStatus.SUCCESS, "Section");
		}
	}

	@Transactional(readOnly = true)
	public Section getSectionById(String id) {
		return sectionDAO.searchUniqueByPropertyEqual("id", id);
	}

	@Transactional(readOnly = true)
	public Vote saveVote(Vote vote) throws ValidationFailedException {
		validateVote(vote);
		return voteDAO.save(vote);
	}

	public void validateVote(Vote vote) throws ValidationFailedException {
		if (vote.getVoteCode() == null)
			throw new ValidationFailedException("vote can't be saved: missing Vote Code");
		if (vote.getVoteDescription() == null)
			throw new ValidationFailedException("vote can't be saved: missing Vote description");
		if (vote.getVoteName() == null)
			throw new ValidationFailedException("vote can't be saved: missing Vote Name");
	}

	@Transactional(readOnly = true)
	public Vote getVote() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return (Vote) voteDAO.search(search).get(0);
	}

	@Transactional(readOnly = true)
	public Vote editVote(Vote vote) throws ValidationFailedException {
		validateVote(vote);
		return voteDAO.save(vote);
	}

	@Transactional(readOnly = true)
	public void deleteVote(Vote vote) throws ValidationFailedException {
		validateVote(vote);
		voteDAO.save(vote);
	}

}
