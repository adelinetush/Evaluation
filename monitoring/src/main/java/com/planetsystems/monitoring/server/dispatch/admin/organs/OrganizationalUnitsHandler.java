/**
 * 
 */
package com.planetsystems.monitoring.server.dispatch.admin.organs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.core.services.OrganisationalUnitService;
import com.planetsystems.monitoring.model.ProcnetErrorCodes;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.exception.ValidationFailedException;
import com.planetsystems.monitoring.model.units.Department;
import com.planetsystems.monitoring.model.units.Division;
import com.planetsystems.monitoring.model.units.Section;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsResult;

/**
 * @author Planet Developer 001
 *
 */
public class OrganizationalUnitsHandler extends AbstractActionHandler<OrganizationalUnitsAction,OrganizationalUnitsResult>{

	@Autowired
	private OrganisationalUnitService organizationalService;

	public OrganizationalUnitsHandler() {
		super(OrganizationalUnitsAction.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param actionType
	 */

	@Override
	public Class<OrganizationalUnitsAction> getActionType() {
		return OrganizationalUnitsAction.class;
	}

	public OrganizationalUnitsResult execute(OrganizationalUnitsAction action,
			ExecutionContext context) throws ActionException {
		
		OrganizationalUnitsResult result = null;
		
		System.out.println("OrganizationalUnitsResult execute");
		
		if(action.getOperationLevel().contentEquals(NameTokens.departments) && action.getOperation().contentEquals(NameTokens.saveOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = saveDepartment(action.getDepartment());
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.departments) && action.getOperation().contentEquals(NameTokens.editOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = editDepartment(action.getDepartment());
			
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.departments) && action.getOperation().contentEquals(NameTokens.deleteOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = deleteDepartment(action.getDepartment());
			
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.departments) && action.getOperation().contentEquals(NameTokens.retrieveOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = retrieveAllDepartments();
			
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.divisions) && action.getOperation().contentEquals(NameTokens.saveOperation)){
				
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result  =  saveDivision(action.getDivision());
			
			}
        else if(action.getOperationLevel().contentEquals(NameTokens.divisions) && action.getOperation().contentEquals(NameTokens.editOperation)){
			
        	System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
        	
        	result  =  editDivision(action.getDivision());
			
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.divisions) && action.getOperation().contentEquals(NameTokens.deleteOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result  =  deleteDivision(action.getDivision());
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.divisions) && action.getOperation().contentEquals(NameTokens.retrieveOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = retrieveAllDivisions();
		}
     else if(action.getOperationLevel().contentEquals(NameTokens.divisions) && action.getOperation().contentEquals(NameTokens.retrieveDivisionsByDepartmentOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = retrieveDivisionsByDepartment(action.getDepartment());
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.sections) && action.getOperation().contentEquals(NameTokens.saveOperation)){
				
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = saveSection(action.getSection());
			
			}
        else if(action.getOperationLevel().contentEquals(NameTokens.sections) && action.getOperation().contentEquals(NameTokens.editOperation)){
			
        	System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
        	
        	result = editSection(action.getSection());
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.sections) && action.getOperation().contentEquals(NameTokens.deleteOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = deleteSection(action.getSection());
			
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.sections) && action.getOperation().contentEquals(NameTokens.retrieveOperation)){
			
			System.out.println("Level: "+action.getOperationLevel()+", Operation: "+action.getOperation());
			
			result = retrieveAllSections();
		}
		else {
			
			result = new OrganizationalUnitsResult(true, "No Match");
		}
		
		return result;
	}

	public void undo(OrganizationalUnitsAction action,
			OrganizationalUnitsResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub
		
	}
	
	private OrganizationalUnitsResult saveDepartment(Department departmentTo){
		
		OrganizationalUnitsResult result = null;
		
		try{
			
			Department department = new Department();
			department.setDepartmentCode(departmentTo.getDepartmentCode());
			department.setDepartmentName(departmentTo.getDepartmentName());
			department.setDepartmentDescription(departmentTo.getDepartmentDescription());
			
			organizationalService.saveDepartment(department);
			
			result = new OrganizationalUnitsResult(true,"Successfully Saved Department");
		}
		catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
		}catch (NullPointerException e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
		return result;
	}
	
	private OrganizationalUnitsResult editDepartment(Department departmentTo){
		
		OrganizationalUnitsResult result = null;
		
		try{
			
			Department department = new Department();
			department = organizationalService.getDepartmentById(departmentTo.getId());
			
			department.setDepartmentCode(departmentTo.getDepartmentCode());
			department.setDepartmentName(departmentTo.getDepartmentName());
			department.setDepartmentDescription(departmentTo.getDepartmentDescription());
			
			organizationalService.editDepartment(department);
			
			result = new OrganizationalUnitsResult(true,"Successfully Edited Department");
		}
		catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
		}catch (NullPointerException e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
		return result;
	}
	
      private OrganizationalUnitsResult deleteDepartment(Department departmentTo){
		
		OrganizationalUnitsResult result = null;
		
		try{
			
			Department department = new Department();
			department = organizationalService.getDepartmentById(departmentTo.getId());
			
			organizationalService.deleteDepartment(department);
			
			result = new OrganizationalUnitsResult(true,"Successfully Deleted Department");
		}
		catch (ValidationFailedException e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
		}catch (NullPointerException e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			result = new OrganizationalUnitsResult(false,
					ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
		return result;
	}
      
      private OrganizationalUnitsResult retrieveAllDepartments(){
  		
  		OrganizationalUnitsResult result = null;
  		
  		try{
  			
  			List<Department> departments = new ArrayList<Department>();
  			List<Department> departmentsDTOs = new ArrayList<Department>();
  			
  			departments = organizationalService.getAllDepartments();
  			
  			
  			for(Department department: departments){
  				
  				System.out.println();
  			Department departmentDTO = new Department();
  			departmentDTO.setId(department.getId());
  			departmentDTO.setDepartmentName(department.getDepartmentName());
  			departmentDTO.setDepartmentCode(department.getDepartmentCode());
  			departmentDTO.setDepartmentDescription(department.getDepartmentDescription());
  			
  			User user = new User();

			if (department.getCreatedBy() != null) {

				user.setId(department.getCreatedBy().getId());
				user.setfName(department.getCreatedBy().getfName());
				user.setlName(department.getCreatedBy().getlName());
			} else {

				
			}

			User userChanged = new User();
			if (department.getChangedBy() != null) {

				userChanged.setId(department.getChangedBy().getId());
				userChanged.setfName(department.getChangedBy().getfName());
				userChanged.setlName(department.getChangedBy().getlName());

			} else {

				
			}
			
			departmentDTO.setChangedBy(userChanged);
			departmentDTO.setCreatedBy(user);
  			
			departmentsDTOs.add(departmentDTO);
			
  			}
  			
  			
  			result = new OrganizationalUnitsResult(true,departmentsDTOs);
  		}
  		catch (NullPointerException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
  		} catch (Exception e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.GENERAL_EXCEPTION);
  		}
  		return result;
  	}
      
      private OrganizationalUnitsResult saveDivision(Division divisionTo){
  		
  		OrganizationalUnitsResult result = null;
  		
  		try{
  			
  			Division division = new Division();
  			division.setDivisionCode(divisionTo.getDivisionCode());
  			division.setDivisionName(divisionTo.getDivisionName());
  			division.setDivisionDesctiption(divisionTo.getDivisionDesctiption());
  			
  			Department department = new Department();
  			department = organizationalService.getDepartmentById(divisionTo.getDepartment().getId());
  			
  			division.setDepartment(department);
  			
  			organizationalService.saveDivision(division);
  			
  			result = new OrganizationalUnitsResult(true,"Successfully Saved Division");
  		}
  		catch (ValidationFailedException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
  		}catch (NullPointerException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
  		} catch (Exception e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.GENERAL_EXCEPTION);
  		}
  		return result;
  	}
      
      private OrganizationalUnitsResult editDivision(Division divisionTo){
    		
    		OrganizationalUnitsResult result = null;
    		
    		try{
    			
    			Division division = new Division();
    			division = organizationalService.getDivisionById(divisionTo.getId());
    			
    			division.setDivisionCode(divisionTo.getDivisionCode());
    			division.setDivisionName(divisionTo.getDivisionName());
    			division.setDivisionDesctiption(divisionTo.getDivisionDesctiption());
    			
    			Department department = new Department();
    			department = organizationalService.getDepartmentById(divisionTo.getDepartment().getId());
    			
    			division.setDepartment(department);
    			
    			organizationalService.editDivision(division);
    			
    			result = new OrganizationalUnitsResult(true,"Successfully Edited Division");
    		}
    		catch (ValidationFailedException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
    		}catch (NullPointerException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
    		} catch (Exception e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.GENERAL_EXCEPTION);
    		}
    		return result;
    	}
  	
