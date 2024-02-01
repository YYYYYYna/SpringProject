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
			<div class="col-md-3" v-for="vo in food_list">
				<div class="thumbnail">
					<a href="#"> <img :src="'https://www.menupan.com'+vo.poster"
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
			food_list:[],
			curpage:1,
			totalPage:0,
			startPage:0,
			endPage:0
		}
	},
	mounted(){
		this.dataSend();
	},
	methods:{
		//반복코딩이라서 메소드화시킴
		dataSend(){
			axios.get("http://localhost:8080/web/food/list_vue.do",{
				params:{
					page:this.curpage
				}
			}).then(response=>{
				console.log(response.data);
				this.food_list=response.data;
				this.curpage=response.data[0].curpage
				this.endPage=response.data[0].endPage
				this.totalPage=response.data[0].totalPage
				this.startPage=response.data[0].startPage
			})
		},
		//이하 페이지와 관련된 메소드
		range(start,end){
			let arr=[]
			let length=end-start //0~9
			for(let i=0;i<=length;i++)
			{
				arr[i]=start
				start++;
			}
			return arr;
		},
		pageChange(page){
			this.curpage=page
			this.dataSend()
		},
		prev(){
			this.curpage=this.startPage-1;
			this.dataSend();
		},
		next(){
			this.curpage=this.endPage+1;
			this.dataSend();
		}
	}
}).mount('.container')
</script>
</body>
</html>