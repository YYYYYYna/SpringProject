package com.sist.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass {

	public static void main(String[] args) {
		
		//�̺κ��� xml�������� �ƴ����� ���� �ٸ�
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		
		SeoulDAO sdao=(SeoulDAO)app.getBean("sDAO");
		GoodsDAO gdao=app.getBean("gDAO",GoodsDAO.class);
		String[] stable= {"","seoul_location","seoul_nature","seoul_shop"};
		String[] gtable= {"","goods_all","goods_best","goods_special","goods_new"};
		Scanner scan=new Scanner(System.in);
		while(true)
		{
			System.out.println("==== Menu ====");
			System.out.println("1. ����");
			System.out.println("2. ��ǰ");
			System.out.println("3. ����");
			System.out.println("==============");
			System.out.print("����:");
			int index=scan.nextInt();
			if(index==1)
			{
				System.out.println("==== ����޴� ====");
				System.out.println("1. ���");
				System.out.println("2. �ڿ� & ����");
				System.out.println("3. ����");
				System.out.println("================");
				System.out.print("���� ����:");
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
				System.out.print("�󼼺� ���� ��ȣ ����:");
				int no=scan.nextInt();
				map.put("no", no);
				SeoulVO vo=sdao.seoulDetailData(map);
				System.out.println("������:"+vo.getTitle());
				System.out.println("�Ұ�:"+vo.getMsg());
				System.out.println("�ּ�:"+vo.getAddress());
			}
			else if(index==2)
			{
				System.out.println("==== ����޴� ====");
				System.out.println("1. ��ü ��ǰ");
				System.out.println("2. ����Ʈ ��ǰ");
				System.out.println("3. Ư�� ��ǰ");
				System.out.println("4. �Ż�ǰ");
				System.out.println("================");
				System.out.print("����޴� ����:");
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
				System.out.print("�󼼺� ��ǰ ��ȣ ����:");
				int no=scan.nextInt();
				map.put("no", no);
				GoodsVO vo=gdao.goodDetailData(map);
				System.out.println("��ǰ��:"+vo.getName());
				System.out.println("�Ұ�:"+vo.getSub());
				System.out.println("����:"+vo.getPrice());
				System.out.println("����:"+vo.getDiscount()+"%");
			}
			else
			{
				System.out.println("���α׷� ����");
				break;
			}
		}
	}
}