      private OrganizationalUnitsResult deleteDivision(Division divisionTo){
  		
  		OrganizationalUnitsResult result = null;
  		
  		try{
  			
  			Division division = new Division();
  			division = organizationalService.getDivisionById(divisionTo.getId());
  		
  			organizationalService.deleteDivision(division);
  			
  			result = new OrganizationalUnitsResult(true,"Successfully Deleted Division");
  		}
  		catch (AccessDeniedException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.ACCESSDENIEDEXCEPTION+": "+e.getMessage());
  		}
  		catch (ValidationFailedException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
  		}catch (NullPointerException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
  		}
  		catch (IllegalArgumentException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.ILLEGALARGUMENTEXCEPTION);
  		} catch (Exception e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.GENERAL_EXCEPTION);
  		}
  		return result;
  	}
	
      private OrganizationalUnitsResult retrieveAllDivisions(){
    		
    		OrganizationalUnitsResult result = null;
    		
    		try{
    			
    			List<Division> divisions = new ArrayList<Division>();
    			List<Division> divisionsDTOs = new ArrayList<Division>();
    			
    			divisions = organizationalService.getAllDivisions();
    			
    			for(Division division: divisions){
    				
    			Division divisionDTO = new Division();
    			divisionDTO.setId(division.getId());
    			divisionDTO.setDivisionCode(division.getDivisionCode());
    			divisionDTO.setDivisionName(division.getDivisionName());
    			divisionDTO.setDivisionDesctiption(division.getDivisionDesctiption());
    			
    			Department department = new Department();
    			department.setId(division.getDepartment().getId());
    			department.setDepartmentCode(division.getDepartment().getDepartmentCode());
    			department.setDepartmentDescription(division.getDepartment().getDepartmentDescription());
    			department.setDepartmentName(division.getDepartment().getDepartmentName());
    			
    			divisionDTO.setDepartment(department);
    			
    			User user = new User();

  			if (division.getCreatedBy() != null) {

  				user.setId(division.getCreatedBy().getId());
  				user.setfName(division.getCreatedBy().getfName());
  				user.setlName(division.getCreatedBy().getlName());
  			} else {

  				
  			}

  			User userChanged = new User();
  			if (division.getChangedBy() != null) {

  				userChanged.setId(division.getChangedBy().getId());
  				userChanged.setfName(division.getChangedBy().getfName());
  				userChanged.setlName(division.getChangedBy().getlName());

  			} else {

  				
  			}
  			
  			divisionDTO.setChangedBy(userChanged);
  			divisionDTO.setCreatedBy(user);
    			
  			divisionsDTOs.add(divisionDTO);
  			
    			}
    			
    			
    			result = new OrganizationalUnitsResult(divisionsDTOs,true);
    		}
    		catch (NullPointerException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
    		} catch (Exception e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.GENERAL_EXCEPTION);
    		}
    		return result;
    	}
      
      private OrganizationalUnitsResult retrieveDivisionsByDepartment(Department departmentTo){
  		
  		OrganizationalUnitsResult result = null;
  		
  		try{
  			
  			Department departmentFrom = new Department();
  			departmentFrom = organizationalService.getDepartmentById(departmentTo.getId());
  			
  			List<Division> divisions = new ArrayList<Division>();
  			List<Division> divisionsDTOs = new ArrayList<Division>();
  			
  			divisions = organizationalService.getDivisionbyDepartment(departmentFrom);
  			
  			for(Division division: divisions){
  				
  			Division divisionDTO = new Division();
  			divisionDTO.setId(division.getId());
  			divisionDTO.setDivisionCode(division.getDivisionCode());
  			divisionDTO.setDivisionName(division.getDivisionName());
  			divisionDTO.setDivisionDesctiption(division.getDivisionDesctiption());
  			
  			Department department = new Department();
  			department.setId(division.getDepartment().getId());
  			department.setDepartmentCode(division.getDepartment().getDepartmentCode());
  			department.setDepartmentDescription(division.getDepartment().getDepartmentDescription());
  			department.setDepartmentName(division.getDepartment().getDepartmentName());
  			
  			divisionDTO.setDepartment(department);
  			
  			User user = new User();

			if (division.getCreatedBy() != null) {

				user.setId(division.getCreatedBy().getId());
				user.setfName(division.getCreatedBy().getfName());
				user.setlName(division.getCreatedBy().getlName());
			} else {

				
			}

			User userChanged = new User();
			if (division.getChangedBy() != null) {

				userChanged.setId(division.getChangedBy().getId());
				userChanged.setfName(division.getChangedBy().getfName());
				userChanged.setlName(division.getChangedBy().getlName());

			} else {

				
			}
			
			divisionDTO.setChangedBy(userChanged);
			divisionDTO.setCreatedBy(user);
  			
			divisionsDTOs.add(divisionDTO);
			
  			}
  			
  			
  			result = new OrganizationalUnitsResult(divisionsDTOs,true);
  		}
  		catch (NullPointerException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
  		} catch (Exception e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.GENERAL_EXCEPTION);
  		}
  		return result;
  	}
      
      private OrganizationalUnitsResult retrieveDepartments(){
    		
    		OrganizationalUnitsResult result = null;
    		
    		try{
    			
    			List<Department> departments = new ArrayList<Department>();
    			List<Department> departmentsDTOs = new ArrayList<Department>();
    			
    			departments = organizationalService.getAllDepartments();
    			
    			for(Department department: departments ){
    		
    			Department departmentDTO = new Department();
    			departmentDTO.setId(department.getId());
    			department.setDepartmentCode(department.getDepartmentCode());
    			department.setDepartmentDescription(department.getDepartmentDescription());
    			department.setDepartmentName(department.getDepartmentName());
   
    	
  			departmentsDTOs.add(departmentDTO);
  			
    			}
    			
    			
    			result = new OrganizationalUnitsResult(true,departmentsDTOs);
    		}
    		catch (NullPointerException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
    		} catch (Exception e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.GENERAL_EXCEPTION);
    		}
    		return result;
    	}
      
      private OrganizationalUnitsResult saveSection(Section sectionTo){
    		
    		OrganizationalUnitsResult result = null;
    		
    		try{
    			
    			Section section = new Section();
    			section.setSectionCode(sectionTo.getSectionCode());
    			section.setSectionName(sectionTo.getSectionName());
    			section.setSectionDescription(sectionTo.getSectionDescription());
    			
    			Division division = new Division();
    			
    			division = organizationalService.getDivisionById(sectionTo.getDivision().getId());
    			
    			section.setDivision(division);
    			
    			organizationalService.saveSection(section);
    			
    			result = new OrganizationalUnitsResult(true,"Successfully Saved Section");
    		}
    		catch (ValidationFailedException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
    		}catch (NullPointerException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
    		} catch (Exception e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.GENERAL_EXCEPTION);
    		}
    		return result;
    	}
      
      private OrganizationalUnitsResult editSection(Section sectionTo){
  		
  		OrganizationalUnitsResult result = null;
  		
  		try{
  			
  			Section section = new Section();
  			section = organizationalService.getSectionById(sectionTo.getId());
  			
  			section.setSectionCode(sectionTo.getSectionCode());
  			section.setSectionName(sectionTo.getSectionName());
  			section.setSectionDescription(sectionTo.getSectionDescription());
  			
  			Division division = new Division();
  			division = organizationalService.getDivisionById(sectionTo.getDivision().getId());
  			
  			section.setDivision(division);
  			
  			organizationalService.editSection(section);
  			
  			result = new OrganizationalUnitsResult(true,"Successfully Edited Section");
  		}
  		catch (ValidationFailedException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
  		}catch (NullPointerException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
  		} catch (Exception e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.GENERAL_EXCEPTION);
  		}
  		return result;
  	}
        
      private OrganizationalUnitsResult deleteSection(Section sectionTo){
    		
    		OrganizationalUnitsResult result = null;
    		
    		try{
    			
    			Section section = new Section();
    			section = organizationalService.getSectionById(sectionTo.getId());
    		
    			organizationalService.deleteSection(section);
    			
    			result = new OrganizationalUnitsResult(true,"Successfully Deleted Section");
    		}
    		catch (ValidationFailedException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.VALIDATIONFAILEDEXCEPTION+": "+e.getMessage());
    		}catch (NullPointerException e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
    		} catch (Exception e) {
    			e.printStackTrace();
    			result = new OrganizationalUnitsResult(false,
    					ProcnetErrorCodes.GENERAL_EXCEPTION);
    		}
    		return result;
    	}
     
      private OrganizationalUnitsResult retrieveAllSections(){
  		
  		OrganizationalUnitsResult result = null;
  		
  		try{
  			
  			List<Section> sections = new ArrayList<Section>();
  			List<Section> sectionsDTOs = new ArrayList<Section>();
  			
  			sections = organizationalService.getAllSections();
  			
  			for(Section section: sections){
  				
  			Section sectionDTO = new Section();
  			sectionDTO.setId(section.getId());
  			sectionDTO.setSectionCode(section.getSectionCode());
  			sectionDTO.setSectionName(section.getSectionName());
  			sectionDTO.setSectionDescription(section.getSectionDescription());
  			
  			Division division = new Division();
  			division.setId(section.getDivision().getId());
  			division.setDivisionCode(section.getDivision().getDivisionCode());
  			division.setDivisionName(section.getDivision().getDivisionName());
  			division.setDivisionDesctiption(section.getDivision().getDivisionDesctiption());
  			
  			sectionDTO.setDivision(division);
  			
  			User user = new User();

			if (division.getCreatedBy() != null) {

				user.setId(division.getCreatedBy().getId());
				user.setfName(division.getCreatedBy().getfName());
				user.setlName(division.getCreatedBy().getlName());
			} else {

				
			}

			User userChanged = new User();
			if (division.getChangedBy() != null) {

				userChanged.setId(division.getChangedBy().getId());
				userChanged.setfName(division.getChangedBy().getfName());
				userChanged.setlName(division.getChangedBy().getlName());

			} else {

				
			}
			
			sectionDTO.setChangedBy(userChanged);
			sectionDTO.setCreatedBy(user);
  			
			sectionsDTOs.add(sectionDTO);
			
  			}
  			
  			
  			result = new OrganizationalUnitsResult(true,sectionsDTOs,"");
  		}
  		catch (NullPointerException e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
  		} catch (Exception e) {
  			e.printStackTrace();
  			result = new OrganizationalUnitsResult(false,
  					ProcnetErrorCodes.GENERAL_EXCEPTION);
  		}
  		return result;
  	}
}
