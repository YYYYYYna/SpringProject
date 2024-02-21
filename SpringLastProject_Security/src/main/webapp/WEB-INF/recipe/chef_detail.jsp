<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 다이얼로그 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
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
   <h2 class="sectiontitle">쉐프 레시피 리스트</h2> 
   <div class="content" id="chefApp"> 
      <div id="gallery">
        <figure>
         <header class="heading inline">
            <input type="text" class="input-sm" ref="ss" v-model="ss" size="20" placeholder="검석어 입력"
            @keyup.enter="recipeFind()">&nbsp;
            <input type="button" class="btn-sm btn" value="검색" style="background: #444; color: #fff;"
            @click="recipeFind()">
         </header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in chef_list" :class="index%4==0?'one_quarter first':'one_quarter'">
               <a :href="'../recipe/recipe_before_detail.do?cno='+vo.cno">
                  <img class="img_click" :src="vo.poster" :title="vo.title"
                  style="border-radius: 10px; " @click="detail(vo.cno)">
               </a>
            </li>
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a class="link" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i===curpage?'current':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a class="link" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
      <div id="dialog" title="맛집 상세보기" v-show="isShow">
          <!-- 컴포넌트에 데이터 전달할때는 v-bind 사용하는거 잊지말기 -->
         <detail_dialog v-bind:food_detail="food_detail"></detail_dialog>
      </div>
    </div>
    <div class="clear"></div>
   </main>
</div>
<script>
 let chefApp=Vue.createApp({
	 data(){
		 return{
			chef_list:[],
			food_detail:{},
			page_list:{},
			cno:${cno},
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			isShow:false,
			ss:''
		 }
	 },
	 mounted(){
		 this.dataRecv()
	 },
	 update(){
		 
	 },
	 methods:{
		 dataRecv(){
			 axios.get('../recipe/chef_detail_vue.do',{
				 params:{
					 page:this.curpage,
					 cno:this.cno
				 }
			 }).then(res=>{
				 console.log(res.data)
				 this.chef_list=res.data
			 })
			 axios.get('../recipe/chef_detail_page_vue.do',{
				 params:{
					 page:this.curpage,
					 cno:this.cno
				 }
			 }).then(res=>{
				 console.log(res.data)
				 this.page_list=res.data
				 this.curpage=res.data.curpage
 				 this.totalpage=res.data.totalpage
 				 this.startPage=res.data.startPage
 				 this.endPage=res.data.endPage
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
		 },
		 recipeFind(){
			 if(this.ss==="")
			 {
				 this.$refs.ss.focus()
				 return
			 }
			 this.dataRecv()
		 },
		 //여기 fno는 지역변수임
		 detail(fno){
			 axios.get('../food/detail_vue.do',{
				 params:{
					 fno:fno
				 }
			 }).then(res=>{
				 console.log(res.data)
				 this.food_detail=res.data
				 
				 this.isShow=true
				 $('#dialog').dialog({
					 autoOpen:false,
					 modal:true,
					 width:700,
					 height:600
				 }).dialog("open")
			 }).catch(error=>{
				 console.log(error.res)
			 })
			
		 }
	 },
	 components:{

	 }
 }).mount("#chefApp")
</script>
</body>
</html>