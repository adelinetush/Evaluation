package com.planetsystems.monitoring.client.views.project;

import com.planetsystems.monitoring.client.listgrids.ProjectsListGrid;
import com.smartgwt.client.types.Side;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ProjectDetailView  extends VLayout{

	private  ProjectsListGrid  objectiveGrid, goalsGrid;
	public ProjectDetailView(){
		 setMembersMargin(5)  ;
         setPadding(5); 
        
         

         final TabSet leftTabSet = new TabSet();
         leftTabSet.setTabBarPosition(Side.TOP);
         leftTabSet.setWidth100();
         leftTabSet.setHeight(250);

         Tab goalTab = new Tab("Goals", "icons/16/content.png");
      goalsGrid = new ProjectsListGrid();
      ListGridField idField = new ListGridField("goalId",
 				"Goal ID");
       idField.setHidden(true);
      ListGridField goals = new ListGridField("goals",
				"Goals");
      goalsGrid.setFields(goals);
      
         goalTab.setPane(goalsGrid);
         
     
         Tab objectiveTab = new Tab("Objectives", "icons/16/highlights.png");
         ListGridField goalIdField = new ListGridField("objectiveId",
   				"Objective ID");
         goalIdField.setHidden(true);
         ListGridField objectives = new ListGridField("objectives",
  				"Objectives");
          objectiveGrid = new ProjectsListGrid(){
                
			@Override
			protected Canvas getExpansionComponent(ListGridRecord record) {
				return super.getExpansionComponent(record);
			}
        	 
         };
       
         objectiveGrid.setFields(objectives);
         
         objectiveTab.setPane(objectiveGrid);

         leftTabSet.addTab(goalTab);
         leftTabSet.addTab(objectiveTab);

        
         setMembersMargin(15);
         addMember(leftTabSet);
         setHeight("*");
	}
	public ProjectsListGrid getObjectiveGrid() {
		return objectiveGrid;
	}
	public ProjectsListGrid getGoalsGrid() {
		return goalsGrid;
	}
	
	
}
