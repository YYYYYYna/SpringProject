package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import com.sist.dao.*;

public class MainClass {
	
	public static void main(String[] args) {
		
		//�޸𸮻���
		ApplicationContext app=new ClassPathXmlApplicationContext();
		
		TestDAO dao=(TestDAO)app.getBean("app.xml");
		Scanner scan=new Scanner(System.in);
		while(true)
		{
			System.out.println("==== �޴� ====");
			System.out.println("1. ��Ϻ���");
			System.out.println("2. �󼼺���");
			System.out.println("3. �߰��ϱ�");
			System.out.println("4. �����ϱ�");
			System.out.println("5. �����ϱ�");
			System.out.println("6. �����ϱ�");
			System.out.println("=============");
			int menu=scan.nextInt();
			if(menu<1 || menu>6)
			{
				System.out.println("���� �޴� �Դϴ�.");
				continue;
			}
			if(menu==6)
			{
				System.out.println("���α׷� ����");
				break;
			}
			else if(menu==1)
			{
				List<TestVO> list=dao.testListData();
				for(TestVO vo:list)
				{
					System.out.println(vo.getNo()+" "+vo.getName());
				}
			}
			else if(menu==2)
			{
				System.out.print("��ȣ�Է�:");
				int no=scan.nextInt();
				TestVO vo=dao.testDetailListData();
				System.out.println(vo.getNo()+" "+vo.getName()+" "+vo.getTest1());
			}
			else if(menu==3)
			{
				System.out.print("�ű��̸��Է�:");
				String name=scan.next();
				
				System.out.print("test1�Է�:");
				int test1=scan.nextInt();

				System.out.print("test2�Է�:");
				int test2=scan.nextInt();
				
				System.out.print("test3�Է�:");
				int test3=scan.nextInt();
				
				TestVO vo=new TestVO();
				vo.setName(name);
				vo.setTest1(test1);
				vo.setTest2(test2);
				vo.setTest3(test3);
				
				dao.testInsertData(vo);
				System.out.println("����Ϸ�");
				
			}
			else if(menu==4)
			{
				
			}
			else if(menu==5)
			{
				
			}
		}
	}
}
