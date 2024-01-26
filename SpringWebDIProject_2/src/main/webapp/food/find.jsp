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
<script src="http://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size="20" class="input-sm" v-model="fd">
			<input type="button" value="검색" class="btn-sm btn-info"
			  @click=find()>
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
		<!-- 출력부 -->
		<div class="col-md-3" v-for="vo in find_data">
					<div class="thumbnail">
						<a href="#"> <img
							:src="'https://www.menupan.com'+vo.poster" alt="Lights" style="width: 100%">
							<div class="caption">
								<p class="title">{{vo.name }}</p>
							</div>
						</a>
					</div>
				</div>
		</div>
	</div>
	<script>
	   const {createApp} = Vue
	   createApp({
		   data(){
			   return{
				   //v-model(멤버변수) 들어가는 위치
				   //fd:'마포',라고 적어두면 검색창에 마포라고 적힌채로 출력됨
				   fd:'마포',
				   find_data:[]
			   }
		   },
		   //$(function(){})의 부분
		   mounted(){
			   axios.get('http://localhost:8080/web/food/find_vue.do',{
				   //데이터 보내는(ajax에서 data:) 부분
				   params:{
					   fd:this.fd,
					   page:1
				   }
				 //데이터 받는(ajax에서 success:) 부분
			   }).then(response=>{
				   this.find_data=response.data
				   console.log(response.data)
			   })
		   },
		   methods:{
			   find(){
				   axios.get('http://localhost:8080/web/food/find_vue.do',{
					   //데이터 보내는(ajax에서 data:) 부분
					   params:{
						   fd:this.fd,
						   page:1
					   }
					 //데이터 받는(ajax에서 success:) 부분
				   }).then(response=>{
					   this.find_data=response.data
					   console.log(response.data)
				   })
			   }
		   }
	   }).mount('.container')
	</script>
</body>
</html>