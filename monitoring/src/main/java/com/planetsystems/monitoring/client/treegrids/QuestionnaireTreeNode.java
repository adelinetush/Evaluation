package com.planetsystems.monitoring.client.treegrids;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

public class QuestionnaireTreeNode extends TreeNode {
    public static String NODE_NAME = "Questionnaire";
    public static String NUMBER = "#";
    public static String QUESTION_NAME = "Question";
    public static String QUESTION_TYPE = "Question Type";
    public static String COMMENT = "Comment";
    
	public static String QUESTION_ID="Question ID";
    //Fields for a answers
	public static String ANSWER="Answer";
	public static String ANSWER_ID="fileId";
	

    
    public QuestionnaireTreeNode(String attribute) {
        setAttribute(NODE_NAME, attribute);
    }
    
    public QuestionnaireTreeNode(String number,String questionId,String questionName,String questionType,String comment) {
        setAttribute(NUMBER, number);
        setAttribute(QUESTION_ID, questionId);
        setAttribute(QUESTION_NAME, questionName);
        setAttribute(QUESTION_TYPE, questionType);
        setAttribute(COMMENT, comment);
        
       
    }
    
    public QuestionnaireTreeNode(String answerId,String answer) {
    	setAttribute(ANSWER_ID, answerId);
        setAttribute(ANSWER, answer);
        
    }

    public boolean contains(String inputValue, Tree tree) {
        if(getAttributeAsString(NODE_NAME).toLowerCase().contains(inputValue)) {
            return true;
        } else {
            TreeNode[] childes = tree.getChildren(this);
            for (TreeNode treeNode : childes) {
                QuestionnaireTreeNode record = (QuestionnaireTreeNode)treeNode;
                if(record.contains(inputValue, tree)){
                    return true;
                }
            }
        }
        return false;
    }
}