/**
 * 
 */
package com.planetsystems.monitoring.shared.dispatch.admin.organs;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author Planet Developer 001
 *
 */
public interface VoteUiHandlers extends UiHandlers{

	void onSaveVoteButtonClicked();
	void onSaveVoteFunctionButtonClicked();
	void onEditVoteButtonClicked();
	void onEditVoteFunctionButtonClicked();
	void onDeleteVoteButtonClicked();
	void onDeleteVoteFunctionButtonClicked();
	void onVoteFunctionsButtonClcked();
	void loadVotesButtonClicked();
	void loadVoteFunctionsButtonClicked();
}
