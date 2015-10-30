package com.planetsystems.monitoring.client.widgets;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContextArea extends VLayout {

	  Label label; 	
			  	
	  public ContextArea() {
	    super();
						
	    GWT.log("init Context Area()...", null);
					
	    // initialise the layout container
	    this.setWidth("*"); 
	    this.setBackgroundColor("#EEEEEE");  
					
	    // initialise the context area label
	    label = new Label(); 
	    label.setContents("Context Area");  
	    label.setAlign(Alignment.CENTER);  
	    label.setOverflow(Overflow.HIDDEN);  
					    
	    // add the label to the layout container
	    this.addMember(label); 
	  }	
	}