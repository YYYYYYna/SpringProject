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
	width: 960px;
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
     <div class="row">
        <div class="col-md-3" v-for="vo in goods_list">
				<div class="thumbnail">
					<a href="#"> <img :src="vo.poster"
						style="min-width: 180px; min-height:200px; width:100%; height: 100%; overflow: hidden;">
						<div class="caption">
							<p class="title">{{vo.name}}</p>
						</div>
					</a>
				</div>
			</div>
     </div>
     <div class="row">
        <div class="text-center">
           <ul class="pagination">
              <li v-if="startPage>1"><a href="#" @click="prev()">&laquo;</a></li>
              <li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#" @click="pageChange(i)">{{i}}</a></li>
              <li v-if="endPage<totalPage"><a href="#" @click="next()">&raquo;</a></li>
           </ul>
        </div>
     </div>
  </div>
  <script>
  let app=Vue.createApp({
	  data(){
		  return{
			  goods_list:[],
			  curpage:1,
			  totalPage:0,
			  startPage:0,
			  endPage:0
		  }
	  },
	  mounted(){
		  this.dataSend()
	  },
	  methods:{
		  //데이터 받는 부분 메소드화
		  dataSend(){
			  axios.get("http://localhost:8080/web/goods/list_vue.do",{
				  params:{
					  page:this.curpage
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.goods_list=response.data;
				  this.curpage=response.data[0].curpage
				  this.endPage=response.data[0].endPage
				  this.totalPage=response.data[0].totalPage
				  this.startPage=response.data[0].startPage
			  })
		  },
		  //페이지 범위 
		  range(start,end){
			  let arr=[]
			  let length=end-start
			  for(let i=0;i<=length;i++)
			  {
				  arr[i]=start;
				  start++;
			  }
			  return arr;
		  },
		  //현재 페이지
		  pageChange(page){
			  this.curpage=page;
			  this.dataSend();
		  },
		  //이전 페이지
		  prev(){
			  this.curpage=this.startPage-1;
			  this.dataSend()
		  },
		  //이후 페이지
		  next(){
			  this.curpage=this.endPage+1;
			  this.dataSend()
		  }
	  }
  }).mount('.container')
  </script>
</body>
</html>