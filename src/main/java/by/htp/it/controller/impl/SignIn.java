package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.UserService;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionInvalidData;
import by.htp.it.service.exception.ServiceExceptionValidationUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final Logger log = LogManager.getLogger(SignIn.class);

	public static final String REQUEST_PARAM_LOGIN = "login";
	public static final String REQUEST_PARAM_PASSWORD = "password";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String SESSION_ATTRIBUTE_USER_ID = "idUser";
	public static final String PATH_AFTER_SING_IN = "Controller?command=GO_TO_MAIN_PAGE&messageSuccessSignIn=You have successfully log in! Pleasant viewing!";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=GO_TO_AUTHORIZATION_PAGE&messageErrorValidationSignIn=Validation error.";
	public static final String PATH_AFTER_INVALID_DATA = "Controller?command=GO_TO_AUTHORIZATION_PAGE&messageErrorInvalidData=Incorrect login and (or) password. Try again.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login;
		String password;

		login = (String) request.getParameter(REQUEST_PARAM_LOGIN);
		password = (String) request.getParameter(REQUEST_PARAM_PASSWORD);

		User unauthorizedUser = new User(login, password);

		try {

			User user = userService.signIn(unauthorizedUser);

			HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_ATTRIBUTE_USER, user);
			session.setAttribute(SESSION_ATTRIBUTE_USER_ID, user.getId());

			response.sendRedirect(PATH_AFTER_SING_IN);

		} catch (ServiceExceptionValidationUser e) {
			log.error("Authorization: validation error.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION);

		} catch (ServiceExceptionInvalidData e) {
			log.error("Authorization: incorrect login and (or) password.", e);
			response.sendRedirect(PATH_AFTER_INVALID_DATA);

		} catch (ServiceException e) {
			log.error("Database error during authorization.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}
	}
}
