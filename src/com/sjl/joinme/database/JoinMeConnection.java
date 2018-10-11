package com.sjl.joinme.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JoinMeConnection implements DatabaseInterface {
	private static Connection conn = null;

	private JoinMeConnection() {
	}

	static {
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("sucess cnn");
		}catch(Exception e) {
			System.out.println("+++Exception in getConnection:"+e);
		}
	}

	public static Connection getConnection() {
		return conn;
	}
}