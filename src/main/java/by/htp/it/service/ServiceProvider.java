package by.htp.it.service;

import by.htp.it.service.impl.CommentServiceImpl;
import by.htp.it.service.impl.NewsServiceImpl;
import by.htp.it.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private final UserService userService = new UserServiceImpl();
	private final NewsService newsService = new NewsServiceImpl();
	private final CommentService commentService = new CommentServiceImpl();

	private ServiceProvider() {

	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

}
