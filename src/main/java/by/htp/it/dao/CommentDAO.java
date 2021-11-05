package by.htp.it.dao;

import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.dao.exception.DAOException;

public interface CommentDAO {

	void addComment(Comment comment) throws DAOException;

	List<Comment> getCommentsForNews(News news) throws DAOException;

}
