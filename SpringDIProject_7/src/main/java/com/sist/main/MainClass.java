package com.sist.main;
import java.util.*;
import com.sist.dao.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		
		//���������~�޸��Ҵ�~~
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		SeoulDAO dao=(SeoulDAO)app.getBean("dao");
		
		List<SeoulVO> list=dao.natureListData();
		for(SeoulVO vo:list)
		{
			System.out.println(vo.getNo()+" "+vo.getTitle());
		}
		/*
		System.out.println("==================");
		Scanner scan=new Scanner(System.in);
		System.out.print("��ȣ�Է�:");
		int no=scan.nextInt();
		SeoulVO vo=dao.natureDetailData(no);
		System.out.println("��ȣ:"+vo.getNo());
		System.out.println("���:"+vo.getTitle());
		System.out.println("�ּ�:"+vo.getAddress());
		System.out.println("�Ұ�:"+vo.getMsg());
		*/
		
		System.out.println("==================");
		Scanner scan=new Scanner(System.in);
		System.out.print("��Ҹ��Է�:");
		String title=scan.next();
		List<SeoulVO> flist=dao.natureTitleDetailData(title);
		for(SeoulVO fvo:flist)
		{
			System.out.println("��ȣ:"+fvo.getNo());
			System.out.println("���:"+fvo.getTitle());
			System.out.println("�ּ�:"+fvo.getAddress());
			System.out.println("�Ұ�:"+fvo.getMsg());
		}
	}
}
