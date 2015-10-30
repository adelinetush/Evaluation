package com.planetsystems.monitoring.model.project;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.planetsystems.monitoring.model.ParentEntity;

@Entity
public class Documents  extends ParentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1494129802630788123L;

	private String fileName;
	
	private Date dateModified;
	
	private String fileType;
	
	private String fileSize;
	
	
	private String filePath;
	
	private ProjectTitle project;
	
	public Documents(){
		
	}

	public Documents(String fileName,Date dateModified,String fileType,String fileSize,String filePath, ProjectTitle project){
		this.fileName=fileName;
		this.dateModified=dateModified;
		this.fileType=fileType;
		this.fileSize=fileSize;
		this.filePath= filePath;
		this.project=project;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="projectId")
	public ProjectTitle getProject() {
		return project;
	}

	public void setProject(ProjectTitle project) {
		this.project = project;
	}

	

}
