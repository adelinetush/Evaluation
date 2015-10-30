package com.planetsystems.monitoring.client.windows;

import com.planetsystems.monitoring.client.listgrids.TasksListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TaskPopUpWindow  extends Window{

	private TasksListGrid taskGrid;
	public TaskPopUpWindow(){
		HLayout layout = new HLayout();
        layout.setWidth100();
        layout.setHeight("30%");
        
		VLayout vlayout=new VLayout();
		IButton saveButton=new IButton("Save Task");
		IButton clearButton=new IButton("Clear");
		
		vlayout.setMembers(saveButton,clearButton);
		vlayout.setAlign(VerticalAlignment.TOP);
		vlayout.setMembersMargin(10);
		
		
		DynamicForm form = new DynamicForm();
		form.setWidth("90%");
		

		TextItem taskNameItem = new TextItem("taskName", "Enter Task");
		DateItem startDateItem = new DateItem("startDate", "Start Date");
		DateItem endDateItem = new DateItem("endDate", "End Date");
		TextAreaItem comment = new TextAreaItem("comment", "Comment");
		ComboBoxItem resource = new ComboBoxItem("resource", "Allocate Task to");
		resource.setValueMap("Kizito Masaba,Fred Kasoma, Godfrey Asiimwe");

		form.setItems(taskNameItem, startDateItem, endDateItem, resource,comment);
		form.setNumCols(4);

		for (FormItem FI : form.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		
		
		layout.addMember(form);
		layout.addMember(vlayout);
		
		taskGrid=new TasksListGrid();

		setTitle("Reporting Window");

		setWidth("60%");
		setHeight("40%");
		setHeight(com.google.gwt.user.client.Window.getClientHeight() - 10);
		addItem(layout);
		addItem(taskGrid);
		setAlign(Alignment.CENTER);
		

	}
	public TasksListGrid getTaskGrid() {
		return taskGrid;
	}
	
}
