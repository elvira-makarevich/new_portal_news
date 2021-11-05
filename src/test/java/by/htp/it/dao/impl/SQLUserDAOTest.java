package by.htp.it.dao.impl;

import static org.junit.Assert.*;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;

import org.junit.Ignore;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.cp.ConnectionPool;

import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;

public class SQLUserDAOTest {

	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/test_news_portal?useSSL=false";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "1111";

	public SQLUserDAOTest() {
		// TODO Auto-generated constructor stub
	}

	@Mock
	ConnectionPool connectionPool;
	@Mock
	Connection mockConn;
	@Mock
	PreparedStatement mockPreparedStmnt;
	@Mock
	ResultSet mockResultSet;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws SQLException, ConnectionPoolException, ClassNotFoundException {
		MockitoAnnotations.initMocks(this);
		Class.forName(DB_DRIVER);
	}

	@Ignore
	@Test
	public void testSignIn() throws SQLException, ConnectionPoolException, DAOException {

		assertNotNull(connectionPool);
		User authorized = new User(1, "Elvira", "admin");

		Mockito.when(connectionPool.takeConnection()).thenReturn(mockConn);
		Mockito.when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
		doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
		doNothing().when(mockPreparedStmnt).executeUpdate();
		Mockito.when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
		Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);

		when(mockResultSet.getInt(1)).thenReturn(authorized.getId());
		when(mockResultSet.getString(3)).thenReturn(authorized.getName());
		when(mockResultSet.getString(2)).thenReturn(authorized.getRole());

		verify(connectionPool, times(1)).takeConnection();

	}

	@Test
	public void testSignInOk() throws DAOException, ConnectionPoolException, SQLException {

		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");

		User unauthorizedUser = new User("elvira", "Pers1111!");

		User expectedUser = new User(5, "Elvira", "admin");
		User authorizedUser = null;

		ps.setString(1, unauthorizedUser.getLogin());
		ps.setString(2, unauthorizedUser.getPassword());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			int id = rs.getInt(1);
			String name = rs.getString(3);
			String role = rs.getString(2);
			authorizedUser = new User(id, name, role);
		}

		assertEquals(expectedUser, authorizedUser);

	}

	@Test
	public void testSignInNot() throws DAOException, ConnectionPoolException, SQLException {

		User unauthorizedUser = new User("elvira", "Pers11!");
		User authorizedUser = null;

		int id = 0;
		String name = "";
		String role = "";

		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");

		ps.setString(1, unauthorizedUser.getLogin());
		ps.setString(2, unauthorizedUser.getPassword());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			id = rs.getInt(1);
			name = rs.getString(3);
			role = rs.getString(2);
			authorizedUser = new User(id, name, role);
		}

		assertNull(authorizedUser);

	}

	@Test(expected = SQLException.class)
	public void testRegisterOk() throws ConnectionPoolException, SQLException {

		RegistrationInfo info = new RegistrationInfo("Alexandra", "Makarevich", "alexande", "alex@mail.ru",
				"Pers1111!");

		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO users(name,surname,login,email,password) VALUES(?,?,?,?,?)");

		ps.setString(1, info.getName());
		ps.setString(2, info.getSurname());
		ps.setString(3, info.getLogin());
		ps.setString(4, info.getEmail());
		ps.setString(5, info.getPassword());
		ps.executeUpdate();

	}

}
