package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.UserDAO;
import by.htp.it.dao.cp.ConnectionPool;
import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.dao.exception.DAOExceptionInvalidPassword;
import by.htp.it.dao.exception.DAOExceptionLoginOrEmailExists;

public class SQLUserDAO implements UserDAO {

	private static final ConnectionPool CONN_POOL = ConnectionPool.getInstance();
	private static final ReentrantLock lockRegister = new ReentrantLock();

	public static final String EMPTY_CONTENT = "";
	public static final String REGISTRATION_INSERT_INTO = "INSERT INTO users(name,surname,login,email,password) VALUES(?,?,?,?,?)";
	public static final String GET_USER_SELECT = "SELECT * FROM users WHERE login=? ";
	public static final String PREPARE_REGISTER_LOGIN_SELECT = "SELECT * FROM users WHERE login=?";
	public static final String PREPARE_REGISTER_EMAIL_SELECT = "SELECT * FROM users WHERE email=?";
	public static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";
	public static final int NOT_EXISTENT_ID = 0;

	@Override
	public void register(RegistrationInfo info) throws DAOException, DAOExceptionLoginOrEmailExists {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(REGISTRATION_INSERT_INTO);
				PreparedStatement psLogin = con.prepareStatement(PREPARE_REGISTER_LOGIN_SELECT);
				PreparedStatement psEmail = con.prepareStatement(PREPARE_REGISTER_EMAIL_SELECT)) {

			lockRegister.lock();
			psLogin.setString(1, info.getLogin());
			psEmail.setString(1, info.getEmail());

			ResultSet rsLogin = psLogin.executeQuery();
			ResultSet rsEmail = psEmail.executeQuery();

			while (rsLogin.next()) {

				throw new DAOExceptionLoginOrEmailExists("Login exists");

			}

			while (rsEmail.next()) {

				throw new DAOExceptionLoginOrEmailExists("Email exists");
			}

			ps.setString(1, info.getName());
			ps.setString(2, info.getSurname());
			ps.setString(3, info.getLogin());
			ps.setString(4, info.getEmail());
			ps.setString(5, info.getPassword());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);

		} finally {

			lockRegister.unlock();
		}

	}

	@Override
	public void changePassword(RegistrationInfo info) throws DAOException, DAOExceptionInvalidPassword {

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement psUpdatePassword = con.prepareStatement(CHANGE_PASSWORD)) {

			psUpdatePassword.setString(1, info.getNewPassword());
			psUpdatePassword.setString(2, info.getLogin());
			psUpdatePassword.executeUpdate();

		} catch (SQLException |

				ConnectionPoolException e) {

			throw new DAOException(e);

		}

	}

	@Override
	public User getUser(String login) throws DAOException {
		User newUser = null;

		int id = NOT_EXISTENT_ID;
		String role = EMPTY_CONTENT;
		String password = EMPTY_CONTENT;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(GET_USER_SELECT)) {

			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
				role = rs.getString(2);
				password = rs.getString(7);
				newUser = new User(id, login, role, password);
			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return newUser;
	}

}
