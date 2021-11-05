package by.htp.it.dao.cp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


import by.htp.it.dao.exception.ConnectionPoolException;

public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> usedConnection;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	public static ConnectionPool getInstance() {
		return instance;
	}

	private ConnectionPool() {

		DBResourceManager dbResourceManager = DBResourceManager.getInstance();

		this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourceManager.getValue(DBParameter.DB_URL);
		this.user = dbResourceManager.getValue(DBParameter.DB_USER);
		this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
		try {
			this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POLL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = 5;
		}
		try {
			initPoolData();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("InitPoolData error", e);
		}
	}

	public void initPoolData() throws ConnectionPoolException {

		try {
			Class.forName(driverName);
			usedConnection = new ArrayBlockingQueue<Connection>(poolSize);
			freeConnection = new ArrayBlockingQueue<Connection>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				freeConnection.add(getNewPooledConnection());
			}
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}

	}

	public Connection takeConnection() throws ConnectionPoolException {

		PooledConnection connection = null;

		try {
			connection = (PooledConnection) freeConnection.take();

			// на случай, если соединение не null, но с ним неполадки - создание нового
			// соединения
			if (connection != null) {
				if (!connection.isValid(0)) {

					connection.reallyClose();
					connection = null;

				} else if (freeConnection.size() + usedConnection.size() < poolSize) {
					freeConnection.add(getNewPooledConnection());
				}

			} else {
				throw new ConnectionPoolException("Database connection error.");
			}

			usedConnection.add(connection);

		} catch (SQLException e) {
			throw new ConnectionPoolException("Database connection error.", e);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Database connection error.", e);
		}
		return connection;
	}

	public void releaseConnection(PooledConnection connection) {

		try {
			connection.clearWarnings();
			usedConnection.remove(connection);
			freeConnection.offer(connection);
		} catch (SQLException e) {
			throw new RuntimeException("Connection Pool: problems returning a connection connection to free Connection", e);
		}
	}

	public void dispose() throws ConnectionPoolException {
		try {
			closeConnectionQueue(usedConnection);
			closeConnectionQueue(freeConnection);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException: close connection queue", e);
		}
	}


	public void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}

	private Connection getNewPooledConnection() throws SQLException {
		return new PooledConnection(DriverManager.getConnection(url, user, password));
	}

}