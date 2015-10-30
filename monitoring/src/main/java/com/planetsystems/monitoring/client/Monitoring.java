package com.planetsystems.monitoring.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.planetsystems.monitoring.client.gin.ClientGinjector;
import com.planetsystems.monitoring.client.utils.MonitoringConstants;
import com.planetsystems.monitoring.client.utils.MonitoringMessages;
import com.planetsystems.monitoring.client.utils.UiUtils;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Monitoring implements EntryPoint {

	private static MonitoringConstants constants;

	private static MonitoringMessages messages;

	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);


	public void onModuleLoad() {
		constants = (MonitoringConstants) GWT.create(MonitoringConstants.class);

		messages = (MonitoringMessages) GWT.create(MonitoringMessages.class);

		DelayedBindRegistry.bind(ginjector);

		ginjector.getPlaceManager().revealCurrentPlace();

	}

	public static MonitoringConstants getConstants() {
		return constants;
	}

	public ClientGinjector getMonitoringGinjector() {
		return ginjector;
	}

	public static MonitoringMessages getMessages() {
		return messages;
	}

	public static void logout() {
		UiUtils.goToRelativePage("ServiceLogout");
	}
}