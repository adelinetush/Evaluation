package com.planetsystems.monitoring.client.views.project;

import com.planetsystems.monitoring.client.gin.ProjectTeamListGrid;
import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProjectTeamView  extends VLayout{
	
	private DynamicForm memberCreationForm;
	
	private Button saveButton;
	
	private ProjectTeamListGrid projectListGrid;
	
	private VLayout mainLayout;
	
	private ComboBoxItem selectMember;
	
	
	public ProjectTeamView(){
		super();
		setWidth100();
		setHeight100();
		
		projectListGrid=new ProjectTeamListGrid();
		HLayout layout=new HLayout();
		layout.setWidth100();
		
		saveButton=new SaveButton("Save");
		
		memberCreationForm=new DynamicForm();
		memberCreationForm.setNumCols(8);
		memberCreationForm.setWidth("90%");
		
		selectMember=new ComboBoxItem("member","Select Member");
		
		TextItem employeeIdItem=new TextItem("employeeId","Employee ID");
		
		TextItem positionItem=new TextItem("position","Position");
		
		memberCreationForm.setFields(selectMember,employeeIdItem,positionItem);
		
		for (FormItem FI : memberCreationForm.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		
		layout.addMember(memberCreationForm);
		layout.addMember(saveButton);
		layout.setHeight("1%");
		
		addMember(layout);
		addMember(projectListGrid);
		
	}


	public Button getSaveButton() {
		return saveButton;
	}


	public DynamicForm getMemberCreationForm() {
		return memberCreationForm;
	}


	public ProjectTeamListGrid getProjectListGrid() {
		return projectListGrid;
	}


	public ComboBoxItem getSelectMember() {
		return selectMember;
	}


	
	

}
