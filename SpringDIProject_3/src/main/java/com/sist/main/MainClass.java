package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.spring.Board;
import com.sist.spring.Notice;

public class MainClass {
	public static void main(String[] args) {
		//规过1
		//String[] xml={"application-board.xml", "application-notice.xml"};
		//ApplicationContext app=new ClassPathXmlApplicationContext("xml");
		
		//规过2
		ApplicationContext app=
				new ClassPathXmlApplicationContext("application-*.xml");
		Board b=app.getBean("board",Board.class);
		System.out.println("锅龋:"+b.getNo());
		System.out.println("捞抚:"+b.getName());
		System.out.println("力格:"+b.getSubject());
		
		Notice n=app.getBean("notice", Notice.class);
	}
}
