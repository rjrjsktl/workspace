<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Note 페이지</title>

    <!-- <link rel="stylesheet" href="../css/main.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <!-- <link rel="stylesheet" href="../css/note.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/note.css">
    
</head>
<body>

    <!-- 이거는 맨 마지막에 해야함 왜냐면 움직이면서 추가 기능 필요 -->



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
                    <input type="text" class="search" name="search" placeholder="Search" maxlength="30"
                            autocomplete="off" required>
                    <select name="sltsearch" class="sltsearch">
                        <option value="edited">Sort by last Edited</option>
                        <option value="created">Sort by recently created</option>
                        <option value="alphabetical">Sort by Alphabetical</option>
                    </select>
                </div>
                    <!-- Note리스트 추가 되는 단 -->
                <div class="bodybottom">
                    <div id="addbox">
                        <div class="addform">
                            No Result
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottomwrap">
            <div class="btncontainer">
                <button type="button" id="createbtn" onclick="location.href='${pageContext.request.contextPath}/note/create'">Create</button>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- main.js 연결 -->
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    
</body>
</html>