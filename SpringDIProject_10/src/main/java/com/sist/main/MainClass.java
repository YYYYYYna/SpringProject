package com.sist.main;
import com.sist.dao.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		
		//메모리생성
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		
		//맛집 카테고리 종류 보여주기
		CategoryDAO cDao=(CategoryDAO)app.getBean("cDAO");//CategoryDAO파일에 @Repository로 id값줌
		List<CategoryVO> clist=cDao.foodCategoryData();
		for(CategoryVO vo:clist)
		{
			System.out.println(vo.getCno()+"."+vo.getTitle());
		}
		
		//카테고리별 맛집 보여주기 1
		System.out.println("==========================");
		FoodDAO fDao=(FoodDAO)app.getBean("fDAO");
		Scanner scan=new Scanner(System.in);
		System.out.println("카테고리 선택(1~30):");
	    int cno=scan.nextInt();
	    List<FoodVO> flist=fDao.foodCategoryListData(cno);
	    
	    //선택 카테고리 정보 보여주기
	    CategoryVO vo1=cDao.categoryInfoData(cno);
	    System.out.println("==========================");
	    System.out.println("선택카테고리제목:"+vo1.getTitle());
	    System.out.println("선택카테고리주제:"+vo1.getSubject());
	    System.out.println("==========================");
	    
	    //카테고리별 맛집 보여주기 2
	    for(FoodVO vo:flist)
	    {
	    	System.out.println(vo.getFno()+" "
	    			+vo.getName()+" "
	    			+vo.getAddress()+" "
	    			+vo.getPhone()+" "
	    			+vo.getType());
	    }
	    
	    //맛집 상세보기
	    System.out.println("==========================");
	    System.out.print("맛집선택:");
	    int fno=scan.nextInt();
	    FoodVO vo=fDao.foodDetailData(fno);
	    System.out.println("업체명:"+vo.getName()+"("+vo.getScore()+")");
	    System.out.println("주소:"+vo.getAddress());
	    System.out.println("전화:"+vo.getPhone());
	    System.out.println("음식종류:"+vo.getType());
	    System.out.println("가격대:"+vo.getPrice());
	    System.out.println("영업시간:"+vo.getTime());
	    System.out.println("주차:"+vo.getParking());
	    System.out.println("메뉴:"+vo.getMenu());
	}
}
