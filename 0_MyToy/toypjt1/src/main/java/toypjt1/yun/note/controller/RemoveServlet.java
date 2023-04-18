package toypjt1.yun.note.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/note/remove")
public class RemoveServlet extends HttpServlet{

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("path로 싸줌");
		String path = "/WEB-INF/views/note/note.jsp";
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
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("remove");
		session.removeAttribute("loginMember");
		
		
		
	}
}
