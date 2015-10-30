/**
 * 
 */
package com.planetsystems.monitoring.client.widgets;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;

/**
 * @author Planet Dev005
 *
 */
public class TextArea extends TextAreaItem{
	
	public TextArea(String title,String title2){
		this.setWidth(100);
		this.setHeight(40);
		this.setAlign(Alignment.LEFT);
		this.setTitle(title2);
	}
}
