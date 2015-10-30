/**
 * 
 */
package com.planetsystems.monitoring.client.views.project.admin;

import com.google.gwt.user.client.ui.Widget;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.planetsystems.monitoring.client.Monitoring;
import com.planetsystems.monitoring.client.events.VoteUiHandlers;
import com.planetsystems.monitoring.client.widgets.ToolBar;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.planetsystems.monitoring.client.listgrids.VoteFunctionListGrid;
import com.planetsystems.monitoring.client.listgrids.VoteListGrid;
import com.planetsystems.monitoring.client.presenters.project.admin.VotePresenter;
/**
 * @author Planet Developer 001
 *
 */
public class VoteView extends ViewWithUiHandlers<VoteUiHandlers> implements VotePresenter.MyView{

	 private static final String CONTEXT_AREA_WIDTH = "100%"; 
	
	private VLayout panel;
	private final VoteListGrid listVoteGrid;
	private final VoteFunctionListGrid listVoteFunctionGrid;
	private final DynamicForm DF_VOTE;
	private final DynamicForm DF_VOTE_FUNCTION;;
	private final ToolBar toolBarVote;
	private final ToolBar toolBarVoteFx;
	private final HLayout HL_BUTTONS1;
	private final HLayout HL_BUTTONS2;
	private final TabSet tabSet;
	
	private VLayout VL_VOTE;
	private VLayout VL_VOTE_FUNCTION;
	
	private  Tab voteTab;
	private Tab voteFunctionTab;
	
	private final TextItem voteIDText = new TextItem("voteIDText","Vote ID");
	private final TextItem voteCodeText = new TextItem("voteCodeText","Vote Code");
	private final TextItem voteNameText = new TextItem("voteNameText","Vote Name");
	private final TextItem voteFunctionNameText = new TextItem("voteFunctionNameText","Votef Function Name");
	private final TextItem voteFunctionCodeText = new TextItem("voteFunctionCodeText","Votef Function Code");
	private final ComboBoxItem voteCombo = new ComboBoxItem("voteCombo","Select Vote");
	
	Button saveButton1 = new Button("Save");
	Button cancelButton1 = new Button("Cancel");
	Button refreshButton1 = new Button("Refresh");
	Button editButton1 = new Button("Edit");
	Button deleteButton1 = new Button("Delete");
	
	Button saveButton2 = new Button("Save");
	Button cancelButton2 = new Button("Cancel");
	Button refreshButton2 = new Button("Refresh");
	Button editButton2 = new Button("Edit");
	Button deleteButton2 = new Button("Delete");
	
	private String mode = "votes";
	
