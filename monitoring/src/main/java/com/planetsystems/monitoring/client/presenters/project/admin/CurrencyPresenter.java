package com.planetsystems.monitoring.client.presenters.project.admin;

import java.util.List;

import com.google.gwt.core.client.GWT;
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
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.planetsystems.monitoring.client.events.CurrencyUiHandlers;
import com.planetsystems.monitoring.client.place.NameTokens;
import com.planetsystems.monitoring.client.presenters.ProjectManagerPresenter;
import com.planetsystems.monitoring.model.Currency;
import com.planetsystems.monitoring.model.CurrencyExchangeRate;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.CurrencyAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.CurrencyResult;

public class CurrencyPresenter extends
		Presenter<CurrencyPresenter.MyView, CurrencyPresenter.MyProxy>
		implements CurrencyUiHandlers {

	DispatchAsync dispatcher;

	/**
	 * @param eventBus
	 * @param view
	 * @param proxy
	 */
	@Inject
	public CurrencyPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);

		this.dispatcher = dispatcher;
	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.currencies)
	public interface MyProxy extends Proxy<CurrencyPresenter>, Place {

	}

	/**
	 * @author Planet Developer 001
	 * 
	 */
	public interface MyView extends View, HasUiHandlers<CurrencyUiHandlers> {

		void setServerResponse(String serverResponse);

		void loadCurrencies(List<Currency> currency);

		void loadCurrencyRates(List<CurrencyExchangeRate> rates);

		void loadCurrencyStates(List<String> states);

		void ClearCurrency();

		void ClearExchange();
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
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onSaveCurrencyButtonClicked()
	 */
	public void onSaveCurrencyButtonClicked(Currency currency) {

		dispatcher.execute(new CurrencyAction(NameTokens.saveOperation,
				NameTokens.currencies, currency),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadCurrencyButtonClicked();
							getView().ClearCurrency();

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	public void onReset() {
		super.onReset();

		/*
		 * onLoadCurrencyStates(); onGetActiveFinancialYear();
		 */

	}

	public void onReveal() {
		super.onReveal();
		onLoadCurrencyButtonClicked();
		onRetrieveCurrencyStates();
		onLoadCurrencyExchangeButtonClicked();
	}

	@Inject
	PlaceManager placeManager;

	public void onBind() {
		super.onBind();
		onLoadCurrencyButtonClicked();
		onLoadCurrencyExchangeButtonClicked();
		onLoadCurrencyStates();

	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		// textToServer = request.getParameter(TokenParameters.TEXT_TO_SERVER,
		// null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onCancelCurrencyButtonClicked()
	 */
	public void onCancelCurrencyButtonClicked() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onSaveExchangeButtonClicked()
	 */
	public void onSaveExchangeButtonClicked(CurrencyExchangeRate rate) {

		dispatcher.execute(new CurrencyAction(NameTokens.saveOperation,
				NameTokens.currencyExchangeRates, rate),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						getView().setServerResponse(result.getServerResponse());
						onLoadCurrencyExchangeButtonClicked();
						// getView().ClearCurrency();

						// } else {

						/*
						 * Log.info("onSuccess:false ");
						 * getView().setServerResponse(
						 * result.getServerResponse());
						 */
						// }

					}

				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onCancelExchangeButtonClicked()
	 */
	public void onCancelExchangeButtonClicked() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onEditCurrencyButtonClicked() This method edits a chosen Currency
	 */
	public void onEditCurrencyButtonClicked(Currency currency) {

		dispatcher.execute(new CurrencyAction(NameTokens.editOperation,
				NameTokens.currencies, currency),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadCurrencyButtonClicked();
							getView().ClearCurrency();

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onEditExchangeButtonClicked()
	 */
	public void onEditExchangeButtonClicked(CurrencyExchangeRate rate) {

		dispatcher.execute(new CurrencyAction(NameTokens.editOperation,
				NameTokens.currencyExchangeRates, rate),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						GWT.log("Save Currency onFailure()...", null);
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadCurrencyExchangeButtonClicked();
							getView().ClearCurrency();

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onDeleteCurrencyButtonClicked()
	 */
	public void onDeleteCurrencyButtonClicked(Currency currency) {

		dispatcher.execute(new CurrencyAction(NameTokens.deleteOperation,
				NameTokens.currencies, currency),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadCurrencyButtonClicked();
							getView().ClearCurrency();

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onDeleteExchangeButtonCliked()
	 */
	public void onDeleteExchangeButtonCliked(CurrencyExchangeRate rate) {

		dispatcher.execute(new CurrencyAction(NameTokens.deleteOperation,
				NameTokens.currencyExchangeRates, rate),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						GWT.log("Save Currency onFailure()...", null);
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().setServerResponse(
									result.getServerResponse());
							onLoadCurrencyExchangeButtonClicked();
							getView().ClearCurrency();

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	public void onLoadCurrencyExchangeButtonClicked() {

		dispatcher.execute(new CurrencyAction(NameTokens.retrieveOperation,
				NameTokens.currencyExchangeRates),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadCurrencyRates(result.getRates());

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

	public void onLoadCurrencyButtonClicked() {

		dispatcher.execute(new CurrencyAction(NameTokens.retrieveOperation,
				NameTokens.currencies), new AsyncCallback<CurrencyResult>() {

			public void onFailure(Throwable caught) {
				getView().setServerResponse(
						"An error Occured: " + caught.getMessage());

			}

			public void onSuccess(CurrencyResult result) {

				if (result.isOperationStatus() == true) {

					getView().loadCurrencies(result.getCurrencies());

				} else {

					getView().setServerResponse(result.getServerResponse());

				}

			}

		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.planetsystems.procnet.gwtui.client.events.CurrencyUiHandlers#
	 * onLoadCurrencyStates()
	 */
	public void onLoadCurrencyStates() {

		dispatcher.execute(new CurrencyAction(
				NameTokens.retrieveCurrencyStates, NameTokens.currencies),
				new AsyncCallback<CurrencyResult>() {
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						getView().setServerResponse(
								"An error occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadCurrencyStates(
									result.getCurrencyStates());

						} else {
							getView().setServerResponse(
									result.getServerResponse());

						}
					}

				});

	}

	public void onRetrieveCurrencyStates() {

		dispatcher.execute(new CurrencyAction(
				NameTokens.retrieveCurrencyStates, NameTokens.currencies),
				new AsyncCallback<CurrencyResult>() {

					public void onFailure(Throwable caught) {
						getView().setServerResponse(
								"An error Occured: " + caught.getMessage());

					}

					public void onSuccess(CurrencyResult result) {

						if (result.isOperationStatus() == true) {

							getView().loadCurrencyStates(
									result.getCurrencyStates());

						} else {

							getView().setServerResponse(
									result.getServerResponse());

						}

					}

				});

	}

}
