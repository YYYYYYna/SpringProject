package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.sawon.Sawon;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=
				new ClassPathXmlApplicationContext("application.xml");
		
		//setmethod 어떻게 호출하는지 활용하는지를 흐름을 파악하는것이 중요
		//application.xml안에서 우리가 생성부터 호출까지 관할하는 역할을 파악하고
		//중간에 property를 사용해서 값을넣을수도 있다.
		
		//형변환 방법1
		//Sawon sa1=(Sawon)app.getBean("sa1");
		
		//형변환 방법2
		Sawon sa1=app.getBean("sa1",Sawon.class);
		
		System.out.println("사번:"+sa1.getSabun());
		System.out.println("이름:"+sa1.getName());
		System.out.println("부서:"+sa1.getDept());
		System.out.println("직위:"+sa1.getJob());
		System.out.println("근무지:"+sa1.getLoc());
		
		System.out.println("==========");
		
		//형변환 방법2
		Sawon sa2=app.getBean("sa2",Sawon.class);
				
		System.out.println("사번:"+sa2.getSabun());
		System.out.println("이름:"+sa2.getName());
		System.out.println("부서:"+sa2.getDept());
		System.out.println("직위:"+sa2.getJob());
		System.out.println("근무지:"+sa2.getLoc());
	
	}
}
