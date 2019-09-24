

package com.monkey.core.exception;

import org.testng.Assert;


public class MonkeyException extends RuntimeException {

	private static final long serialVersionUID = -1426335467764786209L;

	/**
	 * Constructor will throw only message
	 * 
	 * @param code
	 * @param param
	 */
	public MonkeyException(final ExceptionCode code, final String... param) {
		final String message = ExceptionCode.getMessage(code, param);
		Assert.fail(message);
	}

	/**
	 * Constructor will throw message with the cause of the error
	 * 
	 * @param code
	 * @param cause
	 * @param param
	 */
	public MonkeyException(final ExceptionCode code, final Throwable cause, final String... param) {
		final String message = ExceptionCode.getMessage(code, cause != null ? cause.toString() : "undefined cause",
				param != null && param.length >= 1 ? param[0] : "undifined message");
		Assert.fail(message);
	}

}
