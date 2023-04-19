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

	/** viewnote 할때
	 * @return
	 * @throws Exception
	 */
	public List<Note> viewNote() throws Exception {

		Connection conn = getConnection();
		
		List<Note> ntlist = dao.viewNote(conn);
		
		return ntlist;
	}

	/** doneBtn눌렀을경우
	 * @param noteTitle
	 * @param noteMemo
	 * @return
	 * @throws Exception
	 */
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

//	public List<Note> passNote(int noteNo) throws Exception {
//		Connection conn = getConnection();
//		
//		List<Note> note = null;
//		
//		try {
//			note = dao.passNote(conn, noteNo);
//		} finally {
//			close(conn);
//		}
//		return note;
//	}
	public Note passNote(int noteNo) throws Exception {
		Connection conn = getConnection();
		
		Note result = dao.passNote(conn, noteNo);
		
		close(conn);
		
		return result;
	} //------------------ 영식이가 알려준 방법
	
	

	public int removeNote(int noteNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.removeNote(conn, noteNo);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateNote(String noteTitle, String noteMemo, int noteNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateNote(conn, noteTitle, noteMemo, noteNo);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

//	public List<Note> searchNote(String searchValue) throws Exception{
//		Connection conn = getConnection();
//		List<Note> note = null;
//
//		try {
//			note = dao.searchNote(conn, searchValue);
//		} finally {
//			close(conn);
//		}
//		return note;
//	}
	public List<Note> searchNote(String searchValue) throws Exception {
		Connection conn = getConnection();
		System.out.println("service는?");
		List<Note> searchNotelist = null;
		
		searchNotelist = dao.searchNote(conn, searchValue);
		
		close(conn);
		
		return searchNotelist;
	}
}
