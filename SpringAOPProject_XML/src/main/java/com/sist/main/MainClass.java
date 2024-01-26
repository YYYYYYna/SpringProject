package com.sist.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
/*
 *   OOP => �ݺ� �ҽ��� �ִ� ��� 
 *          => �Ѱ��� Ŭ�����ȿ��� => �޼ҵ�
 *          => �������� Ŭ������ ���� => Ŭ���� 
 *          => �ڵ� ȣ�� (AOP)
 *   AOP => OOP�� ������ ����  
 *          
 */

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        		new ClassPathXmlApplicationContext("app.xml");
        MyDAO dao=(MyDAO)app.getBean("dao");
        dao.select();
        System.out.println("==================");
        dao.insert();
        System.out.println("==================");
        dao.update();
        System.out.println("==================");
        dao.delete();
        System.out.println("==================");
        		
	}

}