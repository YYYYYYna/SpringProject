<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
     Vue => AnglulaJS
     |단순한프레임워크
      1.형식이 다 같음 (단순함)
      2.가벼운 프레임 워크
      3.데이터를 효율적으로 다룰수 있음
      4.속도가 빠르다
      5.코드의 재사용이 가능하다
      6.컴포넌트 키반 프로그램
     => 전자상거래, 대시보드, 블로그, 뉴스사이트에서 주로 쓰임
     
     사용 : MVVM
          M (Model) : 데이터 저장 => data()
          V (View) : 화면출력
                     {{}}, v-for, v-model, v-if, v-show, v-if v-else
          VM (VueModel) : 상태(데이터관리,연산처리)
                          생명주기
                          1. mounted : onLoad
                          2. updated : 수정
                          3. 사용자 정의
                             methods:{
                                => 이벤트 처리
                             }
                          4. components:{
                                기능 => 이미지카드, 애니메이션 => 재사용이 가능
                             }
                          5. filter : 10,000
     ------------------------------------------------------------------
     1. Vue객체 생성 => 여러개 생성이 가능
        =====
        |범위지정==>mount('태그명,클래스명,ID명')
        let app=Vue.createApp({
            ----------------
             [1.Model => 데이터 관리]
             data(){
                return{
                   fno:0, Number형
                   fd,'', String형
                   list:[], Array형
                   obj:{}, Object형 => 자바스크립트 객체
                   isShow:true Boolean형
                }==> 선언/초기화 : 서버(Spring/NodeJS)읽기가 불가능하다
             }
            ----------------
              [2.ViewModel => 데이터 처리]
              1) 변수의 초기화
                 => 서버나 파일
                 => 이미 만들어진 메소드 (Callback) => Vue동작시 자동으로 호출
                 mounted(){
                    서버나 파일 읽기 => data에 저장된 변수 초기화
                    ===
                    axios.get("서버URL",{
                                 서버로 요청하는 데이터 설정
                                 params:{
                                    fno:1,
                                    id:'admin'
                                 }
                             }).then(res(결과값을받는다)=>{
                                    멤버변수에 대입
                             })
                    axios.post("서버URL",{
                                 서버로 요청하는 데이터 설정
                                 (post는 params가 없음)
                                    fno:1,
                                    id:'admin'
                             }).then(res(결과값을받는다)=>{
                                    멤버변수에 대입
                             })
                 }
            ----------------
              [3.사용자 정의 메소드]
              =>이벤트 (버튼 클릭, 마우스 오버, key...)
              methods:{
                  btnClick(){
                  },
                  mouseclick(){
                  }
              }
              재사용을 목적
              components:{
                  template:'<div></div>'
              }
        })
        ==============================================================
        화면출력
        
        출력형식
        <div>{{data()에설정된값예를들어 fd}}</div> => text()
        <div :data-no="fd"></div> => :속성명="변수명"
        디렉티브
          => v-for="vo in 배열" => v-for="(vo,index) in 배열"
          => v-if="true/false"
          => v-show="true/false" => display:none, display:''
          => v-if ~ v-else
          => v-model="멤버변수 설정" => 입력값을 멤버변수에 전송
          => 이벤트
             v-on:click ==> @click
             v-on:change ==> @change
             v-on:keyup ==> @keyup.enter 혹은 @keyup.space
             v-on:keydown ==> @keydown
          => 프로그램
             => 반복수행을 할때 : 메소드 제작
             => 시작과 동시에 데이터 읽기
                ==================mounted()
             => 이전
             => 다음
             => 블록별 번호
         
         
          ex)리액트와 형식이 비슷하다
          class A{
             state={
                data()
             }
             componentDidMount(){mounted()}
             btnClick(){
             }
          }
          => react : 단독처리
             
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">{{message}}</h3>
			<input type="button" value="클릭" @click="change()"><br>
			<input type="text" size="20" ref="message" v-model="message">
		</div>
	</div>
	<script>
	let app=Vue.createApp({
		//데이터 설정
		data(){
			return{
				message:'Hello Vue'
			}
		},
		//데이터 제어 / 초기화
		methods:{
			change(){
				this.message="변경됨"
			}
		}
	}).mount('.container')
	</script>
</body>
</html>