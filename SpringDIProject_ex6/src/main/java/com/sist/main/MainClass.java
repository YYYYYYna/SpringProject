package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import com.sist.dao.*;

public class MainClass {
	
	public static void main(String[] args) {
		
		//메모리생성
		ApplicationContext app=new ClassPathXmlApplicationContext();
		
		TestDAO dao=(TestDAO)app.getBean("app.xml");
		Scanner scan=new Scanner(System.in);
		while(true)
		{
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 목록보기");
			System.out.println("2. 상세보기");
			System.out.println("3. 추가하기");
			System.out.println("4. 수정하기");
			System.out.println("5. 삭제하기");
			System.out.println("6. 종료하기");
			System.out.println("=============");
			int menu=scan.nextInt();
			if(menu<1 || menu>6)
			{
				System.out.println("없는 메뉴 입니다.");
				continue;
			}
			if(menu==6)
			{
				System.out.println("프로그램 종료");
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
				System.out.print("번호입력:");
				int no=scan.nextInt();
				TestVO vo=dao.testDetailListData();
				System.out.println(vo.getNo()+" "+vo.getName()+" "+vo.getTest1());
			}
			else if(menu==3)
			{
				System.out.print("신규이름입력:");
				String name=scan.next();
				
				System.out.print("test1입력:");
				int test1=scan.nextInt();

				System.out.print("test2입력:");
				int test2=scan.nextInt();
				
				System.out.print("test3입력:");
				int test3=scan.nextInt();
				
				TestVO vo=new TestVO();
				vo.setName(name);
				vo.setTest1(test1);
				vo.setTest2(test2);
				vo.setTest3(test3);
				
				dao.testInsertData(vo);
				System.out.println("저장완료");
				
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
