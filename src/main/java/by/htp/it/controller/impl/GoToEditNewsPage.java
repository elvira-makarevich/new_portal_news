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

public class GoToEditNewsPage implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(GoToEditNewsPage.class);

	public static final String REQUEST_PARAM_ID_NEWS = "idNews";
	public static final String REQUEST_ATTR_TITLE = "title";
	public static final String REQUEST_ATTR_BRIEF = "brief";
	public static final String REQUEST_ATTR_CONTENT = "content";

	public static final String PATH_EDIT_NEWS_PAGE = "/WEB-INF/jsp/editNews.jsp";
	public static final String SESSION_ATTR_PATH_COMMAND = "go_to_edit_news_page&idNews=";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		News news;

		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID_NEWS));
		news = new News(idNews);

		try {

			News newsForUpdating;
			newsForUpdating = newsService.read(news);

			request.setAttribute(REQUEST_PARAM_ID_NEWS, newsForUpdating.getId());
			request.setAttribute(REQUEST_ATTR_TITLE, newsForUpdating.getTitle());
			request.setAttribute(REQUEST_ATTR_BRIEF, newsForUpdating.getBrief());
			request.setAttribute(REQUEST_ATTR_CONTENT, newsForUpdating.getContent());

			request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND + idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_EDIT_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {

			log.error("Database error during editing a news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
