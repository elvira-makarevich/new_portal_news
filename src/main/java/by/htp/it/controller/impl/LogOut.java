package by.htp.it.controller.impl;

import java.io.IOException;
import by.htp.it.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {

	public static final String MAIN_PAGE_REDIRECT = "Controller?command=GO_TO_MAIN_PAGE";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_ATTRIBUTE_USER);
		session.removeAttribute(SESSION_ATTRIBUTE_ID_USER);
		response.sendRedirect(MAIN_PAGE_REDIRECT);

	}

}
