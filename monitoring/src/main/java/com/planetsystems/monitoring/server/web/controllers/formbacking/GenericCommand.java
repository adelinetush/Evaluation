package com.planetsystems.monitoring.server.web.controllers.formbacking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * represents a command object that can be used in a spring form to hold
 * properties in a map. Internally, the settings in a map and are represented by
 * another class {@link GenericCommandValue}
 * 
 * 
 * 
 */
public class GenericCommand {

	private Map<String, GenericCommandValue> propertiesMap = new HashMap<String, GenericCommandValue>();

	public Map<String, GenericCommandValue> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map<String, GenericCommandValue> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	/**
	 * gets the value of the given key from this command object
	 * 
	 * @param key
	 * @return
	 */
	public Object getValue(String key) {
		if (this.propertiesMap != null) {
			GenericCommandValue commandValue = this.propertiesMap.get(key);
			if (commandValue != null)
				return commandValue.getValue();
		}

		return null;
	}

	/**
	 * gets the string value of the given key from this command object
	 * 
	 * @param key
	 * @return
	 */
	public String getStringValue(String key) {
		if (this.propertiesMap != null) {
			GenericCommandValue commandValue = this.propertiesMap.get(key);
			if (commandValue != null)
				return (String) commandValue.getValue();
		}

		return "";
	}

	/**
	 * gets the date value of the given key from this command object
	 * 
	 * @param key
	 * @return
	 */
	public Date getDateValue(String key) {
		if (this.propertiesMap != null) {
			GenericCommandValue commandValue = this.propertiesMap.get(key);
			if (commandValue != null && commandValue.getValue() instanceof Date)
				return (Date) commandValue.getValue();
		}

		return null;
	}

	/**
	 * gets the integer value of the given key from this command object
	 * 
	 * @param key
	 * @return
	 */
	public int getIntValue(String key) {
		if (this.getStringValue(key) == null
				|| this.getStringValue(key).trim().length() == 0)
			return 0;

		return Integer.parseInt(this.getStringValue(key));
	}

	/**
	 * puts a given value into the command object with the given key
	 * 
	 * @param key
	 * @param value
	 */
	public GenericCommand putValue(String key, Object value) {
		this.getPropertiesMap().put(key, new GenericCommandValue(value));
		return this;
	}

	public double getDoubleValue(String key) {
		if (this.getStringValue(key) == null
				|| this.getStringValue(key).trim().length() == 0)
			return 0.0;

		return Double.parseDouble(this.getStringValue(key));
	}
}
