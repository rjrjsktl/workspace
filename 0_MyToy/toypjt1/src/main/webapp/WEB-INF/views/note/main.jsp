<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Main 페이지</title>

            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index_style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main_style.css">
        </head>

        <body>

            <main>
                <div class="content">
                    <form id="form" method="post" name="note-form" onsubmit="return mainValidate()">
                        <!-- title 단 -->
                        <div class="divtitl" id="bigtitl">Notes App</div>
                        <div class="divtitl" id="smalltitl">Take notes and never forget.</div>
                        <!-- input 등 기능 단 -->

                        <input type="text" id="search" name="search" placeholder="Search" maxlength="30"
                            autocomplete="off" required>
                        <select name="sltsearch" id="sltsearch">
                            <option value="edited">Sort by last Edited</option>
                            <option value="created">Sort by recently created</option>
                            <option value="alphabetical">Sort by Alphabetical</option>
                        </select>

                        <!-- Note리스트 추가 되는 단 -->
                        <div id="addbox">
                            <!-- <div class="nttitle">노트제목</div><div class="nttime">노트시간</div> -->
                        </div>
                        <!-- create버튼 -->
                        <button type="button" id="createbtn"
                            onclick="location.href='${pageContext.request.contextPath}/note/create'">Create
                            Note</button>
                    </form>

                </div>

            </main>





            <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


            <!-- main.js 연결 -->
            <script src="${contextPath}/resources/js/main.js"></script>

        </body>

        </html>