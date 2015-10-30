package com.planetsystems.monitoring.client.presenters.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.client.views.handlers.TaskUiHandlers;
import com.planetsystems.monitoring.client.views.project.ProjectDetailsDynamicForm;
import com.planetsystems.monitoring.client.views.project.ProjectObjectivesInterface;
import com.planetsystems.monitoring.client.views.project.ProjectSpecificObjectivesInterface;
import com.planetsystems.monitoring.client.views.project.TreeNodeRecord;
import com.planetsystems.monitoring.shared.MonitoringAction;
import com.planetsystems.monitoring.shared.MonitoringResult;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class NewProjectPresenter extends
		Presenter<NewProjectPresenter.MyView, NewProjectPresenter.MyProxy>
		implements TaskUiHandlers {

	DispatchAsync dispatcher;

	List<String> objectives = new ArrayList<String>();
	HashMap<Integer, List<String>> specificObjs = new HashMap<Integer, List<String>>();

	public interface MyView extends View {
		
		
		public ProjectDetailsDynamicForm getProjectDetails();

		public ProjectObjectivesInterface getProjectObjectives();

		public ProjectSpecificObjectivesInterface getProjectSpecificObjectives();

		public Button getSaveButton();

		public Button getPreviewButton();

		public TreeGrid getSpecificObjectivesGrid();

		public TreeGrid getObjectivesGrid();

	}

	@ProxyCodeSplit
	@NameToken(NameTokens.new_project)
	public interface MyProxy extends ProxyPlace<NewProjectPresenter> {
	}

	@Inject
	public NewProjectPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, DispatchAsync dispatcher) {

		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				ProjectManagerPresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getProjectObjectives().getSaveButton().addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
			      
				String objective=getView().getProjectObjectives().getObjectiveForm().getValueAsString("objective");
				
				if(objective !=null ){
					objectives.add(objective);
					
					Tree tree=updateData(objectives, null);
					 if(tree !=null){
					getView().getObjectivesGrid().setData(tree);
					
					String[] objectiveOptions= new String[objectives.size()];
					
					for(int i=0; i<objectives.size();i++){
						objectiveOptions[i]=objectives.get(i);
					}
					
								
					if(objectiveOptions !=null){
					getView().getProjectSpecificObjectives().getSpecificObjectiveForm().getItem("objective").setValueMap(objectiveOptions);
					} 
					}
				}else{
					SC.say("Nothing was entered!");
				}
				
				getView().getProjectObjectives().getObjectiveForm().getItem("objective").clearValue();
			}
		});
		
		
	getView().getProjectSpecificObjectives().getSaveObjective().addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
              				
			String selectedObjective=	(String) getView().getProjectSpecificObjectives().getSpecificObjectiveForm().getItem("objective").getValue();
			String specificObjective=getView().getProjectSpecificObjectives().getSpecificObjectiveForm().getValueAsString("specificObjective");
			
			if(selectedObjective !=null && specificObjective !=null ){
				
				
				if(objectives !=null){
					for(int i=0; i<objectives.size();i++){
						
						if(objectives.get(i).equalsIgnoreCase(selectedObjective)){
							
							List<String> subObjs=null;
							
							if(specificObjs.containsKey(i)){
							subObjs=specificObjs.get(i);
							}else{
								subObjs=new ArrayList<String>();
							}
							System.out.println("Captured value: "+specificObjective);
							subObjs.add(specificObjective);
							specificObjs.put(i, subObjs);
							
							break;
						}
					}
															
						Tree tree=updateData(objectives, specificObjs);
						
						 if(tree !=null){
						getView().getSpecificObjectivesGrid().setData(tree);
						 }
					
					
				}else{
					SC.say("No Objective selected or Specific Objective entered");
				}
			
			}
				
			
			getView().getProjectSpecificObjectives().getSpecificObjectiveForm().getItem("specificObjective").clearValue();
			getView().getProjectSpecificObjectives().getSpecificObjectiveForm().getItem("objective").clearValue();
			}
			
			
		});
	
	
	
	
	getView().getSaveButton().addClickHandler(new ClickHandler() {
		
		public void onClick(ClickEvent event) {
		String projectTitle=	getView().getProjectDetails().getValueAsString("projectTitle");
		
		String projectGoal=getView().getProjectDetails().getValueAsString("projectGoal");
		
		dispatcher.execute(new MonitoringAction(1,projectTitle,projectGoal,objectives,specificObjs) , new AsyncCallback<MonitoringResult>() {

			public void onFailure(Throwable arg0) {
				
			}

			public void onSuccess(MonitoringResult arg0) {
				
				
			}
		});
		
			
			
		}
	});
	
	}

	public Tree updateData(List<String> objectives,
			HashMap<Integer, List<String>> specificObjectives) {
		Tree tree = new Tree();
		if (objectives != null) {

			TreeNodeRecord[] records = new TreeNodeRecord[objectives.size()];

			for (int i = 0; i < objectives.size(); i++) {
				String object = objectives.get(i);
				records[i] = new TreeNodeRecord(object);

				if (specificObjectives != null) {

					if (specificObjectives.containsKey(i)) {

						List<String> children = specificObjectives.get(i);
						TreeNodeRecord[] leafs = new TreeNodeRecord[children.size()];
						
						for (int j = 0; j < children.size(); j++) {

							leafs[j] = new TreeNodeRecord(children.get(j));
						}
						records[i].setChildren(leafs);
					}
				}
			}
			tree.setData(records);
		}
		return tree;
	}
}
