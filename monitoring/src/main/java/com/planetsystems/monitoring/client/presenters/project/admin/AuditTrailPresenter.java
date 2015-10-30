/**
 * 
 */
package com.planetsystems.monitoring.client.presenters.project.admin;

import java.util.Date;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.planetsystems.monitoring.client.events.AuditTrailUiHandlers;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.model.audit.AuditEventsTrail;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AuditTrailAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AuditTrailResult;

/**
 * @author Planet Developer 001
 * 
 */
public class AuditTrailPresenter extends
		Presenter<AuditTrailPresenter.MyView, AuditTrailPresenter.MyProxy>
		implements AuditTrailUiHandlers {

	DispatchAsync dispatcher;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public AuditTrailPresenter(EventBus eventBus, MyView view, MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		this.dispatcher = dispatcher;
	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.auditTrail)
	public interface MyProxy extends Proxy<AuditTrailPresenter>, Place {

	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	public interface MyView extends View, HasUiHandlers<AuditTrailUiHandlers> {

		void setServerResponse(String serverResponse);

		void loadAuditEvents(List<AuditEventsTrail> eventsAudit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.Presenter#revealInParent()
	 */
	@Override
	protected void revealInParent() {

		RevealContentEvent.fire(this,
				ProjectManagerPresenter.TYPE_SetContextAreaContent, this);

	}

	@Inject
	PlaceManager placeManager;

	protected void onBind() {
		super.onBind();

		getAllAuditEvents();
	}

	protected void onReset() {
		super.onReset();

		// getAllAuditEvents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.AuditTrailUiHandlers#
	 * getAllAuditEvents()
	 */
	public void getAllAuditEvents() {

		// get the data for the View's tabs
		dispatcher.execute(new AuditTrailAction(NameTokens.auditEvents,
				NameTokens.retrieveAllAuditEventsOpeartion),
				new AsyncCallback<AuditTrailResult>() {
					public void onFailure(Throwable caught) {

						getView().setServerResponse("" + caught.getMessage());

					}

					public void onSuccess(AuditTrailResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadAuditEvents(result.getEvents());

						} else if (result.isOperationStatus() == false) {

							getView().setServerResponse(
									result.getServerResponse());
						}

					}

				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.AuditTrailUiHandlers#
	 * getAuditByDateEvents(java.util.Date)
	 */
	public void getAuditByDateEvents(Date date) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.AuditTrailUiHandlers#
	 * getAllAuditSessions()
	 */
	public void getAllAuditSessions() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.AuditTrailUiHandlers#
	 * getAuditSessionsByDate(java.util.Date)
	 */
	public void getAuditSessionsByDate(Date date) {
		// TODO Auto-generated method stub

	}

}
