package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.spring.Member;
import com.sist.spring.Sawon;

//이부분은 스프링이 관리하는 클래스가 아니다
public class MainClass {
	public static void main(String[] args) {
		
		//메모리 할당
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		
		//sa는 bean에서 명명한 id 값 / sa=new Sawon();의 형태
		Sawon s=(Sawon)app.getBean("sa");
		
	    System.out.println("사번:"+s.getSabun());
	    System.out.println("이름:"+s.getName());
	    System.out.println("성별:"+s.getSex());
	    
	    
	    Member mem=(Member)app.getBean("mem");
	    System.out.println(mem);
	    
	    Member mem1=(Member)app.getBean("mem1");
	    System.out.println(mem1);
	    
	    Member mem2=(Member)app.getBean("mem2");
	    System.out.println(mem2);
	}
}
