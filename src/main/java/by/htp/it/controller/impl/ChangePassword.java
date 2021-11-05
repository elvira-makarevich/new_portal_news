package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.UserService;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionInvalidPassword;
import by.htp.it.service.exception.ServiceExceptionValidationPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangePassword implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final Logger log = LogManager.getLogger(ChangePassword.class);

	public static final String SESSION_ATTR_USER = "user";

	public static final String REQUEST_PARAM_OLD_PASSWORD = "oldPassword";
	public static final String REQUEST_PARAM_NEW_PASSWORD1 = "newPassword1";
	public static final String REQUEST_PARAM_NEW_PASSWORD2 = "newPassword2";

	public static final String PATH_EMPTY_PARAMETER = "Controller?command=Go_To_Change_Password_Page&errorEmptyFields=Fill in all the fields.";
	public static final String PATH_PASSWORDS_MISMATCH = "Controller?command=Go_To_Change_Password_Page&newPasswordsErrorMismatch=New passwords do not match. Try again.";
	public static final String PATH_OLD_MATCHES_NEW = "Controller?command=Go_To_Change_Password_Page&oldPasswordMatchesNewPassword=The old password entered and the new password match.";
	public static final String PATH_AFTER_CHANGING_PASSWORD = "Controller?command=open_profile&messageSuccessChangePassword=The password was changed.";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=Go_To_change_password_Page&messageErrorValidationPassword=New password does not meet security requirements.";
	public static final String PATH_AFTER_INVALID_PASSWORD = "Controller?command=Go_To_change_password_Page&messageErrorInvalidPasswordDB=You entered an invalid password.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user;

		String oldPassword;
		String newPassword1;
		String newPassword2;

		user = (User) request.getSession().getAttribute(SESSION_ATTR_USER);

		oldPassword = (String) request.getParameter(REQUEST_PARAM_OLD_PASSWORD);
		newPassword1 = (String) request.getParameter(REQUEST_PARAM_NEW_PASSWORD1);
		newPassword2 = (String) request.getParameter(REQUEST_PARAM_NEW_PASSWORD2);

		if (oldPassword == null || oldPassword.isEmpty() || newPassword1 == null || newPassword1.isEmpty()
				|| newPassword2 == null || newPassword2.isEmpty()) {

			response.sendRedirect(PATH_EMPTY_PARAMETER);
			return;

		}

		if (!newPassword1.equals(newPassword2)) {
			response.sendRedirect(PATH_PASSWORDS_MISMATCH);
			return;
		}

		if (oldPassword.equals(newPassword1)) {
			response.sendRedirect(PATH_OLD_MATCHES_NEW);
			return;
		}

		RegistrationInfo info = new RegistrationInfo(user.getLogin(),oldPassword, newPassword1);

		try {

			userService.changePassword(info);
			response.sendRedirect(PATH_AFTER_CHANGING_PASSWORD);

		} catch (ServiceExceptionValidationPassword e) {
			log.error("The new password does not meet the password requirements.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION);

		} catch (ServiceExceptionInvalidPassword e) {
			log.error("Incorrect user password entered.", e);
			response.sendRedirect(PATH_AFTER_INVALID_PASSWORD);

		} catch (ServiceException e) {
			log.error("Database error during changing password.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
