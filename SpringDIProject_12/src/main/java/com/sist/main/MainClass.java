package com.sist.main;
/* 
 *   주의사항 : 어노테이션이 올라간다해서 다 메모리할당하는건 아님!!
 *   
 *   [어노테이션]
 *   1. 메모리 할당 (선택적)
 *      => 스프링에서 기능별로 구분해서 사용
 *      @Component : 일반클래스 => ~Manager, MainClass
 *      @Repository : 저장소 => ~DAO
 *      @Service : DAO 여러개를 연결해서 사용 , BI
 *                 => 기능을 통합해서 사용
 *                 => 실무에서는 가장 많이 사용되는 어노테이션
 *                 => ~Service
 *      @Controller : Model (스트러치 : ~Action)
 *                 => BoardController
 *      @RestController : Model => 자바스크립트와 연결
 *       => VueJS
 *      @ControllerAdvice : 모든 Model클래스의 예외처리
 *      @Configuration : application.xml 자바로 설정
 *    
 *   2. DI
 *      @Autowired => 자동으로 객체 주소를 찾아서 주입
 *                 => 반드시 스프링에서 메모리 할당을 해야 자동주입 가능
 *      @
 *      @
 */

//아래의 과정을 통해 annotation이 올라간것만 메모리할당이 가능하도록 만들수 있음
@Component
class A
{
	public void display()
	{
		System.out.println("A:display call...");
	}
}
class B
{
	public void display()
	{
		System.out.println("B:display call...");
	}
}
@Component
class C
{
	public void display()
	{
		System.out.println("C:display call...");
	}
}
public class MainClass {
	public static void main(String[] args) {
		String[] cls= {"com.sist.main.A",
				"com.sist.main.B",
				"com.sist.main.C"};
		try {
			for(String s:cls)
			{
				Class clsName=Class.forName(s);
				if(clsName.isAnnotationPresent(Component.class)==false)
				{
					continue;
				}
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(obj);
			}
		}catch(Exception ex) {}
	}
}
