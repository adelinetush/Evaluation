package com.planetsystems.monitoring.client.views.project;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class TreeGridInterface extends TreeGrid {
		 private Tree tree;
		    private TreeNodeRecord[] records;
		    
		    public TreeGridInterface() {
		        tree = new Tree();
		      
		        
		        TreeGridField field = new TreeGridField(TreeNodeRecord.NODE_NAME);
		        setFields(field);
		    }
		    
		

		    public void updateData() {
		    	
		    	
		    	
		        records = new TreeNodeRecord[2];

		        for (int i = 0; i < 2; i++) {
		            records[i] = new TreeNodeRecord("To reduce the mortality rate of infants by 20%");
		            
		            TreeNodeRecord[] leafs = new TreeNodeRecord[2];
		            for (int j = 0; j < 1; j++) {
		                leafs[j] = new TreeNodeRecord("To procure insecticide treated nets from Europe");
		            }
		            records[i].setChildren(leafs);
		        }
		        tree.setData(records);
		        this.setData(tree);
		    }
		}

	