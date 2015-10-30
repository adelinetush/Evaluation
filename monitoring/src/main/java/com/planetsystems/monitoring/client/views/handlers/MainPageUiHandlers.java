package com.planetsystems.monitoring.client.views.handlers;

import com.gwtplatform.mvp.client.UiHandlers;



public interface MainPageUiHandlers extends UiHandlers {

	void onNavigationPaneSectionHeaderClicked(String name);

	void onNavigationPaneSectionClicked(String name);

	void onNewProjectClickHandler();

	void onCurrentProjectsClickHandler();

	void onClosedProjectClickHandler();

	void onStakeholdersClickHandler();

	void onTeamInterationClickHandler();

	void onApprovedProjectClickHandler();

	void onCreateUserClickHandler();

	void onPendingProjectClickHandler();

	void onOngoingProjectClickHandler();

	void onGetLoggedInUser();

	void onShowUserTaskClickHandler();

}