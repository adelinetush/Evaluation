/**
 * 
 */
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
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.events.VoteUiHandlers;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;

/**
 * @author Planet Developer 001
 * 
 */
public class VotePresenter extends
		Presenter<VotePresenter.MyView, VotePresenter.MyProxy> implements
		VoteUiHandlers {

	DispatchAsync dispatcher;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public VotePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);

		this.dispatcher = dispatcher;
	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.votes)
	public interface MyProxy extends Proxy<VotePresenter>, Place {

	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	public interface MyView extends View, HasUiHandlers<VoteUiHandlers> {

		/*
		 * Vote getVote(); VoteFunction getVoteFunction();
		 */
		void setServerResponseText(String serverResponse);

		/*
		 * void setServerResponse(List<Vote> votes); void
		 * setServerResponseFx(List<VoteFunction> voteFunctions);
		 */
		String getMode();

		void setMode(String mode);

		String getVoteID();

		/*
		 * Vote getVoteToEdit(); VoteFunction getVoteFunctionToEdit();
		 */
		void ClearVoteFields();

		void ClearVoteFunctionFields();
		/* void loadVoteComboData(List<Vote> votes); */

	}

	@Inject
	PlaceManager placeManager;

	@Override
	protected void onBind() {
		super.onBind();

		/*
		 * // get the data for the View's tabs dispatcher.execute(new
		 * RetrieveVotesAction(), new AsyncCallback<RetrieveVotesResult>() {
		 * public void onFailure(Throwable caught) { //
		 * Log.debug("onFailure() - " + caught.getMessage());
		 * GWT.log("onBind(): onFailure() - "+caught.getMessage(),null);
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(RetrieveVotesResult result) {
		 * GWT.log("onBind: onSuccess()",null);
		 * 
		 * if(result.isOperationStatus() == true){
		 * 
		 * getView().setServerResponse(result.getVotes()); } else
		 * if(result.isOperationStatus() == false){
		 * 
		 * getView().setServerResponseText(result.getServerErrorResponse()); }
		 * 
		 * 
		 * }
		 * 
		 * });
		 */
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		// textToServer = request.getParameter(TokenParameters.TEXT_TO_SERVER,
		// null);
	}

	public void onReset() {
		super.onReset();
	}

	public void onReveal() {
		super.onReveal();

		/*
		 * // get the data for the View's tabs dispatcher.execute(new
		 * RetrieveVotesAction(), new AsyncCallback<RetrieveVotesResult>() {
		 * public void onFailure(Throwable caught) { //
		 * Log.debug("onFailure() - " + caught.getMessage());
		 * GWT.log("onFailure() - "+caught.getMessage());
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(RetrieveVotesResult result) {
		 * if(result.isOperationStatus() == true){
		 * 
		 * getView().setServerResponse(result.getVotes()); } else
		 * if(result.isOperationStatus() == false){
		 * 
		 * getView().setServerResponseText(result.getServerErrorResponse()); }
		 * 
		 * }
		 * 
		 * });
		 */

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.planetsystems.procnet.gwtui.client.events.SystemAdministratioUiHandlers
	 * #loadGridData()
	 */
	public void loadGridData() {
		if (getView().getMode().contains("voteFunctions")) {

		} else {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onSaveVoteButtonClicked()
	 */
	public void onSaveVoteButtonClicked() {

		/*
		 * dispatcher.execute(new AddVoteAction(getView().getVote()), new
		 * AsyncCallback<AddVoteResult>() { public void onFailure(Throwable
		 * caught) { // Log.debug("onFailure() - " + caught.getMessage());
		 * GWT.log("onFailure() - "+caught.getMessage());
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(AddVoteResult result) {
		 * 
		 * Log.info("onSuccess() ");
		 * 
		 * if(result.isOperationStatus() == true){
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * getView().ClearVoteFields(); loadVotesButtonClicked();
		 * 
		 * } else if(result.isOperationStatus() == false){
		 * 
		 * getView().setServerResponseText(result.getServerResponse()); } } });
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onSaveVoteFunctionButtonClicked()
	 */
	public void onSaveVoteFunctionButtonClicked() {

		// get the data for the View's tabs
		/*
		 * dispatcher.execute(new AddVoteFunctionAction
		 * (getView().getVoteFunction()), new
		 * AsyncCallback<AddVoteFunctionResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage()); GWT.log("onFailure() - "+caught.getMessage());
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(AddVoteFunctionResult result) {
		 * Log.info("onSuccess() ");
		 * 
		 * if(result.isOpeartionStatus() == true ){
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * getView().ClearVoteFunctionFields();
		 * loadVoteFunctionsButtonClicked();
		 * 
		 * } else if(result.isOpeartionStatus() == false ){
		 * 
		 * getView().setServerResponseText(result.getServerResponse()); } }
		 * 
		 * });
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onEditVoteButtonClicked()
	 */
	public void onEditVoteButtonClicked() {
		/*
		 * dispatcher.execute(new EditVoteAction(getView().getVoteToEdit()), new
		 * AsyncCallback<EditVoteResult>() { public void onFailure(Throwable
		 * caught) { // Log.debug("onFailure() - " + caught.getMessage());
		 * GWT.log("onFailure() - "+caught.getMessage());
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(EditVoteResult result) {
		 * 
		 * Log.info("onSuccess() ");
		 * 
		 * if(result.isOperationStatus() == true ){
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * getView().ClearVoteFields(); loadVotesButtonClicked();
		 * 
		 * } else if(result.isOperationStatus() == false ){
		 * 
		 * getView().setServerResponseText(result.getServerResponse()); }
		 * 
		 * } });
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onEditVoteFunctionButtonClicked()
	 */
	public void onEditVoteFunctionButtonClicked() {

		// get the data for the View's tabs
		/*
		 * dispatcher.execute(new EditVoteFunctionAction
		 * (getView().getVoteFunctionToEdit()), new
		 * AsyncCallback<EditVoteFunctionResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage()); GWT.log("onFailure() - "+caught.getMessage());
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(EditVoteFunctionResult result) {
		 * Log.info("onSuccess() ");
		 * 
		 * if(result.isOperationStatus()==true){
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * getView().ClearVoteFunctionFields();
		 * loadVoteFunctionsButtonClicked();
		 * 
		 * } else if( result.isOperationStatus()==false ){
		 * 
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * 
		 * }
		 * 
		 * }
		 * 
		 * });
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onDeleteVoteButtonClicked()
	 */
	public void onDeleteVoteButtonClicked() {

		/*
		 * dispatcher.execute(new DeleteVoteAction(getView().getVoteToEdit()),
		 * new AsyncCallback<DeleteVoteResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage()); GWT.log("onFailure() - "+caught.getMessage());
		 * getView
		 * ().setServerResponseText("An Error Occurred: "+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(DeleteVoteResult result) {
		 * 
		 * Log.info("onSuccess() ");
		 * 
		 * if(result.isOperationStatus() == true ){
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * getView().ClearVoteFields(); loadVotesButtonClicked();
		 * 
		 * } else if(result.isOperationStatus()== false){
		 * 
		 * getView().setServerResponseText(result.getServerResponse()); }
		 * 
		 * } });
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onDeleteVoteFunctionButtonClicked()
	 */
	public void onDeleteVoteFunctionButtonClicked() {

		// get the data for the View's tabs
		/*
		 * dispatcher.execute(new DeleteVoteFunctionAction
		 * (getView().getVoteFunctionToEdit()), new
		 * AsyncCallback<DeleteVoteFunctionResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage()); GWT.log("onFailure() - "+caught.getMessage());
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(DeleteVoteFunctionResult result) {
		 * Log.info("onSuccess() ");
		 * 
		 * if(result.isOperationStatus()== true){
		 * 
		 * getView().setServerResponseText(result.getServerResponse());
		 * getView().ClearVoteFunctionFields();
		 * loadVoteFunctionsButtonClicked();
		 * 
		 * } else if(result.isOperationStatus()== false ){
		 * 
		 * getView().setServerResponseText(result.getServerResponse()); } }
		 * 
		 * });
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * onVoteFunctionsButtonClcked()
	 */
	public void onVoteFunctionsButtonClcked() {
		// get the data for the View's tabs
		/*
		 * dispatcher.execute(new RetrieveVoteFunctionAction(), new
		 * AsyncCallback<RetrieveVoteFunctionResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage());
		 * GWT.log("onVoteFunctionsButtonClicked(): onFailure() - "
		 * +caught.getMessage(),null);
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(RetrieveVoteFunctionResult result) {
		 * Log.info("onSuccess()");
		 * 
		 * //if(result.i)
		 * getView().setServerResponseFx(result.getVoteFunctions());
		 * 
		 * 
		 * }
		 * 
		 * });
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * loadVotesButtonClicked()
	 */
	public void loadVotesButtonClicked() {
		// get the data for the View's tabs
		/*
		 * dispatcher.execute(new RetrieveVotesAction(), new
		 * AsyncCallback<RetrieveVotesResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage());
		 * GWT.log("loadVotesButtonClicked(): onFailure() - "
		 * +caught.getMessage(),null);
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(RetrieveVotesResult result) {
		 * GWT.log("loadVotesButtonClicked: onSuccess()",null);
		 * getView().setServerResponse(result.getVotes());
		 * getView().loadVoteComboData(result.getVotes());
		 * 
		 * }
		 * 
		 * });
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.VoteUiHandlers#
	 * loadVoteFunctionsButtonClicked()
	 */
	public void loadVoteFunctionsButtonClicked() {

		// get the data for the View's tabs
		/*
		 * dispatcher.execute(new RetrieveVoteFunctionAction(), new
		 * AsyncCallback<RetrieveVoteFunctionResult>() { public void
		 * onFailure(Throwable caught) { // Log.debug("onFailure() - " +
		 * caught.getMessage());
		 * GWT.log("loadVoteFunctionsButtonClicked(): onFailure() - "
		 * +caught.getMessage(),null);
		 * getView().setServerResponseText(""+caught.getMessage());
		 * 
		 * }
		 * 
		 * public void onSuccess(RetrieveVoteFunctionResult result) {
		 * GWT.log("loadVoteFunctionsButtonClicked(): onSuccess()",null);
		 * 
		 * getView().setServerResponseFx(result.getVoteFunctions());
		 * 
		 * 
		 * }
		 * 
		 * });
		 */
	}

}
