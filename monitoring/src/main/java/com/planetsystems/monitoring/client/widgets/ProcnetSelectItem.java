/**
 * 
 */
package com.planetsystems.monitoring.client.widgets;

import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Planet Developer 001
 *
 */
public class ProcnetSelectItem extends SelectItem{
	
	public ListGridField itemCategoryIDField = new ListGridField("id");
    public ListGridField itemCategoryNameField = new ListGridField("name");  
    public ListGridField itemCategoryCodeField = new ListGridField("code");
 
	public ProcnetSelectItem(String name,String title,int width,int height){
		super();
		
		itemCategoryIDField.setHidden(true);
		
	    this.setWidth(width);
        this.setWidth(height);  
        this.setTitle(title);  
        this.setName(name);
        this.setValueField("id");  
        this.setDisplayField("code");  
        this.setPickListWidth(450);  
        this.setPickListFields(itemCategoryIDField,itemCategoryCodeField,itemCategoryNameField);
        this.setFilterLocally(true);
        this.setSortField(itemCategoryCodeField.getName());
        
        
	}
	
	
	
	public ProcnetSelectItem(){
		super();

        
        
	}
}
