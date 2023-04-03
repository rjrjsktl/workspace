package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/nickCheck")
public class NickCheckServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memberNickname = req.getParameter("memberNickname");
		
		try {
			MemberService service = new MemberService();
			
			int result = service.nickDupCheck(memberNickname);
			
			resp.getWriter().print(result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
