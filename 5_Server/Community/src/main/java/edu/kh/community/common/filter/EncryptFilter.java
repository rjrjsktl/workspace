package edu.kh.community.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import edu.kh.community.common.wrapper.EncryptWrapper;

// 암호화를 적용 해야하는 요청 : 로그인, 회원가입, 비밀번호 변경, 회원 탈퇴

// 필터가 적용될 url이 여러개인 경우 : String 배열 초기화 형태 {} 로 작성

@WebFilter(filterName="encryptFilter", 
			urlPatterns={
						"/member/login",
						"/member/signUp",
						"/member/myPage/changePw",
						"/member/myPage/secession"
						})
public class EncryptFilter extends HttpFilter implements Filter {
       
	public void init(FilterConfig fConfig) throws ServletException {}

	public void destroy() {}
		// 위 두개는 넣는게 없어도 피터 클레스에는 무조건 필요함
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// 비밀번호는 피라미터 -> HttpServletRequest에 담겨 있다.
		
		// doFilter 메서드 매개변수는 부모타입인 ServletRequest
		// -> 다운 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		
		// 파라미터를 다시 세팅하는 방법은 필터에서 불가능함!
		// -> Servlet의 Wrapper 클래스를 이용해서
		// HttpServletRequest의 메서드를 오버라이딩 할 수 있다.
		// -> 오버라이딩(재정의)를 통해서 비밀번호 암호화 진행.
		
		EncryptWrapper wrapper = new EncryptWrapper(req);
		
		
		
		chain.doFilter(wrapper, response);
	}

	
}
