package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
////		
////		String id = req.getParameter("inputId");
////		String pw = req.getParameter("inputPw");
////		String cp = req.getParameter("inputPw");
////		String nm = req.getParameter("Name");
////		String em = req.getParameter("Email");
////		String[] hb = req.getParameterValues("hobby");
////		
//		String result = null;
//		
//		if( id.equals("test") && pw.equals("1234") && cp.equals("1234") ) {
//			result = nm + "의 회원가입이 완료되었습니다.";
//		} else {
//			result = "아이디 또는 비밀번호가 잘못되었습니다.";
//		}
//		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/signupResult.jsp");
		
//		req.setAttribute("r", result);
		
		dispatcher.forward(req, resp);
		
		
	}
}
