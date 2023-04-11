package edu.kh.community.todo.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.todo.model.dao.TodoDAO;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.todo.model.vo.Todo;


public class TodoService {

	private TodoDAO dao = new TodoDAO();


	// 인서트 완벽하게 가능함.
	public int insertTodo(int memberNo, String add, int currentIndex) throws Exception {
		Connection conn = getConnection();

		int result = dao.insertTodo(conn, memberNo, add, currentIndex);

		if(result > 0)	commit(conn);  
		else			rollback(conn);
		return result;
	}
	
	// 전체출력을 위한 멤버선택 서비스단
	public int selectMemNum(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		int selectMN = dao.selectMemNum(conn, memberNo);
		close(conn);
		
		return selectMN;
	
	}

	// 전체출력을 위한 전원 호출 서비스단
	public List<Todo> selectAll(int memberNo) throws Exception {
	    Connection conn = getConnection();
	    List<Todo> todoList = dao.selectAll(conn, memberNo);
	    close(conn);
	    return todoList;
	}

	// 컴플릿 삭제를 위한 서비스단
	public int deleteAll(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		int result = dao.deleteAll(conn, memberNo);
		if(result > 0)	commit(conn);  
		else			rollback(conn);
		return result;
	}

	// 모든플래그 Y를 위한 서비스단
	public int updateAll(int memberNo, String action) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateAll(conn, memberNo, action);
		if(result > 0) commit(conn);
		else rollback(conn);
		return result;
	}



	public int updateOne( int currentIndex, String action2) throws Exception {
		Connection conn = getConnection();

		 int result = dao.updateOne(conn, currentIndex, action2);
		 
	     if(result > 0) commit(conn);
	     else rollback(conn);
	     
	     return result;
	}



	public int changeContents(int memberNo, int currentIndex, String taskinput) throws Exception {
		Connection conn = getConnection();

		 int result = dao.changeContents(conn, memberNo, currentIndex, taskinput);
		 
	     if(result > 0) commit(conn);
	     else rollback(conn);
	     
	     return result;
	}







}
