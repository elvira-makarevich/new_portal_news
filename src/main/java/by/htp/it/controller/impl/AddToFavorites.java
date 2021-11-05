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

public class AddToFavorites implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(AddToFavorites.class);

	public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
	public static final String REQUEST_PARAM_ID_NEWS = "idNews";

	public static final String PATH_AFTER_ADD_TO_FAVORITES = "Controller?command=Read_NEWS&responseCommandAddToFavorites=News was added to favorites.&idNews=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;

		int idNews;
		News newsToFavorites;

		idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);

		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID_NEWS));
		newsToFavorites = new News(idNews, idUser);

		try {

			newsService.addToFavorites(newsToFavorites);
			response.sendRedirect(PATH_AFTER_ADD_TO_FAVORITES + idNews);

		} catch (ServiceException e) {

			log.error("Database error during adding a news to favorites.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
