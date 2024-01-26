package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.StudentDAO;
import com.sist.dao.StudentVO;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");
		StudentDAO dao=(StudentDAO)app.getBean("dao");
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
				List<StudentVO> list=dao.studentListData();
				for(StudentVO vo:list)
				{
					System.out.println(vo.getHakbun()+" "+vo.getName());
				}
			}
			else if(menu==2)
			{
				System.out.print("�й� �Է�:");
				int hakbun=scan.nextInt();
				StudentVO vo=dao.studentDetailData(hakbun);
				System.out.println("�й�:"+vo.getHakbun());
				System.out.println("�̸�:"+vo.getName());
				System.out.println("����:"+vo.getKor());
				System.out.println("����:"+vo.getEng());
				System.out.println("����:"+vo.getMath());
			}
			else if(menu==3)
			{
				System.out.print("�̸� �Է�:");
				String name=scan.next();
				
				System.out.print("���� �Է�:");
				int kor=scan.nextInt();
				
				System.out.print("���� �Է�:");
				int eng=scan.nextInt();
				
				System.out.print("���� �Է�:");
				int math=scan.nextInt();
				
				StudentVO vo=new StudentVO();
				vo.setName(name);
				vo.setKor(kor);
				vo.setEng(eng);
				vo.setMath(math);
				
				dao.studentInsert(vo);
				System.out.println("����Ϸ�");
			}
			else if(menu==4)
			{
				System.out.print("�̸� �Է�:");
				String name=scan.next();
				
				System.out.print("���� �Է�:");
				int kor=scan.nextInt();
				
				System.out.print("���� �Է�:");
				int eng=scan.nextInt();
				
				System.out.print("���� �Է�:");
				int math=scan.nextInt();
				
				System.out.print("�й� �Է�:");
				int hakbun=scan.nextInt();
				
				StudentVO vo=new StudentVO();
				vo.setName(name);
				vo.setKor(kor);
				vo.setEng(eng);
				vo.setMath(math);
				vo.setHakbun(hakbun);
				
				dao.studentUpdate(vo);
				System.out.println("�����Ϸ�");
			}
			else if(menu==5)
			{
				System.out.print("�й� �Է�:");
				int hakbun=scan.nextInt();
				
				dao.studentDelete(hakbun);
				System.out.println("�����Ϸ�");
			}
		}
	}
}
