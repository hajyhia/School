package app.core.exceptions;

public class CouponSystemFacadException extends CouponSystemExceprion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CouponSystemFacadException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CouponSystemFacadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CouponSystemFacadException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CouponSystemFacadException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CouponSystemFacadException(Throwable cause) {
		super(cause);
	}
	
	

}