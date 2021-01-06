package app.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import app.core.exceptions.CouponSystemConnectionException;

public class ConnectionPool {

	private String url = "jdbc:mysql://localhost:3306/coupons_db?serverTimezone=Israel&createDatabaseIfNotExist=true";
	private String user = "root";
	private String password = "n12345";
	private static final int MAX = 5;
	private Set<Connection> connections = new HashSet<Connection>();

	private static ConnectionPool instance;

	private ConnectionPool() throws CouponSystemConnectionException {
		for (int i = 0; i < MAX; i++) {
			try {
				Connection con = DriverManager.getConnection(url, user, password);
				connections.add(con);
			} catch (SQLException e) {
				throw new CouponSystemConnectionException("Connection Error: failed to connect", e);
			}
		}
	}

	public static ConnectionPool getInstance() throws CouponSystemConnectionException {
		if (instance == null) {
			instance = new ConnectionPool();
			return instance;
		}
		return instance;
	}

	public synchronized Connection getConnection() throws CouponSystemConnectionException {
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new CouponSystemConnectionException("Connection Eroor: is interrupted", e);
			}
		}

		Iterator<Connection> it = connections.iterator();
		Connection connection = it.next();
		connections.remove(connection);
		return connection;
	}

	public synchronized void restoreConnection(Connection connection) {
		connections.add(connection);
		notify();
	}

	public synchronized void closeAllConnections() throws CouponSystemConnectionException {
		if (connections.size() == 5) {
			for (Connection connection : connections) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new CouponSystemConnectionException("Connection Error: failed to close", e);
				}
			}
		} else {
			throw new CouponSystemConnectionException(
					"Connection Error: failed to close all connections, missing connections in the pool");
		}

	}

}
