<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="wrapper row3" id="foodListDetailApp">
  <main class="container clear"> 
    <h2 class="sectiontitle">맛집 상세보기</h2>
    <div class="content two_third first"> 
         <table class="table">
              <tr>
              <td colspan="2" class="text-center">
              <img :src="'http://www.menupan.com'+food_detail.poster" style="witdh:320px; height: 500px;">
              </td>
              </tr>
              <tr>
              <td colspan="2">
              <h3><span id="name">{{food_detail.name}}</span>&nbsp;<span style="color:orange">{{food_detail.score}}</h3>
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
                 <th width="15%">가격대</th>
                 <td width="55%">{{food_detail.price}}</td>
              </tr>
              <tr>
                 <th width="15%">영업시간</th>
                 <td width="55%">{{food_detail.time}}</td>
              </tr>
              <tr>
                 <th width="15%">좌석</th>
                 <td width="55%">{{food_detail.seat}}</td>
              </tr>
              <tr>
                 <th width="15%">테마</th>
                 <td width="55%">{{food_detail.theme}}</td>
              </tr>
              <tr>
                 <td colspan="3" class="text-right">
                    <input type="button" class="btn-xs btn" value="찜하기">&nbsp;
                    <input type="button" class="btn-xs btn" value="예약">&nbsp;
                    <input type="button" class="btn-xs btn" value="목록" @click="goback()">
                 </td>
              </tr>
              </table>
    </div>
    <div class="one_third"> 
     <div id="map" style="width:100%;height:350px;"></div>
      
    </div>
    <!-- / main body -->
    <div class="clear"></div>
     <h2 class="sectiontitle">관련 레시피</h2>
     <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides" v-for="r in recipe_list">
        <li>
          <figure><img class="radius-10 btmspace-10" :src="r.poster" style="width: 320px;height: 185px;">
            <figcaption><a href="#">{{r.title}}</a></figcaption>
          </figure>
        </li>
      </ul>
     </div>
    
  </main>
</div>
<script>
let foodListDetailApp=Vue.createApp({
	 data(){
	        return {
	           food_detail:{},
	           fno:${fno},
	           food_type:'',
	           recipe_list:[]
	        }
	     },
   mounted(){
      axios.get("../food/food_detail_vue.do",{
         params:{
            fno:this.fno
         }
      }).then(res=>{
    	  console.log(res.data)
          this.food_detail=res.data
          this.food_type=res.data.title
         
         if(window.kakao && window.kakao.maps)
         {
            this.initMap()
         }
         else
         {
            this.addScript()
         }
      })
      
      axios.get('../food/food_detail_recipe.do',{
          params:{
             fno:this.fno,
             title:this.food_type
          }
       }).then(response=>{
          this.recipe_list=response.data
       })

   },
   methods:{
      addScript(){
         const script=document.createElement("script") 
         
         /* global kakao */
         script.onload=()=>kakao.maps.load(this.initMap)
         script.src="https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=23e8040d553778eeeb77f0900cb92322&libraries=services"
         document.head.appendChild(script)
      },
      initMap(){
         var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
          mapOption = {
              center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
              level: 3 // 지도의 확대 레벨
          };  

      // 지도를 생성합니다    
      var map = new kakao.maps.Map(mapContainer, mapOption); 

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(this.food_detail.address, function(result, status) {

          // 정상적으로 검색이 완료됐으면 
           if (status === kakao.maps.services.Status.OK) {

              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("#name").text()+'</div>'
              });
              infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      })
     },
     goback(){
    	 location.href="../food/food_list.do"
     }
   }
}).mount("#foodListDetailApp")
</script>
</body>
</html>