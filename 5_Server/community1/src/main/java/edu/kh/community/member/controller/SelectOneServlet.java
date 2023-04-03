package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/selectOne")
public class SelectOneServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String memberNickname = req.getParameter("memberNickname");
	
	try {
		MemberService service = new MemberService();
		
		Member member = service.selectOne(memberNickname);
		
		// java 객체를 js 객체로 변환해서 응답 ( 출력 )
		// java 객체 --> JS형태 문자열인 JSON --> JS객체로 변환
		
		// 1) JSON을 직접 작성 ( 오타 위험성 높음 )
		
		// 2) JSON-simple 라이브러리 JSONObject 사용해서 할수있음 ( 사용방법이 복잡함 )
		
		// 3) GSON 라이브러리를 이용한 JAVA 객체 > Json 변환
		// new Gson().toJson ( 객체, 응답스트림 );
		// >> 매개변수에 작성 된 객체를 JSON형태로 변환한 후 스트림 통해서 출력
		
		new Gson().toJson(member, resp.getWriter());
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
	
	

}
