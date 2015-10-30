package com.planetsystems.monitoring.model.exception;

import java.io.Serializable;

/**
 * General class to handle Runtime Exceptions. The idea is to enable
 * serialization of custom <code>exceptions</code> to the client.
 */

/**
 * @author Planet Dev002
 *
 */
public class GeneralRuntimeException extends RuntimeException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6827399267894307249L;

	/**
	 * Default non arg constructor.
	 */
	public GeneralRuntimeException() {
	}

	/**
	 * Constructor that takes an argument which is the <code>String</code>
	 * message to display when the exception is thrown.
	 * 
	 * @param message
	 *            message to display when <code>exception</code> is thrown.
	 */
	public GeneralRuntimeException(String message) {
		super(message);
	}

	/**
	 * Constructor that takes an argument which is an instance of
	 * <code>throwable</code>. It is the exception that is thrown used to
	 * initialise this exception too.
	 * 
	 * @param throwable
	 *            exception that is thrown.
	 */
	public GeneralRuntimeException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructs an instance of the <tt>Class</tt> with a custom message and
	 * the <tt>Exception.</tt>
	 * 
	 * @param message
	 *            Custom message to accompany the exception.
	 * @param cause
	 *            <tt>Exception</tt> that was thrown.
	 */
	public GeneralRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
