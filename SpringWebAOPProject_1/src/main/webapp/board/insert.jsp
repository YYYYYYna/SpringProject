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
</style>
</head>
<body>
	<div class="container">
		<div class="row">
		   <h3 class="text-center">글쓰기</h3>
		   <form method="post" action="insert_ok.do">
		   <table class="table">
		      <tr>
		         <th width="15%" class="text-right">이름</th>
		         <th width="85%">
		            <input type="text" name=name size=15 class="input-sm">
		         </th>
		      </tr>
		      <tr>
		         <th width="15%" class="text-right">제목</th>
		         <th width="85%">
		            <input type="text" name=subject size=50 class="input-sm">
		         </th>
		      </tr>
		      <tr>
		         <th width="15%" class="text-right">내용</th>
		         <th width="85%">
		            <textarea name=content rows="5" cols="52"></textarea>
		         </th>
		      </tr>
		      <tr>
		         <th width="15%" class="text-right">비밀번호</th>
		         <th width="85%">
		            <input type="text" name=pwd size=10 class="input-sm">
		         </th>
		      </tr>
		      <tr>
		         <td colspan="2" class="text-center">
		            <input type="submit" value="글쓰기" class="btn-sm btn-info">
		            <input type="button" value="취소" class="btn-sm btn-info"
		            onclick="javascript:history.back()">
		         </td>
		      </tr>
		   </table>
		   </form>
		</div>
		<div style="height: 20px;"></div>
		<jsp:include page="top.jsp"></jsp:include>
	</div>
</body>
</html>