/**
 * 
 */
package com.planetsystems.monitoring.client.widgets.buttons;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;

/**
 * @author Planet Developer 001
 * 
 */
public class SaveButton extends Button {

	public SaveButton(String title) {

		this.setIcon("icons/16/add.png");
		this.setTitle(title);

	}

	public SaveButton(String title, int width) {

		this.setIcon("icons/16/add.png");
		this.setTitle(title);
		this.setWidth(width);
		

	}

	public SaveButton(String title, String width) {

		this.setIcon("icons/16/add.png");
		this.setTitle(title);
		this.setWidth(width);

	}

}
