package toypjt1.yun.note.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import toypjt1.yun.note.model.service.NoteService;

@WebServlet("/note/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("path로 싸줌");
		String path = "/WEB-INF/views/note/note.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println(req.getParameter("noteNo"));
		System.out.println(req.getParameter("noteTitle"));
		System.out.println(req.getParameter("noteMemo"));
		
		String noteTitle = req.getParameter("noteTitle");
		String noteMemo = req.getParameter("noteMemo");
		int noteNo = 0;
		noteNo = Integer.valueOf(req.getParameter("noteNo"));
	
		try {
			NoteService service = new NoteService();
			System.out.println("요기까진옴?");
				
			int result = service.updateNote(noteTitle, noteMemo, noteNo);
			System.out.println(result);
			
			new Gson().toJson(result, resp.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("update가즈아");
//		resp.sendRedirect("update"); 시발 이거 때문이였어? 왜?
	}
	
}
