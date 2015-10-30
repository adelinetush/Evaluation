package com.planetsystems.monitoring.server.web.controllers.formbacking;

/**
 * presents a generic command property value
 * 
 * 
 * 
 */
public class GenericCommandValue {

	private Object value;

	/**
	 * default constructor
	 */
	public GenericCommandValue() {
		super();
	}

	/**
	 * constructor with initial specified values
	 * 
	 * @param value
	 */
	public GenericCommandValue(Object value) {
		super();
		this.value = value;
	}

	/**
	 * gets the value
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * sets the value of this profile setting
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
