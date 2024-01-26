package com.sist.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.sist.spring.Student;

public class MainClass2 {
	public static void main(String[] args) {
		GenericXmlApplicationContext app=
				new GenericXmlApplicationContext("app2.xml");
		Student s=(Student)app.getBean("std");
		//init은 자동실행되는듯
		System.out.println("학번:"+s.getHakbun());
		System.out.println("이름"+s.getName());
		System.out.println("국어"+s.getKor());
		System.out.println("수학"+s.getMath());
		System.out.println("영어"+s.getEng());	
		app.close();
	}
}
