<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['단어', '단어횟수'],
          <c:forEach var="vo" items="${list}">
          ['<c:out value="${vo.word}"/>', <c:out value="${vo.count}"/>],
          </c:forEach>
          
        ]);

        var options = {
          title: '내용분석'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

</head>
<body>
	<div class="wrapper row3" id="fboardApp">
		<main class="container clear">
			<h2 class="sectiontitle">내용보기</h2>
			<table class="table">
				<tr>
				   <th width="20%" class="text-center">번호</th>
				   <td width="30%" class="text-center" v-text="detail_data.no"></td>
				   <th width="20%" class="text-center">작성일</th>
				   <td width="30%" class="text-center" v-text="detail_data.dbday"></td>
				</tr>
				<tr>
				   <th width="20%" class="text-center">이름</th>
				   <td width="30%" class="text-center" v-text="detail_data.name"></td>
				   <th width="20%" class="text-center">조회수</th>
				   <td width="30%" class="text-center" v-text="detail_data.hit"></td>
				</tr>
				<tr>
				   <th width="20%" class="text-center">제목</th>
				   <td colspan="3">{{detail_data.subject}}</td>
				</tr>
				<tr>
				   <td colspan="4" class="text-center" valign="top" height="200px;">
				      <pre style="white-space: pre-wrap; border: none; background: white;">{{detail_data.content}}</pre>
				   </td>
				</tr>
				<tr>
				   <td colspan="4" class="text-right inline">
				      <input type="button" class="btn-xs btn" value="수정" @click="boardupdateForm()">&nbsp;
				      <input type="button" class="btn-xs btn" value="삭제" ref="delBtn" @click="boardDeleteForm()">&nbsp;
				      <input type="button" class="btn-xs btn" value="목록" @click="boardList()">
				   </td>
				</tr>
				<tr v-show="isShow">
				   <td colspan="4" class="text-right inline">
				      비밀번호:&nbsp;<input type="password" ref="pwd" v-model="pwd" class="input-sm" size=15>&nbsp;
				      <input type="button" class="btn-xs btn" value="삭제" @click="boardDelete()">
				   </td>
				</tr>
			</table>
			 <div id="piechart" class="container" style="width: 100%; height: 500px;"></div>
		</main>
	</div>
	<script>
	  let fApp=Vue.createApp({
		  data(){
			  return{
				  no:${no}, //controller에서 model로 받은 값
				  detail_data:{},
				  data_list:[],
				  pwd:'',
				  isShow:false,
				  check:0
			  }
		  },
		  mounted(){
			  axios.get("../freeboard/detail_vue.do",{
				  params:{
					  no:this.no
				  }
			  }).then(res=>{
				  console.log(res.data)
				  this.detail_data=res.data
			  })
		  },
		  methods:{
			  boardList(){
				  location.href="../freeboard/list.do"
			  },
			  boardDeleteForm(){
				  if(this.check===0)
				  {
					  this.check=1;
					  this.isShow=true;
					  this.$refs.pwd.focus();
					  this.$refs.delBtn.value="취소"
				  }
				  else
				  {
					  this.check=0;
					  this.isShow=false
					  this.pwd=''
					  this.$refs.delBtn.value="삭제"
				  }
			  },
			  boardDelete(){
				  if(this.pwd==="")
				  {
					  this.$refs.pwd.focus()
					  //위의 부분은 jquery에서 $('pwd').focus()와 같음! 
					  //위의 부분은 react에서 e.target.focus()와 같음!
					  return;
				  }
				  // delete_vue.do?no=1&pwd=1234 로 만들어주는거임
				  axios.get("../freeboard/delete.vue.do",{
					  params:{
						  no:this.no,
						  pwd:this.pwd
					  }
				  }).then(response=>{
					  if(response.data==="yes")
					  {
						  location.href="../freeboard/list.do"
					  }
					  else
					  {
						  alert("비밀번호가 틀립니다!!")
						  this.pwd=""
						  this.$refs.pwd.focus()
					  }
				  })
			  },
			  boardupdateForm(){
				  location.href="../freeboard/update.do?no="+this.no
			  }
			  
		  }
	  }).mount('#fboardApp')
	</script>
</body>
</html>