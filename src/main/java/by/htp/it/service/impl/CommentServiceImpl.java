package by.htp.it.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.dao.CommentDAO;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.service.CommentService;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionValidationComment;
import by.htp.it.service.validator.CommentServiceValidator;

public class CommentServiceImpl implements CommentService {

	private static final DAOProvider daoProvider = DAOProvider.getInstance();
	private static final CommentDAO comentDAO = daoProvider.getSQLCommentDAOImpl();
	private static final CommentServiceValidator commentServiceValidator = new CommentServiceValidator();

	@Override
	public void addComment(Comment comment) throws ServiceException, ServiceExceptionValidationComment {

		commentServiceValidator.validateAddComment(comment);

		try {
			comentDAO.addComment(comment);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Comment> getCommentsForNews(News news) throws ServiceException {

		List<Comment> commentsForNews = new ArrayList<>();

		try {
			commentsForNews = comentDAO.getCommentsForNews(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		return commentsForNews;
	}

}
