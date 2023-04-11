package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/telDupCheck")
public class TelDupCheckServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 비동기 통신으로 전달 된 파라미터 ( data 속성의 key값 ) 얻어오기
				String memberTel = req.getParameter("memberTel");

				try {

					// 이메일 중복 검사 서비스 호출 후 결과 반환 받기
					MemberService service = new MemberService();
					
					int result = service.memberTel(memberTel);
					
					// 동기식 코드 작성 시 
					// forward 또는 redirect를 이용해서 새로운 페이지가 보이게 동작함
					
					// ** 비동기 통신 시 응답은 화면이 아닌 데이터 
					// -> 응답용 스트림을 이용해서 단순 데이터 전달만 하면 된다.
					resp.getWriter().print(result);
					// 응답용 스트림을 이용해서 result를 출력한다.
					

				}catch(Exception e) {
					e.printStackTrace();
				}
	
	}
	
}
