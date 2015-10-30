/**
 * 
 */
package com.planetsystems.monitoring.client.views.project.admin;

import java.util.List;



import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.events.AuditTrailUiHandlers;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.planetsystems.monitoring.client.listgrids.EventsListGrid;
import com.planetsystems.monitoring.client.listgrids.SessionsListGrid;
import com.planetsystems.monitoring.client.presenters.project.admin.AuditTrailPresenter;
/**
 * @author Planet Developer 001
 *
 */
public class AuditTrailView extends ViewWithUiHandlers<AuditTrailUiHandlers> implements AuditTrailPresenter.MyView{

	private static final String CONTEXT_AREA_WIDTH = "100%";

	private HLayout mainPanel;
	private VLayout panel;
	final TabSet tabSet;
	
	private Tab sessionTab;
	private Tab eventsTab;
	private Tab analysisTab;
	
	final SessionsListGrid sessionsListGrid;
	final EventsListGrid eventsListGrid;
	
	
	@Inject
	public AuditTrailView(TabSet tabSet,SessionsListGrid sessionsListGrid,EventsListGrid eventsListGrid) {


		this.tabSet=tabSet;
		this.sessionsListGrid=sessionsListGrid;
		this.eventsListGrid=eventsListGrid;
		
		
		panel = new VLayout();
		panel.setWidth(CONTEXT_AREA_WIDTH);
		panel.setHeight100();
		
		sessionTab = new Tab();
		sessionTab.setTitle("Sessions ");
		sessionTab.setCanClose(false);
		
		eventsTab = new Tab();
		eventsTab.setTitle("User Events ");
		eventsTab.setCanClose(false);
		
		analysisTab = new Tab();
		analysisTab.setTitle("Analysis ");
		analysisTab.setCanClose(false);
		
		this.sessionsListGrid.setHeight100();
		this.eventsListGrid.setHeight100();
		
		sessionTab.setPane(sessionsListGrid);
		eventsTab.setPane(eventsListGrid);
		
		tabSet.addTab(sessionTab);
		tabSet.addTab(eventsTab);
		tabSet.addTab(analysisTab);
		
		panel.addMember(tabSet);
		
		mainPanel=new HLayout();
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		mainPanel.addMember(panel);
	
	}
	/* (non-Javadoc)
	 * @see com.gwtplatform.mvp.client.View#asWidget()
	 */
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return mainPanel;
	}
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.AuditTrailPresenter.MyView#setServerResponse(java.lang.String)
	 */
	public void setServerResponse(String serverResponse) {
	
		SC.say(""+serverResponse);
		
		
	}
	public void loadAuditEvents(List<AuditEventsTrail> eventsAudit) {
		
		ListGridRecord[] list = new ListGridRecord[eventsAudit.size()];
		int listCount = 0;
		ListGridRecord record ;
		for(AuditEventsTrail event:eventsAudit){
					
		record = new ListGridRecord();
		record.setAttribute(EventsListGrid.ICON,"calender" );
		record.setAttribute(EventsListGrid.EVENT_ID, event.getId());
		record.setAttribute(EventsListGrid.ENTITY,event.getEntity());
		record.setAttribute(EventsListGrid.LOGGED_IN_DATE, event.getDate());
		record.setAttribute(EventsListGrid.LOGGED_IN_TIME, event.getTime());
		record.setAttribute(EventsListGrid.OPERATION, event.getOperation());
		record.setAttribute(EventsListGrid.USER_ID, event.getUser().getId());
		record.setAttribute(EventsListGrid.USER_NAME, event.getUser().getFullName());
		record.setAttribute(EventsListGrid.ACTION_STATUS, event.getStatus().toString());
		
	//	record.setAttribute("emptyField", "");
		
		list[listCount] = record;
		listCount ++;
		
		}
		if(list.length > 0){
		this.eventsListGrid.setData(list);
		}
		else
		{
			
		}
		
	}
	

}
