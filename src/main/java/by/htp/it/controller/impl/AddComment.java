package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.Comment;

import by.htp.it.controller.Command;
import by.htp.it.service.CommentService;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionValidationComment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddComment implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final CommentService commentService = provider.getCommentService();

	private static final Logger log = LogManager.getLogger(AddComment.class);

	public static final String REQUEST_PARAM_COMMENT = "comment";
	public static final String REQUEST_PARAM_ID_NEWS = "idNews";
	public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
	public static final String PATH_EMPTY_COMMENT = "Controller?command=Read_NEWS&idNews=";
	public static final String PATH_AFTER_ADD_COMMENT = "Controller?command=Read_NEWS&idNews=";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=Read_NEWS&messageErrorValidationAddComment=There objectionable words.&idNews=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_main_Page&responseCommandServiceException=Something went wrong...Try again later.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;

		int idNews;
		String contentComment;

		idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);

		contentComment = (String) request.getParameter(REQUEST_PARAM_COMMENT);
		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID_NEWS));

		if (contentComment == null || contentComment.isEmpty()) {
			response.sendRedirect(PATH_EMPTY_COMMENT + idNews);
			return;
		}

		Comment comment = new Comment(idNews, idUser, contentComment);

		try {

			commentService.addComment(comment);
			response.sendRedirect(PATH_AFTER_ADD_COMMENT + idNews);

		} catch (ServiceExceptionValidationComment e) {

			log.error("There objectionable words in the comment.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION + idNews);

		}

		catch (ServiceException e) {
			log.error("Database error during adding a comment.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
