package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.service.CommentService;
import by.htp.it.service.NewsService;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReadNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();
	private static final CommentService commentService = provider.getCommentService();

	private static final Logger log = LogManager.getLogger(ReadNews.class);

	public static final String REQUEST_PARAM_ID = "idNews";
	public static final String REQUEST_ATTR_ID = "idNews";
	public static final String REQUEST_ATTR_NEWS = "newsForReading";
	public static final String REQUEST_ATTR_COMMENTS = "comments";
	public static final String ATTRIBUTE_USER_ID = "idUser";
	public static final String IS_FAVORITE = "isFavorite";

	public static final String SESSION_ATTR_PATH = "path";
	public static final String READ_NEWS_PAGE = "/WEB-INF/jsp/readNews.jsp";
	public static final String SESSION_ATTR_PATH_COMMAND = "read_news&idNews=";
	public static final String VALIDATION_ERROR_ADD_COMMENT = "messageErrorValidationAddComment";
	public static final String MESSAGE_ADD_TO_FAVORITES = "responseCommandAddToFavorites";
	public static final String MESSAGE_DELETE_FROM_FAVORITES = "responseCommandDeleteFromFavorites";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;

		int idNews;
		News news;
		News newsForReading;
		List<Comment> comments;
		boolean isFavorite;

		idUser = (int) request.getSession().getAttribute(ATTRIBUTE_USER_ID);
		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		news = new News(idNews, idUser);

		try {

			newsForReading = newsService.read(news);
			isFavorite = newsService.isFavorite(news);
			comments = commentService.getCommentsForNews(newsForReading);

			String errorValidationAddComment = request.getParameter(VALIDATION_ERROR_ADD_COMMENT);
			String responseCommandDeleteFromFavorites = request.getParameter(MESSAGE_DELETE_FROM_FAVORITES);
			String responseCommandAddToFavorites = request.getParameter(MESSAGE_ADD_TO_FAVORITES);
			request.setAttribute(REQUEST_ATTR_NEWS, newsForReading);
			request.setAttribute(REQUEST_ATTR_ID, idNews);
			request.setAttribute(REQUEST_ATTR_COMMENTS, comments);
			request.setAttribute(IS_FAVORITE, isFavorite);
			request.setAttribute(VALIDATION_ERROR_ADD_COMMENT, errorValidationAddComment);
			request.setAttribute(MESSAGE_DELETE_FROM_FAVORITES, responseCommandDeleteFromFavorites);
			request.setAttribute(MESSAGE_ADD_TO_FAVORITES, responseCommandAddToFavorites);

			request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND + idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(READ_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("Database error during getting the news for reading.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
