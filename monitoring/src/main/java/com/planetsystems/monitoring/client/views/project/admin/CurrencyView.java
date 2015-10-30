/**
 * 
 */
package com.planetsystems.monitoring.client.views.project.admin;

import java.util.LinkedHashMap;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.events.CurrencyUiHandlers;
import com.planetsystems.monitoring.client.gin.CurrencyExchangeListGrid;
import com.planetsystems.monitoring.client.listgrids.CurrencyListGrid;
import com.planetsystems.monitoring.client.presenters.project.admin.CurrencyPresenter;
import com.planetsystems.monitoring.client.widgets.StatusBar;
import com.planetsystems.monitoring.client.widgets.ToolBar;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;
import com.planetsystems.monitoring.model.Status;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * @author Planet Developer 001
 * 
 */
public class CurrencyView extends ViewWithUiHandlers<CurrencyUiHandlers> implements CurrencyPresenter.MyView {

	private static final String CONTEXT_AREA_WIDTH = "100%";

	private HLayout mainPanel;
	private VLayout panel;
	private final ToolBar toolBarCur;
	private final ToolBar toolBarCurRate;
	private final StatusBar statusBar;
	private final Button cancelButton1 = new Button("Cancel");
	private final Button saveButton1 = new Button("Save");
	private final Button editButton1 = new Button("Edit");
	private final Button deleteButton1 = new Button("Delete");
	private final TabSet tabSet;
	private Tab tabCurrency;
	private Tab tabCurrencyExchange;

	private final Button cancelButton2 = new Button("Cancel");
	private final Button saveButton2 = new Button("Save");
	private final Button editButton2 = new Button("Edit");
	private final Button deleteButton2 = new Button("Delete");
	private final Button loadExchangeRates = new Button("Load Rates");

	private final CurrencyListGrid currencyListGrid;
	private final CurrencyExchangeListGrid currencyExchangeListGrid;

	HLayout HL_BUTTONS1 = new HLayout();
	HLayout HL_BUTTONS2 = new HLayout();
	HLayout HL_MAIN = new HLayout();
	VLayout VL_CURRENCY = new VLayout();
	VLayout VL_EXCHANGE = new VLayout();

	DynamicForm DF_CURRENCY = new DynamicForm();
	DynamicForm DF_EXCHANGE = new DynamicForm();

	TextItem currencyNameText = new TextItem("currencyNameText", "Currency Name");
	// TextItem currencyStatusText = new
	// TextItem("currencyStatusText","Currency Status");
	TextItem currencySymbolText = new TextItem("currencySymbolText", "Currency Symbol");
	ComboBoxItem currencyStatusCombo = new ComboBoxItem("currencyStatusCombo", "Currency Status");
	ComboBoxItem currencyFromCombo;
	ComboBoxItem currencyToCombo = new ComboBoxItem("currencyToCombo", "Currency To");
	ComboBoxItem currencyFinYearCombo = new ComboBoxItem("currencyFinYearCombo", "Financial Year");
	TextItem rateNumberItem = new TextItem("rateNumberItem", "Exchange Rate");

