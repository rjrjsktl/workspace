package toypjt1.yun.note.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import toypjt1.yun.note.model.service.NoteService;
import toypjt1.yun.note.model.vo.Note;

@WebServlet("/note/search")
public class SearchServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("연결확인");
		// input에 입력한 search값 가져오기
		System.out.println(req.getParameter("searchValue"));
		String searchValue = req.getParameter("searchValue");
	
		try {
			System.out.println("try는?");
			NoteService service = new NoteService();
			List<Note> searchNotelist = service.searchNote(searchValue);
			System.out.println(searchNotelist);
			
			new Gson().toJson(searchNotelist, resp.getWriter());
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	
	}
}
