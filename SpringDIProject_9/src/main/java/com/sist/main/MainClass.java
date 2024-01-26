package com.sist.main;
/*
 * 1. XML 버전 =====> XML 파일 공유
 * 2. Annotation =====> 개별적으로 사용이 가능
 *      => 분업화 (가장많이사용)
 *    Annotation
 *      => 사용자 정의 클래스
 *      => 라이브러리 클래스 (어노테이션이 없다) => 상속 
 * 3. 사용자 클래스는 어노테이션 사용
 *    라이브러리 클래스 XML => 공통사용
 * ==========================================
 *    4버전 형식 => 5버전 설정파일 (자바) => 6버전 분산처리
 *    ---------------------------        |MSA(Spring Cloud)
 *     |현재국내동향  =============================
 *                   | 보안 (모든 파일 => 자바)
 * 
 * 스프링
 *   => 틀 (프로그램 형식을 정리 => 모든 대발자가 동일한 포맷)
 *      => 형식 통일 (형식을 제공) => 프레임 워크
 *   => 형식 통일
 *      1. DI => 객체 생성
 *               ------- 객체 생성시에 필요한 데이터를 주입
 *                       멤버변수 초기화
 *                       = setXxx()
 *                       = 생성자
 *               ------- 개발자가 사용
 *               -------
 *               객체 소멸
 *               =================== 컨테이너 (제공)
 *      2. 중복 코딩
 *         => OOP(Object Oriented Programming, 객체지향 프로그래밍)에서 단점 (자동호출이 안된다)
 *            => 중복 코딩 (메소드,메소드 여러개 => 클래스)
 *         => 자동 호출이 가능
 *         => AOP(Aspect Oriented Programming, 관점지향 프로그래밍) => 단어만 잘 알면 선방할수 있음
 *            => Before / After / After-Returning / After-Throwing
 *               위빙, JoinPointer,PointCut
 *            => public String 
 *               {
 *                  try{
 *                     @Before
 *                  }catch(Exception ex){
 *                     @After-Throwing
 *                  }finally{
 *                     @After
 *                  }
 *                  @After-Returning
 *                  return "";
 *               }
 *      --------------------------------------------------
 *      1. Application
 *      2. Web Application (MVC)              
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import com.sist.dao.*;

public class MainClass {
	public static void main(String[] args) {
		
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO");//이 "dao"는 app.xml의 아이디명칭
		                              //annotation을 사용해서 id가 없으면 클래스명이 id가됨
		                              //VO빼고 대부분의 클래스를 대입 가능
		
		List<EmpVO> list=dao.empDeptJoinData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEmpno()+" "+vo.getJob()+" "
		                      //+vo.getHiredate().toString()+" "+vo.getSal()+" "
            				  +vo.getDbday()+" "+vo.getSal()+" "
		                      +vo.getDvo().getDname()+" "+vo.getDvo().getLoc());
		}
		
		
	}
}



