	private String selectedVoteId = null;
	private String selectedVoteFunction = null;
	@Inject
	public VoteView(final VoteListGrid listVoteGrid,final VoteFunctionListGrid listVoteFunctionGrid,final ToolBar toolBarVote,ToolBar toolBarVoteFx, final DynamicForm DF_VOTE,final DynamicForm DF_VOTE_FUNCTION
			                    ,final TabSet tabSet){
		this.listVoteGrid=listVoteGrid;
		this.listVoteFunctionGrid=listVoteFunctionGrid;
		this.toolBarVote=toolBarVote;
		this.toolBarVoteFx=toolBarVoteFx;
		this.DF_VOTE=DF_VOTE;
		this.DF_VOTE_FUNCTION=DF_VOTE_FUNCTION;
		this.tabSet=tabSet;
		
		// Set Grid Sizes
		listVoteGrid.setHeight("80%");
		listVoteFunctionGrid.setHeight("80%");
		
		panel = new VLayout();
		//panel.setBackgroundColor("white");
		panel.setBackgroundColor("white");
		//panel.addStyleName("crm-ContextArea");
		panel.setWidth(CONTEXT_AREA_WIDTH);
		panel.setHeight100();
		panel.setMembersMargin(20);
		
		this.listVoteGrid.setHeight("80%");
		this.listVoteFunctionGrid.setHeight("80%");
		tabSet.setWidth100();
		tabSet.setHeight100();
		
		voteTab = new Tab();
		voteTab.setTitle("Votes ");
		voteTab.setID("votes");
		voteTab.setCanClose(false);
		
		voteFunctionTab = new Tab();
		voteFunctionTab.setTitle("Vote Functions");
		voteFunctionTab.setID("voteFunctions");
		voteFunctionTab.setCanClose(false);
		
		VL_VOTE = new VLayout();
		VL_VOTE.setWidth100();
		VL_VOTE.setHeight100();
		
		VL_VOTE_FUNCTION = new VLayout();
		VL_VOTE_FUNCTION.setWidth100();
		VL_VOTE_FUNCTION.setHeight100();
		
		HL_BUTTONS1 = new HLayout();
		HL_BUTTONS1.setWidth100();
		HL_BUTTONS1.setHeight("*");
		HL_BUTTONS1.setAlign(Alignment.CENTER);
		HL_BUTTONS1.setMembersMargin(10);
		
	//	HL_BUTTONS1.addMember(saveButton1);
       // HL_BUTTONS1.addMember(cancelButton1);
      //  HL_BUTTONS1.addMember(editButton1);
        HL_BUTTONS1.addMember(refreshButton1);
        //HL_BUTTONS1.addMember(deleteButton1);
        
        HL_BUTTONS2= new HLayout();
		HL_BUTTONS2.setWidth100();
		HL_BUTTONS2.setHeight("*");
		HL_BUTTONS2.setAlign(Alignment.CENTER);
		HL_BUTTONS2.setMembersMargin(10);
		
		HL_BUTTONS2.addMember(saveButton2);
        HL_BUTTONS2.addMember(cancelButton2);
       // HL_BUTTONS2.addMember(editButton2);
        HL_BUTTONS2.addMember(refreshButton2);
       // HL_BUTTONS2.addMember(deleteButton2);
      
        
        DF_VOTE.setWrapItemTitles(false);
        DF_VOTE.setNumCols(6);
        DF_VOTE.setHeight("15%");
       // DF_VOTE.setItems(voteCodeText,voteNameText);
        voteCodeText.setHint("Numeric only<br>[0-9.]");
        voteCodeText.setKeyPressFilter("[0-9.]");
        
        DF_VOTE_FUNCTION.setWrapItemTitles(false);
        DF_VOTE_FUNCTION.setNumCols(6);
        DF_VOTE_FUNCTION.setHeight("15%");
        DF_VOTE_FUNCTION.setItems(voteFunctionCodeText,voteFunctionNameText);
        voteFunctionCodeText.setHint("Numeric only<br>[0-9.]");
        voteFunctionCodeText.setKeyPressFilter("[0-9.]");
        for(FormItem FI:DF_VOTE.getFields())
		{
			FI.setWidth(400);
			FI.setCellHeight(47);
		}
        
        for(FormItem FI:DF_VOTE_FUNCTION.getFields())
		{
			FI.setWidth(200);
			FI.setCellHeight(47);
		}
      //  initToolBar();
        
        // Add Vote Controls to UI
        VL_VOTE.setMembers(listVoteGrid);//,DF_VOTE,HL_BUTTONS1
        voteTab.setPane(VL_VOTE);
        
        // Add Vote Function Controls to UI
        VL_VOTE_FUNCTION.setMembers(listVoteFunctionGrid,DF_VOTE_FUNCTION,HL_BUTTONS2);
        voteFunctionTab.setPane(VL_VOTE_FUNCTION);
        
        tabSet.addTab(voteTab);
        tabSet.addTab(voteFunctionTab);
        
        panel.addMember(tabSet);
		
		cancelButton1.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				/*if(getUiHandlers() != null){
					
					getUiHandlers().onCancelButton();
				}*/
				//setMode("votes");
				//panel.setMembers(toolBarVote,listVoteGrid,DF_VOTE,HL_BUTTONS1);
				ClearVoteFields();
				
			}
			
		});
		saveButton1.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				if(voteCodeText.getValue() == null){
					SC.say("Enter Vote Code");
					voteCodeText.focusInItem();
				}
				else if(voteNameText.getValue() == null){
		        	SC.say("Enter Vote Name");
		        	voteNameText.focusInItem();
				}
				else{
					
					if(getUiHandlers() != null){
					//SC.say("Save Button Clicked");
					getUiHandlers().onSaveVoteButtonClicked();
				}
				}
			}
			
		});
		
		editButton1.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				if(listVoteGrid.getSelectedRecord() == null){
		    		SC.say("Select Vote Record To Edit");
		    		listVoteGrid.focus();
		    	}
				else if(voteCodeText.getValue() == null){
					SC.say("Enter Vote Code");
					voteCodeText.focusInItem();
				}
				else if(voteNameText.getValue() == null){
		        	SC.say("Enter Vote Name");
		        	voteNameText.focusInItem();
				}
				else{
					
					SC.ask("Edit Vote", "Do you Really want to Edit this Vote", new BooleanCallback(){

						public void execute(Boolean value) {
						
							if(value != null && value){
								
								   if (getUiHandlers() != null) {
								        getUiHandlers().onEditVoteButtonClicked();
								      }
							}
							else {
								
								
							}
							
						}
						
					});
		   
		      }
			}
			
			
		});
		
		deleteButton1.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				 if(listVoteGrid.getSelectedRecord() == null){
			    		SC.say("Select Vote Record To Delete");
			    		listVoteGrid.focus();
			    	}
				 else{ 
		        if (getUiHandlers() != null) {
		           getUiHandlers().onDeleteVoteButtonClicked();
		        }
				 }
			}
			
		});
		refreshButton1.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				if(getUiHandlers()!=null){
					getUiHandlers().loadVotesButtonClicked();
				}
			}
			
		});
		
		cancelButton2.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				/*if(getUiHandlers() != null){
					
					getUiHandlers().onCancelButton();
				}*/
				//setMode("votes");
			//	panel.setMembers(toolBarVote,listVoteGrid,DF_VOTE,HL_BUTTONS1);
				ClearVoteFunctionFields();
				
			}
			
		});
		saveButton2.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				 /*if(voteCombo.getValue() == null){
					SC.say("Select Vote");
					voteCombo.focusInItem();
				}
				else */if(voteFunctionCodeText.getValue() == null){
					SC.say("Enter Vote Function Code");
					voteFunctionCodeText.focusInItem();
				}
				else  if(voteFunctionNameText.getValue() == null){
		        	SC.say("Enter Vote Function Name");
		        	voteFunctionNameText.focusInItem();
				}
				else{
		      if (getUiHandlers() != null) {
		        getUiHandlers().onSaveVoteFunctionButtonClicked();
		      }
				}
			}
			
		});
		
		editButton2.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				if(listVoteFunctionGrid.getSelectedRecord() == null){
		    		SC.say("Select Vote Function Record To Edit");
		    		listVoteGrid.focus();
		    	}
				/*else if(voteCombo.getValue() == null){
					SC.say("Select Vote");
					voteCombo.focusInItem();
				}*/
				else if(voteFunctionCodeText.getValue() == null){
					SC.say("Enter Vote Function Code");
					voteFunctionCodeText.focusInItem();
				}
				else  if(voteFunctionNameText.getValue() == null){
		        	SC.say("Enter Vote Function Name");
		        	voteFunctionNameText.focusInItem();
				}
				else{
		      if (getUiHandlers() != null) {
		        getUiHandlers().onEditVoteFunctionButtonClicked();
		      }
				}
			}
			
		});
		
		deleteButton2.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				 if(listVoteFunctionGrid.getSelectedRecord() == null){
			    		SC.say("Select Vote Function Record To Delete");
			    		listVoteFunctionGrid.focus();
			    	}
				 else{
		        if (getUiHandlers() != null) {
		           getUiHandlers().onDeleteVoteFunctionButtonClicked();
		        }
				 }
			}
			
		});
		refreshButton2.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				if(getUiHandlers()!=null){
					getUiHandlers().loadVoteFunctionsButtonClicked();
				}
			}
			
		});
		
		listVoteGrid.addRecordClickHandler(new RecordClickHandler(){

			public void onRecordClick(RecordClickEvent event) {
				//SC.say("Vote Selected: "+event.getRecord().getAttribute("voteCode"));
				
				//selectedVoteId = event.getRecord().getAttribute("voteID");
				voteCodeText.setValue(event.getRecord().getAttribute("voteCode"));
				voteNameText.setValue(event.getRecord().getAttribute("voteName"));
					
			}
		});
		
		listVoteFunctionGrid.addRecordClickHandler(new RecordClickHandler(){

			public void onRecordClick(RecordClickEvent event) {

				// voteCombo,voteFunctionCodeText,voteFunctionNameText
				//voteCombo.setValue(event.getRecord().getAttribute("voteID"));
				voteFunctionCodeText.setValue(event.getRecord().getAttribute("voteFunctionCode"));
				voteFunctionNameText.setValue(event.getRecord().getAttribute("voteFunctionName"));
				//voteCodeText.setValue(event.getRecord().getAttribute("voteCode"));
			//	voteNameText.setValue(event.getRecord().getAttribute("voteName"));
					
			}
		});
		
		// Tab Selected Handler
		tabSet.addTabSelectedHandler(new TabSelectedHandler(){

			public void onTabSelected(TabSelectedEvent event) {
				
				if(event.getID().contains("voteFunctions")){
					
				if(getUiHandlers() != null){
					getUiHandlers().loadVoteFunctionsButtonClicked();
				}
				
				}
			}
			
		});
	}
	/* (non-Javadoc)
	 * @see com.gwtplatform.mvp.client.View#asWidget()
	 */
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return panel;
	}

	 protected void initToolBar() {

		    toolBarVote.addButton(ToolBar.EDIT_BUTTON, Monitoring.getConstants().editButton(),
		        Monitoring.getConstants().newButtonTooltip(), new ClickHandler() {
		    public void onClick(ClickEvent event) {

		    	if(listVoteGrid.getSelectedRecord() != null){
		    		SC.say("Select Vote Record To Edit");
		    		listVoteGrid.focus();
		    	}
				if(voteCodeText.getValue() == null){
					SC.say("Enter Vote Code");
					voteCodeText.focusInItem();
				}
		        if(voteNameText.getValue() == null){
		        	SC.say("Enter Vote Name");
		        	voteNameText.focusInItem();
				}
		     //   saveButton2.setDisabled(true);
		      if (getUiHandlers() != null) {
		        getUiHandlers().onEditVoteButtonClicked();
		      }
		      }}
		    );

		    toolBarVote.addSeparator();

		 /*   toolBarVote.addButton(ToolBar.LOAD_BUTTON,"Vote Functions",
			        procnet.getConstants().loadButtonTooltip(), new ClickHandler(){

						public void onClick(ClickEvent event) {
						//setMode("voteFunctions");
							
						if(listVoteFunctionGrid.getSelectedRecord() == null){
							SC.say("Selected Vote To Continue");
							listVoteGrid.focus();
						}
							panel.setMembers(toolBarVote,listVoteFunctionGrid,DF_VOTE_FUNCTION,HL_BUTTONS2);
							
							if(getUiHandlers() != null){
								getUiHandlers().onVoteFunctionsButtonClcked();
							}
						}
		    	
		    });*/
		 

		    // toolBar.addButton(ToolBar.MAIL_MERGE_BUTTON,
		    //     Serendipity.getConstants().MailMergeButtonTooltip(), null);
		    toolBarVote.addButton(ToolBar.REPORTS_BUTTON,
		        Monitoring.getConstants().reportsButtonTooltip(), null);



		    toolBarVote.addButton(ToolBar.DELETE_BUTTON,
		        Monitoring.getConstants().deleteButtonTooltip(), new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  
		    	  if(listVoteGrid.getSelectedRecord() != null){
			    		SC.say("Select Vote Record To Delete");
			    		listVoteGrid.focus();
			    	}
		    	  
		        if (getUiHandlers() != null) {
		           getUiHandlers().onDeleteVoteButtonClicked();
		        }
		      }}
		    );

		    // toolBar.addButton(ToolBar.EMAIL_BUTTON,
		    //     Serendipity.getConstants().EmailButtonTooltip(), null);

		    toolBarVote.addSeparator();

		    toolBarVote.addButton(ToolBar.REFRESH_BUTTON,
		       Monitoring.getConstants().refreshButtonTooltip(), new ClickHandler() {
		      public void onClick(ClickEvent event) {
		     /* if (getUiHandlers() != null) {
		        // getUiHandlers().onRefreshButtonClicked();
		      }*/
		      }}
		    );
		    
		    // Vote Function Tool Bar
		    

		    toolBarVoteFx.addButton(ToolBar.EDIT_BUTTON, Monitoring.getConstants().editButton(),
		        Monitoring.getConstants().newButtonTooltip(), new ClickHandler() {
		    public void onClick(ClickEvent event) {

		    	if(listVoteFunctionGrid.getSelectedRecord() != null){
		    		SC.say("Select Vote Function Record To Edit");
		    		listVoteGrid.focus();
		    	}
		    	if(voteCombo.getValue() == null){
					SC.say("Select Vote");
					voteCombo.focusInItem();
				}
				if(voteFunctionCodeText.getValue() == null){
					SC.say("Enter Vote Function Code");
					voteFunctionCodeText.focusInItem();
				}
		        if(voteFunctionNameText.getValue() == null){
		        	SC.say("Enter Vote Function Name");
		        	voteFunctionNameText.focusInItem();
				}
		    	
		      if (getUiHandlers() != null) {
		        getUiHandlers().onEditVoteFunctionButtonClicked();
		      }
		      }}
		    );

		    toolBarVoteFx.addSeparator();

		   toolBarVoteFx.addButton(ToolBar.LOAD_BUTTON,"Load Functions",
			        Monitoring.getConstants().loadButtonTooltip(), new ClickHandler(){

						public void onClick(ClickEvent event) {


							if(getUiHandlers() != null){
								getUiHandlers().onVoteFunctionsButtonClcked();
							}
						}
		    	
		    });
		 

		    // toolBar.addButton(ToolBar.MAIL_MERGE_BUTTON,
		    //     Serendipity.getConstants().MailMergeButtonTooltip(), null);
		    toolBarVoteFx.addButton(ToolBar.REPORTS_BUTTON,
		        Monitoring.getConstants().reportsButtonTooltip(), null);



		    toolBarVoteFx.addButton(ToolBar.DELETE_BUTTON,
		        Monitoring.getConstants().deleteButtonTooltip(), new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  
		    	  if(listVoteFunctionGrid.getSelectedRecord() != null){
			    		SC.say("Select Vote Function Record To Delete");
			    		listVoteFunctionGrid.focus();
			    	}
		    	  
		        if (getUiHandlers() != null) {
		           getUiHandlers().onDeleteVoteFunctionButtonClicked();
		        }
		      }}
		    );

		    // toolBar.addButton(ToolBar.EMAIL_BUTTON,
		    //     Serendipity.getConstants().EmailButtonTooltip(), null);

		    toolBarVoteFx.addSeparator();

		    toolBarVoteFx.addButton(ToolBar.REFRESH_BUTTON,
		       Monitoring.getConstants().refreshButtonTooltip(), new ClickHandler() {
		      public void onClick(ClickEvent event) {
		     /* if (getUiHandlers() != null) {
		        // getUiHandlers().onRefreshButtonClicked();
		      }*/
		      }}
		    );
		  }
