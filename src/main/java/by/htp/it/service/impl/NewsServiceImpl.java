package by.htp.it.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.service.NewsService;
import by.htp.it.service.exception.ServiceException;
import by.htp.it.service.exception.ServiceExceptionValidationNews;
import by.htp.it.service.validator.NewsServiceValidator;

public class NewsServiceImpl implements NewsService {

	private static final DAOProvider daoProvider = DAOProvider.getInstance();
	private static final NewsDAO newsDAO = daoProvider.getSQLNewsDAOImpl();
	private static final NewsServiceValidator newsServiceValidator = new NewsServiceValidator();

	@Override
	public void add(News news) throws ServiceException, ServiceExceptionValidationNews {

		newsServiceValidator.validateNews(news);

		try {
			newsDAO.add(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public void update(News news) throws ServiceException, ServiceExceptionValidationNews {

		newsServiceValidator.validateNews(news);

		try {
			newsDAO.update(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public void delete(News news) throws ServiceException {

		try {
			newsDAO.delete(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> getLatestNews() throws ServiceException {

		List<News> listNews = new ArrayList<>();

		try {
			listNews = newsDAO.getLatestNews();
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return listNews;
	}

	@Override
	public News read(News news) throws ServiceException {

		News newsForReading;

		try {
			newsForReading = newsDAO.read(news);

			if (newsForReading == null) {
				throw new ServiceException("The requested news no longer exists.");
			}

		} catch (DAOException e) {

			throw new ServiceException(e);
		}
		return newsForReading;

	}

	@Override
	public List<News> readAllNews(int startFrom, int limitRecords) throws ServiceException {

		List<News> listOfLimitAmountOfNews = new ArrayList<>();

		try {
			listOfLimitAmountOfNews = newsDAO.readAllNews(startFrom, limitRecords);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		return listOfLimitAmountOfNews;
	}

	@Override
	public int getNumberOfRecords() throws ServiceException {

		int numberOfRecords;

		try {
			numberOfRecords = newsDAO.getNumberOfRecords();
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return numberOfRecords;
	}

	@Override
	public boolean isFavorite(News news) throws ServiceException {

		boolean isFavourite;

		try {
			isFavourite = newsDAO.isFavorite(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		return isFavourite;
	}

	@Override
	public void addToFavorites(News newsToFavorites) throws ServiceException {
		try {
			newsDAO.addToFavorites(newsToFavorites);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public void deleteFromFavorites(News newsFromFavorites) throws ServiceException {

		try {
			newsDAO.deleteFromFavorites(newsFromFavorites);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> viewFavoriteNews(User user) throws ServiceException {

		List<News> listOfFavoriteNews = new ArrayList<>();

		try {
			listOfFavoriteNews = newsDAO.viewFavoriteNews(user);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return listOfFavoriteNews;
	}

	@Override
	public void offerNews(News offeredNews) throws ServiceException, ServiceExceptionValidationNews {

		newsServiceValidator.validateNews(offeredNews);

		try {
			newsDAO.offerNews(offeredNews);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> viewMyOfferedNews(User user) throws ServiceException {

		List<News> listOfOfferedNews = new ArrayList<>();

		try {
			listOfOfferedNews = newsDAO.viewMyOfferedNews(user);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return listOfOfferedNews;
	}

	@Override
	public List<News> viewOfferedNews() throws ServiceException {

		List<News> listOfOfferedNews = new ArrayList<>();

		try {
			listOfOfferedNews = newsDAO.viewOfferedNews();
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return listOfOfferedNews;
	}

	@Override
	public void denyToPublish(News newsDenyToPublish) throws ServiceException {

		try {
			newsDAO.denyToPublish(newsDenyToPublish);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

	@Override
	public News checkOfferedNews(News news) throws ServiceException {

		News newsForChecking;

		try {
			newsForChecking = newsDAO.checkOfferedNews(news);

			if (newsForChecking == null) {
				throw new ServiceException("The requested news no longer exists.");
			}

		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		return newsForChecking;
	}

	@Override
	public void approvePublication(News newsApprovePublication) throws ServiceException {

		try {
			newsDAO.approvePublication(newsApprovePublication);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

	}

}
