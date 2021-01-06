package app.core.exceptions;

public class CouponSystemDAOException extends CouponSystemExceprion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CouponSystemDAOException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CouponSystemDAOException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CouponSystemDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CouponSystemDAOException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CouponSystemDAOException(Throwable cause) {
		super(cause);
	}
	
	

}
