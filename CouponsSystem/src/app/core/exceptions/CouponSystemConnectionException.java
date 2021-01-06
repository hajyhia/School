package app.core.exceptions;

public class CouponSystemConnectionException extends CouponSystemExceprion {

	private static final long serialVersionUID = 1L;

	public CouponSystemConnectionException() {
		super();
	}

	public CouponSystemConnectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponSystemConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponSystemConnectionException(String message) {
		super(message);
	}

	public CouponSystemConnectionException(Throwable cause) {
		super(cause);
	}

}
