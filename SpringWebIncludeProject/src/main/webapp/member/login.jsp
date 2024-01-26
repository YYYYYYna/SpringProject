<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
  margin: 0px auto;
  width: 350px;
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
			<h3 class="text-center">Login</h3>
			<form method="post" action="../member/login_ok.do">
			<table class="table">
				<tr>
				   <th width="20%">ID</th>
				   <td width="80%"><input type="text" name="id" class="input-sm" size="15" required></td>
				</tr>
				<tr>
				   <th width="20%">PW</th>
				   <td width="80%"><input type="password" name="pwd" class="input-sm" size="15" required></td>
				</tr>
				<tr>
				   <td colspan="2" class="text-center">
				      <button>로그인</button>
				   </td>
				</tr>
			</table>
		   </form>
		</div>
	</div>
</body>
</html>