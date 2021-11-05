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

public class AddNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(AddNews.class);

	public static final String REQUEST_PARAM_TITLE = "title";
	public static final String REQUEST_PARAM_BRIEF = "brief";
	public static final String REQUEST_PARAM_CONTENT = "content";
	public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
	public static final String PARAMETER_CONNECTOR = "&";
	public static final String CONNECTOR = "=";

	public static final String PATH_EMPTY_PARAMETER = "Controller?command=Go_To_add_news_Page&messageErrorEmpty=Fill all fields!!!";
	public static final String PATH_TOO_LONG_PARAMETER = "Controller?command=Go_To_add_news_Page&messageErrorTooLongParameter=Too long!!!&title=";
	public static final String PATH_AFTER_ADD_NEWS = "Controller?command=Go_To_Main_Page&responseCommandAddNews=News was added.";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandAddNewsErrorValidation=News was not added. There objectionable words for publication.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	public static final int MAX_TITLE_CHARACTERS = 45;
	public static final int MAX_BRIEF_CHARACTERS = 100;
	public static final int MAX_CONTENT_CHARACTERS = 700;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUser;

		String title;
		String brief;
		String content;
		News news;

		idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);

		title = (String) request.getParameter(REQUEST_PARAM_TITLE);
		brief = (String) request.getParameter(REQUEST_PARAM_BRIEF);
		content = (String) request.getParameter(REQUEST_PARAM_CONTENT);

		if (nullEmptyValidate(title, brief, content)) {
			response.sendRedirect(PATH_EMPTY_PARAMETER);
			return;
		}

		if (title.length() > MAX_TITLE_CHARACTERS || brief.length() > MAX_BRIEF_CHARACTERS
				|| content.length() > MAX_CONTENT_CHARACTERS) {
			response.sendRedirect(PATH_TOO_LONG_PARAMETER + title + PARAMETER_CONNECTOR + REQUEST_PARAM_BRIEF
					+ CONNECTOR + brief + PARAMETER_CONNECTOR + REQUEST_PARAM_CONTENT + CONNECTOR + content);
			return;
		}

		news = new News(title, brief, content, idUser);

		try {

			newsService.add(news);
			response.sendRedirect(PATH_AFTER_ADD_NEWS);

		} catch (ServiceExceptionValidationNews e) {

			log.error("There are objectionable words in the news.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION);
		} catch (ServiceException e) {

			log.error("Database error during adding the news.", e);
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
