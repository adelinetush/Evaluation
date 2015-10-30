package com.planetsystems.monitoring.client.presenters.project;

import com.gwtplatform.dispatch.shared.DispatchAsync;


import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;

public class ProjectsPresenter extends
		Presenter<ProjectsPresenter.MyView, ProjectsPresenter.MyProxy>{

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.thisproject)
	public interface MyProxy extends ProxyPlace<ProjectsPresenter> {
	}

	DispatchAsync dispatcher;

	@Inject
	public ProjectsPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher=dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,ProjectManagerPresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
	

}
