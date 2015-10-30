package com.planetsystems.monitoring.model;

import java.io.Serializable;
import java.util.List;

public class RuleObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2265884733317107209L;
	private String procurementType;
	private double defaultAmount;
	private String procurementMethod;
	private int numberOfDays1;
	private int numberOfDays2;
	private List<String> procurementMethods;
	private int numberOfDays;
	
	public RuleObj() {
		super();
	}

	

	public RuleObj(String procurementType, double defaultAmount,
			String procurementMethod, List<String> procurementMethods) {
		super();
		this.procurementType = procurementType;
		this.defaultAmount = defaultAmount;
		this.procurementMethod = procurementMethod;
		this.procurementMethods = procurementMethods;
	}



	public String getProcurementType() {
		return procurementType;
	}

	public void setProcurementType(String procurementType) {
		this.procurementType = procurementType;
	}

	public double getDefaultAmount() {
		return defaultAmount;
	}

	public void setDefaultAmount(double defaultAmount) {
		this.defaultAmount = defaultAmount;
	}

	public List<String> getProcurementMethods() {
		return procurementMethods;
	}

	public void setProcurementMethods(List<String> procurementMethods) {
		this.procurementMethods = procurementMethods;
	}

	public String getProcurementMethod() {
		return procurementMethod;
	}

	public void setProcurementMethod(String procurementMethod) {
		this.procurementMethod = procurementMethod;
	}



	/**
	 * @return the numberOfDays
	 */
	public int getNumberOfDays() {
		return numberOfDays;
	}



	/**
	 * @param numberOfDays the numberOfDays to set
	 */
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}



	public int getNumberOfDays1() {
		return numberOfDays1;
	}



	public void setNumberOfDays1(int numberOfDays1) {
		this.numberOfDays1 = numberOfDays1;
	}



	public int getNumberOfDays2() {
		return numberOfDays2;
	}



	public void setNumberOfDays2(int numberOfDays2) {
		this.numberOfDays2 = numberOfDays2;
	}
	
	
	

}
