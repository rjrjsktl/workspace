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

@WebServlet("/member/myPage/secession")
public class MyPageSecessionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/myPage-secession.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberPw = req.getParameter("memberPw");
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();
		
		try {
			MemberService service = new MemberService();
			String savedPw = service.selectMemberPassword(memberNo);
			
			if (memberPw.equals(savedPw)) {
				int result = service.exitMember(memberNo);
				
				if (result > 0) {
					session.setAttribute("message", "이용해줘서 감사링");
				} else {
					session.setAttribute("message", "에러 발생");
				}
			} else {
				session.setAttribute("message", "비번이 잘못됨 다시 입력 ㄱㄱ");
				resp.sendRedirect("secession");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath());
		session.removeAttribute("loginMember");
		
	}
}
