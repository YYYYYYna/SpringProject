<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<div class="wrapper row1">
  <header id="header" class="clear"> 
   
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">실시간 맛집 추천</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-phone"></i> +00 (123) 456 7890</li>
        <li><i class="fa fa-envelope-o"></i> info@domain.com</li>
      </ul>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear">  
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
       <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호 찾기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/food_list.do">맛집 목록</a></li>
          <li><a href="../food/food_find.do">맛집 찾기</a></li>
          <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
          <li><a href="../food/food_recommend.do">맛집 추천</a></li>
          <li><a href="../food/food_reserve.do">맛집 예약</a></li>
          <li><a href="../food/food_recipe.do">맛집 레시피</a></li>
          </sec:authorize>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="../recipe/recipe_list.do">레시피</a></li>
          <li><a href="../recipe/chef_list.do">쉐프</a></li>
          <li><a href="pages/full-width.html">오늘의 레시피</a></li>
          <li><a href="pages/sidebar-left.html">오늘의 쉐프</a></li>
          <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
          <li><a href="pages/sidebar-right.html">레시피 만들기</a></li>
          </sec:authorize>
        </ul>
      </li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="../freeboard/list.do">자유게시판</a></li>
          <li><a href="pages/full-width.html">공지사항</a></li>
          <sec:authorize access="hasRole('ROLE_USER')">
          <li><a href="pages/sidebar-left.html">묻고답하기</a></li>
          </sec:authorize>
        </ul>
      </li>
      <li><a href="../chat/chat.do" target="_blank">실시간 채팅</a></li>
      <sec:authorize access="hasRole('ROLE_USER')">
      <li><a href="#">마이페이지</a></li>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
      <li><a href="#">관리자페이지</a></li>
      </sec:authorize>
      <c:if test="${principal.username==null }">
      <li><a href="../member/login.do"><span class="glyphicon glyphicon-log-in">Login</span></a></li>
      </c:if>
      <c:if test="${principal.username!=null }">
      <li><p style="position: absolute; top: -40px; right: -50px; color: #000; width: 300px; text-align: right;">${principal.username }(
      <sec:authorize access="hasRole('ROLE_ADMIN')">관리자</sec:authorize>
      <sec:authorize access="hasRole('ROLE_USER')">일반사용자</sec:authorize>
      )님 로그인 되셨습니다.</p><a href="../member/logout.do"><span class="glyphicon glyphicon-log-in">Logout</span></a></li>
      </c:if>
      <!-- <li><a href="#">관리자페이지</a></li> -->
    </ul>
  </nav>
</div>
</body>
</html>