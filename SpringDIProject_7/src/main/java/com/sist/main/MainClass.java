package com.sist.main;
import java.util.*;
import com.sist.dao.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		
		//가장먼저는~메모리할당~~
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
		System.out.print("번호입력:");
		int no=scan.nextInt();
		SeoulVO vo=dao.natureDetailData(no);
		System.out.println("번호:"+vo.getNo());
		System.out.println("장소:"+vo.getTitle());
		System.out.println("주소:"+vo.getAddress());
		System.out.println("소개:"+vo.getMsg());
		*/
		
		System.out.println("==================");
		Scanner scan=new Scanner(System.in);
		System.out.print("장소명입력:");
		String title=scan.next();
		List<SeoulVO> flist=dao.natureTitleDetailData(title);
		for(SeoulVO fvo:flist)
		{
			System.out.println("번호:"+fvo.getNo());
			System.out.println("장소:"+fvo.getTitle());
			System.out.println("주소:"+fvo.getAddress());
			System.out.println("소개:"+fvo.getMsg());
		}
	}
}
