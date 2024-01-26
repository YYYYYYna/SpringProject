package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.SeoulGoodsConfig;
import com.sist.dao.GoodsDAO;
import com.sist.dao.GoodsVO;
import com.sist.dao.SeoulDAO;
import com.sist.dao.SeoulVO;
import com.sist.mapper.*;
public class MainClass {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		//방법1
		//ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		//방법2
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(SeoulGoodsConfig.class);
		
		GoodsDAO gdao=(GoodsDAO)app.getBean("gDAO");
		SeoulDAO sdao=(SeoulDAO)app.getBean("sDAO");
		
		//가져올 테이블이 여러개라서 이렇게 가져옴
		//그래서 mapper에서 ${table_name}으로 map으로 보낸거임
		String[] stable= {"","seoul_location","seoul_nature","seoul_shop"};
		String[] gtable= {"","goods_all","goods_best","goods_special","goods_new"};
		
		while(true)
		{
			System.out.println("==== Menu ====");
			System.out.println("1. 여행");
			System.out.println("2. 상품");
			System.out.println("3. 종료");
			System.out.println("==============");
			System.out.print("선택:");
			int index=scan.nextInt();
			if(index==1)
			{
				System.out.println("==== 서브메뉴 ====");
				System.out.println("1. 명소");
				System.out.println("2. 자연 & 관광");
				System.out.println("3. 쇼핑");
				System.out.println("================");
				System.out.print("여행 선택:");
				int sno=scan.nextInt();
				String tab_name=stable[sno];
				Map map=new HashMap();
				map.put("table_name",tab_name);
				List<SeoulVO> list=sdao.seoulListData(map);
				for(SeoulVO vo:list)
				{
					System.out.println(vo.getNo()+" "+vo.getTitle());
				}
				System.out.println("========================");
				System.out.print("상세볼 여행 번호 선택:");
				int no=scan.nextInt();
				map.put("no", no);
				SeoulVO vo=sdao.seoulDetailData(map);
				System.out.println("여행지:"+vo.getTitle());
				System.out.println("소개:"+vo.getMsg());
				System.out.println("주소:"+vo.getAddress());
			}
			else if(index==2)
			{
				System.out.println("==== 서브메뉴 ====");
				System.out.println("1. 전체 상품");
				System.out.println("2. 베스트 상품");
				System.out.println("3. 특가 상품");
				System.out.println("4. 신상품");
				System.out.println("================");
				System.out.print("서브메뉴 선택:");
				int gno=scan.nextInt();
				String table_name=gtable[gno];
				Map map=new HashMap();
				map.put("table_name", table_name);
				List<GoodsVO> list=gdao.goodsListData(map);
				for(GoodsVO vo:list)
				{
					System.out.println(vo.getNo()+" "+vo.getName());
				}
				System.out.println("========================");
				System.out.print("상세볼 상품 번호 선택:");
				int no=scan.nextInt();
				map.put("no", no);
				GoodsVO vo=gdao.goodDetailList(map);
				System.out.println("상품명:"+vo.getName());
				System.out.println("소개:"+vo.getSub());
				System.out.println("가격:"+vo.getPrice());
				System.out.println("할인:"+vo.getDiscount()+"%");
			}
			else
			{
				System.out.println("프로그램 종료");
				break;
			}
		}
	}
}