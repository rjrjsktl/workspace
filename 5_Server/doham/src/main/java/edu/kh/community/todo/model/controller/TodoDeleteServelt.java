// 완성 된 DELETE 서블릿입니다 건드리지마세요




package edu.kh.community.todo.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import edu.kh.community.member.model.vo.Member;
import edu.kh.community.todo.model.service.TodoService;

@WebServlet("/todo/deleteAll")
public class TodoDeleteServelt extends HttpServlet {

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

		try {

			TodoService service = new TodoService();
			int result = service.deleteAll(memberNo);

			if ( result > 0 ) {
				System.out.println(result);
				session.setAttribute("aa", "aa");
			} else {
				session.setAttribute("message", "삭제실패");
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
		

		
	}
}


