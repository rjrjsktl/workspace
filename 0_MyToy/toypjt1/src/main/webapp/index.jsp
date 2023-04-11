<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>코드네임 Note</title>

            <link rel="stylesheet" href="resources/css/index_style.css">
        </head>

        <body>

            <main>
                <div class="content">
                    <form id="form" method="post" name="note-form" onsubmit="return homeValidate()">
                        

                        <div class="hometitle" id="hometitle">Welcom Yun Note</div>

                        <button type="button" id="gobtn" 
                        onclick="location.href='${pageContext.request.contextPath}/note/main'">Note 작성하로 고고</button>
        
                    </form>

                </div>

            </main>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


            <!-- main.js 연결 -->
            <script src="${contextPath}/resources/js/main.js"></script>

        </body>

        </html>