package com.planetsystems.monitoring.client.views.project;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class DocumentsViewTreeGrid extends TreeGrid {
	
		    private Tree tree;
		    private TreeNodeRecord[] records;
		    
		    public DocumentsViewTreeGrid() {
		        tree = new Tree();
		      
		        
		        TreeGridField numberField = new TreeGridField(TreeNodeRecord.NUMBER);
		        TreeGridField fileField = new TreeGridField(TreeNodeRecord.FILE_NAME);
		        TreeGridField dateModifiedField = new TreeGridField(TreeNodeRecord.DATE_MODIFIED);
		        TreeGridField typeField = new TreeGridField(TreeNodeRecord.TYPE);
		        TreeGridField fileSizeField = new TreeGridField(TreeNodeRecord.FILE_SIZE);
		        TreeGridField createdByField = new TreeGridField(TreeNodeRecord.CREATED_BY);
		        TreeGridField dateCreatedField = new TreeGridField(TreeNodeRecord.DATE_CREATED);
		        
		        setFields(numberField,fileField,dateModifiedField,typeField,fileSizeField,createdByField,dateCreatedField);
		    }
		    
		

		 public void updateData() {
		        records = new TreeNodeRecord[2];

		        for (int i = 0; i < 2; i++) {
		            records[i] = new TreeNodeRecord("To reduce the mortality rate of infants by 20%",null,null,null,null,null,null,null);
		            
		            TreeNodeRecord[] leafs = new TreeNodeRecord[2];
		            for (int j = 0; j < 1; j++) {
		                leafs[j] = new TreeNodeRecord("To procure insecticide treated nets from Europe",null,null,null,null,null,null,null);
		            }
		            records[i].setChildren(leafs);
		        }
		        tree.setData(records);
		        this.setData(tree);
		    }
		}

	