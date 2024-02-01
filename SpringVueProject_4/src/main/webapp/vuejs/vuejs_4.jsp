<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: 350px;
}

.trs:hover {
	cursor: pointer;
}

.title {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.pos {
	position: sticky;
	top: 0;
	right: 0;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
	<div class="container" id="app1">
		<div class="row">
		   <input type="button" class="btn-xs btn" value="한식" @click="select(1)">
		   <input type="button" class="btn-xs btn" value="중식" @click="select(2)">
		   <input type="button" class="btn-xs btn" value="일식" @click="select(3)">
		   <input type="button" class="btn-xs btn" value="양식" @click="select(4)">
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
		   <div v-if="type===1">
		      한식을 선택
		   </div>
		   <div v-else-if="type===2">
		      중식을 선택
		   </div>
		   <div v-else-if="type===3">
		      일식을 선택
		   </div>
		   <div v-else-if="type===4">
		       양식을 선택
		   </div>
		   <div v-else>
		       선택하세요
		   </div>
		</div>
	</div>
	<div class="container" id="app2">
	   <div class="row">
	      <input type="button" value="로그인" class="btn btn-sm" @click="login1(true)">
	      <input type="button" value="로그아웃" class="btn btn-sm" @click="login1(false)">
	      <div v-if="login===true" >
	         로그인 되었습니다.
	      </div>
	      <div v-else >
	          로그아웃 되었습니다.
	      </div>
	   </div>
	</div>
	<script>
	let app1=Vue.createApp({
		data(){
			return{
				type:0 //선택이 안되도록 초기화
			}
		},
		methods:{
			select(type){
				this.type=type
			}
		}
	}).mount('#app1') //동일 클래스가 두개 있으면 제어 영역이 오류가남
	         //예를들어 container가 두개인경우 mount('.container')로
	         //설정하면 제어영역 오류가 남
	let app2=Vue.createApp({
		data(){
			return{
				login:false
			}
		},
		methods:{
			login1(bCheck){
				this.login=bCheck
			}
		}
	}).mount('#app2')
	</script>
</body>
</html>










