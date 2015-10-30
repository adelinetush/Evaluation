package com.planetsystems.monitoring.client.presenters.project.admin;


import com.google.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.events.TasklogUiHandlers;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;

public class TasklogPresenter extends Presenter<TasklogPresenter.MyView,TasklogPresenter.MyProxy>
             implements TasklogUiHandlers{


	DispatchAsync dispatcher;
	
	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public TasklogPresenter(EventBus eventBus, MyView view, MyProxy proxy,DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		
		
		getView().setUiHandlers(this);
		this.dispatcher=dispatcher;
		
		
	}

	/**
	 * @author Planet Developer 001
	 *
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.tasklogs)
	public interface MyProxy extends Proxy<TasklogPresenter>,Place{

	}

	/**
	 * @author Planet Developer 001
	 *
	 */
	public interface MyView extends View,HasUiHandlers<TasklogUiHandlers>{

		
		
	}
	 
	 public void onReset(){
		 super.onReset();
	
		 
	 }
	 
	 @Inject
	 PlaceManager placeManager;
	 public void onBind(){
		 super.onBind();
	
	 }


	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, ProjectManagerPresenter.TYPE_SetContextAreaContent,
		        this);
		
	}

}
