<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>

<body>
	<header>
		<section>

			<%-- 
			<%= request/getContextPath()
			${pageContext.servletContext.contextPath}
		 --%>
			<!-- 클릭 시 메인페이지로 이동하는 로고 -->
			<a href="${contextPath}">
				<img src="${contextPath}/resources/images/logo.jpg" id="home-logo">
				<%--  <img src="resources/images/logo.jpg" id="home-logo"> --%>
			</a>
		</section>

		<!-- 검색창 영역 -->
		<section>

			<article class="search-area">
				<!-- 내부 input 태그의 값을 서버 또는 페이지로 전달(제출) -->
				<form action="#">
					<fieldset>
						<input type="text" id="query" name="query" placeholder="검색어를 입력해주세요.">

						<!-- 검색 버튼 -->
						<button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>
					</fieldset>
				</form>
			</article>

		</section>



		<section></section>
	</header>


	<nav>
		<ul>
			<li><a href="#">공지사항</a></li>
			<li><a href="#">자유 게시판</a></li>
			<li><a href="#">질문 게시판</a></li>
			<li><a href="#">FAQ</a></li>
			<li><a href="#">1:1문의</a></li>
		</ul>
	</nav>

</body>

</html>