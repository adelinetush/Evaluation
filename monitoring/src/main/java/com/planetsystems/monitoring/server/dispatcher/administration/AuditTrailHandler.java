/**
 * 
 */
package com.planetsystems.monitoring.server.dispatcher.administration;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.core.services.AuditlogService;
import com.planetsystems.monitoring.model.ProcnetErrorCodes;
import com.planetsystems.monitoring.model.User;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AuditTrailAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AuditTrailResult;

/**
 * @author Planet Developer 001
 *
 */
public class AuditTrailHandler extends AbstractActionHandler<AuditTrailAction,AuditTrailResult>{

	@Autowired
	private AuditlogService auditService;

	public AuditTrailHandler() {
		super(AuditTrailAction.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param actionType
	 */

	@Override
	public Class<AuditTrailAction> getActionType() {
		return AuditTrailAction.class;
	}

	public AuditTrailResult execute(AuditTrailAction action,
			ExecutionContext context) throws ActionException {

		
		System.out.println("AuditTrailResult execute");
		
		AuditTrailResult result = null;
		
		if(action.getOperationLevel().contentEquals(NameTokens.auditEvents) && action.getOperation().contentEquals(NameTokens.retrieveAllAuditEventsOpeartion)){
			
			result = getEventsAudit();
		}
		else if(action.getOperationLevel().contentEquals(NameTokens.auditSessions) && action.getOperation().contentEquals(NameTokens.retrieveOperation)){
			
			result = getEventsAudit();
		}
		else{
			
			result = new AuditTrailResult(false,"No Match");
			
		}
		
		return result;
	}

	public void undo(AuditTrailAction action, AuditTrailResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

	private AuditTrailResult getEventsAudit(){
		
		AuditTrailResult result = null;
		
		try{
			
			List<AuditEventsTrail> eventsAudits = new ArrayList<AuditEventsTrail>();
			List<AuditEventsTrail> eventsAuditsDTOs = new ArrayList<AuditEventsTrail>();
			
			eventsAudits = auditService.getAuditEventsTrail();
			
			for(AuditEventsTrail event:eventsAudits){
				
				AuditEventsTrail eventDTO = new AuditEventsTrail();
				eventDTO.setId(event.getId());
				eventDTO.setEntity(event.getEntity());
				eventDTO.setOperation(event.getOperation());
				eventDTO.setStatus(event.getStatus());
				eventDTO.setTime(event.getTime());
				eventDTO.setDate(event.getDate());
				
				User user = new User();
				user.setId(event.getId());
				user.setfName(event.getCreatedBy().getfName());
				user.setlName(event.getCreatedBy().getlName());
			
				eventDTO.setUser(user);
				
				eventsAuditsDTOs.add(eventDTO);
				
			}
			
			result = new AuditTrailResult(true,eventsAuditsDTOs);
			
		}
		catch(NullPointerException ex){
			
			ex.printStackTrace();
			result = new AuditTrailResult(true,ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		}
      catch(Exception ex){
			
			ex.printStackTrace();
			result = new AuditTrailResult(true,ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
      
      return result;
	}
	
   private AuditTrailResult getEventsAuditByDate(Date date){
		
	AuditTrailResult result = null;
		
		try{
			
			List<AuditEventsTrail> eventsAudits = new ArrayList<AuditEventsTrail>();
			List<AuditEventsTrail> eventsAuditsDTOs = new ArrayList<AuditEventsTrail>();
			
			eventsAudits = auditService.getAuditEventsTrailByDate(date);
			
			for(AuditEventsTrail event:eventsAudits){
				
				AuditEventsTrail eventDTO = new AuditEventsTrail();
				eventDTO.setEntity(event.getEntity());
				eventDTO.setOperation(event.getOperation());
				eventDTO.setStatus(event.getStatus());
				eventDTO.setTime(event.getTime());
				eventDTO.setDate(event.getDate());
				
				User user = new User();
				user.setId(event.getId());
				user.setfName(event.getUser().getfName());
				user.setlName(event.getUser().getlName());
			
				eventDTO.setUser(user);
				
				eventsAuditsDTOs.add(eventDTO);
				
			}
			
			result = new AuditTrailResult(true,eventsAuditsDTOs);
			
		}
		catch(NullPointerException ex){
			
			ex.printStackTrace();
			result = new AuditTrailResult(true,ProcnetErrorCodes.NULL_POINTER_EXCEPTION);
		}
      catch(Exception ex){
			
			ex.printStackTrace();
			result = new AuditTrailResult(true,ProcnetErrorCodes.GENERAL_EXCEPTION);
		}
      
      return result;
	}
}
