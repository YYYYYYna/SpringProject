package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.SeoulDAO;
import com.sist.dao.SeoulVO;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		SeoulDAO dao=(SeoulDAO)app.getBean("dao");
		
		List<SeoulVO> list=dao.seoulShopListData();
		for(SeoulVO vo:list)
		{
			System.out.println(vo.getNo()+" "+vo.getTitle());
		}
		
		System.out.println("================");
		Scanner scan=new Scanner(System.in);
		System.out.print("��ȣ�Է�:");
		int no=scan.nextInt();
		SeoulVO vo=dao.seoulShopDetailData(no);
		System.out.println("��ȣ:"+vo.getNo());
		System.out.println("���:"+vo.getTitle());
		System.out.println("�ּ�:"+vo.getAddress());
		System.out.println("�Ұ�:"+vo.getMsg());
		
		
		System.out.println("================");
		Scanner scan2=new Scanner(System.in);
		System.out.print("��Ҹ��Է�:");
        String title=scan2.next();
        List<SeoulVO> slist=dao.seoulshopTitleDetailData(title);
        for(SeoulVO svo:slist)
        {
        	System.out.println("��ȣ:"+svo.getNo());
    		System.out.println("���:"+svo.getTitle());
    		System.out.println("�ּ�:"+svo.getAddress());
    		System.out.println("�Ұ�:"+svo.getMsg());
        }
	}
}
