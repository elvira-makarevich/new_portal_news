package by.htp.it.dao.exception;

public class ConnectionPoolException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
	
	public ConnectionPoolException(String message) {
		super(message);
	}
}