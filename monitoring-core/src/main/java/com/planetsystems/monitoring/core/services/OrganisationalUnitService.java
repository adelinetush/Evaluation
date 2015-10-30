package com.planetsystems.monitoring.core.services;

import java.util.List;

import com.planetsystems.monitoring.model.Vote;
import com.planetsystems.monitoring.model.exception.SessionExpiredException;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;

public interface OrganisationalUnitService {

	/**
	 * @param department
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Department saveDepartment(Department department)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * @return
	 */
	public List<Department> getAllDepartments();

	/**
	 * @param department
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Department editDepartment(Department department)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * @param code
	 * @return
	 */
	public Division getDivisionByCode(String code);

	/**
	 * @param department
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteDepartment(Department department)
			throws SessionExpiredException, ValidationFailedException;

	/**
	 * @return
	 * 
	 */
	List<Department> getAllDepartmentsBy();

	/**
	 * @param division
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Division saveDivision(Division division)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * @return
	 */
	public List<Division> getAllDivisions();

	/**
	 * @param department
	 * @return
	 * @throws SessionExpiredException
	 */
	public List<Division> getDivisionbyDepartment(Department department);

	/**
	 * @param division
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Division editDivision(Division division)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * @param division
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteDivision(Division division)
			throws SessionExpiredException, ValidationFailedException;

	// section

	/**
	 * @param section
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Section saveSection(Section section)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * @return
	 */
	public List<Section> getAllSections();

	/**
	 * @param section
	 * @return
	 * @throws ValidationFailedException
	 * @throws SessionExpiredException
	 */
	public Section editSection(Section section)
			throws ValidationFailedException, SessionExpiredException;

	/**
	 * @param section
	 * @throws SessionExpiredException
	 * @throws ValidationFailedException
	 */
	public void deleteSection(Section section) throws SessionExpiredException,
			ValidationFailedException;

	/**
	 * @param id
	 * @return
	 */
	public Department getDepartmentById(String id);

	/**
	 * @param id
	 * @return
	 */
	public Division getDivisionById(String id);

	/**
	 * @param id
	 * @return
	 */
	public Section getSectionById(String id);

	/**
	 * @param vote
	 * @return
	 * @throws ValidationFailedException
	 */
	public Vote saveVote(Vote vote) throws ValidationFailedException;

	/**
	 * @return
	 */
	public Vote getVote();

	/**
	 * @param vote
	 * @return
	 * @throws ValidationFailedException
	 */
	public Vote editVote(Vote vote) throws ValidationFailedException;

	/**
	 * @param vote
	 * @throws ValidationFailedException
	 */
	public void deleteVote(Vote vote) throws ValidationFailedException;

	/**
	 * @param code
	 * @return
	 */
	public Department getDepartmentByCode(String code);

}