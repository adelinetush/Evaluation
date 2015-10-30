package com.planetsystems.monitoring.client.views.project;

import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProjectObjectivesInterface extends VLayout {

	private DynamicForm objectiveForm;
	
	private Button saveButton;

	public ProjectObjectivesInterface() {
		super();
		setWidth("90%");
		HLayout formLayout = new HLayout();

		objectiveForm = new DynamicForm();

		TextAreaItem objectiveItem = new TextAreaItem("objective", "Objective");

		objectiveForm.setItems(objectiveItem);
		
		objectiveForm.setHeight("1%");

		saveButton = new SaveButton("Add");

		formLayout.addMember(objectiveForm);

		formLayout.addMember(saveButton);
		
		addMember(formLayout);

	}

	
	public DynamicForm getObjectiveForm() {
		return objectiveForm;
	}

	public Button getSaveButton() {
		return saveButton;
	}
	
	

}
