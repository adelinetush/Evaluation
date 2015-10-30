package com.planetsystems.monitoring.client.utils;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class ImagePickerWidget extends VLayout {
private IButton browseButton;
private Img myImage;
	public ImagePickerWidget() {
        super();
		Label label = new Label("Passport Photograph");
		label.setHeight("2%");
		addMember(label);
		myImage = new Img("hrms-logo.png", 48, 48);
		myImage.setAppImgDir("images/");
		myImage.setWidth("5%");
		myImage.setHeight("8%");
		addMember(myImage);
		
		
		browseButton =new IButton("Browse");
		setMembersMargin(2);
		addMember(browseButton);
	}
	public IButton getBrowseButton() {
		return browseButton;
	}
	public Img getMyImage() {
		return myImage;
	}
	
}
