package edu.kh.community.member.model.dao;
import static edu.kh.community.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
         
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   public Member selectOne(Connection conn, String memberEmail) throws SQLException {
      
      Member member = null;
      
      try {
         String sql = prop.getProperty("selectOne");
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, sql);
         
         rs = pstmt.executeQuery();
         if(rs.next()) {
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

}