<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.link,.img_click{
  cursor: pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3" id="foodApp">
  <main class="container clear"> 
    <!-- main body --> 
    <div class="content" > 
      <div id="gallery">
        <figure>
          <h2 class="sectiontitle">맛집 목록</h2> 
          <ul class="nospace clear">
            <li v-for="(vo,index) in food_list" :class="index%4==0?'one_quarter first':'one_quarter'">
                <!-- 클릭하면 쿠키생성!! vue로 만드는거 아님 Controller부분 보고 restcontroller봐야함-->
               <a :href="'../food/food_before_list_detail.do?fno='+vo.fno">
                  <img :src="'https://www.menupan.com'+vo.poster" :title="vo.name"
                  style="border-radius: 10px;">
               </a>
            </li>
          </ul>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a class="link" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i===curpage?'current':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a class="link" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
    </div>
    <!-- / main body -->
    <div class="clear"></div>
    
    <!-- 여기서부터 쿠키!! vue로 만드는거 아님 Controller부분 보고 restcontroller봐야함-->
    <div>
    <h3>최근 방문 맛집</h3>
    <hr>
    <!-- 여기에 v-for문으로 cookie_list 출력하면될듯 -->
    <span v-for="vo in cookie_list">
       <img :src="'https://www.menupan.com'+vo.poster" :title="vo.name"
                  style="border-radius: 10px; width: 100px; height: 100px;">
    </span>
    </div>
  </main>
</div>
<script>
 let foodApp=Vue.createApp({
	 data(){
		 return{
			 food_list:[],
			 curpage:1,
			 totalpage:0,
			 startPage:0,
			 endPage:0,
			 cookie_list:[]
		 }
	 },
	 mounted(){
		this.dataRecv() 
	 },
	 methods:{
		 dataRecv(){
		 axios.get('../food/food_list_vue.do',{
			 params:{
				 page:this.curpage
			 }
		 }).then(res=>{
			 console.log(res.data)
			 this.food_list=res.data
		 })
		 //페이지
		 axios.get('../food/food_page_vue.do',{
			 params:{
				 page:this.curpage
			 }
		 }).then(res=>{
			 console.log(res.data)
			 this.curpage=res.data.curpage
			 this.totalpage=res.data.totalpage
			 this.startPage=res.data.startPage
			 this.endPage=res.data.endPage
		 })
		 
		 //쿠키
		 axios.get('../food/food_cookie_vue.do').then(res=>{
			 console.log(res.data)
			 this.cookie_list=res.data
		 }).catch(error => {
			    console.error('Error fetching cookies:', error);
		  });
		 },
		 range(start,end){
			 let arr=[]
			 let length=end-start
			 for(let i=0;i<=length;i++)
			 {
				 arr[i]=start
				 start++;
			 }
			 return arr;
		 },
		 prev(){
			 this.curpage=this.startPage-1
			 this.dataRecv()
		 },
		 next(){
			 this.curpage=this.endPage+1
			 this.dataRecv()
		 },
		 pageChange(page){
			 this.curpage=page
			 this.dataRecv()
		 }
	 }
 }).mount('#foodApp')
</script>
</body>
</html>