<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<form method="post" action="../seoul/find.do">
				<!-- 검색 부분 -->
				<select name="tablename" class="input-sm">
					<option value="seoul_location" ${tablename=='seoul_location'?"selected":"" }>명소</option>
					<option value="seoul_nature" ${tablename=='seoul_nature'?"selected":"" }>자연</option>
					<option value="seoul_shop" ${tablename=='seoul_shop'?"selected":"" }>상점</option>
				</select> <input type="text" name="ss" size="20" class="input-sm"
					value="${ss }"> <input type="submit" value="검색"
					class="btn-sm">
			</form>
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
			<!-- 결과 출력 부분 -->
			<c:forEach var="vo" items="${slist }">
				<div class="col-sm-3">
				   <a href="#">
					<div class="panel panel-danger">
						<div class="panel-heading title">${vo.title }</div>
						<div class="panel-body text-center">
							<img src="${vo.poster }"
								style="width: 180px; height: 200px;">
						</div>
					</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<!-- 페이지 출력 부분 -->
			<div class="text-center">
				<ul class="pagination">
					<c:if test="${startPage>1 }">
						<li><a href="../seoul/find.do?page=${startPage-1 }&tablename=${tablename}&ss=${ss}">&lt;</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<li ${curpage==i?"class=active":"" }><a
							href="../seoul/find.do?page=${i }&tablename=${tablename}&ss=${ss}">${i }</a></li>
					</c:forEach>
					<c:if test="${endPage<totalpage }">
						<li><a href="../seoul/find.do?page=${endPage+1 }&tablename=${tablename}&ss=${ss}">&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>