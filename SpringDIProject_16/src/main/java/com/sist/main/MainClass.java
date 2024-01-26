package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.FoodConfig;
import com.sist.dao.*;
import com.sist.service.*;
public class MainClass {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      // FoodService
      ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
      
	  //AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(FoodConfig.class);
      
      FoodService service=(FoodService)app.getBean("fService");
      Scanner scan=new Scanner(System.in);
      while(true)
      {
         System.out.println("===== Menu =====");
         System.out.println("1.�ѽ�");
         System.out.println("2.���");
         System.out.println("3.�Ͻ�");
         System.out.println("4.�߽�");
         System.out.println("================");
         System.out.println("����:");
         int no=scan.nextInt();
         String[] temp={"","�ѽ�","���","�Ͻ�","�߽�"};
         String data=temp[no];
         List<FoodVO> list=service.foodListData(data);
         for(FoodVO vo:list)
         {
            System.out.println(vo.getFno()+"."+vo.getName());
         }
         System.out.print("�� ���� ����:");
         int fno=scan.nextInt();
         FoodVO vo=service.foodDetailData(fno);
         System.out.println("��ü��:"+vo.getName());
         System.out.println("��������:"+vo.getType());
         System.out.println("�ּ�:"+vo.getAddress());
         System.out.println("���ݴ�:"+vo.getPrice());
         System.out.println("�����ð�:"+vo.getTime());
         System.out.println(vo.getContent());
      
      }
   }

}