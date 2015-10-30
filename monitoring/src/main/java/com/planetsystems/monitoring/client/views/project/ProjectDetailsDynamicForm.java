package com.planetsystems.monitoring.client.views.project;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class ProjectDetailsDynamicForm  extends DynamicForm{
	
		public ProjectDetailsDynamicForm(){
			super();
		setNumCols(4);
		
		TextItem titleItem=new TextItem("projectTitle","Project Title");
		
		TextItem goalItem=new TextItem("projectGoal","Project Goal");
		
		TextItem baselineItem=new TextItem("projectBaseline","Project Baseline");
		
		TextItem targetItem=new TextItem("projectTarget","Project Target");
		
		setItems(titleItem,goalItem,baselineItem,targetItem);
		
		for (FormItem FI : getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
	}

	
}
