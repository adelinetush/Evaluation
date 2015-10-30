package com.planetsystems.monitoring.client.utils;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class PersonalDetailsWindow extends DynamicForm {

	private TextItem firstNameItem, lastNameItem, familyNameItem,
			nationalityItem, tribeItem, mobileItem, emailAddressItem;

	private ComboBoxItem salutationItem, genderItem;

	public PersonalDetailsWindow() {
            super();
		salutationItem = new ComboBoxItem("salutation", "Title");
		salutationItem.setValueMap("Mr", "Ms", "Mrs", "Dr", "Eng", "Prof",
				"Ps", "Rev", "Rev Fr.", "Bsp");

		
		firstNameItem = new TextItem();
		firstNameItem.setName("firstname");
		firstNameItem.setTitle("First Name");

		lastNameItem = new TextItem();
		lastNameItem.setName("surname");
		lastNameItem.setTitle("Surname");

		familyNameItem = new TextItem();
		familyNameItem.setName("familyname");
		familyNameItem.setTitle("Father's/Husband's Name");

		genderItem = new ComboBoxItem("gender", "Gender");
		genderItem.setValueMap("Male", "Female");

		nationalityItem = new TextItem();
		nationalityItem.setName("nationality");
		nationalityItem.setTitle("Nationality");

		tribeItem = new TextItem();
		tribeItem.setName("tribe");
		tribeItem.setTitle("Tribe");

		mobileItem = new TextItem();
		mobileItem.setName("mobile");
		mobileItem.setTitle("Mobile Number");

		emailAddressItem = new TextItem();
		emailAddressItem.setName("emailAddress");
		emailAddressItem.setTitle("Email Address");

		setNumCols(8);
		setWrapItemTitles(true);
		setItems(salutationItem, firstNameItem, lastNameItem, familyNameItem,
				genderItem, nationalityItem, tribeItem, mobileItem,
				emailAddressItem);
		for (FormItem FI : getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
	}

}
