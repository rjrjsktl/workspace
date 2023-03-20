<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
</head>
<body>
	<%
		String id = request.getParameter("inputId");
		// String pw = request.getParameter("inputPw");
		// String cp = request.getParameter("inputPw");
		String nm = request.getParameter("Name");
		String em = request.getParameter("Email");
		
		String[] pw = request.getParameterValues("inputPw");
		String[] hb = request.getParameterValues("hobby");
	%>
	
	<% if( !pw[0].equals(pw[1]) ) {%>
		<h3>비밀번호가 일치하지 않습니다.</h3>
	<% } else {%>
		<ul>
			<li>아이디 : <%=request.getParameter("inputId")%></li>
			<li>비밀번호 : <%=request.getParameter("inputPw")%></li>
			<li>이름 : <%=request.getParameter("Name")%></li>
			<li>이메일 : <%=request.getParameter("Email")%></li>
			<li>취미 : 
				<% for(String h : hb) {%>
					<%= h %>
				<% } %>			
			</li>
		</ul>
		<h3 style='color : blue;'> <%= nm %>의 회원가입이 완료되었습니다.</h3>
	<% } %>
	<button type='button' onclick='history.back()'>돌아가기</button>






</body>
</html>