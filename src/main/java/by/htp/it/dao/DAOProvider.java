package by.htp.it.dao;

import by.htp.it.dao.impl.SQLCommentDAO;
import by.htp.it.dao.impl.SQLNewsDAO;
import by.htp.it.dao.impl.SQLUserDAO;

public class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private final UserDAO sqlUserDAOImpl = new SQLUserDAO();
	private final NewsDAO sqlNewsDAOImpl = new SQLNewsDAO();
	private final CommentDAO sqlCommentDAOImpl = new SQLCommentDAO();

	private DAOProvider() {

	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getSQLUserDAOImpl() {
		return sqlUserDAOImpl;
	}

	public NewsDAO getSQLNewsDAOImpl() {
		return sqlNewsDAOImpl;
	}

	public CommentDAO getSQLCommentDAOImpl() {
		return sqlCommentDAOImpl;
	}

}
