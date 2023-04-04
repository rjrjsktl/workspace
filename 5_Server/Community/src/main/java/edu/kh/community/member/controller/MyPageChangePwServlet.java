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

@WebServlet("/member/myPage/changePw")
public class MyPageChangePwServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/myPage-changePw.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String oldPw = req.getParameter("currentPw");
		String newPw = req.getParameter("newPw");

		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();

		try {
			MemberService service = new MemberService();
			String savedPw = service.selectMemberPassword(memberNo);

			if (oldPw.equals(savedPw)) {

				int result = service.updatePw(newPw, memberNo);

				if (result > 0) {

					session.setAttribute("message", "비밀번호 수정 성공");

				} else {
					session.setAttribute("message", "비밀번호 수정 실패");
				}
			} else {
				session.setAttribute("message", "현재 비밀번호가 올바르지 않습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("changePw");
	}
}
