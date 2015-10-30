package com.planetsystems.monitoring.client.windows;

import java.util.LinkedHashMap;

import com.planetsystems.monitoring.client.treegrids.QuestionnaireTreeGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class KPIWindow extends Window {

	private String specId;

	private IButton saveButton, cancelButton;

	private DynamicForm newKpiForm;

	Label selectedOptionLabel, optionValueLabel;

	private ComboBoxItem measurementType;

	private QuestionnaireTreeGrid questionGrid;

	public KPIWindow(String specId) {
		super();
		this.specId = specId;
		
		
		VLayout kpiLayout = new VLayout();
		kpiLayout.setWidth100();
		kpiLayout.setHeight100();
		kpiLayout.setMargin(20);

		
		HLayout selectedOptionLayout = new HLayout();
		selectedOptionLayout.setWidth100();
		selectedOptionLayout.setHeight("1%");
		
		selectedOptionLabel = new Label();
		optionValueLabel = new Label();
		
		selectedOptionLayout.setMembers(selectedOptionLabel,optionValueLabel);

		
		
		
		newKpiForm = new DynamicForm();
		newKpiForm.setNumCols(4);
		
		TextItem performanceIndicator = new TextItem("kpi", "K.P.I");
		measurementType = new ComboBoxItem("measurementType","Measurement Type");

		CheckboxItem addQuestions = new CheckboxItem("addQuestion","Add Questionnaire");
		LinkedHashMap<String, String> valueMaps = new LinkedHashMap<String, String>();
		valueMaps.put("qualitative", "Qualitative");
		valueMaps.put("quantitative", "Quantitative");

		measurementType.setValueMap(valueMaps);

		newKpiForm.setItems(performanceIndicator, measurementType, addQuestions);
		newKpiForm.setHeight("1%");

		for (FormItem FI : newKpiForm.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		
		kpiLayout.setMembers(selectedOptionLayout,newKpiForm); //selected options and kpi form added
		

		Label questionLabel = new Label("Questionnaire");
		questionLabel.setHeight("1%");
		
		kpiLayout.addMember(questionLabel);
		

		HLayout questionSectionLayout=new HLayout();
		questionSectionLayout.setWidth100();
		questionSectionLayout.setHeight("20%");
		
		
		HLayout questionLayout=new HLayout();
		questionLayout.setWidth("70");
		questionLayout.setHeight100();
		

		DynamicForm questionForm = new DynamicForm();

		questionForm.setNumCols(4);
		TextItem questionItem = new TextItem("question", "Enter Question");

		questionForm.setItems(questionItem);

		for (FormItem FI : questionForm.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		
		questionLayout.addMember(questionForm);

		
		HLayout addButtonLayout=new HLayout();
		addButtonLayout.setWidth("20");
		addButtonLayout.setHeight100();
		
		Button addQuestionButton = new Button("Save Question");
		addQuestionButton.setAlign(Alignment.CENTER);
		
		addButtonLayout.addMember(addQuestionButton);

		questionSectionLayout.setMembers(questionLayout,addButtonLayout);
		questionSectionLayout.setAlign(VerticalAlignment.CENTER);
		
		kpiLayout.addMember(questionSectionLayout);
		
		questionGrid = new QuestionnaireTreeGrid();

		kpiLayout.addMember(questionGrid);
		
		
		HLayout taskButtonLayout=new HLayout();
		taskButtonLayout.setWidth100();
		taskButtonLayout.setHeight("1%");
		
		saveButton = new IButton("Save");
		cancelButton = new IButton("Cancel");

		taskButtonLayout.setMembers(cancelButton,saveButton);
		taskButtonLayout.setAlign(Alignment.CENTER);
		
		kpiLayout.addMember(taskButtonLayout);
		
		kpiLayout.setAlign(Alignment.CENTER);

		setWidth("60%");
		setHeight("60%");
		setAutoCenter(true);
		addItem(kpiLayout);
		setTitle("New KPI");

	}

	public QuestionnaireTreeGrid getQuestionGrid() {
		return questionGrid;
	}

}
