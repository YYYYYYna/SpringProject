package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.sawon.Sawon;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=
				new ClassPathXmlApplicationContext("application.xml");
		
		//setmethod ��� ȣ���ϴ��� Ȱ���ϴ����� �帧�� �ľ��ϴ°��� �߿�
		//application.xml�ȿ��� �츮�� �������� ȣ����� �����ϴ� ������ �ľ��ϰ�
		//�߰��� property�� ����ؼ� ������������ �ִ�.
		
		//����ȯ ���1
		//Sawon sa1=(Sawon)app.getBean("sa1");
		
		//����ȯ ���2
		Sawon sa1=app.getBean("sa1",Sawon.class);
		
		System.out.println("���:"+sa1.getSabun());
		System.out.println("�̸�:"+sa1.getName());
		System.out.println("�μ�:"+sa1.getDept());
		System.out.println("����:"+sa1.getJob());
		System.out.println("�ٹ���:"+sa1.getLoc());
		
		System.out.println("==========");
		
		//����ȯ ���2
		Sawon sa2=app.getBean("sa2",Sawon.class);
				
		System.out.println("���:"+sa2.getSabun());
		System.out.println("�̸�:"+sa2.getName());
		System.out.println("�μ�:"+sa2.getDept());
		System.out.println("����:"+sa2.getJob());
		System.out.println("�ٹ���:"+sa2.getLoc());
	
	}
}
