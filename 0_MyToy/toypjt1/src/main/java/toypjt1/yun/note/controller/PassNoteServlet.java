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
		
		System.out.println("path로 싸줌");
		String path = "/WEB-INF/views/note/update.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String noteTitle = req.getParameter("noteTitle");
//		String noteMemo = req.getParameter("noteMemo");
//		String noteDate = req.getParameter("noteDate");
		
//		System.out.println("윤석이 바보");
//		System.out.println(req.getParameter("noteTitle"));
//		System.out.println(req.getParameter("noteMemo"));
//		System.out.println(req.getParameter("noteDate"));
		System.out.println("연결확인");
		System.out.println(req.getParameter("noteNo"));
		HttpSession session = req.getSession();
		//Note clickNote = new Note();
		/*
		 * >>
		 * 
		 * 이게 필요가 없고
		 */
//		int noteNo = clickNote.getNoteNo();
		int noteNo = 0;
		noteNo = Integer.valueOf(req.getParameter("noteNo"));
//		System.out.println(noteNo);
		/*
		 * if (clickNote != null) { noteNo = clickNote.getNoteNo(); // noteNo 값을 사용하는 코드
		 * } else { // clickNote가 null인 경우에 대한 처리 // 니 코드는 지금 여기를 타고있음 }
		 */
	
		try {
			NoteService service = new NoteService();
			
			Note clickNote = service.passNote(noteNo);
				
			session.setAttribute("clickNote", clickNote);
			System.out.println(clickNote);
			
			
			
			// 이건 맞는거같은데 
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("pass가즈아");
		resp.sendRedirect("pass");
	}
}
