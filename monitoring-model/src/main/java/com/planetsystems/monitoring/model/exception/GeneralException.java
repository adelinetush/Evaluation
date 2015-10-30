package com.planetsystems.monitoring.model.exception;

import java.io.Serializable;

/**
 * General class to handle all BITMAN Technologies application exceptions it is
 * also the base class for all the application wide exceptions
 * 
 */

/**
 * @author Planet Dev002
 *
 */
public class GeneralException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3343929032632494922L;

	/**
	 * Default non arg constructor.
	 */
	public GeneralException() {
	}

	/**
	 * Constructor that takes an argument which is the <code>String</code>
	 * message to display when the exception is thrown.
	 * 
	 * @param message
	 *            message to display when <code>exception</code> is thrown.
	 */
	public GeneralException(String message) {
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
	public GeneralException(Throwable throwable) {
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
	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}
}
