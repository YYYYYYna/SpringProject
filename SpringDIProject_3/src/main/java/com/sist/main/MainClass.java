package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.spring.Board;
import com.sist.spring.Notice;

public class MainClass {
	public static void main(String[] args) {
		//���1
		//String[] xml={"application-board.xml", "application-notice.xml"};
		//ApplicationContext app=new ClassPathXmlApplicationContext("xml");
		
		//���2
		ApplicationContext app=
				new ClassPathXmlApplicationContext("application-*.xml");
		Board b=app.getBean("board",Board.class);
		System.out.println("��ȣ:"+b.getNo());
		System.out.println("�̸�:"+b.getName());
		System.out.println("����:"+b.getSubject());
		
		Notice n=app.getBean("notice", Notice.class);
	}
}
