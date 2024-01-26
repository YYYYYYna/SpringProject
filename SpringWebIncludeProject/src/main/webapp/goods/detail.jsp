<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" class="text-center" rowspan="8">
					   <img src="${gvo.goods_poster }"
						style="width: 290px; height: 400px;">
				    </td>
					<td colspan="8">
						<h3>${gvo.goods_name }&nbsp;
						    <span style="color: orange;">${gvo.goods_discount }</span>
						</h3>
					</td>
				</tr>
				<tr>
				   <th width="20%" class="text-right">세일가격</th>
				   <td width="50%">${gvo.goods_price }</td>
				</tr>
				<tr>
				   <th width="20%" class="text-right">배송</th>
				   <td width="50%">${gvo.goods_delivery }</td>
				</tr>
				<tr>
				   <th width="20%" class="text-right">가격</th>
				   <td width="50%">${gvo.goods_first_price }</td>
				</tr>
				<tr>
				   <td colspan="3">
				      <pre style="white-space: pre-wrap; border: none; background: white;">
				      ${gvo.goods_sub }
				      </pre>
				   </td>
				</tr>
				<tr>
				   <td colspan="3" class="text-right">
				      <a href="javascript:history.back()" class="btn btn-xs btn-info">목록</a>
				   </td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>