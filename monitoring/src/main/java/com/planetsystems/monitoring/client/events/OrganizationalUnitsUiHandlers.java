package com.planetsystems.monitoring.client.events;

import com.gwtplatform.mvp.client.UiHandlers;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;

public interface OrganizationalUnitsUiHandlers extends UiHandlers{

	void saveDepartmentButtonClicked(Department department);
	void editDepartmentButtonClicked(Department department);
	void deleteDepartmentButtonClicked(Department department);
	void retrieveAllDepartmentsButtonClicked();
	
	void saveDivisionButtonClicked(Division division);
	void editDivisionButtonClicked(Division division);
	void deleteDivisionButtonClicked(Division division);
	void retrieveAllDivisionsButtonClicked();
	
	void saveSectionButtonClicked(Section section);
	void editSectionButtonClicked(Section section);
	void deleteSectionButtonClicked(Section section);
	void retrieveAllSectionsButtonClicked();
	void loadDivisionsCombo();
	
}
