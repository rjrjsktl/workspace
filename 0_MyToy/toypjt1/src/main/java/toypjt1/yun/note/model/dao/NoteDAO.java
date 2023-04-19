package toypjt1.yun.note.model.dao;

import static toypjt1.yun.note.JDBCTemplate.*;
import static toypjt1.yun.note.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import toypjt1.yun.note.model.vo.Note;

public class NoteDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public NoteDAO() {
		try {
			prop = new Properties();

			String filePath = NoteDAO.class.getResource("/toypjt1/yun/note/sql/Note-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public List<Note> noteAll(Connection conn) throws Exception{
//
//		List<Note> ntlist = new ArrayList<>();
//		
//		try {
//			Note note = null;
//			String sql = prop.getProperty("noteAll");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				note = new Note();
//				
//				note.setNoteNo(rs.getInt(1));
//				note.setNoteTitle(rs.getString(2));
//				note.setNoteMemo(rs.getString(3));
//				note.setNoteDate(rs.getString(4));
//				
//				ntlist.add(note);
//			}
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		return ntlist;
//	}

	/** viewnote 단
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Note> viewNote(Connection conn) throws Exception {

		List<Note> ntlist = new ArrayList<>();
		
		try {
			Note note = null;
			String sql = prop.getProperty("viewNote");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				note = new Note();
				
				note.setNoteNo(rs.getInt(1));
				note.setNoteTitle(rs.getString(2));
				note.setNoteDate(rs.getString(3));
				
				ntlist.add(note);
			}
			System.out.println(ntlist);
		} finally {
			close(rs);
			close(pstmt);
		}
		return ntlist;
		}

	/** dontBtn 버튼 누를 시
	 * @param conn
	 * @param noteTitle
	 * @param noteMemo
	 * @return
	 * @throws Exception
	 */
	public int doneBtn(Connection conn, String noteTitle, String noteMemo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("doneBtn");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, noteTitle);
			pstmt.setString(2, noteMemo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 메모 누를 시 create에 note 전달
	 * @param conn
	 * @param noteNo
	 * @return
	 */
	public Note passNote(Connection conn, int noteNo) throws Exception {
//		List<Note> passNote = new ArrayList<>();
		Note note = new Note(); // 영식이방법
		try {
//			Note note = null;
			String sql = prop.getProperty("passNote");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noteNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
//				note = new Note();
				
				note.setNoteTitle(rs.getString(1));
				note.setNoteMemo(rs.getString(2));
				note.setNoteDate(rs.getString(3));
				note.setNoteNo(rs.getInt(4));
				note.setNoteSession(rs.getString(5));
				
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return note;
	}

	/** remove 버튼 누를시
	 * @param conn
	 * @param noteNo
	 * @return
	 * @throws Exception
	 */
	public int removeNote(Connection conn, int noteNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("removeNote");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noteNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateNote(Connection conn, String noteTitle, String noteMemo, int noteNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateNote");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, noteTitle);
			pstmt.setString(2, noteMemo);
			pstmt.setInt(3, noteNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Note> searchNote(Connection conn, String searchValue) throws Exception {
		List<Note> searchNotelist = new ArrayList<>();
		System.out.println("dao는?");
		try {
			String sql = prop.getProperty("search");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+searchValue+"%");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				
				note.setNoteTitle(rs.getString("NOTE_TITLE"));
				note.setNoteMemo(rs.getString("NOTE_MEMO"));
				note.setNoteDate(rs.getString("ENROLL_DT"));
				note.setNoteNo(rs.getInt("NOTE_NO"));
				note.setNoteSession(rs.getString("SECESSION_EX"));
				searchNotelist.add(note);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return searchNotelist;
	}
}
