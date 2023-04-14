package toypjt1.yun.note.model.service;

import java.sql.Connection;
import java.util.List;

import static toypjt1.yun.note.JDBCTemplate.*;
import toypjt1.yun.note.model.dao.NoteDAO;
import toypjt1.yun.note.model.vo.Note;

public class NoteService {
	
	private NoteDAO dao = new NoteDAO();

//	public List<Note> noteAll() throws Exception {
//		
//		Connection conn = getConnection();
//		
//		List<Note> ntlist = dao.noteAll(conn);
//		
//		return ntlist;
//	}

	public List<Note> viewNote() throws Exception {

		Connection conn = getConnection();
		
		List<Note> ntlist = dao.viewNote(conn);
		
		return ntlist;
	}

	public int doneBtn(String noteTitle, String noteMemo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.doneBtn(conn, noteTitle, noteMemo);
		
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
}