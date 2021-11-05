package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.bean.NewsStatus;
import by.htp.it.bean.User;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.cp.ConnectionPool;
import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;

public class SQLNewsDAO implements NewsDAO {

	private static final ConnectionPool CONN_POOL = ConnectionPool.getInstance();

	public static final String ADD_NEWS_INSERT_INTO = "INSERT INTO news(title,brief,content,date,id_user) VALUES(?,?,?,?,?)";
	public static final String OFFER_NEWS_INSERT_INTO = "INSERT INTO news(title,brief,content,date,id_user,status) VALUES(?,?,?,?,?,?)";
	public static final String ADD_NEWS_TO_FAVORITES_INSERT_INTO = "INSERT INTO favourite_news(idfavourite_news,id_user) VALUES(?,?)";
	public static final String UPDATE_NEWS = "UPDATE news SET title = ?, brief = ?, content = ? WHERE id = ?";
	public static final String DENY_TO_PUBLISH = "UPDATE news SET status = 'denied_publication' WHERE id = ?";
	public static final String APPROVE_PUBLICATION = "UPDATE news SET status = 'published' WHERE id = ?";
	public static final String DELETE_NEWS_ID = "DELETE FROM news WHERE id = ?";
	public static final String DELETE_FROM_FAVORITES = "DELETE FROM favourite_news WHERE idfavourite_news = ? AND id_user = ?";
	public static final String LIST_LATEST_NEWS_SELECT = "SELECT * FROM news WHERE status = 'published' ORDER BY date DESC LIMIT 5 ";
	public static final String LIST_OF_ALL_NEWS_SELECT = "SELECT * FROM news WHERE status = 'published' LIMIT ";
	public static final String COMMA = " ,";
	public static final String COUNT_AMOUNT_OF_ALL_NEWS = "SELECT * FROM news WHERE status = 'published'";
	public static final String LIST_OF_USER_OFFERED_NEWS = "SELECT * FROM news WHERE id_user = ?";
	public static final String LIST_OF_OFFERED_NEWS = "SELECT * FROM news WHERE status IN('on_approval','ON_APPROVAL')";
	public static final String LIST_OF_FAVORITE_NEWS = "SELECT news.* FROM news, favourite_news WHERE favourite_news.id_user = ? AND favourite_news.idfavourite_news=news.id";
	public static final String READ_NEWS_SELECT = "SELECT * FROM news WHERE id = ?";
	public static final String EMPTY_CONTENT = "";
	public static final String IS_FAVORITE_NEWS = "SELECT * FROM favourite_news WHERE idfavourite_news=? AND id_user=?";

	@Override
	public void add(News news) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(ADD_NEWS_INSERT_INTO)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.setInt(5, news.getIdUser());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

	}

	@Override
	public void update(News news) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_NEWS)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setInt(4, news.getId());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

	}

	@Override
	public void delete(News news) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection(); PreparedStatement ps = con.prepareStatement(DELETE_NEWS_ID)) {

			ps.setInt(1, news.getId());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

	}

	@Override
	public List<News> getLatestNews() throws DAOException {

		List<News> listNews = new ArrayList<>();

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(LIST_LATEST_NEWS_SELECT);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				int id = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);
				Date date = rs.getDate(5);
				News news = new News(id, title, brief, date);
				listNews.add(news);

			}

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

		return listNews;
	}

	@Override
	public News read(News news) throws DAOException {

		News newsForReading = null;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(READ_NEWS_SELECT)) {

			ps.setInt(1, news.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int idNews = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				newsForReading = new News(idNews, title, brief, content, date);

			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return newsForReading;

	}

	@Override
	public int getNumberOfRecords() throws DAOException {

		int numberOfRecords = 0;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(COUNT_AMOUNT_OF_ALL_NEWS);
				ResultSet rs = ps.executeQuery()) {

			rs.last();
			numberOfRecords = rs.getRow();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return numberOfRecords;
	}

	@Override
	public List<News> readAllNews(int startFrom, int limitRecords) throws DAOException {

		List<News> listOfLimitAmountOfNews = new ArrayList<>();

		String query = LIST_OF_ALL_NEWS_SELECT + startFrom + COMMA + limitRecords;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				int id = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);

				News news = new News(id, title, brief);
				listOfLimitAmountOfNews.add(news);

			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return listOfLimitAmountOfNews;
	}

	@Override
	public boolean isFavorite(News news) throws DAOException {

		boolean isFavourite = false;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(IS_FAVORITE_NEWS)) {

			ps.setInt(1, news.getId());
			ps.setInt(2, news.getIdUser());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				isFavourite = true;
			}
		}

		catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return isFavourite;
	}

	@Override
	public void addToFavorites(News newsToFavorites) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(ADD_NEWS_TO_FAVORITES_INSERT_INTO)) {

			ps.setInt(1, newsToFavorites.getId());
			ps.setInt(2, newsToFavorites.getIdUser());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

	}

	@Override
	public void deleteFromFavorites(News newsFromFavorites) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_FROM_FAVORITES)) {

			ps.setInt(1, newsFromFavorites.getId());
			ps.setInt(2, newsFromFavorites.getIdUser());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}
	}

	@Override
	public List<News> viewFavoriteNews(User user) throws DAOException {

		List<News> listOfFavoriteNews = new ArrayList<>();

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(LIST_OF_FAVORITE_NEWS)) {

			ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);

				News news = new News(id, title, brief);
				listOfFavoriteNews.add(news);

			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return listOfFavoriteNews;
	}

	@Override
	public void offerNews(News offeredNews) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(OFFER_NEWS_INSERT_INTO)) {

			ps.setString(1, offeredNews.getTitle());
			ps.setString(2, offeredNews.getBrief());
			ps.setString(3, offeredNews.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.setInt(5, offeredNews.getIdUser());
			ps.setString(6, NewsStatus.ON_APPROVAL.toString());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

	}

	@Override
	public List<News> viewMyOfferedNews(User user) throws DAOException {

		List<News> listOfOfferedNews = new ArrayList<>();

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(LIST_OF_USER_OFFERED_NEWS)) {

			ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int idOfferedNews = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);
				String status = rs.getString(7);

				status = status.toUpperCase();
				News news = new News(idOfferedNews, title, brief, NewsStatus.valueOf(status));

				listOfOfferedNews.add(news);

			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);

		}

		return listOfOfferedNews;
	}

	@Override
	public List<News> viewOfferedNews() throws DAOException {

		List<News> listOfOfferedNews = new ArrayList<>();

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(LIST_OF_OFFERED_NEWS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				int idOfferedNews = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);

				String status = rs.getString(7);

				status = status.toUpperCase();
				News news = new News(idOfferedNews, title, brief, NewsStatus.valueOf(status));

				listOfOfferedNews.add(news);
			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);

		}
		return listOfOfferedNews;
	}

	@Override
	public void denyToPublish(News newsDenyToPublish) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(DENY_TO_PUBLISH)) {

			ps.setInt(1, newsDenyToPublish.getId());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);

		}
	}

	@Override
	public News checkOfferedNews(News news) throws DAOException {

		News newsForChecking = null;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(READ_NEWS_SELECT)) {

			ps.setInt(1, news.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idNews = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				newsForChecking = new News(idNews, title, brief, content, date);
			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);

		}

		return newsForChecking;
	}

	@Override
	public void approvePublication(News newsApprovePublication) throws DAOException {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(APPROVE_PUBLICATION)) {

			ps.setInt(1, newsApprovePublication.getId());

			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);

		}
	}
}