/*	 (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#getVote()
	 
	public Vote getVote() {
		Vote vote = new Vote();
		
		vote.setVot_code(voteCodeText.getValueAsString());
		vote.setVot_name(voteNameText.getValueAsString());
		return vote;
	}*/
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#setServerResponseText()
	 */
	public void setServerResponseText(String serverResponse) {
		SC.say(serverResponse);
		
	}
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#setServerResponse(java.util.List)
	 
	public void setServerResponse(List<Vote> votes) {
		
		ListGridRecord[] list = new ListGridRecord[votes.size()];
		int listCount = 0;
		ListGridRecord record ;
		for(Vote vote:votes){
			record = new ListGridRecord();
		record.setAttribute("icon","users" );
		record.setAttribute("voteID", vote.getId());
		record.setAttribute("voteCode", vote.getVot_code());
		record.setAttribute("voteName",vote.getVot_name());
		
		if (vote.getChangedBy() != null) {

			record.setAttribute("changedBy", vote.getChangedBy()
					.getFullName());

		} else {

			//

		}

		if (vote.getCreatedBy() != null) {

			record.setAttribute("createdBy", vote.getCreatedBy()
					.getFullName());

		} else {

		}
		record.setAttribute("emptyField", "");
		
		list[listCount] = record;
		listCount ++;
		
		}
		if(list.length > 0){
		this.listVoteGrid.setData(list);
		}
		else
		{
			//SC.say("No Funding Agency Records");
		}
		
	}
	 (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#getMode()
	 
	public String getMode() {
		
		return mode;
	}
	 (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#setMode()
	 
	public void setMode(String mode) {
	this.mode=mode;
	
		
	}
	 (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#getVoteFunction()
	 
	public VoteFunction getVoteFunction() {
	    
        VoteFunction voteFunction = new VoteFunction();
        Vote vote  = new Vote();
		

     //   voteFunction.setVoteId(voteCombo.getValueAsString());//listVoteGrid.getSelectedRecord().getAttribute("voteID")
        voteFunction.setVot_fun_code(voteFunctionCodeText.getValueAsString());
        voteFunction.setVot_fun_name(voteFunctionNameText.getValueAsString());
        
		return voteFunction;
	}*/
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#getVoteID(java.lang.String)
	 */
	public String getVoteID() {
		
		if(selectedVoteId == null){
			SC.say("Select Vote Record To Load Vote Functions");
			
			this.listVoteGrid.focus();
			return selectedVoteId;
		}
		else{
		
		return	selectedVoteId;
		}
		
	}
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#setServerResponseFx(java.util.List)
	 
	public void setServerResponseFx(List<VoteFunction> voteFunctions) {
		
		ListGridRecord[] list = new ListGridRecord[voteFunctions.size()];
		int listCount = 0;
		ListGridRecord record ;
		for(VoteFunction voteFunction:voteFunctions){
			record = new ListGridRecord();
		record.setAttribute("icon","users" );
		record.setAttribute("voteFunctionID", voteFunction.getId());
		record.setAttribute("voteFunctionCode", voteFunction.getVot_fun_code());
		record.setAttribute("voteCode", voteFunction.getVoteCode());
		record.setAttribute("voteID", voteFunction.getVoteId());
		record.setAttribute("voteFunctionName",voteFunction.getVot_fun_name());
		record.setAttribute("voteName",voteFunction.getVoteName());
		record.setAttribute("emptyField", "");//voteID
		
		if (voteFunction.getChangedBy() != null) {

			record.setAttribute("changedBy", voteFunction.getChangedBy()
					.getFullName());

		} else {

			//

		}

		if (voteFunction.getCreatedBy() != null) {

			record.setAttribute("createdBy", voteFunction.getCreatedBy()
					.getFullName());

		} else {

		}
		
		list[listCount] = record;
		listCount ++;
		
		}
		if(list.length > 0){
		this.listVoteFunctionGrid.setData(list);
		}
		else
		{
			//SC.say("No Funding Agency Records");
		}
	}*/
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#ClearFields()
	 */
	public void ClearVoteFields() {
		DF_VOTE.clearValues();
		
	}
	
	public void ClearVoteFunctionFields() {
		DF_VOTE_FUNCTION.clearValues();
		
	}
	/* (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#getVoteToEdit()
	 
	public Vote getVoteToEdit() {
        Vote vote = new Vote();
		
        vote.setId(this.listVoteGrid.getSelectedRecord().getAttribute("voteID"));
		vote.setVot_code(voteCodeText.getValueAsString());
		vote.setVot_name(voteNameText.getValueAsString());
		
		return vote;
	}
	
	 (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#getVoteFunction()
	 
	public VoteFunction getVoteFunctionToEdit() {
	    
        VoteFunction voteFunction = new VoteFunction();

        voteFunction.setVoteId(voteCombo.getValueAsString());//listVoteGrid.getSelectedRecord().getAttribute("voteID")
        voteFunction.setId(listVoteFunctionGrid.getSelectedRecord().getAttribute("voteFunctionID"));
        voteFunction.setVot_fun_code(voteFunctionCodeText.getValueAsString());
        voteFunction.setVot_fun_name(voteFunctionNameText.getValueAsString());
        
		return voteFunction;
	}
	
	 (non-Javadoc)
	 * @see com.planetsystems.procnet.gwtui.client.presenters.systemadmin.VotePresenter.MyView#loadVoteComboData(java.util.List)
	 
	public void loadVoteComboData(List<Vote> votes) {
		
		LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
		
		for(Vote vote: votes){
			
			valueMap.put(vote.getId(), vote.getVot_name());
		}
		
		this.voteCombo.setValueMap(valueMap);
		
	}*/
	public String getMode() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setMode(String mode) {
		// TODO Auto-generated method stub
		
	}
}
