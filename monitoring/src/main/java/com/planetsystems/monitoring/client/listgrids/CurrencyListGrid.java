/**
 * 
 */
package com.planetsystems.monitoring.client.listgrids;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Planet Developer 001
 *
 */
public class CurrencyListGrid extends ListGrid{

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	private static final String CURRENCY_ID="currencyID";
	private static final String CURRENCY_ID_DISPLAY="Currency ID";
	private static final String CURRENCY_NAME="currencyName";
	private static final String CURRENCY_NAME_DISPLAY="Currency Name";
	private static final String CURRENCY_STATUS_ID="currencyStatusID";
	private static final String CURRENCY_STATUS_ID_DISPLAY="Status";
	private static final String CURRENCY_STATUS_NAME="currencyStatus";
	private static final String CURRENCY_STATUS_NAME_DISPLAY="Currency Status";
	private static final String CURRENCY_SYMBOL="currencySymbol";
	private static final String CURRENCY_SYMBOL_DISPLAY="Currency Symbol";
	private static final String EMPTY_FIELD = "emptyField"; 
	private static final String EMPTY_FIELD_DISPLAY_NAME = " "; 
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	private static final String CREATED_BY= "createdBy";
	private static final String CREATED_BY_DISPLAY= "Created By";
	private static final String CHANGED_BY= "changedBy";
	private static final String CHANGED_BY_DISPLAY= "ChangedBy";
	
	private static final int ICON_COLUMN_LENGTH = 27;

	
	public CurrencyListGrid(){
		super();
		
		 // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX);
	    
	    ListGridField currencyIDField = new ListGridField(CURRENCY_ID, CURRENCY_ID_DISPLAY);  
	    currencyIDField.setHidden(true);
	    ListGridField currencyStatusIDField = new ListGridField(CURRENCY_STATUS_ID, CURRENCY_STATUS_ID_DISPLAY); 
	    currencyStatusIDField.setHidden(true);
	    
	    ListGridField currencyNameField = new ListGridField(CURRENCY_NAME, CURRENCY_NAME_DISPLAY); 
	    currencyNameField.setWidth("20%");//
	    ListGridField currencyStatusField = new ListGridField(CURRENCY_STATUS_NAME, CURRENCY_STATUS_NAME_DISPLAY);
	    currencyStatusField.setWidth("20%");
	    ListGridField currencySymbolField = new ListGridField(CURRENCY_SYMBOL, CURRENCY_SYMBOL_DISPLAY); 
	    currencySymbolField.setWidth("20%");
	    ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);
	    emptyField.setWidth("*");
	    
	    ListGridField createdByField = new ListGridField(CREATED_BY, CREATED_BY_DISPLAY); 
		createdByField.setWidth("20%");
		createdByField.setWrap(true);
		ListGridField changedByField = new ListGridField(CHANGED_BY, CHANGED_BY_DISPLAY); 
		changedByField.setWidth("20%");
		changedByField.setWrap(true);
		
		this.setCanPickFields(false);
		this.setShowRowNumbers(true);
		
	    this.setFields(iconField,currencyIDField,currencyStatusIDField,currencyNameField,currencyStatusField,currencySymbolField,
	    		createdByField,changedByField);
	}
}
