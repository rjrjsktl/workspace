package edu.kh.community.todo.model.dao;

import java.sql.Connection;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.todo.model.vo.Todo;

public class TodoDAO {

	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Properties prop;

	// DAO 기본생성자
	public TodoDAO() {
		try {
			prop = new Properties();
			String filePath = TodoDAO.class.getResource("/edu/kh/community/sql/todo-sql.xml").getPath();
			prop.loadFromXML( new FileInputStream(filePath) );
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// 인서트 DAO
	public int insertTodo(Connection conn, int memberNo, String add, int currentIndex) throws Exception {
	    int result = 0;

	    try {
	   
	        String sql = prop.getProperty("insertTodo");

	        pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, currentIndex);
	        pstmt.setString(2, add);
	        pstmt.setInt(3, memberNo);
	        
	        result = pstmt.executeUpdate();

	    } finally {
	        close(pstmt);
	    }

	    return result;
	}

	// 전원선택 DAO
	public int selectMemNum(Connection conn, int memberNo) throws Exception {

		String sql = prop.getProperty("selectNo");
		int memnum = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memnum = rs.getInt("MEMBER_NO");
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return memnum;

	}

	// 전원호출 DAO
	public List<Todo> selectAll(Connection conn, int memberNo) throws Exception {
		List<Todo> todoList = new ArrayList<>();
		Todo todo = null;

		try {
			String sql = prop.getProperty("selectTodoAll");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();
			//	    	private int todonum;
			//	    	private String content;
			//	    	private int memberNo;
			//	    	private char flag;

			while(rs.next()) {
				todo = new Todo();
				todo.setMemberNo(rs.getInt("MEMBER_NO"));
				todo.setTodonum(rs.getInt("TODO_NO"));
				todo.setFlag(rs.getString("FLAG"));
				todo.setContent(rs.getString("TODO_CONTENTS"));
				todoList.add(todo);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return todoList;
	}

	// 완료삭제 DAO 세션플래그Y만 삭제
	public int deleteAll(Connection conn, int memberNo) throws Exception {
		
		int result = 0;
		

		try {
			String sql = prop.getProperty("deleteAll");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();


		} finally {

			close(pstmt);

		}

		return result;

	}
	
	// 전원 세션플래그 Y로 변경
	public int updateAll(Connection conn, int memberNo, String action) throws Exception {
		  int result = 0;
		    String sql = null;

		    if (action.equals("Y")) {
		        sql = prop.getProperty("updateAllY");
		    } else if (action.equals("N")) {
		        sql = prop.getProperty("updateAllN");
		    }

		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, memberNo);
		        result = pstmt.executeUpdate();
		    } finally {
		        close(pstmt);
		    }

		    System.out.println("result: " + result); // 추가한 로그

		    return result;
	}

	
	
	// 하나만 선택해서 세션플레그 변경
	public int updateOne(Connection conn, int currentIndex, String action2) throws Exception {
	   
		int result = 0;
	    String sql = null;

	    if (action2.equals("Y")) {
	        sql = prop.getProperty("updateOneY");
	    } else if (action2.equals("N")) {
	        sql = prop.getProperty("updateOneN");
	    }

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, currentIndex);
	        result = pstmt.executeUpdate();
	    } finally {
	        close(pstmt);
	    }

	    System.out.println("result: " + result); // 추가한 로그

	    return result;
	}



	public int changeContents(Connection conn, int memberNo, int currentIndex, String taskinput) throws Exception {
		 int result = 0;
	    	String sql = prop.getProperty("changeContents");

		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, taskinput);
		        pstmt.setInt(2, currentIndex);
		        result = pstmt.executeUpdate();
		    } finally {
		        close(pstmt);
		    }

		    System.out.println("result: " + result); // 추가한 로그

		    return result;
	}



	
}








