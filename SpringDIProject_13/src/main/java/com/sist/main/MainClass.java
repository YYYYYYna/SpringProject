package com.sist.main;
/*
 *   DAO => �ڵ�����
 *   DAO => ���� 
 */

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

public class MainClass {
	public static void main(String[] args) {
		//xml�� ������ �����̳ʷ� ����
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