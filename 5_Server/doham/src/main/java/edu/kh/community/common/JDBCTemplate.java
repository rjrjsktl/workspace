package edu.kh.community.common;

import java.sql.Statement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JDBCTemplate {	

	/*
	 * DB 연결 ( Connection 생성), 자동커밋 off
	 * 트랜젝션 제어, JDBC 객체 자원반환 ( close )
	 * 
	 * 이러한 JDBC에서 반복 사용되는 코드를 모아둔 클래스
	 * 
	 * 모든 필드, 메서드가 static *
	 * -> 어디서든지 클래스명.필드명 / 클래스명.메서드명
	 * 	호출 가능 ( 별도 객체 생성 x ) 
	 *
	 */

	private static Connection conn = null;
	// static 메서드에 필드를 사용하기 위해서는 필드도 static 이여야한다.


	//alt shift j
	/** DB 연결 정보를 담고있는 Connection 객체 생성 및 반환 메서드
	 * @return
	 */
	public static Connection getConnection() {

		try {

			// JNDI ( java naming & directory interface API )
			// - 디렉토리에 접근하는데 사용하는 JAVA api
			// - 애플리케이션 ( 프로그램, 웹앱) 은 JDNI를 이용해서 파일 또는 서버 RESOURCE를 찾을 수 있음.
			
			Context initContext = new InitialContext();
			
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			
			// DBCP 세팅의 <Resource> 태그를 찾아서 DataSource 형식의 객체로 얻어오기
			// DataSource : Connection Pool을 구현하는 객체 ( 만들어둔 Connection 얻어오기 가능 )
			
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
			
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			
			
		}catch( Exception e) {
			System.out.println("[Connection 생성 중 예외 발생]");
			e.printStackTrace();
		}


		return conn;
	}

	
	
	/** Connection 객체 자원 반환 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			
			if( conn !=null && !conn.isClosed()) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Statement stmt) {
	    try {
	        if (stmt != null && !stmt.isClosed()) {
	            stmt.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	/** ResultSet 객체 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			
			if( rs !=null && !rs.isClosed()) rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 트랜잭션 commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			
			if( conn !=null && !conn.isClosed()) conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 트랜잭션 Rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			
			if( conn !=null && !conn.isClosed()) conn.rollback();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
