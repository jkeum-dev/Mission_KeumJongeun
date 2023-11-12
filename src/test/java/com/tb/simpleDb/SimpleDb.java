package com.tb.simpleDb;

import java.sql.*;

public class SimpleDb {
	private String hostname;
	private String username;
	private String password;
	private String dbName;
	private boolean devMode;
	private Connection connection;

	public SimpleDb(String hostname, String username, String password, String dbName) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.dbName = dbName;
	}

	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}

	public void connect() throws SQLException {
		String connectionUrl = "jdbc:mysql://" + hostname + ":3306" +  "/" + dbName;
		connection = DriverManager.getConnection(connectionUrl, username, password);
		if (devMode) {
			System.out.println("Connected to the database.");
		}
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				if (devMode) {
					System.out.println("Disconnected from the database.");
				}
			} catch (SQLException e) {
				if (devMode) {
					System.out.println("Error while disconnecting: " + e.getMessage());
				}
			}
		}
	}

	public void run(String sql, Object... params) {
		try {
			connect(); // 데이터베이스 연결
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
				stmt.executeUpdate();
				if (devMode) {
					System.out.println("PreparedStatement executed: " + sql);
				}
			}
		} catch (SQLException e) {
			logError("Error executing PreparedStatement: " + sql, e);
		} finally {
			try {
				disconnect(); // 데이터베이스 연결 해제
			} catch (Exception e) {
				logError("Error disconnecting from the database", e);
			}
		}
	}


	// 로그 에러
	private void logError(String message, Exception e) {
		if (devMode) {
			System.err.println(message);
			e.printStackTrace();
		}
		// 로깅 시스템이 있다면 로그를 남길 수 있음.
	}
}
