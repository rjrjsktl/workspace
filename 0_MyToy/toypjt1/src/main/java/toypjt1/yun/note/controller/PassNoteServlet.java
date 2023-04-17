package toypjt1.yun.note.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import toypjt1.yun.note.model.service.NoteService;
import toypjt1.yun.note.model.vo.Note;

@WebServlet("/note/pass")
public class PassNoteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/note/create.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String noteTitle = req.getParameter("noteTitle");
		String noteMemo = req.getParameter("noteMemo");
		String noteDate = req.getParameter("noteDate");
		
		HttpSession session = req.getSession();
		
		Note clickNote = (Note)session.getAttribute("clickNote");
		int noteNo = clickNote.getNoteNo();
	
		try {
			NoteService service = new NoteService();
			
			int result = service.passNote(noteNo);
			
			if(result > 0) {
				clickNote.setNoteTitle(noteTitle);
				clickNote.setNoteMemo(noteMemo);
				clickNote.setNoteDate(noteDate);
				
				session.setAttribute("clickNote", clickNote);
			}
			resp.sendRedirect("create");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
//		session.removeAttribute("clickNote");
	}
}
