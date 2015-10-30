package com.planetsystems.monitoring.client.views.project;

import java.util.Date;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

public class TreeNodeRecord extends TreeNode {
    public static String NODE_NAME = "Objectives";
    public static String NUMBER = "No";
    public static String PROJECT_NAME = "Project Name";
    public static String OWNER = "Owner";
    public static String STARTED = "Started";
    public static String ENDED = "Ended";
    public static String STATUS = "Status";
    public static String NEXT_ACTOR = "Next Actor";
    public static String LATEST_COMMENT = "Latest Comment";
	public static String FILE_ID_DISPLAY="File ID";
    //Fields for a file
	public static String FILE_NAME="File Name";
	public static String DATE_MODIFIED="Date Modified";
	public static String TYPE="Type";
	public static String FILE_SIZE="File Size";
	public static String CREATED_BY="Created By";
	public static String DATE_CREATED="Date Created";
	public static String FILE_ID="fileId";
	

    
    public TreeNodeRecord(String attribute) {
        setAttribute(NODE_NAME, attribute);
    }
    
    public TreeNodeRecord(String number,String projectName,String initiatedOwner,String dateStarted, String dateEnded,String status,String nextActor, String latestComment ) {
        setAttribute(NUMBER, number);
        setAttribute(PROJECT_NAME, projectName);
        setAttribute(OWNER, initiatedOwner);
        setAttribute(STARTED, dateStarted);
        setAttribute(ENDED, dateEnded);
        setAttribute(STATUS, status);
        setAttribute(NEXT_ACTOR, nextActor);
        setAttribute(LATEST_COMMENT, latestComment);
    }
    
    public TreeNodeRecord(String fileId,String fileName,String type,String fileSize, String createdBy,Date dateCreated,Date dateModified) {
    	setAttribute(FILE_ID, fileId);
        setAttribute(FILE_NAME, fileName);
        setAttribute(DATE_MODIFIED, dateModified);
        setAttribute(TYPE, type);
        setAttribute(FILE_SIZE, fileSize);
        setAttribute(CREATED_BY, createdBy);
        setAttribute(DATE_CREATED, dateCreated);
    }

    public boolean contains(String inputValue, Tree tree) {
        if(getAttributeAsString(NODE_NAME).toLowerCase().contains(inputValue)) {
            return true;
        } else {
            TreeNode[] childes = tree.getChildren(this);
            for (TreeNode treeNode : childes) {
                TreeNodeRecord record = (TreeNodeRecord)treeNode;
                if(record.contains(inputValue, tree)){
                    return true;
                }
            }
        }
        return false;
    }
}