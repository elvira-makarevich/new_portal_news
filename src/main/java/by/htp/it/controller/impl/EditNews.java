package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.service.NewsService;
import by.htp.it.service.ServiceProvider;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionValidationNews;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(EditNews.class);

	public static final String REQUEST_PARAM_ID = "idNews";
	public static final String REQUEST_PARAM_TITLE = "title";
	public static final String REQUEST_PARAM_BRIEF = "brief";
	public static final String REQUEST_PARAM_CONTENT = "content";
	public static final String PARAMETER_CONNECTOR = "&";
	public static final String CONNECTOR = "=";

	public static final String PATH_EMPTY_PARAMETER = "Controller?command=go_to_edit_news_page&messageErrorEmpty=Fill all fields!!!&idNews=";
	public static final String PATH_TOO_LONG_PARAMETER = "Controller?command=go_to_edit_news_page&messageErrorTooLongParameter=Too long!!!&idNews=";
	public static final String PATH_AFTER_EDIT_NEWS = "Controller?command=Go_To_Main_Page&responseCommandNewsEditOk=News was edited.";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=Go_To_Main_Page&messageErrorValidationEditNews=News was not edited. There objectionable words for publication.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	public static final int MAX_TITLE_CHARACTERS = 45;
	public static final int MAX_BRIEF_CHARACTERS = 100;
	public static final int MAX_CONTENT_CHARACTERS = 200;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		News news;

		String title;
		String brief;
		String content;

		title = request.getParameter(REQUEST_PARAM_TITLE);
		brief = request.getParameter(REQUEST_PARAM_BRIEF);
		content = request.getParameter(REQUEST_PARAM_CONTENT);
		idNews = Integer.parseInt((String) request.getParameter(REQUEST_PARAM_ID));

		if (nullEmptyValidate(title, brief, content)) {
			response.sendRedirect(PATH_EMPTY_PARAMETER + idNews);
			return;
		}

		if (title.length() > MAX_TITLE_CHARACTERS || brief.length() > MAX_BRIEF_CHARACTERS
				|| content.length() > MAX_CONTENT_CHARACTERS) {
			response.sendRedirect(PATH_TOO_LONG_PARAMETER + idNews);
			return;
		}

		news = new News(idNews, title, brief, content);

		try {

			newsService.update(news);
			response.sendRedirect(PATH_AFTER_EDIT_NEWS);

		} catch (ServiceExceptionValidationNews e) {

			log.error("There are objectionable words in the news.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION);

		} catch (ServiceException e) {
			log.error("Database error during editing a news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

	private static boolean nullEmptyValidate(String title, String brief, String content) {

		if (title == null || title.isEmpty()) {
			return true;
		}

		if (brief == null || brief.isEmpty()) {
			return true;
		}

		if (content == null || content.isEmpty()) {
			return true;
		}

		return false;

	}

}
