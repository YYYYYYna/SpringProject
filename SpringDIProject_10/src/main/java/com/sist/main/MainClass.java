package com.sist.main;
import com.sist.dao.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		
		//�޸𸮻���
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		
		//���� ī�װ� ���� �����ֱ�
		CategoryDAO cDao=(CategoryDAO)app.getBean("cDAO");//CategoryDAO���Ͽ� @Repository�� id����
		List<CategoryVO> clist=cDao.foodCategoryData();
		for(CategoryVO vo:clist)
		{
			System.out.println(vo.getCno()+"."+vo.getTitle());
		}
		
		//ī�װ��� ���� �����ֱ� 1
		System.out.println("==========================");
		FoodDAO fDao=(FoodDAO)app.getBean("fDAO");
		Scanner scan=new Scanner(System.in);
		System.out.println("ī�װ� ����(1~30):");
	    int cno=scan.nextInt();
	    List<FoodVO> flist=fDao.foodCategoryListData(cno);
	    
	    //���� ī�װ� ���� �����ֱ�
	    CategoryVO vo1=cDao.categoryInfoData(cno);
	    System.out.println("==========================");
	    System.out.println("����ī�װ�����:"+vo1.getTitle());
	    System.out.println("����ī�װ�����:"+vo1.getSubject());
	    System.out.println("==========================");
	    
	    //ī�װ��� ���� �����ֱ� 2
	    for(FoodVO vo:flist)
	    {
	    	System.out.println(vo.getFno()+" "
	    			+vo.getName()+" "
	    			+vo.getAddress()+" "
	    			+vo.getPhone()+" "
	    			+vo.getType());
	    }
	    
	    //���� �󼼺���
	    System.out.println("==========================");
	    System.out.print("��������:");
	    int fno=scan.nextInt();
	    FoodVO vo=fDao.foodDetailData(fno);
	    System.out.println("��ü��:"+vo.getName()+"("+vo.getScore()+")");
	    System.out.println("�ּ�:"+vo.getAddress());
	    System.out.println("��ȭ:"+vo.getPhone());
	    System.out.println("��������:"+vo.getType());
	    System.out.println("���ݴ�:"+vo.getPrice());
	    System.out.println("�����ð�:"+vo.getTime());
	    System.out.println("����:"+vo.getParking());
	    System.out.println("�޴�:"+vo.getMenu());
	}
}
