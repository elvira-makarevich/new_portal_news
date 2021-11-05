package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {
	
	public static final String PATH_UNKNOWN_COMMAND = "/WEB-INF/jsp/unknownCommand.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "unknown_command";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_UNKNOWN_COMMAND);
	    requestDispatcher.forward(request, response);
		
	}

}
