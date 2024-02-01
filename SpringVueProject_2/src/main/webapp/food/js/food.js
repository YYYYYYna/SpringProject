	let app=vue.createApp({
		data(){
			return{
				food_list:[], //[]=ArrayList
				food_detail:{}, //{}=VO
				fno:0,
				isShow:false //이러면 해당부분 처음에는 안보임 false니까
			}
		},
		//브라우저가 실행이 되었을때 처리하는 부분 => window.onload / $(function(){})
		mounted(){
			axios.get('http://localhost:8080/web/food/list_vue.do').then(response=>{
				console.log(response.data)
				this.food_list=response.data
			}) 
		},
		methods:{
			detail(fno){
				this.isShow=true;
				this.fno=fno;
				axios.get('http://localhost:8080/web/food/food_detail_vue.do',{
					params:{
						fno:this.fno
					}
				}).then(response=>{
					console.log(response.data)
					this.food_detail=response.data;
				})
			}
		}
	}).mount('#app') //관리영역
