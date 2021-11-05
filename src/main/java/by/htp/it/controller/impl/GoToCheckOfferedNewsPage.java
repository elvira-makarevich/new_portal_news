package by.htp.it.controller.impl;

import java.io.IOException;

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

public class GoToCheckOfferedNewsPage implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(GoToCheckOfferedNewsPage.class);

	public static final String REQUEST_PARAM_ID = "idNews";
	public static final String REQUEST_ATTR = "news";

	public static final String SESSION_ATTR_PATH = "path";
	public static final String PATH_OFFERED_NEWS_PAGE = "/WEB-INF/jsp/checkOfferedNews.jsp";
	public static final String SESSION_ATTR_PATH_COMMAND = "go_to_check_offered_news_page&idNews=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		News news;

		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		news = new News(idNews);

		try {

			News newsForChecking;
			newsForChecking = newsService.checkOfferedNews(news);
			request.setAttribute(REQUEST_ATTR, newsForChecking);
			
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND+idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_OFFERED_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {

			log.error("Database error while opening the page to check the news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
