/// 완료 된 인서트 서블릿입니다 건드리지마세요






package edu.kh.community.todo.model.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.vo.Member;
import edu.kh.community.todo.model.service.TodoService;

import static edu.kh.community.common.JDBCTemplate.*;

@WebServlet("/todo/todo")
public class TDLinsertServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/todo/todo.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TodoService service = new TodoService();

		String add = req.getParameter("addInput");
		String currentIndexParam = req.getParameter("currentIndex");
		int currentIndex = Integer.parseInt(currentIndexParam);
		
		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();
		
	   
		try {
			
			int result = service.insertTodo(memberNo, add, currentIndex);
			if (result > 0) {
				session.setAttribute("agree", "Good");
			} else {
				session.setAttribute("message", "input에 실패메시지.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
