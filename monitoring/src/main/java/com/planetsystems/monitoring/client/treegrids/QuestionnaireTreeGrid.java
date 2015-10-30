package com.planetsystems.monitoring.client.treegrids;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class QuestionnaireTreeGrid extends TreeGrid {

	private Tree tree;
	private QuestionnaireTreeNode[] records;

	public QuestionnaireTreeGrid() {
		tree = new Tree();

		TreeGridField numberField = new TreeGridField(
				QuestionnaireTreeNode.NUMBER);
		numberField.setWidth("30%");

		TreeGridField questionIdField = new TreeGridField(
				QuestionnaireTreeNode.QUESTION_ID);
		questionIdField.setWidth("10%");
		questionIdField.setHidden(true);

		TreeGridField questionField = new TreeGridField(
				QuestionnaireTreeNode.QUESTION_NAME);

		questionField.setWidth("10%");

		TreeGridField questionTypeField = new TreeGridField(
				QuestionnaireTreeNode.QUESTION_TYPE);
		questionTypeField.setWidth("10%");

		TreeGridField commentField = new TreeGridField(
				QuestionnaireTreeNode.COMMENT);
		commentField.setWidth("10%");
		setFields(numberField, questionIdField, questionField,
				questionTypeField, commentField);
		
		
		this.setSelectionType(SelectionStyle.MULTIPLE);
	}

	public void updateData() {
		records = new QuestionnaireTreeNode[2];

		for (int i = 0; i < 2; i++) {
			records[i] = new QuestionnaireTreeNode(
					"How many people have received nets?", null, null, null,
					null);
			QuestionnaireTreeNode[] leafs = new QuestionnaireTreeNode[2];
			for (int j = 0; j < 1; j++) {
				leafs[j] = new QuestionnaireTreeNode(
						"How many people have not yet received nets", null,
						null, null, null);
			}
			records[i].setChildren(leafs);
		}
		tree.setData(records);
		this.setData(tree);
	}
}
