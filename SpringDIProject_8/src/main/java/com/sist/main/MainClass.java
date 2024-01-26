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
		System.out.print("번호입력:");
		int no=scan.nextInt();
		SeoulVO vo=dao.seoulShopDetailData(no);
		System.out.println("번호:"+vo.getNo());
		System.out.println("장소:"+vo.getTitle());
		System.out.println("주소:"+vo.getAddress());
		System.out.println("소개:"+vo.getMsg());
		
		
		System.out.println("================");
		Scanner scan2=new Scanner(System.in);
		System.out.print("장소명입력:");
        String title=scan2.next();
        List<SeoulVO> slist=dao.seoulshopTitleDetailData(title);
        for(SeoulVO svo:slist)
        {
        	System.out.println("번호:"+svo.getNo());
    		System.out.println("장소:"+svo.getTitle());
    		System.out.println("주소:"+svo.getAddress());
    		System.out.println("소개:"+svo.getMsg());
        }
	}
}
