package edu.kh.community.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import static edu.kh.community.common.JDBCTemplate.*;

import edu.kh.community.member.model.vo.Member;

public class MemberDAO {

	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Properties prop;



	public MemberDAO() {

		try {

			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();

			prop.loadFromXML( new FileInputStream(filePath) );

		}catch(Exception e){
			e.printStackTrace();
		}

	}


	public Member selectOne(Connection conn, String memberNickname) throws Exception {

		Member member = null;

		try {

			String sql = prop.getProperty("selectOne");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberNickname);

			rs = pstmt.executeQuery();

			if( rs.next() ) {
				member = new Member();

				member.setMemberEmail(rs.getString(1));
				member.setMemberNickname(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberAddress(rs.getString(4));
				member.setEnrollDate(rs.getString(5));

			}

		}finally {

			close(rs);
			close(pstmt);

		}


		return member;
	}


	public List<Member> selectAll(Connection conn) throws SQLException {

		List<Member> memberList = new ArrayList<>();
		Member member = null;

		try {

			String sql = prop.getProperty("selectAll");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				member = new Member();

				member.setMemberNo(rs.getInt(1));
				member.setMemberEmail(rs.getString(2));
				member.setMemberNickname(rs.getString(3));


				memberList.add(member);

			}

		}finally {
			//사용한 객체 자원 반환

			close(rs);
			close(stmt);

		}


		return memberList;
	}


	public Member login(Connection conn, Member mem) throws Exception {

		Member loginMember = null;

		try {
			// get SQL
			String sql = prop.getProperty("login");

			// pstmt 생성 및 sql 적재하기
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());

			// SQL 수행
			rs = pstmt.executeQuery();

			if( rs.next() ) {
				loginMember = new Member();

				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
				loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
				loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
				loginMember.setEnrollDate(rs.getString("ENROLL_DT"));	

			}


		}finally {

			close(rs);
			close(pstmt);

		}

		return loginMember;

	}




	public int emailDupCheck(Connection conn, String memberEmail) throws Exception{

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

			if( rs.next() ) {
				result = rs.getInt(1);
			}


		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	/** 인증번호, 발급일 수정 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
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

	/** 인증번호 생성 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
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


	public int checkNumber(Connection conn, String cNumber, String inputEmail) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("checkNumber");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputEmail);

			pstmt.setString(2, cNumber);

			pstmt.setString(3, inputEmail);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}


		} finally {
			close(rs);
			close(pstmt);
		}


		return result;

	}


	public int signUp(Connection conn, Member mem) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("signUp");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberNickname());
			pstmt.setString(4, mem.getMemberTel());
			pstmt.setString(5, mem.getMemberAddress());

			result = pstmt.executeUpdate();

		}finally {
			
			close(pstmt);
			
		}


		// 결과 반환
		return result;
	}


	public int memberNickname(Connection conn, String memberNickname) throws Exception {

		// 결과 저장용 변수 선언
		int result = 0;

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("nicknameDupCheck");

			pstmt = conn.prepareStatement(sql);

			// 위치홀더 알맞은 값 세팅
			pstmt.setString(1, memberNickname);

			// SQL 수행 후 결과 반환받기

			rs = pstmt.executeQuery();

			if( rs.next() ) {
				result = rs.getInt(1);
			}


		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}


	public int memberTel(Connection conn, String memberTel) throws Exception {
		// 결과 저장용 변수 선언
		int result = 0;

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("telDupCheck");

			pstmt = conn.prepareStatement(sql);

			// 위치홀더 알맞은 값 세팅
			pstmt.setString(1, memberTel);

			// SQL 수행 후 결과 반환받기

			rs = pstmt.executeQuery();

			if( rs.next() ) {
				result = rs.getInt(1);
			}


		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	public int updateProfileImage(Connection conn, int memberNo, String profileImage) throws Exception{
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
	


	
	public String selectMemberPassword(Connection conn, int memberNo) throws Exception {
		String sql = prop.getProperty("selectMemberPassword");
		String savedPw = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				savedPw = rs.getString("MEMBER_PW");
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return savedPw;
	}

	public int updateMemberPassword(Connection conn, int memberNo, String newPassword) throws Exception {
		String sql = prop.getProperty("updateMemberPassword");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int exitMember(Connection conn, int memberNo ) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("exitMember");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,memberNo);

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}


	public int updateInfomation(Connection conn, int memberNo, String memberNickname, String memberTel,
			String memberAddress) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("updateInfomation");

			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, memberNickname);
			pstmt.setString(2, memberTel);
			pstmt.setString(3, memberAddress);
			pstmt.setInt(4, memberNo);
			
			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}
	


	
		
	

}


