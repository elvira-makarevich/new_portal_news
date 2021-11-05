package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.service.NewsService;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewFavoriteNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(ViewFavoriteNews.class);

	public static final String RESPONSE_ATTR_NEWS = "favoriteNews";
	public static final String SESSION_ATTRIBUTE_USER = "user";

	public static final String SESSION_ATTR_PATH_COMMAND = "view_favorite_news";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String PATH_FAVORITE_NEWS_PAGE = "/WEB-INF/jsp/favoriteNews.jsp";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user;

		List<News> listOfFavoriteNews;

		user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER);

		try {

			listOfFavoriteNews = newsService.viewFavoriteNews(user);

			request.setAttribute(RESPONSE_ATTR_NEWS, listOfFavoriteNews);

			request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_FAVORITE_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {

			log.error("Database error during getting the list of favorite news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
