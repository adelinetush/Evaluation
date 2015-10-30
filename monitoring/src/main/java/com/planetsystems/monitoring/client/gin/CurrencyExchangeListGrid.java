/**
 * 
 */
package com.planetsystems.monitoring.client.gin;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Planet Developer 001
 *
 */
public class CurrencyExchangeListGrid extends ListGrid{

	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME="#";
	private static final String CURRENCY_EXCHANGE_ID="currencyExchangeID";
	private static final String CURRENCY_EXCHANGE_ID_DISPLAY="Currency Exchange ID";
	private static final String CURRENCY_TO_ID="currencyToID";
	private static final String CURRENCY_TO_ID_DISPLAY="Currency To ID";
	private static final String CURRENCY_FROM_ID="currencyFromID";
	private static final String CURRENCY_FROM_ID_DISPLAY="Currency From ID";
	private static final String CURRENCY_TO_NAME="currencyToName";
	private static final String CURRENCY_TO_NAME_DISPLAY="Currency To Name";
	private static final String CURRENCY_FROM_NAME="currencyFromName";
	private static final String CURRENCY_FROM_NAME_DISPLAY="Currency From Name";
	private static final String CURRENCY_RATE="currencyRate";
	private static final String CURRENCY_RATE_DISPLAY="Exchange Rate";
	private static final String FINANCIAL_YEAR_ID="finYearID";
	private static final String FINANCIAL_YEAR_ID_DISPLAY="Financial Year ID";
	private static final String FINANCIAL_YEAR_NAME="finYearName";
	private static final String FINANCIAL_YEAR_NAME_DISPLAY="Financial Year Name";
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
	private static final int CURRENCY_TO_ID_COLUMN_LENGTH = 0;
	private static final int CURRENCY_EXCHANGE_ID_COLUMN_LENGTH = 0;
	private static final int CURRENCY_FROM_ID_COLUMN_LENGTH = 0;
	private static final int CURRENCY_TO_NAME_COLUMN_LENGTH = 100;
	private static final int CURRENCY_FROM_NAME_COLULMN_LENGTH=100;
	private static final int CURRENCY_RATE_COLUMN_LENGTH=100;
	private static final int FINANCIAL_YEAR_ID_COLUMN_LENGTH = 0; 
	private static final int FINANCIAL_YEAR_NAME_COLUMN_LENGTH = 150;
	
	public CurrencyExchangeListGrid(){
		super();
		
		 // initialize the List Grid fields  
	    ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME, ICON_COLUMN_LENGTH);
	    iconField.setImageSize(16); 
	    iconField.setAlign(Alignment.CENTER);
	    iconField.setType(ListGridFieldType.IMAGE);  
	    iconField.setImageURLPrefix(URL_PREFIX);  
	    iconField.setImageURLSuffix(URL_SUFFIX);
	    
	    ListGridField currencyExchangeIDField = new ListGridField(CURRENCY_EXCHANGE_ID, CURRENCY_EXCHANGE_ID_DISPLAY,
	    		CURRENCY_EXCHANGE_ID_COLUMN_LENGTH);  
	    currencyExchangeIDField.setHidden(true);
	    
	    ListGridField currencyToIDField = new ListGridField(CURRENCY_TO_ID, CURRENCY_TO_ID_DISPLAY,
	    		CURRENCY_TO_ID_COLUMN_LENGTH); 
	    currencyToIDField.setHidden(true);
	    
	    ListGridField currencyFromIDField = new ListGridField(CURRENCY_FROM_ID, CURRENCY_FROM_ID_DISPLAY,
	    		CURRENCY_FROM_ID_COLUMN_LENGTH); 
	    currencyFromIDField.setHidden(true);
	    ListGridField currencyToNameField = new ListGridField(CURRENCY_TO_NAME, CURRENCY_TO_NAME_DISPLAY); 
	    currencyToNameField.setWidth("15%");
	    ListGridField currencyFromNameField = new ListGridField(CURRENCY_FROM_NAME, CURRENCY_FROM_NAME_DISPLAY); 
	    currencyFromNameField.setWidth("15%");
	    ListGridField exchandeRateField = new ListGridField(CURRENCY_RATE,CURRENCY_RATE_DISPLAY);
	    exchandeRateField.setWidth("15%");
	    ListGridField financialYearIDField = new ListGridField(FINANCIAL_YEAR_ID, FINANCIAL_YEAR_ID_DISPLAY); 
	    financialYearIDField.setHidden(true);
	    ListGridField financialYearNameField = new ListGridField(FINANCIAL_YEAR_NAME, FINANCIAL_YEAR_NAME_DISPLAY); 
	    financialYearNameField.setWidth("15%");
	    ListGridField emptyField = new ListGridField(EMPTY_FIELD, EMPTY_FIELD_DISPLAY_NAME);
	    emptyField.setWidth("20%");
	    
	    ListGridField createdByField = new ListGridField(CREATED_BY, CREATED_BY_DISPLAY); 
		createdByField.setWidth("20%");
		createdByField.setWrap(true);
		ListGridField changedByField = new ListGridField(CHANGED_BY, CHANGED_BY_DISPLAY); 
		changedByField.setWidth("20%");
		changedByField.setWrap(true);
		
		this.setCanPickFields(false);
		this.setShowRowNumbers(true);
	    this.setFields(currencyExchangeIDField,currencyToIDField,currencyToNameField,currencyFromIDField,
	    		currencyFromNameField,exchandeRateField,financialYearIDField,financialYearNameField,createdByField,changedByField);
}
}