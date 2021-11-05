package by.htp.it.service;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.service.exception.ServiceException;

public interface NewsService {

	void add(News news) throws ServiceException;

	void update(News news) throws ServiceException;

	void delete(News news) throws ServiceException;

	News read(News news) throws ServiceException;

	List<News> getLatestNews() throws ServiceException;

	List<News> readAllNews(int startNews, int endNews) throws ServiceException;

	List<News> viewFavoriteNews(User user) throws ServiceException;

	int getNumberOfRecords() throws ServiceException;

	boolean isFavorite(News news) throws ServiceException;

	void addToFavorites(News newsToFavorites) throws ServiceException;

	void deleteFromFavorites(News newsFromFavorites) throws ServiceException;

	void offerNews(News offeredNews) throws ServiceException;

	List<News> viewMyOfferedNews(User user) throws ServiceException;

	List<News> viewOfferedNews() throws ServiceException;

	void denyToPublish(News newsDenyToPublish) throws ServiceException;

	News checkOfferedNews(News news) throws ServiceException;

	void approvePublication(News newsApprovePublication) throws ServiceException;

}
