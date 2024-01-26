<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 960px;
}

.title {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
.text_area{
    width: 500px !important;
    height: 100px !important;
    display: inline-block;
    white-space: wrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
/*textgram*/
.testgram h3{
    border-bottom: 1px solid graytext;
}
.testgram .name_list{
    border-bottom: 1px solid graytext;
}
.name_list span{
    display: inline-block;
    border: 1px solid blue;
}

</style>
</head>
<body>
	<div class="container">
		<div class="row">
		   <h3 class="text-center">답변형 게시판 ex1</h3>
		   
		   <table class="table">
		     <tr>
		       <td>
		          <a href="insert.do" class="btn btn-sm btn-info">새글</a>
		       </td>
		     </tr>
		   </table>
		   <table class="table">
		      <tr class="info">
		         <th width="10%" class="text-center">번호</th>
		         <th width="45%" class="text-center">제목</th>
		         <th width="10%" class="text-center">이름</th>
		         <th width="15%" class="text-center">작성일</th>
		         <th width="10%" class="text-center">조회수</th>
		      </tr>
		      <c:set var="count" value="${count }"/>
		      <c:forEach var="vo" items="${list }">
		      <tr>
		         <td width="10%" class="text-center">${vo.no }</td>
		         <td class="text_area">
		            <c:if test="${vo.group_tab>0 }">
		              <c:forEach var="i" begin="1" end="${vo.group_tab }">
		                &nbsp;&nbsp;
		              </c:forEach>
		               <img src="re_icon.png">
		            </c:if>
		            <c:if test="${vo.subject=='관리자가 삭제한 게시글 입니다.' }">
		              <span style="color: gray;">${vo.subject }</span>
		            </c:if>
		            <c:if test="${vo.subject!='관리자가 삭제한 게시글 입니다.' }">
		              <!--<a href="detail.do?no=${vo.no }" >${vo.content }</a> -->
		              <span>${vo.content }</span>
		            </c:if>
		         </td>
		         <td width="10%" class="text-center">${vo.name }</td>
		         <td width="15%" class="text-center">${vo.dbday }</td>
		         <td width="10%" class="text-center">${vo.hit }</td>
		      </tr>
		      <c:set var="count" value="${count-1 }"/>
		      </c:forEach>
		   </table>
		   <div class="testgram">
		    <h3 class="text-center">추천 아이디 리스트</h3>
		    <div class="name_list">
		     <c:forEach var="vo" items="${list }">
		        <span>${vo.name }</span>
		     </c:forEach>
		     </div>
		     <h3 class="text-center">피드 리스트</h3>
		     <div class="feed_list">
		      <c:forEach var="vo" items="${list }">
		        <table class="table">
		           <tr style="display:flex; justify-content: space-between;">
		              <th><img src="re_icon.png"></th>
		              <th><span>${vo.name }</span><span>위치</span></th>		              
		              <th><input type="button" class="btn btn-sm"></th>		              
		           </tr>
		           <tr>
		              <td colspan="3">${vo.content }</td>
		           </tr>
		        </table>
		     </c:forEach>
		     </div>
		   </div>
		   <table class="table">
		     <tr>
		       <td>
		          <input type="checkbox" value="N" name="fd">이름
		          <input type="checkbox" value="S" name="fd">제목
		          <input type="checkbox" value="C" name="fd">내용
		          <input type="text" class="input-sm" name="ss">
		          <input type="submit" value="검색" class="btn-sm btn-info">
		       </td>
		       <td class="text-right">
		          <a href="list.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-success">이전</a>
		          ${curpage } page / ${totalpage } pages
		          <a href="list.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-success">다음</a>
		       </td>
		     </tr>
		   </table>
		   
		</div>
		<div style="height: 20px;"></div>
		<jsp:include page="top.jsp"></jsp:include>
	</div>
</body>
</html>