package com.planetsystems.monitoring.client.windows;

import java.util.LinkedHashMap;
import java.util.List;

import com.planetsystems.monitoring.model.project.Objectives;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewSpecificObjectiveWindow extends Window {

    List<Objectives> objectiveOptions;
	public NewSpecificObjectiveWindow( List<Objectives> objectiveOptions) {
		this.objectiveOptions=objectiveOptions;
	}

	private IButton saveButton, cancelButton;

	private DynamicForm newActivityForm;

	private ComboBoxItem objOptions;

	public NewSpecificObjectiveWindow() {
		super();
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		newActivityForm = new DynamicForm();

		newActivityForm.setNumCols(2);

		TextAreaItem specificObjective = new TextAreaItem("specificObjective", "Specific Objective");

		LinkedHashMap<String,String> valueMap=new LinkedHashMap<String,String>();
		
		if(objectiveOptions !=null){
			for(Objectives obj: objectiveOptions){
				valueMap.put(obj.getId(), obj.getObjectiveName());
			}
		}

		objOptions = new ComboBoxItem("objective", "Select Objective");
		objOptions.setValueMap(valueMap);

		newActivityForm.setItems(objOptions,specificObjective);

		for (FormItem FI : newActivityForm.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		HLayout taskButtonsLayout = new HLayout();
		taskButtonsLayout.setWidth100();
		taskButtonsLayout.setHeight("1%");
		saveButton = new IButton("Save");
		
		cancelButton = new IButton("Cancel");
		
		

		taskButtonsLayout.setMembers(cancelButton, saveButton);
		taskButtonsLayout.setAlign(Alignment.CENTER);

		mainLayout.addMember(newActivityForm);

		mainLayout.addMember(taskButtonsLayout);
		mainLayout.setMargin(20);

		setWidth("40%");
		setHeight("30%");
		setTitle("New Specific Objective");
		// setHeight(com.google.gwt.user.client.Window.getClientHeight() - 10);
		addItem(mainLayout);
		setAutoCenter(true);
		
	}

}
