package by.htp.it.dao;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.dao.exception.DAOException;

public interface NewsDAO {

	void add(News news) throws DAOException;

	void update(News news) throws DAOException;

	void delete(News news) throws DAOException;

	News read(News news) throws DAOException;

	List<News> getLatestNews() throws DAOException;

	List<News> readAllNews(int startNews, int endNews) throws DAOException;

	int getNumberOfRecords() throws DAOException;

	boolean isFavorite(News news) throws DAOException;

	void addToFavorites(News newsToFavorites) throws DAOException;

	void deleteFromFavorites(News newsFromFavorites) throws DAOException;

	List<News> viewFavoriteNews(User user) throws DAOException;

	void offerNews(News offeredNews) throws DAOException;

	List<News> viewMyOfferedNews(User user) throws DAOException;

	List<News> viewOfferedNews() throws DAOException;

	void denyToPublish(News newsDenyToPublish) throws DAOException;

	News checkOfferedNews(News news) throws DAOException;

	void approvePublication(News newsApprovePublication) throws DAOException;

}
