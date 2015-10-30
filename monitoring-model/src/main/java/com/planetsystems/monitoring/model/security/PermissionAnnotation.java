package com.planetsystems.monitoring.model.security;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;


/**
 * @author Planet Dev002
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionAnnotation {

	/**
	 * system assigned id for the permission
	 * 
	 * @return
	 */
	String id();

	/**
	 * the description of the permission
	 * 
	 * @return
	 */
	String description();
}
