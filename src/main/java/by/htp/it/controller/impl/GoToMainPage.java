package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.service.NewsService;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(GoToMainPage.class);

	public static final String PATH_MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_COMMAND = "Go_To_Main_Page";
	public static final String REQUEST_ATTRIBUTE_NEWS = "latestNews";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session;
			
		List<News> latestNews;		
		
		session = request.getSession(true);

		try {

			latestNews = newsService.getLatestNews();
			request.setAttribute(REQUEST_ATTRIBUTE_NEWS, latestNews);

			session.setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_MAIN_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("Database error during geting the latest news.", e);
//перевод на страницу

		}

	}
}