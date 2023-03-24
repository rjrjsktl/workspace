package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.*;
import static edu.kh.community.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.member.model.vo.Member;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberEmail) throws SQLException {

		Member member = null;

		try {
			String sql = prop.getProperty("selectOne");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();

				member.setMemberEmail(rs.getString(1));
				member.setMemberNickname(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberAddress(rs.getString(4));
				member.setEnrollDate(rs.getString(5));
			}

			System.out.println("member::" + member);

		} finally {
			close(rs);
			close(pstmt);

		}

		return member;
	}

	public List<Member> selectAll(Connection conn) throws Exception {

		// 결과 저장용 변수 선언
		List<Member> mbList = new ArrayList<>();

		try {
			Member member = null;
			// SQL 작성하기
			String sql = prop.getProperty("selectAll");

			// Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// SQL 수행 후 결과 (ResultSet) 반환 받기
			rs = pstmt.executeQuery();

			while(rs.next()) {
				member = new Member();
				member.setMemberNo(rs.getInt(1));
				member.setMemberEmail(rs.getString(2));
				member.setMemberNickname(rs.getString(3));

				mbList.add(member);
			}
			System.out.println(mbList);

		} finally {
			// 사용한 JDBC 객체 자원반환
			close(rs);
			close(pstmt);
		}
		// 결과반환
		return mbList;
	}
}