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

	/**
	 * @param conn
	 * @return
	 * @throws Exception
	 */
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

			while (rs.next()) {
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

	/**
	 * 로그인 DAO
	 * 
	 * @param conn
	 * @param mem
	 * @return
	 */
	public Member login(Connection conn, Member mem) throws Exception {

		// 결과 저장용 변수 선언 (제일 먼저 해야 함)
		Member loginMember = null;

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("login");

			// PreparedStatment 생성 및 SQL 적재
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());

			// SQL 수행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				loginMember = new Member();

				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
				loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
				loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
				loginMember.setEnrollDate(rs.getString("ENROLL_DT"));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return loginMember; // null 또는 Member 객체 주소

	}

	/**
	 * 이메일 중복검사 DAO
	 * 
	 * @param conn
	 * @param memberEmail
	 * @return
	 */
	public int emailDupCheck(Connection conn, String memberEmail) throws Exception {

		// 결과 저장용 변수 선언
		int result = 0;

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("emailDupCheck");

			// pstmt 생성
			pstmt = conn.prepareStatement(sql);

			// 위치홀더 알맞은 값 세팅
			pstmt.setString(1, memberEmail);

			// SQL 수행 후 결과 반환받기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	/**
	 * 인증번호, 발급일 수정 DAO
	 * 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("updateCertification");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, cNumber);
			pstmt.setString(2, inputEmail);

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 인증번호 생성 DAO
	 * 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("insertCertification");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 인증번호 확인 DAO
	 * 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return result
	 */
	public int checkNumber(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("checkNumber");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			pstmt.setString(3, inputEmail);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	/**
	 * 닉네임 중복 확인 DAO
	 * 
	 * @param conn
	 * @param memberNickname
	 * @return result
	 */
	public int nickDupCheck(Connection conn, String memberNickname) throws Exception {

		// 결과 저장용 변수 선언
		int result = 0;

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("nickDupCheck");

			// pstmt 생성
			pstmt = conn.prepareStatement(sql);

			// 위치홀더 알맞은 값 세팅
			pstmt.setString(1, memberNickname);

			// SQL 수행 후 결과 반환받기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	/**
	 * 회원가입 DAO
	 * 
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member mem) throws Exception {

		int result = 0; // 결과 저장용 변수

		try {
			String sql = prop.getProperty("signUp");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberNickname());

			pstmt.setString(4, mem.getMemberTel());
			pstmt.setString(5, mem.getMemberAddress());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		// 결과 반환
		return result;
	}

	/**
	 * 프로필 이미지 변경 DAO
	 * 
	 * @param conn
	 * @param memberNo
	 * @param profileImage
	 * @return result
	 */
	public int updateProfileImage(Connection conn, int memberNo, String profileImage) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("updateProfileImage");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profileImage);
			pstmt.setInt(2, memberNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}

	/** info DAO
	 * @param conn
	 * @param memberNickname
	 * @param memberTel
	 * @return result
	 */
	public int updateInfo(Connection conn, String memberNickname, String memberTel, int memberNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateInfo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);
			pstmt.setString(2, memberTel);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
	
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePw(Connection conn, String newPw, int memberNo, String oldPw) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, oldPw);
			
			result = pstmt.executeUpdate();
	
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	

}