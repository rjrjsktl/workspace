package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/info")

public class MyPageInfoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/myPage-info.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String memberNickname = req.getParameter("memberNickname");
		String memberTel = req.getParameter("memberTel");
		String[] address = req.getParameterValues("memberAddress");	

		String memberAddress = null;
		if(!address[0].equals("")) { 
			memberAddress = String.join(",,", address);	}

		try {
			HttpSession session = req.getSession();
			Member loginMember =  (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();

			MemberService service = new MemberService();

			int result = service.updateInfomation(memberNo, memberNickname, memberTel, memberAddress);

			if ( result > 0) {
				session.setAttribute("message", "회원정보가 수정 되었습니다.");
				
				 	loginMember.setMemberNickname(memberNickname);
				    loginMember.setMemberTel(memberTel);
				    loginMember.setMemberAddress(memberAddress);
				    session.setAttribute("loginMember", loginMember);
				    
			}else {
				session.setAttribute("message", "회원정보 수정 실패.");
			}
			resp.sendRedirect("info"); 	
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}
}
