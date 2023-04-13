<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 문자열 관련 함수(메서드) 제공 JSTL (EL형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create 페이지</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/create_style.css">
</head>
<body>
    <main>
        <div class="content">
            <form id="form" method="post" name="note-form" onsubmit="return noteValidate()">
                <!-- title 단 -->
                <div class="divtitl" id="bigtitl">Notes App</div>
                <div class="divtitl" id="smalltitl">Take notes and never forget.</div>
                
                <!-- back 버튼 등 기능 단 -->
                <button type="button" id="backbtn" value="back" onclick="window.history.back()">&lt;back</button>
                <span class="nowtm" id="nowtm">time</span>
                <!-- Note리스트 추가 되는 단 -->
                <br><input type="text" id="nttitle" name="nttitle" placeholder="Note Title" maxlength="30" autocomplete="off" required> 
                <br>
                <textarea type="text" id="nttext" name="nttext" placeholder="Enter Note Text" style="resize: none;" maxlength="1000" autocomplete="off" required></textarea>
                <!--remove note 버튼 아마 onclick 바꿔야 할거임-->
                <button type="button" id="removebtn" onclick="clickRemoveBtn()">Remove Note</button>
                <!-- done 버튼 -->
                <button type="button" id="donebtn" onclick="clickDoneBtn()">Done</button>
            </form>
        </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- main.js 연결 -->
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>







</body>
</html>