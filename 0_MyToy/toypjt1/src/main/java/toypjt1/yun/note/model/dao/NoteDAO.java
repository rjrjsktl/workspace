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
				//note.setNoteMemo(rs.getString(3));
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

}