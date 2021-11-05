package by.htp.it.service;

import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.service.exception.ServiceException;

public interface CommentService {
	
	void addComment(Comment comment) throws ServiceException;

	List<Comment> getCommentsForNews(News news) throws ServiceException;

}
