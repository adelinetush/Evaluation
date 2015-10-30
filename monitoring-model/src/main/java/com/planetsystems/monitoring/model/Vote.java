package com.planetsystems.monitoring.model;

import javax.persistence.Entity;

@Entity
public class Vote extends ParentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1181319574746662151L;
	private String voteName;
	private String voteDescription;
	private String voteCode;
	/**
	 * @return the voteName
	 */
	public String getVoteName() {
		return voteName;
	}
	/**
	 * @param voteName the voteName to set
	 */
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	/**
	 * @return the voteDescription
	 */
	public String getVoteDescription() {
		return voteDescription;
	}
	/**
	 * @param voteDescription the voteDescription to set
	 */
	public void setVoteDescription(String voteDescription) {
		this.voteDescription = voteDescription;
	}
	/**
	 * @return the voteCode
	 */
	public String getVoteCode() {
		return voteCode;
	}
	/**
	 * @param voteCode the voteCode to set
	 */
	public void setVoteCode(String voteCode) {
		this.voteCode = voteCode;
	}
	
	
	

}
