package toypjt1.yun.note.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import toypjt1.yun.note.model.service.NoteService;
import toypjt1.yun.note.model.vo.Note;

@WebServlet("/note/done")
public class DoneServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/note/main.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 노트 제목과 내용 가져오기
		String noteTitle = req.getParameter("noteTitle");
		String noteMemo = req.getParameter("noteMemo");
				
		try {
			NoteService service = new NoteService();
			
			int result = service.doneBtn(noteTitle, noteMemo);
			System.out.println(result);
			
			new Gson().toJson(result, resp.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
