package edu.kh.community.member.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ** 로그아웃
		// session scope에 세팅 된 회원 정보를 없앰

		// session 얻어오기

		HttpSession session = req.getSession();

		// 1) session에서 회원 정보만 없앰
		/*
		 * 
		 * session.removeAttribute("loginMember");
		 */

		// 2) session 전체를 없애고 새로운 session 만들기(더 많이쓰는 방법 )

		session.invalidate(); // 세션 무효화 >> 현재sessino을 없앰 >> 자동으로 새로운 sessino이 생성 된다.


		// 메인페이지로 돌아가기
		resp.sendRedirect( req.getContextPath() );


	}
}
