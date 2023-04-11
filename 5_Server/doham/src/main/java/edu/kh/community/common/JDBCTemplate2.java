package edu.kh.community.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate2 {

	public JDBCTemplate2() {}

	public static Connection getConnection() {

		Connection conn = null;

		try {

			Class.forName("oracle.jdbc.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "id";
			String password = "pw";

			conn = DriverManager.getConnection(url, user, password);

			if (conn != null) {
				System.out.println("DB conn success");
			} else {
				System.out.println("DB conn fail");
			}

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}


	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null && !pstmt.isClosed()) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void close(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try { 
			if (conn != null && !conn.isClosed()) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
