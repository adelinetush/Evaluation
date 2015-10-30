package com.planetsystems.monitoring.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.planetsystems.monitoring.model.units.Department;


/**
 * @author Planet Dev002
 * 
 */
@Entity
public class Directorate extends ParentEntity {

	private static final long serialVersionUID = 7434118334924090050L;
	private String dir_name;
	private String dir_description;

	private List<Department> dpt;
	private String dptName;
	private Vote vote;
	private String voteId;
	private String voteName;
	private User directorateHead;

	public Directorate() {

	}

	public Directorate(String dir_name, String dir_description, String dptName) {
		super();
		this.dir_name = dir_name;
		this.dir_description = dir_description;
		this.dptName = dptName;
	}

	public Directorate(String dir_name, String dir_description) {
		this.dir_name = dir_name;
		this.dir_description = dir_description;

	}

	public Directorate(String dir_name, String dir_description,
			List<Department> dpt) {
		this.dir_name = dir_name;
		this.dir_description = dir_description;
		this.dpt = dpt;
	}

	public String getDir_name() {
		return dir_name;
	}

	public void setDir_name(String dir_name) {
		this.dir_name = dir_name;
	}

	public String getDir_description() {
		return dir_description;
	}

	public void setDir_description(String dir_description) {
		this.dir_description = dir_description;
	}

	@OneToMany(mappedBy = "dir")
	public List<Department> getDpt() {
		return dpt;
	}

	public void setDpt(List<Department> dpt) {
		this.dpt = dpt;
	}

	@Transient
	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	/**
	 * @return the vote
	 */
	@ManyToOne
	@JoinColumn(name = "voteFk")
	public Vote getVote() {
		return vote;
	}

	/**
	 * @param vote
	 *            the vote to set
	 */
	public void setVote(Vote vote) {
		this.vote = vote;
	}

	/**
	 * @return the voteId
	 */
	@Transient
	public String getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId
	 *            the voteId to set
	 */
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the voteName
	 */
	@Transient
	public String getVoteName() {
		return voteName;
	}

	/**
	 * @param voteName
	 *            the voteName to set
	 */
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	/**
	 * @return the directorateHead
	 */
	@ManyToOne
	@JoinColumn(name="directorateHead")
	public User getDirectorateHead() {
		return directorateHead;
	}

	/**
	 * @param directorateHead the directorateHead to set
	 */
	public void setDirectorateHead(User directorateHead) {
		this.directorateHead = directorateHead;
	}
	
	

}
