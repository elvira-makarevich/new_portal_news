package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command {

	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCALE = "local";
	public static final String PART_OF_PATH = "Controller?command=";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(SESSION_ATTR_LOCALE, request.getParameter(SESSION_ATTR_LOCALE));
		String path = PART_OF_PATH + (String) request.getSession(true).getAttribute(SESSION_ATTR_PATH);
		response.sendRedirect(path);

	}

}
