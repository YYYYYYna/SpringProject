<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:960px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
       <div class="text-center">
          <my-btn1/>
       </div>
    </div>
  </div>
  <script>
  const mybtn1={
		template:`<button class="btn-sm btn">1번</button>`  
  }
  const mybtn2={
		template:`<button class="btn-sm btn">2번</button>` 
  }
  const mybtn3={
		template:`<button class="btn-sm btn">3번</button>`   
  }
  let app=createApp({
	  data(){
		  return{
			  
		  }
	  },
	  methods:{
		  
	  },
	  component:{
		  'my-btn1':mybtn1,
		  'my-btn2':mybtn2,
		  'my-btn3':mybtn3
	  }
  }).mount(".container")
  </script>
</body>
</html>