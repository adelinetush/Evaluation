package com.planetsystems.monitoring.model.exception;


/**
 * @author Planet Dev002
 *
 */
public class UnexpectedException extends GeneralRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8749649252859292752L;

	/**
	 * Default non arg constructor.
	 */
	public UnexpectedException() {
	}

	/**
	 * Constructor that takes an argument which is the <code>String</code>
	 * message to display when the exception is thrown.
	 * 
	 * @param message
	 *            message to display when <code>exception</code> is thrown.
	 */
	public UnexpectedException(String message) {
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
	public UnexpectedException(Throwable throwable) {
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
	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}
}
