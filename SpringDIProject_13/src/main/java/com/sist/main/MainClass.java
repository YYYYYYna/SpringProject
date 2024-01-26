package com.sist.main;
/*
 *   DAO => 자동주입
 *   DAO => 직접 
 */

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

public class MainClass {
	public static void main(String[] args) {
		//xml을 스프링 컨테이너로 전송
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		EmpDAO dao=(EmpDAO)app.getBean("eDAO");
		
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
	    {
	       System.out.println(vo.getEmpno()+" "
	                      +vo.getEname()+" "
	                      +vo.getJob());
	    }
	}
	
    
 }