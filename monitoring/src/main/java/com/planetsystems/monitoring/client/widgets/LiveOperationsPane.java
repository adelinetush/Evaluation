package com.planetsystems.monitoring.client.widgets;

import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;

//import com.allen_sauer.gwt.log.client.Log;

public class LiveOperationsPane extends VLayout {
	
  private static final String WEST_WIDTH = "20%";
	
  
  // private static final int HEADER_HEIGHT = 31;
  
	
  public LiveOperationsPane() {
	  super();
	
    // initialise the Navigation Pane layout container
	  this.setStyleName("crm-NavigationPane");	
    this.setWidth(WEST_WIDTH);
   
    
    HTMLPane discussionPane=new HTMLPane();
    discussionPane.setHeight("40%");
    this.setShowEdges(true);
    this.setEdgeSize(2);
    
    HTMLPane teamsPane=new HTMLPane();
    teamsPane.setHeight("20%");
    
    HTMLPane mailsPane=new HTMLPane();
    mailsPane.setHeight("20%");
    mailsPane.setShowEdges(true);
    mailsPane.setEdgeSize(2);
    
    HTMLPane relatedPane=new HTMLPane();
    relatedPane.setHeight("20%");
    relatedPane.setShowEdges(true);
    relatedPane.setEdgeSize(2);
    
	  this.addMember(discussionPane);
	  this.addMember(teamsPane);
	  this.addMember(mailsPane);
	  this.addMember(relatedPane);
	  this.setRedrawOnResize(true);
	  
	  
  }	
  
 


}
