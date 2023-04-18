package toypjt1.yun.note.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import toypjt1.yun.note.model.service.NoteService;
import toypjt1.yun.note.model.vo.Note;

@WebServlet("/note/remove")
public class RemoveServlet extends HttpServlet{

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("path로 싸줌");
		String path = "/WEB-INF/views/note/note.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		System.out.println("note페이지로 이동???뭔데");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(req.getParameter("noteNo"));
		int noteNo = 0;
		noteNo = Integer.valueOf(req.getParameter("noteNo"));
		try {
			NoteService service = new NoteService();
				
			int result = service.removeNote(noteNo);
			System.out.println(result);
			
			new Gson().toJson(result, resp.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("remove가즈아");
//		resp.sendRedirect("remove"); 시발 이거 때문이였어? 왜?
	}
}
