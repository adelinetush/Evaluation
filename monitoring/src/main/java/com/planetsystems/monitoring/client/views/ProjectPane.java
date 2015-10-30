package com.planetsystems.monitoring.client.views;


import com.smartgwt.client.types.Alignment;

import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ProjectPane {
	private final String TITLE = "Monitoring and Evaluation Project";

	public void onModuleLoad() {
		

		// RootPanel.get().add(mainForm);
		VLayout main = new VLayout();
		VLayout vlayout = new VLayout();
		
		// vlayout.setHeight(100);
		TabSet tabs = new TabSet();
		tabs.setTabBarPosition(Side.TOP);
		tabs.setTabBarAlign(Side.LEFT);
		tabs.setWidth(1024);
		tabs.setHeight(600);
		Tab details = new Tab("Project Details");
		tabs.addTab(details);
		Tab specific = new Tab("Specific Objectives");
		tabs.addTab(specific);

		Tab indicators = new Tab("Perfomance Indicators");
		tabs.addTab(indicators);
		Tab team = new Tab("Team");
		tabs.addTab(team);
		Label title = new Label(TITLE);
		title.setWidth(300);
		title.setHeight(30);
		title.setAlign(Alignment.CENTER);
		vlayout.addMember(title);
		vlayout.addMember(tabs);
		main.addMember(vlayout);

		// ListGrid Setup
		ListGrid mainList = new ListGrid() {
			
			

			@Override
			protected Canvas getExpansionComponent(final ListGridRecord record) {
				ListGrid listGrid = new ListGrid() {
					

					@Override
					protected Canvas getExpansionComponent(
							final ListGridRecord record) {

						final ListGrid grid = this;

						VLayout layout = new VLayout(5);
						layout.setPadding(5);

						final ListGrid countryGrid = new ListGrid();
						countryGrid.setHeight(224);
						countryGrid.setCellHeight(22);
						countryGrid.setDataSource(getRelatedDataSource(record));
						

						countryGrid.setCanEdit(true);
						countryGrid.setModalEditing(true);
						countryGrid.setEditEvent(ListGridEditEvent.CLICK);
						countryGrid.setListEndEditAction(RowEndEditAction.NEXT);
						countryGrid.setAutoSaveEdits(false);

						layout.addMember(countryGrid);

						HLayout hLayout = new HLayout(10);
						hLayout.setAlign(Alignment.CENTER);

						IButton saveButton = new IButton("Add");
						saveButton.setTop(250);
						saveButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								countryGrid.saveAllEdits();
							}
						});
						hLayout.addMember(saveButton);

						IButton discardButton = new IButton("Edit");
						discardButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								countryGrid.discardAllEdits();
							}
						});
						hLayout.addMember(discardButton);

						IButton closeButton = new IButton("Remove");
						closeButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								grid.collapseRecord(record);
							}
						});
						hLayout.addMember(closeButton);

						layout.addMember(hLayout);

						return layout;
					}
				};

				listGrid.setWidth100();
				listGrid.setHeight(500);
				listGrid.setDrawAheadRatio(4);
				listGrid.setCanExpandRecords(true);

				listGrid.setAutoFetchData(true);
				

				return listGrid;

			}
		};
		mainList.setWidth100();
		mainList.setHeight(500);
		mainList.setDrawAheadRatio(4);
		mainList.setCanExpandRecords(true);

		mainList.setAutoFetchData(true);
		

		details.setPane(mainList);

		main.draw();

	}
}



