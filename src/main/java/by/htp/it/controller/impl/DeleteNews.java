package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.service.NewsService;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(DeleteNews.class);

	public static final String REQUEST_PARAM_ID = "idNews";

	public static final String PATH_AFTER_DELETE_NEWS = "Controller?command=Go_To_Main_Page&responseCommandDeleteNews=News was deleted.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		News news;

		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		news = new News(idNews);

		try {

			newsService.delete(news);
			response.sendRedirect(PATH_AFTER_DELETE_NEWS);

		} catch (ServiceException e) {

			log.error("Database error during deleting a news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}
	}

}
