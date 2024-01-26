package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.spring.Member;
import com.sist.spring.Sawon;

//�̺κ��� �������� �����ϴ� Ŭ������ �ƴϴ�
public class MainClass {
	public static void main(String[] args) {
		
		//�޸� �Ҵ�
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		
		//sa�� bean���� ����� id �� / sa=new Sawon();�� ����
		Sawon s=(Sawon)app.getBean("sa");
		
	    System.out.println("���:"+s.getSabun());
	    System.out.println("�̸�:"+s.getName());
	    System.out.println("����:"+s.getSex());
	    
	    
	    Member mem=(Member)app.getBean("mem");
	    System.out.println(mem);
	    
	    Member mem1=(Member)app.getBean("mem1");
	    System.out.println(mem1);
	    
	    Member mem2=(Member)app.getBean("mem2");
	    System.out.println(mem2);
	}
}
