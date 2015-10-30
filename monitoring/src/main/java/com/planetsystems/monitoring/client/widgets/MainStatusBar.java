/**
 * 
 */
package com.planetsystems.monitoring.client.widgets;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Planet Developer 001
 *
 */
public class MainStatusBar extends HLayout{

	private static final String STATUSBAR_HEIGHT="23px";
	private static final String TOOLSTRIP_WIDTH="*";
	
	protected final ToolStrip toolStrip;
	protected final ToolStripButton userIDLabel;
	protected String userID;
	protected final ToolStripButton fullNameLabel;
	protected String fullNameID;
	protected final ToolStripButton roleLabel;
	protected String roleID;
	/*protected final ToolStripButton databaseLabel;*/
	protected final ToolStripButton serverTimeLabel;
	protected String serverTime;
	
	
	public MainStatusBar(){
		super();
		
		this.setStyleName("crm-ApplicationMenu");
		this.setHeight(STATUSBAR_HEIGHT);
		this.setWidth100();
		
		   // initialise the StatusBar's ToolStrip
	    toolStrip = new ToolStrip();
	    toolStrip.setStyleName("crm-StatusBar-ToolStrip");
	    toolStrip.setHeight(STATUSBAR_HEIGHT);
	    toolStrip.setWidth(TOOLSTRIP_WIDTH);
	    toolStrip.setAlign(Alignment.LEFT);
	    toolStrip.setMembersMargin(10);
	    toolStrip.setAlign(Alignment.CENTER);
	    
	    
	    // initialise the User ID button
	    userIDLabel = new ToolStripButton();
	    userIDLabel.setShowRollOver(false);
	    userIDLabel.setShowDisabled(true);
	    userIDLabel.setTitle("User ID:<b>" + userID + "</b>");
	    userIDLabel.disable();
	    
	 //   toolStrip.addMember(userIDLabel);
	  
	    // initialise the Full Name button
	    fullNameLabel = new ToolStripButton();
	    fullNameLabel.setShowRollOver(false);
	    fullNameLabel.setShowDisabled(true);
	    fullNameLabel.setTitle("Full Name:<b>" + fullNameID + "</b>");
	    fullNameLabel.disable();
	    
	   // toolStrip.addMember(fullNameLabel);
	    
	 // initialise the Role button
	    roleLabel = new ToolStripButton();
	    roleLabel.setShowRollOver(false);
	    roleLabel.setShowDisabled(true);
	    roleLabel.setTitle("Role:<b>" + roleID + "</b>");
	    roleLabel.disable();
	    
	   // toolStrip.addMember(roleLabel);
	    
	 // initialise the Server Time button
	    serverTimeLabel = new ToolStripButton();
	    serverTimeLabel.setShowRollOver(false);
	    serverTimeLabel.setShowDisabled(true);
	    serverTimeLabel.setTitle("Server:<b>" + serverTime + "</b>");
	    serverTimeLabel.disable();
	    
	   // toolStrip.addMember(serverTimeLabel);
	    
	    Label footerLabel = new Label("<h4><b>"+"&copy; Planet Systems Ltd, 2015"+"</b></h4>");
	    footerLabel.setWidth("20%");
	    footerLabel.setAlign(Alignment.CENTER);
	    toolStrip.addMember(footerLabel);
	    
	    this.addMember(toolStrip);
	}


	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}


	/**
	 * @return the fullNameID
	 */
	public String getFullNameID() {
		return fullNameID;
	}


	/**
	 * @return the roleID
	 */
	public String getRoleID() {
		return roleID;
	}


	/**
	 * @return the serverTime
	 */
	public String getServerTime() {
		return serverTime;
	}


	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}


	/**
	 * @param fullNameID the fullNameID to set
	 */
	public void setFullNameID(String fullNameID) {
		this.fullNameID = fullNameID;
	}


	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}


	/**
	 * @param serverTime the serverTime to set
	 */
	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	
}
