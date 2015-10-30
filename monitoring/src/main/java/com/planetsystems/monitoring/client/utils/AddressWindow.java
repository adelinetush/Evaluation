package com.planetsystems.monitoring.client.utils;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class AddressWindow  extends DynamicForm{
	private TextItem  pVillageItem,
	pSubCountyItem, pCountyItem, pDistrictItem, pCityItem,
	pCountryItem;
	
	public AddressWindow(){
		super();
		pVillageItem = new TextItem();
		pVillageItem.setName("pvillage");
		pVillageItem.setTitle("Village");

		
		pSubCountyItem = new TextItem();
		pSubCountyItem.setName("psubcounty");
		pSubCountyItem.setTitle("Sub-County");

		pCountyItem = new TextItem();
		pCountyItem.setName("pcounty");
		pCountyItem.setTitle("County");

		pDistrictItem = new TextItem();
		pDistrictItem.setName("pdistrict");
		pDistrictItem.setTitle("District");

		pCityItem = new TextItem();
		pCityItem.setName("city");
		pCityItem.setTitle("City");

		pCountryItem = new TextItem();
		pCountryItem.setName("pcountry");
		pCountryItem.setTitle("Country");

		
		setNumCols(8);
		setWrapItemTitles(true);
		setItems(pVillageItem, pSubCountyItem, pCountyItem,
				pDistrictItem, pCityItem, pCountryItem);
		for (FormItem FI : getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
	}

	public TextItem getpVillageItem() {
		return pVillageItem;
	}

	public TextItem getpSubCountyItem() {
		return pSubCountyItem;
	}

	public TextItem getpCountyItem() {
		return pCountyItem;
	}

	public TextItem getpDistrictItem() {
		return pDistrictItem;
	}

	public TextItem getpCityItem() {
		return pCityItem;
	}

	public TextItem getpCountryItem() {
		return pCountryItem;
	}

}
