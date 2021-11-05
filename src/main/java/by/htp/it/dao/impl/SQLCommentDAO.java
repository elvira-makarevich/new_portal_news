package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.dao.CommentDAO;
import by.htp.it.dao.cp.ConnectionPool;
import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;

public class SQLCommentDAO implements CommentDAO {

	private static final ConnectionPool CONN_POOL = ConnectionPool.getInstance();

	public static final String ADD_COMMENT_INSERT_INTO = "INSERT INTO comments(id_news,id_user,comment,date) VALUES(?,?,?,?)";
	public static final String COMMENTS_FOR_NEWS = "SELECT * FROM comments WHERE id_news = ? ORDER BY date DESC LIMIT 7";

	@Override
	public void addComment(Comment comment) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(ADD_COMMENT_INSERT_INTO)) {

			ps.setInt(1, comment.getId_news());
			ps.setInt(2, comment.getId_user());
			ps.setString(3, comment.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

	}

	@Override
	public List<Comment> getCommentsForNews(News news) throws DAOException {

		List<Comment> commentsForNews = new ArrayList<>();

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(COMMENTS_FOR_NEWS)) {

			ps.setInt(1, news.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String content = rs.getString(4);
				Date date = rs.getDate(5);

				Comment comment = new Comment(content, date);
				commentsForNews.add(comment);

			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return commentsForNews;
	}

}
