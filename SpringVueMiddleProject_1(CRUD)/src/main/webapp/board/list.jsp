<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringVueProject_5</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 700px;
}

.images:hover {
	cursor: pointer;
}

.title {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">자유게시판</h3>
		<div class="row">
		   <table class="table">
		      <tr>
		         <td>
		            <a href="insert.do" class="btn btn-sm">새글</a>
		         </td>
		      </tr>
		   </table>
		   <table class="table">
		      <tr class="info">
		         <th class="text-center" width="10%">번호</th>
		         <th class="text-center" width="45%">제목</th>
		         <th class="text-center" width="15%">이름</th>
		         <th class="text-center" width="20%">작성일</th>
		         <th class="text-center" width="10%">조회수</th>
		      </tr>
		       <tr v-for="vo in board_list">
		         <td class="text-center" width="10%">{{vo.no}}</td>
		                           <!-- vue 데이터 쓰려면 꼭 :붙이는거 잊지 말기 -->
		         <td width="45%"><a :href="'detail.do?no='+vo.no">{{vo.subject}}</a></td>
		         <td class="text-center" width="15%">{{vo.name}}</td>
		         <td class="text-center" width="20%">{{vo.dbday}}</td>
		         <td class="text-center" width="10%">{{vo.hit}}</td>
		      </tr>
		      <tr>
		         <td colspan="5" class="text-center">
		           <input type="button" class="btn btn-sm" value="이전" @click="prev()">
		           {{curpage}} page / {{totalpage}} pages
		           <input type="button" class="btn btn-sm" value="다음" @click="next()">
		         </td>
		      </tr>
		      <tr>
		         <td colspan="5" v-html="test">
		          <!-- {{test}} -->
		         </td>
		      </tr>
		   </table>
		</div>
	</div>
	<script>
		let app = Vue.createApp({
			data(){
				return{
					board_list:[],
					curpage:1,
					totalpage:0,
					test:'<span style="display: block; text-align: center;">hello</span>'
				}
			},
			mounted(){
				this.dataSend()
			},
			methods:{
				dataSend(){
					axios.get("../board/list_vue.do",{
						//params가 있어야 ?뒤의값이 들어감
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.board_list=response.data
					})
					
					//페이지 받아오기
					axios.get("../board/page_vue.do",{
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
					})
					
				},
				prev(){
					this.curpage=this.curpage>1?this.curpage-1:this.curpage
					this.dataSend();
				},
				next(){
					this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
					this.dataSend();
				}
			}
		}).mount('.container')
	</script>
</body>
</html>