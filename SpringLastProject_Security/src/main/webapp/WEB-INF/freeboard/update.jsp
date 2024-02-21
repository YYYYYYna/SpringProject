<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row1{
   margin: 0px auto;
   width: 700px;
}
</style>
</head>
<body>
	<div class="wrapper row3" id="fboardApp">
		<main class="container clear">
			<h2 class="sectiontitle">수정하기</h2>
			<table class="table">
			   <tr>
			      <th width="10%">이름</th>
			      <td width="90%"><input type="text" ref="name" v-model="name" size=15 class="input-sm"></td>
			   </tr>
			   <tr>
			      <th width="10%">제목</th>
			      <td width="90%"><input type="text" ref="subject" v-model="subject" size=15 class="input-sm"></td>
			   </tr>
			   <tr>
			      <th width="10%">내용</th>
			      <td width="90%">
                     <textarea rows="10" cols="52" ref="content" v-model="content"></textarea>
                  </td>
			   </tr>
			   <tr>
			      <th width="10%">비밀번호</th>
			      <td width="90%"><input type="password" ref="pwd" v-model="pwd" size=15 class="input-sm"></td>
			   </tr>
			   <tr>
			      <td colspan="2" class="text-center">
			         <input type="button" value="수정하기" class="btn-sm btn" @click="update()">&nbsp;
			         <input type="button" value="취소" class="btn-sm btn" @click="goback()">
			      </td>
			   </tr>
			</table>
		</main>
	</div>
	<script>
	  let fboardApp=Vue.createApp({
		  data(){
			  return{
				  update_data:{},
				  name:'',
				  subject:'',
				  content:'',
				  pwd:'',
				  no:${no}
			  }
		  },
		  mounted(){
			  axios.get('../freeboard/update_vue.do',{
				  params:{
					  no:this.no
				  }
			  }).then(res=>{
				  console.log(res.data)
				  this.update_data=res.data
				  this.name=res.data.name
				  this.subject=res.data.subject
				  this.content=res.data.content
			  })
		  },
		  methods:{
			  goback(){
				  //router를 사용하려면 관련을 설치해야함
				  //this.$router.go(-1)
				  window.history.back()
			  },
			  update(){
				  if(this.name==="")
				  {
					  this.$refs.name.focus();
					  return
				  }
				  if(this.subject==="")
				  {
					  this.$refs.subject.focus();
					  return
				  }
				  if(this.content==="")
				  {
					  this.$refs.content.focus();
					  return
				  }
				  if(this.pwd==="")
				  {
					  this.$refs.pwd.focus();
					  return
				  }
				  axios.post('../freeboard/update_ok_vue.do',null,{
					  params:{
						  name:this.name,
						  subject:this.subject,
						  content:this.content,
						  pwd:this.pwd,
						  no:this.no
					  }
				  }).then(res=>{
					  if(res.data==="yes")
					  {
						  location.href="../freeboard/detail.do?no="+this.no
					  }
					  else
					  {
						  alert("비밀번호가 틀립니다!!")
						  this.pwd=""
						  this.$refs.pwd.focus()
					  }
				  })
			  }
		  }
	  }).mount('#fboardApp')
	</script>
</body>
</html>