package by.htp.it.service.exception;

public class ServiceExceptionInvalidData extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceExceptionInvalidData() {
		super();
	}

	public ServiceExceptionInvalidData(String message) {
		super(message);
	}

	public ServiceExceptionInvalidData(Exception e) {
		super(e);
	}

	public ServiceExceptionInvalidData(String message, Exception e) {
		super(message, e);
	}
}
