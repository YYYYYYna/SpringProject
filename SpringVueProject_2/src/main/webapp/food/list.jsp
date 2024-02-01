<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function () {
   $('#msg').keyup(function(){
      $('#print').text($('#msg').val())
   })
})
</script> -->
<style type="text/css">
.container-fluid{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 100%;
}
.images:hover{
cursor: pointer;
}
.title{
   white-space: nowrap;
   text-overflow: hidden;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="container-fluid" id="app">
<div class="row">
<div class="col-sm-8">
<div class="row">
  <div class="col-md-3" v-for="vo in food_list">
    <div class="thumbnail">
        <img :src="'https://www.menupan.com'+vo.poster" alt="Lights" style="width:100%" class="images"
        v-on:click="detail(vo.fno)">
        <div class="caption">
          <p class="title">{{vo.name}}</p>
        </div>
    </div>
  </div>
</div>
</div>
<div class="col-sm-4" v-show="isShow">
<table class="table">
<tr>
<td width="30%" class="text-center" rowspan="7">
<img :src="'https://www.menupan.com'+food_detail.poster" style="width: 100%">
</td>
<td colspan="2">
<h3>{{food_detail.name}}&nbsp;<span style="color: orange">{{food_detail.score}}</span></h3>
</td>
</tr>
</table>
</div>
</div>
</div>
<script>
let app=Vue.createApp({
   data(){
      return{
         food_list:[], 
         food_detail:{}, 
         fno:0,
         isShow:false
      }
   },
   mounted(){
      axios.get('http://localhost:8080/web/food/list_vue.do').then(response=>{
         console.log(response.data)
         this.food_list=response.data
      })
   },
   methods:{
      detail(fno){
         this.isShow=true
         this.fno=fno;
         let _this=this
         axios.get('http://localhost:8080/web/food/detail_vue.do',{
            params:{
               fno:_this.fno
            }
         }).then(response=>{
            console.log(response.data)
            _this.food_detail=response.data;
         })
      }
      
   }
}).mount('#app')
</script>
</body>
</html>