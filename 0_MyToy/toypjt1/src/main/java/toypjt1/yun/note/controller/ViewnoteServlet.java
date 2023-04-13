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

@WebServlet("viewnote")
public class ViewnoteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			NoteService service = new NoteService();
			List<Note> ntlist = service.viewNote();
			
			new Gson().toJson(ntlist, resp.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	
	
	}
}
