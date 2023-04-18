<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create 페이지</title>

    <!-- <link rel="stylesheet" href="../css/main.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <!-- <link rel="stylesheet" href="../css/create.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/create.css">

    <link rel="icon" href="data:,">
</head>
<body>

    <div id="wrap">
        <div class="topwrap">
            <div class="titlesize">
                <!-- title 단 -->
                <div class="bigtitl">Notes App</div>
                <div class="smalltitl">Take notes and never forget.</div>
            </div>
        </div>
        <div class="bodywrap">
            <div class="bodycontainer"> <!-- 글씨 나오는 싸이즈(create에선 가운데 틀) -->
                <div class="bodytop">
                    <!-- back 버튼 등 기능 단 -->
                    <button type="button" id="backbtn" value="back"
                        onclick="window.history.back()">&lt;back</button>
                    <span>Now Time</span>
                </div>
                    <!-- Note리스트 추가 되는 단 -->
                <div class="bodybottom">
                    <input type="text" id="nttitle" name="nttitle" placeholder="Note Title"
                        maxlength="30" autocomplete="off">
                    <textarea type="text" id="ntmemo" name="ntmemo" placeholder="Enter Note Text" maxlength="1000" autocomplete="off"></textarea>
                </div>
            </div>
        </div>
        <div class="bottomwrap">
            <div class="btncontainer">
                <!-- done 버튼 -->
                <button type="button" id="donebtn" onclick="clickDoneBtn()">Done</button>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- main.js 연결 -->
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>