package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.mapper.*;
import com.sist.config.CustomerConfig;
import com.sist.config.DataBaseConfig;
import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		
		//방법1
		//ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		
		//방법2
		Class[] cls= {CustomerConfig.class, DataBaseConfig.class};
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(cls);
		
		FoodDAO dao=(FoodDAO)app.getBean("fDAO");
		Scanner scan=new Scanner(System.in);
		while(true)
		{
			System.out.println("======== Menu ========");
			System.out.println("1. 이름으로 찾기");
			System.out.println("2. 주소로 찾기");
			System.out.println("3. 음식종류로 찾기");
			System.out.println("4. 이름+주소로 찾기");
			System.out.println("5. 이름+음식종류로 찾기");
			System.out.println("6. 주소+음식종류로 찾기");
			System.out.println("7. 이름+주소+음식종류로 찾기");
			System.out.println("======================");
			System.out.print("메뉴 선택:");
			int no=scan.nextInt();
			
			String data="";
			switch (no) {
			case 1: data="N";
				break;
			case 2: data="A";
				break;
			case 3: data="T";
				break;
			case 4: data="NA";
				break;
			case 5: data="NT";
				break;
			case 6: data="AT"; 
				break;
			case 7: data="NAT";
				break;
			default:
				System.out.println("프로그램 종료");
				break;
			}
			
			String[] fsArr=data.split("");
			Map map=new HashMap();
			System.out.print("검색어 입력:");
			String ss=scan.next();
			map.put("fsArr", fsArr);
			map.put("ss", ss);
			List<FoodVO> list=dao.foodFindData(map);
			
			for(FoodVO vo:list)
			{
				System.out.println(vo.getName()+" "+vo.getAddress()+" "+vo.getType());
			}
		}
	}
}
