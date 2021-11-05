package by.htp.it.service.exception;

public class ServiceExceptionLoginOrEmailExists extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public ServiceExceptionLoginOrEmailExists() {
		super();
	}

	public ServiceExceptionLoginOrEmailExists(String message) {
		super(message);
	}

	public ServiceExceptionLoginOrEmailExists(Exception e) {
		super(e);
	}

	public ServiceExceptionLoginOrEmailExists(String message, Exception e) {
		super(message, e);
	}

}
