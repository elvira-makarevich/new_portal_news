package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GoToAddNewsPage implements Command {

	public static final String PATH_ADD_NEWS_PAGE = "/WEB-INF/jsp/addNews.jsp";
	public static final String SESSION_ATTR_PATH_COMMAND = "Go_To_Add_News_Page";
	public static final String SESSION_ATTR_PATH = "path";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_ADD_NEWS_PAGE);
		requestDispatcher.forward(request, response);

	}
}
