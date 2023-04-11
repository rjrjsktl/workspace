package edu.kh.community.todo.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.todo.model.service.TodoService;

@WebServlet("/todo/updateOne")
public class TodoFlagOneServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/todo/todo.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String currentIndexParam = req.getParameter("data_todonum");
		int currentIndex = Integer.parseInt(currentIndexParam);
		
		String action2 = req.getParameter("action2");
		
		
		try {
			System.out.println(currentIndex); 
			System.out.println(action2);
			

		    TodoService service = new TodoService();

		    int result = service.updateOne( currentIndex, action2);
		    
		    if (result > 0) {
		        session.setAttribute("message", "수정~");
		    } else {
		        session.setAttribute("message", "수정실패");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}


