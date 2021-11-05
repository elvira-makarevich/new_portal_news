package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.controller.Command;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.UserService;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionLoginOrEmailExists;
import by.htp.it.service.exception.ServiceExceptionValidationUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final Logger log = LogManager.getLogger(RegistrationNewUser.class);

	public static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";
	public static final String REQUEST_PARAM_NAME = "name";
	public static final String REQUEST_PARAM_SURNAME = "surname";
	public static final String REQUEST_PARAM_LOGIN = "login";
	public static final String REQUEST_PARAM_EMAIL = "email";
	public static final String PARAMETER_CONNECTOR = "&";
	public static final String CONNECTOR = "=";
	public static final String REQUEST_PARAM_PASSWORD1 = "password1";
	public static final String REQUEST_PARAM_PASSWORD2 = "password2";
	public static final String PATH_PASSWORD_MISMATCH = "Controller?command=Go_To_Registration_Page&passwordErrorMismatch=Password mismatch. Try again.&name=";
	public static final String PATH_AFTER_REGISTRATION = "Controller?command=Go_To_Authorization_Page&messageSuccessRegistration=You have successfully registered! Please log in.";
	public static final String PATH_AFTER_LOGIN_OR_EMAIL_EXISTS = "Controller?command=Go_To_Registration_Page&messageErrorLoginOrEmailExists=Entered login or email exists!!! Try again.&name=";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=Go_To_Registration_Page&messageErrorValidation=ErrorValidation.&name=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = (String) request.getParameter(REQUEST_PARAM_NAME);
		String surname = (String) request.getParameter(REQUEST_PARAM_SURNAME);
		String login = (String) request.getParameter(REQUEST_PARAM_LOGIN);
		String email = (String) request.getParameter(REQUEST_PARAM_EMAIL);
		String password1 = (String) request.getParameter(REQUEST_PARAM_PASSWORD1);
		String password2 = (String) request.getParameter(REQUEST_PARAM_PASSWORD2);

		if (!password1.equals(password2)) {
			response.sendRedirect(PATH_PASSWORD_MISMATCH + name + PARAMETER_CONNECTOR + REQUEST_PARAM_SURNAME
					+ CONNECTOR + surname + PARAMETER_CONNECTOR + REQUEST_PARAM_LOGIN + CONNECTOR + login
					+ PARAMETER_CONNECTOR + REQUEST_PARAM_EMAIL + CONNECTOR + email);
			return;
		}

		RegistrationInfo info = new RegistrationInfo(name, surname, login, email, password1);

		try {

			userService.register(info);
			response.sendRedirect(PATH_AFTER_REGISTRATION);

		} catch (ServiceExceptionLoginOrEmailExists e) {
			log.error("Registration: login or email exists.", e);
			response.sendRedirect(PATH_AFTER_LOGIN_OR_EMAIL_EXISTS + name + PARAMETER_CONNECTOR + REQUEST_PARAM_SURNAME
					+ CONNECTOR + surname + PARAMETER_CONNECTOR + REQUEST_PARAM_LOGIN + CONNECTOR + login
					+ PARAMETER_CONNECTOR + REQUEST_PARAM_EMAIL + CONNECTOR + email);

		} catch (ServiceExceptionValidationUser e) {
			log.error("Registration: validation error.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION + name + PARAMETER_CONNECTOR + REQUEST_PARAM_SURNAME
					+ CONNECTOR + surname + PARAMETER_CONNECTOR + REQUEST_PARAM_LOGIN + CONNECTOR + login
					+ PARAMETER_CONNECTOR + REQUEST_PARAM_EMAIL + CONNECTOR + email);

		} catch (ServiceException e) {
			log.error("Database error during registration new user.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}