	@Inject
	public CurrencyView(ToolBar toolBarCur, ToolBar toolBarCurRate, StatusBar statusBar, final CurrencyListGrid currencyListGrid, final CurrencyExchangeListGrid currencyExchangeListGrid, TabSet tabSet) {
		this.currencyListGrid = currencyListGrid;
		this.currencyExchangeListGrid = currencyExchangeListGrid;
		this.toolBarCur = toolBarCur;
		this.toolBarCurRate = toolBarCurRate;
		this.statusBar = statusBar;
		this.tabSet = tabSet;
		currencyFromCombo = new ComboBoxItem("currencyFromCombo", "Currency From");
		
		Label headerAdd = new Label();
		headerAdd = new Label();
		headerAdd.setStyleName("crm-ContextArea-Header-Label");
		headerAdd.setHeight("1%");
		headerAdd.setContents("Currency Setup & Management");
		headerAdd.setWidth("100%");
		headerAdd.setAlign(Alignment.LEFT);

		panel = new VLayout();
		// panel.addStyleName("crm-ContextArea");
		panel.setWidth(CONTEXT_AREA_WIDTH);
		panel.setHeight100();
		panel.setBackgroundColor("white");

		this.currencyListGrid.setHeight("85%");
		this.currencyExchangeListGrid.setHeight("85%");

		tabSet.setWidth100();
		tabSet.setHeight100();

		tabCurrency = new Tab();
		tabCurrency.setTitle("Currency ");
		tabCurrency.setCanClose(false);
		tabCurrency.setID("currencyTab");

		tabCurrencyExchange = new Tab();
		tabCurrencyExchange.setTitle("Currency Exchange ");
		tabCurrencyExchange.setCanClose(false);
		tabCurrencyExchange.setID("currencyExchangeTab");

		VL_CURRENCY.setHeight100();
		VL_CURRENCY.setWidth100();
		VL_CURRENCY.setMembersMargin(10);

		VL_EXCHANGE.setHeight100();
		VL_EXCHANGE.setWidth100();
		VL_EXCHANGE.setMembersMargin(10);

		HL_MAIN.setHeight100();
		HL_MAIN.setWidth100();
		HL_MAIN.setBackgroundColor("white");
		HL_MAIN.setMembersMargin(5);

		HL_MAIN.setMembers(VL_CURRENCY, VL_EXCHANGE);

		// DF_CURRENCY = new DynamicForm();

		DF_CURRENCY.setWidth100();
		DF_CURRENCY.setHeight("10%");
		DF_CURRENCY.setWrapItemTitles(false);
		DF_CURRENCY.setNumCols(6);
		DF_CURRENCY.setShowEdges(true);
		DF_CURRENCY.setEdgeSize(3);

		currencyNameText.setRequired(true);
		currencyStatusCombo.setRequired(true);
		currencySymbolText.setRequired(true);

		DF_CURRENCY.setItems(currencyNameText, currencyStatusCombo, currencySymbolText);

		for (FormItem FI : DF_CURRENCY.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}

		// DF_EXCHANGE = new DynamicForm();

		DF_EXCHANGE.setWidth100();
		DF_EXCHANGE.setHeight("10%");
		DF_EXCHANGE.setWrapItemTitles(false);
		DF_EXCHANGE.setNumCols(6);
		DF_EXCHANGE.setShowEdges(true);
		DF_EXCHANGE.setEdgeSize(3);

		currencyFinYearCombo.setRequired(true);
		currencyFromCombo.setRequired(true);
		currencyToCombo.setRequired(true);
		rateNumberItem.setRequired(true);

		DF_EXCHANGE.setItems(currencyFinYearCombo, currencyFromCombo, rateNumberItem);

		for (FormItem FI : DF_EXCHANGE.getFields()) {
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
		// HL_BUTTONS = new HLayout();
		HL_BUTTONS1.setMembers(saveButton1, editButton1, deleteButton1, cancelButton1);
		HL_BUTTONS1.setHeight(50);
		HL_BUTTONS1.setMembersMargin(5);
		HL_BUTTONS1.setAlign(Alignment.CENTER);

		currencyListGrid.setHeight("*");
		VL_CURRENCY.setMembers(toolBarCur, currencyListGrid, DF_CURRENCY, HL_BUTTONS1);

		// HL_BUTTONS = new HLayout();
		HL_BUTTONS2.setMembers(saveButton2, editButton2, deleteButton2, cancelButton2);
		HL_BUTTONS2.setHeight(50);
		HL_BUTTONS2.setMembersMargin(5);
		HL_BUTTONS2.setAlign(Alignment.CENTER);

		currencyExchangeListGrid.setHeight("*");
		VL_EXCHANGE.setMembers(toolBarCurRate, currencyExchangeListGrid, DF_EXCHANGE, HL_BUTTONS2);

		tabCurrency.setPane(VL_CURRENCY);
		tabCurrencyExchange.setPane(VL_EXCHANGE);

		tabSet.addTab(tabCurrency);
		tabSet.addTab(tabCurrencyExchange);

		GWT.log("Adding panel...");
		
		panel.addMember(headerAdd);
		panel.addMember(tabSet);
		
		
		mainPanel=new HLayout();
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		mainPanel.addMember(panel);

		// Load Currency Status
		LoadStatusCombo();

		saveButton1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				DF_CURRENCY.validate();
				if (currencyNameText.getValue() == null) {
					SC.say("Currency Name cannot be Empty");
					currencyNameText.focusInItem();
				}

				else if (currencyStatusCombo.getValue() == null) {
					SC.say("Currency Status cannot be Empty");
					currencyStatusCombo.focusInItem();
				}

				else if (currencySymbolText.getValue() == null) {
					SC.say("Currency Symbol cannot be Empty");
					currencySymbolText.focusInItem();
				} else {

					Currency currency = new Currency();
					currency.setCur_name(currencyNameText.getValueAsString());
					currency.setCur_symbol(currencySymbolText.getValueAsString());
					currency.setCur_status(Status.valueOf(currencyStatusCombo.getValueAsString()));

					if (getUiHandlers() != null) {
						getUiHandlers().onSaveCurrencyButtonClicked(currency);
					}
				}

			}

		});
		editButton1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				DF_CURRENCY.validate();
				GWT.log("Edit Currency Button Clicked...", null);

				if (currencyListGrid.getSelectedRecord() == null) {
					SC.say("Select Record to Edit");
					currencyListGrid.focus();
				} else if (currencyNameText.getValue() == null) {
					SC.say("Currency Name cannot be Empty");
					currencyNameText.focusInItem();
				} else if (currencySymbolText.getValue() == null) {
					SC.say("Currency Symbol cannot be Empty");
					currencySymbolText.focusInItem();
				} else if (currencyStatusCombo.getValue() == null) {
					SC.say("Currency Status cannot be Empty");
					currencyStatusCombo.focusInItem();
				} else {

					final Currency currency = new Currency();
					currency.setId(currencyListGrid.getSelectedRecord().getAttribute("currencyID"));
					currency.setCur_name(currencyNameText.getValueAsString());
					currency.setCur_symbol(currencySymbolText.getValueAsString());
					currency.setCur_status(Status.valueOf(currencyStatusCombo.getValueAsString()));

					SC.ask("Edit Currency", "Do you Really want to Edit this Currency", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {
									getUiHandlers().onEditCurrencyButtonClicked(currency);
								}
							} else {

							}

						}

					});

				}

			}

		});
		deleteButton1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (currencyListGrid.getSelectedRecord() == null) {
					SC.say("Select Record to Delete");
					currencyListGrid.focus();
				} else {

					final Currency currency = new Currency();
					currency.setId(currencyListGrid.getSelectedRecord().getAttribute("currencyID"));

					SC.ask("Delete Currency", "Do you Really want to Delete this Currency", new BooleanCallback() {

						public void execute(Boolean value) {
							if (value != null && value) {

								if (getUiHandlers() != null) {
									getUiHandlers().onDeleteCurrencyButtonClicked(currency);
								}

							} else {

							}

						}

					});

				}
			}

		});
		saveButton2.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				DF_EXCHANGE.validate();
				if (currencyFinYearCombo.getValue() == null) {
					SC.say("Select Financial Year");
					currencyFinYearCombo.focusInItem();
				}
				/*
				 * if(currencyStatusCombo.getValue() == null){
				 * SC.say("Currency Status cannot be Empty");
				 * currencyStatusCombo.focusInItem(); }
				 */
				else if (currencyFromCombo.getValue() == null) {
					SC.say("Select Currency From");
					currencyFromCombo.focusInItem();
				}/*
				 * else if (currencyToCombo.getValue() == null) {
				 * SC.say("Select Currency To Convert into");
				 * currencyToCombo.focusInItem();// rateNumberItem }
				 */else if (rateNumberItem.getValue() == null) {
					SC.say("Enter a Rate");
					rateNumberItem.focusInItem();// rateNumberItem
				} else {

					CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();

					// Set Currency From
					Currency currencyFrom = new Currency();
					currencyFrom.setId(currencyFromCombo.getValueAsString());

					
					// currencyExchangeRate.setTocurrency(currencyTo);
					currencyExchangeRate.setFromcurrency(currencyFrom);
					// currencyExchangeRate.setRate(rate);

					try {
						currencyExchangeRate.setRate(Double.parseDouble(rateNumberItem.getValueAsString().replace(",", "")));
					} catch (Exception e) {
						SC.warn("Error Parsing String" + e.getMessage(), new BooleanCallback() {

							public void execute(Boolean value) {

							}

						});
					}
					if (getUiHandlers() != null) {
						getUiHandlers().onSaveExchangeButtonClicked(currencyExchangeRate);
					}
				}
				// ClearExchange();
			}

		});
		editButton2.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				DF_EXCHANGE.validate();

				if (currencyExchangeListGrid.getSelectedRecord() == null) {
					SC.say("Select Record to Edit");
					currencyExchangeListGrid.focus();
				} else if (currencyFinYearCombo.getValue() == null) {
					SC.say("Select Financial Year");
					currencyFinYearCombo.focusInItem();
				}
				/*
				 * if (currencyStatusCombo.getValue() == null) {
				 * SC.say("Currency Status cannot be Empty");
				 * currencyStatusCombo.focusInItem(); }
				 */else if (currencyFromCombo.getValue() == null) {
					SC.say("Select Currency From");
					currencyFromCombo.focusInItem();
				}/*
				 * else if (currencyToCombo.getValue() == null) {
				 * SC.say("Select Currency To Convert into");
				 * currencyToCombo.focusInItem();// rateNumberItem }
				 */else if (rateNumberItem.getValue() == null) {
					SC.say("Enter a Rate");
					rateNumberItem.focusInItem();// rateNumberItem
				} else {
					final CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
					currencyExchangeRate.setId(currencyExchangeListGrid.getSelectedRecord().getAttribute("currencyExchangeID"));
					currencyExchangeRate.setRate(Double.parseDouble((String) rateNumberItem.getValue()));

				
					// Set Currency To
					Currency currencyTo = new Currency();
					currencyTo.setId(currencyExchangeListGrid.getSelectedRecord().getAttribute("currencyToID"));

					// Set Currency From
					Currency currencyFrom = new Currency();
					currencyFrom.setId(currencyFromCombo.getValueAsString());

					currencyExchangeRate.setTocurrency(currencyTo);
					currencyExchangeRate.setFromcurrency(currencyFrom);
					System.out.println("Tracking currency from currency view: " + currencyFrom.getId());
					System.out.println("Tracking currency To currency view: " + currencyTo.getId());
					// currencyExchangeRate.setRate(rate);

					try {
						currencyExchangeRate.setRate(Double.parseDouble(rateNumberItem.getValueAsString().replace(",", "")));
					} catch (Exception e) {
						SC.warn("Error Parsing String" + e.getMessage(), new BooleanCallback() {

							public void execute(Boolean value) {

							}

						});
					}

					SC.ask("Edit Exchange Rate", "Do you Really want to Edit Currency Exchange Rate", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {
									getUiHandlers().onEditExchangeButtonClicked(currencyExchangeRate);
								}
							} else {

							}

						}

					});

				}

			}

		});
		deleteButton2.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				GWT.log("Delete Currency Exchange Button Clicked...", null);

				if (currencyExchangeListGrid.getSelectedRecord() == null) {
					SC.say("Select Record to Delete");
					currencyExchangeListGrid.focus();
				} else {

					final CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();

					currencyExchangeRate.setId(currencyExchangeListGrid.getSelectedRecord().getAttribute("currencyExchangeID"));

					
					// Set Currency From
					Currency currencyFrom = new Currency();
					currencyFrom.setId(currencyFromCombo.getValueAsString());

					// currencyExchangeRate.setTocurrency(currencyTo);
					currencyExchangeRate.setFromcurrency(currencyFrom);
					// currencyExchangeRate.setRate(rate);

					try {
						currencyExchangeRate.setRate(Double.parseDouble(rateNumberItem.getValueAsString().replace(",", "")));
					} catch (Exception e) {
						SC.warn("Error Parsing String" + e.getMessage(), new BooleanCallback() {

							public void execute(Boolean value) {

							}

						});
					}

					SC.ask("Delete Exchange Rate", "Do you Really want ot Delete Currency Exchange Rate", new BooleanCallback() {

						public void execute(Boolean value) {

							if (value != null && value) {

								if (getUiHandlers() != null) {
									getUiHandlers().onDeleteExchangeButtonCliked(currencyExchangeRate);
								}
							} else {

							}

						}
					});

				}

			}

		});
		cancelButton2.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				ClearExchange();
			}

		});

		cancelButton1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				ClearCurrency();

			}

		});

		currencyFromCombo.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				// SC.say("Selected Currency Id:"+event.getValue().toString());

			}

		});

		currencyToCombo.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				// SC.say("Selected Currency Id:"+event.getValue().toString());

			}

		});

		currencyListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				currencyNameText.setValue(event.getRecord().getAttribute("currencyName"));
				currencyStatusCombo.setValue(event.getRecord().getAttribute("currencyStatus"));
				currencySymbolText.setValue(event.getRecord().getAttribute("currencySymbol"));

			}

		});

		bindCustomUiHandlers();

		tabSet.addTabSelectedHandler(new TabSelectedHandler() {

			public void onTabSelected(TabSelectedEvent event) {

				if (event.getID().contentEquals("currencyExchangeTab")) {

				} else if (event.getID().contentEquals("currencyExchangeTab")) {

				}
			}

		});

		this.currencyExchangeListGrid.addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {

				// currencyFinYearCombo,currencyFromCombo,currencyToCombo,rateNumberItem

				currencyFinYearCombo.setValue(event.getRecord().getAttribute("finYearID"));
				currencyFromCombo.setValue(event.getRecord().getAttribute("currencyFromID"));
				/*
				 * currencyToCombo.setValue(event.getRecord()
				 * .getAttribute("currencyToID"));
				 */
				rateNumberItem.setValue(event.getRecord().getAttribute("currencyRate"));
			}

		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.View#asWidget()
	 */
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return mainPanel;
	}

	protected void bindCustomUiHandlers() {

		initToolBar();
	}

	protected void initToolBar() {

		toolBarCur.addButton(ToolBar.PRINT_PREVIEW_BUTTON, Monitoring.getConstants().printPreviewButtonTooltip(), null);

		toolBarCur.addSeparator();

		toolBarCur.addButton(ToolBar.EXPORT_BUTTON, Monitoring.getConstants().exportButtonTooltip(), null);

		toolBarCur.addSeparator();

		toolBarCur.addButton(ToolBar.REPORTS_BUTTON, Monitoring.getConstants().reportsButtonTooltip(), null);

		toolBarCur.addSeparator();

		toolBarCur.addButton(ToolBar.REFRESH_BUTTON, Monitoring.getConstants().refreshButtonTooltip(), Monitoring.getConstants().refreshButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (getUiHandlers() != null) {
					getUiHandlers().onLoadCurrencyButtonClicked();
				}
			}
		});

		// Currency Exchange

		toolBarCurRate.addButton(ToolBar.PRINT_PREVIEW_BUTTON, Monitoring.getConstants().printPreviewButtonTooltip(), null);

		toolBarCurRate.addSeparator();

		toolBarCurRate.addButton(ToolBar.EXPORT_BUTTON, Monitoring.getConstants().exportButtonTooltip(), null);

		toolBarCurRate.addSeparator();

		toolBarCurRate.addButton(ToolBar.REPORTS_BUTTON, Monitoring.getConstants().reportsButtonTooltip(), null);

		toolBarCurRate.addSeparator();

		toolBarCurRate.addButton(ToolBar.REFRESH_BUTTON, Monitoring.getConstants().refreshButtonTooltip(), new ClickHandler() {
			public void onClick(ClickEvent event) {

			}
		});
	}

	public void LoadStatusCombo() {

	}

	public void ClearExchange() {

		DF_EXCHANGE.clearValues();

	}

	public void ClearCurrency() {
		DF_CURRENCY.clearValues();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#setServerResponse(java.lang.String)
	 */
	public void setServerResponse(String serverResponse) {
		SC.say(serverResponse);

	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrency() Currency To Save
	 * 
	 * 
	 * public Currency getCurrency() {
	 * 
	 * Currency currency = new Currency();
	 * currency.setCur_name(currencyNameText.getValueAsString());
	 * currency.setCur_symbol(currencySymbolText.getValueAsString());
	 * 
	 * Status state = new Status();
	 * state.setId(currencyStatusCombo.getValueAsString());
	 * 
	 * currency.setCur_status(state);// (CurrencyStatus) //
	 * currencyStatusCombo.getValue()
	 * 
	 * return currency; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrencyExchangeRate()
	 * 
	 * public CurrencyExchangeRate getCurrencyExchangeRate() {
	 * 
	 * CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
	 * 
	 * // Set Financial Year FinancialYear financialYear = new FinancialYear();
	 * financialYear.setId(currencyFinYearCombo.getValueAsString());//
	 * 402880ea4496e1eb014496e391190001 //
	 * //currencyFinYearCombo.getValueAsString()
	 * 
	 * // Set Currency To Currency currencyTo = new Currency();
	 * currencyTo.setId(currencyToCombo.getValueAsString());
	 * 
	 * // Set Currency From Currency currencyFrom = new Currency();
	 * currencyFrom.setId(currencyFromCombo.getValueAsString());
	 * 
	 * currencyExchangeRate.setFinancialyear(financialYear);
	 * currencyExchangeRate.setTocurrency(currencyTo);
	 * currencyExchangeRate.setFromcurrency(currencyFrom); //
	 * currencyExchangeRate.setRate(rate);
	 * 
	 * try { currencyExchangeRate.setRate(Double.parseDouble(rateNumberItem
	 * .getValueAsString().replace(",", ""))); } catch (Exception e) {
	 * SC.warn("Error Parsing String" + e.getMessage(), new BooleanCallback() {
	 * 
	 * public void execute(Boolean value) {
	 * 
	 * }
	 * 
	 * }); }
	 * 
	 * return currencyExchangeRate; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#setServerResponseCurrencies(java.util.List)
	 * 
	 * public void setServerResponseCurrencies(List<Currency> currencies) {
	 * 
	 * try { //
	 * this.listGrid.setData((ListGridRecord[])UsersData.getUserRecords(users));
	 * ListGridRecord[] list = new ListGridRecord[currencies.size()]; int
	 * listCount = 0; ListGridRecord record;
	 * 
	 * LinkedHashMap<String, String> valueMap = new LinkedHashMap<String,
	 * String>();
	 * 
	 * for (Currency currecy : currencies) {
	 * 
	 * record = new ListGridRecord(); record.setAttribute("icon", "");
	 * record.setAttribute("currencyID", currecy.getId());
	 * record.setAttribute("currencyName", currecy.getCur_name());
	 * record.setAttribute("currencyStatusID", currecy.getCurrencyStatusId());
	 * record.setAttribute("currencyStatus", currecy.getCurrencyStatusName());
	 * record.setAttribute("currencySymbol", currecy.getCur_symbol());
	 * record.setAttribute("emptyField", "");
	 * 
	 * if (currecy.getChangedBy() != null) {
	 * 
	 * record.setAttribute("changedBy", currecy.getChangedBy() .getFullName());
	 * 
	 * } else {
	 * 
	 * //
	 * 
	 * }
	 * 
	 * if (currecy.getCreatedBy() != null) {
	 * 
	 * record.setAttribute("createdBy", currecy.getCreatedBy() .getFullName());
	 * 
	 * } else {
	 * 
	 * }
	 * 
	 * valueMap.put(currecy.getId(), currecy.getCur_name());
	 * 
	 * list[listCount] = record; listCount++;
	 * 
	 * }
	 * 
	 * this.currencyListGrid.setData(list);
	 * currencyFromCombo.setValueMap(valueMap);
	 * currencyToCombo.setValueMap(valueMap);
	 * 
	 * } catch (Exception e) { SC.say("Error Display" + e.getMessage());
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#setServerResponseExchangeRate(java.util.List)
	 * 
	 * public void setServerResponseExchangeRate(CurrencyExchangeRate rates) {
	 * 
	 * ListGridRecord[] list = new ListGridRecord[1];
	 * 
	 * // for(){ ListGridRecord record = new ListGridRecord();
	 * record.setAttribute("icon", "calendar");
	 * record.setAttribute("currencyExchangeID", rates.getId());
	 * record.setAttribute("currencyToID", rates.getToCurrencyId());
	 * record.setAttribute("currencyFromID", rates.getFromCurrencyId());
	 * record.setAttribute("currencyToName", rates.getToCurrencyName());
	 * record.setAttribute("currencyFromName", rates.getFromCurrencyName());
	 * record.setAttribute("finYearID", rates.getFinancialYearId());
	 * record.setAttribute("finYearName", rates.getFinancialYearName());
	 * record.setAttribute("currencyRate", rates.getRate());
	 * 
	 * if (rates.getChangedBy() != null) {
	 * 
	 * record.setAttribute("changedBy", rates.getChangedBy() .getFullName());
	 * 
	 * } else {
	 * 
	 * //
	 * 
	 * }
	 * 
	 * if (rates.getCreatedBy() != null) {
	 * 
	 * record.setAttribute("createdBy",rates.getCreatedBy() .getFullName());
	 * 
	 * } else {
	 * 
	 * }
	 * 
	 * list[0] = record; // }
	 * 
	 * this.currencyExchangeListGrid.setData(list); }
	 * 
	 * public void LoadCombos() { try {
	 * 
	 * ListGridRecord[] listRecords = new ListGridRecord[2]; ListGridRecord
	 * record1 = new ListGridRecord(); record1.setAttribute("123",
	 * "Uganda Shillings");
	 * 
	 * listRecords[0] = record1;
	 * 
	 * ListGridRecord record2 = new ListGridRecord();
	 * record2.setAttribute("124", "US Dollars");
	 * 
	 * listRecords[1] = record2;
	 * 
	 * currencyFromCombo.setOptionDataSource(SelectItemDataSource
	 * .getInstance(listRecords));
	 * currencyToCombo.setOptionDataSource(SelectItemDataSource
	 * .getInstance(listRecords)); } catch (Exception e) { SC.say("Error: " +
	 * e.getMessage()); } // ListGridRecord record2 = new ListGridRecord();
	 * 
	 * }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#loadFinancialYears()
	 * 
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrencyExchangeRateR()
	 * 
	 * public CurrencyExchangeRate getCurrencyExchangeRateR() {
	 * CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
	 * 
	 * // Set Financial Year FinancialYear financialYear = new FinancialYear();
	 * financialYear.setId(currencyFinYearCombo.getValueAsString());
	 * 
	 * // Set Currency To Currency currencyTo = new Currency();
	 * currencyTo.setId(currencyToCombo.getValueAsString());
	 * 
	 * // Set Currency From Currency currencyFrom = new Currency();
	 * currencyFrom.setId(currencyFromCombo.getValueAsString());
	 * 
	 * currencyExchangeRate.setFinancialYearId(currencyFinYearCombo
	 * .getValueAsString());
	 * currencyExchangeRate.setFromCurrencyId(currencyFromCombo
	 * .getValueAsString()); currencyExchangeRate
	 * .setToCurrencyId(currencyToCombo.getValueAsString());
	 * 
	 * return currencyExchangeRate; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrencyToEdit()
	 * 
	 * public Currency getCurrencyToEdit() { Currency currency = new Currency();
	 * currency.setId(this.currencyListGrid.getSelectedRecord().getAttribute(
	 * "currencyID"));
	 * currency.setCur_name(currencyNameText.getValueAsString());
	 * currency.setCur_symbol(currencySymbolText.getValueAsString());
	 * 
	 * Status status = new Status();
	 * status.setId(currencyStatusCombo.getValueAsString());
	 * currency.setCur_status(status);// (CurrencyStatus) //
	 * currencyStatusCombo.getValue()
	 * 
	 * return currency; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrencyDelete()
	 * 
	 * public Currency getCurrencyDelete() { Currency currency = new Currency();
	 * currency.setId(this.currencyListGrid.getSelectedRecord().getAttribute(
	 * "currencyID"));
	 * 
	 * return currency; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#setCurrencyStates(java.util.List)
	 * 
	 * public void setCurrencyStates(List<Status> states) {
	 * LinkedHashMap<String, String> valueMap = new LinkedHashMap<String,
	 * String>();
	 * 
	 * for (Status state : states) {
	 * 
	 * valueMap.put(state.getId(), state.getStatus()); }
	 * 
	 * if (!valueMap.isEmpty()) {
	 * this.currencyStatusCombo.setValueMap(valueMap); } else {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrencyExchangeRateToEdit()
	 * 
	 * public CurrencyExchangeRate getCurrencyExchangeRateToEdit() {
	 * 
	 * CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
	 * 
	 * currencyExchangeRate.setId(this.currencyExchangeListGrid
	 * .getSelectedRecord().getAttribute("finYearID"));
	 * 
	 * // Set Financial Year FinancialYear financialYear = new FinancialYear();
	 * financialYear.setId(currencyFinYearCombo.getValueAsString());//
	 * 402880ea4496e1eb014496e391190001 //
	 * //currencyFinYearCombo.getValueAsString()
	 * 
	 * // Set Currency To Currency currencyTo = new Currency();
	 * currencyTo.setId(currencyToCombo.getValueAsString());
	 * 
	 * // Set Currency From Currency currencyFrom = new Currency();
	 * currencyFrom.setId(currencyFromCombo.getValueAsString());
	 * 
	 * currencyExchangeRate.setFinancialyear(financialYear);
	 * currencyExchangeRate.setTocurrency(currencyTo);
	 * currencyExchangeRate.setFromcurrency(currencyFrom); //
	 * currencyExchangeRate.setRate(rate);
	 * 
	 * try { currencyExchangeRate.setRate(Double.parseDouble(rateNumberItem
	 * .getValueAsString().replace(",", ""))); } catch (Exception e) {
	 * SC.warn("Error Parsing String" + e.getMessage(), new BooleanCallback() {
	 * 
	 * public void execute(Boolean value) {
	 * 
	 * }
	 * 
	 * }); }
	 * 
	 * return currencyExchangeRate; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.
	 * CurrencyPresenter.MyView#getCurrencyExchangeRateToDelete()
	 * 
	 * public CurrencyExchangeRate getCurrencyExchangeRateToDelete() {
	 * 
	 * CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
	 * 
	 * // event.getRecord().getAttribute("finYearID")
	 * currencyExchangeRate.setId(this.currencyExchangeListGrid
	 * .getSelectedRecord().getAttribute("finYearID")); return
	 * currencyExchangeRate; }
	 */

	public void loadCurrencies(List<Currency> currency) {

		ListGridRecord[] list = new ListGridRecord[currency.size()];
		int listCount = 0;
		ListGridRecord record;

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

		for (Currency currecy : currency) {

			record = new ListGridRecord();
			record.setAttribute("icon", "");
			record.setAttribute("currencyID", currecy.getId());
			record.setAttribute("currencyName", currecy.getCur_name());
			record.setAttribute("currencyStatusID", currecy.getCurrencyStatusId());
			record.setAttribute("currencyStatus", currecy.getCurrencyStatusName());
			record.setAttribute("currencySymbol", currecy.getCur_symbol());
			record.setAttribute("emptyField", "");

			if (currecy.getChangedBy() != null) {

				record.setAttribute("changedBy", currecy.getChangedBy().getFullName());

			} else {

			}

			if (currecy.getCreatedBy() != null) {

				record.setAttribute("createdBy", currecy.getCreatedBy().getFullName());

			} else {

			}

			valueMap.put(currecy.getId(), currecy.getCur_name());

			list[listCount] = record;
			listCount++;

		}

		this.currencyListGrid.setData(list);
		currencyFromCombo.setValueMap(valueMap);
		currencyToCombo.setValueMap(valueMap);
	}

	public void loadCurrencyRates(List<CurrencyExchangeRate> rates) {

		ListGridRecord[] list = new ListGridRecord[rates.size()];
		// System.out.println("size: "+rates.size());
		int listCounter = 0;
		ListGridRecord record;
		for (CurrencyExchangeRate rate : rates) {
			record = new ListGridRecord();
			record.setAttribute("icon", "");
			record.setAttribute("currencyExchangeID", rate.getId());
			record.setAttribute("currencyToID", rate.getToCurrencyId());
			System.out.println("Currency to ID getting into the table" + rate.getToCurrencyId());
			record.setAttribute("currencyFromID", rate.getFromCurrencyId());
			record.setAttribute("currencyToName", rate.getToCurrencyName());
			record.setAttribute("currencyFromName", rate.getFromCurrencyName());
			record.setAttribute("finYearID", rate.getFinancialYearId());
			record.setAttribute("finYearName", rate.getFinancialYearName());
			record.setAttribute("currencyRate", rate.getRate());

			if (rate.getChangedBy() != null) {

				record.setAttribute("changedBy", rate.getChangedBy().getFullName());
			} else {

				//

			}

			if (rate.getCreatedBy() != null) {

				record.setAttribute("createdBy", rate.getCreatedBy().getFullName());

			} else {

			}

			list[listCounter] = record;
			listCounter++;
		}
		this.currencyExchangeListGrid.setData(list);

	}

	public void loadCurrencyStates(List<String> states) {

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

		for (String state : states) {

			valueMap.put(state, state);
		}

		if (!valueMap.isEmpty()) {
			this.currencyStatusCombo.setValueMap(valueMap);
		} else {

		}

	}

	
	public void loadCurrencyRates(CurrencyExchangeRate rate) {
		// TODO Auto-generated method stub

	}

}
