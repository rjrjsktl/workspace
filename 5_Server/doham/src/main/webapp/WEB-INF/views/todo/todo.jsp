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
    <title>ToDoList</title>
     <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
     <link rel="stylesheet" href="${contextPath}/resources/css/16_layout.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
  
</head>

<body>
     <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <h1>ToDoList</h1>
    <form action="#">
        <section class="todo-input-section">
            <span id="allcheck" class="fa-solid fa-check"></span>
            <input type="text" placeholder="What needs to be done?" id="add">
        </section>
        <div id="addBox"></div>
        <div id="userselect">
            <span id="itemsLeft" style="color:#4d4d4d;"><span id="items">0</span> items left</span>
            <ul>
                <li id="filterAll">All</a></li>
                <li id="filterActive">Active</a></li>
                <li id="filterComplete">Completed</a></li>
            </ul>
            <span id="clear" >Clear Completed</span>
        </div>
         </form>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        
 
   


    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
        <!-- myPage.js 추가 -->
    <script src="${contextPath}/resources/js/todo/16_ToDoList.js"></script>
    
    
    
    
    
</body>
</html>



