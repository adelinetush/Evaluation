package com.planetsystems.monitoring.client.views.project;

import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProjectSpecificObjectivesInterface extends VLayout {
	
	public DynamicForm specificObjectiveForm;
	
	public Button saveObjective;
	
	public ProjectSpecificObjectivesInterface(){
		super();
		
		HLayout formLayout=new HLayout();
		formLayout.setHeight("90%");
		specificObjectiveForm=new DynamicForm();
		specificObjectiveForm.setNumCols(2);
		
		saveObjective=new SaveButton("Add");
		
		ComboBoxItem objectiveOptions=new ComboBoxItem("objective","Select Objective");
		objectiveOptions.setValueMap("To reduce the infant mortality rate");
		
		TextAreaItem specificObjective=new TextAreaItem("specificObjective", "Specific Objective");
		
		specificObjectiveForm.setItems(objectiveOptions,specificObjective);
		
		for (FormItem FI : specificObjectiveForm.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		
		formLayout.addMember(specificObjectiveForm);
		
		formLayout.addMember(saveObjective);
		
		formLayout.setMembersMargin(10);	
		
		addMember(formLayout);
		
	}

	public DynamicForm getSpecificObjectiveForm() {
		return specificObjectiveForm;
	}

	public Button getSaveObjective() {
		return saveObjective;
	}

}
