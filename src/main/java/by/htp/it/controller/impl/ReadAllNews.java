package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

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

public class ReadAllNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final Logger log = LogManager.getLogger(ReadAllNews.class);

	public static final String PARAM_PAGE = "currentPage";
	public static final String RESPONSE_ATTR_NO_OF_PAGES = "numberOfPages";
	public static final String SESSION_ATTRIBUTE_LIMIT_NEWS = "limitNews";
	public static final String PARAMETER_CURRENT_PAGE = "&currentPage=";

	public static final String SESSION_ATTR_PATH = "path";
	public static final String PATH_ALL_NEWS_PAGE = "/WEB-INF/jsp/allNews.jsp";
	public static final String PATH_AFTER_GETTING_NEWS = "read_All_News&numberOfPages=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";

	public static final int RECORDS_PER_PAGE = 5;
	public static final int DEFAULT_PAGE_NUMBER = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> listOfLimitAmountOfNews;
		int numberOfRecords;
		int numberOfPages;

		try {

			int page = DEFAULT_PAGE_NUMBER;

			if (request.getParameter(PARAM_PAGE) != null) {
				page = Integer.parseInt(request.getParameter(PARAM_PAGE));
			}

			listOfLimitAmountOfNews = newsService.readAllNews((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);

			numberOfRecords = newsService.getNumberOfRecords(); // получить количесво всех записей
			numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / RECORDS_PER_PAGE);// рассчитать количество
																						// страниц
		
			request.setAttribute(SESSION_ATTRIBUTE_LIMIT_NEWS, listOfLimitAmountOfNews);
			request.setAttribute(PARAM_PAGE, page);
			request.setAttribute(RESPONSE_ATTR_NO_OF_PAGES, numberOfPages);
			
			request.getSession().setAttribute(SESSION_ATTR_PATH, PATH_AFTER_GETTING_NEWS + numberOfPages + PARAMETER_CURRENT_PAGE + page);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_ALL_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("Database error during getting the limit amount of news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}

	}

}
