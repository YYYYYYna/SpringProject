package com.sist.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.sist.spring.Student;

public class MainClass2 {
	public static void main(String[] args) {
		GenericXmlApplicationContext app=
				new GenericXmlApplicationContext("app2.xml");
		Student s=(Student)app.getBean("std");
		//init�� �ڵ�����Ǵµ�
		System.out.println("�й�:"+s.getHakbun());
		System.out.println("�̸�"+s.getName());
		System.out.println("����"+s.getKor());
		System.out.println("����"+s.getMath());
		System.out.println("����"+s.getEng());	
		app.close();
	}
}
