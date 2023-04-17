<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>코드네임 Note</title>

    <!-- <link rel="stylesheet" href="../css/main.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <!-- <link rel="stylesheet" href="../css/index.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
    
    <link rel="icon" href="data:,">
</head>
<body>

    <div id="wrap">
        <div class="topwrap">
            <div class="titlesize">
                <div class="hometitle">WelCome</div>
            </div>
        </div>
        <div class="bodywrap">
            <div class="bodycontainer"> <!-- 글씨 나오는 싸이즈(create에선 가운데 틀) -->
                <div class="username">Yun</div>
                <div class="note">Note</div>
            </div>
        </div>
        <div class="bottomwrap">
            <div class="btncontainer">
                <button type="button" class="gobtn"
                onclick="location.href='${pageContext.request.contextPath}/note/note'">Let's Go</button>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


    <!-- main.js 연결 -->
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>