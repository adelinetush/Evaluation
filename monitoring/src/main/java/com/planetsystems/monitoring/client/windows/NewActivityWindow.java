package com.planetsystems.monitoring.client.windows;

import com.smartgwt.client.types.Alignment;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewActivityWindow extends Window {

	private String specId;

	private IButton saveButton, cancelButton;

	private DynamicForm newActivityForm;

	private ComboBoxItem resourceOptions;

	public NewActivityWindow(String specId) {
		super();
		this.specId = specId;
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		mainLayout.setMargin(20);

		newActivityForm = new DynamicForm();

		newActivityForm.setNumCols(4);

		TextAreaItem activityName = new TextAreaItem("activityName", "Activity");
		DateItem startDate = new DateItem("startDate", "Start Date");

		DateItem endDate = new DateItem("endDate", "End Date");

		resourceOptions = new ComboBoxItem("resource", "Resource");

		newActivityForm.setItems(activityName, startDate, endDate,
				resourceOptions);

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
		
		taskButtonsLayout.setMargin(10);

		taskButtonsLayout.setAlign(Alignment.CENTER);

		mainLayout.addMember(newActivityForm);

		mainLayout.addMember(taskButtonsLayout);

		setWidth("60%");
		setHeight("40%");
		setAutoCenter(true);
		addItem(mainLayout);
		setTitle("New Activity");

	}

}
