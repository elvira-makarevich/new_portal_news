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

public class DeleteFromFavorites implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(DeleteFromFavorites.class);

	public static final String REQUEST_PARAM_ID_NEWS = "idNews";
	public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";

	public static final String PATH_AFTER_DELETE_FROM_FAVORITES = "Controller?command=read_news&responseCommandDeleteFromFavorites=News was deleted from favorites.&idNews=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;

		int idNews;
		News newsFromFavorites;

		idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);
		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID_NEWS));
		newsFromFavorites = new News(idNews, idUser);

		try {

			newsService.deleteFromFavorites(newsFromFavorites);
			response.sendRedirect(PATH_AFTER_DELETE_FROM_FAVORITES + idNews);

		} catch (ServiceException e) {

			log.error("Database error during deleting from favorites.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
