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
   <h2 class="sectiontitle">맛집 검색</h2> 
   <div class="content" id="findApp"> 
      <div id="gallery">
        <figure>
          <header class="heading inline">
             <input type="text" ref="fd" size="20" class="input-sm" v-model="fd" 
             @keyup.enter="find()">&nbsp;
             <input type="button" value="검색" class="btn-sm btn" style="background-color: #666; color: #fff;"
             @click="find()">
          </header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in food_list" :class="index%4==0?'one_quarter first':'one_quarter'">
               
                  <img class="img_click" :src="'https://www.menupan.com'+vo.poster" :title="vo.name"
                  style="border-radius: 10px; " @click="detail(vo.fno)">
     
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
 const detailComponent={
		props:['food_detail'],
		template:`<h3 class="text-center">맛집 상세보기</h3>
		          <table class="table">
		             <tr>
		                <td width="30%" class="text-center" rowspan="9">
		                   <img :src="'http://www.menupan.com'+food_detail.poster" style="width:100%">
		                </td>
		                <td colspan="2">
		                   <h3>{{food_detail.name}}&nbsp;
		                   <span style="color:orange;">{{food_detail.score}}</span></h3>
		                </td>
		             </tr>
		             <tr>
		                <th width="15%">주소</th>
		                <td width="55%">{{food_detail.address}}</td>
		             </tr>
		             <tr>
		                <th width="15%">전화</th>
		                <td width="55%">{{food_detail.phone}}</td>
		             </tr>
		             <tr>
		                <th width="15%">음식종류</th>
		                <td width="55%">{{food_detail.type}}</td>
		             </tr>
		             <tr>
		                <th width="15%">영업시간</th>
		                <td width="55%">{{food_detail.time}}</td>
		             </tr>
		             <tr>
		                <th width="15%">가격대</th>
		                <td width="55%">{{food_detail.price}}</td>
		             </tr>
		             <tr>
		                <th width="15%">좌석</th>
		                <td width="55%">{{food_detail.seat}}</td>
		             </tr>
		             <tr>
		                <th width="15%">테마</th>
		                <td width="55%">{{food_detail.theme}}</td>
		             </tr>
		          </table>
		         `
 }
 let findApp=Vue.createApp({
	 data(){
		 return{
			food_list:[],
			fd:'마포',
			food_detail:{},
			page_list:{},
			fno:1,
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			isShow:false
		 }
	 },
	 mounted(){
		 this.dataRecv()
	 },
	 update(){
		 
	 },
	 methods:{
		 dataRecv(){
			 axios.get('../food/find_vue.do',{
				 params:{
					 page:this.curpage,
					 fd:this.fd
				 }
			 }).then(res=>{
				 console.log(res.data)
				 this.food_list=res.data
			 })
			 axios.get('../food/page_vue.do',{
				 params:{
					 page:this.curpage,
					 fd:this.fd
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
		 find(){
			 this.curpage=1
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
		 //상세보기 => dialog
		 //클릭시 안나올때는 main의 jquery충돌을 의심해볼것
		 //컴포넌트에 데이터 전달할때는 v-bind 사용하는거 잊지말기
		 'detail_dialog':detailComponent
	 }
 }).mount("#findApp")
</script>
</body>
</html>