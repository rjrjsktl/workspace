// 전체출력 완벽하게 나오는 서블릿입니다 건드리지마세요


package edu.kh.community.todo.model.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.kh.community.member.model.vo.Member;
import edu.kh.community.todo.model.service.TodoService;
import edu.kh.community.todo.model.vo.Todo;

@WebServlet("/todo/memberTodo")
public class TodoAllServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/todo/todo.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    Member loginMember = (Member)session.getAttribute("loginMember");
	
	    int memberNo = loginMember.getMemberNo();

	    System.out.println(memberNo);
	    try {
	    	
	        TodoService service = new TodoService();
	        List<Todo> todoList = service.selectAll(memberNo);
	        
	        

		    System.out.println(todoList);
	        
	        Gson gson = new Gson();
	        String json = gson.toJson(todoList);

	        resp.getWriter().write(json);

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	}
	
}
