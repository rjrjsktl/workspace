package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;


@WebServlet("/member/login")

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달 된 파라미터 변수에 저장
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");

		System.out.println("inputEmail : " + inputEmail);
		System.out.println("inputPw : " + inputPw );

		Member mem = new Member();
		mem.setMemberEmail(inputEmail);
		mem.setMemberPw(inputPw);

		try {

			//서비스 객체 생성
			MemberService service = new MemberService();

			Member loginMember = service.login(mem);

			// ** 로그인 **
			// ID/PW가 일치하는 회원 정보를 Session scope에 세팅하는 것

			// Session 객체 얻어오기
			HttpSession session = req.getSession();

			if(loginMember != null) { // 성공

				// 회원정보를 session에 셋팅
				session.setAttribute("loginMember", loginMember);
				session.setAttribute("message", "어서와");

				session.setMaxInactiveInterval(3600);

				// 아이디 정보 저장 ( cookie )
				// =========================================================

				/* Cookie : 클라이언트(브라우저)에서 관리하는 파일
				 * 
				 * - 특정 주소 요청 시 마다
				 *  해당 주소와 연관 된 쿠키 파일을 브라우저가 알아서 읽어옴
				 * 읽어 온 쿠키파일 내용을 서버에 같이 전달
				 * 
				 * 생성 및 사용방법 
				 * 
				 * 1) 서버가 요청에 대한 응답을 할 때
				 *    쿠키를 생성한 후 응답에 쿠키를 담아서 클라이언트에게 전달
				 *    
				 * 2) 응답에 담긴 쿠키가 클라이언트에 파일형태로 저장
				 * 
				 * 3) 이후 특정 주소 요청 시 쿠키 파일을 브라우저가 찾아 자동으로
				 *    요청에 실어서 보냄
				 *    
				 * 4) 서버는 요청에 실려온 쿠키 파일을 사용한다.
				 *    
				 * 
				 */

				// Cookies 객체 생성

				Cookie c = new Cookie("saveId", inputEmail);

				// 아이디 저장이 체크된 경우

				if (req.getParameter("saveId") != null) {

					// 쿠키 파일을 30일 동안 유지
					c.setMaxAge(60 * 60 * 24 * 30); 

				} else { // 체크안했을 때 + ( 체크된 것을 해지했을때 )
					// 쿠키 파일을 0초동안 유지
					// 기존의 존재하던 쿠키 파일에 유지 시간을 0초로 덮어씌움
					// >> 삭제
					c.setMaxAge(0); 
				}

				// 해당 쿠키파일이 적용 될 주소를 지정
				c.setPath(req.getContextPath());
				// 최상의 주소 : /community

				// 응답 객체를 이용해서 클라이언트로 전달 
				 resp.addCookie(c);


			}else { // 실패
				session.setAttribute("message", "아이디 또는 비밀번호 불일치");

			}

			//forward 는 새로운 페이지로 이동하지만 ( 요청위임 )
			/*
			 * 1. forward ( 요청위임 )
			 * >> Servlet으로 응답화면 만들기가 불편해서
			 * >> JSP로 req,resp 객체 위임하여
			 * >> 요청에 대한 응답화면 대신 만든다.
			 * 	  ex) 주문(클라이언트) >> 주문받음(서블릿) >> 무엇인가만듬(JSP)
			 *  
			 */
			
			
			// 로그인의 경우 화면이 바뀌거나 하지않기때문에
			// redirect를 사용한다. ( 재요청 )
			
			
			/*			 
			 * * 2. redirect ( 재요청 )
			 * >> 현재 Servlet에서 응답 페이지를 만들지 않고
			 * >> 응답 페이지를 만들 수 있는
			 * >> 다른 요청의 주소로 클라이언트를 이동시킴(재요청)
			 * >> login서블릿 >> login서블릿 ( redirect )
			 * httpservlet request, response를 받은 후의 사이트와 같을까?
			 * >> 다름
			 * 
			 * CGV 카페
			 * ex) 팝콘주세요(클라이언트) > 위치가르쳐줌(서블릿) > 클라이언트가 팝콘 파는곳으로 이동 ( 다른주소 재요청) 
			 * 
			 */
			
			/* >> 클라이언트 재요청
			 *  -> 기존 HttpServletRequest/Response 제거
			 *  -> 새로운 HttpServletRequest/Response 생성
			 *  // 유지를하고싶으면 세팅을 따로해야한다.
			 *  // 로그인전의 req와 후의 req가 다르기때문
			 */
			
			// --> 리다이렉트시 Request 객체가 유지되지 않기 때문에
			// 특정 데이터를 전달하고나 유지하고싶으면
			// session 또는 application 범위에 세팅해야한다!
			
			resp.sendRedirect( req.getContextPath() );
			// dispatcher를 사용하면 꼬인다. ( forward 사용xxx )

		}catch(Exception e) {
			e.printStackTrace();
		}	

	}

}








