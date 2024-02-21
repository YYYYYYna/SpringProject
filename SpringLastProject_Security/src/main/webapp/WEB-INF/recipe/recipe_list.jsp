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
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <div class="content" id="recipeApp"> 
      <div id="gallery">
        <figure>
          <h2 class="sectiontitle">총 <span style="color: green">{{count.toLocaleString()}}</span> 개의 레시피가 있습니다.</h2> 
          <ul class="nospace clear">
            <li v-for="(vo,index) in recipe_list" :class="index%4==0?'one_quarter first':'one_quarter'">
               <a :href="'../recipe/recipe_list_detail.do?no='+vo.no">
                  <img :src="vo.poster" :title="vo.title"
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
  </main>
</div>
<script>
 let foodApp=Vue.createApp({
	 data(){
		 return{
			 recipe_list:[],
			 curpage:1,
			 totalpage:0,
			 startPage:0,
			 endPage:0,
			 count:0
		 }
	 },
/* 	 filters: {
		  currency: function(value) {
		    let num = new Number(value);
		    return num.toFixed(0).replace(/\d(?=(\d{3})+$)/g, "$&,");
		  }
		}, */
	 mounted(){
		this.dataRecv() 
	 },
	 methods:{
		 dataRecv(){
		 axios.get('../recipe/recipe_list_vue.do',{
			 params:{
				 page:this.curpage
			 }
		 }).then(res=>{
			 console.log(res.data)
			 this.recipe_list=res.data
		 })
		 //페이지
		 axios.get('../recipe/recipe_page_vue.do',{
			 params:{
				 page:this.curpage
			 }
		 }).then(res=>{
			 console.log(res.data)
			 this.curpage=res.data.curpage
			 this.totalpage=res.data.totalpage
			 this.startPage=res.data.startPage
			 this.endPage=res.data.endPage
			 this.count=res.data.count
		 })
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
 }).mount('#recipeApp')
</script>
</body>
</html>