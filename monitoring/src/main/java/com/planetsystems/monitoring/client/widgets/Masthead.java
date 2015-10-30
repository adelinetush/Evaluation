package com.planetsystems.monitoring.client.widgets;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class Masthead extends HLayout {

  private static final int MASTHEAD_HEIGHT = 58;
  
  
  
	private static final int IMAGE_SIZE = 48;
	private static final String WEST_WIDTH = "40%";
	private static final String EAST_WIDTH = "40%";
	private static final String CENTER_WIDTH = "20%";
//	private static final String LOGO = "procnet_icon.png";//rosette.png
	private static final String NAME_LABEL = "ProcNet";
	
	private static final int CLIENT_WIDTH_IMAGE_SIZE = 50;
	private static final int CLIENT_HEIGHT_IMAGE_SIZE = 60;
	
	private static final int PROCNET_WIDTH_IMAGE_SIZE = 109;
	private static final int PROCNET_HEIGHT_IMAGE_SIZE = 30;
	
	private static final String LOGO = "procnet-logo.png";//rosette.png
	private static final String CLIENT_LOGO = "ura_logo.png";
	
	
	private  String userName="Moses Kalema";
	private  String orgnanizationName="Planet Systems";
	
	private  String SIGNED_IN_USER_LABEL ="";
	private  String SIGNED_IN_USER_VOTE ="URA";
	private  String SERVER_TIME ="";
	private String USER_LABEL=""+SIGNED_IN_USER_LABEL+"<br>"+SIGNED_IN_USER_VOTE;

	private  Label signedInUserLabel;
	private  Label serverTimeLabel;
	
    
  public Masthead() {
	super();

	GWT.log("Matshead()");
	
//	SIGNED_IN_USER_LABEL = "<b>"+userName+"</b><br/>"+orgnanizationName+"";
	
	// initialize the Masthead layout container
	this.setStyleName("crm-Masthead");
	this.setHeight(MASTHEAD_HEIGHT);
	
	// initialize Logo image
   // Img logo = new Img(LOGO, IMAGE_SIZE, IMAGE_SIZE);
   /* Img logo = new Img(LOGO, IMAGE_SIZE, IMAGE_SIZE);
    logo.setStyleName("crm-Masthead-Logo");*/
    
    Img logo = new Img(LOGO, PROCNET_WIDTH_IMAGE_SIZE, PROCNET_HEIGHT_IMAGE_SIZE);
    logo.setStyleName("crm-Masthead-Logo");
    
    Img clientLogo = new Img(CLIENT_LOGO, CLIENT_WIDTH_IMAGE_SIZE, CLIENT_HEIGHT_IMAGE_SIZE);
    clientLogo.setStyleName("crm-Masthead-Logo");
    
    // initialize the Name Label
    Label name = new Label();
    name.setStyleName("crm-Masthead-Name");
    name.setContents(NAME_LABEL);
    
    // initialize the West layout container
    HLayout westLayout = new HLayout();
    westLayout.setHeight(MASTHEAD_HEIGHT);
    westLayout.setWidth(WEST_WIDTH);
  //westLayout.addMember(logo);
    westLayout.addMember(name);
    
    // initialize the Signed In User label
    signedInUserLabel = new Label();
    signedInUserLabel.setStyleName("crm-Masthead-SignedInUser");
    signedInUserLabel.setContents(USER_LABEL);
    
    serverTimeLabel = new Label();
    serverTimeLabel.setStyleName("crm-Masthead-SignedInUser");
    serverTimeLabel.setContents(SERVER_TIME);
    
    // initialize the East layout container
    HLayout eastLayout = new HLayout();
    eastLayout.setAlign(Alignment.RIGHT);
    eastLayout.setHeight(MASTHEAD_HEIGHT);
    eastLayout.setWidth(EAST_WIDTH);
    eastLayout.addMember(signedInUserLabel);
    eastLayout.addMember(clientLogo);
    
    HLayout centerLayout = new HLayout();
    centerLayout.setAlign(Alignment.CENTER);
    centerLayout.setHeight(MASTHEAD_HEIGHT);
    centerLayout.setWidth(CENTER_WIDTH);
    centerLayout.addMember(serverTimeLabel);
    
    // add the West and East layout containers to the Masthead layout container
    this.addMember(westLayout);
   // this.addMember(centerLayout);
    this.addMember(eastLayout);
    
  }	
  
  
  
  /**
	 * @return the signedInUser
	 */
	public Label getSignedInUser() {
		return signedInUserLabel;
	}
	/**
	 * @param signedInUser the signedInUser to set
	 */
	public void setSignedInUser(Label signedInUser) {
		this.signedInUserLabel = signedInUser;
	}

	public void setSignedInUserLabelContents(String contents) {
		signedInUserLabel.setContents(contents);
	  } 
		  
	  public String getSignedInUserLabelContents() {
		  return signedInUserLabel.getContents();
	  }
	/**
	 * @return the serverTimeLabel
	 */
	public Label getServerTimeLabel() {
		return serverTimeLabel;
	}
	/**
	 * @param serverTimeLabel the serverTimeLabel to set
	 */
	public void setServerTimeLabel(Label serverTimeLabel) {
		this.serverTimeLabel = serverTimeLabel;
	} 

	public void setServerTimeLabelContents(String contents) {
		serverTimeLabel.setContents(contents);
	  } 
		  
	  public String getServerTimeLabelContents() {
		  return serverTimeLabel.getContents();
	  }
	/**
	 * @return the sIGNED_IN_USER_LABEL
	 */
	public String getSIGNED_IN_USER_LABEL() {
		return SIGNED_IN_USER_LABEL;
	}
	/**
	 * @param sIGNED_IN_USER_LABEL the sIGNED_IN_USER_LABEL to set
	 */
	public void setSIGNED_IN_USER_LABEL(String sIGNED_IN_USER_LABEL) {
		SIGNED_IN_USER_LABEL = sIGNED_IN_USER_LABEL;
	}
	/**
	 * @return the sIGNED_IN_USER_VOTE
	 */
	public String getSIGNED_IN_USER_VOTE() {
		return SIGNED_IN_USER_VOTE;
	}
	/**
	 * @param sIGNED_IN_USER_VOTE the sIGNED_IN_USER_VOTE to set
	 */
	public void setSIGNED_IN_USER_VOTE(String sIGNED_IN_USER_VOTE) {
		SIGNED_IN_USER_VOTE = sIGNED_IN_USER_VOTE;
	}
	/**
	 * @return the sERVER_TIME
	 */
	public String getSERVER_TIME() {
		return SERVER_TIME;
	}
	/**
	 * @param sERVER_TIME the sERVER_TIME to set
	 */
	public void setSERVER_TIME(String sERVER_TIME) {
		SERVER_TIME = sERVER_TIME;
	}

}